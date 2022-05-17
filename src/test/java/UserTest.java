import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
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
    public void addsUserAndReturnsCorrectInfo() {
        User user = setupUser();
        user.add();
        User foundUser = User.findById(user.getId());
        assertEquals(user,foundUser);
    }

    @Test
    public void allUserInstancesAreSaved() {
        User user1 = setupUser();
        user1.add();
        User user2 = setupUser();
        user2.add();
        assert User.getAll() != null;
        assertEquals(2,User.getAll().size());
    }

    @Test
    void correctlyUpdatesUserInfo() {
        User user = setupUser();
        assertEquals("Jane Doe",user.getUserName());
        user.update("John Doe","Kisumu","Python",false);
        assertEquals("John Doe", user.getUserName());
    }

    @Test
    public void deletesUserById() {
        User user = setupUser();
        user.add();
        user.delete();
        assertNull(User.findById(user.getId()));
    }

    @Test
    public void nullFieldsAreNotSaved() {
        User user = new User("","","",true);
        try {
            user.add();
            assert User.getAll() != null;
            assertEquals(1,User.getAll().size());
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}