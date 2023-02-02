package space.nixus.swaggex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import space.nixus.swaggex.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM user u WHERE u.email = :email and u.password = :password",
    nativeQuery = true)
    User getUserByCredentials(@Param("email") String email, @Param("password") String password);
}
