import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

public class investMoney {
    static JPanel panel;
    static JFrame frame;
    static JSpinner inputAmount; 
    static JButton investBut, cancel;
    static JLabel investAmount, cardLabel, success, amountHaveInvested, investLabel,cardDetails;
    static ArrayList<String> cardAL = new ArrayList<>();
    static String accNum, accSort, bankName;
    static double balance = 4000;
    static double start = 1;
    static double min = 1;
    static double max = balance;
    static double step = 1;
    static SpinnerNumberModel inputModel = new SpinnerNumberModel(start, min, max, step);
    static NumberFormat formatter = NumberFormat.getCurrencyInstance();

        
    public static void investGUI(String username, String fullname, Double balance, Double invest, Double claim){
       panel = new JPanel();
       frame = new JFrame("Investor: Invest");
       
       frame.setSize(350,250);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.add(panel);
       panel.setLayout(null);
       panel.setBackground(Color.decode("#E6E6FA"));
       
       amountHaveInvested = new JLabel("Total amount invested: ");
       amountHaveInvested.setBounds(10, 10, 200,25);
       amountHaveInvested.setFont(new Font("Roboto", Font.PLAIN, 14));
       amountHaveInvested.setForeground(Color.decode("#4B3869"));
       panel.add(amountHaveInvested);
       
       String investMoney = formatter.format(invest);
       investLabel = new JLabel(investMoney);
       investLabel.setBounds(260, 10, 100, 25);
       investLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
       investLabel.setForeground(Color.decode("#4B3869"));
       panel.add(investLabel);  
       
       investAmount = new JLabel("How much would you like to invest?");
       investAmount.setBounds(10, 45, 250,25);
       investAmount.setFont(new Font("Roboto", Font.PLAIN, 14));
       investAmount.setForeground(Color.decode("#4B3869"));
       panel.add(investAmount);
       
       //spinner for user input
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
               investorHomePage.inHomePageGUI(fullname, balance, username);
               frame.dispose(); 
           }
       });
       panel.add(cancel);
       
       success = new JLabel(" ");
       success.setBounds(170, 170,135, 25);
       success.setFont(new Font("Roboto", Font.BOLD, 12));
       success.setForeground(Color.decode("#4B3869"));
       panel.add(success);
       
       investBut = new JButton("Invest");
       investBut.setBounds(250, 145,75, 25);
       investBut.setFont(new Font("Roboto", Font.BOLD, 12));
       investBut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       investBut.setBackground(Color.decode("#CAB8FF"));
       investBut.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               Double change = (Double) inputAmount.getValue();
               Double newInvested = invest + change;
               try {
                   authorise.authGUI(username, newInvested, true, fullname, connectDB.getEmail(username), "investGUI", 0.0);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               Date dateObject = Date.valueOf(LocalDate.now());
               try {
                   connectDB.createTransaction(username, change, "INVEST MONEY", "not yet implemented", dateObject);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               frame.dispose();
           }
       });
       panel.add(investBut);
                   
       frame.setVisible(true);
       
       
    }
}
