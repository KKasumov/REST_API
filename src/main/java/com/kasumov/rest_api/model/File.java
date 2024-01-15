package com.kasumov.rest_api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_path")
    private String filePath;

    public File() {
    }

    public File(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public File(Integer id, String fileName, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + id +
                ", \"fileName\":" + "\"" + fileName + "\"" +
                ", \"filePath\":" + "\"" + filePath + "\"" +
                "}";
    }
}
