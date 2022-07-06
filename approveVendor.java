import com.example.smtp.sendEmail;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class approveVendor {
    static JPanel panel;
    static JFrame frame;
    static JButton go, approve, deny, back;
    static JLabel title, nameLabel, vendorName, add, address, phoneNumbLabel, phoneNo, emailAddLabel, emailAdd,
                reasonForLabel, reasonFor, userLabel, userRequested;
    static JComboBox usernames;
    static ArrayList<String> vendorNames = new ArrayList<>(), vendorDetails = new ArrayList<>();


    public static void approveGUI(String username, Double balance, String fullname) throws Exception {
        panel = new JPanel();
        frame = new JFrame("Approve a Vendor");

        frame.setSize(400,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        title = new JLabel("Select an account to process: ");
        title.setBounds(10, 10, 190,25);
        title.setFont(new Font("Roboto", Font.PLAIN, 12));
        title.setForeground(Color.decode("#4B3869"));
        panel.add(title);

        vendorNames = connectDB.getReqVendorNames();
        String[] vNames = new String[vendorNames.size()];
        for (int i = 0; i < vendorNames.size(); i++) {
            vNames[i] = vendorNames.get(i);
        }
        usernames = new JComboBox<>(vNames);
        usernames.setBounds(180, 12, 140, 20);
        usernames.setSelectedIndex(-1);
        panel.add(usernames);

        go = new JButton("Go");
        go.setBounds(325, 12, 50, 20);
        go.setBackground(Color.decode("#CAB8FF"));
        go.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        go.setFont(new Font("Roboto", Font.PLAIN, 10));
        go.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    goButton();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(go);


        nameLabel = new JLabel("Vendor Name: ");
        nameLabel.setBounds(10, 35, 100,25);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 13));
        nameLabel.setForeground(Color.decode("#4B3869"));
        panel.add(nameLabel);

        vendorName = new JLabel("");
        vendorName.setBounds(140, 35, 100,25);
        vendorName.setFont(new Font("Roboto", Font.PLAIN, 13));
        vendorName.setForeground(Color.decode("#4B3869"));
        panel.add(vendorName);

        add = new JLabel("Address: ");
        add.setBounds(10, 60, 80,25);
        add.setFont(new Font("Roboto", Font.BOLD, 13));
        add.setForeground(Color.decode("#4B3869"));
        panel.add(add);

        address = new JLabel("");
        address.setBounds(40, 60, 190,90);
        address.setFont(new Font("Roboto", Font.PLAIN, 13));
        address.setForeground(Color.decode("#4B3869"));
        panel.add(address);

        phoneNumbLabel = new JLabel("Phone Number: ");
        phoneNumbLabel.setBounds(10, 100, 190,90);
        phoneNumbLabel.setFont(new Font("Roboto", Font.BOLD, 13));
        phoneNumbLabel.setForeground(Color.decode("#4B3869"));
        panel.add(phoneNumbLabel);

        phoneNo = new JLabel("");
        phoneNo.setBounds(140, 100, 190,90);
        phoneNo.setFont(new Font("Roboto", Font.PLAIN, 13));
        phoneNo.setForeground(Color.decode("#4B3869"));
        panel.add(phoneNo);

        emailAddLabel = new JLabel("Email Address: ");
        emailAddLabel.setBounds(10, 130, 190,90);
        emailAddLabel.setFont(new Font("Roboto", Font.BOLD, 13));
        emailAddLabel.setForeground(Color.decode("#4B3869"));
        panel.add(emailAddLabel);

        emailAdd = new JLabel("");
        emailAdd.setBounds(140, 130, 190,90);
        emailAdd.setFont(new Font("Roboto", Font.PLAIN, 13));
        emailAdd.setForeground(Color.decode("#4B3869"));
        panel.add(emailAdd);

        reasonForLabel = new JLabel("Reason for request: ");
        reasonForLabel.setBounds(10, 160, 190,90);
        reasonForLabel.setFont(new Font("Roboto", Font.BOLD, 13));
        reasonForLabel.setForeground(Color.decode("#4B3869"));
        panel.add(reasonForLabel);

        reasonFor = new JLabel("");
        reasonFor.setBounds(140, 160, 190,90);
        reasonFor.setFont(new Font("Roboto", Font.PLAIN, 13));
        reasonFor.setForeground(Color.decode("#4B3869"));
        panel.add(reasonFor);

        userLabel = new JLabel("User Requesting:");
        userLabel.setBounds(10, 190, 190,90);
        userLabel.setFont(new Font("Roboto", Font.BOLD, 13));
        userLabel.setForeground(Color.decode("#4B3869"));
        panel.add(userLabel);

        userRequested = new JLabel("");
        userRequested.setBounds(140, 190, 190,90);
        userRequested.setFont(new Font("Roboto", Font.PLAIN, 13));
        userRequested.setForeground(Color.decode("#4B3869"));
        panel.add(userRequested);

        approve = new JButton("Approve");
        approve.setBounds(300, 270, 75, 25);
        approve.setBackground(Color.decode("#CAB8FF"));
        approve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        approve.setFont(new Font("Roboto", Font.PLAIN, 10));
        approve.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    approveButton(usernames.getSelectedItem().toString(), vendorDetails.get(7));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                try {
                    approveVendor.approveGUI(username, balance, fullname);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(approve);

        deny = new JButton("Deny");
        deny.setBounds(220, 270, 75, 25);
        deny.setBackground(Color.decode("#CAB8FF"));
        deny.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deny.setFont(new Font("Roboto", Font.PLAIN, 10));
        deny.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                denyButton(usernames.getSelectedItem().toString(), vendorDetails.get(7));
                frame.dispose();
                try {
                    approveVendor.approveGUI(username, balance, fullname);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(deny);

        back = new JButton("Back");
        back.setBounds(10, 270, 75, 25);
        back.setBackground(Color.decode("#CAB8FF"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setFont(new Font("Roboto", Font.PLAIN, 10));
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                thirdPartyHome.tpHomePageGUI(username, balance, fullname);
                frame.dispose();
            }
        });
        panel.add(back);
       frame.setVisible(true);
    }

    public static void goButton() throws Exception {
        vendorDetails = connectDB.getReqVendorDetails(usernames.getSelectedItem().toString());

        vendorName.setText(vendorDetails.get(0));
        address.setText("<html>" + vendorDetails.get(1) + "<br>" + vendorDetails.get(2) + "<br>" + vendorDetails.get(3) + "<html>");
        phoneNo.setText(vendorDetails.get(4));
        emailAdd.setText(vendorDetails.get(5));
        reasonFor.setText(vendorDetails.get(6));
        userRequested.setText(vendorDetails.get(7));
    }

    public static void denyButton(String vendorName, String vendorReqUser){
        JPanel panel;
        JFrame frame;
        JButton ok, back;
        JLabel message;

        panel = new JPanel();
        frame = new JFrame();

        frame.setSize(200, 160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        message = new JLabel("<html>Your are about to deny " + vendorName + ", do you wish to continue?<html>");
        message.setBounds(7, 10, 175, 45);
        message.setFont(new Font("Roboto", Font.PLAIN, 12));
        message.setForeground(Color.decode("#4B3869"));
        panel.add(message);

        ok = new JButton("Yes");
        ok.setBounds(95, 60, 55, 25);
        ok.setBackground(Color.decode("#CAB8FF"));
        ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ok.setFont(new Font("Roboto", Font.PLAIN, 9));
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    connectDB.denyVendorApp(vendorName);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                String subject = "*Please read*";
                String email = "We regret to inform you that your vendor application has been denied," +
                        "please contact our team on 0117 953 9657 or thirdpartyteam@gmail.com" +
                        "in order to find out why or appeal your application";
                String emailAddress = null;
                try {
                    emailAddress = connectDB.getEmail(vendorReqUser);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                sendEmail.sendEmail(emailAddress, subject, email);
                frame.dispose();
            }
        });
        panel.add(ok);
        frame.setVisible(true);

        back = new JButton("Back");
        back.setBounds(30, 60, 55, 25);
        back.setBackground(Color.decode("#CAB8FF"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setFont(new Font("Roboto", Font.PLAIN, 9));
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(back);
    }


    public static void approveButton(String vendorName, String vendorReqUser) throws Exception {
        ArrayList<String> vendorInformation = connectDB.approveVendorApp(vendorName);
        String vendorEmail = vendorInformation.get(0);
        String vendorUsername = vendorInformation.get(1);
        String pass = vendorInformation.get(2);
        String prisonerName = connectDB.getPrisonerFullName(vendorReqUser);
        String subject = "Please read";
        String email = "You have been approved as a vendor on " + prisonerName + "'s account. Your username is: \n" +
                vendorUsername + "\n\n Your password is:\n" + pass + "\n\n If you have any queries, please contact our " +
                "third party team.";
        String emailAddress = connectDB.getEmail(vendorEmail);
        sendEmail.sendEmail(emailAddress, subject, email);

        //EMAIL SENT TO USER THAT REQUESTED THE VENDOR
        String subject2 = "Please read";
        String email2 = "Your vendor request for " + vendorName + " has been approved. You can now send money to them from" +
                "your account. \n\n If this request wasn't made by you please contact our third party team immediately.";
        String emailAddress2 = connectDB.getEmail(vendorReqUser);
        sendEmail.sendEmail(emailAddress2, subject2, email2);

        JOptionPane.showMessageDialog(frame, "Vendor approved! \nAn email has been sent to the vendor \n" +
                "with details for them to log in.");

    }
}
