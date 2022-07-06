import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class ChangePassword {
    static JPanel panel;
    static JFrame frame;
    static JLabel currPassLabel, firstPassLabel, secondPassLabel, success;
    static JPasswordField currPass, firstPass, secondPass;
    static JButton change, cancel;

    public static void changePassGUI(String prevPage, String username, Double balance, String fullname) throws IOException{
        panel = new JPanel();
        frame = new JFrame("Change Password");
        
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        currPassLabel = new JLabel("Current Password:");
        currPassLabel.setBounds(13, 20, 120,25);
        currPassLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        currPassLabel.setForeground(Color.decode("#4B3869"));
        panel.add(currPassLabel);
        
        currPass = new JPasswordField(20);
        currPass.setBounds(140,20,165,25);
        panel.add(currPass);
        
        firstPassLabel = new JLabel("New Password:");
        firstPassLabel.setBounds(33, 45, 100,25);
        firstPassLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        firstPassLabel.setForeground(Color.decode("#4B3869"));
        panel.add(firstPassLabel);
        
        firstPass = new JPasswordField(20);  
        firstPass.setBounds(140,45, 165,25);
        panel.add(firstPass);
        
        secondPassLabel = new JLabel("Confirm Password:");
        secondPassLabel.setBounds(11, 70, 120,25);
        secondPassLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        secondPassLabel.setForeground(Color.decode("#4B3869"));
        panel.add(secondPassLabel);
        
        secondPass = new JPasswordField(20);  
        secondPass.setBounds(140,70, 165,25);
        panel.add(secondPass);
        
        change = new JButton("Change");
        change.setBounds(226, 100, 78, 25);
        change.setFont(new Font("Roboto", Font.BOLD, 12));
        change.setBackground(Color.decode("#CAB8FF"));
        change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        change.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {

               if (firstPass.getText().equals(secondPass.getText()) && firstPass.getText().length() >= 8)
               {
                   try {
                       connectDB.changePW(username, firstPass.getText(), prevPage);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   JOptionPane.showMessageDialog(frame, "Success!");

                   if (prevPage == "authUserHome") {
                       authUserHome.auHomeGUI(username, fullname);
                       frame.dispose();
                   }
                   else if (prevPage == "exPrisonHome") {
                       exPrisonHome.exHomeGUI(username, fullname, balance);
                       frame.dispose();
                   }
                   else if (prevPage == "vendorHomePage") {
                       try {
                           vendorHomePage.vendorHomeGUI(username, balance, fullname);
                       } catch (Exception ex) {
                           ex.printStackTrace();
                       }
                       frame.dispose();
                   }
                   else if (prevPage == "investorHomePage") {
                       investorHomePage.inHomePageGUI(fullname, balance, username);
                       frame.dispose();
                   }
                   else if (prevPage == "LogIn") {
                       try {
                           LogIn.logInGUI();
                       } catch (IOException ex) {
                           ex.printStackTrace();
                       }
                   }
                   else if (prevPage == "thirdPartyHome") {
                       thirdPartyHome.tpHomePageGUI(username, balance, fullname);
                       frame.dispose();
                   }
               }

               else if(!firstPass.getText().equals(secondPass.getText())){
                   JOptionPane.showMessageDialog(frame, "Passwords do not match!");
               }
               else if(firstPass.getText().equals(secondPass.getText()) && firstPass.getText().length() < 8){
                   JOptionPane.showMessageDialog(frame, "Password needs to be 8 or more characters");
               }

           }
       });
        panel.add(change);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(151, 100, 73, 25);
        cancel.setFont(new Font("Roboto", Font.BOLD, 12));
        cancel.setBackground(Color.decode("#CAB8FF"));
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancel.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {

               if (prevPage == "authUserHome") {
                   authUserHome.auHomeGUI(username, fullname);
                   frame.dispose();
               }
               else if (prevPage == "exPrisonHome") {
                   exPrisonHome.exHomeGUI(username, fullname, balance);
                   frame.dispose();
               }
               else if (prevPage == "vendorHomePage") {
                   try {
                       vendorHomePage.vendorHomeGUI(username, balance, fullname);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   frame.dispose();
               }
               else if (prevPage == "investorHomePage") {
                   investorHomePage.inHomePageGUI(fullname, balance, username);
                   frame.dispose();
               }
               else if (prevPage == "LogIn") {
                   try {
                       LogIn.logInGUI();
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
               }
               else if (prevPage == "thirdPartyHome") {
                   thirdPartyHome.tpHomePageGUI(username, balance, fullname);
                   frame.dispose();
               }
           }
       });
        panel.add(cancel);
        
        success = new JLabel(" ");
        success.setBounds(100, 130, 300, 25);
        success.setFont(new Font("Roboto", Font.BOLD, 11));
        success.setForeground(Color.decode("#4B3869"));
        panel.add(success);
        
        
        frame.setVisible(true);
        
    }
    
}
