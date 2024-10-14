import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class EventTest {
    private EventTestFacade eventFacade;
    private UserTestFacade userFacade;

    @Before
    public void setUp() {
        eventFacade = new EventTestFacade();
        userFacade = new UserTestFacade();
    }

    @After
    public void tearDown() {
        eventFacade.deleteAllEvents();
        userFacade.deleteAllUsers();
    }

    @Test
    public void eventCreateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        String name = "Show de Rock";
        String description = "Banda";
        Date date = calendar.getTime();

        String login = "john";
        String nameUser = "john souza";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = true;

        userFacade.create(login, password, nameUser, cpf, email, isAdmin);

        String id = eventFacade.create(login, name, description, date);

        assertNotNull(eventFacade.getById(id));
        assertEquals("Show de Rock", eventFacade.getNameByEventId(id));
        assertEquals("Banda", eventFacade.getDescriptionByEventId(id));
        assertEquals(date.getYear(), eventFacade.getYearByEventId(id));
        assertEquals(date.getMonth(), eventFacade.getMonthByEventId(id));
        assertEquals(date.getDay(), eventFacade.getDayByEventId(id));
    }

    @Test
    public void addSeatTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        String name = "Show de Rock";
        String description = "Banda";
        Date date = calendar.getTime();

        String login = "john";
        String nameUser = "john souza";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = true;

        userFacade.create(login, password, nameUser, cpf, email, isAdmin);

        String id = eventFacade.create(login, name, description, date);

        eventFacade.addSeatByEventId("A1", id);

        List<String> seats =  eventFacade.getSeatsByEventId(id);
        assertTrue(seats.contains("A1"));
    }

    @Test
    public void removeSeatTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        String name = "Show de Rock";
        String description = "Banda";
        Date date = calendar.getTime();

        String login = "john";
        String nameUser = "john souza";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = true;

        userFacade.create(login, password, nameUser, cpf, email, isAdmin);

        String id = eventFacade.create(login, name, description, date);

        eventFacade.addSeatByEventId("A1", id);

        eventFacade.removeSeatByEventId("A1", id);

        List<String> seats =  eventFacade.getSeatsByEventId(id);
        assertFalse(seats.contains("A1"));
    }

    @Test
    public void activeEventTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        String name = "Show de Rock";
        String description = "Banda";
        Date date = calendar.getTime();

        String login = "john";
        String nameUser = "john souza";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = true;

        userFacade.create(login, password, nameUser, cpf, email, isAdmin);
        String id = eventFacade.create(login, name, description, date);

        assertTrue(eventFacade.getIsActiveByEventId(id));
    }

    @Test
    public void inactiveEventTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.SEPTEMBER, 10);
        String name = "Show de Rock";
        String description = "Banda";
        Date date = calendar.getTime();

        String login = "john";
        String nameUser = "john souza";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = true;

        userFacade.create(login, password, nameUser, cpf, email, isAdmin);
        String id = eventFacade.create(login, name, description, date);

        assertFalse(eventFacade.getIsActiveByEventId(id));
    }

}

