package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend;

/**
 * This class is used as a parent class for the
 * number grade and letter grade objects, and
 * has the functions for setting the course
 * name and type, as well as an abstract
 * function for generating the weightages for
 * the courses.
 * 
 * @author Pranav Moorthy
 * @version 1.0.0
 */
public abstract class AbstractGrade {
  private String courseName;
  private String courseType;
  private float weightedGrade;
  private float unweightedGrade;

  /**
   * This constructor sets the course name and type for the
   * grade object
   * 
   * @param courseName Name of the course taken by the student
   * @param courseType Type of the course taken by the student
   */
  public AbstractGrade(String courseName, String courseType) {
    setName(courseName);
    setType(courseType);
  }

  /**
   * @return Returns the name of the course
   */
  public String getName() {
    return courseName;
  }

  /**
   * @return Returns the type of the course
   */
  public String getType() {
    return courseType;
  }

  /**
   * Sets the name of the course
   * 
   * @param name The name that the course name should be set to
   */
  public void setName(String name) {
    courseName = name;
  }

  /**
   * Sets the type of the course
   * 
   * @param type The type that the course type should be set to
   */
  public void setType(String type) {
    courseType = type;
  }

  /**
   * Sets the weighted grade of the course
   * 
   * @param weightedGrade The number that the course's weighted grade should be
   *                      set to
   */
  public void setWeightedGrade(float weightedGrade) {
    this.weightedGrade = weightedGrade;
  }

  /**
   * Sets the weighted grade of the course
   * 
   * @param unweightedGrade The number that the course's unweighted grade should
   *                        be set to
   */
  public void setUnweightedGrade(float unweightedGrade) {
    this.unweightedGrade = unweightedGrade;
  }

  /**
   * Sets both the weighted grade and the unweighted grade
   * 
   * @param baseGrade The unweighted grade
   * @param increment The number that will be added to the unweighted grade to
   *                  determine the weighted grade
   */
  public void setGrades(float baseGrade, float increment) {
    setWeightedGrade(baseGrade + increment);
    setUnweightedGrade(baseGrade);
  }

  /**
   * @return Returns the weighted grade of the course
   */
  public float getWeightedGrade() {
    return weightedGrade;
  }

  /**
   * @return Returns the unweighted grade of the course
   */
  public float getUnweightedGrade() {
    return unweightedGrade;
  }

  /**
   * The tempelate for the function that will determine the
   * weightage for the course
   * 
   * @throws Exception
   */
  protected abstract void generateWeightage() throws Exception;
}