import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class connectDB {
    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:finalproject.db";
            Connection conn = DriverManager.getConnection(url);
            return conn;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //ENCRYPTED
    public static void insertUser(String userID, String title, String fName, String sName, Date dob, String mobNum, String phoneNum,
                                  String emailAddress, String houseNum, String address1, String address2,
                                  String city, String postCode, String prisonNum, String borrowAmount, String loanTerm) throws Exception {
        Double loan = Double.parseDouble(loanTerm);
        Double borrow = Double.parseDouble(borrowAmount);
        Double interest = borrow * Math.pow((1 + (0.05 / 1)), (1 * loan));
        Random rnd = new Random();
        int idnum = rnd.nextInt(999999);
        String tempuser = String.valueOf(idnum);
        Connection conn = getConnection();
        Statement stmt = null;

        //Encrypting all the data
        String encTempUser = encryptDecrypt.encrypt(tempuser);
        String encTitle = encryptDecrypt.encrypt(title);
        String encFName = encryptDecrypt.encrypt(fName);
        String encSName = encryptDecrypt.encrypt(sName);
        String encDoB = encryptDecrypt.encrypt(String.valueOf(dob));
        String encMobNum = encryptDecrypt.encrypt(mobNum);
        String encPhoneNum = encryptDecrypt.encrypt(phoneNum);
        String encEmailAddress = encryptDecrypt.encrypt(emailAddress);
        String encHouseNum = encryptDecrypt.encrypt(houseNum);
        String encAddress1 = encryptDecrypt.encrypt(address1);
        String encAddress2 = encryptDecrypt.encrypt(address2);
        String encCity = encryptDecrypt.encrypt(city);
        String encPostCode = encryptDecrypt.encrypt(postCode);
        String encPrisonNum = encryptDecrypt.encrypt(prisonNum);
        String encBorrow = encryptDecrypt.encrypt(borrowAmount);
        String encLoanTerm = encryptDecrypt.encrypt(loanTerm);
        String encInterest = encryptDecrypt.encrypt(String.valueOf(interest));

        String sqlString = ("INSERT INTO users VALUES ('" + userID + "', '" + encTempUser + "', '" + " " + "', '" + encTitle + "', '" + encFName + "', '" + encSName + "', '" + encDoB
                + "', '" + encMobNum + "', '" + encPhoneNum + "', '" + encEmailAddress + "', '" + encHouseNum + "', '" + encAddress1 +
                "', '" + encAddress2 + "', '" + encCity + "', '" + encPostCode + "')");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        sqlString = ("INSERT INTO exPrisoners VALUES ('" + userID + "', '" + " " + "', '" + encSName + "', '" + encPrisonNum + "', '" + encBorrow + "', '" + encLoanTerm + "', '" +
                encInterest + "', '" + encBorrow + "', " + 0 + ", " + null + ", " + null + "' ')");
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }

    //does not need to be encrypted
    public static String genUserID(String dbName) {

        Random rnd = new Random();
        int idnum = rnd.nextInt(999999);
        String userID = String.format("%06d", idnum);

        String query = "SELECT * FROM " + dbName + " WHERE userID = '" + userID + "'";
        Connection conn = getConnection();
        Statement st;
        ResultSet rs;
        String id;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                id = rs.getString("userID");

                if (id == userID) {
                    genUserID(dbName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }

    //ENCRYPTED
    public static void insertGuarantor(String userID, String title, String fName, String sName, Date dob, String mNum, String pNum,
                                       String email, String houseNum, String addr1, String addr2, String city, String postCode,
                                       String relation, String other, String prisonerID) throws Exception {

        String encTitle = encryptDecrypt.encrypt(title);
        String encFName = encryptDecrypt.encrypt(fName);
        String encSName = encryptDecrypt.encrypt(sName);
        String encDoB = encryptDecrypt.encrypt(dob.toString());
        String encMNum = encryptDecrypt.encrypt(mNum);
        String encPNum = encryptDecrypt.encrypt(pNum);
        String encEmail = encryptDecrypt.encrypt(email);
        String encHouseNum = encryptDecrypt.encrypt(houseNum);
        String encAddr1 = encryptDecrypt.encrypt(addr1);
        String encAddr2 = encryptDecrypt.encrypt(addr2);
        String encCity = encryptDecrypt.encrypt(city);
        String encPostCode = encryptDecrypt.encrypt(postCode);
        String encRelation = encryptDecrypt.encrypt(relation);
        String encOther = encryptDecrypt.encrypt(other);


        Connection conn = getConnection();
        Statement stmt = null;
        String sqlString = ("INSERT INTO guarantors VALUES ('" + userID + "', '" + encTitle + "', '" + encFName + "', '" + encSName + "', '" + encDoB
                + "', '" + encMNum + "', '" + encPNum + "', '" + encEmail + "', '" + encHouseNum + "', '" + encAddr1 +
                "', '" + encAddr2 + "', '" + encCity + "', '" + encPostCode + "', '" + encRelation + "', '" + encOther + "', '" + prisonerID + "')");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }

    //DECRYPTS
    public static String paybackAmount(String username) throws Exception {
        Connection conn = getConnection();
        Statement stmt = null;
        String payback = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT toPayBack FROM exPrisoners WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    payback = rs.getString(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        payback = encryptDecrypt.decrypt(payback);
        return payback;
    }

    //DECRYPTS
    public static String getEmail(String userID) throws Exception {
        Connection conn = getConnection();
        Statement stmt = null;
        String email = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT emailAddress FROM users WHERE userID = '" + userID + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    email = " " + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        email = encryptDecrypt.decrypt(email);
        return email;
    }

    //DECRYPTS
    public static ArrayList<String> loanData(String nm, String id) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        ArrayList<String> data = new ArrayList<>();

        String fname = null, sname = null, name, topline = null, dob = null, prisonNumb = null, borrowed = null, loanTerm = null, payBack, guarName, guarDOB, guarAdd, houseNum = null, addr1 = null, addr2 = null, city = null, postCode = null;

        //get house number
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT houseNum FROM users WHERE userID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    houseNum = rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        houseNum = encryptDecrypt.decrypt(houseNum);
        //get addr1
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT address1 FROM users WHERE userID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    addr1 = rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        addr1 = encryptDecrypt.decrypt(addr1);
        //get addr2
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT address2 FROM users WHERE userID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    addr2 = rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        addr2 = encryptDecrypt.decrypt(addr2);
        //get city
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT city FROM users WHERE userID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    city = rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        city = encryptDecrypt.decrypt(city);
        //get postCode
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT postCode FROM users WHERE userID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    postCode = rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        postCode = encryptDecrypt.decrypt(postCode);
        //get fname
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT fname FROM users WHERE userID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    fname = rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        fname = encryptDecrypt.decrypt(fname);
        //get sname
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT sname FROM users WHERE userID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    sname = rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        sname = encryptDecrypt.decrypt(sname);
        //get date of birth
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT dob FROM users WHERE userID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    dob = String.valueOf(rs.getObject(i));
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dob = encryptDecrypt.decrypt(dob);
        //get prison num
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT prisonNumb FROM exPrisoners WHERE ID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    prisonNumb = rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        prisonNumb = encryptDecrypt.decrypt(prisonNumb);
        //get borrow amount
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT borrowAmount FROM exPrisoners WHERE ID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    borrowed = rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        borrowed = encryptDecrypt.decrypt(borrowed);
        //get loan term
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT loanTerm FROM exPrisoners WHERE ID = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    loanTerm =  rs.getString(i);
                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        loanTerm = encryptDecrypt.decrypt(loanTerm);

        topline = houseNum + " " + addr1;
        name = fname + " " +  sname;

        //getting from guarantor table
        //initialising new strings
        String gfname = null, gsname = null, gdob = null, gaddr1 = null, gaddr2 = null, gaddr3 = null, gaddr4 = null, gaddr5 = null;

        //guarantor first name
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT fName FROM guarantors WHERE prisonerUsername = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    gfname = rs.getString(i);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gfname = encryptDecrypt.decrypt(gfname);
        //guarantor surname
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT sName FROM guarantors WHERE prisonerUsername = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    gsname = rs.getString(i);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gsname = encryptDecrypt.decrypt(gsname);
        //guarantor surname
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT dob FROM guarantors WHERE prisonerUsername = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    gdob = String.valueOf(rs.getObject(i));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gdob = encryptDecrypt.decrypt(gdob);
        //guarantor addr1
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT houseNum FROM guarantors WHERE prisonerUsername = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    gaddr1 = rs.getString(i);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gaddr1 = encryptDecrypt.decrypt(gaddr1);
        //guarantor addr2
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT addr1 FROM guarantors WHERE prisonerUsername = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    gaddr2 = rs.getString(i);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gaddr2 = encryptDecrypt.decrypt(gaddr2);
        //guarantor addr3
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT addr2 FROM guarantors WHERE prisonerUsername = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    if (rs.getString(i) != null){
                        gaddr3 = rs.getString(i);
                    }
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gaddr3 = encryptDecrypt.decrypt(gaddr3);
        //guarantor addr4
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT city FROM guarantors WHERE prisonerUsername = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    gaddr4 = rs.getString(i);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gaddr4 = encryptDecrypt.decrypt(gaddr4);
        //guarantor addr5
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT postCode FROM guarantors WHERE prisonerUsername = '" + id + "'");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    gaddr5 = rs.getString(i);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gaddr5 = encryptDecrypt.decrypt(gaddr5);

        data.add(name);
        data.add(houseNum);
        data.add(addr1);
        data.add(addr2);
        data.add(city);
        data.add(postCode);
        data.add(dob);
        data.add(prisonNumb);
        data.add(borrowed);
        data.add(loanTerm);
        data.add(gfname);
        data.add(gsname);
        data.add(gdob);
        data.add(gaddr1);
        data.add(gaddr2);
        data.add(gaddr3);
        data.add(gaddr4);
        data.add(gaddr5);

        return data;
    }

    //ENCRYPTS PASSWORD
    public static void addUserLogInInfo(String username, String password, String userID) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        String sqlString = ("UPDATE users SET userName = '" + username + "' WHERE userID = '" + userID + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        sqlString = ("UPDATE exPrisoners SET username = '" + username + "',  approved = 1 WHERE ID = '" + userID + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String pw = password;
        pw = encryptDecrypt.encrypt(pw);

        sqlString = ("UPDATE users SET passWord = '" + pw + "' WHERE userID = '" + userID + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void denyLoan(String userID) {

        Connection conn = getConnection();
        Statement stmt = null;
        String sqlString = ("UPDATE exPrisoners SET approved = 0 WHERE ID = '" + userID + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        sqlString = ("DELETE FROM exPrisoners WHERE ID = '" + userID + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //DECRYPTS
    public static Boolean checkLogInDB(JTextField userText, JPasswordField passwordText, JFrame frame) throws Exception {

        String em = userText.getText();
        String pw = passwordText.getText();
        String fname = null;
        String fullname = null;
        String balance = null;
        Boolean check = true;
        String userID = null;

        Connection conn = getConnection();
        Statement stmt = null;

        //get first name
        try {
            Boolean login = null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT fName FROM users WHERE userName = '" + em + "'");

            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    fname = rs.getString(i);
                }
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if (!em.contains("VE")){
            fname = encryptDecrypt.decrypt(fname);
        }

        //get second name
        try {
            Boolean login = null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT sName FROM users WHERE userName = '" + em + "'");

            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    String surname = rs.getString(i);
                    if (!em.contains("VE")){
                        surname = encryptDecrypt.decrypt(surname);
                    }
                    fullname = fname + " " + surname;
                }
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        if (em.contains("EX")) {
            //get exprisoner balance
            try {
                Boolean login = null;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT balance FROM exPrisoners WHERE username = '" + em + "'");

                int n = 0;
                while (rs.next()) {
                    int numColumns = rs.getMetaData().getColumnCount();
                    n++;
                    for (int i = 1; i <= numColumns; i++) {
                        balance = rs.getString(i);
                    }
                }
                rs.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            balance = encryptDecrypt.decrypt(balance);
        }

        //get userID
        try {
            Boolean login = null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT userID FROM users WHERE userName = '" + em + "'");

            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    userID = rs.getString(i);
                }
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Boolean login = null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT passWord FROM users WHERE userName = '" + em + "'");

            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    if (encryptDecrypt.decrypt(rs.getString(i)).equals(pw)) {
                        login = true;
                    }
                    else {
                        login = false;
                    }

                    if (login == false) {
                        JOptionPane.showMessageDialog(frame, "Login credentials incorrect");
                        check = false;
                        break;
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Login success!");

                        //login type 1
                        //ex prisoner
                        //EX
                        if (em.contains("EX")) {
                            exPrisonHome.exHomeGUI(em, fullname, Double.valueOf(balance));
                            frame.dispose();
                        }

                        //login type 2
                        //vendor
                        //VE -- FIX ME
                        else if (em.contains("VE")) {
                            if (!checkVendorFirstLogin(em)){
                                vendorHomePage.vendorHomeGUI(em, 0.0, fullname);
                                frame.dispose();
                            }
                            else {
                                vendorHomePage.firstLogin(em, 0.0, fullname);
                                frame.dispose();
                            }
                        }

                        //login type 3
                        //3rd party
                        //TP
                        else if (em.contains("TP")) {
                            thirdPartyHome.tpHomePageGUI(em, null, fullname);
                            frame.dispose();
                        }

                        //login type 4
                        //investor
                        //IN
                        else if (em.contains("IN")) {
                            investorHomePage.inHomePageGUI(fullname, 0.0, em);
                            frame.dispose();
                        }

                        //login type 5
                        //authorised to view
                        //AU
                        else if (em.contains("AU")) {
                            authUserHome.auHomeGUI(em, fullname);
                            frame.dispose();
                        }

                        break;
                    }
                }
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }
            }
        }

        return check;

    }

    //ENCRYPTS
    public static void updateBalance(Double balance, String username) throws Exception {

        String encBalance = encryptDecrypt.encrypt(String.valueOf(balance));
        Connection conn = getConnection();
        Statement stmt = null;
        String sqlString = ("UPDATE exPrisoners SET balance = '" + encBalance + "' WHERE username = '" + username + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //ENCRYPTS
    public static void changePW(String username, String newPW, String acctType) throws Exception {

        String encNewPW = encryptDecrypt.encrypt(newPW);
        Connection conn = getConnection();
        Statement stmt = null;
        String sqlString = ("UPDATE users SET passWord = '" + encNewPW + "' WHERE userName = '" + username + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //does not need encryption/decryption
    public static ArrayList<String> getInvestorInformation(String username) {
        Connection conn = getConnection();
        Statement stmt = null;
        String invest = null;
        String claim = null;
        ArrayList<String> investInfo = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT totalInvested FROM investors WHERE userName = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    invest = " " + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT claimBalance FROM investors WHERE userName = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    claim = " " + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        investInfo.add(invest);
        investInfo.add(claim);

        return investInfo;

    }

    //does not need encryption/decryption
    public static void updateInvestAmount(Double invested, String username) {


        Connection conn = getConnection();
        Statement stmt = null;
        String sqlString = ("UPDATE investors SET totalInvested = " + invested + " WHERE userName = '" + username + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //does not need encryption/decryption
    public static ArrayList<String> getInvestorCardInfo(String username) {
        Connection conn = getConnection();
        Statement stmt = null;
        String bankAccNum = null;
        String bankAccSort = null;
        String bankName = null;
        ArrayList<String> bankingInformation = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bankAccNo FROM investors WHERE userName = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    bankAccNum = " " + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bankAccSortNo FROM investors WHERE userName = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    bankAccSort = " " + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bankName FROM investors WHERE userName = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    bankName = " " + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        bankingInformation.add(bankAccNum);
        bankingInformation.add(bankAccSort);
        bankingInformation.add(bankName);

        return bankingInformation;

    }

    //does not need encryption/decryption
    public static void updateClaimAmount(Double claimed, String username) {


        Connection conn = getConnection();
        Statement stmt = null;
        String sqlString = ("UPDATE investors SET claimBalance = " + claimed + " WHERE userName = '" + username + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //ENCRYPTS
    public static void createTransaction(String userName, Double amount, String typeOfTrans, String destination, Date date) throws Exception {

        String encAmount = encryptDecrypt.encrypt(String.valueOf(amount));
        String encTypeOfTrans = encryptDecrypt.encrypt(typeOfTrans);
        String encDestination = encryptDecrypt.encrypt(destination);
        String encDate = encryptDecrypt.encrypt(String.valueOf(date));

        Connection conn = getConnection();
        Statement stmt = null;
        String sqlString = ("INSERT INTO transactions VALUES ('" + userName + "', '" + encAmount + "', '" + encTypeOfTrans + "', '" + encDestination + "', '" + encDate + "')");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }

    //DECRYPTS
    public static ArrayList<ArrayList<String>> loansToApprove() {

        Connection conn = getConnection();
        Statement stmt = null;
        ArrayList<String> snames = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID FROM exPrisoners WHERE approved = 0 OR approved = null");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;

                for (int i = 1; i <= numColumns; i++) {
                    id.add((rs.getString(i)));

                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT sName FROM exPrisoners WHERE approved = 0 OR approved = null");
            int n = 0;

            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;

                for (int i = 1; i <= numColumns; i++) {
                    snames.add(encryptDecrypt.decrypt(rs.getString(i)));

                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        data.add(snames);
        data.add(id);
        return data;
    }

    //ENCRYPTS
    public static void updatePayback(Double payback, String username) throws Exception {

        String encPayback = encryptDecrypt.encrypt(String.valueOf(payback));
        Connection conn = getConnection();
        Statement stmt = null;
        String sqlString = ("UPDATE exPrisoners SET toPayback = '" + encPayback + "' WHERE username = '" + username + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static DefaultTableModel getTableData(DefaultTableModel model, String username) {

        Connection conn = getConnection();
        Statement stmt = null;
        String amount = null;
        String typeOfTrans = null;
        String destination = null;
        String date = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transactions WHERE userName = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    amount = rs.getString("amount");
                    typeOfTrans = rs.getString("typeOfTrans");
                    destination = rs.getString("destination");
                    date = rs.getString("date");
                    model.addRow(new Object[]{username, amount, typeOfTrans, destination, date});
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return model;
    }

    //DECRYPTS
    public static String getPrisonerFullName(String username) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        String fullName = null;
        String fName = null;
        String sName = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT fName FROM users WHERE userName = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    fName = rs.getString(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        fName = encryptDecrypt.decrypt(fName);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT sName FROM users WHERE userName = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    sName = rs.getString(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        sName = encryptDecrypt.decrypt(sName);

        fullName = fName + " " + sName;
        return fullName;
    }

    //DECRYPTS
    public static String addPendingViewer1(String pUsername, String auUsername, JFrame frame) throws Exception {

        String prisonersPendingViewers = null;
        Connection conn = getConnection();
        Statement stmt = null;

        //get prisonerpendingviewers string
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT pendingViewers FROM exPrisoners WHERE username = '" + pUsername + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    prisonersPendingViewers = rs.getString(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        prisonersPendingViewers = encryptDecrypt.decrypt(prisonersPendingViewers);
        return prisonersPendingViewers;
    }

    //ENCRYPTS
    public static void addPendingViewer2(String pendingviewers, String pUsername, String auUsername, JFrame frame) throws Exception {
        String prisonersPendingViewers = pendingviewers;
        Connection conn = getConnection();
        Statement stmt = null;

        if (prisonersPendingViewers == null) {
            prisonersPendingViewers = "";
        }

        if (prisonersPendingViewers.contains(auUsername)) {
            JOptionPane.showMessageDialog(frame, "Already awaiting approval!");
        }
        else {
            if (prisonersPendingViewers == "") {
                prisonersPendingViewers = auUsername;
                prisonersPendingViewers = encryptDecrypt.encrypt(prisonersPendingViewers);
                String sqlString = ("UPDATE exPrisoners SET pendingViewers = '" + prisonersPendingViewers + "' WHERE username = '" + pUsername + "';");

                try {
                    conn.setAutoCommit(false);
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sqlString);
                    stmt.close();
                    conn.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                prisonersPendingViewers = prisonersPendingViewers + ", " + auUsername;
                prisonersPendingViewers = encryptDecrypt.encrypt(prisonersPendingViewers);
                String sqlString = ("UPDATE exPrisoners SET pendingViewers = '" + prisonersPendingViewers + "' WHERE username = '" + pUsername + "';");

                try {
                    conn.setAutoCommit(false);
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sqlString);
                    stmt.close();
                    conn.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //does not need encryption/decryption
    public static String getAttachedUsers(String username) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        String attachedUsers = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usersAttached FROM authViewer WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    attachedUsers = encryptDecrypt.decrypt(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return attachedUsers;

    }


    public static ArrayList<String> getVendorInfo(String username) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        String vendor = null;
        ArrayList<String> vendorInfo = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM vendors WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    vendor = rs.getString(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        vendorInfo.add(vendor);

        return vendorInfo;

    }

    public static ArrayList<String> getVendorNames(String username) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        String usersAttached = null;
        ArrayList<String> vendorInfo = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vendors");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    usersAttached = rs.getString("usersAttached");

                    if (usersAttached.contains(username)) {
                        vendorInfo.add(rs.getString("name"));
                    }
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }


        return vendorInfo;
    }

    //DECRYPTS
    public static Double getVendorBalance(String username) throws Exception {
        Connection conn = getConnection();
        Statement stmt = null;
        Double balance = 0.0;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT balance FROM vendors WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    if (!rs.getString(i).equals("0")) {
                        balance = Double.parseDouble(encryptDecrypt.decrypt(rs.getString(i)));
                    }
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return balance;
    }

    //DECRYPTS AND ENCRYPTS
    public static void updateVendorBalanceAdd(String vendorname, Double trans) throws Exception {
        Connection conn = getConnection();
        Statement stmt = null;
        String balance = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT balance FROM vendors WHERE name = '" + vendorname + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    if (!rs.getString(i).equals("0")){
                        balance = encryptDecrypt.decrypt(rs.getString(i));
                    }
                    else {
                        balance = "0";
                    }
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        balance = balance + trans;
        balance = encryptDecrypt.encrypt(balance);

        String sqlString = ("UPDATE vendors SET balance = '" + balance + "' WHERE name = '" + vendorname + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //ENCRYPTS
    public static void updateVendorBalance(String username, Double balance) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;

        String encBalance = encryptDecrypt.encrypt(String.valueOf(balance));
        String sqlString = ("UPDATE vendors SET balance = '" + encBalance + "' WHERE username = '" + username + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //DECRYPTS
    public static String getVendorBankDetails(String username) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        String bankAccNo = null;
        String bankAccSortCode = null;
        String bankAccName = null;
        String fullDetails = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bankAccNo FROM vendors WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    bankAccNo = encryptDecrypt.decrypt(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bankAccSortNo FROM vendors WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    bankAccSortCode = encryptDecrypt.decrypt(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bankName FROM vendors WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    bankAccName = encryptDecrypt.decrypt(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        fullDetails = bankAccNo + " " + bankAccSortCode + " " + bankAccName;
        return fullDetails;

    }

    //DECRYPTS
    public static ArrayList<String> getReqVendorNames() throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        ArrayList<String> vendorNames = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM requestedVendors");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    vendorNames.add(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return vendorNames;
    }

    //DECRYPTS
    public static ArrayList<String> getReqVendorDetails(String vName) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        ArrayList<String> vendorDetails = new ArrayList<>();
        String vendAddr = null;
        vendorDetails.add(vName);
        String encVName = encryptDecrypt.encrypt(vName);

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT address FROM requestedVendors WHERE name = '" + vName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    vendAddr = encryptDecrypt.decrypt(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        //splitting address to feed it into the arraylist
        String[] add = vendAddr.split(", ");
        for (int i = 0; i < add.length; i++) {
            vendorDetails.add(add[i]);
        }

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT phoneNo FROM requestedVendors WHERE name = '" + vName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    vendorDetails.add(encryptDecrypt.decrypt(rs.getString(i)));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT emailAdd FROM requestedVendors WHERE name = '" + vName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    vendorDetails.add(encryptDecrypt.decrypt(rs.getString(i)));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT reason FROM requestedVendors WHERE name = '" + vName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    vendorDetails.add(encryptDecrypt.decrypt(rs.getString(i)));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT userRequested FROM requestedVendors WHERE name = '" + vName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    vendorDetails.add(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return vendorDetails;
    }

    //ENCRYPTS
    public static void denyVendorApp(String vendorName) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;

        String encVName = encryptDecrypt.encrypt(vendorName);

        String sqlString = ("DELETE FROM requestedVendors WHERE name = '" + encVName + "'");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        sqlString = ("DELETE FROM users WHERE fName = '" + encVName + "'");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> approveVendorApp(String vendorName) throws Exception {

        Connection conn = getConnection();
        ArrayList<String> vendorEmailandUserName = new ArrayList<>();
        Statement stmt = null;
        String idNum = null;
        String name = vendorName;
        String address = null;
        String phoneNo = null;
        String emailAdd = null;
        String reason = null;
        String busOrInd = "1";
        String userRequested = null;
        String username;
        String str = "0123456789";
        int p = str.length();

        username ="VE";

        for (int i = 1; i <= 5; i++) {
            username += (str.charAt((int) ((Math.random() * 10) % p)));
        }

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID FROM requestedVendors WHERE name = '" + vendorName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    idNum = "" + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT address FROM requestedVendors WHERE name = '" + vendorName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    address = "" + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT phoneNo FROM requestedVendors WHERE name = '" + vendorName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    phoneNo = "" + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT emailAdd FROM requestedVendors WHERE name = '" + vendorName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    emailAdd = "" + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT reason FROM requestedVendors WHERE name = '" + vendorName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    reason = "" + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT busOrInd FROM requestedVendors WHERE name = '" + vendorName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    busOrInd = "" + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT userRequested FROM requestedVendors WHERE name = '" + vendorName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    userRequested = "" + rs.getObject(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        String sqlString = ("INSERT INTO vendors VALUES ('" + idNum + "', '" + username + "', '" + name + "', '" +
                reason + "', '" + busOrInd + "', '" + userRequested + "', 0, 0, 0, '0', 1)");
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        char[] password = generatePassword();
        String pass = password.toString();
        String encPass = encryptDecrypt.encrypt(pass);

        sqlString = ("UPDATE users SET userName = '" + username + "', passWord = '" + encPass + "' WHERE userID ='" + idNum + "'");
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        sqlString = ("DELETE FROM requestedVendors WHERE name = '" + vendorName + "'");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        vendorEmailandUserName.add(emailAdd);
        vendorEmailandUserName.add(username);
        vendorEmailandUserName.add(pass);
        return vendorEmailandUserName;

    }


    public static String[] getRequestedPrisonersToView() {

        Connection conn = getConnection();
        Statement stmt = null;
        ArrayList<String> requestedPrisoners = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username FROM exPrisoners WHERE pendingViewers IS NOT NULL");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    requestedPrisoners.add(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        String[] rqPris = new String[requestedPrisoners.size()];

        for (int i = 0; i < requestedPrisoners.size(); i++) {
            rqPris[i] = requestedPrisoners.get(i);
        }

        return rqPris;

    }

    //DECRYPTS
    public static int checkPendingViewerCount(String username) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        String viewers = null;
        int count = 0;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT pendingViewers FROM exPrisoners WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    if (rs.getString(i).length() > 5){
                        viewers = encryptDecrypt.decrypt(rs.getString(i));
                    }
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        String[] arr = viewers.split(", ");
        count = arr.length;
        return count;

    }

    //DECRYPTS
    public static String[] getPendingViewers(String username) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        String pendingViewers = null;
        int count = 0;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT pendingViewers FROM exPrisoners WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    if (rs.getString(i).length() > 5){
                        pendingViewers = encryptDecrypt.decrypt(rs.getString(i));
                    }
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        String[] arr = pendingViewers.split(", ");

        return arr;

    }

    //ENCRYPTS
    public static void approveViewer(String AUusername, String EXusername) throws Exception {

        String[] pendingviewers = getPendingViewers(EXusername);
        ArrayList<String> newPV = new ArrayList<>();
        String newPVString = null;

        for (int i = 0; i < pendingviewers.length; i++) {

            if (pendingviewers[i].equals(AUusername)) {
                continue;
            }
            else {
                newPV.add(pendingviewers[i]);
            }
        }

        for (int i = 0; i < newPV.size(); i++) {
            if (i == 0) {
                newPVString = newPV.get(i);
            }
            else {
                newPVString = newPVString + ", " + newPV.get(i);
            }
        }
        Connection conn = getConnection();
        Statement stmt = null;

        String sqlString = null;

        if (newPVString == null) {
            sqlString = ("UPDATE exPrisoners SET pendingViewers = NULL WHERE username = '" + EXusername + "';");
        }
        else {
            newPVString = encryptDecrypt.encrypt(newPVString);
            sqlString = ("UPDATE exPrisoners SET pendingViewers = '" + newPVString + "' WHERE username = '" + EXusername + "';");
        }

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        AUusername = encryptDecrypt.encrypt(AUusername);
        sqlString = ("UPDATE exPrisoners SET approvedViewers = '" + AUusername + "' WHERE username = '" + EXusername + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        AUusername = encryptDecrypt.decrypt(AUusername);
        //adding the EX to the AU's users attached
        String attachedUsers = getAttachedUsers(AUusername);
        if (attachedUsers == null) {
            attachedUsers = "";
        }

        //updating attached users
        if (attachedUsers.contains("EX")) {
            attachedUsers = attachedUsers + ", " + EXusername;
            attachedUsers = encryptDecrypt.encrypt(attachedUsers);
            sqlString = ("UPDATE authViewer SET usersAttached = '" + attachedUsers + "' WHERE username = '" + AUusername + "';");

            try {
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                stmt.executeUpdate(sqlString);
                stmt.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            EXusername = encryptDecrypt.encrypt(EXusername);

            sqlString = ("UPDATE authViewer SET usersAttached = '" + EXusername + "' WHERE username = '" + AUusername + "';");

            try {
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                stmt.executeUpdate(sqlString);
                stmt.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //ENCRYPTS
    public static void denyViewer(String AUusername, String EXusername) throws Exception {

        String[] pendingviewers = getPendingViewers(EXusername);
        ArrayList<String> newPV = new ArrayList<>();
        String newPVString = null;

        for (int i = 0; i < pendingviewers.length; i++) {

            if (pendingviewers[i].equals(AUusername)) {
                continue;
            }
            else {
                newPV.add(pendingviewers[i]);
            }
        }

        for (int i = 0; i < newPV.size(); i++) {
            if (i == 0) {
                newPVString = newPV.get(i);
            }
            else {
                newPVString = newPVString + ", " + newPV.get(i);
            }
        }

        if (newPVString == null) {
            newPVString = "";
        }

        Connection conn = getConnection();
        Statement stmt = null;

        newPVString = encryptDecrypt.encrypt(newPVString);

        String sqlString = ("UPDATE exPrisoners SET pendingViewers = '" + newPVString + "' WHERE username = '" + EXusername + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //DECRYPTS
    public static ArrayList<String> getListOfVendors() throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        ArrayList<String> vendorNames = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM vendors WHERE busOrInd = 1");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    vendorNames.add(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }


        return vendorNames;
    }

    //DECRYPTS AND ENCRYPTS
    public static void requestExistingVendor(String username, String vendorName) throws Exception {

        Connection conn = getConnection();
        Statement stmt = null;
        String attachedUsers = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usersAttached FROM vendors WHERE name = '" + vendorName + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    attachedUsers = encryptDecrypt.decrypt(rs.getString(i));
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        attachedUsers = attachedUsers + ", " + username;
        attachedUsers = encryptDecrypt.encrypt(attachedUsers);

        String sqlString = ("UPDATE vendors SET usersAttached ='" + attachedUsers + "' WHERE name = '" + vendorName + "'");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addNewRequestedIndividualVendor(String username, String vendorName, String address, String phoneNo, String emailAdd, String reason) throws Exception {
        Connection conn = getConnection();
        Statement stmt = null;
        String userID = genUserID("users");

        String encAddress = encryptDecrypt.encrypt(address);
        String encPhoneNo = encryptDecrypt.encrypt(phoneNo);
        String encEmailAdd = encryptDecrypt.encrypt(emailAdd);
        String encReason = encryptDecrypt.encrypt(reason);

        String sqlString = ("INSERT INTO requestedVendors VALUES ('" + userID + "', '" + vendorName + "', '" + encAddress + "', '" +
                encPhoneNo + "', '" + encEmailAdd + "', '" + encReason + "', 0, + '" + username +"')");
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        sqlString = ("INSERT INTO users VALUES ('" + userID + "', '" + userID + "', ' ', 'N/A', '" + vendorName + "', 'N/A', 'N/A','N/A', '" + encPhoneNo + "', '" + encEmailAdd + "', " +
                "'N/A', 'N/A', 'N/A', 'N/A', 'N/A')");
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }


    public static Boolean checkVendorFirstLogin(String username) {
        Connection conn = getConnection();
        Statement stmt = null;
        Boolean check = false;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT firstLogin FROM vendors WHERE username = '" + username + "'");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    check = rs.getBoolean(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return check;
    }

    //ENCRYPTS
    public static void updateVendorBankDetails(String username, String bankName, String bankAccNo, String bankSortCode) throws Exception {
        Connection conn = getConnection();
        Statement stmt = null;

        String encBankName = encryptDecrypt.encrypt(bankName);
        String encBankAccNo = encryptDecrypt.encrypt(bankAccNo);
        String encBankSortCode = encryptDecrypt.encrypt(bankSortCode);

        String sqlString = ("UPDATE vendors SET bankAccNo = '" + encBankAccNo + "', bankAccSortNo = '" + encBankSortCode + "', bankName = '" + encBankName + "', " +
                "firstLogin = 0 WHERE username = '" + username + "';");

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static char[] generatePassword() {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[8];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< 8; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }

    public static Double getTotalInvested() {

        Connection conn = getConnection();
        Statement stmt = null;
        Double invest = 0.0;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT totalInvested FROM investors");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    invest = invest + rs.getDouble(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return invest;
    }

    public static Double getTotalLoaned() {

        Connection conn = getConnection();
        Statement stmt = null;
        Double loaned = 0.0;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT borrowAmount FROM exPrisoners");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    loaned = loaned + rs.getDouble(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return loaned;
    }

    public static Double getTotalToVendors() {

        Connection conn = getConnection();
        Statement stmt = null;
        Double vend = 0.0;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT balance FROM vendors");
            int n = 0;
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                n++;
                for (int i = 1; i <= numColumns; i++) {
                    vend = vend + rs.getDouble(i);
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return vend;
    }
}