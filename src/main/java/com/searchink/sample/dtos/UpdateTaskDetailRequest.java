package com.searchink.sample.dtos;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateTaskDetailRequest {
    @NotNull @NotEmpty
    public String title;
    public String description;

    @NotNull @Future
    public Date dueAt;

    @NotNull
    @Min(1) @Max(10)
    public int priority;
}
