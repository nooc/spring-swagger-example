package space.nixus.swaggex.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import space.nixus.swaggex.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query(value="SELECT * FROM book b WHERE b.title LIKE '%:search%' or b.authors LIKE '%:search%' or b.isbn LIKE '%:search%'",
    nativeQuery = true)
    List<Book> findAllByKeyword(@Param("search") String search);
}
