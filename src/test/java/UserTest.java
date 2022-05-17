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
}