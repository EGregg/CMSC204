
/**
 * Leave comments for this one. Had to be implemented
 * @author EGREGG
 *
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
	
	public Graph() {
		towns = new HashSet<Town>();
		roads = new HashSet<Road>();
		prevVertex = new HashMap<String, Town>();
	}

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for (Road r : roads) {
			if ((sourceVertex.equals(r.getSource()) || sourceVertex.equals(r.getDestination()))
					&& (destinationVertex.equals(r.getDestination()) || destinationVertex.equals(r.getSource()))) {
				return r;
			}
		}
		return null;
//	  
//    if (sourceVertex == null || destinationVertex == null) {
//      return null;
//    }
//    return roads.stream().filter(r -> r.contains(sourceVertex) && r.contains(destinationVertex))
//        .findAny().orElse(null);
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int distance, String description)
			throws IllegalArgumentException, NullPointerException {
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		Road road = new Road(sourceVertex, destinationVertex, distance, description);
		roads.add(road);
		return road;
	}

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

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for (Road r : roads) {
			if (r.contains(sourceVertex) && r.contains(destinationVertex)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town t) {
		return towns.contains(t);
	}

	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	@Override
	public Set<Road> edgesOf(Town town) {
		if (town == null) {
			throw new NullPointerException();
		}
		Set<Road> roadSet = new HashSet<>();
		for (Road r : roads) {
			if (r.contains(town)) {
				roadSet.add(r);
			}
		}
		if (roadSet.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return roadSet;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int distance, String description) {
		Road road = null;
		for (Road r : roads) {
			if (r.contains(destinationVertex) && r.contains(sourceVertex) && (distance > -1) && description != null) {
				road = r;
			}
		}
		if (roads.remove(road)) {
			return road;
		}
		return null;
	}

	@Override
	public boolean removeVertex(Town t) {
		if (t == null) {
			return false;
		}
		return towns.remove(t);
	}

	@Override
	public Set<Town> vertexSet() {
		return towns;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {

		destination = destinationVertex;
		dijkstraShortestPath(sourceVertex);
		ArrayList<Road> roadPath = new ArrayList<>();

		boolean anySource = roads.stream().anyMatch(r -> r.contains(sourceVertex));
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
		// towns.clear();
		// roads.clear();
		return roadPath.stream().map(Road::toString).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Dijkstra's Shortest Path Method using Adjancency Matrix. Internal structures
	 * are built which hold the ability to retrieve the path, shortest distance from
	 * the sourceVertex to all the other vertices in the graph, etc.
	 * 
	 * @param sourceVertex - the vertex to find shortest path from
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		// shortestPath.clear();
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
			if (!t.equals(sourceVertex)) {
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
	 * Process the shortest paths of towns and add into arraylist
	 * 
	 * @param sourceVertex   - index of town
	 * @param minPathLengths - array with towns short distances
	 */
	private void computeShortPath(int sourceVertex, int[] minPathLengths) {

		if (sourceVertex == -1) {
			return;
		}

		computeShortPath(minPathLengths[sourceVertex], minPathLengths);

		int townIndex = 0;

		for (Town t : towns) {
			if (townIndex == sourceVertex) {
				shortestPath.add(t.getName());
			}
			townIndex++;
		}
	}

}
