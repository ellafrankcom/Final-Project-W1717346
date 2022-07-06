
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class approveAuthUser {
    static JPanel panel;
    static JFrame frame;
    static JComboBox requests;
    static JButton go, approve, deny, back;
    static JLabel toRequest, authLabel, authUser, title, userLabel, userID, nameLabel, userName;
    static String[] rqPris = connectDB.getRequestedPrisonersToView();
    static int count = 0;

    public static void approveGUI(String username, Double balance, String fullname) {
        panel = new JPanel();
        frame = new JFrame("User Approval");

        frame.setSize(350, 245);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        toRequest = new JLabel("Users Requested: ");
        toRequest.setForeground(Color.decode("#4B3869"));
        toRequest.setFont(new Font("Roboto", Font.PLAIN, 14));
        toRequest.setBounds(5, 5, 125,25);
        panel.add(toRequest);

        requests = new JComboBox<>(rqPris);
        requests.setBounds(135, 5, 100,25);
        panel.add(requests);

        go = new JButton("Go");
        go.setBounds(250, 8, 50, 20);
        go.setBackground(Color.decode("#CAB8FF"));
        go.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        go.setFont(new Font("Roboto", Font.PLAIN, 10));
        go.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //Finding out how many AU's are trying to view the selected prisoner
                String selectedUser = requests.getSelectedItem().toString();
                try {
                    count = connectDB.checkPendingViewerCount(selectedUser);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (count == 1) {
                    //get AU
                    String[] pv = new String[0];
                    try {
                        pv = connectDB.getPendingViewers(selectedUser);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    authUser.setText(pv[0]);
                    //get prisoner's username
                    userID.setText(selectedUser);
                    //get prisoner's full name
                    try {
                        userName.setText(connectDB.getPrisonerFullName(selectedUser));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    try {
                        goButton(username, fullname, balance, selectedUser);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }


        });
        panel.add(go);

        authLabel = new JLabel("Authorised User:");
        authLabel.setForeground(Color.decode("#4B3869"));
        authLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        authLabel.setBounds(5, 40, 125,25);
        panel.add(authLabel);

        authUser = new JLabel(""); //get from login
        authUser.setBounds(135, 40, 120, 25);
        authUser.setForeground(Color.decode("#4B3869"));
        authUser.setFont(new Font("Roboto", Font.PLAIN, 14));
        panel.add(authUser);

        title = new JLabel("Ex-Prisoner Details");
        title.setForeground(Color.decode("#4B3869"));
        title.setFont(new Font("Roboto", Font.BOLD, 14));
        title.setBounds(5, 70, 145,25);
        panel.add(title);

        userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.decode("#4B3869"));
        userLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        userLabel.setBounds(5, 100, 125,25);
        panel.add(userLabel);

        userID = new JLabel(""); //get from login
        userID.setBounds(135, 100, 120, 25);
        userID.setForeground(Color.decode("#4B3869"));
        userID.setFont(new Font("Roboto", Font.PLAIN, 14));
        panel.add(userID);

        nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.decode("#4B3869"));
        nameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        nameLabel.setBounds(5, 130, 125,25);
        panel.add(nameLabel);

        userName = new JLabel(""); //get from login
        userName.setBounds(135, 130, 120, 25);
        userName.setForeground(Color.decode("#4B3869"));
        userName.setFont(new Font("Roboto", Font.PLAIN, 14));
        panel.add(userName);

        approve = new JButton("Approve");
        approve.setBounds(245, 170, 75, 20);
        approve.setBackground(Color.decode("#CAB8FF"));
        approve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        approve.setFont(new Font("Roboto", Font.PLAIN, 10));
        approve.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    connectDB.approveViewer(authUser.getText(), userID.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(frame, authUser.getText() + " approved to view " + userID.getText());
                thirdPartyHome.tpHomePageGUI(username, balance, fullname);
                frame.dispose();
            }
        });
        panel.add(approve);

        deny = new JButton("Deny");
        deny.setBounds(165, 170, 75, 20);
        deny.setBackground(Color.decode("#CAB8FF"));
        deny.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deny.setFont(new Font("Roboto", Font.PLAIN, 10));
        deny.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    connectDB.denyViewer(authUser.getText(), userID.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(frame, authUser.getText() + " denied from viewing " + userID.getText());
                thirdPartyHome.tpHomePageGUI(username, balance, fullname);
                frame.dispose();

            }
        });
        panel.add(deny);

        back = new JButton("Back");
        back.setBounds(10, 170, 75, 20);
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


        frame.setVisible(true);

    }

    static JPanel panel2;
    static JFrame frame2;
    static JComboBox requests2;
    static JButton go2, ok2, back2;
    static JLabel multipleStatement2, authUserLabel2, authUserName2;

    public static void goButton(String username, String fullname, Double balance, String selectedUser) throws Exception {

        String[] viewers = connectDB.getPendingViewers(selectedUser);

        panel2 = new JPanel();
        frame2 = new JFrame("User Approval");

        frame2.setSize(325, 185);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
        frame2.add(panel2);
        panel2.setBackground(Color.decode("#E6E6FA"));
        panel2.setLayout(null);

        multipleStatement2 = new JLabel("<html>This user has more than one user requesting access, <br> please select which one to approve<html>");
        multipleStatement2.setForeground(Color.decode("#4B3869"));
        multipleStatement2.setFont(new Font("Roboto", Font.PLAIN, 12));
        multipleStatement2.setBounds(5, 5, 325,35);
        panel2.add(multipleStatement2);

        requests2 = new JComboBox<>(viewers);
        requests2.setBounds(40, 50, 100,20);
        panel2.add(requests2);

        go2 = new JButton("Go");
        go2.setBounds(150, 50, 50, 20);
        go2.setBackground(Color.decode("#CAB8FF"));
        go2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        go2.setFont(new Font("Roboto", Font.PLAIN, 10));
        go2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    authUserName2.setText(connectDB.getPrisonerFullName(requests2.getSelectedItem().toString()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel2.add(go2);

        authUserLabel2 = new JLabel("Authorised User:");
        authUserLabel2.setForeground(Color.decode("#4B3869"));
        authUserLabel2.setFont(new Font("Roboto", Font.PLAIN, 14));
        authUserLabel2.setBounds(5, 75, 125,25);
        panel2.add(authUserLabel2);

        authUserName2 = new JLabel(""); //get from login
        authUserName2.setBounds(135, 75, 120, 25);
        authUserName2.setForeground(Color.decode("#4B3869"));
        authUserName2.setFont(new Font("Roboto", Font.PLAIN, 14));
        panel2.add(authUserName2);

        ok2 = new JButton("OK");
        ok2.setBounds(145, 110, 70, 20);
        ok2.setBackground(Color.decode("#CAB8FF"));
        ok2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ok2.setFont(new Font("Roboto", Font.PLAIN, 10));
        ok2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //get AU full name
                authUser.setText(requests2.getSelectedItem().toString());
                //get prisoner's username
                userID.setText(selectedUser);
                //get prisoner's full name
                try {
                    userName.setText(connectDB.getPrisonerFullName(selectedUser).substring(1));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frame2.dispose();
            }
        });
        panel2.add(ok2);

        back2 = new JButton("Back");
        back2.setBounds(70, 110, 70, 20);
        back2.setBackground(Color.decode("#CAB8FF"));
        back2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back2.setFont(new Font("Roboto", Font.PLAIN, 10));
        back2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();

            }
        });
        panel2.add(back2);

        frame2.setVisible(true);

    }

}
