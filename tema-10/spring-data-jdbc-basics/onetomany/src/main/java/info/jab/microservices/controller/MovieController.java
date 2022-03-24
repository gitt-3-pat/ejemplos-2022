package info.jab.microservices.controller;

import info.jab.microservices.model.Movie;
import info.jab.microservices.model.Rental;
import info.jab.microservices.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    @GetMapping("api/movies")
    public ResponseEntity<List<Movie>> getMovies() {

        List<Movie> list = StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok().body(list);
    }

    @Transactional
    @GetMapping("api/movies/add")
    public ResponseEntity<Movie> addPerson() {

        Set<Rental> set = new HashSet<>();
        Rental rental = new Rental();
        rental.setDuration(10);
        rental.setPrice(20);
        set.add(rental);

        Rental rental2 = new Rental();
        rental2.setDuration(100);
        rental2.setPrice(200);
        set.add(rental2);

        Movie movie = new Movie();
        movie.setTitle("Demo");
        movie.setDescription("Description");
        movie.setRental(set);

        movieRepository.save(movie);

        return ResponseEntity.ok().body(movie);
    }

    @Transactional
    @GetMapping("api/movies/add2")
    public ResponseEntity<Movie> addMovie2() {

        Set<Rental> set = new HashSet<>();
        Rental rental = new Rental();
        rental.setDuration(20);
        rental.setPrice(40);
        set.add(rental);

        Rental rental2 = new Rental();
        rental2.setDuration(200);
        rental2.setPrice(400);
        set.add(rental2);

        Movie movie = new Movie();
        movie.setTitle("Demo 2");
        movie.setDescription("Description 2");
        movie.setRental(set);

        movieRepository.save(movie);

        return ResponseEntity.ok().body(movie);
    }

    @Transactional
    @GetMapping("api/movies/update/{id}")
    public ResponseEntity<Movie> updatePerson(@PathVariable("id") Long id) {

        LOGGER.info("ID: {}", id);

        Optional<Movie> ouser = movieRepository.findById(id);

        if(ouser.isPresent()) {
            Movie user = ouser.get();
            //user.setCredentials(null);

            movieRepository.save(user);

            return ResponseEntity.ok().body(user);
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("api/movies/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {

        LOGGER.info("ID: {}", id);

        movieRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
