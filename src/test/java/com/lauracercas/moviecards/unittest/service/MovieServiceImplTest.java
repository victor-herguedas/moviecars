package com.lauracercas.moviecards.unittest.service;

import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.service.movie.MovieServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Autor: Laura Cercas Ramos Proyecto: TFM Integración Continua con GitHub
 * Actions Fecha: 04/06/2024
 */
class MovieServiceImplTest {
	@Mock
	private RestTemplate template;
	@InjectMocks
	private MovieService sut = new MovieServiceImpl();
	private AutoCloseable closeable;

	@BeforeEach
	public void setUp() {
		this.closeable = openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.closeable.close();
	}

	@Test
	public void shouldGetAllMovies() {
		final Movie[] movies = new Movie[2];
		movies[0] = new Movie();
		movies[1] = new Movie();

		when(this.template.getForObject(anyString(), any())).thenReturn(movies);

		final List<Movie> result = this.sut.getAllMovies();

		assertEquals(2, result.size());
	}

	@Test
	public void shouldGetMovieById() {
		final Movie movie = new Movie();
		movie.setId(1);
		movie.setTitle("Sample Movie");

		when(this.template.getForObject(anyString(), any())).thenReturn(movie);

		final Movie result = this.sut.getMovieById(1);

		assertEquals(1, result.getId());
		assertEquals("Sample Movie", result.getTitle());
	}

}