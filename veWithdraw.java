import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

public class veWithdraw {
    static JPanel panel;
    static JFrame frame;
    static JComboBox<String> accDetails;
    static JSpinner inputAmount;
    static JButton claim, cancel;
    static JLabel howMuch, pickAcc, success, withdrawLabel, withdrawTotalLabel;
    static double withdrawTotal = 1000;
    static NumberFormat formatter = NumberFormat.getCurrencyInstance();
    static double start = 1;
    static double min = 1;
    static double max = withdrawTotal;
    static double step = 1;
    static SpinnerNumberModel inputModel = new SpinnerNumberModel(start, min, max, step);
    static ArrayList<String> bankDetails = new ArrayList<>();

    public static void withdrawGUI(String username, String fullname, Double balance) throws Exception {
        panel = new JPanel();
        frame = new JFrame("Vendor: Withdraw");

        frame.setSize(345,215);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        withdrawTotal = connectDB.getVendorBalance(username);

        withdrawLabel = new JLabel("Maximum amount you can withdraw:");
        withdrawLabel.setBounds(10, 10, 250,25);
        withdrawLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        withdrawLabel.setForeground(Color.decode("#4B3869"));
        panel.add(withdrawLabel);

        String withdrawMoney = formatter.format(withdrawTotal);
        withdrawTotalLabel = new JLabel(withdrawMoney);
        withdrawTotalLabel.setBounds(260, 10, 100, 25);
        withdrawTotalLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        withdrawTotalLabel.setForeground(Color.decode("#4B3869"));
        panel.add(withdrawTotalLabel);

        howMuch = new JLabel("How much would you like to withdraw?");
        howMuch.setBounds(10, 45, 250,25);
        howMuch.setFont(new Font("Roboto", Font.PLAIN, 14));
        howMuch.setForeground(Color.decode("#4B3869"));
        panel.add(howMuch);

        inputAmount = new JSpinner();
        inputAmount.setModel(inputModel);
        inputAmount.setBounds(250, 45,75, 25);
        inputAmount.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputAmount.setForeground(Color.decode("#4B3869"));
        panel.add(inputAmount);

        pickAcc = new JLabel("Choose the account details: ");
        pickAcc.setBounds(10, 75, 200,25);
        pickAcc.setFont(new Font("Roboto", Font.PLAIN, 14));
        pickAcc.setForeground(Color.decode("#4B3869"));
        panel.add(pickAcc);

        String details = connectDB.getVendorBankDetails(username);
        bankDetails.add(details);
        String[] arr = new String[bankDetails.size()];
        for (int i = 0; i < bankDetails.size(); i++) {
            arr[i] = bankDetails.get(i);
        }
        accDetails = new JComboBox<>(arr);
        accDetails.setBounds(10, 100, 315, 25);
        accDetails.setFont(new Font("Roboto", Font.PLAIN, 12));
        accDetails.setSelectedItem(null);
        panel.add(accDetails);

        cancel = new JButton("Cancel");
        cancel.setBounds(170, 145,75, 25);
        cancel.setFont(new Font("Roboto", Font.BOLD, 12));
        cancel.setBackground(Color.decode("#CAB8FF"));
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    vendorHomePage.vendorHomeGUI(username, balance, fullname);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });
        panel.add(cancel);

        success = new JLabel(" ");
        success.setBounds(170, 170,135, 25);
        panel.add(success);

        claim = new JButton("Claim");
        claim.setBounds(250, 145,75, 25);
        claim.setFont(new Font("Roboto", Font.BOLD, 12));
        claim.setBackground(Color.decode("#CAB8FF"));
        claim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        claim.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                double value = (double) inputAmount.getValue();
                double newBalance = withdrawTotal - value;

                if (value <= max && value != 0) {
                    try {
                        authorise.authGUI(username, newBalance, false, fullname, connectDB.getEmail(username), "veWithdraw", 0.0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        connectDB.updateVendorBalance(username, newBalance);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    Date dateObject = Date.valueOf(LocalDate.now());
                    try {
                        connectDB.createTransaction(username, value, "WITHDRAW", "Bank", dateObject);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    frame.dispose();

                }
                else if (value > max || value == 0) {
                    success.setText("Error, please try again.");
                }
            }

        });
        panel.add(claim);

        frame.setVisible(true);

    }
}
