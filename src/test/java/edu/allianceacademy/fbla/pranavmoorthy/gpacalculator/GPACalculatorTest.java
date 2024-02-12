package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend.GPACalculator;
import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend.LetterGrade;
import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend.NumberGrade;

class GPACalculatorTest {
 
    @Test
    void calculateGPAFor5SubjectsNumber() {
        GPACalculator calculator = new GPACalculator();
        NumberGrade grade1 = new NumberGrade("Science", "Regular", 83);
        NumberGrade grade2 = new NumberGrade("Science", "Regular", 83);
        NumberGrade grade3 = new NumberGrade("Science", "Regular", 83);
        NumberGrade grade4 = new NumberGrade("Science", "Regular", 83);
        NumberGrade grade5 = new NumberGrade("Science", "Regular", 83);
        calculator.addGrade(grade1);
        calculator.addGrade(grade2);
        calculator.addGrade(grade3);
        calculator.addGrade(grade4);
        calculator.addGrade(grade5);
        try {
            grade1.generateWeightage();
            grade2.generateWeightage();
            grade3.generateWeightage();
            grade4.generateWeightage();
            grade5.generateWeightage();
            calculator.calculateGPA();
            assertEquals(3.0, calculator.getUnweightedGPA());  
        } catch (Exception e) {
            fail("Exception detected");
            e.printStackTrace();
        }
    }

    @Test
    void calculateGPAFor5SubjectsLetter() {
        GPACalculator calculator = new GPACalculator();
        LetterGrade grade1 = new LetterGrade("Science", "Regular", 'B');
        LetterGrade grade2 = new LetterGrade("Science", "Regular", 'B');
        LetterGrade grade3 = new LetterGrade("Science", "Regular", 'B');
        LetterGrade grade4 = new LetterGrade("Science", "Regular", 'B');
        LetterGrade grade5 = new LetterGrade("Science", "Regular", 'B');
        calculator.addGrade(grade1);
        calculator.addGrade(grade2);
        calculator.addGrade(grade3);
        calculator.addGrade(grade4);
        calculator.addGrade(grade5);
        try {
            grade1.generateWeightage();
            grade2.generateWeightage();
            grade3.generateWeightage();
            grade4.generateWeightage();
            grade5.generateWeightage();
            calculator.calculateGPA();
            assertEquals(3.0, calculator.getUnweightedGPA());  
        } catch (Exception e) {
            fail("Exception detected");
            e.printStackTrace();
        }
    } 

    void calculateGPAFor5SubjectsWithWeightedLetter() {
        GPACalculator calculator = new GPACalculator();
        LetterGrade grade1 = new LetterGrade("Science", "Honors", 'B');
        LetterGrade grade2 = new LetterGrade("Science", "AP", 'B');
        LetterGrade grade3 = new LetterGrade("Science", "Regular", 'B');
        LetterGrade grade4 = new LetterGrade("Science", "Regular", 'B');
        LetterGrade grade5 = new LetterGrade("Science", "Regular", 'B');
        calculator.addGrade(grade1);
        calculator.addGrade(grade2);
        calculator.addGrade(grade3);
        calculator.addGrade(grade4);
        calculator.addGrade(grade5);
        try {
            grade1.generateWeightage();
            grade2.generateWeightage();
            grade3.generateWeightage();
            grade4.generateWeightage();
            grade5.generateWeightage();
            calculator.calculateGPA();
            assertEquals(3.3, calculator.getWeightedGPA());  
        } catch (Exception e) {
            fail("Exception detected");
            e.printStackTrace();
        }
    }

    void calculateGPAFor5SubjectsWithWeightedNumber() {
        GPACalculator calculator = new GPACalculator();
        NumberGrade grade1 = new NumberGrade("Science", "AP", 83);
        NumberGrade grade2 = new NumberGrade("Science", "Honors", 83);
        NumberGrade grade3 = new NumberGrade("Science", "Regular", 83);
        NumberGrade grade4 = new NumberGrade("Science", "Regular", 83);
        NumberGrade grade5 = new NumberGrade("Science", "Regular", 83);
        calculator.addGrade(grade1);
        calculator.addGrade(grade2);
        calculator.addGrade(grade3);
        calculator.addGrade(grade4);
        calculator.addGrade(grade5);
        try {
            grade1.generateWeightage();
            grade2.generateWeightage();
            grade3.generateWeightage();
            grade4.generateWeightage();
            grade5.generateWeightage();
            calculator.calculateGPA();
            assertEquals(3.3, calculator.getUnweightedGPA());  
        } catch (Exception e) {
            fail("Exception detected");
            e.printStackTrace();
        }
    }
}