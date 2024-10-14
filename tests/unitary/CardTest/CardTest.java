package Tests.CardTest;

// oq testar quando criar ID do usuário, nome, número do cartão, data de validade
//e CVV.

import Facade.CardTestFacade;
import Facade.UserTestFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class CardTest {
    private CardTestFacade cardFacade;
    private UserTestFacade userFacade;

    @Before
    public void setUp() {
        cardFacade = new CardTestFacade();
        userFacade = new UserTestFacade();
    }

    @After
    public void tearDown() {
        cardFacade.deleteAllCards();
        userFacade.deleteAllUsers();
    }

    @Test
    public void CardCreateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2026, Calendar.DECEMBER, 20);
        String cardNumber = "12345678";
        Date expiryDate = calendar.getTime();
        int cvv = 123;

        String login = "john";
        String nameUser = "john souza";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = false;

        userFacade.create(login, password, nameUser, cpf, email, isAdmin);

        String id = cardFacade.create(email, cardNumber, expiryDate, cvv);
        assertNotNull(cardFacade.getById(id));
        assertEquals("john souza", cardFacade.getUserNameByCardId(id));
        assertEquals("12345678", cardFacade.getCardNumberByCardId(id));
        assertEquals(expiryDate.getYear(), cardFacade.getYearByCardId(id));
        assertEquals(expiryDate.getMonth(), cardFacade.getMonthByCardId(id));
        assertEquals(expiryDate.getDay(), cardFacade.getDayByCardId(id));
    }

    @Test
    public void CardRemoveTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2026, Calendar.DECEMBER, 20);
        String cardNumber = "12345678";
        Date expiryDate = calendar.getTime();
        int cvv = 123;

        String email = "remove@example.com";
        String nameUser = "Jane Doe";
        String password = "teste123";
        String cpf = "987654321";
        Boolean isAdmin = false;

        userFacade.create("removeLogin", password, nameUser, cpf, email, isAdmin);
        String id = cardFacade.create(email, cardNumber, expiryDate, cvv);

        assertNotNull(cardFacade.getById(id));
        cardFacade.delete(id);
        assertNull(cardFacade.getById(id));
    }

    @Test
    public void CardDisableTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.NOVEMBER, 15);
        String cardNumber = "87654321";
        Date expiryDate = calendar.getTime();
        int cvv = 456;

        String email = "disable@example.com";
        String nameUser = "Mark Doe";
        String password = "test456";
        String cpf = "567890123";
        Boolean isAdmin = false;

        userFacade.create("disableLogin", password, nameUser, cpf, email, isAdmin);
        String id = cardFacade.create(email, cardNumber, expiryDate, cvv);

        cardFacade.disable(id);

        assertTrue(cardFacade.getStatusByCardId(id));
    }

    @Test
    public void CardDuplicateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2027, Calendar.MARCH, 10);
        String cardNumber = "12345678";
        Date expiryDate = calendar.getTime();
        int cvv = 789;

        String email = "duplicate@example.com";
        String nameUser = "Duplicate User";
        String password = "duplicate123";
        String cpf = "987654321";
        Boolean isAdmin = false;

        userFacade.create("duplicateLogin", password, nameUser, cpf, email, isAdmin);
        String id1 = cardFacade.create(email, cardNumber, expiryDate, cvv);
        assertNotNull(cardFacade.getById(id1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cardFacade.create(email, cardNumber, expiryDate, cvv);
        });

        assertEquals("Cartão com este número já existe.", exception.getMessage());
    }
}
