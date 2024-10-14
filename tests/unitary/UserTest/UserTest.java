package test.java.com.UEFS.system.CommonTests.UserTest;

import main.java.UEFS.system.testFacade.UserTestFacade.UserTestFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserTest {
    private UserTestFacade userFacade;

    @Before
    public void setUp() {
        userFacade = new UserTestFacade();
    }

    @After
    public void tearDown() {
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
    public void getUsersTest() {
        assertEquals(0, userFacade.getSizeUsers());

        String login1 = "john#1";
        String name1 = "john";
        String email1 = "john@example.com";
        String password1 = "teste123";
        String cpf1 = "123456789";
        Boolean isAdmin1 = false;

        String login2 = "Ana@1";
        String name2 = "rob";
        String email2 = "rob@example.com";
        String password2 = "teste123";
        String cpf2 = "987654321";
        Boolean isAdmin2 = false;

        userFacade.create(login1, password1, name1, cpf1, email1, isAdmin1);
        userFacade.create(login2, password2, name2, cpf2, email2, isAdmin2);

        assertEquals(2, userFacade.getSizeUsers());
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

    @Test
    public void deleteUserTest() {
        String login = "john#1";
        String name = "john";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = false;

        userFacade.create(login, password, name, cpf, email, isAdmin);

        assertEquals(1, userFacade.getSizeUsers());

        userFacade.deleteByUserEmail(email);

        assertEquals(0, userFacade.getSizeUsers());
    }

    @Test
    public void duplicateUserTest() {
        String login1 = "john";
        String name1 = "john souza";
        String email1 = "john@example.com";
        String password1 = "teste123";
        String cpf1 = "123456789";
        Boolean isAdmin1 = false;

        String login2 = "john";
        String name2 = "john silva";
        String email2 = "john@example.com";
        String password2 = "teste123";
        String cpf2 = "987654321";
        Boolean isAdmin2 = false;

        userFacade.create(login1, password1, name1, cpf1, email1, isAdmin1);

        Exception exception = assertThrows(SecurityException.class, () -> {
            userFacade.create(login2, password2, name2, cpf2, email2, isAdmin2);
        });

        assertEquals("Login, email e/ou cpf já está em uso.", exception.getMessage());
    }

    @Test
    public void loginUserTest(){
        String login = "john";
        String name = "john souza";
        String email = "john@example.com";
        String password = "teste123";
        String cpf = "123456789";
        Boolean isAdmin = true;


        userFacade.create(login, password, name, cpf, email, isAdmin);

        assertTrue(userFacade.login(login, password));
    }
}
