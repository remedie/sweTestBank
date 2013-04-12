//SWE Test Bank GUI
//Last Modified: 4/8/2013

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;

public class TestBankGUI extends JFrame
{
   private String[] subjectList;
   private LinkedList<Test> testBank;

   public TestBankGUI()
   {
      setTitle("Society of Women Engineers");

      createDatabase();

      Box whole = new Box(BoxLayout.Y_AXIS);

      JLabel head = new JLabel("Test Bank Tool");
      head.setMaximumSize(new Dimension(300, 100));
      head.setFont(new Font("Serif", Font.BOLD, 28));
      head.setHorizontalAlignment(SwingConstants.CENTER);
      whole.add(head);
      
      JButton search = new JButton("Test Search");
      search.setMaximumSize(new Dimension(150, 28));
      search.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent a){
            setVisible(false);
            TestBankSearchGUI t = new TestBankSearchGUI();            
      }});
      search.setBackground(new Color(0, 76, 0));
      search.setForeground(Color.WHITE);
      whole.add(search);

      Box fillerV2 = new Box(BoxLayout.Y_AXIS);
      fillerV2.setMaximumSize(new Dimension(140, 15));
      whole.add(fillerV2);

      JButton add = new JButton("Add Test");
      add.setMaximumSize(new Dimension(150, 28));
      add.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent a){
            
      }});
      add.setBackground(new Color(0, 76, 0));
      add.setForeground(Color.WHITE);
      whole.add(add);

      Box fillerV3 = new Box(BoxLayout.Y_AXIS);
      fillerV3.setMaximumSize(new Dimension(140, 15));
      whole.add(fillerV3);

      JButton remove = new JButton("Remove Test");
      remove.setMaximumSize(new Dimension(150, 28));
      remove.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent a){
            
      }});
      remove.setBackground(new Color(0, 76, 0));
      remove.setForeground(Color.WHITE);
      whole.add(remove);

      setBackground(Color.WHITE);
      setSize(300,300);
      add(whole);
      setVisible(true);
   }

   private class TestBankSearchGUI extends JFrame
   {
      public TestBankSearchGUI()
      {
         setTitle("Society of Women Engineers");
         
          Box whole = new Box(BoxLayout.Y_AXIS);

          JLabel head = new JLabel("Test Bank Search");
          head.setMaximumSize(new Dimension(300, 100));
          head.setFont(new Font("Serif", Font.BOLD, 28));
          head.setHorizontalAlignment(SwingConstants.CENTER);
          whole.add(head);

          Box searchMaterial = new Box(BoxLayout.Y_AXIS);
          
          Box subject = new Box(BoxLayout.X_AXIS);
          
          subject.add(new JLabel("SUBJECT: "));
          JComboBox subjects = new JComboBox(subjectList);
          subjects.setMaximumSize(new Dimension(180, 30));
          subject.add(subjects);
          searchMaterial.add(subject);

          Box course = new Box(BoxLayout.X_AXIS);

          course.add(new JLabel("COURSE#: "));
          JTextField num = new JTextField();
          num.setMaximumSize(new Dimension(165, 30));
          num.setEditable(true);
          course.add(num);
          searchMaterial.add(course);

          Box prof = new Box(BoxLayout.X_AXIS);

          prof.add(new JLabel("PROFESSOR: "));
          JTextField pField = new JTextField();
          pField.setMaximumSize(new Dimension(165, 30));
          pField.setEditable(true);
          prof.add(pField);
          searchMaterial.add(prof);

          whole.add(searchMaterial);


      }
   }
   
   private void createDatabase()
   {
      try
      {
         Scanner scan = new Scanner(new File("test_bank.csv"));
         String line;
         Scanner lScan;
         testBank = new LinkedList<Test>();

         String sbj;
         int course;
         String qtr;
         String prof;
         int qty;

         int i = 0;
         String oldSbj = "";

         //Add file contents into a linked list of test objects
         while(scan.hasNextLine())
         {
            line = scan.nextLine();
            lScan = new Scanner(line).useDelimiter(",");
            sbj = lScan.next();
            course = lScan.nextInt();
            qtr = lScan.next();
            prof = lScan.next();
            qty = lScan.nextInt();
            testBank.add(new Test(sbj, course, qtr, prof, qty));
            
            if(!oldSbj.equals(sbj))
            {
               subjectList[i] = sbj;
               oldSbj = sbj;
            }
          }
         }catch(FileNotFoundException e)
         {
            System.out.println("Error: File not found");
         }
   }

}

