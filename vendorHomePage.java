import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.*;

public class vendorHomePage {
     static String bankName, accountNumber, sortCode;

    public static void vendorHomeGUI(String username, Double balance, String fullname) throws Exception {
        JPanel panel;
        JFrame frame;
        JButton withdraw, viewTransactions, changePass, logOut;
        JLabel accountNum, accountNo, availible, funds;
        ArrayList<String> vendorInfo = new ArrayList<>();
        Double vendorfunds;

        panel = new JPanel();
        frame = new JFrame("Home Page");

        frame.setSize(250,205);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        vendorInfo = connectDB.getVendorInfo(username);
        String vendorname = vendorInfo.get(0);

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

        accountNo = new JLabel("Account No:");
        accountNo.setForeground(Color.decode("#4B3869"));
        accountNo.setFont(new Font("Roboto", Font.PLAIN, 14));
        accountNo.setBounds(40, 10, 83,25);
        panel.add(accountNo);

        accountNum = new JLabel(username);
        accountNum.setBounds(125, 10, 80, 25);
        accountNum.setForeground(Color.decode("#4B3869"));
        accountNum.setFont(new Font("Roboto", Font.PLAIN, 14));
        panel.add(accountNum);

        availible = new JLabel("Available:");
        availible.setBounds(58, 40, 105, 25);
        availible.setForeground(Color.decode("#4B3869"));
        availible.setFont(new Font("Roboto", Font.PLAIN, 14));
        panel.add(availible);

        vendorfunds = connectDB.getVendorBalance(username);
        funds = new JLabel(vendorfunds.toString());
        funds.setBounds(125, 40, 105, 25);
        funds.setForeground(Color.decode("#4B3869"));
        funds.setFont(new Font("Roboto", Font.PLAIN, 14));
        panel.add(funds);

        withdraw = new JButton("Withdraw Balance");
        withdraw.setBounds(20, 70, 200, 25);
        withdraw.setFont(new Font("Roboto", Font.BOLD, 12));
        withdraw.setBackground(Color.decode("#CAB8FF"));
        withdraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        withdraw.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    veWithdraw.withdrawGUI(username, fullname, balance);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });
        panel.add(withdraw);

        viewTransactions = new JButton("View Transactions");
        viewTransactions.setBounds(20, 100, 200, 25);
        viewTransactions.setFont(new Font("Roboto", Font.BOLD, 12));
        viewTransactions.setBackground(Color.decode("#CAB8FF"));
        viewTransactions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewTransactions.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    viewTrans.viewGUI(username, balance, fullname, vendorname);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });
        panel.add(viewTransactions);

        changePass = new JButton("Change Password");
        changePass.setBounds(20, 130, 200, 25);
        changePass.setFont(new Font("Roboto", Font.BOLD, 12));
        changePass.setBackground(Color.decode("#CAB8FF"));
        changePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changePass.addActionListener(e -> {
            try {
                ChangePassword.changePassGUI("vendorHomePage", username, balance, fullname);
            } catch (IOException ex) {
                Logger.getLogger(investorHomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.dispose();
        });
        panel.add(changePass);

        frame.setVisible(true);

    }

    public static void firstLogin(String username, Double balance, String fullname){
        JPanel panel;
        JFrame frame;
        JButton confirm, back;
        JLabel title, bName, accountNo, sortC;
        JTextField bankN, accountNumb, sCode;

        panel = new JPanel();
        frame = new JFrame("Bank Details");

        frame.setSize(300,205);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        title = new JLabel("Please input your bank details");
        title.setBounds(25, 5,230,25);
        title.setFont(new Font("Roboto", Font.BOLD, 14));
        title.setForeground(Color.decode("#4B3869"));
        panel.add(title);

        bName = new JLabel("Bank Name:");
        bName.setBounds(10, 35,80,25);
        bName.setFont(new Font("Roboto", Font.PLAIN, 14));
        bName.setForeground(Color.decode("#4B3869"));
        panel.add(bName);

        bankN = new JTextField(20);
        bankN.setBounds(100,35,165,25);
        panel.add(bankN);

        accountNo = new JLabel("Account No:");
        accountNo.setBounds(10, 65,80,25);
        accountNo.setFont(new Font("Roboto", Font.PLAIN, 14));
        accountNo.setForeground(Color.decode("#4B3869"));
        panel.add(accountNo);

        accountNumb = new JTextField(8);
        accountNumb.setBounds(100,65,165,25);
        panel.add(accountNumb);

        sortC = new JLabel("Sort No:");
        sortC.setBounds(10, 95,80,25);
        sortC.setFont(new Font("Roboto", Font.PLAIN, 14));
        sortC.setForeground(Color.decode("#4B3869"));
        panel.add(sortC);

        sCode = new JTextField(6);
        sCode.setBounds(100,95,165,25);
        panel.add(sCode);

        confirm = new JButton("Confirm");
        confirm.setBounds(190, 130, 80, 25);
        confirm.setFont(new Font("Roboto", Font.BOLD, 12));
        confirm.setBackground(Color.decode("#CAB8FF"));
        confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirm.addActionListener(e -> {
            bankName = bankN.getText();
            accountNumber = accountNumb.getText();
            sortCode = sCode.getText();
            if(accountNumber.length() != 8 || sortCode.length() != 6){
                JOptionPane.showMessageDialog(frame, "Error, bank details incorrect. Please try again.");
            }
            else {
                //login
                try {
                    connectDB.updateVendorBankDetails(username, bankName, accountNumber, sortCode);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    vendorHomeGUI(username, balance, fullname);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }

        });
        panel.add(confirm);

        back = new JButton("Back");
        back.setBounds(10, 130, 75, 25);
        back.setFont(new Font("Roboto", Font.BOLD, 12));
        back.setBackground(Color.decode("#CAB8FF"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(e -> {
            try {
                LogIn.logInGUI();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        panel.add(back);

        frame.setVisible(true);

    }
}
