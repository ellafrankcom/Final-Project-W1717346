import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class auRequestMore {
    static JPanel panel;
    static JFrame frame;
    static JButton request, back;
    static JTextField accountNo;
    static JLabel title, accountNumber;
    static String prisonerName;
    static int count = 0;

    public static void requestGUI(String fullname, String username){
        panel = new JPanel();
        frame = new JFrame("Request User");

        frame.setSize(245,130);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        title = new JLabel("Request");
        title.setBounds(50, 5,150,25);
        title.setFont(new Font("Roboto", Font.BOLD, 13));
        title.setForeground(Color.decode("#653C94"));
        panel.add(title);

        accountNumber = new JLabel("Account Number: ");
        accountNumber.setBounds(10, 30, 200,25);
        accountNumber.setFont(new Font("Roboto", Font.PLAIN, 12));
        accountNumber.setForeground(Color.decode("#4B3869"));
        panel.add(accountNumber);

        accountNo = new JTextField();
        accountNo.setBounds(110, 32, 70,20);
        panel.add(accountNo);

        request = new JButton("Request");
        request.setBounds(143, 60,75, 25);
        request.setFont(new Font("Roboto", Font.BOLD, 10));
        request.setBackground(Color.decode("#CAB8FF"));
        request.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        request.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    requestButton(accountNo.getText(), username);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(request);

        back = new JButton("Back");
        back.setBounds(73, 60,65, 25);
        back.setFont(new Font("Roboto", Font.BOLD, 10));
        back.setBackground(Color.decode("#CAB8FF"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                authUserHome.auHomeGUI(username, fullname);
                frame.dispose();
            }
        });
        panel.add(back);


        frame.setVisible(true);

    }
    public static void requestButton(String pUsername, String username) throws Exception {

        prisonerName = connectDB.getPrisonerFullName(pUsername);

        if (accountNo.getText().equals("")) {
            accountNumber.setForeground(Color.RED);
        }
        else if (prisonerName.equals("null null")){
            accountNumber.setForeground(Color.RED);
            JOptionPane.showMessageDialog(frame, "User does not exist.");
        }
        else {
            message(prisonerName, pUsername, username);
        }

    }

    public static void message(String prisonerName, String pUsername, String username){
        JFrame frame;
        JPanel panel;
        JLabel title, message;
        JButton ok, back;

        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(200,175);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        title = new JLabel("Requesting");
        title.setBounds(60, 3, 90,25);
        title.setFont(new Font("Roboto", Font.BOLD, 12));
        title.setForeground(Color.decode("#653C94"));
        panel.add(title);

        message = new JLabel("<html>You've requested " + prisonerName + " is this correct?<html>");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setBounds(5, 35, 170,45);
        message.setFont(new Font("Roboto", Font.PLAIN, 12));
        message.setForeground(Color.decode("#4B3869"));
        panel.add(message);

        ok = new JButton("OK");
        ok.setBounds(95, 95, 50, 25);
        ok.setFont(new Font("Roboto", Font.BOLD, 9));
        ok.setBackground(Color.decode("#CAB8FF"));
        ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
               count++;
               if(count == 1){
                   message.setText("<html>A request has been sent to our team and you will receive an email<html>");
                   String pendingviewers = null;
                   try {
                       pendingviewers = connectDB.addPendingViewer1(pUsername, username, frame);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   try {
                       connectDB.addPendingViewer2(pendingviewers, pUsername, username, frame);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
               }
               else if(count == 2){
                   frame.dispose();
                   accountNo.setText("");

               }
            }
        });
        panel.add(ok);

        back = new JButton("Back");
        back.setBounds(30, 95, 60, 25);
        back.setFont(new Font("Roboto", Font.BOLD, 9));
        back.setBackground(Color.decode("#CAB8FF"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(back);

        frame.setVisible(true);
    }

}
