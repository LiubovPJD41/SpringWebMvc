package Polyaeva.model;

public class PostDTO {
    Long id;
    String content;

    public PostDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
