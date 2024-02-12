package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.backend.GPACalculator;
import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.utils.Constants;

public class CourseTable {
    private JPanel table ;
    private float weightedGPA;
    private float unweightedGPA;
    private GridBagConstraints gbc;
    private GridBagLayout layout;
    private GPACalculator calculator;
    private GPAUIBuilder builder;
    private List<CourseTableRow> rows;

    public CourseTable(GPAUIBuilder b){
        table = new JPanel();
        
        layout = new GridBagLayout();
        table.setLayout(layout);
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc = new GridBagConstraints();
        
        JLabel name = new JLabel("Name");
        JLabel type = new JLabel("Type");
        JLabel grade = new JLabel("Grade");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        name.setFont(name.getFont().deriveFont(name.getFont().getStyle() | Font.BOLD));
        table.add(name, gbc);
        gbc.gridx = 1;
        
        type.setFont(type.getFont().deriveFont(type.getFont().getStyle() | Font.BOLD));
        table.add(type, gbc);

        gbc.gridx = 2;
        grade.setFont(grade.getFont().deriveFont(grade.getFont().getStyle() | Font.BOLD));
        table.add(grade, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 2, 0);
        calculator = new GPACalculator();
        builder = b;

        rows = new ArrayList<>();
    }

    public JScrollPane buildTable(int num) {
        for(int x = 0; x < num; x++){
            gbc.gridy++;
            CourseTableRow row = new CourseTableRow(this);
            rows.add(row);
            
            gbc.gridx = 0;
            gbc.weightx = 0;
            table.add(row.getRowComponents()[0], gbc);

            gbc.gridx = 1;
            table.add(row.getRowComponents()[1], gbc);
            
            gbc.weightx = 1;
            gbc.gridx = 2;
            if(builder.getGradeType().equals(Constants.NUMBER_GRADE))
                table.add(row.getRowComponents()[2], gbc);
            if(builder.getGradeType().equals(Constants.LETTER_GRADE))
                table.add(row.getRowComponents()[3], gbc);        
        }
        return getScrollPane();
    }

    public JScrollPane rebuildTable(int num){
        Component[] componentList = table.getComponents();
        int times = (componentList.length/3) - 1;

        if(num > times){
            for(int x = 0; x < num - times; x++){
                gbc.gridy++;
                CourseTableRow row = new CourseTableRow(this);
                rows.add(row);
                
                gbc.gridx = 0;
                gbc.weightx = 0;
                table.add(row.getRowComponents()[0], gbc);

                gbc.gridx = 1;
                table.add(row.getRowComponents()[1], gbc);
                
                gbc.weightx = 1;
                gbc.gridx = 2;

                if(builder.getGradeType().equals(Constants.NUMBER_GRADE))
                    table.add(row.getRowComponents()[2], gbc);
                if(builder.getGradeType().equals(Constants.LETTER_GRADE))
                    table.add(row.getRowComponents()[3], gbc);
            }
        }else if(num < times){
            int length = calculator.getGradeList().size();
            List<Component> toRemove = new ArrayList<>();

            for (Component component : componentList) {
                if(layout.getConstraints(component).gridy > num + 1)
                    toRemove.add(component);
            }

            for (Component component : toRemove) {
                table.remove(component);
            }

            for(int x = length - 1; x > num - 1; x--){
                rows.remove(x);
                calculator.removeGrade(x);
            }
        }
        
        return getScrollPane();
    }

    public void changeCalcList(){
        int size = calculator.getGradeList().size();
        calculator.removeAllGrades();

        if(getBuilder().getGradeType() == Constants.LETTER_GRADE){
            for(int x = 0; x < size; x++){
                calculator.addGrade(rows.get(x).getLetterGradeObj());
            }
        }

        if(getBuilder().getGradeType() == Constants.NUMBER_GRADE){
            for(int x = 0; x < size; x++){
                calculator.addGrade(rows.get(x).getNumberGradeObj());
            }
        }
    }

    public JScrollPane changeTableGrade(String type){
        Component[] componentList = table.getComponents();
        List<Component> toReplace = new ArrayList<>();

        for(int index = 3; index < componentList.length; index++){
            GridBagConstraints constraints = layout.getConstraints(componentList[index]);
            if(constraints.gridx == 2){
                toReplace.add(componentList[index]);
            }
        }

        int counter = 0;
        for (Component c : toReplace) {
            table.remove(c);
            gbc.weightx = 1;
            gbc.gridx = 2;
            gbc.gridy = counter + 2;

            if(type.equals(Constants.LETTER_GRADE)){
                table.add(rows.get(counter).getRowComponents()[3], gbc);
            }

            if(type.equals(Constants.NUMBER_GRADE)){
                table.add(rows.get(counter).getRowComponents()[2], gbc);
            }

            counter++;
        }

        return getScrollPane();
    }

    private JScrollPane getScrollPane(){
        JScrollPane tableScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setPreferredSize(new Dimension(500, 300));
        tableScrollPane.setMaximumSize(new Dimension(500, 300));
        return tableScrollPane;
    }

    public void resetTable(){
        for (CourseTableRow row : rows) {
            row.resetRow();
        }
    }

    public List<CourseTableRow> getRows() {
        return rows;
    }

    public float getWeightedGPA() {
        return weightedGPA;
    }

    public void setWeightedGPA(float weightedGPA) {
        this.weightedGPA = weightedGPA;
    }
    
    public float getUnweightedGPA() {
        return unweightedGPA;
    }

    public void setUnweightedGPA(float unweightedGPA) {
        this.unweightedGPA = unweightedGPA;
    }
    
    public JPanel getTable() {
        return table;
    }

    public void setTable(JPanel table) {
        this.table = table;
    }

    public GPACalculator getCalculator() {
        return calculator;
    }

    public GPAUIBuilder getBuilder() {
        return builder;
    }
}
