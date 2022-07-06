import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class exPrisonFunds {
   static JPanel panel;
   static JFrame frame;
   static JComboBox<String> vendors; 
   static JSpinner inputAmount; 
   static JButton claim, cancel;
   static JLabel vendorLabel, loanLabel, loanBalance, releaseAmount, message;
   static double loanAmount = 5000; //read from database
   static double start = 1;
   static double min = 1;
   static double max = loanAmount;
   static double step = 1;
   static SpinnerNumberModel inputModel = new SpinnerNumberModel(start, min, max, step);
   static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
   static ArrayList<String> vendorString = new ArrayList<>();

   public static void exFundsGUI(Double balance, String fullname, String username) throws Exception {
       panel = new JPanel();
       frame = new JFrame("Release Funds");
       
       frame.setSize(350,235);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.add(panel);
       panel.setBackground(Color.decode("#E6E6FA"));
       panel.setLayout(null);
       
       loanLabel = new JLabel("Loan Balance:");
       loanLabel.setBounds(10, 10, 250,25);
       loanLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
       loanLabel.setForeground(Color.decode("#4B3869"));
       panel.add(loanLabel);

       String L = currencyFormatter.format(balance);
       loanBalance = new JLabel(L);
       loanBalance.setBounds(260, 10, 100, 25);
       loanBalance.setFont(new Font("Roboto", Font.PLAIN, 14));
       loanBalance.setForeground(Color.decode("#4B3869"));
       panel.add(loanBalance);
       
       releaseAmount = new JLabel("Amount of funds to release?");
       releaseAmount.setBounds(10, 43, 250,25);
       releaseAmount.setFont(new Font("Roboto", Font.PLAIN, 14));
       releaseAmount.setForeground(Color.decode("#4B3869"));
       panel.add(releaseAmount);

       inputAmount = new JSpinner();
       inputAmount.setModel(inputModel);
       inputAmount.setBounds(240, 45,85, 25);
       inputAmount.setFont(new Font("Roboto", Font.PLAIN, 14));
       inputAmount.setForeground(Color.decode("#4B3869"));
       panel.add(inputAmount);
           
       vendorLabel = new JLabel("Vendor to receive funds: ");
       vendorLabel.setBounds(10, 75, 200,25);
       vendorLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
       vendorLabel.setForeground(Color.decode("#4B3869"));
       panel.add(vendorLabel);

       vendorString = connectDB.getVendorNames(username);
       Set<String> set = new HashSet<>(vendorString);
       vendorString.clear();
       vendorString.addAll(set);
       String[] cbstring = new String[vendorString.size()];
       for (int i = 0; i < vendorString.size(); i++) {
           cbstring[i] = vendorString.get(i);
       }
       vendors = new JComboBox<>(cbstring);
       vendors.setBounds(10, 100, 315, 25);
       vendors.setFont(new Font("Roboto", Font.PLAIN, 12));
       vendors.setSelectedItem(null);
       panel.add(vendors);
       
       cancel = new JButton("Cancel");
       cancel.setBounds(140, 145,75, 25);
       cancel.setFont(new Font("Roboto", Font.BOLD, 12));
       cancel.setBackground(Color.decode("#CAB8FF"));
       cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       cancel.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               frame.dispose();
               exPrisonHome.exHomeGUI(username, fullname, balance);
           }
       });
       panel.add(cancel);

       message = new JLabel(" ");
       message.setBounds(150, 170,195, 25);
       panel.add(message);
       
       claim = new JButton("Send Funds");
       claim.setBounds(220, 145,100, 25);
       claim.setFont(new Font("Roboto", Font.BOLD, 12));
       claim.setBackground(Color.decode("#CAB8FF"));
       claim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       claim.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               double value = (double) inputAmount.getValue();
               if (value <= max && value != 0 && vendors.getSelectedItem() != null ) {
                   Double newbalance = balance - value;
                   try {
                       authorise.authGUI(username, newbalance, false, fullname, connectDB.getEmail(username), "exFundsGUI", 0.0);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }

                   Date dateObject = Date.valueOf(LocalDate.now());

                   try {
                       connectDB.createTransaction(username, value, "SEND FUNDS", (String) vendors.getSelectedItem(), dateObject);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   try {
                       connectDB.updateVendorBalanceAdd((String) vendors.getSelectedItem(), value);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   frame.dispose();
               }
               else {
                   message.setText("Error, please try again.");
               }
           }
       });
       panel.add(claim);
       frame.setVisible(true);
    }
   }


