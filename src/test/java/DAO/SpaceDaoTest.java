package DAO;

import Database.DB;
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
        conn = DB.sql2o.open();
        DB.createTables(conn);
        spaceDao = new SpaceDao(DB.sql2o);
        locationDao = new LocationDao(DB.sql2o);
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void populateDb() {
        Space space = new Space("Cyberspace hub", "Code code code", 3, false);
        spaceDao.add(space);
        assertEquals(true, true);
    }

    @Test
    public void add() {
        Space space = setUpSpace();
        assertEquals("iHub coding space", space.getSpaceName());
    }

    @Test
    public void update() {
        Location location = new Location("Nairobi");
        locationDao.add(location);
        Space space = setUpSpace();
        Space updatedSpace = new Space("iHub Tech space", "Tech space for open to all geeks", location.getId(), false);
        updatedSpace.setId(space.getId());
        spaceDao.update(space.getId(), updatedSpace);
        assertEquals("iHub Tech space", updatedSpace.getSpaceName());
    }

    @Test
    public void getAll() {
        spaceDao.clearAll();
        Space space1 = setUpSpace();
        Space space2 = setUpSpace();
        assertEquals(2, spaceDao.getAll().size());
    }

    @Test
    public void findById() {
        Space space = setUpSpace();
        assertEquals(space.getSpaceName(), spaceDao.findById(space.getId()).getSpaceName());
    }

    @Test
    public void findFreeSpace() {
        Location location = new Location("Nairobi");
        locationDao.add(location);
        spaceDao.clearAll();
        Space space = setUpSpace();
        Space space1 = setUpSpace();
        assertEquals(space.getLocationId(), spaceDao.findFreeSpace(space.getLocationId()).get(0).getLocationId());
    }

    @Test
    public void deleteById() {
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
        locationDao.add(location);
        Space space = new Space("iHub coding space", "Tech space for open to all geeks", location.getId(), false);
        spaceDao.add(space);
        return space;
    }
}