import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.*;

public class loanAppGuarantor {
   static JPanel panel;
   static JFrame frame;
   static JLabel guarantorTitle, enterDetails, title, firNam, surNam, dob, slash, mobNo, homNo, email, houseNam, addL1, addL2, cityLabel,
           post, relation, required, otherLabel;
   static JTextField firstName, surName, mobileNo, homeNo, emailAdd, houseName, addLine1,
           addLine2, cityText, postcode, otherText;
   static JSpinner dateOfBirthDays, dateOfBirthMonths, dateOfBirthYears;
   static JButton next, back;
   static JComboBox<String> titleBox, relationBox;
   static String[] titleString = {"...", "Mr", "Ms", "Miss", "Mrs", "Mx"};
   static String[] relationString = {"...", "Parent", "Partner", "Employer", "Family Member", "Friend", "Company", "Charity", "Other"};
   static SpinnerNumberModel daysModel = new SpinnerNumberModel(1, 1, 31, 1);
   static SpinnerNumberModel monthsModel = new SpinnerNumberModel(1, 1, 12, 1);
   static SpinnerNumberModel yearsModel = new SpinnerNumberModel(1920, 1920, 2003, 1);
   static ArrayList<String> guarantorAddress = new ArrayList<String>();
    static ArrayList<String> guarantorDetails = new ArrayList<String>();
   static String getTitle, firstname, surname, dateOfBirth, mobileNumb, homePhone, emailAddress, guaHouseNo, otherRelate,
           guaAddL1, guaAddL2, guaCity, guaPost, getRelation;
   static int dobDay, dobMon, dobYear;

