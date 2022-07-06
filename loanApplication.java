import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.*;
import java.text.DecimalFormat;

public class loanApplication {
   static JPanel panel;
   static JFrame frame;
   static JLabel loanTitle, enterDetails, titleLabel, firNam, surNam, dob, slash, mobNo, homNo, email, houseNam, disclose1, disclose2, addL1, addL2, cityLabel,
           post, inNo, borrow, term, pound, years, afterInterest, afterInterestAmount, interestDisclose, required;
   static JTextField firstNameTxt, surnameTxt, mobileNo, homeNo, emailAdd, houseName, addLine1,
           addLine2, cityText, postcode, inmateNo;
   static JSpinner borrowAmount, loanTerm, dateOfBirthDays, dateOfBirthMonths, dateOfBirthYears;
   static JButton next, cancel;
   static JCheckBox tick;
   static JComboBox<String> titleBox;
   static SpinnerNumberModel borrowModel = new SpinnerNumberModel(100, 100, 5000, 100);
   static SpinnerNumberModel termModel = new SpinnerNumberModel(1, 1, 10, 1);
   static SpinnerNumberModel daysModel = new SpinnerNumberModel(1, 1, 31, 1);
   static SpinnerNumberModel monthsModel = new SpinnerNumberModel(1, 1, 12, 1);
   static SpinnerNumberModel yearsModel = new SpinnerNumberModel(1920, 1920, 2003, 1);
   static String[] titleString = {"...","Mr", "Ms", "Miss", "Mrs", "Mx"};
   static String title, firstName, surname, dateOfBirth, mobileNumb, homePhone, emailAddress, houseNo, addressL1,
    addressL2, addCity, addPost, inmateNumber, loan, length;
   static ArrayList<String> address = new ArrayList<String>();
   static ArrayList<String> personalDetails = new ArrayList<String>();
   static double interest;
   static int dobDay, dobMon, dobYear;
   static final DecimalFormat format = new DecimalFormat("0.00");
   
