package com.kodilla.task.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    @OneToMany(targetEntity = User.class, mappedBy ="task", fetch=FetchType.LAZY)
    private Set<User> userList = new HashSet<>();
    @OneToMany(targetEntity = SubTask.class, mappedBy ="task", fetch=FetchType.LAZY)
    private Set<SubTask> subTaskList = new HashSet<>();

    public Task(){

    }

    public Task(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public Set<SubTask> getSubTaskList() {
        return subTaskList;
    }
}
