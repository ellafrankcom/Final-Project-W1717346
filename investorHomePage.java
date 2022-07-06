import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

public class investorHomePage {
   static JPanel panel;
   static JFrame frame;
   static JButton invest, claim, viewTran, changePass, logOut;
   static JLabel accountNum, accountNo, fullName, name, claimTotal, claimTitle, investTotal, investTitle;
   static double claimAmount;
   static double investAmount;
   static ArrayList<String> investInfo = new ArrayList<>();
   static NumberFormat formatter = NumberFormat.getCurrencyInstance();
   
   public static void inHomePageGUI(String fullname, Double balance, String username){
       panel = new JPanel();
       frame = new JFrame("Investor");

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

       investInfo = connectDB.getInvestorInformation(username);
       investAmount = Double.parseDouble(investInfo.get(0));
       claimAmount = Double.parseDouble(investInfo.get(1));

       frame.setSize(250,260);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.add(panel);
       panel.setBackground(Color.decode("#E6E6FA"));
       panel.setLayout(null);
       
       accountNo = new JLabel("Account No:");
       accountNo.setForeground(Color.decode("#4B3869"));
       accountNo.setFont(new Font("Roboto", Font.PLAIN, 14));
       accountNo.setBounds(26, 10, 80,25);
       panel.add(accountNo);

       accountNum = new JLabel(username);
       accountNum.setBounds(110, 10, 80, 25); 
       accountNum.setForeground(Color.decode("#4B3869"));
       accountNum.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(accountNum);
       
       name = new JLabel("Investor:");
       name.setBounds(48, 30, 105, 25); 
       name.setForeground(Color.decode("#4B3869"));
       name.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(name);
       
       fullName = new JLabel(fullname);
       fullName.setBounds(110, 30, 105, 25); 
       fullName.setForeground(Color.decode("#4B3869"));
       fullName.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(fullName);

       investTitle = new JLabel("Total Invested:");
       investTitle.setBounds(9, 50, 105, 25);
       investTitle.setForeground(Color.decode("#4B3869"));
       investTitle.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(investTitle);
       
       String t = formatter.format(investAmount);
       investTotal = new JLabel(t);
       investTotal.setBounds(110, 50, 105, 25);
       investTotal.setForeground(Color.decode("#4B3869"));
       investTotal.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(investTotal);
       
       claimTitle = new JLabel("Total to Claim:");
       claimTitle.setBounds(11, 70, 105, 25);
       claimTitle.setForeground(Color.decode("#4B3869"));
       claimTitle.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(claimTitle);
              
       String s = formatter.format(claimAmount);
       claimTotal = new JLabel(s);
       claimTotal.setBounds(110, 70, 105, 25);
       claimTotal.setForeground(Color.decode("#4B3869"));
       claimTotal.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(claimTotal);

       invest = new JButton("Invest");
       invest.setBounds(20, 110, 200, 25);
       invest.setFont(new Font("Roboto", Font.BOLD, 12));
       invest.setBackground(Color.decode("#CAB8FF"));
       invest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       invest.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               investMoney.investGUI(username, fullname, balance, investAmount, claimAmount);
               frame.dispose();
               
           }
       });
       panel.add(invest);
       
       claim = new JButton("Claim Back");
       claim.setBounds(20, 140, 200, 25);
       claim.setFont(new Font("Roboto", Font.BOLD, 12));
       claim.setBackground(Color.decode("#CAB8FF"));
       claim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       claim.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               investorClaim.inClaimGUI(username, fullname, claimAmount);
               frame.dispose();
           }
       });
       panel.add(claim);

       viewTran = new JButton("View Transactions");
       viewTran.setBounds(20, 170, 200, 25);
       viewTran.setFont(new Font("Roboto", Font.BOLD, 12));
       viewTran.setBackground(Color.decode("#CAB8FF"));
       viewTran.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       viewTran.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   viewTrans.viewGUI(username, balance, fullname, "");
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               frame.dispose();
           }
       });
       panel.add(viewTran);
       
       changePass = new JButton("Change Password");
       changePass.setBounds(20, 230, 200, 25);
       changePass.setFont(new Font("Roboto", Font.BOLD, 12));
       changePass.setBackground(Color.decode("#CAB8FF"));
       changePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       changePass.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   ChangePassword.changePassGUI("investorHomePage", username, balance, fullname);
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
