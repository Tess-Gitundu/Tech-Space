package Models;

import DAO.LocationDao;
import Database.DB;

public class Space {
    private int id;
    private String spaceName;
    private String spaceDetails;
    private int locationId;
    private Boolean isFull;
    private Location location;

    private String locationName;

    private LocationDao locationDao = new LocationDao(DB.sql2o);

    public Space(String spaceName, String spaceDetails, int locationId, Boolean isFull) {
        this.spaceName = spaceName;
        this.spaceDetails = spaceDetails;
        this.locationId = locationId;
        this.isFull = isFull;
        setLocationName();
        setLocation();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public String getSpaceDetails() {
        return spaceDetails;
    }

    public int getLocationId() {
        return locationId;
    }

    public Boolean getIsFull() {
        return isFull;
    }

    public void setLocation() {
        this.location = locationDao.findById(this.locationId);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocationName() {
        this.locationName = getLocation().getLocation();
    }

    public String getLocationName() {
        return locationName;
    }
}
