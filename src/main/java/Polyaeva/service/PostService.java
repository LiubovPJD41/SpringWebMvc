package Polyaeva.service;

import Polyaeva.model.Post;
import Polyaeva.model.PostDTO;
import Polyaeva.repository.PostRepository;
import org.springframework.stereotype.Service;
import Polyaeva.exception.NotFoundException;

import java.util.List;

@Service
public class PostService {
    // сервис завязан на интерфейс, а не на конкретную реализацию
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDTO> all() {
        return repository.all();
    }

    public PostDTO getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public PostDTO save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}