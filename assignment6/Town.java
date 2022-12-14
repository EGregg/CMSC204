/**
 * Author: Edward Gregg
 * Assignment: Assignment 6
 * Due Date: 13DEC2022
 */


import java.util.ArrayList;

public class Town implements Comparable<Town> {

	private String name;
	// private ArrayList<Town> adjacentTowns;

	Town(String name) {
		this.name = name;
	}

	Town(Town templateTown) {
		this.name = templateTown.name;
	}

	/*
	 * Hashcode for town name
	 */
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(Town t) {
		return this.name.compareTo(t.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Town)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		
		Town t = (Town) obj;
		return this.name.equals(t.name);
	}

	/*
	 * Gets town name
	 */
	public String getName() {
		return name;
	}

	/*
	 * Prints name of town
	 */
	public String toString() {
		return name;
	}
}