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
    public void User_instantiatesCorrectly() {
        User user = setupUser();
        assertTrue(user instanceof User);
    }

    //getter tests
    @Test
    public void User_getsNameOfSavedUser() {
        User user = setupUser();
        assertEquals("Jane Doe", user.getUserName());
    }

    @Test
    public void User_getsLocationOfSavedUser() {
        User user = setupUser();
        assertEquals("Nairobi", user.getUserLocation());
    }

    @Test
    public void User_getsLanguageDetailOfSavedUser() {
        User user = setupUser();
        assertEquals("Java", user.getLanguage());
    }

    @Test
    public void User_getsAvailabilityOfSavedUser() {
        User user = setupUser();
        assertTrue(user.isAvailable());
    }

    //crud tests
    @Test
    public void UserAddsAndReturnsCorrectInfo() {
        User user = setupUser();
        user.add();
        User foundUser = User.findById(user.getId());
        assertEquals(user,foundUser);
    }

    @Test
    public void User_allInstancesAreSaved() {
        User user1 = setupUser();
        user1.add();
        User user2 = setupUser();
        user2.add();
        assertEquals(2,User.getAll().size());
    }

}