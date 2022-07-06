import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class authorise {
    static JPanel panel;
    static JFrame frame;
    static JLabel approveTitle, codeDisclose, first, second,
            third, fourth;
    static JTextField firstDigit, secondDigit, thirdDigit, fourthDigit;
    static JButton ok, back;
    static String code;
    static boolean correct;
    static String OTP;
    static int count = 0;

    public static void authGUI(String username, Double balance, boolean add, String fullname, String emailAdd, String prevPage, Double payback) {
        authorise.OTP();
        String subject = "*AUTHORISATION REQUIRED*";
        String message= "To continue with your authorisation " +
                "please enter the following code \n\n " +
                "VERIFICATION CODE: \n" + OTP + "\n\n If this transaction " +
                "wasn't you please contact our team immediately.";
        sendEmail.sendEmail(emailAdd, subject, message);

        panel = new JPanel();
        frame = new JFrame("Authorisation");

        frame.setSize(270, 210);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        approveTitle = new JLabel("Input authorisation code:");
        approveTitle.setBounds(40, 5, 250, 25);
        approveTitle.setFont(new Font("Roboto", Font.BOLD, 14));
        approveTitle.setForeground(Color.decode("#4B3869"));
        panel.add(approveTitle);

        codeDisclose = new JLabel("<html>Please check your email for a 4 digit authorisation code<html>");
        codeDisclose.setBounds(6, 35, 250, 25);
        codeDisclose.setFont(new Font("Roboto", Font.ITALIC, 11));
        codeDisclose.setForeground(Color.decode("#4B3869"));
        panel.add(codeDisclose);

        //prompts user to input 4 digit code
        first = new JLabel("1st:");
        first.setBounds(30, 60, 25, 25);
        first.setFont(new Font("Roboto", Font.BOLD, 13));
        first.setForeground(Color.decode("#4B3869"));
        panel.add(first);

        firstDigit = new JTextField();
        firstDigit.setBounds(30, 80, 25, 25);
        firstDigit.setFont(new Font("Roboto", Font.BOLD, 15));
        firstDigit.setHorizontalAlignment(JTextField.CENTER);
        firstDigit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 39) {
                    secondDigit.requestFocus();
                }
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    firstDigit.setText("");
                }
                switch (e.getKeyChar()) {
                    case KeyEvent.VK_0:
                        secondDigit.requestFocus();
                    case KeyEvent.VK_1:
                        secondDigit.requestFocus();
                    case KeyEvent.VK_2:
                        secondDigit.requestFocus();
                    case KeyEvent.VK_3:
                        secondDigit.requestFocus();
                    case KeyEvent.VK_4:
                        secondDigit.requestFocus();
                    case KeyEvent.VK_5:
                        secondDigit.requestFocus();
                    case KeyEvent.VK_6:
                        secondDigit.requestFocus();
                    case KeyEvent.VK_7:
                        secondDigit.requestFocus();
                    case KeyEvent.VK_8:
                        secondDigit.requestFocus();
                    case KeyEvent.VK_9:
                        secondDigit.requestFocus();
                }
            }
        });
        panel.add(firstDigit);

        second = new JLabel("2nd:");
        second.setBounds(80, 60, 30, 25);
        second.setFont(new Font("Roboto", Font.BOLD, 13));
        second.setForeground(Color.decode("#4B3869"));
        panel.add(second);

        secondDigit = new JTextField();
        secondDigit.setBounds(80, 80, 25, 25);
        secondDigit.setFont(new Font("Roboto", Font.BOLD, 15));
        secondDigit.setHorizontalAlignment(JTextField.CENTER);
        secondDigit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 37) {
                    firstDigit.requestFocusInWindow();
                }
                if (e.getKeyCode() == 39) {
                    thirdDigit.requestFocus();
                }
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    secondDigit.setText("");
                    firstDigit.requestFocus();
                }
                switch (e.getKeyChar()) {
                    case KeyEvent.VK_0:
                        thirdDigit.requestFocus();
                    case KeyEvent.VK_1:
                        thirdDigit.requestFocus();
                    case KeyEvent.VK_2:
                        thirdDigit.requestFocus();
                    case KeyEvent.VK_3:
                        thirdDigit.requestFocus();
                    case KeyEvent.VK_4:
                        thirdDigit.requestFocus();
                    case KeyEvent.VK_5:
                        thirdDigit.requestFocus();
                    case KeyEvent.VK_6:
                        thirdDigit.requestFocus();
                    case KeyEvent.VK_7:
                        thirdDigit.requestFocus();
                    case KeyEvent.VK_8:
                        thirdDigit.requestFocus();
                    case KeyEvent.VK_9:
                        thirdDigit.requestFocus();
                }
            }
        });
        panel.add(secondDigit);

        third = new JLabel("3rd:");
        third.setBounds(130, 60, 30, 25);
        third.setFont(new Font("Roboto", Font.BOLD, 13));
        third.setForeground(Color.decode("#4B3869"));
        panel.add(third);

        thirdDigit = new JTextField();
        thirdDigit.setBounds(130, 80, 25, 25);
        thirdDigit.setFont(new Font("Roboto", Font.BOLD, 15));
        thirdDigit.setHorizontalAlignment(JTextField.CENTER);
        thirdDigit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 37) {secondDigit.requestFocusInWindow();}
                if (e.getKeyCode() == 39) {fourthDigit.requestFocus();}
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    thirdDigit.setText("");
                    secondDigit.requestFocus();
                }
                switch (e.getKeyChar()) {
                    case KeyEvent.VK_0:
                        fourthDigit.requestFocus();
                    case KeyEvent.VK_1:
                        fourthDigit.requestFocus();
                    case KeyEvent.VK_2:
                        fourthDigit.requestFocus();
                    case KeyEvent.VK_3:
                        fourthDigit.requestFocus();
                    case KeyEvent.VK_4:
                        fourthDigit.requestFocus();
                    case KeyEvent.VK_5:
                        fourthDigit.requestFocus();
                    case KeyEvent.VK_6:
                        fourthDigit.requestFocus();
                    case KeyEvent.VK_7:
                        fourthDigit.requestFocus();
                    case KeyEvent.VK_8:
                        fourthDigit.requestFocus();
                    case KeyEvent.VK_9:
                        fourthDigit.requestFocus();
                }
            }
        });
        panel.add(thirdDigit);

        fourth = new JLabel("4th:");
        fourth.setBounds(180, 60, 30, 25);
        fourth.setFont(new Font("Roboto", Font.BOLD, 13));
        fourth.setForeground(Color.decode("#4B3869"));
        panel.add(fourth);

        fourthDigit = new JTextField();
        fourthDigit.setBounds(180, 80, 25, 25);
        fourthDigit.setFont(new Font("Roboto", Font.BOLD, 15));
        fourthDigit.setHorizontalAlignment(JTextField.CENTER);
        fourthDigit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 37) {
                    thirdDigit.requestFocusInWindow();
                }
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    fourthDigit.setText("");
                    thirdDigit.requestFocus();
                }
            }
        });
        panel.add(fourthDigit);

        back = new JButton("Back");
        back.setBounds(55, 120, 60, 23);
        back.setFont(new Font("Roboto", Font.BOLD, 10));
        back.setBackground(Color.decode("#CAB8FF"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(username.contains("EX")){
                    exPrisonHome.exHomeGUI(username, fullname, balance);
                    frame.dispose();
                }
                if(username.contains("IN")){
                    investorHomePage.inHomePageGUI(fullname, balance, username);
                    frame.dispose();
                }
                if(username.contains("VE")){
                    try {
                        vendorHomePage.vendorHomeGUI(username, balance, fullname);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    frame.dispose();
                }
            }
        });
        panel.add(back);

        ok = new JButton("Ok");
        ok.setBounds(120, 120, 60, 23);
        ok.setFont(new Font("Roboto", Font.BOLD, 10));
        ok.setBackground(Color.decode("#CAB8FF"));
        ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    okButton(username, fullname, balance, prevPage, payback);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(ok);

        frame.setVisible(true);
    }

    public static void okButton(String username, String fullname, Double balance, String prevPage, Double payback) throws Exception {
        count += 1;
        code = firstDigit.getText() + secondDigit.getText() + thirdDigit.getText() + fourthDigit.getText();

        if (!code.equals(OTP)) {
            firstDigit.setText("");
            secondDigit.setText("");
            thirdDigit.setText("");
            fourthDigit.setText("");
            codeDisclose.setHorizontalAlignment(codeDisclose.CENTER);
            codeDisclose.setText("<html>Error please try again<html>");
            firstDigit.requestFocus();
        }
        if (code.equals(OTP)) {
            frame.dispose();
            correct = true;
        }
        if (count >= 3) {
            frame.dispose();
            correct = false;
        }

        if(username.contains("EX")){
            if (prevPage == "exFundsGUI"){
                connectDB.updateBalance(balance, username);
            }
            else if (prevPage == "exPaybackGUI") {
                connectDB.updatePayback(payback, username);
            }
            exPrisonHome.exHomeGUI(username, fullname, balance);

            frame.dispose();
        }
        if(username.contains("IN")){
            investorHomePage.inHomePageGUI(fullname, balance, username);
            if (prevPage == "investGUI"){
                connectDB.updateInvestAmount(balance, username);
            }
            else if (prevPage == "investorClaim") {
                connectDB.updateClaimAmount(balance, username);
            }
            frame.dispose();
        }
        if(username.contains("VE")){
            vendorHomePage.vendorHomeGUI(username, balance, fullname);
            frame.dispose();
        }
    }

    public static void OTP(){
        String str = "0123456789";
        int n = str.length();

        OTP="";

        for (int i = 1; i <= 4; i++) {
            OTP += (str.charAt((int) ((Math.random() * 10) % n)));
        }

    }
}
