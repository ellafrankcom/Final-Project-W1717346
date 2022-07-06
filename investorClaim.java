import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

public class investorClaim {
   static JPanel panel;
   static JFrame frame;
   static JSpinner inputAmount; 
   static JButton claimb, cancel;
   static JLabel claimAmount, pickCard, success, amountCanClaim, claimLabel, cardDetails;
   static double claimTotal = 1000;
   static NumberFormat formatter = NumberFormat.getCurrencyInstance();
   static double start = 1;
   static double min = 1;
   static double max = claimTotal;
   static double step = 1;
   static ArrayList<String> cardAL = new ArrayList<>();
   static String bankName, accNum, accSort;
   static SpinnerNumberModel inputModel = new SpinnerNumberModel(start, min, max, step);

   public static void inClaimGUI(String username, String fullname, Double claim){
       panel = new JPanel();
       frame = new JFrame("Investor: Claim");
       
       frame.setSize(350,250);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.add(panel);
       panel.setBackground(Color.decode("#E6E6FA"));
       panel.setLayout(null);
       
       amountCanClaim = new JLabel("Maximum amount you can claim:");
       amountCanClaim.setBounds(10, 10, 250,25);
       amountCanClaim.setFont(new Font("Roboto", Font.PLAIN, 14));
       amountCanClaim.setForeground(Color.decode("#4B3869"));
       panel.add(amountCanClaim);
       
       String claimMoney = formatter.format(claim);
       claimLabel = new JLabel(claimMoney);
       claimLabel.setBounds(260, 10, 100, 25);
       claimLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
       claimLabel.setForeground(Color.decode("#4B3869"));
       panel.add(claimLabel);
       
       claimAmount = new JLabel("How much would you like to claim?");
       claimAmount.setBounds(10, 45, 250,25);
       claimAmount.setFont(new Font("Roboto", Font.PLAIN, 14));
       claimAmount.setForeground(Color.decode("#4B3869"));
       panel.add(claimAmount);

       inputAmount = new JSpinner();
       inputAmount.setModel(inputModel);
       inputAmount.setBounds(240, 45,85, 25);
       inputAmount.setFont(new Font("Roboto", Font.PLAIN, 14));
       inputAmount.setForeground(Color.decode("#4B3869"));
       panel.add(inputAmount);
       
       pickCard = new JLabel("Card details: ");
       pickCard.setBounds(10, 75, 200,25);
       pickCard.setFont(new Font("Roboto", Font.PLAIN, 14));
       pickCard.setForeground(Color.decode("#4B3869"));
       panel.add(pickCard);

       cardAL = connectDB.getInvestorCardInfo(username);
       accNum = cardAL.get(0);
       accSort = cardAL.get(1);
       bankName = cardAL.get(2);

       cardDetails = new JLabel(bankName + ": " + accSort + " " + accNum);
       cardDetails.setBounds(10, 100, 315, 25);
       cardDetails.setFont(new Font("Roboto", Font.PLAIN, 14));
       cardDetails.setForeground(Color.decode("#4B3869"));
       panel.add(cardDetails);
       
       cancel = new JButton("Cancel");
       cancel.setBounds(170, 145,75, 25);
       cancel.setFont(new Font("Roboto", Font.BOLD, 12));
       cancel.setBackground(Color.decode("#CAB8FF"));
       cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       cancel.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               investorHomePage.inHomePageGUI(fullname, claim, username);
               frame.dispose();
           }
       });
       panel.add(cancel);
       
       success = new JLabel(" ");
       success.setBounds(170, 170,135, 25);
       panel.add(success);
       
       claimb = new JButton("Claim");
       claimb.setBounds(250, 145,75, 25);
       claimb.setFont(new Font("Roboto", Font.BOLD, 12));
       claimb.setBackground(Color.decode("#CAB8FF"));
       claimb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       claimb.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               double value = (double) inputAmount.getValue();
               if (value <= max && value != 0) {
                   double value2 = claim - value;
                   try {
                       authorise.authGUI(username, value2, false, fullname, connectDB.getEmail(username), "investorClaim", 0.0);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   Date dateObject = Date.valueOf(LocalDate.now());
                   try {
                       connectDB.createTransaction(username, value, "CLAIM MONEY", "not yet implemented", dateObject);
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
       panel.add(claimb);
       
       frame.setVisible(true);
       
   }

}
