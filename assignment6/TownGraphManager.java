
/**
 * Author: Edward Gregg
 * Assignment: Assignment 6
 * Due Date: 13DEC2022
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

public class TownGraphManager implements TownGraphManagerInterface {

	private Graph graph = new Graph();

	@Override
	public boolean addRoad(String town1, String town2, int distance, String roadName) {
		if (graph.addEdge(new Town(town1), new Town(town2), distance, roadName) != null) {
			return true;
		}
		return false;
	}

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
		return graph.vertexSet().stream().filter(town -> town.getName().equals(name)).findAny().orElse(null);
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
		return graph.edgeSet().stream().map(Road::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
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
		return graph.vertexSet().stream().map(Town::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

	public void populateTownGraph(File file) throws FileNotFoundException, IOException {

		InputStream in = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		br.lines().map(s -> s.split(";|\\,")).forEach(ar -> {
			addTown(ar[2]);
			addTown(ar[3]);
			addRoad(ar[2], ar[3], Integer.parseInt(ar[1]), ar[0]);
		});

		br.close();
	}

}