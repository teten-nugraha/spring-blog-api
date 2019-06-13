package com.blog.react.blogspringreact.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Identifier cannot blank")
    @JsonProperty("identifier")
    @Column(unique = true)
    private String identifier;

    @NotBlank(message = "Title cannot blank")
    @JsonProperty("title")
    @Column(unique = true)
    @Size(min = 5, max = 20, message = "Please fill between 5 to 20 character")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @JsonProperty("content")
    private String content;

    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = CascadeType.REFRESH
    )
    @JoinColumn(
        name = "category_id",
        nullable = false
    )
    private Category category;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = true)
    private Date published_At;

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

    public Post() {
    }
}
