package com.pikkvile.pikkr.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;
    @Column(name="name")
    String name;
    @Column(name="added")
    Date added;
    @Column(name="post_id")
    int postId;
    @Column(name="position")
    int position;
    @ManyToMany
    @JoinTable(name="photo_tags",
            joinColumns = @JoinColumn(name="photo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="tag_id", referencedColumnName = "id"))
    List<Tag> tags = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
