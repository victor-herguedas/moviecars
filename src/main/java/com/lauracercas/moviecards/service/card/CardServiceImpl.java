package com.lauracercas.moviecards.service.card;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Card;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Autor: Laura Cercas Ramos Proyecto: TFM Integración Continua con GitHub
 * Actions Fecha: 04/06/2024
 */
@Service
public class CardServiceImpl implements CardService {

	@Autowired
	ActorService actorService;

	@Autowired
	MovieService movieService;

	@Override
	public String registerActorInMovie(Card card) {
		final Integer actorId = card.getIdActor();
		final Integer movieId = card.getIdMovie();

		final Actor actor = this.actorService.getActorById(actorId);
		final Movie movie = this.movieService.getMovieById(movieId);

		if (actor == null || movie == null) {
			return Messages.ERROR_MESSAGE;
		}

		if (movie.existActorInMovie(actor)) {
			return Messages.CARD_ALREADY_EXISTS;
		}

		movie.addActor(actor);
		this.movieService.save(movie);
		return Messages.CARD_REGISTRATION_SUCCESS;
	}

}
