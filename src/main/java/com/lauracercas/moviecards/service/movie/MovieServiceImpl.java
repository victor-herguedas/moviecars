package com.lauracercas.moviecards.service.movie;

import com.lauracercas.moviecards.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

	private final RestTemplate restTemplate;
	private final String baseUrl = "https://moviecards-service-herguedas.azurewebsites.net/movies";

	public MovieServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Movie> getAllMovies() {
		// Llamada GET para obtener todas las películas
		final Movie[] movies = this.restTemplate.getForObject(this.baseUrl, Movie[].class);
		return Arrays.asList(movies != null ? movies : new Movie[0]);
	}

	@Override
	public Movie save(Movie movie) {
		if (movie.getId() != null) {
			// Llamada PUT para actualizar una película existente
            this.restTemplate.put(this.baseUrl, movie);
			return movie;
		} else {
			// Llamada POST para crear una nueva película
			return this.restTemplate.postForObject(this.baseUrl, movie, Movie.class);
		}
	}

	@Override
	public Movie getMovieById(Integer movieId) {
		// Construir la URL para obtener una película específica
		final String url = UriComponentsBuilder.fromHttpUrl(this.baseUrl).pathSegment(movieId.toString()).toUriString();

		return this.restTemplate.getForObject(url, Movie.class);
	}
}