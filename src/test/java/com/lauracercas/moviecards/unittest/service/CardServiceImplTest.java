package com.lauracercas.moviecards.unittest.service;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Card;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.service.card.CardServiceImpl;
import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.util.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Autor: Laura Cercas Ramos Proyecto: TFM Integración Continua con GitHub
 * Actions Fecha: 04/06/2024
 */
class CardServiceImplTest {

	@Mock
	ActorService actorService;
	@Mock
	MovieService movieService;
	private CardServiceImpl sut;
	private AutoCloseable closeable;

	@BeforeEach
	public void setUp() {
		this.closeable = openMocks(this);
		this.sut = new CardServiceImpl(this.actorService, this.movieService);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.closeable.close();
	}

	@Test
	public void shouldRegisterCardAndReturnOkMessage() {
		final Card card = new Card();
		card.setIdActor(1);
		card.setIdMovie(2);

		final Actor actor = new Actor();
		actor.setId(1);
		actor.setName("Sample Actor");
		final Movie movie = new Movie();
		movie.setId(2);
		movie.setTitle("Sample Movie");
		movie.setActors(new ArrayList<>());

		when(this.actorService.getActorById(1)).thenReturn(actor);
		when(this.movieService.getMovieById(2)).thenReturn(movie);
		when(this.movieService.save(movie)).thenReturn(movie);

		final String result = this.sut.registerActorInMovie(card);

		assertEquals(Messages.CARD_REGISTRATION_SUCCESS, result);
	}

	@Test
	public void shouldNotCreateCardAndReturnErrorMessage() {
		final Card card = new Card();
		card.setIdActor(1);
		card.setIdMovie(2);

		when(this.actorService.getActorById(1)).thenReturn(null);
		when(this.movieService.getMovieById(2)).thenReturn(null);

		final String result = this.sut.registerActorInMovie(card);

		assertEquals(Messages.ERROR_MESSAGE, result);
	}

	@Test
	public void shouldNotRegisterCardWhenAlreadyExistAndReturnAlertMessage() {
		final Card card = new Card();
		card.setIdActor(1);
		card.setIdMovie(2);

		final Actor actor = new Actor();
		actor.setId(1);
		actor.setName("John Doe");

		final Movie movie = new Movie();
		movie.setId(2);
		movie.setTitle("Sample Movie");
		movie.setActors(List.of(actor));

		when(this.actorService.getActorById(1)).thenReturn(actor);
		when(this.movieService.getMovieById(2)).thenReturn(movie);

		final String result = this.sut.registerActorInMovie(card);

		assertEquals(Messages.CARD_ALREADY_EXISTS, result);

	}

}