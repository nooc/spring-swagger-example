package space.nixus.swaggex.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private long id;
    private String email;
    private String password;
    private int role;
}
