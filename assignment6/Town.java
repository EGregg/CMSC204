/**
 * Leave comments for this one. Had to be implemented
 * @author EGREGG
 *
 */


import java.util.ArrayList;

public class Town implements Comparable<Town>{

    private String name;
    private ArrayList<Town> adjacentTowns;
    
    Town(String name){
        this.name = name;
    }
    
    Town(Town templateTown){
        this.name=templateTown.name;
    }
    
    @Override
    public int compareTo(Town t) {      
        return this.name.compareTo(t.name);
    }

     @Override
      public boolean equals(Object obj) {
        if (obj == null) {
          return false;
        }
        if (obj == this) {
          return true;
        }
        if (!(obj instanceof Town)) {
          return false;
        }
        Town t = (Town) obj;
        return this.name.equals(t.name);
      }
    
    public String getName() {
        return name;
    }
    
    public int hashCode() {
        return name.hashCode();
    }
    
    public String toString() {
        return name;
    }
}