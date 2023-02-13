package space.nixus.swaggex.controller;

import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.security.RolesAllowed;
import space.nixus.swaggex.model.Book;
import space.nixus.swaggex.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/books")
@RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
public class BooksController {
    
    @Autowired
    BookService bookService;

    @GetMapping(value="/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBook(id);
    }
    
    @GetMapping(value="/find/{search}")
    public List<Book> findBooks(@PathVariable String search) {
        return bookService.findBooks(search);
    }

    @PostMapping(value="/{id}")
    public ResponseEntity<Boolean> updateBook(@PathVariable long id, @RequestBody Book book) {
        if(id != book.getId()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @PutMapping(value="/create")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping(value="/{id}")
    public boolean deleteBook(@RequestParam long id) {
        return bookService.deleteBook(id);
    }
}
