package space.nixus.swaggex.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import space.nixus.swaggex.model.Book;

public class BookService {

    private static final String GET_BOOK_BY_ID = "SELECT * FROM Book WHERE id = ?";
    private static final String SEARCH_BOOK = "SELECT * FROM Book WHERE title LIKE ? or authors LIKE ? or isbn LIKE ?";

    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 
     * @param book
     * @return
     */
    public Book createBook(Book book) {
        throw new UnsupportedOperationException();
    }

    public Book getBook(long bookId) {
        throw new UnsupportedOperationException();
    }

    public List<Book> findBooks(String search) {
        throw new UnsupportedOperationException();
    }

    public boolean updateBook(Book book) {
        throw new UnsupportedOperationException();
    }

    public boolean deleteBook(long bookId) {
        throw new UnsupportedOperationException();
    }
}
