import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class authUserHome {
    static JPanel panel;
    static JFrame frame;
    static JButton pickUser, requestMore, changePass, logOut;
    static JLabel nameTitle, name, accountTitle, accountNumber;

    public static void auHomeGUI(String username, String fullname){
        panel = new JPanel();
        frame = new JFrame("AU Home");

        frame.setSize(255,190);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        logOut = new JButton("Logout");
        logOut.setBounds(210, 10, 25, 25);
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

        nameTitle = new JLabel("Name: ");
        nameTitle.setBounds(10, 10, 50,25);
        nameTitle.setFont(new Font("Roboto", Font.PLAIN, 12));
        nameTitle.setForeground(Color.decode("#4B3869"));
        panel.add(nameTitle);

        name = new JLabel(fullname); //database
        name.setBounds(60, 10, 150,25);
        name.setFont(new Font("Roboto", Font.PLAIN, 12));
        name.setForeground(Color.decode("#4B3869"));
        panel.add(name);

        accountTitle = new JLabel("Account No: ");
        accountTitle.setBounds(10, 30, 80,25);
        accountTitle.setFont(new Font("Roboto", Font.PLAIN, 12));
        accountTitle.setForeground(Color.decode("#4B3869"));
        panel.add(accountTitle);

        accountNumber = new JLabel(username); //database
        accountNumber.setBounds(80, 30, 150,25);
        accountNumber.setFont(new Font("Roboto", Font.PLAIN, 12));
        accountNumber.setForeground(Color.decode("#4B3869"));
        panel.add(accountNumber);

        pickUser = new JButton("View Transactions");
        pickUser.setBounds(10, 60,220, 25);
        pickUser.setFont(new Font("Roboto", Font.BOLD, 12));
        pickUser.setBackground(Color.decode("#CAB8FF"));
        pickUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pickUser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    auViewTrans.viewGUI(username, fullname);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });
        panel.add(pickUser);

        requestMore = new JButton("Request Authorisation");
        requestMore.setBounds(10, 90,220, 25);
        requestMore.setFont(new Font("Roboto", Font.BOLD, 12));
        requestMore.setBackground(Color.decode("#CAB8FF"));
        requestMore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        requestMore.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                auRequestMore.requestGUI(fullname, username);
                frame.dispose();
            }
        });
        panel.add(requestMore);

        changePass = new JButton("Change Password");
        changePass.setBounds(10, 120,220, 25);
        changePass.setFont(new Font("Roboto", Font.BOLD, 12));
        changePass.setBackground(Color.decode("#CAB8FF"));
        changePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changePass.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    ChangePassword.changePassGUI("authUserHome", username, 0.0, fullname);
                    frame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        panel.add(changePass);

        frame.setVisible(true);
    }
}
