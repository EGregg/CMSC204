import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;


public class CourseDBManager implements CourseDBManagerInterface {

	/*might be able to simplify this by:
	 * CourseDBStructure courseDataBase = new CourseDBStructure(4);
	 * 
	 */
  private CourseDBStructure courseDataBase;

  public CourseDBManager() {
    courseDataBase = new CourseDBStructure(10);
  }

  			//do not touch, needs to stay this way to pass test
		  @Override
		  public void add(String id, int crn, int credits, String roomNum, String instructor) {
		    CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		    courseDataBase.add(element);
		  }

		  //do not touch, working as expected
		  @Override
		  public CourseDBElement get(int crn) {
		    try {
		    		return courseDataBase.get(crn);
		    		
		    } catch (IOException e) {
		      //System.out.println("Exception was thrown in Manager get CRN");
		    		e.getMessage();
		    }
		    return null;
		  }
		  

		  @Override
		  public void readFile(File input) throws FileNotFoundException {
			  
//		    InputStream in = new FileInputStream(input);
//		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
//		
//		    
//		    List<String[]> list = br.lines().map(s -> s.split(" ")).collect(Collectors.toList());
//		    for (String[] ar : list) {
//		    	
//			      if (ar.length > 5) {
//				        StringBuilder instructor = new StringBuilder();
//				        
//				        for (int i = 4; i < ar.length; i++) {
//				          instructor.append(ar[i] + " ");
//				        }
//		        
//		        courseDataBase.add(new CourseDBElement(ar[0], Integer.valueOf(ar[1]), Integer.valueOf(ar[2]), ar[3],
//		            instructor.toString().trim()));
//		        
//			      } else {
//			        courseDataBase.add(new CourseDBElement(ar[0], Integer.valueOf(ar[1]), Integer.valueOf(ar[2]), ar[3],
//			            ar[4]));
//			      }
//			      
//		    }
			  
			  	//default code for scanning through a file and breaking it into 4 parts
				Scanner scanner;
				try {
					scanner = new Scanner(input);
				} catch (FileNotFoundException e) {
					throw new FileNotFoundException("The file was not found.");
				}
				
				while (scanner.hasNextLine()) {
					//Splits each term in the line into an array
					String[] args = scanner.nextLine().split(" ");
					
					//Determine name
					String name = "";
					for (int i = 4; i < args.length; i++) {
						name += args[i] + " ";
					}
					add(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3], name);
				}
				scanner.close();  
		    
		  }


		  @Override
		  public ArrayList<String> showAll() {
		    ArrayList<CourseDBElement> tmp = new ArrayList<>();
		    
		    ArrayList<String> list;
		    for (int i = 0; i < courseDataBase.getTableSize(); i++) {
		    	
		      if (courseDataBase.hashTable[i] != null) {
		    	  tmp.addAll(courseDataBase.hashTable[i]);
		      }
		      
		    }
		    list = (ArrayList<String>) tmp.stream().map(s -> 
		    				s.toString()).collect(Collectors.toList());
		    return list;
		  }



		
		}