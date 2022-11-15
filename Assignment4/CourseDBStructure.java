import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class CourseDBStructure implements CourseDBStructureInterface {

  protected LinkedList<CourseDBElement>[] hashTable;
  
  

  				  //forgot about unchecked warning for linkedlist until now EG
  					//do not touch, necessary to pass test
				  @SuppressWarnings("unchecked")
				  public CourseDBStructure(int i) {
				    hashTable = new LinkedList[i];
				  }
				
				//forgot about unchecked warning for linkedlist until now EG
				  //do not touch, necessary to pass test
				  @SuppressWarnings("unchecked")
				  public CourseDBStructure(String string, int i) {
				    hashTable = new LinkedList[i];
				  }
				  


			//needs to be this way to pass test, do not touch
			  //EG
			  @Override
			  public int getTableSize() {
			    return hashTable.length;
			  }

	  		//copied from book and I think in class. Do not touch
	  		//EG
			  @Override
			  public void add(CourseDBElement element) {
			    int index = getHashIndex(element);
			
			    if (hashTable[index] == null) {
			      hashTable[index] = new LinkedList<CourseDBElement>();
			      hashTable[index].add(element);
			    } else {
			      if (hashTable[index].contains(element))
			        return;
			      else
			        hashTable[index].add(element);
			    }
			  }


	  @Override
	  public CourseDBElement get(int crn) throws IOException {
	
	    CourseDBElement temp = new CourseDBElement();
	
	    temp.setCRN(crn);
	    int index = getHashIndex(temp);
	    LinkedList<CourseDBElement> list = hashTable[index];
	
	    return list.stream().filter(c -> c.getCRN() == crn).findAny().orElseThrow(IOException::new);
	  }
	


	

	  private int getHashIndex(CourseDBElement element) {
		    int hashIndex = element.hashCode() % hashTable.length;
			    if (hashIndex < 0) {
			      hashIndex += hashTable.length;
			    }
			    return hashIndex;
	  }
	
	@Override
	public ArrayList<String> showAll() {
		// TODO Auto-generated method stub
		return null;
	}

}