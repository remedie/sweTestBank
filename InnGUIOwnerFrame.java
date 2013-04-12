import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class InnGUIOwnerFrame extends JFrame {
   protected InnDatabase db;
   protected InnGUI Parent;
   private String tmpStart;
   private String tmpEnd;
   private OccOverviewFrame OV;
 
   public InnGUIOwnerFrame(InnGUI Parent) {
      this.Parent = Parent;
      this.addWindowListener(this.Parent);
      setTitle("InnReservations Owner Menu");
      
      Box whole = new Box(BoxLayout.Y_AXIS);

      Box buttons = new Box(BoxLayout.Y_AXIS);
      buttons.setMaximumSize(new Dimension(150, 170));

      Box fillerV1 = new Box(BoxLayout.Y_AXIS);
      fillerV1.setMaximumSize(new Dimension(140, 15));

      JButton occOvervButton = new JButton("Occupancy");
      occOvervButton.setMaximumSize(new Dimension(150, 28));
      occOvervButton.addActionListener(new OccButtonListener());
      buttons.add(occOvervButton);
      buttons.add(fillerV1);

      Box fillerV2 = new Box(BoxLayout.Y_AXIS);
      fillerV2.setMaximumSize(new Dimension(140, 15));

      JButton revenueButton = new JButton("Revenue");
      revenueButton.setMaximumSize(new Dimension(150, 28));
      revenueButton.addActionListener(new RevButtonListener());
      buttons.add(revenueButton);
      buttons.add(fillerV2);

      Box fillerV3 = new Box(BoxLayout.Y_AXIS);
      fillerV3.setMaximumSize(new Dimension(140, 15));

      JButton reservButton = new JButton("Reservations");
      reservButton.setMaximumSize(new Dimension(150, 28));
      reservButton.addActionListener(new ResButtonListener());
      buttons.add(reservButton);
      buttons.add(fillerV3);

      Box fillerV4 = new Box(BoxLayout.Y_AXIS);
      fillerV4.setMaximumSize(new Dimension(140, 15));

      JButton roomsButton = new JButton("Rooms");
      roomsButton.setMaximumSize(new Dimension(150, 28));
      roomsButton.addActionListener(new RmsButtonListener());
      buttons.add(roomsButton);
      buttons.add(fillerV4);

      JLabel head = new JLabel("Welcome Owner");
      head.setMaximumSize(new Dimension(300, 100));
      head.setFont(new Font("Serif", Font.BOLD, 28));
      head.setHorizontalAlignment(SwingConstants.CENTER);
      head.setOpaque(true);
      head.setBackground(Color.pink);
      
      Box fillerV5 = new Box(BoxLayout.Y_AXIS);
      fillerV5.setMaximumSize(new Dimension(140, 15));

      whole.add(head);
      whole.add(fillerV5);
      whole.add(buttons);
      setSize(300,300);
      add(whole);
      setVisible(true); 
   }
    
   private class OccOverviewFrame extends JFrame
   {
      public OccOverviewFrame()
      {
          setTitle("InnReservations Occupancy Overview");

          Box whole = new Box(BoxLayout.Y_AXIS);

          Box dates = new Box(BoxLayout.X_AXIS);

          Box start = new Box(BoxLayout.Y_AXIS);
          
          Box occlist = new Box(BoxLayout.Y_AXIS);

          JLabel startLabel = new JLabel("Start Date");
          startLabel.setFont(new Font("Arial", Font.BOLD, 12));
          start.add(startLabel);
          
          final JFormattedTextField startDate = new JFormattedTextField();
          startDate.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent p){
                startDate.selectAll();
                tmpStart = startDate.getSelectedText(); 
          }});
          startDate.setMaximumSize(new Dimension(165,30));
          start.add(startDate);
          
          Box end = new Box(BoxLayout.Y_AXIS);         
          
          JLabel endLabel = new JLabel("End Date");
          endLabel.setFont(new Font("Arial", Font.BOLD, 12));
          end.add(endLabel);
          
          final JTextField endDate = new JTextField();
          endDate.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent a) {
                endDate.selectAll();
                tmpEnd = endDate.getSelectedText(); 
          }});
          endDate.setMaximumSize(new Dimension(165,30));
          endDate.setEditable(true);
          end.add(endDate);

          dates.add(start);
          dates.add(end);

          JButton srch = new JButton("Search");
          srch.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                setVisible(false);
                OccOverviewTable o = new OccOverviewTable();
             }
          });
          dates.add(srch);
          
          Box filler = new Box(BoxLayout.Y_AXIS);
          filler.setMaximumSize(new Dimension(200, 100));

          whole.add(dates);
          whole.add(filler);
          whole.add(occlist);
          setSize(450,450);
          add(whole);
          setVisible(true);
          
      }

  }
 
   private class OccOverviewTable extends JFrame
   {
      public OccOverviewTable()
      {

          setTitle("InnReservations Occupancy Overview");
          
          boolean twoDates = checkDates();

          Box whole = new Box(BoxLayout.Y_AXIS);

          Box dates = new Box(BoxLayout.X_AXIS);

          Box start = new Box(BoxLayout.Y_AXIS);
          
          Box occlist = new Box(BoxLayout.Y_AXIS);

          JLabel startLabel = new JLabel("Start Date");
          startLabel.setFont(new Font("Arial", Font.BOLD, 12));
          start.add(startLabel);
          
          final JTextField startDate = new JTextField(tmpStart);
          startDate.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent a) {
                startDate.selectAll();
                tmpStart = startDate.getSelectedText();
             }});
          startDate.setMaximumSize(new Dimension(165,30));
          start.add(startDate);
          
          Box end = new Box(BoxLayout.Y_AXIS);         
          
          JLabel endLabel = new JLabel("End Date");
          endLabel.setFont(new Font("Arial", Font.BOLD, 12));
          end.add(endLabel);
          
          final JTextField endDate = new JTextField(tmpEnd);
          endDate.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent a) {
                endDate.selectAll();
                tmpEnd = endDate.getSelectedText(); 
          }});
          endDate.setMaximumSize(new Dimension(165,30));
          end.add(endDate);

          dates.add(start);
          dates.add(end);

          JButton srch = new JButton("Search");
          srch.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                setVisible(false);
                OccOverviewTable o = new OccOverviewTable();
             }
          });
          dates.add(srch);
          
          Box labels = new Box(BoxLayout.X_AXIS);
          
          JLabel ID = new JLabel("     ID     ");
          ID.setMaximumSize(new Dimension(150, 40));
          ID.setFont(new Font("Arial", Font.BOLD, 14));
          
          JLabel name = new JLabel("     NAME     ");
          name.setMaximumSize(new Dimension(150, 40));
          name.setFont(new Font("Arial", Font.BOLD, 14));
          
          JLabel status = new JLabel("     STATUS     ");
          status.setMaximumSize(new Dimension(150, 40));
          status.setFont(new Font("Arial", Font.BOLD, 14));
          
          labels.add(ID);
          labels.add(name);
          labels.add(status);

          String[] col = {"ID", "Name", "Status"};
          final JTable listTable = new JTable(roomStatus(tmpStart, tmpEnd), col);
          if(twoDates)
          {
             listTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent m){
                   String str = (String)listTable.getValueAt(listTable.getSelectedRow(),1);
                   BListFrame b = new BListFrame(str);        
                }
             });
          }
          else
          {
             listTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent m){
                   String str = (String)listTable.getValueAt(listTable.getSelectedRow(),1);
                   String code = getReservationCode(str);
                   DetailedFrame d = new DetailedFrame(code, str);        
                }
             });
             
          }
          occlist.add(listTable);
        
          Box filler = new Box(BoxLayout.Y_AXIS);
          filler.setMaximumSize(new Dimension(200, 100));

          whole.add(dates);
          whole.add(filler);
          whole.add(labels);
          whole.add(occlist);
          setSize(450,450);
          add(whole);
          setVisible(true);
          
      }
   }
   
   //determines if two dates were entered or not
   public boolean checkDates()
   {
      if (tmpEnd == null)
      {
         tmpEnd = tmpStart;
         return false;
      }
      return true;
   }

   public String[][] roomStatus(String s, String e)
   {
       Connection conn = InnDatabase.getConnection();
       String[][] list = new String[10][3];
       int i = 0;
       String sql = "";

       try
       {
          Statement s1 = conn.createStatement();
          
          sql += "(SELECT rm.RoomName, rm.RoomID, 'Fully Occupied' AS STATUS";
          sql += " FROM Rooms rm, Reservations r";
          sql += " WHERE rm.RoomID = r.Room AND ((r.CheckOut <= '" + e + "'";
          sql += " AND r.CheckOut > '" + s + "') OR (r.CheckIn <= '" + s + "'";
          sql += " AND r.CheckOut >= '" + e + "'))";
          sql += " GROUP BY rm.RoomID, rm.RoomName";
          sql += " HAVING SUM(r.CheckOut - r.CheckIn) >= (TO_DATE('" + e + "', 'DD-MON-YYYY') -";
          sql += " TO_DATE('" + s + "', 'DD-MON-YYYY')))";
          sql += " UNION";
          sql += " ((SELECT rm.RoomName, rm.RoomID, 'Partially Occupied' AS STATUS";
          sql += " FROM Rooms rm, Reservations r";
          sql += " WHERE rm.RoomID = r.Room AND ((r.CheckOut > '" + s + "'";
          sql += " AND r.CheckOut < '" + e + "') OR (r.CheckIn > '" + s + "'";
          sql += " AND r.CheckIn < '" + e + "'))";
          sql += " GROUP BY rm.RoomID, rm.RoomName)";
          sql += " MINUS";
          sql += " (SELECT rm.RoomName, rm.RoomID, 'Partially Occupied' AS STATUS";
          sql += " FROM Rooms rm, Reservations r";
          sql += " WHERE rm.RoomID = r.Room AND r.CheckOut <= '" + e + "'";
          sql += " AND r.CheckOut > '" + s + "'";
          sql += " GROUP BY rm.RoomID, rm.RoomName";
          sql += " HAVING SUM(r.CheckOut - r.CheckIn) >= (TO_DATE('" + e + "', 'DD-MON-YYYY') -";
          sql += " TO_DATE('" + s + "', 'DD-MON-YYYY'))))";
          sql += " UNION";
          sql += " ((SELECT rm.RoomName, rm.RoomID, 'Empty' AS STATUS";
          sql += " FROM Rooms rm)";
          sql += " MINUS";
          sql += " (SELECT rm.RoomName, rm.RoomID, 'Empty' AS STATUS";
          sql += " FROM Rooms rm, Reservations r";
          sql += " WHERE rm.RoomID = r.Room AND ((r.CheckOut <= '" + e + "'";
          sql += " AND r.CheckOut > '" + s + "') OR (r.CheckIn <= '" + s + "'";
          sql += " AND r.CheckOut >= '" + e + "'))";
          sql += " GROUP BY rm.RoomID, rm.RoomName";
          sql += " HAVING SUM(r.CheckOut - r.CheckIn) >= (TO_DATE('" + e + "', 'DD-MON-YYYY') -";
          sql += " TO_DATE('" + s + "', 'DD-MON-YYYY')))";
          sql += " MINUS";
          sql += " (SELECT rm.RoomName, rm.RoomID, 'Empty' AS STATUS";
          sql += " FROM Rooms rm, Reservations r";
          sql += " WHERE rm.RoomID = r.Room AND ((r.CheckOut > '" + s + "'";
          sql += " AND r.CheckOut < '" + e + "') OR (r.CheckIn > '" + s + "'";
          sql += " AND r.CheckIn < '" + e + "'))";
          sql += " GROUP BY rm.RoomID, rm.RoomName))";
          sql += "ORDER BY RoomID";

          ResultSet result = s1.executeQuery(sql);
          boolean f = result.next();
          while(f)
          {
              list[i][0] = result.getString(1);
              list[i][1] = result.getString(2);
              list[i][2] = result.getString(3);
              f = result.next();
              i++;
           }
        } catch(Exception ex) {System.out.println(ex);};
        
       return list; 
   }

   public String getReservationCode(String rm)
   {
       Connection conn = InnDatabase.getConnection();
       String code= "";
       String sql = "";

       try
       {
          Statement s1 = conn.createStatement();
          
          sql += "SELECT Code FROM Reservations WHERE Room = '" + rm + "'";
          sql += " AND CheckIn <= '" + tmpStart + "' AND CheckOut > '" + tmpEnd + "'";

          ResultSet result = s1.executeQuery(sql);
          boolean f = result.next();
          code = result.getString(1);
       } catch(Exception ex) {System.out.println(ex);};

       return code; 
   }

   private class BListFrame extends JFrame
   {
      private String rm;

      public BListFrame(String r)
      {
          this.rm = r;
          setTitle("InnReservations Occupancy Overview");

          Box whole = new Box(BoxLayout.Y_AXIS);

          Box labels = new Box(BoxLayout.X_AXIS);
          
          String[] col = {"Code", "In", "Out"};
          final JTable listTable = new JTable(briefReservations(rm), col);
          listTable.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent m){
                DetailedFrame d = new DetailedFrame((String)listTable.getValueAt(listTable.getSelectedRow(), 0), rm);
             }
          });

          Box occlist = new Box(BoxLayout.Y_AXIS);
          occlist.add(new JScrollPane(listTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)); 
        
          whole.add(occlist);
          setSize(450,450);
          add(whole);
          setVisible(true);
      }
   }

   public String[][] briefReservations(String rm)
   {
       Connection conn = InnDatabase.getConnection();
       if(tmpStart == null)
          tmpStart = "01-JAN-2009";
       if(tmpEnd == null)
          tmpEnd = "04-JAN-2012";
 
       String[][] list = new String[countBriefReservations(rm)][3];
       int i = 0;
       
       String sql = "";
       try
       {
          Statement s1 = conn.createStatement();
        
          sql += "SELECT r.Code, TO_CHAR(r.CheckIn, 'DD-MON-YYYY'), TO_CHAR(r.CheckOut, 'DD-MON-YYYY')";
          sql += " FROM Reservations r";
          sql += " WHERE r.Room = '" + rm + "'";
          sql += " AND ((r.CheckOut <= '" + tmpEnd + "' AND r.CheckOut > '" + tmpStart + "')";
          sql += " OR (r.CheckIn <= '" + tmpStart + "' AND r.CheckOut >= '" + tmpEnd + "')";
          sql += " OR (r.CheckIn >= '" + tmpStart + "' AND r.CheckIn < '" + tmpEnd + "'))";
          sql += " ORDER BY r.CheckIn";

          ResultSet result = s1.executeQuery(sql);

          boolean f = result.next();

          while(f)
          {
              list[i][0] = result.getString(1);
              list[i][1] = result.getString(2);
              list[i][2] = result.getString(3);
              f = result.next();
              i++;
           }
        } catch(Exception ex) {System.out.println(ex);};
       
       if(tmpStart == "01-JAN-2009")
          tmpStart = null;
       if(tmpEnd == "04-JAN-2012")
          tmpEnd = null;        
       return list; 
   }

   public int countBriefReservations(String rm)
   {
       Connection conn = InnDatabase.getConnection();
       int i = 0;
       
       String sql = "";
       try
       {
          Statement s1 = conn.createStatement();
        
          sql += "SELECT COUNT(*)";
          sql += " FROM Reservations r";
          sql += " WHERE r.Room = '" + rm + "'";
          sql += " AND ((r.CheckOut <= '" + tmpEnd + "' AND r.CheckOut > '" + tmpStart + "')";
          sql += " OR (r.CheckIn <= '" + tmpStart + "' AND r.CheckOut >= '" + tmpEnd + "')";
          sql += " OR (r.CheckIn >= '" + tmpStart + "' AND r.CheckIn < '" + tmpEnd + "'))";

          ResultSet result = s1.executeQuery(sql);

          boolean f = result.next();
          i = result.getInt(1);

        } catch(Exception ex) {System.out.println(ex);};
        
       return i; 
   }

   private class DetailedFrame extends JFrame
   {
      private String code;
      private String room;

      public DetailedFrame(String c, String rm)
      {
         this.code = c;
         this.room = rm;

         setTitle("InnReservations Detailed Reservation Information");
         
         Box whole = new Box(BoxLayout.Y_AXIS);
         String[] col = {"Categories", "Values"};
         JTable list = new JTable(detReservations(room, code), col); 
         whole.add(list);
         setSize(450,450);
         add(whole);
         setVisible(true);
      }
   }
   
   public String[][] detReservations(String rm, String code)
   {
       Connection conn = InnDatabase.getConnection();
    
       String[][] list = new String[9][2];
       
       list[0][0] = "CODE";
       list[1][0] = "CHECKIN";
       list[2][0] = "CHECKOUT";
       list[3][0] = "ROOM NAME";
       list[4][0] = "NIGHTLY RATE";
       list[5][0] = "LAST NAME";
       list[6][0] = "FIRST NAME";
       list[7][0] = "# ADULTS";
       list[8][0] = "# KIDS";
       
       int i = 0;
       
       String sql = "";
       try
       {
          Statement s1 = conn.createStatement();
        
          sql += "SELECT r.Code, TO_CHAR(r.CheckIn, 'DD-MON-YYYY'), TO_CHAR(r.CheckOut, 'DD-MON-YYYY')";
          sql += ", rm.RoomName, r.Rate, r.LastName, r.FirstName, r.Adults, r.Kids";
          sql += " FROM Reservations r, Rooms rm";
          sql += " WHERE r.Room = '" + rm + "' AND rm.RoomID = r.Room";
          sql += " AND r.Code = " + code;

          ResultSet result = s1.executeQuery(sql);

          boolean f = result.next();
          list[0][1] = result.getString(1);
          list[1][1] = result.getString(2);
          list[2][1] = result.getString(3);
          list[3][1] = result.getString(4);
          list[4][1] = result.getString(5);
          list[5][1] = result.getString(6);
          list[6][1] = result.getString(7);
          list[7][1] = result.getString(8);
          list[8][1] = result.getString(9);
        } catch(Exception ex) {System.out.println(ex);};
        
       return list; 
   }

   private class ReservationsFrame extends JFrame
   {
      public ReservationsFrame()
      {
          setTitle("InnReservations Reservations");

          Box whole = new Box(BoxLayout.Y_AXIS);

          Box dates = new Box(BoxLayout.X_AXIS);

          Box start = new Box(BoxLayout.Y_AXIS);

          JLabel startLabel = new JLabel("Start Date");
          startLabel.setFont(new Font("Arial", Font.BOLD, 12));
          start.add(startLabel);
          
          final JTextField startDate = new JTextField();
          startDate.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent a) {
                startDate.selectAll();
                tmpStart = startDate.getSelectedText();
             }});
          startDate.setMaximumSize(new Dimension(165,30));
          start.add(startDate);
          
          Box end = new Box(BoxLayout.Y_AXIS);         
          
          JLabel endLabel = new JLabel("End Date");
          endLabel.setFont(new Font("Arial", Font.BOLD, 12));
          end.add(endLabel);
          
          final JTextField endDate = new JTextField();
          endDate.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent a) {
                endDate.selectAll();
                tmpEnd = endDate.getSelectedText();
             }});
          endDate.setMaximumSize(new Dimension(165,30));
          end.add(endDate);

          dates.add(start);
          dates.add(end);
          
          Box filler3 = new Box(BoxLayout.X_AXIS);
          filler3.setMaximumSize(new Dimension(200, 100));
          dates.add(filler3);
 
          Box rms = new Box(BoxLayout.X_AXIS);
         
          JLabel rmLabel = new JLabel("Room");
          rmLabel.setFont(new Font("Arial", Font.BOLD, 12));
          rms.add(rmLabel);

          final JComboBox rm = new JComboBox(listRooms());
          rm.setMaximumSize(new Dimension(180, 30));
          rms.add(rm);
          
          JButton srch = new JButton("Search");
          srch.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                setVisible(false);
                ReservationsTable o = new ReservationsTable((String)rm.getSelectedItem());
             }
          });
          rms.add(srch);
 
          Box list = new Box(BoxLayout.Y_AXIS);
          
          JTable listTable = new JTable(10, 2);
          list.add(listTable);
        
          Box filler = new Box(BoxLayout.Y_AXIS);
          filler.setMaximumSize(new Dimension(200, 100));
          
          Box filler2 = new Box(BoxLayout.X_AXIS);
          filler2.setMaximumSize(new Dimension(230, 100));
          rms.add(filler2);

          whole.add(dates);
          whole.add(rms);
          whole.add(filler);
          whole.add(list);
          setSize(450,450);
          add(whole);
          setVisible(true);
          
      }
   }

   private class ReservationsTable extends JFrame
   {
      private String room;

      public ReservationsTable(String r)
      {
          this.room = r;

          setTitle("InnReservations Reservations");
          
          boolean twoDates = checkDates();

          Box whole = new Box(BoxLayout.Y_AXIS);

          Box dates = new Box(BoxLayout.X_AXIS);

          Box start = new Box(BoxLayout.Y_AXIS);
          
          Box occlist = new Box(BoxLayout.Y_AXIS);

          JLabel startLabel = new JLabel("Start Date");
          startLabel.setFont(new Font("Arial", Font.BOLD, 12));
          start.add(startLabel);
          
          final JTextField startDate = new JTextField(tmpStart);
          startDate.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent a) {
                startDate.selectAll();
                tmpStart = startDate.getSelectedText();
             }});
          startDate.setMaximumSize(new Dimension(165,30));
          start.add(startDate);
          
          Box end = new Box(BoxLayout.Y_AXIS);         
          
          JLabel endLabel = new JLabel("End Date");
          endLabel.setFont(new Font("Arial", Font.BOLD, 12));
          end.add(endLabel);
          
          final JTextField endDate = new JTextField(tmpEnd);
          endDate.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent a) {
                endDate.selectAll();
                tmpEnd = endDate.getSelectedText(); 
          }});
          endDate.setMaximumSize(new Dimension(165,30));
          end.add(endDate);

          dates.add(start);
          dates.add(end);
          
          Box rms = new Box(BoxLayout.X_AXIS);
         
          JLabel rmLabel = new JLabel("Room");
          rmLabel.setFont(new Font("Arial", Font.BOLD, 12));
          rms.add(rmLabel);

          final JComboBox rm = new JComboBox(listRooms());
          rm.setMaximumSize(new Dimension(180, 30));
          rms.add(rm);

          JButton srch = new JButton("Search");
          srch.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                setVisible(false);
                ReservationsTable o = new ReservationsTable((String)rm.getSelectedItem());
             }
          });
          dates.add(srch);
          
          Box labels = new Box(BoxLayout.X_AXIS);
          
          String[] col = {"Code", "Room", "In", "Out"};
          final JTable listTable;

          if(room == "" && tmpStart != null)
             listTable = new JTable(listReservations(), col);
          else if(room != "" && tmpStart == null)
             listTable = new JTable(listReservations(room), col);
          else if(room != "" && tmpStart != null)
             listTable = new JTable(listReservations(room, tmpStart), col);
          else
             listTable = new JTable(listAllReservations(), col);
             
             
             
           listTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent m){
                   DetailedFrame b = new DetailedFrame((String)listTable.getValueAt(listTable.getSelectedRow(),0), (String)listTable.getValueAt(listTable.getSelectedRow(),1));        
                }
             });

          
          occlist.add(new JScrollPane(listTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)); 
          Box filler = new Box(BoxLayout.Y_AXIS);
          filler.setMaximumSize(new Dimension(200, 100));

          whole.add(dates);
          whole.add(rms);
          whole.add(filler);
          //whole.add(labels);
          whole.add(occlist);
          setSize(450,450);
          add(whole);
          setVisible(true);
          
      }
   }
   
   public String[][] listReservations()
   {
       Connection conn = InnDatabase.getConnection();
       String[][] list = new String[getTotalDateReservationCount()][4];
       int i = 0;
       String sql ="";

       try
       {
          Statement s1 = conn.createStatement();
          sql += "SELECT Code, Room, TO_CHAR(CheckIn, 'DD-MON-YYYY'),";
          sql += " TO_CHAR(CheckOut, 'DD-MON-YYYY')";
          sql += " FROM Reservations";
          sql += " WHERE CheckIn >= '" + tmpStart + "' AND CheckIn <= '" + tmpEnd + "'"; 
          ResultSet result = s1.executeQuery(sql);
          boolean f = result.next();
          while(f)
          {
              list[i][0] = result.getString(1);
              list[i][1] = result.getString(2);
              list[i][2] = result.getString(3);
              list[i][3] = result.getString(4);
              f = result.next();
              i++;
           }
        } catch(Exception e) {System.out.println(e);};
        
       return list; 
   }
   
   public String[][] listReservations(String rm)
   {
       Connection conn = InnDatabase.getConnection();
       String[][] list = new String[getTotalRoomReservationCount(rm)][4];
       int i = 0;
       String sql ="";

       try
       {
          Statement s1 = conn.createStatement();
          sql += "SELECT r.Code, r.Room, TO_CHAR(r.CheckIn, 'DD-MON-YYYY'),";
          sql += " TO_CHAR(r.CheckOut, 'DD-MON-YYYY')";
          sql += " FROM Reservations r, Rooms rm";
          sql += " WHERE rm.RoomName = '" + rm + "' AND r.Room = rm.RoomID";
          sql += " ORDER BY r.CheckIn"; 
          ResultSet result = s1.executeQuery(sql);
          boolean f = result.next();
          while(f)
          {
              list[i][0] = result.getString(1);
              list[i][1] = result.getString(2);
              list[i][2] = result.getString(3);
              list[i][3] = result.getString(4);
              f = result.next();
              i++;
           }
        } catch(Exception e) {System.out.println(e);};
        
       return list; 
   }

   public String[][] listReservations(String rm, String date)
   {
       Connection conn = InnDatabase.getConnection();
       String[][] list = new String[getTotalDateRoomReservationCount(rm)][4];
       int i = 0;
       String sql ="";

       try
       {
          Statement s1 = conn.createStatement();
          sql += "SELECT r.Code, r.Room, TO_CHAR(r.CheckIn, 'DD-MON-YYYY'),";
          sql += " TO_CHAR(r.CheckOut, 'DD-MON-YYYY')";
          sql += " FROM Reservations r, Rooms rm";
          sql += " WHERE rm.RoomName = '" + rm + "' AND r.Room = rm.RoomID"; 
          sql += " AND r.CheckIn >= '" + tmpStart + "' AND r.CheckIn <= '" + tmpEnd + "'"; 
          ResultSet result = s1.executeQuery(sql);
          boolean f = result.next();
          while(f)
          {
              list[i][0] = result.getString(1);
              list[i][1] = result.getString(2);
              list[i][2] = result.getString(3);
              list[i][3] = result.getString(4);
              f = result.next();
              i++;
           }
        } catch(Exception e) {System.out.println(e);};
        
       return list; 
   }

   public int getTotalReservationCount()
   {
      Connection conn = InnDatabase.getConnection();
      int total = 0;
      try
      {
          Statement s1 = conn.createStatement();
          ResultSet result = s1.executeQuery("SELECT COUNT(*) FROM Reservations");
          boolean f = result.next();
          total = result.getInt(1);
      } catch(Exception e) {System.out.println(e);};
      return total;
   }
   
   public int getTotalRoomReservationCount(String rm)
   {
      Connection conn = InnDatabase.getConnection();
      int total = 0;
      String sql = "";

      try
      {
          Statement s1 = conn.createStatement();
          sql += "SELECT COUNT(*)";
          sql += " FROM Reservations r, Rooms rm";
          sql += " WHERE rm.RoomName = '" + rm + "' AND r.Room = rm.RoomID";
          sql += " ORDER BY r.CheckIn"; 
          ResultSet result = s1.executeQuery(sql);
          boolean f = result.next();
          total = result.getInt(1);
      } catch(Exception e) {System.out.println(e);};
      return total;
   }

   public int getTotalDateRoomReservationCount(String rm)
   {
      Connection conn = InnDatabase.getConnection();
      int total = 0;
      String sql = "";

      try
      {
          Statement s1 = conn.createStatement();
          sql += "SELECT COUNT(*)";
          sql += " FROM Reservations r, Rooms rm";
          sql += " WHERE rm.RoomName = '" + rm + "' AND r.Room = rm.RoomID"; 
          sql += " AND r.CheckIn >= '" + tmpStart + "' AND r.CheckIn <= '" + tmpEnd + "'"; 
          ResultSet result = s1.executeQuery(sql);
          boolean f = result.next();
          total = result.getInt(1);
      } catch(Exception e) {System.out.println(e);};
      return total;
   }

   public int getTotalDateReservationCount()
   {
      Connection conn = InnDatabase.getConnection();
      int total = 0;
      String sql = "";

      try
      {
          Statement s1 = conn.createStatement();
          sql += "SELECT COUNT(*)";
          sql += " FROM Reservations r";
          sql += " WHERE r.CheckIn >= '" + tmpStart + "' AND r.CheckIn <= '" + tmpEnd + "'"; 
          ResultSet result = s1.executeQuery(sql);
          boolean f = result.next();
          total = result.getInt(1);
      } catch(Exception e) {System.out.println(e);};
      return total;
   }
   public String[][] listAllReservations()
   {
       Connection conn = InnDatabase.getConnection();
       String[][] list = new String[getTotalReservationCount()][4];
       int i = 0;
       String sql ="";

       try
       {
          Statement s1 = conn.createStatement();
          sql += "SELECT Code, Room, TO_CHAR(CheckIn, 'DD-MON-YYYY'),";
          sql += " TO_CHAR(CheckOut, 'DD-MON-YYYY') FROM Reservations";
          ResultSet result = s1.executeQuery(sql);
          boolean f = result.next();
          while(f)
          {
              list[i][0] = result.getString(1);
              list[i][1] = result.getString(2);
              list[i][2] = result.getString(3);
              list[i][3] = result.getString(4);
              f = result.next();
              i++;
           }
        } catch(Exception e) {System.out.println(e);};
        
       return list; 
    }
   
   //for combo box selection
   public String[] listRooms()
   {
       Connection conn = InnDatabase.getConnection();
       String[] list = new String[11];
       list[0] = "";
       int i = 1;

       try
       {
          Statement s1 = conn.createStatement();
          ResultSet result = s1.executeQuery("SELECT * FROM Rooms");
          boolean f = result.next();
          while(f)
          {
              list[i] = result.getString(2);
              f = result.next();
              i++;
           }
        } catch(Exception e) {System.out.println(e);};
        
       return list; 
    }
   
   private class RoomsFrame extends JFrame
   {
      public RoomsFrame()
      {
         setTitle("InnReservations Rooms");
         
         Box whole = new Box(BoxLayout.X_AXIS);
         Box rooms = new Box(BoxLayout.Y_AXIS);

         final JTable list = new JTable(10, 1);
         String[] rms = listRooms();
         
         list.setValueAt(rms[1], 0, 0);
         list.setValueAt(rms[2], 1, 0);
         list.setValueAt(rms[3], 2, 0);
         list.setValueAt(rms[4], 3, 0);
         list.setValueAt(rms[5], 4, 0);
         list.setValueAt(rms[6], 5, 0);
         list.setValueAt(rms[7], 6, 0);
         list.setValueAt(rms[8], 7, 0);
         list.setValueAt(rms[9], 8, 0);
         list.setValueAt(rms[10], 9, 0);
         
         JLabel label = new JLabel("ROOMS");
         label.setFont(new Font("Arial", Font.BOLD, 14));

         rooms.add(label);
         rooms.add(list);

         JButton b1 = new JButton("Room Info");
         b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a){
               RoomInfoFrame r = new RoomInfoFrame((String)list.getValueAt(list.getSelectedRow(),0)); 
         }});
         b1.setMaximumSize(new Dimension(150, 25));
         
         JButton b2 = new JButton("Reservations");
         b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){
               String tmp = getRoomID((String)list.getValueAt(list.getSelectedRow(),0));
               BListFrame b = new BListFrame(tmp);
         }});
         b2.setMaximumSize(new Dimension(150, 25));
         
         Box buttons = new Box(BoxLayout.Y_AXIS);
         buttons.add(b1);
         buttons.add(b2);

         whole.add(rooms);
         whole.add(buttons);
         setSize(400,300);
         add(whole);
         setVisible(true);
      }
   }

   public String getRoomID(String rm)
   {
       Connection conn = InnDatabase.getConnection();
       String ID = "";

       try
       {
          Statement s1 = conn.createStatement();
          ResultSet result = s1.executeQuery("SELECT RoomID FROM Rooms WHERE RoomName = '" + rm + "'");
          boolean f = result.next();
          ID = result.getString(1);
       } catch(Exception e) {System.out.println(e);};
        
       return ID; 
   }
  
   private class RoomInfoFrame extends JFrame
   {
      private String room;

      public RoomInfoFrame(String r)
      {
         this.room = r;
         setTitle("Room Information");
         
         Box whole = new Box(BoxLayout.Y_AXIS);
         String[] col = {"Categories", "Values"};
         JTable list = new JTable(roomInfo(room), col);
         whole.add(list);
         
         setSize(400, 400);
         add(whole);
         setVisible(true);
      }
   }

   public String[][] roomInfo(String rm)
   {
       Connection conn = InnDatabase.getConnection();
    
       String[][] list = new String[11][2];
       
       list[0][0] = "ID";
       list[1][0] = "NAME";
       list[2][0] = "# BEDS";
       list[3][0] = "BED TYPE";
       list[4][0] = "MAX GUESTS";
       list[5][0] = "BASE RATE";
       list[6][0] = "DECOR";
       list[7][0] = "# NIGHTS OCCUPIED";
       list[8][0] = "% OCCUPIED";
       list[9][0] = "REVENUE";
       list[10][0] = "% REVENUE";
       
       
       int i = 0;
       
       String sql = "";
       try
       {
          Statement s1 = conn.createStatement();
        
          sql += "SELECT *";
          sql += " FROM Rooms";
          sql += " WHERE RoomName = '" + rm + "'";

          ResultSet result = s1.executeQuery(sql);

          boolean f = result.next();
          list[0][1] = result.getString(1);
          list[1][1] = result.getString(2);
          list[2][1] = result.getString(3);
          list[3][1] = result.getString(4);
          list[4][1] = result.getString(5);
          list[5][1] = result.getString(6);
          list[6][1] = result.getString(7);
        } catch(Exception ex) {System.out.println(ex);};
       
        list[7][1] = "" + occupancy(rm);
        list[8][1] = "" + percentOccupied(rm);
        list[9][1] = "" + totalRev(rm);
        list[10][1] = "" + totalRev(rm) / totalRev();
        
        
       return list; 
   }
   
   public int occupancy(String rm)
   {
       Connection conn = InnDatabase.getConnection();
    
       int num = 0;
 
       String sql = "";
       try
       {
          Statement s1 = conn.createStatement();
        
          sql += "SELECT SUM(r.CheckOut - r.CheckIn)";
          sql += " FROM Rooms rm, Reservations r";
          sql += " WHERE rm.RoomName = '" + rm + "'";
          sql += " AND r.Room = rm.RoomID";

          ResultSet result = s1.executeQuery(sql);

          boolean f = result.next();
          num = result.getInt(1);

        } catch(Exception ex) {System.out.println(ex);};
        
       return num; 
   }


   public int reservations(String rm)
   {
       Connection conn = InnDatabase.getConnection();
    
       int num = 0;
 
       String sql = "";
       try
       {
          Statement s1 = conn.createStatement();
        
          sql += "SELECT COUNT(*)";
          sql += " FROM Rooms rm, Reservations r";
          sql += " WHERE rm.RoomName = '" + rm + "'";
          sql += " AND r.Room = rm.RoomID";

          ResultSet result = s1.executeQuery(sql);

          boolean f = result.next();
          num = result.getInt(1);

        } catch(Exception ex) {System.out.println(ex);};
        
       return num; 
   }

   public double percentOccupied(String rm)
   {
       Connection conn = InnDatabase.getConnection();

       String max = maxDate();
       String min = minDate();
       double num = 0;
 
       String sql = "";
       try
       {
          Statement s1 = conn.createStatement();
        
          sql += "SELECT SUM(r.CheckOut - r.CheckIn) / (";
          sql += "TO_DATE('" + max + "', 'DD-MON-YYYY') - ";
          sql += "TO_DATE('" + min + "', 'DD-MON-YYYY'))";
          sql += " FROM Rooms rm, Reservations r";
          sql += " WHERE rm.RoomName = '" + rm + "'";
          sql += " AND r.Room = rm.RoomID";

          ResultSet result = s1.executeQuery(sql);

          boolean f = result.next();
          num = result.getDouble(1);

        } catch(Exception ex) {System.out.println(ex);};
        
       return num;
   }
   
   public String maxDate()
   {
      Connection conn = InnDatabase.getConnection();

      String max = "";
      String sql = "";

      try
      {
         Statement s1 = conn.createStatement();
     
         sql += "SELECT TO_CHAR(MAX(CheckOut), 'DD-MON-YYYY') FROM Reservations";
         
         ResultSet result = s1.executeQuery(sql);
         boolean f = result.next();
         max = result.getString(1);

      } catch(Exception ex) {System.out.println(ex);};

     return max;
   }  

   public String minDate()
   {
      Connection conn = InnDatabase.getConnection();

      String min = "";
      String sql = "";

      try
      {
         Statement s1 = conn.createStatement();
     
         sql += "SELECT TO_CHAR(MIN(CheckIn), 'DD-MON-YYYY') FROM Reservations";
         
         ResultSet result = s1.executeQuery(sql);
         boolean f = result.next();
         min = result.getString(1);

      } catch(Exception ex) {System.out.println(ex);};

     return min;
   }

   public double totalRev(String rm)
   {
      Connection conn = InnDatabase.getConnection();
 
      double rev = 0;

      String sql = "";

      try
      {
         Statement s1 = conn.createStatement();
     
         sql += "SELECT SUM(r.Rate * (r.CheckOut - r.CheckIn))";
         sql += " FROM Reservations r, Rooms rm";
         sql += " WHERE r.Room = rm.RoomID AND rm.RoomName = '" + rm + "'";
         
         ResultSet result = s1.executeQuery(sql);
         boolean f = result.next();
         rev = result.getDouble(1);

      } catch(Exception ex) {System.out.println(ex);};

     return rev;
   }
   
   public double totalRev()
   {
      Connection conn = InnDatabase.getConnection();
 
      double rev = 0;

      String sql = "";

      try
      {
         Statement s1 = conn.createStatement();
     
         sql += "SELECT SUM(Rate * (CheckOut - CheckIn))";
         sql += " FROM Reservations";
         
         ResultSet result = s1.executeQuery(sql);
         boolean f = result.next();
         rev = result.getDouble(1);

      } catch(Exception ex) {System.out.println(ex);};

     return rev;
   }

   private class RevenueFrame extends JFrame
   {
      public RevenueFrame()
      {
          setTitle("InnReservations Revenue");
          Box whole = new Box(BoxLayout.Y_AXIS);
          JButton counts = new JButton("Reservation Counts");
          counts.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                CountsTable t = new CountsTable();
                setVisible(false);
          }});

          JButton occ = new JButton("Days Occupied");
          occ.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                DaysOccTable d = new DaysOccTable();
                setVisible(false);
          }});

          JButton rev = new JButton("Revenue");
          rev.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                RevTable d = new RevTable();
                setVisible(false);
          }});
          
          whole.add(counts);
          whole.add(occ);
          whole.add(rev);
          setSize(450,150);
          add(whole);
          setVisible(true);
      }
   }
   
   private class RevTable extends JFrame
   {
      public RevTable()
      {
          String[] col = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC", "TOTAL"};
          setTitle("Reservation Counts by Month");
          Box whole = new Box(BoxLayout.Y_AXIS);
          Box x = new Box(BoxLayout.X_AXIS);
          Box y = new Box(BoxLayout.Y_AXIS);
          Box tab = new Box(BoxLayout.X_AXIS);
          
          Box buttons = new Box(BoxLayout.X_AXIS);
          JButton counts = new JButton("Reservation Counts");
          counts.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                CountsTable t = new CountsTable();
                setVisible(false);
          }});

          JButton occ = new JButton("Days Occupied");
          occ.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                DaysOccTable d = new DaysOccTable();
                setVisible(false);
          }});

          JButton rev = new JButton("Revenue");
          rev.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                RevTable d = new RevTable();
                setVisible(false);
          }});
          
          buttons.add(counts);
          buttons.add(occ);
          buttons.add(rev);
          whole.add(buttons);

          String[] rms = listRooms();
          String[][] newRms = new String[10][1];
          newRms[0][0] = rms[1];
          newRms[1][0] = rms[2];
          newRms[2][0] = rms[3];
          newRms[3][0] = rms[4];
          newRms[4][0] = rms[5];
          newRms[5][0] = rms[6];
          newRms[6][0] = rms[7];
          newRms[7][0] = rms[8];
          newRms[8][0] = rms[9];
          newRms[9][0] = rms[10];

          String[] row = {"Rooms"};
          JScrollPane sp1 = new JScrollPane(new JTable(newRms, row));
          sp1.setMaximumSize(new Dimension(200, 250));
          tab.add(sp1);
                
          JTable table = new JTable(generateRevTable(), col);
          JScrollPane sp2 = new JScrollPane(table);
          sp2.setMaximumSize(new Dimension(1000, 250));
          tab.add(sp2);

          whole.add(tab);
          setSize(1200, 250);
          add(whole);
          setVisible(true); 
      }
   }

   private class DaysOccTable extends JFrame
   {
      public DaysOccTable()
      {
          String[] col = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC", "TOTAL"};
          setTitle("Reservation Counts by Month");
          Box whole = new Box(BoxLayout.Y_AXIS);
          Box x = new Box(BoxLayout.X_AXIS);
          Box y = new Box(BoxLayout.Y_AXIS);
          Box tab = new Box(BoxLayout.X_AXIS);

          Box buttons = new Box(BoxLayout.X_AXIS);
          JButton counts = new JButton("Reservation Counts");
          counts.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                CountsTable t = new CountsTable();
                setVisible(false);
          }});

          JButton occ = new JButton("Days Occupied");
          occ.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                DaysOccTable d = new DaysOccTable();
                setVisible(false);
          }});

          JButton rev = new JButton("Revenue");
          rev.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                RevTable d = new RevTable();
                setVisible(false);
          }});
          
          buttons.add(counts);
          buttons.add(occ);
          buttons.add(rev);
          whole.add(buttons);
          
          String[] rms = listRooms();
          String[][] newRms = new String[10][1];
          newRms[0][0] = rms[1];
          newRms[1][0] = rms[2];
          newRms[2][0] = rms[3];
          newRms[3][0] = rms[4];
          newRms[4][0] = rms[5];
          newRms[5][0] = rms[6];
          newRms[6][0] = rms[7];
          newRms[7][0] = rms[8];
          newRms[8][0] = rms[9];
          newRms[9][0] = rms[10];

          String[] row = {"Rooms"};
          JScrollPane sp1 = new JScrollPane(new JTable(newRms, row));
          sp1.setMaximumSize(new Dimension(200, 250));
          tab.add(sp1);
                
          JTable table = new JTable(generateOccTable(), col);
          JScrollPane sp2 = new JScrollPane(table);
          sp2.setMaximumSize(new Dimension(800, 250));
          tab.add(sp2);

          whole.add(tab);
          setSize(1000, 250);
          add(whole);
          setVisible(true); 
      }
   }
   
   private class CountsTable extends JFrame
   {
      public CountsTable()
      {
          setTitle("Reservation Counts by Month");
          Box whole = new Box(BoxLayout.Y_AXIS);
          Box x = new Box(BoxLayout.X_AXIS);
          Box y = new Box(BoxLayout.Y_AXIS);
          Box tab = new Box(BoxLayout.X_AXIS);

          Box buttons = new Box(BoxLayout.X_AXIS);
          JButton counts = new JButton("Reservation Counts");
          counts.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                CountsTable t = new CountsTable();
                setVisible(false);
          }});

          JButton occ = new JButton("Days Occupied");
          occ.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                DaysOccTable d = new DaysOccTable();
                setVisible(false);
          }});

          JButton rev = new JButton("Revenue");
          rev.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent a){
                RevTable d = new RevTable();
                setVisible(false);
          }});
          
          buttons.add(counts);
          buttons.add(occ);
          buttons.add(rev);
          whole.add(buttons);
          
          String[] rms = listRooms();
          String[][] newRms = new String[10][1];
          newRms[0][0] = rms[1];
          newRms[1][0] = rms[2];
          newRms[2][0] = rms[3];
          newRms[3][0] = rms[4];
          newRms[4][0] = rms[5];
          newRms[5][0] = rms[6];
          newRms[6][0] = rms[7];
          newRms[7][0] = rms[8];
          newRms[8][0] = rms[9];
          newRms[9][0] = rms[10];

          String[] row = {"Rooms"};
          JScrollPane sp1 = new JScrollPane(new JTable(newRms, row));
          sp1.setMaximumSize(new Dimension(200, 250));
          tab.add(sp1);
                
          String[] col = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC", "TOTAL"};
          JTable table = new JTable(generateCountTable(), col);
          JScrollPane sp2 = new JScrollPane(table);
          sp2.setMaximumSize(new Dimension(800, 250));
          tab.add(sp2);

          whole.add(tab);
          setSize(1000, 250);
          add(whole);
          setVisible(true); 
      }
   }

   public Double[][] generateCountTable()
   {
      Double[][] rev = new Double[10][13]; 
      String[] rms = listRooms();
      double[] tmp;

      for(int i = 1; i < rms.length; i++)
      {
          tmp = resCountMonth(rms[i]);
          rev[i-1][0] = tmp[0];
          rev[i-1][1] = tmp[1];
          rev[i-1][2] = tmp[2];
          rev[i-1][3] = tmp[3];
          rev[i-1][4] = tmp[4];
          rev[i-1][5] = tmp[5];
          rev[i-1][6] = tmp[6];
          rev[i-1][7] = tmp[7];
          rev[i-1][8] = tmp[8];
          rev[i-1][9] = tmp[9];
          rev[i-1][10] = tmp[10];
          rev[i-1][11] = tmp[11];
          rev[i-1][12] = tmp[12];
      } 
      
      return rev;
   }
   
   public double[] resCountMonth(String rm)
   {
      Connection conn = InnDatabase.getConnection();
 
      double[] count = new double[13];

      String sql = "";
      int i = 0;

      try
      {
         Statement s1 = conn.createStatement();
     
         sql += "SELECT COUNT(*)";
         sql += " FROM Reservations r, Rooms rm";
         sql += " WHERE rm.RoomName = '" + rm + "'";
         sql += " AND rm.RoomID = r.Room";
         sql += " AND TO_NUMBER(TO_CHAR(CheckOut, 'YYYY')) = 2010";
         sql += " GROUP BY TO_CHAR(CheckOut, 'MM')";
         sql += " ORDER BY TO_NUMBER(TO_CHAR(CheckOut, 'MM'))";
         
         ResultSet result = s1.executeQuery(sql);
         boolean f = result.next();
         while(f)
         {
              count[i] = result.getDouble(1);
              f = result.next();
              i++;
         }

      } catch(Exception ex) {System.out.println(ex);};
     
     count[12] = reservations(rm);

     return count;
   }

   public Double[][] generateOccTable()
   {
      Double[][] rev = new Double[10][13]; 
      String[] rms = listRooms();
      double[] tmp;

      for(int i = 1; i < rms.length; i++)
      {
          tmp = resOccMonth(rms[i]);
          rev[i-1][0] = tmp[0];
          rev[i-1][1] = tmp[1];
          rev[i-1][2] = tmp[2];
          rev[i-1][3] = tmp[3];
          rev[i-1][4] = tmp[4];
          rev[i-1][5] = tmp[5];
          rev[i-1][6] = tmp[6];
          rev[i-1][7] = tmp[7];
          rev[i-1][8] = tmp[8];
          rev[i-1][9] = tmp[9];
          rev[i-1][10] = tmp[10];
          rev[i-1][11] = tmp[11];
          rev[i-1][12] = tmp[12];
      } 
      
      return rev;
   }
   
   public double[] resOccMonth(String rm)
   {
      Connection conn = InnDatabase.getConnection();
 
      double[] count = new double[13];

      String sql = "";
      int i = 0;

      try
      {
         Statement s1 = conn.createStatement();
     
         sql += "SELECT SUM(r.CheckOut - r.CheckIn)";
         sql += " FROM Reservations r, Rooms rm";
         sql += " WHERE rm.RoomName = '" + rm + "'";
         sql += " AND rm.RoomID = r.Room";
         sql += " AND TO_NUMBER(TO_CHAR(CheckOut, 'YYYY')) = 2010";
         sql += " GROUP BY TO_CHAR(CheckOut, 'MM')";
         sql += " ORDER BY TO_NUMBER(TO_CHAR(CheckOut, 'MM'))";
         
         ResultSet result = s1.executeQuery(sql);
         boolean f = result.next();
         while(f)
         {
              count[i] = result.getDouble(1);
              f = result.next();
              i++;
         }

      } catch(Exception ex) {System.out.println(ex);};
     
     count[12] = occupancy(rm);

     return count;
   }

   public Double[][] generateRevTable()
   {
      Double[][] rev = new Double[10][14]; 
      String[] rms = listRooms();
      double[] tmp;

      for(int i = 1; i < rms.length; i++)
      {
          tmp = resRevMonth(rms[i]);
          rev[i-1][0] = tmp[0];
          rev[i-1][1] = tmp[1];
          rev[i-1][2] = tmp[2];
          rev[i-1][3] = tmp[3];
          rev[i-1][4] = tmp[4];
          rev[i-1][5] = tmp[5];
          rev[i-1][6] = tmp[6];
          rev[i-1][7] = tmp[7];
          rev[i-1][8] = tmp[8];
          rev[i-1][9] = tmp[9];
          rev[i-1][10] = tmp[10];
          rev[i-1][11] = tmp[11];
          rev[i-1][12] = tmp[12];
      } 
      
      return rev;
   }
   
   public double[] resRevMonth(String rm)
   {
      Connection conn = InnDatabase.getConnection();
 
      double[] count = new double[13];

      String sql = "";
      int i = 0;

      try
      {
         Statement s1 = conn.createStatement();
     
         sql += "SELECT SUM(r.Rate * (r.CheckOut - r.CheckIn))";
         sql += " FROM Reservations r, Rooms rm";
         sql += " WHERE rm.RoomName = '" + rm + "'";
         sql += " AND rm.RoomID = r.Room";
         sql += " AND TO_NUMBER(TO_CHAR(CheckOut, 'YYYY')) = 2010";
         sql += " GROUP BY TO_CHAR(CheckOut, 'MM')";
         sql += " ORDER BY TO_NUMBER(TO_CHAR(CheckOut, 'MM'))";
         
         ResultSet result = s1.executeQuery(sql);
         boolean f = result.next();
         while(f)
         {
              count[i] = result.getDouble(1);
              f = result.next();
              i++;
         }

      } catch(Exception ex) {System.out.println(ex);};
     
     count[12] = totalRev(rm);

     return count;
   }

   /**
    * Action listener for Occupancy Overview Button.
   /**
    * Action listener for Occupancy Overview Button.
    */
   private class OccButtonListener implements ActionListener
   {
      /**
       * Action from pressing button.
       * @param e clicking action.
       */
      public void actionPerformed(ActionEvent e)
      {
         tmpStart = null;
         tmpEnd = null;
         OV = new OccOverviewFrame();
      }
   }
   

   /**
    * Action listener for Revenue Button.
    */
   private class RevButtonListener implements ActionListener
   {
      /**
       * Action from pressing button.
       * @param e clicking action.
       */
      public void actionPerformed(ActionEvent e)
      {
         tmpStart = null;
         tmpEnd = null;
         RevenueFrame rev = new RevenueFrame();
      }
   }

   /**
    * Action listener for Reservations button.
    */
   private class ResButtonListener implements ActionListener
   {
      /**
       * Action from pressing button.
       * @param e clicking action.
       */
      public void actionPerformed(ActionEvent e)
      {
         tmpStart = null;
         tmpEnd = null;
         ReservationsFrame res = new ReservationsFrame();
      }
   }

   /**
    * Action listener for Rooms button.
    */
   private class RmsButtonListener implements ActionListener
   {
      /**
       * Action from pressing button.
       * @param e clicking action.
       */
      public void actionPerformed(ActionEvent e)
      {
         tmpStart = null;
         tmpEnd = null;
         RoomsFrame rms = new RoomsFrame();
      }
   }
}
