package space.nixus.swaggex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book {
    private long id;
    private String title;
    private String year;
    private String author;
    private String isbn;
}
