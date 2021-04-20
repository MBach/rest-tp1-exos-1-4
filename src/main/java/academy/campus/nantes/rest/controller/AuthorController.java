package academy.campus.nantes.rest.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import academy.campus.nantes.rest.entity.Author;
import academy.campus.nantes.rest.repository.AuthorRepository;

@RestController
@RequestMapping("author")
public class AuthorController {

    static Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorRepository authorRepository;

    @ResponseBody
    @GetMapping("{id}")
    public Author getAuthorById(final @PathVariable("id") String authorId) {
        try {
            Optional<Author> author = authorRepository.findById(Integer.valueOf(authorId));
            return author.get();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @ResponseBody
    @PutMapping("{id}")
    public Author editAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(final @PathVariable("id") Integer authorId) {
        authorRepository.deleteById(authorId);
    }
}
