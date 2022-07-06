import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.*;
import javax.swing.*;

public class exPrisonHome {
    static NumberFormat formatter = NumberFormat.getCurrencyInstance();

   public static void exHomeGUI(String username, String fullname, Double balance){
       JPanel panel;
       JFrame frame;
       JButton requestFunds, requestVendor, viewTransactions, paybackLoan, changePass, logOut;
       JLabel accountNum, accountNo, fullName, name, loanTitle, loanTotal;
       panel = new JPanel();
       frame = new JFrame("Home Page");
       
       frame.setSize(250,290);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.add(panel);
       panel.setBackground(Color.decode("#E6E6FA"));
       panel.setLayout(null);
       
       accountNo = new JLabel("Account No:");
       accountNo.setForeground(Color.decode("#4B3869"));
       accountNo.setFont(new Font("Roboto", Font.PLAIN, 14));
       accountNo.setBounds(40, 10, 80,25);
       panel.add(accountNo);
            
       accountNum = new JLabel(username);
       accountNum.setBounds(125, 10, 80, 25); 
       accountNum.setForeground(Color.decode("#4B3869"));
       accountNum.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(accountNum);
       
       name = new JLabel("User:");
       name.setBounds(84, 35, 105, 25); 
       name.setForeground(Color.decode("#4B3869"));
       name.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(name);
       
       fullName = new JLabel(fullname);
       fullName.setBounds(125, 35, 105, 25); 
       fullName.setForeground(Color.decode("#4B3869"));
       fullName.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(fullName);
       
             
       loanTitle = new JLabel("Current Balance:");
       loanTitle.setBounds(10, 60, 115, 25);
       loanTitle.setForeground(Color.decode("#4B3869"));
       loanTitle.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(loanTitle);

       String b = formatter.format(balance);
       loanTotal = new JLabel(b);
       loanTotal.setBounds(125, 60, 105, 25);
       loanTotal.setForeground(Color.decode("#4B3869"));
       loanTotal.setFont(new Font("Roboto", Font.PLAIN, 14));
       panel.add(loanTotal);

       requestFunds = new JButton("Send Funds");
       requestFunds.setBounds(20, 90, 200, 25);
       requestFunds.setFont(new Font("Roboto", Font.BOLD, 12));
       requestFunds.setBackground(Color.decode("#CAB8FF"));
       requestFunds.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       requestFunds.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   exPrisonFunds.exFundsGUI(balance, fullname, username);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               frame.dispose();
           }
       });
       panel.add(requestFunds);

       logOut = new JButton("Logout");
       logOut.setBounds(2, 10, 25, 25);
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
       
       requestVendor = new JButton("Request Vendor Approval");
       requestVendor.setBounds(20, 120, 200, 25);
       requestVendor.setFont(new Font("Roboto", Font.BOLD, 12));
       requestVendor.setBackground(Color.decode("#CAB8FF"));
       requestVendor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       requestVendor.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               exRequestVendor.requestGUI(username, fullname, balance);
               frame.dispose();
           }
       });
       panel.add(requestVendor);
       
       viewTransactions = new JButton("View Transactions");
       viewTransactions.setBounds(20, 150, 200, 25);
       viewTransactions.setFont(new Font("Roboto", Font.BOLD, 12));
       viewTransactions.setBackground(Color.decode("#CAB8FF"));
       viewTransactions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       viewTransactions.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   viewTrans.viewGUI(username, balance, fullname, "");
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               frame.dispose();
           }
       });
       panel.add(viewTransactions);
       
       paybackLoan = new JButton("Payback Loan");
       paybackLoan.setBounds(20, 180, 200, 25);
       paybackLoan.setFont(new Font("Roboto", Font.BOLD, 12));
       paybackLoan.setBackground(Color.decode("#CAB8FF"));
       paybackLoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       paybackLoan.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   exPrisonPayback.exPaybackGUI(username, fullname, balance);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               frame.dispose();
           }
       });
       panel.add(paybackLoan);
       
       changePass = new JButton("Change Password");
       changePass.setBounds(20, 210, 200, 25);
       changePass.setFont(new Font("Roboto", Font.BOLD, 12));
       changePass.setBackground(Color.decode("#CAB8FF"));
       changePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       changePass.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   ChangePassword.changePassGUI("exPrisonHome", username, balance, fullname);
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
