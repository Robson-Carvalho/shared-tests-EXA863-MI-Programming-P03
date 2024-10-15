package Tests.PurchaseTest;

import Facade.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PurchaseTest {
    private UserTestFacade userFacade;
    private TicketTestFacade ticketFacade;
    private EventTestFacade eventFacade;
    private PurchaseTestFace purchaseFacade;
    private CardTestFacade cardFacade;

    @Before
    public void setUp() {
        cardFacade = new CardTestFacade();
        userFacade = new UserTestFacade();
        eventFacade = new EventTestFacade();
        purchaseFacade = new PurchaseTestFace();
        ticketFacade = new TicketTestFacade();
    }

    @After
    public void tearDown() {
        cardFacade.deleteAllCards();
        userFacade.deleteAllUsers();
        eventFacade.deleteAllEvents();
        purchaseFacade.deleteAllPurchases();
        ticketFacade.deleteAllTickets();
    }

    @Test
    public void createReceiptPurchaseTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        String name = "Show de Rock";
        String description = "Banda XYZ";
        Date date = calendar.getTime();

        String login = "john";
        String nameUser = "john souza";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = true;

        String login1 = "jose";
        String nameUser1 = "jose souza";
        String email1 = "jose@example.com";
        String password1 = "teste456";
        String cpf1 = "123456009";
        Boolean isAdmin1 = false;

        String cardId = null;

        userFacade.create(login, nameUser, email, password, cpf, isAdmin);
        userFacade.create(login1, nameUser1, email1, password1, cpf1, isAdmin1);

        String eventId = eventFacade.create(login, name, description, date);

        assertNotNull(eventFacade.getById(eventId));

        String purchaseId = purchaseFacade.create(email1, eventId, null);

        assertNotNull(purchaseFacade.getById(purchaseId));
        assertEquals(eventId, purchaseFacade.getEventByPurchaseId(purchaseId));
        assertEquals(login1, purchaseFacade.getUserLoginByPurchaseId(purchaseId));
        assertNotNull(purchaseFacade.getTicketByPurchaseId(purchaseId));
        assertNull(purchaseFacade.getCardByPurchaseID(purchaseId));
    }

    @Test
    public void createCardPurchaseTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        String name = "Show de Rock";
        String description = "Banda XYZ";
        Date date = calendar.getTime();

        String login = "john";
        String nameUser = "john souza";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = true;

        String login1 = "jose";
        String nameUser1 = "jose souza";
        String email1 = "jose@example.com";
        String password1 = "teste456";
        String cpf1 = "123456009";
        Boolean isAdmin1 = false;

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2027, Calendar.MARCH, 10);
        String cardNumber = "12345678";
        Date expiryDate = calendar.getTime();
        int cvv = 789;

        String cardId = cardFacade.create(email, cardNumber, expiryDate, cvv);
        assertNotNull(cardFacade.getById(cardId));

        userFacade.create(login, nameUser, email, password, cpf, isAdmin);
        userFacade.create(login1, nameUser1, email1, password1, cpf1, isAdmin1);

        String eventId = eventFacade.create(login, name, description, date);

        assertNotNull(eventFacade.getById(eventId));

        String purchaseId = purchaseFacade.create(email1, eventId, cardId);

        assertNotNull(purchaseFacade.getById(purchaseId));
        assertEquals(eventId, purchaseFacade.getEventByPurchaseId(purchaseId));
        assertEquals(login1, purchaseFacade.getUserLoginByPurchaseId(purchaseId));
        assertNotNull(purchaseFacade.getTicketByPurchaseId(purchaseId));
        assertNotNull(purchaseFacade.getCardByPurchaseID(purchaseId));
    }
}