   public static void loanAppGuarantorGUI(String userID){
       panel = new JPanel();
       frame = new JFrame("Loan Application");
              
       frame.setSize(350,545);
       panel.setBackground(Color.decode("#E6E6FA"));
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       panel.setLayout(null);
       frame.add(panel);
       
       guarantorTitle = new JLabel("Guarantor Details");
       guarantorTitle.setBounds(125, 3, 150,25);
       guarantorTitle.setFont(new Font("Roboto", Font.BOLD, 13));
       guarantorTitle.setForeground(Color.decode("#653C94"));
       panel.add(guarantorTitle);
       
       enterDetails = new JLabel("Please enter your Guarantor's details");
       enterDetails.setBounds(10, 20, 200, 25);
       enterDetails.setFont(new Font("Roboto", Font.ITALIC, 11));
       enterDetails.setForeground(Color.decode("#47475E"));
       panel.add(enterDetails);
       
       title = new JLabel("*Title:");
       title.setBounds(10, 45, 150,25);
       title.setFont(new Font("Roboto", Font.PLAIN, 13));
       title.setForeground(Color.decode("#47475E"));
       panel.add(title);
       
       titleBox = new JComboBox<>(titleString);
       titleBox.setBounds(107, 47, 60, 17);
       titleBox.setSelectedItem(null);
       panel.add(titleBox);
       
       firNam = new JLabel("*First Name(s):");
       firNam.setBounds(10, 70, 100, 25);
       firNam.setFont(new Font("Roboto", Font.PLAIN, 13));
       firNam.setForeground(Color.decode("#4B3869"));
       panel.add(firNam);
       
       firstName = new JTextField(firstname);
       firstName.setBounds(107, 72, 200, 20);
       panel.add(firstName);
       
       surNam = new JLabel("*Surname:");
       surNam.setBounds(10, 100, 100, 25);
       surNam.setFont(new Font("Roboto", Font.PLAIN, 13));
       surNam.setForeground(Color.decode("#4B3869"));
       panel.add(surNam);
       
       surName = new JTextField(surname);
       surName.setBounds(107, 102, 200, 20);
       panel.add(surName);
       
       dob = new JLabel("*Date of Birth:(dd/mm/yyyy)");
       dob.setBounds(10, 130, 165, 25);
       dob.setFont(new Font("Roboto", Font.PLAIN, 13));
       dob.setForeground(Color.decode("#4B3869"));
       panel.add(dob);
       
       dateOfBirthDays = new JSpinner();
       dateOfBirthDays.setBounds(173, 132, 35, 20);
       dateOfBirthDays.setModel(daysModel);
       panel.add(dateOfBirthDays);
            
       slash = new JLabel("/");
       slash.setBounds(211, 130, 5, 25);
       slash.setFont(new Font("Roboto", Font.PLAIN, 13));
       slash.setForeground(Color.decode("#4B3869"));
       panel.add(slash);
       
       dateOfBirthMonths = new JSpinner();
       dateOfBirthMonths.setBounds(219, 132, 35, 20);
       dateOfBirthMonths.setModel(monthsModel);
       panel.add(dateOfBirthMonths);
       
       slash = new JLabel("/");
       slash.setBounds(257, 130, 5, 25);
       slash.setFont(new Font("Roboto", Font.PLAIN, 13));
       slash.setForeground(Color.decode("#4B3869"));
       panel.add(slash);
       
       dateOfBirthYears = new JSpinner();
       dateOfBirthYears.setBounds(265, 132, 50, 20);
       dateOfBirthYears.setModel(yearsModel);
       JSpinner.NumberEditor editor = new JSpinner.NumberEditor(dateOfBirthYears, "#" );
       dateOfBirthYears.setEditor(editor);
       panel.add(dateOfBirthYears);
              
       mobNo = new JLabel("*Mobile Number:");
       mobNo.setBounds(10, 160, 100, 25);
       mobNo.setFont(new Font("Roboto", Font.PLAIN, 13));
       mobNo.setForeground(Color.decode("#4B3869"));
       panel.add(mobNo);
       
       mobileNo = new JTextField(mobileNumb);
       mobileNo.setBounds(107, 162, 200, 20);
       panel.add(mobileNo);
       
       homNo = new JLabel(" Phone Number:");
       homNo.setBounds(10, 190, 100, 25);
       homNo.setFont(new Font("Roboto", Font.PLAIN, 13));
       homNo.setForeground(Color.decode("#4B3869"));
       panel.add(homNo);
       
       homeNo = new JTextField(homePhone);
       homeNo.setBounds(107, 192, 200, 20);
       panel.add(homeNo);
       
       email = new JLabel("*Email Address:");
       email.setBounds(10, 220, 100, 25);
       email.setFont(new Font("Roboto", Font.PLAIN, 13));
       email.setForeground(Color.decode("#4B3869"));
       panel.add(email);
       
       emailAdd = new JTextField(emailAddress);
       emailAdd.setBounds(107, 222, 200, 20);
       panel.add(emailAdd);
       
       houseNam = new JLabel("*House name/number:");
       houseNam.setBounds(10, 255, 130, 25);
       houseNam.setFont(new Font("Roboto", Font.PLAIN, 13));
       houseNam.setForeground(Color.decode("#4B3869"));
       panel.add(houseNam);
       
       houseName = new JTextField(guaHouseNo);
       houseName.setBounds(143, 257, 165, 20);
       panel.add(houseName);
           
       addL1 = new JLabel("*Address Line 1:");
       addL1.setBounds(10, 285, 130, 25);
       addL1.setFont(new Font("Roboto", Font.PLAIN, 13));
       addL1.setForeground(Color.decode("#4B3869"));
       panel.add(addL1);
       
       addLine1 = new JTextField(guaAddL1);
       addLine1.setBounds(143, 287, 165, 20);
       panel.add(addLine1);
       
       addL2 = new JLabel(" Address Line 2:");
       addL2.setBounds(10, 315, 130, 25);
       addL2.setFont(new Font("Roboto", Font.PLAIN, 13));
       addL2.setForeground(Color.decode("#4B3869"));
       panel.add(addL2);
       
       addLine2 = new JTextField(guaAddL2);
       addLine2.setBounds(143, 317, 165, 20);
       panel.add(addLine2);
       
       cityLabel = new JLabel("*City/Town:");
       cityLabel.setBounds(10, 345, 130, 25);
       cityLabel.setFont(new Font("Roboto", Font.PLAIN, 13));
       cityLabel.setForeground(Color.decode("#4B3869"));
       panel.add(cityLabel);
       
       cityText = new JTextField(guaCity);
       cityText.setBounds(143, 347, 165, 20);
       panel.add(cityText);
       
       post = new JLabel("*Postcode:");
       post.setBounds(10, 375, 130, 25);
       post.setFont(new Font("Roboto", Font.PLAIN, 13));
       post.setForeground(Color.decode("#4B3869"));
       panel.add(post);
       
       postcode = new JTextField(guaPost);
       postcode.setBounds(143, 377, 75, 20);
       panel.add(postcode);
       
       relation = new JLabel("*Relation to Guarantor:");
       relation.setBounds(10, 405, 200, 25);
       relation.setFont(new Font("Roboto", Font.PLAIN, 13));
       relation.setForeground(Color.decode("#4B3869"));
       panel.add(relation);
       
       relationBox = new JComboBox<>(relationString);
       relationBox.setBounds(150, 409, 120, 20);
       panel.add(relationBox);
       
       otherLabel = new JLabel("If other, please specify:");
       otherLabel.setBounds(10, 435, 200, 25);
       otherLabel.setFont(new Font("Roboto", Font.PLAIN, 13));
       otherLabel.setForeground(Color.decode("#4B3869"));
       panel.add(otherLabel);
       
       otherText = new JTextField(otherRelate);
       otherText.setBounds(150, 437, 157, 20);
       panel.add(otherText);
       
       back = new JButton("Back");
       back.setBounds(155, 470,75, 25);
       back.setFont(new Font("Roboto", Font.BOLD, 12));
       back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       back.setBackground(Color.decode("#CAB8FF"));
       back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
                loanApplication.loanAppGUI();
                frame.dispose(); 
           }
       });
       panel.add(back);
       
       next = new JButton("Next");
       next.setBounds(235, 470,75, 25);
       next.setFont(new Font("Roboto", Font.BOLD, 12));
       next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       next.setBackground(Color.decode("#CAB8FF"));
       next.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   nextButton(userID);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
       });
       panel.add(next);
       
       required = new JLabel("(* Required field)");
       required.setBounds(10, 475, 250, 25);
       required.setFont(new Font("Roboto", Font.PLAIN, 11));
       required.setForeground(Color.decode("#4B3869"));
       panel.add(required);
            
       frame.setVisible(true);
   }

    public static void nextButton(String pUserID) throws Exception {
        boolean isTitle, dobBool, isFirst, isSur, isMob, isEmail,isHouse, isAddL1, isCity, isPost, isRelation;
        if (titleBox.getSelectedIndex() == -1) {
            title.setForeground(Color.red);
            isTitle = false;
        }
        else{
            title.setForeground(Color.decode("#4B3869"));
            isTitle = true;
            int i = titleBox.getSelectedIndex();
            getTitle = titleString[i];
        }
        if (firstName.getText().equals("")) {
            firNam.setForeground(Color.RED);
            isFirst =false;
        }
        else{
            firNam.setForeground(Color.decode("#4B3869"));
            isFirst = true;
            firstname = firstName.getText();
        }
        if (surName.getText().equals("")) {
            surNam.setForeground(Color.RED);
            isSur = false;
        }
        else{
            surNam.setForeground(Color.decode("#4B3869"));
            isSur = true;
            surname = surName.getText();
        }
        if (dateOfBirthDays.getValue().equals(1) && dateOfBirthMonths.getValue().equals(1) && dateOfBirthYears.getValue().equals(1920)) {
            dob.setForeground(Color.RED);
            dobBool = false;
        }
        else{
            dob.setForeground(Color.decode("#4B3869"));
            dobBool = true;
            dobDay = (int) dateOfBirthDays.getValue();
            dobMon = (int) dateOfBirthMonths.getValue();
            dobYear = (int) dateOfBirthYears.getValue();
            dateOfBirth = String.valueOf(dobDay) + "/" + String.valueOf(dobMon) + "/" + String.valueOf(dobYear);
        }
        if (mobileNo.getText().equals("")) {
            mobNo.setForeground(Color.RED);
            isMob = false;
        }
        else{
            mobNo.setForeground(Color.decode("#4B3869"));
            isMob = true;
            mobileNumb = mobileNo.getText();
        }
        if (emailAdd.getText().equals("")) {
            email.setForeground(Color.RED);
            isEmail = false;
        }
        else{
            email.setForeground(Color.decode("#4B3869"));
            isEmail = true;
            emailAddress = emailAdd.getText();
        }
        if (houseName.getText().equals("")) {
            houseNam.setForeground(Color.RED);
            isHouse = false;
        }
        else{
            houseNam.setForeground(Color.decode("#4B3869"));
            guaHouseNo = houseName.getText();
            guarantorAddress.add(guaHouseNo);
            isHouse = true;
        }
        if (addLine1.getText().equals("")) {
            addL1.setForeground(Color.RED);
            isAddL1 = false;
        }
        else{
            addL1.setForeground(Color.decode("#4B3869"));
            guaAddL1 = addLine1.getText();
            guarantorAddress.add(guaAddL1);
            isAddL1 = true;
        }
        if (!addLine2.equals("")){
            guaAddL2 = addLine2.getText();
            guarantorAddress.add(guaAddL2);
        }

        if (cityText.getText().equals("")) {
            cityLabel.setForeground(Color.RED);
            isCity = false;
        }
        else{
            cityLabel.setForeground(Color.decode("#4B3869"));
            guaCity = cityText.getText();
            guarantorAddress.add(guaCity);
            isCity = true;
        }
        if (postcode.getText().equals("")) {
            post.setForeground(Color.RED);
            isPost = false;
        }
        else{
            post.setForeground(Color.decode("#4B3869"));
            guaPost = postcode.getText();
            guarantorAddress.add(guaPost);
            isPost = true;
        }
        if (relationBox.getSelectedIndex() == 0) {
            relation.setForeground(Color.red);
            isRelation = false;
        }
        else if (relationBox.getSelectedIndex() == 8 && otherText.getText().equals("")) {
            otherLabel.setForeground(Color.red);
            isRelation = false;
        }
        else{
            relation.setForeground(Color.decode("#4B3869"));
            isRelation = true;
            int i = relationBox.getSelectedIndex();
            getRelation = relationString[i];
        }

        if(isTitle && dobBool && isFirst && isSur && isMob && isEmail && isHouse && isAddL1 && isCity && isPost && isRelation){
            guarantorDetails.add(getTitle);
            guarantorDetails.add(firstname);
            guarantorDetails.add(surname);
            guarantorDetails.add(dateOfBirth);
            guarantorDetails.add(mobileNumb);
            if(!homeNo.equals("")){
                homePhone = homeNo.getText();
                guarantorDetails.add(homePhone);
            }
            guarantorDetails.add(emailAddress);
            guarantorDetails.add(String.valueOf(guarantorAddress));
            guarantorDetails.add(getRelation);
            if(!otherText.getText().equals("")){
                otherRelate = otherText.getText();
                guarantorDetails.add(otherRelate);
            }

            String userID = connectDB.genUserID("guarantors");
            String dob = dateOfBirthYears.getValue() + "-" + dateOfBirthMonths.getValue() + "-" + dateOfBirthDays.getValue();

            connectDB.insertGuarantor(userID, getTitle, firstname, surname, Date.valueOf(dob), mobileNumb, homePhone, emailAddress, guaHouseNo,
                    guaAddL1, guaAddL2, guaCity, guaPost, getRelation, otherRelate, pUserID);

            loanAppConfirm.confirmGUI(pUserID);
            frame.dispose();
        }
    }
}
