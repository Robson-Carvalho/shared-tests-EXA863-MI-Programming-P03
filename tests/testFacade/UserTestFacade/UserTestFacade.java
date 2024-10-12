public class UserTestFacade {
    private final UserController userController;

    public UserTestFacade() {
        this.userController = new UserController();
    }

    public boolean create(String login, String password, String name, String cpf, String email, Boolean isAdmin) {
        
    }

    public String getLoginByUserEmail(String email) {
        
    }

    public String getNameByUserEmail(String email) {
        
    }

    public String getEmailByUserEmail(String email) {
        
    }

    public String getPasswordByUserEmail(String email) {
        
    }

    public String getCpfByUserEmail(String email) {
    
    }

    public boolean getIsAdminByUserEmail(String email) {
        
    }

    public boolean thereIsUserWithEmail(String email)  {
        
    }

    public void deleteAllUsers(){
        
    }
}
