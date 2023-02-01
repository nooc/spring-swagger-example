package space.nixus.swaggex.controller;

import org.springframework.web.bind.annotation.RestController;
import space.nixus.swaggex.model.Book;
import space.nixus.swaggex.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class BooksController {
    
    @Autowired
    BookService bookService;

    @GetMapping(value="/books/{id}")
    public Book getBook(@RequestParam long id) {
        return bookService.getBook(id);
    }
    
    @GetMapping(value="/books/find/{search}")
    public List<Book> findBooks(@RequestParam String search) {
        return bookService.findBooks(search);
    }

    @PostMapping(value="/books/{id}")
    public boolean updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @PutMapping(value="/books/create")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping(value="/books/{id}")
    public boolean deleteBook(@RequestParam long id) {
        return bookService.deleteBook(id);
    }
}
