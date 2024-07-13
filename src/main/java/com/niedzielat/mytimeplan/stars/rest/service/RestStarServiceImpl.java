package com.niedzielat.mytimeplan.stars.rest.service;

import com.niedzielat.mytimeplan.stars.Star;
import com.niedzielat.mytimeplan.stars.StarService;
import com.niedzielat.mytimeplan.stars.rest.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class RestStarServiceImpl implements RestStarService {

    private final StarRepository starRepository;
    private final StarService starService = new StarService();

    @Autowired
    public RestStarServiceImpl(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @Override
    public Star getStarById(Integer id) {
        return starRepository.getStarById(id);
    }

    @Override
    public void addStar(Star starToAdd) {
        starRepository.addStar(starToAdd);
    }

    @Override
    public void updateStar(Integer id, Star newStar) {
        starRepository.updateStar(id, newStar);
    }

    @Override
    public void deleteStarById(Integer id) {
        starRepository.deleteStarById(id);
    }


    @Override
    public List<Star> findClosestStars(int size) {
        return starService.findClosestStars(starRepository.getStars(), size);
    }

    @Override
    public Map<Long, Integer> getNumberOfStarsByDistances() {
        return starService.getNumberOfStarsByDistances(starRepository.getStars());
    }

    @Override
    public Collection<Star> getUniqueStars() {
        return starService.getUniqueStars(starRepository.getStars());
    }
}
