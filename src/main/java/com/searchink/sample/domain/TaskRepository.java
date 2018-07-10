package com.searchink.sample.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface TaskRepository extends CrudRepository<Task, UUID> {
    @Query("select t from Task t where t.status = com.searchink.sample.domain.TaskStatus.WAITING and t.dueDate <= current_date order by t.priority")
    List<Task> findAllTaskToExecute(Pageable pageable);
}
