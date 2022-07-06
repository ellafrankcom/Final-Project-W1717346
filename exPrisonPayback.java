import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.*;

public class exPrisonPayback {
   static JPanel panel;
   static JFrame frame;
   static JSpinner inputAmount; 
   static JButton pay, cancel;
   static JLabel renameLabel, repayBalance, repayLabel, cardLabel, cardDetails;
   static double outstandingBalance = 4000;
   static double start = 1;
   static double min = 1;
   static double max = outstandingBalance;
   static double step = 1;
   static SpinnerNumberModel inputModel = new SpinnerNumberModel(start, min, max, step);
   
   public static void exPaybackGUI(String username, String fullname, Double balance) throws Exception {
       panel = new JPanel();
       frame = new JFrame("Payback Loan");

       frame.setSize(350,235);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.add(panel);
       panel.setBackground(Color.decode("#E6E6FA"));
       panel.setLayout(null);
       
       renameLabel = new JLabel("Outstanding Debt:");
       renameLabel.setBounds(10, 10, 250,25);
       renameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
       renameLabel.setForeground(Color.decode("#4B3869"));
       panel.add(renameLabel);
       
       String L = connectDB.paybackAmount(username);
       repayBalance = new JLabel(L);
       repayBalance.setBounds(260, 10, 100, 25);
       repayBalance.setFont(new Font("Roboto", Font.PLAIN, 14));
       repayBalance.setForeground(Color.decode("#4B3869"));
       panel.add(repayBalance);

       repayLabel = new JLabel("Amount of funds to repay?");
       repayLabel.setBounds(10, 43, 250,25);
       repayLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
       repayLabel.setForeground(Color.decode("#4B3869"));
       panel.add(repayLabel);

       inputAmount = new JSpinner();
       inputAmount.setModel(inputModel);
       inputAmount.setBounds(240, 45,85, 25);
       inputAmount.setFont(new Font("Roboto", Font.PLAIN, 14));
       inputAmount.setForeground(Color.decode("#4B3869"));
       panel.add(inputAmount);
           
       cardLabel = new JLabel("Card details: ");
       cardLabel.setBounds(10, 75, 200,25);
       cardLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
       cardLabel.setForeground(Color.decode("#4B3869"));
       panel.add(cardLabel);

       cardDetails = new JLabel("HSBC: 43-02-32, 54376542");
       cardDetails.setBounds(10, 100, 315, 25);
       cardDetails.setFont(new Font("Roboto", Font.PLAIN, 14));
       cardDetails.setForeground(Color.decode("#4B3869"));
       panel.add(cardDetails);
       
       cancel = new JButton("Cancel");
       cancel.setBounds(140, 145,75, 25);
       cancel.setFont(new Font("Roboto", Font.BOLD, 12));
       cancel.setBackground(Color.decode("#CAB8FF"));
       cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       cancel.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               exPrisonHome.exHomeGUI(username, fullname, balance);
               frame.dispose();
           }
       });
       panel.add(cancel);

       pay = new JButton("Pay Funds");
       pay.setBounds(220, 145,100, 25);
       pay.setFont(new Font("Roboto", Font.BOLD, 12));
       pay.setBackground(Color.decode("#CAB8FF"));
       pay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       pay.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               Date dateObject = Date.valueOf(LocalDate.now());
               Double value = (Double) inputAmount.getValue();
               Double newpayback = Double.parseDouble(L) - value;
               try {
                   authorise.authGUI(username, balance, true,fullname, connectDB.getEmail(username), "exPaybackGUI", newpayback);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }


               try {
                   connectDB.createTransaction(username, value, "LOAN PAYBACK", "INVESTOR", dateObject);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               frame.dispose();
           }

       });
       panel.add(pay);
       
       frame.setVisible(true);
       
   }

}

 
    

