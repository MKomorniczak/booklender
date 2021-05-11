package se.lexicon.marek.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.marek.booklender.dto.LibraryUserDto;
import se.lexicon.marek.booklender.service.LibraryUserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/libraryUser")
public class LibraryUserController {
    LibraryUserService libraryUserService;

    @Autowired
    public void setLibraryUserService(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable("userId") int userId) {
/*        if (userId == 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        List<LibraryUserDto> libraryUserDtos = new ArrayList<>();
        LibraryUserDto libraryUserDto = libraryUserService.findById(userId);
        libraryUserDtos.add(libraryUserDto);
        return ResponseEntity.ok(libraryUserDtos);*/
        return ResponseEntity.ok(libraryUserService.findById(userId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<LibraryUserDto> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(libraryUserService.findByEmail(email));

    }

    @GetMapping("/")
    public ResponseEntity<List<LibraryUserDto>> findAll() {
        return ResponseEntity.ok(libraryUserService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<LibraryUserDto> create(@RequestBody LibraryUserDto libraryUserDto) {
        LibraryUserDto result = libraryUserService.create(libraryUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PutMapping("/")
    public ResponseEntity<LibraryUserDto> update(@RequestBody LibraryUserDto libraryUserDto) {
        libraryUserService.update(libraryUserDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<LibraryUserDto> deleteById(@PathVariable("userId") int userId){
        libraryUserService.delete(userId);
        return ResponseEntity.ok().build();
    }
}
