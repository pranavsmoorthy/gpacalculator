package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend;

import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.utils.Constants;

/**
 * This class holds the variables and functions
 * needed for a grade represented by a letter
 * 
 * @author Pranav Moorthy
 * @version 1.0.0
 */
public class LetterGrade extends AbstractGrade {
  private char grade;

  /**
   * Sets the name, grade, and type of a course
   * when the object is instantiated
   * 
   * @param name  Name of the course
   * @param type  Type of the course
   * @param grade Letter grade of the course
   */
  public LetterGrade(String name, String type, char grade) {
    super(name, type);
    setGrade(grade);
  }

  /**
   * @return Returns the course grade
   */
  public char getGrade() {
    return grade;
  }

  /**
   * Sets the grade of the course
   * 
   * @param grade Grade the course grade should be set to
   */
  public void setGrade(char grade) {
    this.grade = grade;
    generateWeightage();
  }

  /**
   * Creates the weightage of a course based on
   * the grade set
   * 
   * The grades are set like so
   * - A: Unweighted GPA is 4
   * - B: Unweighted GPA is 3
   * - C: Unweighted GPA is 2
   * - D: Unweighted GPA is 1
   * - F: Unweighted GPA is 0
   * 
   * If the course types is
   * - AP: The weighted GPA is set to the unweighted GPA + 1
   * - Honors: The weighted GPA is set to the unweighted GPA + 0.5
   */
  public void generateWeightage() {
    float baseGrade = 0;
    float increment = 0;

    if (grade == 'A')
      baseGrade = 4;
    else if (grade == 'B')
      baseGrade = 3;
    else if (grade == 'C')
      baseGrade = 2;
    else if (grade == 'D')
      baseGrade = 1;
    else if (grade == 'F' || grade == 'S')
      baseGrade = 0;

    if (getType().equals(Constants.AP))
      increment = 1;
    else if (getType().equals(Constants.HONORS))
      increment = 0.5f;

    setGrades(baseGrade, increment);
  }
}
