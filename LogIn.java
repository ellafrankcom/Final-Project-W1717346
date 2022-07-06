import com.example.smtp.sendEmail;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;

public class LogIn {

    static JPanel panel;
    static JFrame frame;
    static JLabel userLabel, passwordLabel, loanApp, success;
    static JTextField userText;
    static JPasswordField passwordText;
    static JButton logInButton;
    public static String user, password;
    static int count = 0;

    public static void logInGUI() throws IOException{
        panel = new JPanel();
        frame = new JFrame("Log in");
        
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);
        
        userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20,80,25);
        userLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        userLabel.setForeground(Color.decode("#4B3869"));
        panel.add(userLabel);
        
        userText = new JTextField(20); //change size??
        userText.setBounds(100,20,165,25);
        panel.add(userText);
        
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50,80,25);
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.decode("#4B3869"));
        panel.add(passwordLabel);
        
        passwordText = new JPasswordField(20);  
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        logInButton = new JButton("Login");
        logInButton.setBounds(100, 95, 80, 25);
        logInButton.setFont(new Font("Roboto", Font.BOLD, 12));
        logInButton.setBackground(Color.decode("#CAB8FF"));
        logInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logInButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   if (!connectDB.checkLogInDB(userText, passwordText, frame)) {
                       count++;
                   }
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               if (count > 3) {
                   JOptionPane.showMessageDialog(frame, "Login attempts exceeded");
                   String emAdd = null;
                   try {
                       emAdd = connectDB.getEmail(userText.getText());
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
                   LocalDateTime now = LocalDateTime.now();
                   String time = now.toString();
                   String subject = "Error, Requires Immediate Attention";
                   String message = "There has been an attempted login to your account at " + time
                           + " if this was you please ignore this message \n \n If this login attempt " +
                           "wasn't you please contact the Micro-Loan team immediately.";
                   sendEmail.sendEmail(emAdd, subject, message);
                   System.exit(0);
               }
           }
       });
        panel.add(logInButton);

        loanApp = new JLabel("Click here to apply for a loan");
        loanApp.setBounds(10, 140, 160, 15);
        loanApp.setForeground(Color.BLUE.darker());
        loanApp.setFont(new Font("Roboto", Font.BOLD, 11));
        loanApp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loanApp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                loanApplication.loanAppGUI();
                frame.dispose();
            }

        });
        panel.add(loanApp);
        
        success = new JLabel(" ");
        success.setBounds(100, 115, 300, 25);
        success.setForeground(Color.decode("#4B3869"));
        success.setFont(new Font("Roboto", Font.BOLD, 12));
        panel.add(success);
                
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(logInButton);
    }
}