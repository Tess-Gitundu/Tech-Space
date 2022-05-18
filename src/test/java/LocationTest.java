import DAO.LocationDao;
import Database.DB;
import Models.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private Connection conn;
    private LocationDao locationDao;


    @BeforeEach
    void setUp() {
        conn = DB.sql2o.open();
        DB.createTables(conn);
        locationDao = new LocationDao(DB.sql2o);
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    private Location setupLocation() {
        return new Location("Nairobi");
    }

    @Test
    public void Location_instantiatesCorrectly() {
        Location location = setupLocation();
        assertTrue(location instanceof Location);
    }

    @Test
    public void Location_getsSavedLocation() {
        Location location = setupLocation();
        assertEquals("Nairobi", location.getLocation());
    }

    @Test
    public void saveLocation(){
        Location location = setupLocation();
        locationDao.add(location);
        assertEquals("Nairobi", location.getLocation());
    }

    @Test
    public void findAllLocations(){
        locationDao.clearAll();
        Location location1 = setupLocation();
        locationDao.add(location1);
        Location location2 = setupLocation();
        locationDao.add(location2);
        assertEquals(2, locationDao.getAll().size());
    }

    @Test
    public void updateLocation(){
        Location location = setupLocation();
        locationDao.add(location);
        Location updatedLocation = new Location("Naivasha");
        locationDao.update(location.getId(), updatedLocation);
        assertEquals("Naivasha", updatedLocation.getLocation());
        assertEquals(location.getId(), updatedLocation.getId());
    }

    @Test
    public void findLocationByIdSuccess(){
        Location location = setupLocation();
        locationDao.add(location);
        assertEquals(location, locationDao.findById(location.getId()));
    }

    @Test
    public void testDeleteLocationById(){
        Location location = setupLocation();
        locationDao.add(location);
        locationDao.deleteById(location.getId());
        assertEquals(null, locationDao.findById(location.getId()));
    }

    @Test
    public void clearAll(){
        locationDao.clearAll();
        assertEquals(0, locationDao.getAll().size());
    }

}