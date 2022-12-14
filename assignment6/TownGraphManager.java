/**
 * Leave comments for this one. Had to be implemented
 * @author EGREGG
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * TownGraphManager will hold an object of your Graph. There are methods to populate the graph
 * (reading from a text file), add a town (vertices), add a road (edge), list all towns and all
 * roads, and list towns adjacent to a given town.
 * 
 * @author Fernando Gonzales-Vigil
 */
public class TownGraphManager implements TownGraphManagerInterface {

  private Graph graph = new Graph();

  /**
   * Adds a road to the graph
   * 
   * @param town1 - name of town 1
   * @param town2 - name of town 2
   * @param distance - distance of the road
   * @param roadName - name of road
   * @return true if the road was added successfully
   */
  @Override
  public boolean addRoad(String town1, String town2, int distance, String roadName) {
    if (graph.addEdge(new Town(town1), new Town(town2), distance, roadName) != null) {
      return true;
    }
    return false;
  }

  /**
   * Returns the name of the road that both towns are connected through
   * 
   * @param town1 - name of town 1
   * @param town2 - name of town 2
   * @return name of road if town 1 and town2 are in the same road, returns null if not
   */
  @Override
  public String getRoad(String town1, String town2) {
    return graph.getEdge(new Town(town1), new Town(town2)).getName();
  }


  @Override
  public boolean addTown(String name) {
    return graph.addVertex(new Town(name));
  }


  @Override
  public Town getTown(String name) {
    return graph.vertexSet().stream().filter(town -> town.getName().equals(name)).findAny()
        .orElse(null);
  }


  @Override
  public boolean containsTown(String name) {
    return graph.containsVertex(new Town(name));
  }


  @Override
  public boolean containsRoadConnection(String town1, String town2) {
    return graph.containsEdge(new Town(town1), new Town(town2));
  }


  @Override
  public ArrayList<String> allRoads() {
    return graph.edgeSet().stream().map(Road::getName).sorted()
        .collect(Collectors.toCollection(ArrayList::new));
  }


  @Override
  public boolean deleteRoadConnection(String town1, String town2, String road) {
    return graph.removeEdge(new Town(town1), new Town(town2), 0, road) != null;
  }

  @Override
  public boolean deleteTown(String name) {
    return graph.removeVertex(new Town(name));
  }

  @Override
  public ArrayList<String> allTowns() {
    return graph.vertexSet().stream().map(Town::getName).sorted()
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public ArrayList<String> getPath(String town1, String town2) {
    return graph.shortestPath(new Town(town1), new Town(town2));
  }

  public void populateTownGraph(File file) throws FileNotFoundException, IOException {
    
    InputStream in = new FileInputStream(file);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));

    br.lines()
    .map(s -> s.split(";|\\,"))
    .forEach(ar -> {
      addTown(ar[2]);
      addTown(ar[3]);
      addRoad(ar[2], ar[3], Integer.parseInt(ar[1]), ar[0]);
    });

    br.close();
  }

}