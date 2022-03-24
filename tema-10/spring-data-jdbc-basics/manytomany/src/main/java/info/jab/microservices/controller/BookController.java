package info.jab.microservices.controller;

import info.jab.microservices.model.Author;
import info.jab.microservices.model.Book;
import info.jab.microservices.repository.AuthorRepository;
import info.jab.microservices.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    @GetMapping("api/books")
    public ResponseEntity<List<Book>> getMovies() {

        List<Book> list = StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok().body(list);
    }

    @Transactional
    @GetMapping("api/books/add")
    public ResponseEntity<Book> addPerson() {

        Author author1 = new Author();
        author1.setName("John");
        author1.setBirthdate("01/01/2019");

        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("Peter");
        author2.setBirthdate("01/01/2020");
        authorRepository.save(author2);

        Book book = new Book();
        book.setTitle("Demo");
        book.setIsbn("978-1-56619-909-4");
        book.addAuthor(author1);
        book.addAuthor(author2);

        bookRepository.save(book);

        return ResponseEntity.ok().body(book);
    }

    @Transactional
    @GetMapping("api/books/add2")
    public ResponseEntity<Book> addBook2() {

        Author author1 = new Author();
        author1.setName("Dan");
        author1.setBirthdate("01/01/2019");

        authorRepository.save(author1);


        Author author2 = new Author();
        author2.setName("May");
        author2.setBirthdate("01/01/2019");

        authorRepository.save(author2);

        Book book = new Book();
        book.setTitle("Demo");
        book.setIsbn("978-1-56619-909-4");
        book.addAuthor(author1);
        //book.addAuthor(author2);

        bookRepository.save(book);

        return ResponseEntity.ok().body(book);
    }

    @Transactional
    @GetMapping("api/movies/update/{id}")
    public ResponseEntity<Book> updatePerson(@PathVariable("id") Long id) {

        LOGGER.info("ID: {}", id);

        /*
        Optional<Book> ouser = movieRepository.findById(id);

        if(ouser.isPresent()) {
            Movie user = ouser.get();
            //user.setCredentials(null);

            movieRepository.save(user);

            return ResponseEntity.ok().body(user);
        }

         */

        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("api/movies/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {

        LOGGER.info("ID: {}", id);

        bookRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
