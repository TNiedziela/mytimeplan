package com.niedzielat.mytimeplan.stars.rest.repository;

import com.niedzielat.mytimeplan.stars.Star;

import java.util.List;

/**
 *  Normally repository would be part of JPA connection to database, however as I'm not using database in the task I'm making a simplification.
 */
public interface StarRepository {

    List<Star> getStars();
    Star getStarById(Integer id);
    void addStar(Star starToAdd);
    void updateStar(Integer id, Star newStar);
    void deleteStarById(Integer id);
}
