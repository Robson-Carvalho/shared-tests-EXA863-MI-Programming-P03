import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private UserTestFacade userFacade;

    @Before
    public void setUp() {
        /*  Método para instânciar a fachada de testes sempre 
            que um teste for executado.*/
        userFacade = new UserTestFacade();
    }

    @After
    public void tearDown() {
        /*  Método para apagar todos os usuários do database, visando que as 
            informações de um teste anterior afete o teste atual.*/
        userFacade.deleteAllUsers();
    }

    @Test
    public void createUserTest() {
        String login = "login";
        String name = "testUser";
        String email = "test@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = false;

        boolean userExists = userFacade.thereIsUserWithEmail(email);
        assertFalse(userExists);

        boolean createdUser = userFacade.create(login, password, name, cpf, email, isAdmin);
        assertTrue(createdUser);

        String createdUserLogin = userFacade.getLoginByUserEmail(email);
        String createdUserName = userFacade.getNameByUserEmail(email);
        String createdUserEmail = userFacade.getEmailByUserEmail(email);
        String createdUserPassword = userFacade.getPasswordByUserEmail(email);
        String createdUserCPF = userFacade.getCpfByUserEmail(email);
        boolean createdUserIsAdmin = userFacade.getIsAdminByUserEmail(email);

        assertEquals(login, createdUserLogin);
        assertEquals(name, createdUserName);
        assertEquals(email, createdUserEmail);
        assertEquals(password, createdUserPassword);
        assertEquals(cpf, createdUserCPF);
        assertEquals(isAdmin, createdUserIsAdmin);
    }

    @Test
    public void updateUserTest(){
        String login = "login";
        String name = "testUser";
        String email = "test@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = false;

        userFacade.create(login, password, name, cpf, email, isAdmin);

        userFacade.setNameByUserEmail("newName", email);
        userFacade.setPasswordByUserEmail("teste234", email);
        userFacade.setEmailByUserEmail("war@example.com", email);

        assertEquals("newName", userFacade.getNameByUserEmail("war@example.com"));
        assertEquals("teste234", userFacade.getPasswordByUserEmail("war@example.com"));
        assertEquals("war@example.com", userFacade.getEmailByUserEmail("war@example.com"));
    }
}
