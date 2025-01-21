package com.lauracercas.moviecards.unittest.model;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActorTest {

	Actor actor = new Actor();

	@Test
	void testSetGetId() {
		final Integer idExample = 1;
		this.actor.setId(idExample);
		assertEquals(idExample, this.actor.getId());
	}

	@Test
	void testSetGetName() {
		final String nameExample = "Sample name";
		this.actor.setName(nameExample);
		assertEquals(nameExample, this.actor.getName());
	}

	@Test
	void testSetGetBirthDate() {
		final Date birthDateExample = new Date();
		this.actor.setBirthDate(birthDateExample);
		assertEquals(birthDateExample, this.actor.getBirthDate());
	}

	@Test
	void testSetGetDeadDate() {
		final Date deadDate = new Date();
		this.actor.setBirthDate(deadDate);
		assertEquals(deadDate, this.actor.getBirthDate());
	}

	@Test
	void testSetGetCountry() {
		final String countryExample = "Sample country";
		this.actor.setCountry(countryExample);
		assertEquals(countryExample, this.actor.getCountry());

	}

	@Test
	void testSetGetMovies() {
		final List<Movie> moviesExample = new ArrayList<Movie>();
		this.actor.setMovies(moviesExample);
		assertEquals(moviesExample, this.actor.getMovies());
	}

}
