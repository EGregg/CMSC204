/**
 * Leave comments for this one. Had to be implemented
 * @author EGREGG
 *
 */

public class Road implements Comparable<Road> {

  private Town source, destination;
  private int distance;
  private String name;

  Road(Town source, Town destination, int distance, String name) {
    this.source = source;
    this.destination = destination;
    this.distance = distance;
    this.name = name;
  }

  Road(Town source, Town destination, String name) {
    this.source = source;
    this.destination = destination;
    this.distance = 1;
    this.name = name;
  }

  @Override
  public int compareTo(Road r) {
    return this.name.compareTo(r.name);
  }

  public boolean contains(Town town) {
    return source.getName().equals(town.getName()) || destination.getName().equals(town.getName());
  }

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

  @Override
  public String toString() {
    return source.getName() + " via " + name + " to " + destination.getName() + " " + distance
        + " mi";
  }

  public Town getSource() {
    return source;
  }

  public Town getDestination() {
    return destination;
  }

  public int getDistance() {
    return distance;
  }

  public String getName() {
    return name;
  }

}
