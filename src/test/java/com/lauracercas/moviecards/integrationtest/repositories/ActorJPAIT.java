package com.lauracercas.moviecards.integrationtest.repositories;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.repositories.ActorJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Autor: Laura Cercas Ramos Proyecto: TFM Integración Continua con GitHub
 * Actions Fecha: 04/06/2024
 */
@DataJpaTest
public class ActorJPAIT {

	@Autowired
	private ActorJPA actorJPA;

	@Test
	public void testSaveActor() {
		final Actor actor = new Actor();
		actor.setName("actor");
		actor.setBirthDate(new Date());
		actor.setDeadDate(new Date(102, 5, 22, 0, 0, 0));
		actor.setCountry("spain");

		final Actor savedActor = this.actorJPA.save(actor);

		assertNotNull(savedActor.getId());

		final Optional<Actor> foundActor = this.actorJPA.findById(savedActor.getId());

		assertTrue(foundActor.isPresent());
		assertEquals(savedActor, foundActor.get());
		assertEquals(new Date(102, 5, 22, 0, 0, 0), foundActor.get().getDeadDate());
	}

	@Test
	public void testFindById() {
		final Actor actor = new Actor();
		actor.setName("actor");
		actor.setBirthDate(new Date());
		actor.setDeadDate(new Date(102, 5, 22, 0, 0, 0));
		final Actor savedActor = this.actorJPA.save(actor);

		final Optional<Actor> foundActor = this.actorJPA.findById(savedActor.getId());

		assertTrue(foundActor.isPresent());
		assertEquals(savedActor, foundActor.get());
		assertEquals(new Date(102, 5, 22, 0, 0, 0), foundActor.get().getDeadDate());
	}
}
