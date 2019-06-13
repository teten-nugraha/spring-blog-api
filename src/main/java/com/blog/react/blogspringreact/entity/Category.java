package com.blog.react.blogspringreact.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category Identifier is required")
    @Size(min = 4, max = 5,message = "Please use 4 to 10 caracter")
    @Column(updatable = false,unique = true)
    @JsonProperty("categoryIdentifier")
    private String categoryIdentifier;

    @NotBlank(message = "Project name is required")
    @Column(unique = true)
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Description is required")
    @JsonProperty("description")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }

    public Category() {
    }
}
