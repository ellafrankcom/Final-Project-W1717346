import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.BufferUnderflowException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class auViewTrans {
    static JPanel panel;
    static JFrame frame;
    static JButton view, back;
    static JComboBox<String> accounts;
    static JLabel whichUser, transactions;
    public static void viewGUI(String username, String fullname) throws Exception {
        panel = new JPanel();
        frame = new JFrame("View Transactions");

        frame.setSize(450,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        whichUser = new JLabel("Which user would you like to view?");
        whichUser.setBounds(10, 10, 220,25);
        whichUser.setFont(new Font("Roboto", Font.PLAIN, 12));
        whichUser.setForeground(Color.decode("#4B3869"));
        panel.add(whichUser);

        String usersAttached = connectDB.getAttachedUsers(username);
        if (usersAttached == null) {
            usersAttached = "";
        }
        String[] approvedPrisoners = usersAttached.split(", ");
        String[] approvedPrisonersNames = new String[approvedPrisoners.length];
        for (int i = 0; i < approvedPrisoners.length; i++) {
            approvedPrisonersNames[i] = connectDB.getPrisonerFullName(approvedPrisoners[i]);
        }

        accounts = new JComboBox<>(approvedPrisonersNames);
        accounts.setBounds(210, 10, 115, 22);
        accounts.setFont(new Font("Roboto", Font.PLAIN, 12));
        accounts.setSelectedItem(null);
        panel.add(accounts);

        view = new JButton("View");
        view.setBounds(340, 10,55, 20);
        view.setFont(new Font("Roboto", Font.BOLD, 9));
        view.setBackground(Color.decode("#CAB8FF"));
        view.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        view.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String username = approvedPrisoners[accounts.getSelectedIndex()];
                try {
                    String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
                    String user = "root";
                    String password = "";

                    Connection con = connectDB.getConnection();

                    String query = "SELECT * FROM transactions WHERE userName = '" + username + "'";

                    Statement stm = con.createStatement();
                    ResultSet res = stm.executeQuery(query);

                    String columns[] = {"Username", "Amount", "Type", "Destination", "Date"};
                    String data[][] = new String[10][5];
                    ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();

                    int i = 0;
                    while (res.next()) {
                        ArrayList<String> temp = new ArrayList<>();
                        String un = res.getString("userName");
                        String amount = res.getString("amount");
                        String typeOfTrans = res.getString("typeOfTrans");
                        String destination = res.getString("destination");
                        String date = res.getString("date");


                        if (un.length() > 3){
                            data[i][0] = un;
                            data[i][1] = encryptDecrypt.decrypt(amount);
                            data[i][2] = encryptDecrypt.decrypt(typeOfTrans);
                            data[i][3] = encryptDecrypt.decrypt(destination);
                            data[i][4] = encryptDecrypt.decrypt(date);
                        }

                        i++;
                    }

                    DefaultTableModel model = new DefaultTableModel(data, columns);
                    JTable table = new JTable(model);
                    table.setShowGrid(true);
                    table.setShowVerticalLines(true);
                    JScrollPane pane = new JScrollPane(table);
                    pane.setBounds(10, 60, 415, 120);
                    panel.add(pane);


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(view);

        transactions = new JLabel("Transactions:");
        transactions.setBounds(10, 40, 120,25);
        transactions.setFont(new Font("Roboto", Font.PLAIN, 12));
        transactions.setForeground(Color.decode("#4B3869"));
        panel.add(transactions);

        back = new JButton("Back");
        back.setBounds(365, 185,60, 20);
        back.setFont(new Font("Roboto", Font.BOLD, 9));
        back.setBackground(Color.decode("#CAB8FF"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                authUserHome.auHomeGUI(username, fullname);
                frame.dispose();
            }
        });
        panel.add(back);

        frame.setVisible(true);

    }
}
