import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommentTest {
    private UserTestFacade userTestFacade;
    private EventTestFacade eventTestFacadeFacade;
    private TicketTestFacade ticketTestFacade;
    private CommentTestFacade commentTestFacade;

    @Before
    public void setUp() {
        userTestFacade = new UserTestFacade();
        eventTestFacadeFacade = new EventTestFacade();
        ticketTestFacade = new TicketTestFacade();
        commentTestFacade = new CommentTestFacade();
    }

    @After
    public void tearDown() {
        userTestFacade.deleteAllUsers();
        eventTestFacadeFacade.deleteAllEvents();
        ticketTestFacade.deleteAllTickets();
        commentTestFacade.deleteAllComments();
    }

    @Test
    public void createCommentTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 10);
        String name = "Show de Rock";
        String description = "Banda XYZ";
        Date date = calendar.getTime();

        String login = "LS@";
        String nameUser = "Lia Silva";
        String email = "lia@example.com";
        String password = "teste123";
        String cpf = "987654321";
        Boolean isAdmin = false;

        userTestFacade.create(login, password, nameUser, cpf, email, isAdmin);

        String eventId = eventTestFacadeFacade.addEventInDataBaseWithPastDate(name, description, date);

        String commentId = commentTestFacade.create(userTestFacade.getUserIdByEmail(email), eventId, 2, "Bom evento!");

        assertNotNull(commentTestFacade.getById(commentId));
        assertEquals("Bom evento!", commentTestFacade.getContentById(commentId));
        assertEquals(2, commentTestFacade.getRatingCommentById(commentId));
        assertEquals(userTestFacade.getUserIdByEmail(email), commentTestFacade.getUserIdById(commentId));
        assertEquals(eventId, commentTestFacade.getEventIdById(commentId));
    }

    @Test
    public void readCommentByEventTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 10);
        Date date = calendar.getTime();


        String login = "LS@";
        String nameUser = "Lia Silva";
        String email = "lia@example.com";
        String password = "teste123";
        String cpf = "987654321";
        Boolean isAdmin = false;

        userTestFacade.create(login, password, nameUser, cpf, email, isAdmin);

        String firstEventId = eventTestFacadeFacade.addEventInDataBaseWithPastDate("Show de Rock", "Evento musical", date);
        String secondEventId = eventTestFacadeFacade.addEventInDataBaseWithPastDate("Jogo do Bahia", "Evento esportivo", date);

        String c1Id = commentTestFacade.create(userTestFacade.getUserIdByEmail(email), firstEventId, 4, "Bom evento!");
        String c2Id = commentTestFacade.create(userTestFacade.getUserIdByEmail(email), secondEventId, 5, "Bom evento!");
        String c3Id = commentTestFacade.create(userTestFacade.getUserIdByEmail(email), secondEventId, 3, "Bom evento!");

        assertNotNull(commentTestFacade.getById(c1Id));
        assertNotNull(commentTestFacade.getById(c2Id));
        assertNotNull(commentTestFacade.getById(c3Id));

        assertEquals(1, eventTestFacadeFacade.getCommentQuantityByEventId(firstEventId));
        assertEquals(2, eventTestFacadeFacade.getCommentQuantityByEventId(secondEventId));
    }
}
