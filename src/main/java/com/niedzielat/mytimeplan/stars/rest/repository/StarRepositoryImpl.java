package com.niedzielat.mytimeplan.stars.rest.repository;

import com.niedzielat.mytimeplan.stars.Star;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Component
public class StarRepositoryImpl implements StarRepository {

    private Map<Integer, Star> stars;
    private Integer recentlyRemovedId;

    public StarRepositoryImpl() {
        stars = new TreeMap<>();
        recentlyRemovedId = null;
    }

    @Override
    public List<Star> getStars() {
        return stars.values().stream().toList();
    }

    @Override
    public Star getStarById(Integer id) {
        if (stars.containsKey(id)) {
            return stars.get(id);
        } else  {
            throw new RuntimeException("No star with provided id = " + id + " exists");
        }
    }

    private int getNextId() {
        int nextId;
        if (Objects.nonNull(recentlyRemovedId)) {
            nextId = recentlyRemovedId;
            recentlyRemovedId = null;
        } else {
            nextId = stars.size();
        }
        return nextId;
    }

    @Override
    public void addStar(Star starToAdd) {
        int nextId = getNextId();
        if (!stars.containsKey(nextId)) {
            stars.put(nextId, starToAdd);
        } else {
            throw new RuntimeException("There were problems while generating id for new star: id = " + nextId + "is already taken");
        }
    }

    @Override
    public void updateStar(Integer id, Star newStar) {
        if (stars.containsKey(id)) {
            stars.put(id, newStar);
        } else {
            throw new RuntimeException("No star with provided id = " + id + " exists");
        }
    }

    @Override
    public void deleteStarById(Integer id) {
        if (stars.containsKey(id)) {
            stars.remove(id);
        } else {
            throw new RuntimeException("No star with provided id = " + id + " exists");
        }
    }
}
