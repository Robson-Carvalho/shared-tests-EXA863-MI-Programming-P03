public class UserTest {
    private EventController eventController;
    private TicketController ticketController;
    private UserController userController;

    @Before
    public void setUp() {
        eventController = new EventController();
        ticketController = new TicketController();
        userController =  new UserController();
    }

    @After
    public void tearDown() {
        eventController.deleteAll();
        ticketController.deleteAll();
        userController.deleteAll();
    }


    @Test
    public void duplicateUserInDataBaseTest() throws Exception {
        userController.create("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        try{
             userController.create("johndoe", "senha456", "John Doe", "12345678901", "john.doe@example.com", false);
        }catch (Exception e){
            assertEquals(e.getMessage(), "Login, email e/ou cpf já está em uso.");
        }
    }

    @Test
    public void createUserInDataBaseTest() throws Exception {
        User user = userController.create("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        User createdUser = userController.getById(user.getId());

        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
    public void createUsersInDataBaseTest() throws Exception {
        userController.create("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        userController.create("lucas", "senha456", "John Doe", "12345678902", "lucas.doe@example.com", false);

        List<User> users = userController.getAll();

        assertEquals(2, users.size());
    }


    @Test
    public void deleteUserTest() throws Exception {
        User user = userController.create("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        userController.delete(user.getId());

        User createdUser = userController.getById(user.getId());

        assertNull(createdUser);
    }

    @Test
    public void updateUserTest() throws Exception {
        User firstUser = userController.create("john", "senha123", "John", "12345678901", "john@example.com", false);
        User secondUser = userController.create("lucas", "senha456", "Lucas", "12345678902", "lucas@example.com", false);

        firstUser.setEmail(secondUser.getEmail());

        try{
            userController.update(firstUser);
        }catch (Exception e){
            assertEquals(e.getMessage(), "Email já está em uso.");
        }

        firstUser.setEmail("john123@example.com");

        userController.update(firstUser);

        User updatedUser = userController.getById(firstUser.getId());

        assertEquals(updatedUser.getEmail(), firstUser.getEmail());

    }
}
