/*
 * Author: Edward Gregg
 * Class: CMSC 204
 * Project: Assignment 4
 * Due Date: Nov ??, 2022
 *
 * 
 */

public class CourseDBElement implements Comparable {
  private String courseId;
  private int CRN;
  private int credits;
  private String room;
  private String instructor;

  
  /**
   * constructor that creates object for this class
   * @param courseId
   * @param crn
   * @param credits
   * @param room
   * @param instructor
   */
  public CourseDBElement(String courseId, int crn, int credits, String room, String instructor) {
    this.courseId = courseId;
    this.CRN = crn;
    this.credits = credits;
    this.room = room;
    this.instructor = instructor;
  }
  
  /**
   * Constructor with default parameters
   */

  public CourseDBElement() {
    this.courseId = "";
    this.CRN = 0;
    this.credits = 0;
    this.room = "";
    this.instructor = "";
  }

  /**
   * @return id of the course
   */
  public String getID() {
    return courseId;
  }

  /**
   * @param courseId - id of the course
   */
  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  /**
   * @return unique number that represents the course
   */
  public int getCRN() {
    return CRN;
  }

  /**
   * @param crn - unique number that represents the course
   */
  public void setCRN(int crn) {
    CRN = crn;
  }

  /**
   * @return number of credits of the course
   */
  public int getCredits() {
    return credits;
  }

  /**
   * @param credits - number of credits of the course
   */
  public void setCredits(int credits) {
    this.credits = credits;
  }

  /**
   * @return room number
   */
  public String getRoomNum() {
    return room;
  }

  /**
   * @param room - room number as string
   */
  public void setRoom(String room) {
    this.room = room;
  }

  /**
   * @return name of the Instructor
   */
  public String getInstructor() {
    return instructor;
  }

  /**
   * @param instructor - name of the instructor
   */
  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  /**
   * @return CRN gets hashed because it's unique to every class
   * found online, way for hasing
   */
  @Override
	public int hashCode() {
		String code = Integer.toString(CRN);
		return code.hashCode();
	}
  
	public String crnString() {
		return Integer.toString(CRN);
	}

/**
 * generic toString
 */
  @Override
  public String toString() {
    return "\nCourse:" + courseId + " CRN:" + CRN + " Credits:" + credits + " Instructor:" + instructor + " Room:" + room;
  }

  /**
   * The comparison will be based in the CRN
   * 
   * @param element - element to be compared with
   */
  @Override
  public int compareTo(CourseDBElement element) {
    return Integer.compare(this.getCRN(), element.getCRN());
  }
  

  @Override
  public boolean equals(Object obj) {
    if (obj == null){
        return false;
    }

    if (obj == this) {
        return true;
    } 

    if (!(obj instanceof CourseDBElement)) {
        return false;
    }
    CourseDBElement cde = (CourseDBElement) obj;
    return this.getCRN() == cde.getCRN();
}

}