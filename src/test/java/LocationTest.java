import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    private Location setupLocation() {
        return new Location("Nairobi");
    }

    @Test
    public void Location_instantiatesCorrectly() {
        Location location = setupLocation();
        assertTrue(location instanceof Location);
    }
}