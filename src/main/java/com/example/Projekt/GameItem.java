package com.example.Projekt;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "game")
public class GameItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String production;

    private String description;
}
