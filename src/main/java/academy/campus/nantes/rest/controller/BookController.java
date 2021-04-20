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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import academy.campus.nantes.rest.entity.Book;
import academy.campus.nantes.rest.repository.BookRepository;

@RestController
@RequestMapping("book")
public class BookController {

    static Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = "title")
    public List<Book> getBooksByTitle(@RequestParam(value = "title", defaultValue = "") String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books;
    }

    @ResponseBody
    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @ResponseBody
    @GetMapping("{id}")
    public Book getBookById(final @PathVariable("id") String bookId) {
        try {
            Optional<Book> book = bookRepository.findById(Integer.valueOf(bookId));
            return book.get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    
    @PutMapping
    public Book editBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    
    @DeleteMapping("{id}")
    public void deleteBook(final @PathVariable("id") Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}
