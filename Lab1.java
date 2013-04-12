/*
 * Cait Rahm and Matt Zimmerer
 * CPE 365
 * Lab1
 */
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;

public class Lab1
{
   public static void main(String[] args)
   {
      try
      {
         Scanner scan = new Scanner(new File("students.txt"));
         String line;
         Scanner lScan;
         LinkedList<Student> list= new LinkedList<Student>();
         String token;
         String first;
         String last;
         String tFirst;
         String tLast;
         int bus;
         int rm;
         int grade;
         int i=0;
         int c;
         double t1;
         double t2;

         //Add file contents into a linked list of student objects
         while(scan.hasNextLine())
         {
            line = scan.nextLine();
            lScan = new Scanner(line).useDelimiter(",");
            last = lScan.next();
            first = lScan.next();
            grade = lScan.nextInt();
            rm = lScan.nextInt();
            bus = lScan.nextInt();
            tFirst = lScan.next();
            tLast = lScan.next();
            c = 0;
            while(c < i && list.get(c).last.compareTo(last) < 0)
            {
               c++; 
            } 
            
            list.add(c, new Student(last, first, grade, rm, bus, tFirst, tLast));
            i++;
         }
         
         //Begin search
         scan = new Scanner(System.in);
         
         do
         {
            System.out.print("Please enter in your search or quit: ");
            token = scan.next();

            if(token.equals("S:") || token.equals("Student:"))
            {
               lScan = new Scanner(scan.nextLine());
               last = lScan.next();
               if(lScan.hasNext())
               {
                  token = lScan.next();
                  if(token.equals("Bus") || token.equals("B"))
                  {
                     System.out.println("Student's with lastname " + last + ":");
                     c = 0;
                     
                     //TIME START
                     t1 = System.nanoTime();
                     
                     while(list.get(c).last.compareTo(last) <= 0) 
                     {
                       if(list.get(c).last.equals(last))
                       System.out.println(list.get(c).last + ", " + list.get(c).first + ", " + list.get(c).bus); 
                       c++;
                     }
                     t2 = System.nanoTime();
                     //TIME STOP
                     t2 = (t2 - t1) / 1000000000; //Conversion from nano
                     System.out.println("Search time = " + t2 + " seconds"); 
                  }
                  else
                     System.out.println("Search format should be either S[tudent]: <lastname> or S[tudent]: <lastname> B[us]"); 
               }
               else
               {
                  System.out.println("Student's with lastname " + last + ":");
                  c = 0;
                  //TIME START
                  t1 = System.nanoTime();
                  while(list.get(c).last.compareTo(last) <= 0) 
                  {
                    if(list.get(c).last.equals(last))
                       System.out.println(list.get(c).last + ", " + list.get(c).first + ", " + list.get(c).grade + ", " + list.get(c).room + ", " + list.get(c).tLast + ", " + list.get(c).tFirst); 
                    c++;
                  }
                  t2 = System.nanoTime();
                  //TIME STOP
                  t2 = (t2 - t1) / 1000000000;
                  System.out.println("Search time = " + t2 + " seconds");
               
               }
            }
            else if(token.equals("T:") || token.equals("Teacher:"))
            {
               tLast = scan.next();
               System.out.println("Students of " + tLast + ":");
               //TIME START
               t1 = System.nanoTime();
               for(int j = 0; j < i; j++)
               {
                 if(list.get(j).tLast.equals(tLast))
                    System.out.println(list.get(j).last + ", " + list.get(j).first); 
               }
               t2 = System.nanoTime();
               //TIME STOP
               t2 = (t2 - t1) / 1000000000;
               System.out.println("Search time = " + t2 + " seconds");
            }
            else if(token.equals("C:") || token.equals("Classroom:"))
            {
               rm = scan.nextInt();
               System.out.println("Students in Classroom " + rm + ":");
               //TIME START
               t1 = System.nanoTime();
               for(int j = 0; j < i; j++)
               {
                 if(list.get(j).room == rm)
                    System.out.println(list.get(j).last + ", " + list.get(j).first); 
               }
               t2 = System.nanoTime();
               //TIME STOP
               t2 = (t2 - t1) / 1000000000;
               System.out.println("Search time = " + t2 + " seconds");   
            }
            else if(token.equals("B:") || token.equals("Bus:"))
            {
               bus = scan.nextInt();
               System.out.println("Students on Bus " + bus + ":");   
               //TIME START
               t1 = System.nanoTime();
               for(int j = 0; j < i; j++)
               {
                 if(list.get(j).bus == bus)
                    System.out.println(list.get(j).last + ", " + list.get(j).first + ", " + list.get(j).grade + ", " + list.get(j).room); 
               }
               t2 = System.nanoTime();
               //TIME STOP
               t2 = (t2 - t1) / 1000000000;
               System.out.println("Search time = " + t2 + " seconds");
            }
         }while(!token.equals("Q") && !token.equals("Quit"));
      
     }
     catch(FileNotFoundException e)
     {
        System.out.println("Error: File not found");
     }
   }
}
