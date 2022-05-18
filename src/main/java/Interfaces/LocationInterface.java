package Interfaces;

import Models.Location;

import java.util.List;

public interface LocationInterface {
    void add(Location location);

    List<Location> getAll();

    void update(int id, Location location);

    Location findById(int id);

    void deleteById(int id);

    void clearAll();
}
