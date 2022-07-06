import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class exRequestVendor {
   static JPanel panel, panel2, panel3, panel4, panel5;
   static JFrame frame, frame2, frame3, frame4, frame5;
   static JButton next, cancel, back, ok, yes;
   static JRadioButton business, individual;
   static JLabel isTheVendor,  preTitle, preDisclose, notIncluded1, notIncluded2, Title, Name,
           address,  phone, Email, Re, ifOther, endMessage;
   static JTextField busName, phoneNumber, EmailAdd, other;
   static JTextArea add;
   static boolean busPicked, indPicked, tickBox;
   static JCheckBox tick;
   static JComboBox approvedVend, Reason;
   static ArrayList<String> approvedVendors = new ArrayList<>();
   static String[] reason = {"...","Landlord", "Groceries\\Essentials", "Petrol", "Child Care\\Child Maintenance", "Bills",
            "Other"};
   static String value;
   
   public static void requestGUI(String username,String fullname, Double balance){
       panel = new JPanel();
       frame = new JFrame("Request Vendor");
        
       frame.setSize(270,150);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       panel.setBackground(Color.decode("#E6E6FA"));
       panel.setLayout(null);
       frame.add(panel);
       
       isTheVendor = new JLabel("Is the vendor a...?");
       isTheVendor.setBounds(60, 3, 150,25);
       isTheVendor.setFont(new Font("Roboto", Font.BOLD, 15));
       isTheVendor.setForeground(Color.decode("#653C94"));
       panel.add(isTheVendor);
       
       business = new JRadioButton("Business");
       business.setBounds(25, 40, 100,25);
       business.setFont(new Font("Roboto", Font.PLAIN, 14));
       business.setForeground(Color.decode("#4B3869"));
       business.setBackground(Color.decode("#E6E6FA"));
       business.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               if(business.isSelected()){
                   busPicked = true;
                   indPicked = false;
               }
             }
       });
       panel.add(business);
       
       individual = new JRadioButton("Individual");
       individual.setBounds(130, 40, 100,25);
       individual.setFont(new Font("Roboto", Font.PLAIN, 14));
       individual.setForeground(Color.decode("#4B3869"));
       individual.setBackground(Color.decode("#E6E6FA"));
       individual.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               if(individual.isSelected()){
                   indPicked = true;
                   busPicked = false;
               }
             }
       });
       panel.add(individual);

       ButtonGroup G = new ButtonGroup();
       G.add(business);
       G.add(individual);
            
       cancel = new JButton("Cancel");
       cancel.setBounds(50, 80,75, 25);
       cancel.setFont(new Font("Roboto", Font.BOLD, 12));
       cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       cancel.setBackground(Color.decode("#CAB8FF"));
       cancel.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               exPrisonHome.exHomeGUI(username, fullname, balance);
               frame.dispose();
           }
       });
       panel.add(cancel);
       
       next = new JButton("Next");
       next.setBounds(130, 80,75, 25);
       next.setFont(new Font("Roboto", Font.BOLD, 12));
       next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       next.setBackground(Color.decode("#CAB8FF"));
       next.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               if(busPicked){
                   try {
                       preApprovedBusGUI(username, fullname, balance);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   frame.dispose();
               }
               else if(indPicked){
                   requestBusandIndGUI(username, fullname, balance);
                   frame.dispose();
               }
             }
       });
       panel.add(next);
       
       frame.setVisible(true);
   }
   
   public static void preApprovedBusGUI(String username, String fullname, Double balance) throws Exception {
       panel2 = new JPanel();
       frame2 = new JFrame("Request Vendor: Business");
        
       frame2.setSize(350,240);
       frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame2.setLocationRelativeTo(null);
       panel2.setBackground(Color.decode("#E6E6FA"));
       panel2.setLayout(null);
       frame2.add(panel2);
       
       preTitle = new JLabel("Pre-Approved Businesses:");
       preTitle.setBounds(75, 5, 200,25);
       preTitle.setFont(new Font("Roboto", Font.BOLD, 14));
       preTitle.setForeground(Color.decode("#653C94"));
       panel2.add(preTitle);
       
       preDisclose = new JLabel("<html>Check if the vendor you want to approve is already on our list of businesses:<html>");
       preDisclose.setBounds(15, 35, 300,25);
       preDisclose.setFont(new Font("Roboto", Font.ITALIC, 11));
       preDisclose.setForeground(Color.decode("#4B3869"));
       panel2.add(preDisclose);
       
       Name = new JLabel("Business Name:");
       Name.setBounds(10, 65, 110, 25);
       Name.setFont(new Font("Roboto", Font.PLAIN, 12));
       Name.setForeground(Color.decode("#4B3869"));
       panel2.add(Name);

       //drop-down to pick approved vendor
       approvedVendors = connectDB.getListOfVendors();
       approvedVendors.add("N/A");
       String[] vendList = new String[approvedVendors.size()];
       for (int i = 0; i < approvedVendors.size(); i ++) {
           vendList[i] = approvedVendors.get(i);
       }
       approvedVend = new JComboBox(vendList);
       approvedVend.setBounds(10, 90, 310, 25);
       panel2.add(approvedVend);
       int input = (int) approvedVend.getSelectedIndex();
       value = vendList[input];

       notIncluded1 = new JLabel("<html>If the business you wish to send money to is NOT on the list <html>");
       notIncluded1.setBounds(15, 125, 300,15);
       notIncluded1.setFont(new Font("Roboto", Font.ITALIC, 11));
       notIncluded1.setForeground(Color.decode("#4B3869"));
       panel2.add(notIncluded1);
       
       notIncluded2 = new JLabel("<html>select N/A in the drop down and tick here<html>");
       notIncluded2.setBounds(14, 140, 210,15);
       notIncluded2.setFont(new Font("Roboto", Font.ITALIC, 11));
       notIncluded2.setForeground(Color.decode("#4B3869"));
       panel2.add(notIncluded2);

       tick = new JCheckBox();
       tick.setBounds(230, 140, 20, 15);
       tick.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       tick.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               if(tick.isSelected()){
                   tickBox = true;
               }
               else{
                   tickBox = false;
               }
            }
       });
       panel2.add(tick);
       
       back = new JButton("Back");
       back.setBounds(155, 165,75, 25);
       back.setFont(new Font("Roboto", Font.BOLD, 12));
       back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       back.setBackground(Color.decode("#CAB8FF"));
       back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               //goes to previous GUI
               requestGUI(username, fullname, balance);
               frame2.dispose();
           }
       });
       panel2.add(back);
       
       next = new JButton("Next");
       next.setBounds(235, 165,75, 25);
       next.setFont(new Font("Roboto", Font.BOLD, 12));
       next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       next.setBackground(Color.decode("#CAB8FF"));
       next.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               //gets vendor chosen
               int input = (int) approvedVend.getSelectedIndex();
               value = vendList[input];
               //if the vendor isnt in the list, open new GUI to input details
               if(tickBox && (value == "N/A"|| value == "...")){
                   requestBusandIndGUI(username, fullname, balance);
                   frame2.dispose();
               }
               //highlights red if error occurs
               else if(tickBox && (value != "N/A" || value != "...")){
                   notIncluded1.setForeground(Color.RED);
                   notIncluded2.setForeground(Color.RED);
               }
               //highlights red if error occurs
               else if(input < 0){
                   approvedVend.setBackground(Color.decode("#FA5F55"));
               }
               //if business pre-approved, display confirmation message
               else if(input > 0 && value != "N/A" && !tickBox){
                   approvedVend.setBackground(Color.decode("#D1D1EF"));
                   try {
                       connectDB.requestExistingVendor(username, approvedVend.getSelectedItem().toString());
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   JOptionPane.showMessageDialog(frame2, "Your vendor request has been approved, you can now send money to your requested vendor");
                   frame2.dispose();
                   exPrisonHome.exHomeGUI(username, fullname, balance);
               }
            }
       });
       panel2.add(next);
       
       frame2.setVisible(true);       
   }
   
   public static void requestBusandIndGUI(String username, String fullname, Double balance){
       panel3 = new JPanel();
       frame3 = new JFrame("Request Vendor");
        
       frame3.setSize(335,360);
       frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame3.setLocationRelativeTo(null);
       panel3.setBackground(Color.decode("#E6E6FA"));
       panel3.setLayout(null);
       frame3.add(panel3);
       
       Title = new JLabel("Vendor Details:");
       Title.setBounds(110, 5, 150,25);
       Title.setFont(new Font("Roboto", Font.BOLD, 14));
       Title.setForeground(Color.decode("#653C94"));
       panel3.add(Title);
       
       Name = new JLabel("*Vendor Name:");
       Name.setBounds(10, 30, 110, 25);
       Name.setFont(new Font("Roboto", Font.PLAIN, 13));
       Name.setForeground(Color.decode("#4B3869"));
       panel3.add(Name);
       
       busName = new JTextField();
       busName.setBounds(125, 32, 180, 20);
       panel3.add(busName);

       address = new JLabel("*Address:");
       address.setBounds(10, 55, 110, 25);
       address.setFont(new Font("Roboto", Font.PLAIN, 13));
       address.setForeground(Color.decode("#4B3869"));
       panel3.add(address);

       add = new JTextArea(5, 20);
       add.setBounds(125, 57, 180, 83);
       add.setLineWrap(true);
       add.setWrapStyleWord(true);
       panel3.add(add);

       phone = new JLabel("*Phone Number:");
       phone.setBounds(10, 150, 110, 25);
       phone.setFont(new Font("Roboto", Font.PLAIN, 13));
       phone.setForeground(Color.decode("#4B3869"));
       panel3.add(phone);

       phoneNumber = new JTextField();
       phoneNumber.setBounds(125, 152, 180, 20);
       panel3.add(phoneNumber);

       Email = new JLabel("*Email Address:");
       Email.setBounds(10, 175, 110, 25);
       Email.setFont(new Font("Roboto", Font.PLAIN, 13));
       Email.setForeground(Color.decode("#4B3869"));
       panel3.add(Email);

       EmailAdd = new JTextField();
       EmailAdd.setBounds(125, 177, 180, 20);
       panel3.add(EmailAdd);

       Re = new JLabel("*Reason for requesting this vendor:");
       Re.setBounds(10, 200, 200, 25);
       Re.setFont(new Font("Roboto", Font.PLAIN, 12));
       Re.setForeground(Color.decode("#4B3869"));
       panel3.add(Re);

       Reason = new JComboBox(reason);
       Reason.setBounds(12, 225, 200, 20);
       panel3.add(Reason);

       ifOther = new JLabel("*If other please specify:");
       ifOther.setBounds(10, 250, 140, 25);
       ifOther.setFont(new Font("Roboto", Font.PLAIN, 12));
       ifOther.setForeground(Color.decode("#4B3869"));
       panel3.add(ifOther);

       other = new JTextField();
       other.setBounds(140, 252, 165, 20);
       panel3.add(other);

       back = new JButton("Back");
       back.setBounds(150, 285,75, 25);
       back.setFont(new Font("Roboto", Font.BOLD, 12));
       back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       back.setBackground(Color.decode("#CAB8FF"));
       back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   preApprovedBusGUI(username, fullname, balance);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               frame3.dispose();
           }
       });
       panel3.add(back);

       next = new JButton("Next");
       next.setBounds(230, 285,75, 25);
       next.setFont(new Font("Roboto", Font.BOLD, 12));
       next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       next.setBackground(Color.decode("#CAB8FF"));
       next.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               String formattedAddr = add.getText();
               formattedAddr = formattedAddr.replace("\n", ", ");
               try {
                   connectDB.addNewRequestedIndividualVendor(username, busName.getText(), formattedAddr, phoneNumber.getText(), EmailAdd.getText(), Reason.getSelectedItem().toString());
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               JOptionPane.showMessageDialog(frame3, "Your vendor request has been sent, an email will be sent to you shortly");
               frame3.dispose();
               exPrisonHome.exHomeGUI(username, fullname, balance);
           }
       });
       panel3.add(next);
       
       frame3.setVisible(true);
   }






}

 
