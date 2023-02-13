package space.nixus.swaggex.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import space.nixus.swaggex.model.Book;
import space.nixus.swaggex.repository.BookRepository;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    /**
     * 
     * @param book
     * @return
     */
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }

    public Book getBook(long bookId) {
        return bookRepo.findById(bookId).get();
    }

    @Query
    public List<Book> findBooks(String search) {
        // trim and replace whitespaces by '%'
        var searchValue = search.trim().replaceAll("\\s+", "%");
        return bookRepo.findAllByKeyword(searchValue);
    }

    public boolean updateBook(Book book) {
        return bookRepo.save(book) != null;
    }

    public boolean deleteBook(long bookId) {
        bookRepo.deleteById(bookId);
        return true;
    }
}
