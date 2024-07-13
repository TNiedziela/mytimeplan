package com.niedzielat.mytimeplan.stars;

import java.util.*;
import java.util.stream.Collectors;

public class StarService {

    public List<Star> findClosestStars(List<Star> stars, int size) {
        if (stars.isEmpty()) {
            throw new RuntimeException("Stars list is empty");
        } else if (size <= 0) { // making an assumption that if provided size is bigger than number of stars, then all the stars will be returned
            throw new RuntimeException("Provided size must be bigger than 0, but got: size = " + size);
        }
        return stars.stream()
                .sorted(Comparator.comparingLong(Star::getDistance))
                .limit(size)
                .collect(Collectors.toList());
    }

    public Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        if (stars.isEmpty()) {
            throw new RuntimeException("Stars list is empty");
        }
        Map<Long, Integer> starsMap = new HashMap<>();
        for (Star star : stars) {
            Long currentDistance = star.getDistance();
            if (starsMap.containsKey(currentDistance)) {
                starsMap.put(currentDistance, starsMap.get(currentDistance) + 1);
            } else {
                starsMap.put(currentDistance, 1);
            }
        }
        return new TreeMap<>(starsMap);
    }

    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        if (stars.isEmpty()) {
            throw new RuntimeException("Stars list is empty");
        }
        Map<String, Integer> nameToOccuranceMap = new HashMap<>();
        for (Star star : stars) {
            String currentName = star.getName();
            if (nameToOccuranceMap.containsKey(currentName)) {
                nameToOccuranceMap.put(currentName, nameToOccuranceMap.get(currentName) + 1);
            } else {
                nameToOccuranceMap.put(currentName, 1);
            }
        }
        List<String> uniqueNamesList = nameToOccuranceMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();

        return stars.stream()
                .filter(star -> uniqueNamesList.contains(star.getName()))
                .toList();
    }
}
