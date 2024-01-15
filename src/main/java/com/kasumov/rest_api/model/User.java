package com.kasumov.rest_api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> eventList;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.userName = name;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(Integer id, String name, List<Event> events) {
        this.id = id;
        this.userName = name;
        this.eventList = events;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"userName\":" + "\"" + userName + "\"" + "}";
    }
}
