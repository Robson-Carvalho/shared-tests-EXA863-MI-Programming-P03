import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import models.Ingresso;
import models.Usuario;
import models.Evento;
import service.TicketService;
import Stores.TicketStore;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TicketTest {

    private TicketService ticketService;
    private TicketStore ticketStore;
    private Usuario usuario;
    private Evento evento;

    @Before
    public void setUp() {
        // Inicializa as stores e serviços
        ticketStore = new TicketStore();
        ticketService = new TicketService(ticketStore);

        // Cria um usuário e evento fictício para os testes
        usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.DECEMBER, 10);
        Date data = calendar.getTime();
        evento = new Evento("Evento Teste", "Descrição do Evento Teste", data);
    }

    @Test
    public void testBuyTicket() {
        // Simula a compra de um ingresso
        Ingresso ticket = ticketService.createTicket(evento.getID(), "A1", usuario.getID());

        // Verifica se o ingresso foi criado e adicionado corretamente
        assertNotNull(ticket);
        assertEquals("A1", ticket.getAssento());
        assertEquals(usuario.getID(), ticket.getUserID());
    }

    @Test
    public void testListBoughtTickets() {
        // Compra 2 ingressos
        ticketService.createTicket(evento.getID(), "A1", usuario.getID());
        ticketService.createTicket(evento.getID(), "A2", usuario.getID());

        // Recupera os ingressos comprados pelo usuário
        List<Ingresso> tickets = ticketService.getBoughTicket(usuario);

        // Verifica se os ingressos foram adicionados e listados corretamente
        assertEquals(2, tickets.size());
        assertEquals("A1", tickets.get(0).getAssento());
        assertEquals("A2", tickets.get(1).getAssento());
    }

    @Test
    public void testCancelTicket() {
        // Compra um ingresso
        Ingresso ticket = ticketService.createTicket(evento.getID(), "A1", usuario.getID());

        // Cancela o ingresso
        ticket.cancelar();
        ticketStore.updateTicket(ticket);

        // Verifica se o ingresso foi cancelado
        assertFalse(ticket.isAtivo());
    }
}
