import DAO.UserDao;
import Database.DB;
import Models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private UserDao userDao = new UserDao(DB.sql2o);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    public void clearDown() {
        userDao.clearAll();
    }

    //helper method
    private User setupUser() {
        return new User("Jane Doe","Nairobi","Java",true);
    }

    @Test
    public void instantiatesUserCorrectly() {
        User user = setupUser();
        assertTrue(user instanceof User);
    }

    //getter tests
    @Test
    public void getsNameOfSavedUser() {
        User user = setupUser();
        assertEquals("Jane Doe", user.getUserName());
    }

    @Test
    public void getsLocationOfSavedUser() {
        User user = setupUser();
        assertEquals("Nairobi", user.getUserLocation());
    }

    @Test
    public void getsLanguageDetailOfSavedUser() {
        User user = setupUser();
        assertEquals("Java", user.getLanguage());
    }

    @Test
    public void getsAvailabilityOfSavedUser() {
        User user = setupUser();
        assertTrue(user.isAvailable());
    }

    //crud tests
    @Test
    public void savesUserAndReturnsCorrectInfo() {
        User user = setupUser();
        userDao.save(user);
        User foundUser = userDao.findById(user.getId());
        assertEquals(user,foundUser);
    }

    @Test
    public void allUserInstancesAreSaved() {
        User user1 = setupUser();
        userDao.save(user1);
        User user2 = setupUser();
        userDao.save(user2);
        assert userDao.getAll() != null;
        assertEquals(2,userDao.getAll().size());
    }

    @Test
    void correctlyUpdatesUserInfo() {
        User user = setupUser();
        userDao.save(user);
        User newUser = new User("John Doe","Kisumu","Python",false);
        newUser.setId(user.getId());
        assertEquals("Jane Doe",user.getUserName());
        userDao.update(user.getId(), newUser);
        assertEquals("John Doe", newUser.getUserName());
    }

    @Test
    public void nullFieldsAreNotSaved() {
        User user = new User("","","",true);
        try {
            userDao.save(user);
            assert userDao.getAll() != null;
            assertEquals(1,userDao.getAll().size());
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}