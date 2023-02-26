package com.kodilla.task.domain;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "subTask_id")
    private SubTask subTask;


    public User(Long id, String name, String lastName, Task task, SubTask subTask) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.task = task;
        this.subTask = subTask;


    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Task getTask() {
        return task;
    }

    public SubTask getSubTask() {
        return subTask;
    }
}