   public static void loanAppGUI(){
       panel = new JPanel();
       frame = new JFrame("Loan Application");
              
       frame.setSize(350,660);
       panel.setBackground(Color.decode("#E6E6FA"));
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       panel.setLayout(null);
       frame.add(panel);
       
       loanTitle = new JLabel("Loan Application");
       loanTitle.setBounds(125, 3, 150,25);
       loanTitle.setFont(new Font("Roboto", Font.BOLD, 13));
       loanTitle.setForeground(Color.decode("#653C94"));
       panel.add(loanTitle);
       
       enterDetails = new JLabel("Please enter your details");
       enterDetails.setBounds(10, 17, 150, 25);
       enterDetails.setFont(new Font("Roboto", Font.ITALIC, 11));
       enterDetails.setForeground(Color.decode("#47475E"));
       panel.add(enterDetails);
       
       titleLabel = new JLabel("*Title:");
       titleLabel.setBounds(10, 45, 150,25);
       titleLabel.setFont(new Font("Roboto", Font.PLAIN, 13));
       titleLabel.setForeground(Color.decode("#47475E"));
       panel.add(titleLabel);
       
       titleBox = new JComboBox<>(titleString);
       titleBox.setBounds(107, 47, 60, 17);
       titleBox.setSelectedItem(null);
       panel.add(titleBox);
       
       firNam = new JLabel("*First Name(s):");
       firNam.setBounds(10, 70, 100, 25);
       firNam.setFont(new Font("Roboto", Font.PLAIN, 13));
       firNam.setForeground(Color.decode("#4B3869"));
       panel.add(firNam);
       
       firstNameTxt = new JTextField(firstName);
       firstNameTxt.setBounds(107, 72, 200, 20);
       panel.add(firstNameTxt);

       surNam = new JLabel("*Surname:");
       surNam.setBounds(10, 100, 100, 25);
       surNam.setFont(new Font("Roboto", Font.PLAIN, 13));
       surNam.setForeground(Color.decode("#4B3869"));
       panel.add(surNam);
       
       surnameTxt = new JTextField(surname);
       surnameTxt.setBounds(107, 102, 200, 20);
       panel.add(surnameTxt);

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

       disclose1 = new JLabel("If you don't currently have a permanent address please tick");
       disclose1.setBounds(10, 244, 300, 20);
       disclose1.setFont(new Font("Roboto", Font.ITALIC, 11));
       disclose1.setForeground(Color.decode("#4B3869"));
       panel.add(disclose1);
       
       disclose2 = new JLabel("this box and enter your last known permanent address");
       disclose2.setBounds(10, 263, 270, 20);
       disclose2.setFont(new Font("Roboto", Font.ITALIC, 11));
       disclose2.setForeground(Color.decode("#4B3869"));
       panel.add(disclose2);

       tick = new JCheckBox();
       tick.setBounds(285, 252, 15, 35);
       tick.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       panel.add(tick);
       
       houseNam = new JLabel("*House name/number:");
       houseNam.setBounds(10, 285, 130, 25);
       houseNam.setFont(new Font("Roboto", Font.PLAIN, 13));
       houseNam.setForeground(Color.decode("#4B3869"));
       panel.add(houseNam);
       
       houseName = new JTextField(houseNo);
       houseName.setBounds(143, 287, 165, 20);
       panel.add(houseName);

       addL1 = new JLabel("*Address Line 1:");
       addL1.setBounds(10, 315, 130, 25);
       addL1.setFont(new Font("Roboto", Font.PLAIN, 13));
       addL1.setForeground(Color.decode("#4B3869"));
       panel.add(addL1);
       
       addLine1 = new JTextField(addressL1);
       addLine1.setBounds(143, 317, 165, 20);
       panel.add(addLine1);

       addL2 = new JLabel(" Address Line 2:");
       addL2.setBounds(10, 345, 130, 25);
       addL2.setFont(new Font("Roboto", Font.PLAIN, 13));
       addL2.setForeground(Color.decode("#4B3869"));
       panel.add(addL2);

       addLine2 = new JTextField(addressL2);
       addLine2.setBounds(143, 347, 165, 20);
       panel.add(addLine2);
       
       cityLabel = new JLabel("*City/Town:");
       cityLabel.setBounds(10, 375, 130, 25);
       cityLabel.setFont(new Font("Roboto", Font.PLAIN, 13));
       cityLabel.setForeground(Color.decode("#4B3869"));
       panel.add(cityLabel);

       cityText = new JTextField(addCity);
       cityText.setBounds(143, 377, 165, 20);
       panel.add(cityText);
       
       post = new JLabel("*Postcode:");
       post.setBounds(10, 405, 130, 25);
       post.setFont(new Font("Roboto", Font.PLAIN, 13));
       post.setForeground(Color.decode("#4B3869"));
       panel.add(post);
       
       postcode = new JTextField(addPost);
       postcode.setBounds(143, 407, 75, 20);
       panel.add(postcode);
       
       inNo = new JLabel("*Prison Number:");
       inNo.setBounds(10, 435, 130, 25);
       inNo.setFont(new Font("Roboto", Font.PLAIN, 13));
       inNo.setForeground(Color.decode("#4B3869"));
       panel.add(inNo);
       
       inmateNo = new JTextField(inmateNumber);
       inmateNo.setBounds(143, 437, 60, 20);
       panel.add(inmateNo);
       
       borrow = new JLabel("*Amount you'd like to borrow?:");
       borrow.setBounds(10, 465, 250, 25);
       borrow.setFont(new Font("Roboto", Font.PLAIN, 13));
       borrow.setForeground(Color.decode("#4B3869"));
       panel.add(borrow);
       
       borrowAmount = new JSpinner();
       borrowAmount.setBounds(200, 467, 65, 20);
       borrowAmount.setModel(borrowModel);
       panel.add(borrowAmount);

       pound = new JLabel("£");
       pound.setBounds(190, 465, 250, 25);
       pound.setFont(new Font("Roboto", Font.PLAIN, 13));
       pound.setForeground(Color.decode("#4B3869"));
       panel.add(pound);
       
       term = new JLabel("*Length of loan term: ");
       term.setBounds(10, 495, 250, 25);
       term.setFont(new Font("Roboto", Font.PLAIN, 13));
       term.setForeground(Color.decode("#4B3869"));
       panel.add(term);

       loanTerm = new JSpinner();
       loanTerm.setBounds(200, 497, 65, 20);
       loanTerm.setModel(termModel);
       panel.add(loanTerm);

       years = new JLabel("years");
       years.setBounds(270, 495, 250, 25);
       years.setFont(new Font("Roboto", Font.PLAIN, 13));
       years.setForeground(Color.decode("#4B3869"));
       panel.add(years);
       
       afterInterest = new JLabel("Amount to payback:");
       afterInterest.setBounds(10, 525, 250, 25);
       afterInterest.setFont(new Font("Roboto", Font.PLAIN, 13));
       afterInterest.setForeground(Color.decode("#4B3869"));
       panel.add(afterInterest);

       afterInterestAmount = new JLabel(String.valueOf(interest));
       afterInterestAmount.setBounds(10, 525, 250, 25);
       afterInterestAmount.setFont(new Font("Roboto", Font.PLAIN, 13));
       afterInterestAmount.setForeground(Color.decode("#4B3869"));
       panel.add(afterInterest);
       
       interestDisclose = new JLabel("<html>Interest is calculated with a compound rate of 5% per year<html>");
       interestDisclose.setBounds(10, 545, 300, 25);
       interestDisclose.setFont(new Font("Roboto", Font.PLAIN, 10));
       interestDisclose.setForeground(Color.decode("#4B3869"));
       panel.add(interestDisclose);
       
       cancel = new JButton("Cancel");
       cancel.setBounds(155, 580,75, 25);
       cancel.setFont(new Font("Roboto", Font.BOLD, 12));
       cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       cancel.setBackground(Color.decode("#CAB8FF"));
       cancel.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   LogIn.logInGUI();
                   frame.dispose();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
       });
       panel.add(cancel);
       
       next = new JButton("Next");
       next.setBounds(235, 580,75, 25);
       next.setFont(new Font("Roboto", Font.BOLD, 12));
       next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       next.setBackground(Color.decode("#CAB8FF"));
       next.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               try {
                   nextButton();
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
       });
       panel.add(next);
       
       required = new JLabel("(* Required field)");
       required.setBounds(10, 589, 250, 25);
       required.setFont(new Font("Roboto", Font.PLAIN, 11));
       required.setForeground(Color.decode("#4B3869"));
       panel.add(required);
       
       frame.setVisible(true);
   }

   public static void nextButton() throws Exception {
       boolean isTitle, dobBool, isFirst, isSur, isMob, isEmail,isHouse, isAddL1, isCity, isPost, isInNo, ticked = false;
       if (titleBox.getSelectedIndex() == -1) {
           titleLabel.setForeground(Color.red);
           isTitle = false;
       }
       else{
           titleLabel.setForeground(Color.decode("#4B3869"));
           isTitle = true;
           int i = titleBox.getSelectedIndex();
           title = titleString[i];
       }
       if (firstNameTxt.getText().equals("")) {
           firNam.setForeground(Color.RED);
           isFirst =false;
       }
       else{
           firNam.setForeground(Color.decode("#4B3869"));
           isFirst = true;
           firstName = firstNameTxt.getText();
       }
       if (surnameTxt.getText().equals("")) {
           surNam.setForeground(Color.RED);
           isSur = false;
       }
       else{
           surNam.setForeground(Color.decode("#4B3869"));
           isSur = true;
           surname = surnameTxt.getText();
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
       if (tick.isSelected()){
           ticked = true;
       }
       if (houseName.getText().equals("")) {
           houseNam.setForeground(Color.RED);
           isHouse = false;
       }
       else{
           houseNam.setForeground(Color.decode("#4B3869"));
           houseNo = houseName.getText();
           address.add(houseNo);
           isHouse = true;
       }
       if (addLine1.getText().equals("")) {
           addL1.setForeground(Color.RED);
           isAddL1 = false;
       }
       else{
           addL1.setForeground(Color.decode("#4B3869"));
           addressL1 = addLine1.getText();
           address.add(addressL1);
           isAddL1 = true;
       }
       if (!addLine2.equals("")){
           addressL2 = addLine2.getText();
           address.add(addressL2);
       }

       if (cityText.getText().equals("")) {
           cityLabel.setForeground(Color.RED);
           isCity = false;
       }
       else{
           cityLabel.setForeground(Color.decode("#4B3869"));
           addCity = cityText.getText();
           address.add(addCity);
           isCity = true;
       }
       if (postcode.getText().equals("")) {
           post.setForeground(Color.RED);
           isPost = false;
       }
       else{
           post.setForeground(Color.decode("#4B3869"));
           addPost = postcode.getText();
           address.add(addPost);
           isPost = true;
       }
       if (inmateNo.getText().equals("")) {
           inNo.setForeground(Color.RED);
           isInNo = false;
       }
       else{
           inNo.setForeground(Color.decode("#4B3869"));
           isInNo = true;
           inmateNumber = inmateNo.getText();
       }

       if(isTitle  && dobBool && isFirst && isSur && isMob && isEmail && isHouse && isAddL1 && isCity && isPost && isInNo){
           int lo = (int) borrowAmount.getValue();
           loan = String.valueOf(lo);
           int le = (int) loanTerm.getValue();
           length = String.valueOf(le);
           interest = Double.parseDouble(format.format(lo * Math.pow((1 + (0.05 / 1)), (1 * le))));

           String userID = connectDB.genUserID("users");

           String dob = dateOfBirthYears.getValue() + "-" + dateOfBirthMonths.getValue() + "-" + dateOfBirthDays.getValue();

           connectDB.insertUser(userID, title, firstName, surname, Date.valueOf(dob), mobileNumb, homePhone,
                   emailAddress, houseNo, addressL1, addressL2, addCity, addPost, inmateNumber,
                   loan, length);

           personalDetails.add(title);
           personalDetails.add(firstName);
           personalDetails.add(surname);
           personalDetails.add(dateOfBirth);
           personalDetails.add(mobileNumb);
           if(!homeNo.equals("")){
               homePhone = homeNo.getText();
               personalDetails.add(homePhone);
           }
           personalDetails.add(emailAddress);
           if(ticked){personalDetails.add("Temporary Address");}
           personalDetails.add(String.valueOf(address));
           personalDetails.add(inmateNumber);
           personalDetails.add(loan);
           personalDetails.add(length);
           personalDetails.add(String.valueOf(interest));
           loanAppGuarantor.loanAppGuarantorGUI(userID);
           frame.dispose();
       }
   }
}
