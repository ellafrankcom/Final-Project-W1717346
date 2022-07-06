import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class viewTrans {
    public static void viewGUI(String username, double balance, String fullname, String vendorname) throws Exception {
        JFrame frame;
        JPanel panel;
        JLabel title, userLabel, userName;
        JButton back;

        panel = new JPanel();
        frame = new JFrame("Transactions");

        frame.setSize(500,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        panel.setBackground(Color.decode("#E6E6FA"));
        panel.setLayout(null);

        title = new JLabel("User Transactions");
        title.setBounds(190, 5, 150, 25);
        title.setFont(new Font("Roboto", Font.BOLD, 14));
        title.setForeground(Color.decode("#4B3869"));
        panel.add(title);

        back = new JButton("Back");
        back.setBounds(5, 5, 60, 25);
        back.setFont(new Font("Roboto", Font.BOLD, 10));
        back.setBackground(Color.decode("#CAB8FF"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(username.contains("EX")){
                    exPrisonHome.exHomeGUI(username, fullname, balance);
                    frame.dispose();
                }
                if(username.contains("IN")){
                    investorHomePage.inHomePageGUI(fullname, balance, username);
                    frame.dispose();
                }
                if(username.contains("VE")){
                    try {
                        vendorHomePage.vendorHomeGUI(username, balance, fullname);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    frame.dispose();
                }
            }
        });
        panel.add(back);

        userLabel = new JLabel("User: ");
        userLabel.setBounds(10, 40, 60,25);
        userLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
        userLabel.setForeground(Color.decode("#4B3869"));
        panel.add(userLabel);

        userName = new JLabel(fullname);
        userName.setBounds(55, 40, 110,25);
        userName.setFont(new Font("Roboto", Font.PLAIN, 12));
        userName.setForeground(Color.decode("#4B3869"));
        panel.add(userName);

        if (username.contains("EX") || username.contains("IN"))
        {
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


                    data[i][0] = un;
                    data[i][1] = encryptDecrypt.decrypt(amount);
                    data[i][2] = encryptDecrypt.decrypt(typeOfTrans);
                    data[i][3] = encryptDecrypt.decrypt(destination);
                    data[i][4] = encryptDecrypt.decrypt(date);

                    i++;
                }

                DefaultTableModel model = new DefaultTableModel(data, columns);
                JTable table = new JTable(model);
                table.setShowGrid(true);
                table.setShowVerticalLines(true);
                JScrollPane pane = new JScrollPane(table);
                pane.setBounds(10, 60, 465, 225);
                panel.add(pane);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else if (username.contains("VE"))
        {
            try {
                String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
                String user = "root";
                String password = "";

                Connection con = connectDB.getConnection();

                String query = "SELECT * FROM transactions WHERE destination = '" + vendorname + "' OR userName = '" + username + "'";

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


                    data[i][0] = un;
                    data[i][1] = encryptDecrypt.decrypt(amount);
                    data[i][2] = encryptDecrypt.decrypt(typeOfTrans);
                    data[i][3] = encryptDecrypt.decrypt(destination);
                    data[i][4] = encryptDecrypt.decrypt(date);

                    i++;
                }

                DefaultTableModel model = new DefaultTableModel(data, columns);
                JTable table = new JTable(model);
                table.setShowGrid(true);
                table.setShowVerticalLines(true);
                JScrollPane pane = new JScrollPane(table);
                pane.setBounds(10, 60, 465, 225);
                panel.add(pane);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




        frame.setVisible(true);

    }
}
