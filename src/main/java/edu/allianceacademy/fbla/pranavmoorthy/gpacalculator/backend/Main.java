package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend;

import java.util.Scanner;

/**
 * This class has the variables and functions needed to
 * run the GPA calculator via command line
 * 
 * @author Pranav Moorthy
 * @version 1.0.0
 */
public class Main {
    /**
     * Sets up a new calculator, makes a list of letter grades through 
     * prompting the user, then gives that list to the calculator
     * and prints the values it gives
     * 
     * @param args
     */
    public static void main(String[] args){
        GPACalculator calculator = new GPACalculator();
        createLetterGradeList(calculator);
        calculator.calculateGPA();
        System.out.println("Weighted GPA: " + calculator.getWeightedGPA());
        System.out.println("Unweighted GPA: " + calculator.getUnweightedGPA());
    }

    /**
     * The function gets the number of classes from the student.
     * 
     * @param calculator The calculator object that has the list that holds the grades.
     */
    public static void createLetterGradeList(GPACalculator calculator){
        Scanner scan = new Scanner(System.in);

        System.out.println("How many classes");
        int numClasses = scan.nextInt();

        for(int i = 0; i < numClasses; i++){
            System.out.println("What is the name of the class?");
            String name = scan.next();

            System.out.println("What is the letter grade?");
            char letter = scan.next().charAt(0);

            System.out.println("What is the type of course?");
            String type = scan.next();

            calculator.addGrade((new LetterGrade(name, type, letter)));
        }

        scan.close();
    }
    
    /**
     * 
     * @param calculator
     */
    public static void createNumberGradeList(GPACalculator calculator){
        Scanner scan = new Scanner(System.in);
        System.out.println("How many classes do you have?");

        int numClasses = scan.nextInt();
        for(int i = 0; i < numClasses; i++){
            System.out.println("What is the name of the class?");
            String name = scan.nextLine();

            System.out.println("What is the number grade?");
            int number = scan.nextInt();

            System.out.println("What is the type of course?");
            String type = scan.nextLine();

            calculator.addGrade(new NumberGrade(name, type, number));
            System.out.println("_________________");
        }

        scan.close();
    }
}
