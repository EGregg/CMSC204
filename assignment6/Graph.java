
/**
 * Author: Edward Gregg
 * Assignment: Assignment 6
 * Due Date: 13DEC2022
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph implements GraphInterface<Town, Road> {

	private ArrayList<String> shortestPath = new ArrayList<>();
	private Set<Town> towns = new HashSet<>();
	private Set<Road> roads = new HashSet<>();
	private Town destination;
	private Map<String, Town> prevVertex;

	/*
	 * Default constructor
	 */
	public Graph() {
		towns = new HashSet<Town>();
		roads = new HashSet<Road>();
		prevVertex = new HashMap<String, Town>();
	}

	@Override
	public Road addEdge(Town beginningPoint, Town destinationVertex, int distance, String description)
			throws IllegalArgumentException, NullPointerException {
		if (beginningPoint == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		if (!containsVertex(beginningPoint) || !containsVertex(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		Road road = new Road(beginningPoint, destinationVertex, distance, description);
		roads.add(road);
		return road;
	}

	@Override
	public Road getEdge(Town beginningPoint, Town destinationVertex) {
		for (Road r : roads) {
			if ((beginningPoint.equals(r.getSource()) || beginningPoint.equals(r.getDestination()))
					&& (destinationVertex.equals(r.getDestination()) || destinationVertex.equals(r.getSource()))) {
				return r;
			}
		}
		return null;
	}

	/**
	 * returns true if theres an edge going from the source to the destination
	 */
	@Override
	public boolean containsEdge(Town beginningPoint, Town destinationVertex) {
		for (Road r : roads) {
			if (r.contains(beginningPoint) && r.contains(destinationVertex)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * adds the vertex if its not already in the graph
	 */
	@Override
	public boolean addVertex(Town t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException();
		}
		if (!towns.contains(t)) {
			towns.add(t);
			return true;
		}
		return false;
	}

	/**
	 * checks if the graph contains the vertex
	 */
	@Override
	public boolean containsVertex(Town v) {
		for (Town t : towns) {
			if (t.equals(v)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * sets the edges of the graph
	 */
	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	/**
	 * returns all edges in a set connected to the vertex that's we want
	 */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> output = new HashSet<Road>();
		if (vertex == null) {
			throw new NullPointerException();
		}
		if (!containsVertex(vertex)) {
			throw new IllegalArgumentException();
		}
		for (Road r : roads) {
			if (r.getSource().equals(vertex) || r.getDestination().equals(vertex)) {
				output.add(r);
			}
		}
		return output;
	}

	/**
	 * removes the road from the graph
	 */
	@Override
	public Road removeEdge(Town beginningPoint, Town destinationVertex, int distance, String description) {
		Road road = null;
		for (Road r : roads) {
			if (r.contains(destinationVertex) && r.contains(beginningPoint) && (distance > -1) && description != null) {
				road = r;
			}
		}
		if (roads.remove(road)) {
			return road;
		}
		return null;
	}

	/**
	 * removes the town from the graph
	 */
	@Override
	public boolean removeVertex(Town t) {
		if (t == null) {
			return false;
		}
		return towns.remove(t);
	}

	/**
	 * returns a set of towns
	 */
	@Override
	public Set<Town> vertexSet() {
		return towns;
	}

	/**
	 * shortest path strategy found on geeksforgeeks
	 */
	@Override
	public ArrayList<String> shortestPath(Town beginningPoint, Town destinationVertex) {

		destination = destinationVertex;
		dijkstraShortestPath(beginningPoint);
		ArrayList<Road> roadPath = new ArrayList<>();

		boolean anySource = roads.stream().anyMatch(r -> r.contains(beginningPoint));
		boolean anyDest = roads.stream().anyMatch(r -> r.contains(destinationVertex));

		if (!anySource || !anyDest) {
			return new ArrayList<>();
		}

		for (int i = 0; i < shortestPath.size() - 1; i++) {
			Town source = new Town(shortestPath.get(i));
			Town destination = new Town(shortestPath.get(i + 1));
			Road road = getEdge(source, destination);
			roadPath.add(new Road(source, destination, road.getDistance(), road.getName()));
		}

		shortestPath.clear();
		return roadPath.stream().map(Road::toString).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Dijkstra's Shortest Path Method using adjacency
	 * 
	 * found on geeksforgeeks
	 * 
	 */
	@Override
	public void dijkstraShortestPath(Town beginningPoint) {
		List<Town> vertices = new ArrayList<>(towns);

		int[][] adjacencyMatrix = new int[towns.size()][towns.size()];

		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				if (i == j || !containsEdge(vertices.get(i), vertices.get(j))) {
					adjacencyMatrix[i][j] = 0;
				} else {
					int distance = getEdge(vertices.get(i), vertices.get(j)).getDistance();
					adjacencyMatrix[i][j] = adjacencyMatrix[j][i] = distance;
				}
			}
		}

		int startTown = 0;
		for (Town t : vertices) {
			if (!t.equals(beginningPoint)) {
				startTown++;
			} else {
				break;
			}
		}

		int endTown = 0;

		for (Town t : vertices) {
			if (!t.equals(destination)) {
				endTown++;
			} else {
				break;
			}
		}

		int numTowns = adjacencyMatrix[0].length;
		int[] shortDistances = new int[numTowns];
		boolean[] visited = new boolean[numTowns];

		for (int t = 0; t < numTowns; t++) {
			shortDistances[t] = Integer.MAX_VALUE;
			visited[t] = false;
		}

		shortDistances[startTown] = 0;
		int[] minPathLengths = new int[numTowns];
		minPathLengths[startTown] = -1;

		for (int i = 1; i < numTowns; i++) {
			int nearestTown = -1;
			int minDistance = Integer.MAX_VALUE;

			for (int townIndex = 0; townIndex < numTowns; townIndex++) {
				if (!visited[townIndex] && shortDistances[townIndex] < minDistance) {
					nearestTown = townIndex;
					minDistance = shortDistances[townIndex];
				}
			}

			visited[nearestTown] = true;

			for (int townIndex = 0; townIndex < numTowns; townIndex++) {
				int roadDistance = adjacencyMatrix[nearestTown][townIndex];
				if (roadDistance > 0 && ((minDistance + roadDistance) < shortDistances[townIndex])) {
					minPathLengths[townIndex] = nearestTown;
					shortDistances[townIndex] = minDistance + roadDistance;
				}
			}

		}
		computeShortPath(endTown, minPathLengths);
	}

	/**
	 * Find the shortest path method from the beginningPoint or beginningVertex to
	 * the endPoint or endingVertex
	 * 
	 * @param beginningPoint
	 * @param minPathLengths
	 */
	private void computeShortPath(int beginningPoint, int[] minPathLengths) {

		if (beginningPoint == -1) {
			return;
		}

		computeShortPath(minPathLengths[beginningPoint], minPathLengths);

		int townIndex = 0;

		for (Town t : towns) {
			if (townIndex == beginningPoint) {
				shortestPath.add(t.getName());
			}
			townIndex++;
		}
	}

}
