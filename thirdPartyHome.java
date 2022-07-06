import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.*;
import javax.swing.*;

public class thirdPartyHome {
   static JPanel panel;
   static JFrame frame;
   static JButton approveVendorButton, approveLoan, authViewers, changePass, logOut;
   static JLabel accountNum, accountNo, fullName, name;

   public static void tpHomePageGUI(String username, Double balance, String fullname){
       panel = new JPanel();
       frame = new JFrame("Third Party");
       
       frame.setSize(250,220);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.add(panel);
       panel.setBackground(Color.decode("#E6E6FA"));
       panel.setLayout(null);

       logOut = new JButton("Logout");
       logOut.setBounds(205, 10, 25, 25);
       logOut.setFont(new Font("Roboto", Font.BOLD, 12));
       logOut.setBackground(Color.decode("#CAB8FF"));
       logOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       logOut.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   LogIn.logInGUI();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
               frame.dispose();
           }
       });
       panel.add(logOut);
       
       accountNo = new JLabel("Account No:");
       accountNo.setBounds(22, 10, 80,25);
       accountNo.setFont(new Font("Roboto", Font.PLAIN, 11));
       accountNo.setForeground(Color.decode("#4B3869"));
       panel.add(accountNo);

       accountNum = new JLabel(username);
       accountNum.setBounds(85, 10, 80, 25);
       accountNum.setFont(new Font("Roboto", Font.PLAIN, 11));
       accountNum.setForeground(Color.decode("#4B3869"));
       panel.add(accountNum);
       
       name = new JLabel("Full Name: ");
       name.setBounds(31, 30, 80, 25);
       name.setFont(new Font("Roboto", Font.PLAIN, 11));
       name.setForeground(Color.decode("#4B3869"));
       panel.add(name);
       
       fullName = new JLabel(fullname);
       fullName.setBounds(85, 30, 105, 25);
       fullName.setFont(new Font("Roboto", Font.PLAIN, 11));
       fullName.setForeground(Color.decode("#4B3869"));
       panel.add(fullName);

       approveVendorButton = new JButton("Approve Vendor");
       approveVendorButton.setBounds(20, 55, 200, 25);
       approveVendorButton.setFont(new Font("Roboto", Font.BOLD, 12));
       approveVendorButton.setBackground(Color.decode("#CAB8FF"));
       approveVendorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       approveVendorButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   approveVendor.approveGUI(username, balance, fullname);
                   frame.dispose();
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
       });
       panel.add(approveVendorButton);
       
       approveLoan = new JButton("Approve Loan Application");
       approveLoan.setBounds(20, 85, 200, 25);
       approveLoan.setFont(new Font("Roboto", Font.BOLD, 12));
       approveLoan.setBackground(Color.decode("#CAB8FF"));
       approveLoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       approveLoan.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               approveLoanApp.approveGUI(username, 0.0, fullname);
               frame.dispose();
           }
       });
       panel.add(approveLoan);
       
       authViewers = new JButton("Authorise Viewers");
       authViewers.setBounds(20, 115, 200, 25);
       authViewers.setFont(new Font("Roboto", Font.BOLD, 12));
       authViewers.setBackground(Color.decode("#CAB8FF"));
       authViewers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       authViewers.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               approveAuthUser.approveGUI(username, balance, fullname);
               frame.dispose();
           }
       });
       panel.add(authViewers);
       
       changePass = new JButton("Change Password");
       changePass.setBounds(20, 145, 200, 25);
       changePass.setFont(new Font("Roboto", Font.BOLD, 12));
       changePass.setBackground(Color.decode("#CAB8FF"));
       changePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       changePass.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   ChangePassword.changePassGUI("thirdPartyHome", username, 0.0, fullname);
               } catch (IOException ex) {
                   Logger.getLogger(investorHomePage.class.getName()).log(Level.SEVERE, null, ex);
               }
               frame.dispose();
           
           }
       });
       panel.add(changePass);

       frame.setVisible(true);
   }
    
}
