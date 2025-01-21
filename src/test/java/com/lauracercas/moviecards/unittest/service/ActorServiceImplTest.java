// package com.lauracercas.moviecards.unittest.service;
//
// import com.lauracercas.moviecards.model.Actor;
// import com.lauracercas.moviecards.repositories.ActorJPA;
// import com.lauracercas.moviecards.service.actor.ActorServiceImpl;
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
// class ActorServiceImplTest {
//
// @Mock
// private ActorJPA actorJPA;
// private ActorServiceImpl sut;
// private AutoCloseable closeable;
//
// @BeforeEach
// void setUp() {
// this.closeable = openMocks(this);
// // sut = new ActorServiceImpl(actorJPA);
// }
//
// @AfterEach
// void tearDown() throws Exception {
// this.closeable.close();
// }
//
// @Test
// public void shouldGetAllActors() {
// final List<Actor> actors = new ArrayList<>();
// actors.add(new Actor());
// actors.add(new Actor());
//
// when(this.actorJPA.findAll()).thenReturn(actors);
//
// final List<Actor> result = this.sut.getAllActors();
//
// assertEquals(2, result.size());
// }
//
// @Test
// public void shouldGetActorById() {
// final Actor actor = new Actor();
// actor.setId(1);
// actor.setName("Sample Actor");
//
// when(this.actorJPA.getById(anyInt())).thenReturn(actor);
//
// final Actor result = this.sut.getActorById(1);
//
// assertEquals(1, result.getId());
// assertEquals("Sample Actor", result.getName());
// }
//
// @Test
// public void shouldSaveActor() {
// final Actor actor = new Actor();
// actor.setName("New Actor");
//
// when(this.actorJPA.save(actor)).thenReturn(actor);
//
// final Actor result = this.sut.save(actor);
//
// assertEquals("New Actor", result.getName());
// }
//
// }