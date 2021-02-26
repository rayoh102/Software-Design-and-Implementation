/*
 * Copyright (C) 2021 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder;

import graph.Graph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;

import java.util.*;


/**
 * This class is a map of the UW campus. It is a group of points in cartesian coordinates connected by their distance
 * to each other via paths.
 *
 * Fields:
 * @spec.specfield campusGraph : a graph of locations and distances // the campus in graph form
 * @spec.specfield nameToLocation: a map from short name to location // short name to coordinate
 * @spec.specfield buildings : a list of buildings on campus
 * @spec.specfield paths : a list of paths on campus
 */
public class CampusMap implements ModelAPI {

    // A graph representing the buildings and path locations on campus as well as the distance between them
    Graph<Point, Double> campusGraph;
    // A simple map connecting a building's short name to it's long name
    Map<String, String> shortToLong;
    // A simple map connecting a building's long name to it's location as a Point
    Map<String, Point> lNameToLocation;
    // A list of the buildings on campus
    List<CampusBuilding> buildings;
    // A list of the short names on campus
    List<String> shortNames;
    // A list of the paths on campus
    List<CampusPath> paths;

    /*
        RI: campusGraph, shortToLong, lNameToLocation, buildings, paths != null and no value in either shortToLong
        (the long name) or lNameToLocation (the point) is null
     */
    /*
        AF: This represents the UW campus. Parts of campus have a location in a coordinate system. Buildings also
        have a coordinate, along with a short and long name that they are known buy.
     */

    /**
     * Constructor for our campus map.
     * Initializes lists of buildings and paths for use in other methods as well as uses these lists to create the
     * "graph" of path and building locations as points in 2D, with distance as edges. Uses parser of buildings to
     * create a mapping for each building name to its corresponding space in 2D as a Point.
     */

    public CampusMap() {
        CampusPathsParser parser = new CampusPathsParser();
        shortToLong = new HashMap<>();
        lNameToLocation = new HashMap<>();
        shortNames = new LinkedList<>();
        buildings = parser.parseCampusBuildings("campus_buildings.tsv");
        paths = parser.parseCampusPaths("campus_paths.tsv");
        campusGraph = createGraph();
    }

    public Graph<Point, Double> createGraph() {
        Graph<Point, Double> graph = new Graph<>();
        for(CampusBuilding building : buildings) {
            Point build = new Point(building.getX(), building.getY());
            shortNames.add(building.getShortName());
            shortToLong.put(building.getShortName(), building.getLongName());
            lNameToLocation.put(building.getLongName(), build);
            graph.addNode(build);
        }
        for (CampusPath path : paths) {
            double pointX1 = path.getX1();
            double pointY1 = path.getY1();
            Point point1 = new Point(pointX1, pointY1);
            double pointX2 = path.getX2();
            double pointY2 = path.getY2();
            Point point2 = new Point(pointX2, pointY2);
            if (!graph.containsNode(point1)) {
                graph.addNode(point1);
            }
            if (!graph.containsNode(point2)) {
                graph.addNode(point2);
            }
            graph.addEdge(point1, point2, path.getDistance());
            graph.addEdge(point2, point1, path.getDistance());
        }
        return graph;
    }


    /**
     * @param shortName The short name of a building to query.
     * @return {@literal true} iff the short name provided exists in this campus map.
     */

    @Override
    public boolean shortNameExists(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        return shortToLong.containsKey(shortName);
    }

    /**
     * @param shortName The short name of a building to look up.
     * @return The long name of the building corresponding to the provided short name.
     * @throws IllegalArgumentException if the short name provided does not exist.
     */
    @Override
    public String longNameForShort(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        if(!shortNameExists(shortName)) {
            throw new IllegalArgumentException("Building with given short name doesn't exist");
        }
        return shortToLong.get(shortName);
    }

    /**
     * @return A mapping from all the buildings' short names to their long names in this campus map.
     */
    @Override
    public Map<String, String> buildingNames() {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        return Collections.unmodifiableMap(shortToLong);
    }

    /**
     * Finds the shortest path, by distance, between the two provided buildings.
     *
     * @param startShortName The short name of the building at the beginning of this path.
     * @param endShortName   The short name of the building at the end of this path.
     * @return A path between {@code startBuilding} and {@code endBuilding}, or {@literal null}
     * if none exists.
     * @throws IllegalArgumentException if {@code startBuilding} or {@code endBuilding} are
     *                                  {@literal null}, or not valid short names of buildings in
     *                                  this campus map.
     */
    @Override
    public Path findShortestPath(String startShortName, String endShortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        if(startShortName == null || endShortName == null) {
            throw new IllegalArgumentException("No input can be null");
        }
        if(!shortNameExists(startShortName) || !shortNameExists(endShortName)) {
            throw new IllegalArgumentException("Short name for at least one building wasn't found");
        }
        Point startPoint = lNameToLocation.get(shortToLong.get(startShortName));
        Point endPoint = lNameToLocation.get(shortToLong.get(endShortName));
        return DijkstrasAlgorithm.minimumCostPath(campusGraph, startPoint, endPoint);
    }
}


