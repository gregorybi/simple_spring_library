package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/delete/{id}")
public String deleteBook(@PathVariable String id) {
    if (bookRepository.existsById(id)) {
        bookRepository.deleteById(id);
        return "Book with ID " + id + " deleted successfully.";
    } else {
        return "Book with ID " + id + " not found.";
    }
}

@PutMapping("/update/{id}")
public String updateBook(@PathVariable String id, @RequestBody Book updatedBook) {
    return bookRepository.findById(id)
        .map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            bookRepository.save(book);
            return "Book with ID " + id + " updated successfully.";
        })
        .orElse("Book with ID " + id + " not found.");
}


}
