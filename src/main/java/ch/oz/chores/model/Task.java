package ch.oz.chores.model;

import jakarta.persistence.*;

@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Category category;

  @Column(nullable = false)
  private String title;

  public Task() {

  }

  public Task(Category category, String title) {
    this.category = category;
    this.title = title;
  }

  public Task(Long id, Category category, String title) {
    this.id = id;
    this.category = category;
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
