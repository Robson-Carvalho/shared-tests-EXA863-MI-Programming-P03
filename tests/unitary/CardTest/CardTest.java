package tests;

import models.Card;
import service.CardService;
import Stores.CardStore;
import controller.ClientController;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class CardTest {

    private ClientController clientController;
    private CardService cardService;
    private CardStore cardStore;

    @Before
    public void setUp() {
        cardStore = new CardStore();
        cardService = new CardService(cardStore);
        clientController = new ClientController(null, cardService, null,
                null, null, null);
    }

    @Test
    public void testAddCreditCard() {
        UUID userId = UUID.randomUUID();
        Date expiryDate = new Date();

        // Adiciona um cartão de crédito através do controller
        Card card = clientController.addCreditCard(
                "John Doe",
                "Visa",
                "1234567890123456",
                "987654321",
                expiryDate,
                123,
                userId
        );

        // Verifica se o cartão foi criado e os atributos são correspondentes
        assertNotNull(card);
        assertEquals("John Doe", card.getCardHolderName());
        assertEquals("Visa", card.getCardBrand());
        assertEquals("1234567890123456", card.getCardNumber());
        assertEquals("987654321", card.getAccountNumber());
        assertEquals(expiryDate, card.getExpiryDate());
        assertEquals(123, card.getCvv());
        assertTrue(card.isActive());
        assertEquals(userId, card.getUserID());

        // Verifica se o cartão foi armazenado na CardStore
        assertEquals(1, cardStore.get().size());
        assertEquals(card, cardStore.getByID(card.getID()));
    }

    @Test
    public void testRemoveCreditCard() {
        UUID userId = UUID.randomUUID();
        Date expiryDate = new Date();

        // Primeiro, cria e adiciona um cartão
        Card card = clientController.addCreditCard(
                "John Doe",
                "MasterCard",
                "9876543210987654",
                "123456789",
                expiryDate,
                456,
                userId
        );

        // Remove o cartão adicionado
        clientController.removeCreditCard(card.getID());

        // Verifica se o cartão foi removido
        assertEquals(0, cardStore.get().size());
        assertNull(cardStore.getByID(card.getID()));
    }

    @Test
    public void testDisableCreditCard() {
        UUID userId = UUID.randomUUID();
        Date expiryDate = new Date();

        // Primeiro, cria e adiciona um cartão
        Card card = clientController.addCreditCard(
                "Jane Smith",
                "American Express",
                "5555444433332222",
                "111122223333",
                expiryDate,
                789,
                userId
        );

        // Desativa o cartão
        Card disabledCard = clientController.disableCreditCard(card.getID());

        // Verifica se o cartão foi desativado
        assertNotNull(disabledCard);
        assertFalse(disabledCard.isActive());

        // Verifica se a mudança foi persistida na CardStore
        Card cardFromStore = cardStore.getByID(card.getID());
        assertNotNull(cardFromStore);
        assertFalse(cardFromStore.isActive());
    }
}