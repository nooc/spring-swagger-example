package space.nixus.swaggex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import space.nixus.swaggex.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    Token findByValue(String value);

    @Modifying
    @Query(value = "DELETE FROM token WHERE expires < :timestamp",
    nativeQuery = true)
    void deleteByExpiresLessThan(@Param("timestamp") Long timestamp);
}
