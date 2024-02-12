package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.ui;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.utils.Constants;

public class GPAUIBuilder{
   private JPanel courseInfoTable;
   private CourseTable tableBuilder;
   private JTextField num;
   private String oldVal; 
   private JLabel weighted;
   private JLabel unweighted;
   private String gradeType;
   private boolean checker;

  public GPAUIBuilder() {
    this.courseInfoTable = new JPanel();
    this.tableBuilder = new CourseTable(this);
    this.num = new JTextField("17", 3);
    this.oldVal = "17";
    this.weighted = new JLabel("<html><b>Weighted GPA:</b><font color='red'> X.XXXX </font></html>");
    this.unweighted = new JLabel("<html><b>Unweighted GPA:</b><font color='red'> X.XXXX </font></html>");
    this.gradeType = Constants.NUMBER_GRADE;
    this.checker = false;
  }

  public void build(){
    try {
      UIManager.setLookAndFeel(
              UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      
    }

   GPASplashScreen splashScreen = new GPASplashScreen();

    JFrame frame = new JFrame();
    buildMenuBar(frame);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.weightx = 1;
    gbc.weighty = 0.0;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.insets = new Insets(2, 2, 2, 2);
    frame.add(buildGradeTypePanel(frame), gbc);
    
    gbc.gridy = 1;
    gbc.weightx = 0;
    frame.add(buildCourseNumPanel(frame), gbc);

    gbc.gridy = 2;
    gbc.insets = new Insets(2, 5, 2, 2);

    gbc.gridy = 3;
    gbc.weightx = 0;
    gbc.insets = new Insets(2, 2, 2, 2);
    JLabel courseDetailsLabel = new JLabel(" Course Details: ");
    courseDetailsLabel.setFont(courseDetailsLabel.getFont().deriveFont(
      courseDetailsLabel.getFont().getStyle() | Font.BOLD));
    frame.add(courseDetailsLabel, gbc);
    
    gbc.gridy = 4;
    gbc.anchor = GridBagConstraints.CENTER;
    courseInfoTable.add(tableBuilder.buildTable(17), gbc);
    frame.getContentPane().add(courseInfoTable, gbc);

    gbc.gridy = 5;
    gbc.weighty = 1;
    gbc.insets = new Insets(2, 2, 15, 20);
    gbc.anchor = GridBagConstraints.LAST_LINE_START;
    frame.getContentPane().add(buildGPAPanel(), gbc);

    gbc.gridy = 5;
    gbc.insets = new Insets(2, 20, 15, 2);
    gbc.anchor = GridBagConstraints.LAST_LINE_END;
    frame.getContentPane().add(buildButtonPanel(), gbc);

    frame.setSize(520, 500);
    frame.setVisible(true);
  }

  private JPanel buildGradeTypePanel(final JFrame frame) {
    GridBagConstraints gbc = new GridBagConstraints();
    JPanel gradeTypePanel = new JPanel(new GridBagLayout());
    JLabel gradeLabel = new JLabel(" Grade Type: ");
    gradeLabel.setFont(gradeLabel.getFont().deriveFont(
      gradeLabel.getFont().getStyle() | Font.BOLD));
    ButtonGroup gradeButtonGroup = new ButtonGroup();

    JRadioButton letterGradeButton = new JRadioButton("Letter Grade");
    letterGradeButton.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        courseInfoTable.removeAll();
        gradeType = Constants.LETTER_GRADE;
        courseInfoTable.add(tableBuilder.changeTableGrade(Constants.LETTER_GRADE));
        tableBuilder.changeCalcList();
        frame.revalidate();
      }
    });

    JRadioButton numberGradeButton = new JRadioButton("Number Grade");
    numberGradeButton.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if(checker){
          courseInfoTable.removeAll();
          gradeType = Constants.NUMBER_GRADE;
          courseInfoTable.add(tableBuilder.changeTableGrade(Constants.NUMBER_GRADE));
          tableBuilder.changeCalcList();
          frame.revalidate();
        }else{
          checker = true;
        }
      }
    });

    gradeButtonGroup.add(letterGradeButton);
    gradeButtonGroup.add(numberGradeButton);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gradeTypePanel.add(gradeLabel, gbc);

    gbc.gridx = 1;
    gradeTypePanel.add(letterGradeButton, gbc);

    gbc.gridx = 2;
    gradeTypePanel.add(numberGradeButton, gbc);
    numberGradeButton.setSelected(true);
    return gradeTypePanel;
  }

  private JPanel buildCourseNumPanel(final JFrame frame) {
    JPanel courseNum = new JPanel();
    JLabel courseNumLabel = new JLabel("Number of courses: ");
    courseNumLabel.setFont(courseNumLabel.getFont().deriveFont(
      courseNumLabel.getFont().getStyle() | Font.BOLD));
    courseNum.add(courseNumLabel);
    courseNum.add(num);
    num.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
          String currentVal = num.getText();
          int textAreaInt = 0;
      
          if(!currentVal.equals(oldVal)) {
            oldVal = currentVal;
  
            try{
              textAreaInt = Integer.parseInt(currentVal);
            }catch(Exception exception){
              exception.printStackTrace();
            }
  
            if(textAreaInt > 0){
              courseInfoTable.removeAll();
              courseInfoTable.add(tableBuilder.rebuildTable(textAreaInt));
            }else{
              JOptionPane.showMessageDialog(null, "Please enter a POSITIVE NON ZERO INTEGER", "Error", JOptionPane.ERROR_MESSAGE);
              num.requestFocusInWindow();
              return;
            }
            frame.revalidate();
          }
        }      
    });
    num.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String currentVal = num.getText();
        int textAreaInt = 0;
      
        if(!currentVal.equals(oldVal)) {
          oldVal = currentVal;
  
          try{
            textAreaInt = Integer.parseInt(currentVal);
          }catch(Exception exception){
            exception.printStackTrace();
          }
  
          if(textAreaInt > 0){
            courseInfoTable.removeAll();
            courseInfoTable.add(tableBuilder.rebuildTable(textAreaInt));
          }else{
            JOptionPane.showMessageDialog(null, "Please enter a POSITIVE NON ZERO INTEGER", "Error", JOptionPane.ERROR_MESSAGE);
            num.requestFocusInWindow();
            return;
          }
          frame.revalidate();
        }
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearFocusOwner();
      }
    });
    return courseNum;
  }

  private JPanel buildGPAPanel() {
    JPanel gpaPanel = new JPanel();
    gpaPanel.setLayout(new GridLayout(2, 1));
    gpaPanel.add(weighted);
    gpaPanel.add(unweighted);
    return gpaPanel;
  }

  private JPanel buildButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    JButton download = new JButton("Download");
    download.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        writeToFile();
      }
    });
    JButton reset = new JButton("Reset");
    reset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        resetTable();
      }
    });
    JButton quit = new JButton("Quit");
    quit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    gbc.gridx = 0;
    gbc.gridy = 0;
    buttonPanel.add(reset, gbc);

    gbc.gridx = 1;
    buttonPanel.add(download);

    gbc.gridx = 2;
    buttonPanel.add(quit, gbc);
    return buttonPanel;
  }

  private void buildMenuBar(JFrame f){
    JMenuBar menuBar = new JMenuBar();

    JMenu fileMenu = new JMenu("File");
    JMenuItem downloadMenuItem = new JMenuItem("Download");
    downloadMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        writeToFile();
      }
    });
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
    exitMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    fileMenu.add(downloadMenuItem);
    fileMenu.add(exitMenuItem);

    JMenu tableMenu = new JMenu("Table");
    JMenuItem resetMenuItem = new JMenuItem("Reset");
    resetMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
    resetMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        resetTable();
      }
    });
    tableMenu.add(resetMenuItem);

    menuBar.add(fileMenu);
    menuBar.add(tableMenu);
    f.setJMenuBar(menuBar);
  }

  public void writeToFile() {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
      GPAHTMLGenerator generator = new GPAHTMLGenerator();
      try {
        generator.writeToFile(selectedFile.getAbsolutePath(), tableBuilder);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Could not download file", "Error", JOptionPane.ERROR_MESSAGE);
      }
		}
  }

  public void resetTable(){
    setUnweightedPlaceholderText();
    setWeightedPlaceholderText();
    tableBuilder.resetTable();
  }

  public void setWeighted() {
    this.weighted.setText("<html><b>Weighted GPA:</b><font color='red'> " + tableBuilder.getWeightedGPA() + "</font></html>");
  }

  public void setUnweighted() {
    this.unweighted.setText("<html><b>Unweighted GPA:</b><font color='red'> " + tableBuilder.getUnweightedGPA() + "</font></html>");;
  }

  public void setWeightedPlaceholderText() {
    this.weighted.setText("<html><b>Weighted GPA:</b><font color='red'> X.XXXX</font></html>");
  }

  public void setUnweightedPlaceholderText() {
    this.unweighted.setText("<html><b>Unweighted GPA:</b><font color='red'> X.XXXX</font></html>");;
  }

  public void setLabels(){
    setWeighted();
    setUnweighted();
  }

  public String getGradeType() {
    return gradeType;
  }
}
