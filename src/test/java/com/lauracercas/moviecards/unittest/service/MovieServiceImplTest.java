// package com.lauracercas.moviecards.unittest.service;
//
// import com.lauracercas.moviecards.model.Movie;
// import com.lauracercas.moviecards.repositories.MovieJPA;
// import com.lauracercas.moviecards.service.movie.MovieServiceImpl;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.anyInt;
// import static org.mockito.Mockito.when;
// import static org.mockito.MockitoAnnotations.openMocks;
//
/// **
// * Autor: Laura Cercas Ramos Proyecto: TFM Integración Continua con GitHub
// * Actions Fecha: 04/06/2024
// */
// class MovieServiceImplTest {
// @Mock
// private MovieJPA movieJPA;
// private MovieServiceImpl sut;
// private AutoCloseable closeable;
//
// @BeforeEach
// public void setUp() {
// this.closeable = openMocks(this);
// // sut = new MovieServiceImpl(movieJPA);
// }
//
// @AfterEach
// void tearDown() throws Exception {
// this.closeable.close();
// }
//
// @Test
// public void shouldGetAllMovies() {
// final List<Movie> movies = new ArrayList<>();
// movies.add(new Movie());
// movies.add(new Movie());
//
// when(this.movieJPA.findAll()).thenReturn(movies);
//
// final List<Movie> result = this.sut.getAllMovies();
//
// assertEquals(2, result.size());
// }
//
// @Test
// public void shouldGetMovieById() {
// final Movie movie = new Movie();
// movie.setId(1);
// movie.setTitle("Sample Movie");
//
// when(this.movieJPA.getById(anyInt())).thenReturn(movie);
//
// final Movie result = this.sut.getMovieById(1);
//
// assertEquals(1, result.getId());
// assertEquals("Sample Movie", result.getTitle());
// }
//
// @Test
// public void shouldSaveMovie() {
// final Movie movie = new Movie();
// movie.setTitle("New Movie");
//
// when(this.movieJPA.save(movie)).thenReturn(movie);
//
// final Movie result = this.sut.save(movie);
//
// assertEquals("New Movie", result.getTitle());
// }
//
// }