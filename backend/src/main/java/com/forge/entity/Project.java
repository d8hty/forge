package com.forge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jakarta.persistence.*;import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String repositoryUrl;

    private LocalDateTime createdAt;

    public Project() {}

    public Project(
        String name,
        String repositoryUrl,
        User user) {

    this.name = name;
    this.repositoryUrl = repositoryUrl;
    this.createdAt = LocalDateTime.now();
    this.user = user;
}
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setName(String name) {
    this.name = name;
}

public void setRepositoryUrl(String repositoryUrl) {
    this.repositoryUrl = repositoryUrl;
}
@ManyToOne
@JoinColumn(name = "user_id", nullable = false)
private User user;
public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}
}