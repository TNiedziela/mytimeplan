package com.niedzielat.mytimeplan;

import com.niedzielat.mytimeplan.stars.Star;
import com.niedzielat.mytimeplan.stars.StarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


class StarServiceTest {

    @Test
    void findClosestStarsTest() {
        StarService starService = new StarService();
        List<Star> starsToTest = getStarsToTest();

        List<Star> closestFiveStars = starService.findClosestSTars(starsToTest, 5);

        Assertions.assertEquals(5, closestFiveStars.size());
        Assertions.assertEquals(closestFiveStars.get(0).getDistance(), 1111432L);
        Assertions.assertEquals(closestFiveStars.get(1).getDistance(), 1124156L);
        Assertions.assertEquals(closestFiveStars.get(2).getDistance(), 1223012L);
        Assertions.assertEquals(closestFiveStars.get(3).getDistance(), 2441487L);
        Assertions.assertEquals(closestFiveStars.get(4).getDistance(), 6891284L);
    }

    @Test
    void getNumberOfStarsByDistancesTest() {
        StarService starService = new StarService();
        List<Star> starsToTest = getStarsToTest();

        Map<Long, Integer> numberOfStarsByDistances = starService.getNumberOfStarsByDistances(starsToTest);
        Assertions.assertEquals(10, numberOfStarsByDistances.entrySet().size());
        Assertions.assertEquals(1, numberOfStarsByDistances.get(2441487L));
        Assertions.assertEquals(1, numberOfStarsByDistances.get(1111432L));
        Assertions.assertEquals(2, numberOfStarsByDistances.get(92418425L));
        Assertions.assertEquals(3, numberOfStarsByDistances.get(991925111L));

        List<Long> keyList = numberOfStarsByDistances.keySet().stream().toList();
        // checking if map is properly ordered by keys
        Assertions.assertEquals(List.of(1111432L, 1124156L, 1223012L, 2441487L, 6891284L, 7763140L, 87656945L, 92418425L, 534356177L, 991925111L), keyList);
    }

    @Test
    void getUniqueStarsTest() {
        StarService starService = new StarService();
        List<Star> starsToTest = getStarsToTest();

        Collection<Star> uniqueStars = starService.getUniqueStars(starsToTest);

        Assertions.assertEquals(5, uniqueStars.size());
        Assertions.assertTrue(uniqueStars.stream()
                .map(Star::getName)
                .toList()
                .containsAll(List.of("unique_star1", "unique_star2", "unique_star3", "unique_star4", "unique_star5")));
    }

    List<Star> getStarsToTest() {
        List<Star> stars = new ArrayList<>();
        stars.add(new Star("star", 1223012L)); // 3
        stars.add(new Star("star", 2441487L)); // 4
        stars.add(new Star("unique_star1", 92418425L)); // 8=9
        stars.add(new Star("star", 1111432L)); // 1
        stars.add(new Star("unique_star2", 92418425L)); // 8=9
        stars.add(new Star("unique_star3", 991925111L)); // 11=12=13
        stars.add(new Star("star", 7763140L)); // 6
        stars.add(new Star("star", 6891284L)); // 5
        stars.add(new Star("unique_star4", 534356177L)); // 10
        stars.add(new Star("star", 1124156L)); // 2
        stars.add(new Star("star", 87656945L)); // 7
        stars.add(new Star("star", 991925111L)); // 11=12=13
        stars.add(new Star("unique_star5", 991925111L)); // 11=12=13

        return stars;
    }

}
