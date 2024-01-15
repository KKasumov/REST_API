package com.kasumov.rest_api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;

    public Event() {
    }

    /*public Event(User user, File file) {
        this.user = user;
        this.file = file;
    }
*/
    public Event(Integer id, User user, File file) {
        this.id = id;
        this.user = user;
        this.file = file;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + id +
                ", \"user\":" + user +
                ", \"file\":" + file +
                "}";
    }
}
