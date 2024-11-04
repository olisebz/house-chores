package ch.oz.chores.model;


import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;

    public Entry() {

    }

    public Entry(LocalDate date, Task task, User user) {
      this.date = date;
      this.task = task;
      this.user = user;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public LocalDate getDate() {
      return date;
    }

    public void setDate(LocalDate date) {
      this.date = date;
    }

    public Task getTask() {
      return task;
    }

    public void setTask(Task task) {
      this.task = task;
    }

    public User getUser() {
      return user;
    }

    public void setUser(User user) {
      this.user = user;
    }

}
