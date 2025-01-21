package com.lauracercas.moviecards.controller;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Card;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.service.card.CardService;
import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Autor: Laura Cercas Ramos Proyecto: TFM Integración Continua con GitHub
 * Actions Fecha: 04/06/2024
 */
@Controller
public class CardController {

	private final MovieService movieService;
	@Autowired
	ActorService actorService;
	private final CardService cardService;

	public CardController(MovieService movieService, ActorService actorService, CardService cardService) {
		this.movieService = movieService;
		this.actorService = actorService;
		this.cardService = cardService;
	}

	@GetMapping("registerActorMovie")
	public String showInfoForm(Model model) {
		this.prepareCardInfoForm(model);

		return "cards/registerActorMovieForm";
	}

	private void prepareCardInfoForm(Model model) {
		final List<Actor> actors = this.actorService.getAllActors();
		final List<Movie> movies = this.movieService.getAllMovies();

		model.addAttribute("actors", actors);
		model.addAttribute("movies", movies);
		model.addAttribute("card", new Card());
	}

	@PostMapping("register")
	public String registerCard(@ModelAttribute Card card, Model model) {
		final String result = this.cardService.registerActorInMovie(card);

		model.addAttribute("message", result);
		if (!result.equals(Messages.CARD_REGISTRATION_SUCCESS)) {
			this.prepareCardInfoForm(model);
			return "cards/registerActorMovieForm";
		}

		return "index";
	}
}
