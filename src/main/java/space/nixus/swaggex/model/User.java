package space.nixus.swaggex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public static final int ROLE_NONE     = 0x0000;
    public static final int ROLE_USER     = 0x0001;
    public static final int ROLE_ADMIN    = 0x1000;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    @Email
    private String email = null;
    @Column
    private String password = null;
    @Column(nullable = false)
    private Integer role = ROLE_NONE;
}
