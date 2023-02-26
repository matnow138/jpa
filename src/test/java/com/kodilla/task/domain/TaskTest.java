package com.kodilla.task.domain;

import com.kodilla.jpa.domain.Invoice;
import org.hibernate.jpa.QueryHints;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaskTest {


    @PersistenceUnit
    private EntityManagerFactory emf;

    private List<Long> insertExampleData(){
        Task t1 = new Task(null, "Task1", "In Progress");
        Task t2 = new Task(null, "Task2", "Done");

        SubTask s1 = new SubTask(null, "SubTask1", "In Progress",t1);
        SubTask s2 = new SubTask(null, "SubTask2", "In Progress",t1);
        SubTask s3 = new SubTask(null, "SubTask3", "Done",t2);

        User u1 = new User(null, "Name1", "Name1",t1,s1);
        User u2 = new User(null, "Name2", "Name2",t1,s2);
        User u3 = new User(null, "Name3", "Name3",t2,s1);



        t1.getUserList().add(u1);


        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(t1);
        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.persist(t1);
        em.persist(t2);
        em.flush();
        em.getTransaction().commit();
        em.close();

        return List.of(t1.getId(),t2.getId());



    }

    private String taskIds(List<Long> taskIds){
        return taskIds.stream()
                .map(n->""+n)
                .collect(Collectors.joining(","));
    }

    @Test
    void shouldNPlusOneProblemOccure(){
        //Given
        List<Long> savedTasks = insertExampleData();
        EntityManager em = emf.createEntityManager();

        //When
        System.out.println("****************** BEGIN OF FETCHING *******************");
        System.out.println("*** STEP 1 – query for invoices ***");

        TypedQuery<Task> query =em.createQuery(
                "from Task where id in (" + taskIds(savedTasks) +")", Task.class);



        EntityGraph<Task> eg = em.createEntityGraph(Task.class);
        eg.addAttributeNodes("userList");
        eg.addSubgraph("subTaskList").addAttributeNodes("task","userList");

        query.setHint("javax.persistence.fetchgraph", eg);

        List<Task> tasks = query.getResultList();



        for (Task task:tasks){
            System.out.println("*** STEP 2 – read data from task ***");
            System.out.println(task);
            for (User user:task.getUserList()){
                System.out.println("*** STEP 3 – read data from Task - Users ***");
                System.out.println(user);
            }
            for (SubTask subTask:task.getSubTaskList()){
                System.out.println("*** STEP 4 – read data from Task - subTask ***");
                System.out.println(task);

                for (User user:subTask.getUserList()){
                    System.out.println("*** STEP 5 – read data from SubTask - User ***");
                    System.out.println(user);
                }
            }
        }
        System.out.println("****************** END OF FETCHING *******************");


    }

}