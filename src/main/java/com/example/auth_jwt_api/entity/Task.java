package com.example.auth_jwt_api.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }
}
