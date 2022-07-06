import com.example.smtp.sendEmail;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class approveLoanApp {
    static JPanel panel, panel1, panel3;
    static JFrame frame, frame2, frame3;
    static JButton go, approve, deny, back, ok, back2, yes, back3;
    static JLabel title, fullName, name, add, address, dateofBirth, dob, prisonNo, prisonNumb, amountBorrowed,
            borrowed, amountPayback, payback, term, loanTerm, guarantorName, guarName, guarantorDOB,
            guarDOB,  guarantorAdd, guarAdd, message,message2;
    static JComboBox usernames;
    static ArrayList<ArrayList<String>> users;

    public static void approveGUI(String username, Double balance, String funame){
        panel = new JPanel();
        frame = new JFrame("Approve a Loan");

        frame.setSize(400,460);
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

        users = connectDB.loansToApprove();
        usernames = new JComboBox<>(users.get(0).toArray());
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

        fullName = new JLabel("Full Name: ");
        fullName.setBounds(10, 35, 80,25);
        fullName.setFont(new Font("Roboto", Font.BOLD, 12));
        fullName.setForeground(Color.decode("#4B3869"));
        panel.add(fullName);

        name = new JLabel("");
        name.setBounds(100, 36, 80,25);
        name.setFont(new Font("Roboto", Font.PLAIN, 12));
        name.setForeground(Color.decode("#4B3869"));
        panel.add(name);

        add = new JLabel("Address: ");
        add.setBounds(10, 57, 80,25);
        add.setFont(new Font("Roboto", Font.BOLD, 12));
        add.setForeground(Color.decode("#4B3869"));
        panel.add(add);

        address = new JLabel("");
        address.setBounds(100, 57, 190,90);
        address.setFont(new Font("Roboto", Font.PLAIN, 12));
        address.setForeground(Color.decode("#4B3869"));
        panel.add(address);

        dateofBirth = new JLabel("Date of Birth: ");
        dateofBirth.setBounds(10, 145, 100,25);
        dateofBirth.setFont(new Font("Roboto", Font.BOLD, 12));
        dateofBirth.setForeground(Color.decode("#4B3869"));
        panel.add(dateofBirth);

        dob = new JLabel("");
        dob.setBounds(100, 145, 80,25);
        dob.setFont(new Font("Roboto", Font.PLAIN, 12));
        dob.setForeground(Color.decode("#4B3869"));
        panel.add(dob);

        prisonNo = new JLabel("Prison #: ");
        prisonNo.setBounds(10, 168, 80,25);
        prisonNo.setFont(new Font("Roboto", Font.BOLD, 12));
        prisonNo.setForeground(Color.decode("#4B3869"));
        panel.add(prisonNo);

        prisonNumb = new JLabel("");
        prisonNumb.setBounds(100, 168, 80,25);
        prisonNumb.setFont(new Font("Roboto", Font.PLAIN, 12));
        prisonNumb.setForeground(Color.decode("#4B3869"));
        panel.add(prisonNumb);

        amountBorrowed = new JLabel("Loan Amount: ");
        amountBorrowed.setBounds(10, 191, 100,25);
        amountBorrowed.setFont(new Font("Roboto", Font.BOLD, 12));
        amountBorrowed.setForeground(Color.decode("#4B3869"));
        panel.add(amountBorrowed);

        borrowed = new JLabel("");
        borrowed.setBounds(100, 191, 80,25);
        borrowed.setFont(new Font("Roboto", Font.PLAIN, 12));
        borrowed.setForeground(Color.decode("#4B3869"));
        panel.add(borrowed);

        amountPayback = new JLabel("To Payback: ");
        amountPayback.setBounds(10, 214, 90,25);
        amountPayback.setFont(new Font("Roboto", Font.BOLD, 12));
        amountPayback.setForeground(Color.decode("#4B3869"));
        panel.add(amountPayback);

        payback = new JLabel("");
        payback.setBounds(100, 214, 90,25);
        payback.setFont(new Font("Roboto", Font.PLAIN, 12));
        payback.setForeground(Color.decode("#4B3869"));
        panel.add(payback);

        term = new JLabel("Loan Term: ");
        term.setBounds(10, 237, 90,25);
        term.setFont(new Font("Roboto", Font.BOLD, 12));
        term.setForeground(Color.decode("#4B3869"));
        panel.add(term);

        loanTerm = new JLabel("");;
        loanTerm.setBounds(100, 237, 90,25);
        loanTerm.setFont(new Font("Roboto", Font.PLAIN, 12));
        loanTerm.setForeground(Color.decode("#4B3869"));
        panel.add(loanTerm);

        guarantorName = new JLabel("Guarantor: ");
        guarantorName.setBounds(10, 260, 90,25);
        guarantorName.setFont(new Font("Roboto", Font.BOLD, 12));
        guarantorName.setForeground(Color.decode("#4B3869"));
        panel.add(guarantorName);

        guarName = new JLabel("");;
        guarName.setBounds(100, 260, 100,25);
        guarName.setFont(new Font("Roboto", Font.PLAIN, 12));
        guarName.setForeground(Color.decode("#4B3869"));
        panel.add(guarName);

        guarantorDOB = new JLabel("Date of Birth: ");
        guarantorDOB.setBounds(10, 283, 90,25);
        guarantorDOB.setFont(new Font("Roboto", Font.BOLD, 12));
        guarantorDOB.setForeground(Color.decode("#4B3869"));
        panel.add(guarantorDOB);

        guarDOB = new JLabel("");;
        guarDOB.setBounds(100, 283, 100,25);
        guarDOB.setFont(new Font("Roboto", Font.PLAIN, 12));
        guarDOB.setForeground(Color.decode("#4B3869"));
        panel.add(guarDOB);

        guarantorAdd = new JLabel("Guarantor Add: ");
        guarantorAdd.setBounds(10, 306, 90,25);
        guarantorAdd.setFont(new Font("Roboto", Font.BOLD, 12));
        guarantorAdd.setForeground(Color.decode("#4B3869"));
        panel.add(guarantorAdd);

        guarAdd = new JLabel("");;
        guarAdd.setBounds(100, 308, 100,80);
        guarAdd.setFont(new Font("Roboto", Font.PLAIN, 12));
        guarAdd.setForeground(Color.decode("#4B3869"));
        panel.add(guarAdd);

        frame.setVisible(true);

        approve = new JButton("Approve");
        approve.setBounds(300, 385, 75, 25);
        approve.setBackground(Color.decode("#CAB8FF"));
        approve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        approve.setFont(new Font("Roboto", Font.PLAIN, 10));
        approve.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                approveButton(username, balance, funame);
                frame.repaint();
            }
        });
        panel.add(approve);

        deny = new JButton("Deny");
        deny.setBounds(220, 385, 75, 25);
        deny.setBackground(Color.decode("#CAB8FF"));
        deny.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deny.setFont(new Font("Roboto", Font.PLAIN, 10));
        deny.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                denyButton(username, balance, funame);
                frame.repaint();

            }
        });
        panel.add(deny);

        back = new JButton("Back");
        back.setBounds(10, 385, 75, 25);
        back.setBackground(Color.decode("#CAB8FF"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setFont(new Font("Roboto", Font.PLAIN, 10));
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                thirdPartyHome.tpHomePageGUI(username, balance, funame);
                frame.dispose();
            }
        });
        panel.add(back);
    }

    public static void goButton() throws Exception {

        int index = usernames.getSelectedIndex();

        ArrayList<String> nms = users.get(0);
        String nm = nms.get(index);
        nm = nm.replace(" ", "");

        ArrayList<String> ids = users.get(1);
        String id = ids.get(index);

        ArrayList<String> data = connectDB.loanData(nm, id);
        String userOpt = data.get(3);
        if (userOpt == null) {
            userOpt = " ";
        }
        String guarOpt = data.get(15);
        if (guarOpt == null) {
            guarOpt = " ";
        }

        name.setText(data.get(0));
        address.setText("<html>" + data.get(1) + "<br>" + data.get(2) + "<br>" + data.get(4)+ "<br>" + data.get(5) + "</html>");
        dob.setText(data.get(6));
        prisonNumb.setText(data.get(7));
        borrowed.setText(data.get(8));
        loanTerm.setText(data.get(9));
        guarName.setText(data.get(10) + " " + data.get(11));
        guarDOB.setText(data.get(12));
        guarAdd.setText("<html>" + data.get(13) + "<br>" + data.get(14) + "<br>" + data.get(16)+ "<br>" + data.get(17) + "</html>");

        Double borr = Double.parseDouble(data.get(8));
        Double leng = Double.parseDouble(data.get(9));
        Double interest = Double.parseDouble(loanApplication.format.format(borr * Math.pow((1 + (0.05 / 1)), (1 * leng))));
        payback.setText(String.valueOf(interest));

    }

    public static void approveButton(String username, Double balance, String funame) {


        panel3 = new JPanel();
        frame3 = new JFrame();

        frame3.setSize(200,160);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setLocationRelativeTo(null);
        frame3.add(panel3);
        panel3.setBackground(Color.decode("#E6E6FA"));
        panel3.setLayout(null);

        message2 = new JLabel("<html>Your are about to approve " + usernames.getSelectedItem() + ", do you wish to continue?<html>");
        message2.setBounds(7, 10, 175,45);
        message2.setFont(new Font("Roboto", Font.PLAIN, 12));
        message2.setForeground(Color.decode("#4B3869"));
        panel3.add(message2);

        yes = new JButton("Yes");
        yes.setBounds(95, 60, 55, 25);
        yes.setBackground(Color.decode("#CAB8FF"));
        yes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        yes.setFont(new Font("Roboto", Font.PLAIN, 9));
        yes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int index = usernames.getSelectedIndex();
                ArrayList<String> ids = users.get(1);
                String id = ids.get(index);

                String username;
                String str = "0123456789";
                int n = str.length();

                username ="EX";

                for (int i = 1; i <= 5; i++) {
                    username += (str.charAt((int) ((Math.random() * 10) % n)));
                }
                char[] password = connectDB.generatePassword();
                String subject = "*User Log In Credentials*";
                String email = "Please find your login credentials for your MicroLoans profile stated below\n\n" +
                        "Username:\n" + username + "\n\nPassword:\n" + password.toString();
                String emailAddress = null;
                try {
                    emailAddress = connectDB.getEmail(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                sendEmail.sendEmail(emailAddress, subject, email);


                try {
                    connectDB.addUserLogInInfo(username, password.toString(), id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                frame.dispose();
                frame3.dispose();
                thirdPartyHome.tpHomePageGUI(username, balance, funame);
            }
        });
        panel3.add(yes);

        back3 = new JButton("Back");
        back3.setBounds(30, 60, 55, 25);
        back3.setBackground(Color.decode("#CAB8FF"));
        back3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back3.setFont(new Font("Roboto", Font.PLAIN, 9));
        back3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {frame.dispose();}});
        panel3.add(back3);

        frame3.setVisible(true);
    }

    public static void denyButton(String username, Double balance, String funame) {
        panel1 = new JPanel();
        frame2 = new JFrame();

        frame2.setSize(200, 160);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
        frame2.add(panel1);
        panel1.setBackground(Color.decode("#E6E6FA"));
        panel1.setLayout(null);

        message = new JLabel("<html>Your are about to deny " + name.getText() + ", do you wish to continue?<html>");
        message.setBounds(7, 10, 175, 45);
        message.setFont(new Font("Roboto", Font.PLAIN, 12));
        message.setForeground(Color.decode("#4B3869"));
        panel1.add(message);

        ok = new JButton("Yes");
        ok.setBounds(95, 60, 55, 25);
        ok.setBackground(Color.decode("#CAB8FF"));
        ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ok.setFont(new Font("Roboto", Font.PLAIN, 9));
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int index = usernames.getSelectedIndex();
                ArrayList<String> ids = users.get(1);
                String id = ids.get(index);

                connectDB.denyLoan(id);

                String subject = "*Please read*";
                String email = "We regret to inform you that your loan application has been denied, " +
                        "please contact our team on 0117 953 9657 or thirdpartyteam@tpauth.com " +
                        "in order to find out why or appeal your application";
                String emailAddress = null;
                try {
                    emailAddress = connectDB.getEmail(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                sendEmail.sendEmail(emailAddress, subject, email);
                frame2.dispose();
                frame.dispose();
                approveLoanApp.approveGUI(username, balance, funame);
            }
        });
        panel1.add(ok);
        frame2.setVisible(true);

        back2 = new JButton("Back");
        back2.setBounds(30, 60, 55, 25);
        back2.setBackground(Color.decode("#CAB8FF"));
        back2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back2.setFont(new Font("Roboto", Font.PLAIN, 9));
        back2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });
        panel1.add(back2);
    }
}
