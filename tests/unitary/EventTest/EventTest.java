package test;

import Stores.EventStore;
import controller.AdminController;
import models.Evento;
import models.Review;
import models.Usuario;
import org.junit.After;
import service.EventService;
import service.ReviewService;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class EventTest {

    private AdminController adminController;
    private EventService eventService;
    private EventStore eventStore;
    private ReviewService reviewService;
    private Usuario admin;
    private Evento evento;

    @Before
    public void setUp() {
        eventStore = new EventStore();
        eventService = new EventService(eventStore, new Stores.ReviewStore());
        reviewService = new ReviewService(new Stores.ReviewStore());
        adminController = new AdminController(eventService, reviewService);
        admin = new Usuario("Admin", "admin@example.com", "password", "123456789", "g@gmail.com", true);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.DECEMBER, 10);
        Date data = calendar.getTime();
        evento = new Evento("Evento Teste", "Descrição do Evento Teste", data);
    }

    @Test
    public void testCreateEvent() {
        adminController.createEvent(admin, evento.getName(), evento.getDescription(), evento.getDate());

        Evento createdEvent = eventService.getEvent(evento.getName());
        assertNotNull(createdEvent);
        assertEquals(evento.getDescription(), createdEvent.getDescription());
    }

    @Test(expected = SecurityException.class)
    public void testCreateEventByUser() {
        Usuario user = new Usuario("User", "user@example.com", "password", "1230456789", "2g@gmail.com", false); // Criando um usuário comum
        adminController.createEvent(user, evento.getName(), evento.getDescription(), evento.getDate());
    }

    @Test
    public void testDeleteEvent() {
        adminController.createEvent(admin, evento.getName(), evento.getDescription(), evento.getDate());
        adminController.deleteEvent(evento.getName());

        Evento deletedEvent = eventService.getEvent(evento.getName());
        assertNull(deletedEvent);
    }

    @Test
    public void testListEvents() {
        adminController.createEvent(admin, evento.getName(), evento.getDescription(), evento.getDate());
        adminController.listEvents();

        Evento retrievedEvent = eventService.getEvent(evento.getName());
        List<Evento> events = eventStore.get();
        assertEquals(1, events.size());
        assertNotNull(retrievedEvent);
    }

    @Test(expected = SecurityException.class)
    public void testDeleteEventByUser() {
        Usuario user = new Usuario("User", "user@example.com", "password", "123456789", "g@gmail.com", false); // Criando um usuário comum
        adminController.deleteEvent(evento.getName());
    }

    @After
    public void tearDown() {
        eventService.deleteEvent(evento.getName());
    }
}