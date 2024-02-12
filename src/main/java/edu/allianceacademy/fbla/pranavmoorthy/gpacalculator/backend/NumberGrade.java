package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend;

import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.utils.Constants;

public class NumberGrade extends AbstractGrade{
    private float grade;

    public NumberGrade(String name, String type, float grade){
      super(name, type); 
      setGrade(grade);
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void generateWeightage() throws Exception{
        if(grade < 0){
          throw new Exception();
        }

        float baseGrade = 0;
        float increment = 0;
    
        if(grade >= 90)
          baseGrade = 4;
        else if(grade < 90 && grade >= 80)
          baseGrade = 3;
        else if(grade < 80 && grade >= 70)
          baseGrade = 2;
        else if(grade < 70 && grade >= 60)
          baseGrade = 1;
        else if(grade < 60)
          baseGrade = 0;
    
        if(getType().equals(Constants.AP))
          increment = 1;
        else if(getType().equals(Constants.HONORS))
          increment = 0.5f;
    
        setGrades(baseGrade, increment);
    }
}
