package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.ui;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend.LetterGrade;
import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend.NumberGrade;
import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.utils.Constants;

public class CourseTableRow{
    private Component[] components;

    private JTextField courseName = new JTextField("", 25);
    private String[] courseTypeOptions = {
        Constants.REGULAR, 
        Constants.HONORS, 
        Constants.AP
    };
    private JComboBox courseTypeDropdown = new JComboBox(courseTypeOptions);
    private JTextField courseNumberGrade = new JTextField("", 10);
    private NumberGrade numberGradeObj;
    private LetterGrade letterGradeObj;
    private JComboBox courseLetterGradeDropdown;
    private String[] letterGradeOptions = {
        "    Select    ",
        "       A      ",
        "       B      ",
        "       C      ",
        "       D      ",
        "       F      "
    };

    public CourseTableRow(final CourseTable t){
        courseNumberGrade.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus lost");
                try{
                    updateGPALabels(t);
                }catch(Exception exception){
                    if(!courseNumberGrade.getText().equals("")){
                        redirectToGradebox();
                        return;
                    }
                }
            }
        });
        courseName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                numberGradeObj.setName(courseName.getText());
                letterGradeObj.setName(courseName.getText());
            }
        });
        courseTypeDropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                numberGradeObj.setType((String) courseTypeDropdown.getSelectedItem());
                letterGradeObj.setType((String) courseTypeDropdown.getSelectedItem());
                try{
                    updateGPALabels(t);
                }catch(Exception exception){
                    redirectToGradebox();
                    return;
                }
            }
        });
        courseLetterGradeDropdown = new JComboBox(letterGradeOptions);
        courseLetterGradeDropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    updateGPALabels(t);
                }catch(Exception exception){}
            }
        });

        components = new Component[]{courseName, courseTypeDropdown, courseNumberGrade, courseLetterGradeDropdown};
        numberGradeObj = new NumberGrade("", Constants.REGULAR, 0);
        letterGradeObj = new LetterGrade("", Constants.REGULAR, 'S');
        t.getCalculator().addGrade(numberGradeObj);
    }

    public Component[] getRowComponents() {
        return components;
    }

    private void redirectToGradebox(){
        JOptionPane.showMessageDialog(null, "Invalid value, please set a number grade", "Invalid value", JOptionPane.ERROR_MESSAGE);
        courseNumberGrade.requestFocusInWindow();
    }

    private void updateGPALabels(CourseTable t) throws Exception {
        if(t.getBuilder().getGradeType() == Constants.NUMBER_GRADE){
            if(courseNumberGrade.getText().equals(""))
                return;
            numberGradeObj.setGrade(Float.parseFloat(courseNumberGrade.getText()));
            numberGradeObj.generateWeightage();
        }
        else if(t.getBuilder().getGradeType() == Constants.LETTER_GRADE){
            letterGradeObj.setGrade(((String) courseLetterGradeDropdown.getSelectedItem()).toCharArray()[7]);
            letterGradeObj.generateWeightage();
        }

        t.getCalculator().calculateGPA();
        t.setWeightedGPA(t.getCalculator().getWeightedGPA());
        t.setUnweightedGPA(t.getCalculator().getUnweightedGPA());
        t.getBuilder().setLabels();
    }

    public NumberGrade getNumberGradeObj() {
        return numberGradeObj;
    }

    public LetterGrade getLetterGradeObj() {
        return letterGradeObj;
    }

    public void resetRow(){
        courseNumberGrade.setText("");
        courseLetterGradeDropdown.setSelectedIndex(0);
        courseTypeDropdown.setSelectedIndex(0);
        courseName.setText("");
        letterGradeObj = new LetterGrade("", Constants.REGULAR, 'S');
        numberGradeObj = new NumberGrade("", Constants.REGULAR, 0);
    }
}
