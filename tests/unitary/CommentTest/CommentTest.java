package test.java.com.UEFS.system.CommonTests.CommentTest;

import main.java.UEFS.system.controller.CommentController;
import main.java.UEFS.system.controller.EventController;
import main.java.UEFS.system.controller.UserController;
import java.lang.Exception;
import main.java.UEFS.system.model.Comment;
import main.java.UEFS.system.model.Event;
import main.java.UEFS.system.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class CommentTest {
    public EventController eventController;
    public CommentController commentController;
    public UserController userController;

    @Before
    public void setUp() {
        eventController = new EventController();
        commentController = new CommentController();
        userController = new UserController();
    }

    @After
    public void tearDown() {
        eventController.deleteAll();
        commentController.deleteAll();
        userController.deleteAll();
    }

    @Test
    public void commentCreateTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        User user = userController.create("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Event event = eventController.create(admin,"Show de Rock", "Banda XYZ", data);

        Comment commentCreated = commentController.create(user.getId(), event.getId(), 5, "Muito bom!");

        Comment comment = commentController.getById(commentCreated.getId());

        assertEquals(commentCreated.getId(), comment.getId());
        assertEquals(commentCreated.getUserID(), comment.getUserID());
        assertEquals(commentCreated.getEventID(), comment.getEventID());
        assertEquals(commentCreated.getContent(), comment.getContent());
        assertEquals(commentCreated.getRating(), comment.getRating());
    }

    @Test
    public void commentDeleteTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        User user = userController.create("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Event event = eventController.create(admin,"Show de Rock", "Banda XYZ", data);

        Comment comment = commentController.create(user.getId(), event.getId(), 5, "Muito bom!");

        Comment commentCreated = commentController.getById(comment.getId());

        assertEquals(commentCreated.getId(), comment.getId());

        commentController.delete(commentCreated.getId());

        assertNull(commentController.getById(commentCreated.getId()));
    }

    @Test
    public void testCreateFutureEventComment() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2026, Calendar.SEPTEMBER, 30);
        Date data = calendar.getTime();

        User admin = userController.create("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        User user = userController.create("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Event event = eventController.create(admin,"Show de Rock", "Banda XYZ", data);

        Exception exception = assertThrows(SecurityException.class, () -> {
            Comment commentCreated = commentController.create(user.getId(), event.getId(), 5, "Muito bom!");
            assertNull(commentCreated);

        });

        assertEquals("Comentário só pode ser adicionando após a realização do evento.", exception.getMessage());
    }
}
