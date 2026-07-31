package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(nullable = false, length = 20)
    private Role role;

    @Column(length = 20)
    private Provider provider;

    @Column(name = "social_id", length = 100)
    private String socialId;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks = new ArrayList<>();
}
