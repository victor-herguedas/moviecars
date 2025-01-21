package com.lauracercas.moviecards.unittest.service;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.service.actor.ActorServiceImpl;
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
class ActorServiceImplTest {

	@Mock
	private RestTemplate template;
	@InjectMocks
	private ActorService sut = new ActorServiceImpl();
	private AutoCloseable closeable;

	@BeforeEach
	void setUp() {
		this.closeable = openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.closeable.close();
	}

	@Test
	public void shouldGetAllActors() {
		final Actor[] actors = new Actor[2];
		actors[0] = new Actor();
		actors[1] = new Actor();

		when(this.template.getForObject(anyString(), any())).thenReturn(actors);

		final List<Actor> result = this.sut.getAllActors();

		assertEquals(2, result.size());
	}

	@Test
	public void shouldGetActorById() {
		final Actor actor = new Actor();
		actor.setId(1);
		actor.setName("Sample Actor");

		when(this.template.getForObject(anyString(), any())).thenReturn(actor);

		final Actor result = this.sut.getActorById(1);

		assertEquals(1, result.getId());
		assertEquals("Sample Actor", result.getName());
	}

}