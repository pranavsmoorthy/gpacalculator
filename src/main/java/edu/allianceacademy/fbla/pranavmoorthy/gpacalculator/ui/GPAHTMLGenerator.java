package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.ui;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.utils.Constants;

public class GPAHTMLGenerator {
  public void writeToFile(String filePath, CourseTable table) throws Exception {
    List<CourseTableRow> rowList = table.getRows();
    StringBuilder html = new StringBuilder().
      append(" <!DOCTYPE html>\n").
      append("   <html>\n").
      append("  \n").
      append("     <head>\n").
      append("       <style>\n").
      append("         table {\n").
      append("           font-family: arial, sans-serif;\n").
      append("           border-collapse: collapse;\n").
      append("           width: 100%;\n").
      append("         }\n").
      append("  \n").
      append("         td,\n").
      append("         th {\n").
      append("           border: 1px solid #dddddd;\n").
      append("           text-align: left;\n").
      append("            padding: 8px;\n").
      append("          }\n").
      append("  \n").
      append("         tr:nth-child(even) {\n").
      append("           background-color: #dddddd;\n").
      append("         }\n").
      append("       </style>\n").
      append("     </head>\n").
      append("  \n").
      append("     <body>\n").
      append("  \n").
      append("       <h2>GPA REPORT</h2>\n").
      append("  \n").
      append("       <table>\n").
      append("         <tr>\n").
      append("           <th>Course Name</th>\n").
      append("           <th>Course Type</th>\n").
      append("           <th>Grade</th>\n").
      append("         </tr>");

    for (CourseTableRow courseTableRow : rowList) {
      String gradeString = "";

      if(table.getBuilder().getGradeType().equals(Constants.NUMBER_GRADE))
        gradeString = String.valueOf(courseTableRow.getNumberGradeObj().getGrade());
      else if(table.getBuilder().getGradeType().equals(Constants.LETTER_GRADE)){
        gradeString = String.valueOf(courseTableRow.getLetterGradeObj().getGrade());
      }

      html.append("         <tr>\n").
        append("<td>").append(courseTableRow.getLetterGradeObj().getName()).append("</td>\n").
        append("<td>").append(courseTableRow.getLetterGradeObj().getType()).append("</td>\n").
        append("<td>").append(gradeString).append("</td>\n").
        append("</tr>");
    }

    html.append("</table>\n").
      append("      <br />\n").
      append("      <b> Weighted GPA: ").append(table.getWeightedGPA()).append("</b>\n").
      append("      <br />\n").
      append("      <br />\n").
      append("      <b> Unweighted GPA: ").append(table.getUnweightedGPA()).append("</b>\n").
      append("    </body>\n").
      append("  </html>\n");

    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    writer.write(html.toString());

    writer.close();

    File file = new File(filePath);
    Desktop.getDesktop().open(file);
  }
}
