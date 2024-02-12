package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the lists and functions
 * use to calculate the weighted and
 * unweighted GPAs for a student
 * 
 * @author Pranav Moorthy
 * @version 1.0.0
 */
public class GPACalculator {
  private List<AbstractGrade> gradeList;
  private float weightedGPA;
  private float unweightedGPA;

  /**
   * The constructor sets up the list that holds
   * the grade objects
   */
  public GPACalculator() {
    this.gradeList = new ArrayList<AbstractGrade>();
  }

  /**
   * @return Returns the list of grades
   */
  public List<AbstractGrade> getGradeList() {
    return gradeList;
  }

  /**
   * Adds a grade object to the list
   * 
   * @param grade Grade object to be added
   */
  public void addGrade(AbstractGrade grade) {
    this.gradeList.add(grade);
  }

  /**
   * Removes a grade object from the list
   * 
   * @param index The location for which object to remove
   */
  public void removeGrade(int index) {
    this.gradeList.remove(index);
  }

  /**
   * Removes all the grades from the list
   */
  public void removeAllGrades() {
    this.gradeList.removeAll(gradeList);
  }

  /**
   * @return Returns the weighted GPA
   */
  public float getWeightedGPA() {
    return weightedGPA;
  }

  /**
   * @return Returns the unweighted GPA
   */
  public float getUnweightedGPA() {
    return unweightedGPA;
  }

  /**
   * Calculates bot the weighted and unweighted GPAs
   * in one function
   */
  public void calculateGPA() {
    this.calculateUnweightedGPA();
    this.calculateWeightedGPA();
  }

  /**
   * Calculates the weighted GPA
   */
  private void calculateWeightedGPA() {
    float totalSum = 0;

    for (int i = 0; i < this.gradeList.size(); i++) {
      totalSum += this.gradeList.get(i).getWeightedGrade();
    }

    weightedGPA = totalSum / this.gradeList.size();
  }

  /**
   * Calculates the unweighted GPA
   */
  private void calculateUnweightedGPA() {
    float totalSum = 0;

    for (int i = 0; i < gradeList.size(); i++) {
      totalSum += this.gradeList.get(i).getUnweightedGrade();
    }

    unweightedGPA = totalSum / this.gradeList.size();
  }
}