package se.lexicon.marek.booklender.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class LibraryUserDto {
    private int userId;

    private LocalDate regDate;
    private String name;
    private String email;
}
