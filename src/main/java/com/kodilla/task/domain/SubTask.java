package com.kodilla.task.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    @OneToMany(targetEntity = User.class, mappedBy ="subTask")
    private Set<User> userList = new HashSet<>();
    @ManyToOne
    @JoinColumn(name ="Task_id")
    private Task task;

    public SubTask(){

    }

    public SubTask(Long id, String name, String status, Task task) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.task = task;


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
}
