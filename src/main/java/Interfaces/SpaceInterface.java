package Interfaces;

import Models.Space;

import java.util.List;

public interface SpaceInterface {
    void add(Space space);

    void update(int id, Space space);

    List<Space> getAll();

    Space findById(int id);

    List<Space> findFreeSpace();

    void deleteById(int id);

    void clearAll();
}
