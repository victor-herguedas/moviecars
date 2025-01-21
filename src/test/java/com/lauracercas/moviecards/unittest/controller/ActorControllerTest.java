package com.lauracercas.moviecards.unittest.controller;

import com.lauracercas.moviecards.controller.ActorController;
import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.util.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Autor: Laura Cercas Ramos Proyecto: TFM Integración Continua con GitHub
 * Actions Fecha: 04/06/2024
 */
class ActorControllerTest {

	private ActorController controller;

	@Mock
	private ActorService actorServiceMock;

	private AutoCloseable closeable;
	@Mock
	private Model model;

	private static final String ACTOR = "actor";

	@BeforeEach
	void setUp() {
		this.closeable = openMocks(this);
		this.controller = new ActorController(this.actorServiceMock);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.closeable.close();
	}

	@Test
	public void shouldGoListActorAndGetAllActors() {
		final List<Actor> actors = new ArrayList<>();

		when(this.actorServiceMock.getAllActors()).thenReturn(actors);

		final String viewName = this.controller.getActorsList(this.model);

		assertEquals("actors/list", viewName);
	}

	@Test
	public void shouldInitializeActor() {
		final String viewName = this.controller.newActor(this.model);

		assertEquals("actors/form", viewName);

		verify(this.model).addAttribute(ACTOR, new Actor());
		verify(this.model).addAttribute("title", Messages.NEW_ACTOR_TITLE);
	}

	@Test
	public void shouldSaveActorWithNoErrors() {
		final Actor actor = new Actor();
		final BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);

		when(this.actorServiceMock.save(any(Actor.class))).thenReturn(actor);

		final String viewName = this.controller.saveActor(actor, result, this.model);

		assertEquals("actors/form", viewName);

		verify(this.model).addAttribute(ACTOR, actor);
		verify(this.model).addAttribute("title", Messages.EDIT_ACTOR_TITLE);
		verify(this.model).addAttribute("message", Messages.SAVED_ACTOR_SUCCESS);
	}

	@Test
	public void shouldUpdateActorWithNoErrors() {
		final Actor actor = new Actor();
		actor.setId(1);
		final BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);

		when(this.actorServiceMock.save(any(Actor.class))).thenReturn(actor);

		final String viewName = this.controller.saveActor(actor, result, this.model);

		assertEquals("actors/form", viewName);

		verify(this.model).addAttribute(ACTOR, actor);
		verify(this.model).addAttribute("title", Messages.EDIT_ACTOR_TITLE);
		verify(this.model).addAttribute("message", Messages.UPDATED_ACTOR_SUCCESS);
	}

	@Test
	public void shouldTrySaveActorWithErrors() {
		final Actor actor = new Actor();
		final BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(true);

		final String viewName = this.controller.saveActor(actor, result, this.model);

		assertEquals("actors/form", viewName);

		verifyNoInteractions(this.model);
	}

	@Test
	public void shouldGoToEditActor() {
		final Actor actor = new Actor();
		actor.setId(1);
		final List<Movie> movies = List.of(new Movie());
		actor.setMovies(movies);
		when(this.actorServiceMock.getActorById(actor.getId())).thenReturn(actor);

		final String viewName = this.controller.editActor(actor.getId(), this.model);

		assertEquals("actors/form", viewName);

		verify(this.model).addAttribute(ACTOR, actor);
		verify(this.model).addAttribute("movies", movies);
		verify(this.model).addAttribute("title", Messages.EDIT_ACTOR_TITLE);
	}

}