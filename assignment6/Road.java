/**
 * Author: Edward Gregg
 * Assignment: Assignment 6
 * Due Date: 13DEC2022
 */


public class Road implements Comparable<Road> {

	private Town source, destination;
	private int distance;
	private String name;

	/*
	 * Constructs with parameters
	 */
	Road(Town source, Town destination, int distance, String name) {
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		this.name = name;
	}

	/*
	 * Constructs with a default distance of 1
	 */
	Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
		this.distance = 1;
	}

	/*
	 * toString method to return name of source, towns encountered, destination and
	 * distance traveled has to be in this exact format to pass testDO NOT TOUCH
	 */
	@Override
	public String toString() {
		return source.getName() + " via " + name + " to " + destination.getName() + " " + distance + " mi";
	}

	/*
	 * returns source
	 */
	public Town getSource() {
		return source;
	}

	/*
	 * returns destination
	 */
	public Town getDestination() {
		return destination;
	}

	/*
	 * returns distance
	 */
	public int getDistance() {
		return distance;
	}

	/*
	 * returns name
	 */
	public String getName() {
		return name;
	}

	/*
	 * gets weight (the distance) from town to town
	 */
	public int getWeight() {
		return distance;
	}

	/*
	 * standard compare to method
	 */
	@Override
	public int compareTo(Road o) {
		return this.name.compareTo(o.name);
	}

	/*
	 * check if road contains given town
	 */
	public boolean contains(Town town) {
		return source.getName().equals(town.getName()) || destination.getName().equals(town.getName());
	}

	/**
	 * Equals method for road object needed to past test DO NOT TOUCH found on geeks
	 * for geeks
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Road)) {
			return false;
		}
		Road r = (Road) obj;
		return (this.source.equals(r.source) && this.destination.equals(r.destination))
				|| (this.source.equals(r.destination) && this.destination.equals(r.source));
	}

}
