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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        Boolean isAdmin = true;

        userFacade.create(login, password, nameUser, cpf, email, isAdmin);

        String id = cardFacade.create(email, cardNumber, expiryDate, cvv);
        assertNotNull(cardFacade.getById(id));
        assertEquals("john souza", cardFacade.getUserNameByCardId(id));
        assertEquals("12345678", cardFacade.getCardNumberByCardId(id));
        assertEquals(expiryDate.getYear(), cardFacade.getYearByCardId(id));
        assertEquals(expiryDate.getMonth(), cardFacade.getMonthByCardId(id));
        assertEquals(expiryDate.getDay(), cardFacade.getDayByCardId(id));
    }
}
