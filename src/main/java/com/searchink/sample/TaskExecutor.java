package com.searchink.sample;

import com.searchink.sample.domain.Task;
import com.searchink.sample.domain.TaskRepository;
import com.searchink.sample.domain.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class TaskExecutor {
    private final TaskRepository taskRepository;
    private static final Logger log = LoggerFactory.getLogger(TaskExecutor.class);
    private static final Random Rnd = new Random();

    @Autowired
    public TaskExecutor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void runTask() throws InterruptedException {
        List<Task> tasks = this.taskRepository.findAllTaskToExecute(new PageRequest(0, 1));
        if (tasks.size() == 0) {
            log.debug("No tasks to run!");
            return;
        }

        Task task = tasks.get(0);
        log.info("Running task {}", task.getId());
        task.setStatus(TaskStatus.RUNNING);

        taskRepository.save(task);

        // NOTE: To simulate task running we will get random seconds to delay
        int timeToRun = Rnd.nextInt(100);
        log.debug("Run task for {} seconds", timeToRun);
        Thread.sleep(timeToRun * 1000);

        log.info("Finished executing task {}", task.getId());
        task.setStatus(TaskStatus.DONE);
        taskRepository.save(task);
    }
}
