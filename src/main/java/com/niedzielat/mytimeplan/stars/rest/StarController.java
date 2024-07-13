package com.niedzielat.mytimeplan.stars.rest;


import com.niedzielat.mytimeplan.stars.Star;
import com.niedzielat.mytimeplan.stars.rest.service.RestStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/stars")
public class StarController {

    private final RestStarService restStarService;

    @Autowired
    public StarController(RestStarService restStarService) {
        this.restStarService = restStarService;
    }


    @GetMapping("/{starId}")
    public Star getStarById(@PathVariable(name = "starId") Integer id) {
        return restStarService.getStarById(id);
    }

    @PostMapping("/add")
    public void addStar(Star starToAdd) {
        restStarService.addStar(starToAdd);
    }

    @PostMapping("/{starId}")
    public void updateStar(@PathVariable(name = "starId") Integer id, @RequestBody Star newStar) {
        restStarService.updateStar(id, newStar);
    }

    @DeleteMapping("/{starId}")
    public void deleteStar(@PathVariable(name = "starId") Integer id) {
        restStarService.deleteStarById(id);
    }

    @GetMapping("/closest")
    public Iterable<Star> findClosestStars(@RequestParam("size") int size) {
        return restStarService.findClosestStars(size);
    }

    @GetMapping("/by-distances")
    Map<Long, Integer> getNumberOfStarsByDistances() {
        return restStarService.getNumberOfStarsByDistances();
    }

    @GetMapping("/get-unique")
    Collection<Star> getUniqueStars() {
        return restStarService.getUniqueStars();
    }
}
