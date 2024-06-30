package Polyaeva.model;

import java.util.Objects;

public class Post {
    private Long id;
    private String content;
    private boolean isDeleted = false;

    public Post(Long id, String content, boolean isDeleted) {
        this.id = id;
        this.content = content;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDeleted() {
        isDeleted = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) && content.equals(post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}