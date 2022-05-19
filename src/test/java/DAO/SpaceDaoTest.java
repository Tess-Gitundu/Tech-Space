package DAO;

import Database.DatabaseRule;
import Models.Location;
import Models.Space;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;


import java.util.List;

import static org.junit.Assert.*;

public class SpaceDaoTest {
    private Connection conn;
    private LocationDao locationDao;
    private SpaceDao spaceDao;

    @Before
    public void setUp() throws Exception {
        conn = DatabaseRule.sql2o.open();
        DatabaseRule.createTables(conn);
        spaceDao = new SpaceDao(DatabaseRule.sql2o);
        locationDao = new LocationDao(DatabaseRule.sql2o);
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        spaceDao.clearAll();
        Space space = setUpSpace();
        System.out.println(space.locationName);
        assertEquals("iHub coding space", space.getSpaceName());
    }

    @Test
    public void update() {
        spaceDao.clearAll();
        Location location = new Location("Kisumu");
        locationDao.clearAll();
        Space space = setUpSpace();
        locationDao.add(location);
        Space updatedSpace = new Space("iHub Tech space", "Tech space for open to all geeks", location.getId(), false);
        updatedSpace.setId(space.getId());
        spaceDao.update(space.getId(), updatedSpace);
        assertEquals("iHub Tech space", updatedSpace.getSpaceName());
    }

    @Test
    public void getAll() {
        spaceDao.clearAll();
        Location location = new Location("Kisumu");
        Space space1 = setUpSpace();
        locationDao.add(location);
        System.out.println(location.getId());
        Space space2 = new Space("iHub coding zone", "Tech space for open to all geeks", location.getId(), false);
        spaceDao.add(space2);
        assertEquals(2, spaceDao.getAll().size());
    }

    @Test
    public void findById() {
        spaceDao.clearAll();
        Space space = setUpSpace();
        System.out.println(spaceDao.findById(space.getId()).locationName);
        assertEquals(space.getSpaceName(), spaceDao.findById(space.getId()).getSpaceName());
    }

    @Test
    public void findFreeSpace() {
        Location location = new Location("Kisumu");
        locationDao.clearAll();
        spaceDao.clearAll();
        Space space = setUpSpace();
        locationDao.add(location);
        Space space1 = new Space("iHub coding zone", "Tech space for open to all geeks", location.getId(), false);
        assertEquals(space.getLocationId(), spaceDao.findFreeSpace(space.getLocationId()).get(0).getLocationId());
    }

    @Test
    public void deleteById() {
        spaceDao.clearAll();
        Space space = setUpSpace();
        spaceDao.deleteById(space.getId());
        assertEquals(null, spaceDao.findById(space.getId()));
    }

    @Test
    public void clearAll() {
        spaceDao.clearAll();;
        assertEquals(0, spaceDao.getAll().size());
    }

    //
    public Space setUpSpace(){
        Location location = new Location("Nairobi");
        locationDao.clearAll();
        locationDao.add(location);
        Space space = new Space("iHub coding space", "Tech space for open to all geeks", location.getId(), false);
        spaceDao.add(space);
        return space;
    }
}