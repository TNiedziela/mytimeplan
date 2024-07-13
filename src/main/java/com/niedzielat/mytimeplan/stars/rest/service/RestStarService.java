package com.niedzielat.mytimeplan.stars.rest.service;

import com.niedzielat.mytimeplan.stars.Star;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RestStarService {

    Star getStarById(Integer id);
    void addStar(Star starToAdd);
    void updateStar(Integer id, Star newStar);
    void deleteStarById(Integer id);
    List<Star> findClosestStars(int size);
    Map<Long, Integer> getNumberOfStarsByDistances();
    Collection<Star> getUniqueStars();

}
