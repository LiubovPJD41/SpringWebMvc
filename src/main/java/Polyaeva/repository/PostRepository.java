package Polyaeva.repository;

import Polyaeva.exception.NotFoundException;
import Polyaeva.model.Post;
import Polyaeva.model.PostDTO;
import Polyaeva.model.PostDTOMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
@Repository
public class PostRepository {
    private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
    private final AtomicLong postsCounter = new AtomicLong(1L);
    private final PostDTOMapper postDTOMapper = new PostDTOMapper();

    public List<PostDTO> all() {

        return this.posts.values()
                .stream()
                .filter(post -> !post.isDeleted())
                .map(postDTOMapper)
                .collect(Collectors.toList());
    }

    public Optional<PostDTO> getById(long id) {
        return posts.values()
                .stream()
                .filter(post ->!post.isDeleted())
                .filter(post ->post.getId() == id)
                .findFirst()
                .map(postDTOMapper);
    }

    public PostDTO save(Post post) {
        if (post.isDeleted()) {
            throw new NotFoundException();
        }
        if (post.getId() == 0) {
            searchingNextCounter();
            post.setId(postsCounter.longValue());
            posts.put(post.getId(), post);
            return postDTOMapper.apply(post);
        }
        Post postUpdated = null; //if we have such ID
        for (Post pst : this.posts.values()) {
            if (Objects.equals(pst.getId(), post.getId())) {
                pst.setContent(post.getContent());
                postUpdated = pst;
            }
        }
        posts.put(post.getId(), post);
        if (postUpdated != null) {
            return postDTOMapper.apply(postUpdated);
        }
        return postDTOMapper.apply(post);
    }

    public void removeById(long id) {
        if (!this.posts.containsKey(id) ||this.posts.get(id).isDeleted()) {
            throw new NotFoundException("No post found");
        }
        this.posts.get(id).setDeleted();
        //this.posts.remove(id);
    }

    public void searchingNextCounter() {
        for (Post p : this.posts.values()) {
            if (p.getId() == postsCounter.longValue())
                postsCounter.addAndGet(1);
        }
    }
}