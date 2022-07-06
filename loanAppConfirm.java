import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;

public class loanAppConfirm {
   static JPanel panel;
   static JFrame frame;
   static JLabel title1, title2, title3, disclose, userName, userDOB, userPhone, userEmail, userAdd, userPost, prisonNo, borrow, term, guarantorName, guarantorDOB,
           guarantorPhone, guarantorEmail, guarantorAdd, guarantorPost, guarantorRelation;
   static JLabel userFullName, userDateOfBirth, userPhoneNos, userEmailAdd, userAddress, userPostcode, prisonNumber, borrowAmount, termLength,
           guarantorFullName, guarantorDateOfBirth, guarantorPhoneNo, guarantorEmailAdd, guarantorAddress,
           guarantorPostcode, guarantorRelationship;
   static JButton continueButton, back;
   static double borrowed = 1000;
   static NumberFormat formatter = NumberFormat.getCurrencyInstance();
   
   public static void confirmGUI(String userID){
       panel = new JPanel();
       frame = new JFrame("Loan Application");
              
       frame.setSize(340,610);
       panel.setBackground(Color.decode("#E6E6FA"));
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       panel.setLayout(null); 
       frame.add(panel);
       
       title1 = new JLabel("Confirm your details");
       title1.setBounds(100, 3, 150,25);
       title1.setFont(new Font("Roboto", Font.BOLD, 15));
       title1.setForeground(Color.decode("#653C94"));
       panel.add(title1);
       
       disclose = new JLabel("Please confirm the details you have entered are correct");
       disclose.setBounds(25, 25, 300, 25);
       disclose.setFont(new Font("Roboto", Font.ITALIC, 11));
       disclose.setForeground(Color.decode("#47475E"));
       panel.add(disclose);
       
       title2 = new JLabel("Your details");
       title2.setBounds(10, 50, 150,25);
       title2.setFont(new Font("Roboto", Font.BOLD, 14));
       title2.setForeground(Color.decode("#653C94"));
       panel.add(title2);
       
       userName = new JLabel("Full Name:");
       userName.setBounds(10, 70, 150,25);
       userName.setFont(new Font("Roboto", Font.BOLD, 13));
       userName.setForeground(Color.decode("#47475E"));
       panel.add(userName);

       String fullName = loanApplication.title + " " + loanApplication.firstName + " " + loanApplication.surname;
       userFullName = new JLabel(fullName);
       userFullName.setBounds(135, 70, 150,25);
       userFullName.setFont(new Font("Roboto", Font.PLAIN, 13));
       userFullName.setForeground(Color.decode("#47475E"));
       panel.add(userFullName);
       
       userDOB = new JLabel("Date of Birth:");
       userDOB.setBounds(10, 90, 150,25);
       userDOB.setFont(new Font("Roboto", Font.BOLD, 13));
       userDOB.setForeground(Color.decode("#47475E"));
       panel.add(userDOB);
       
       userDateOfBirth = new JLabel(loanApplication.dateOfBirth);
       userDateOfBirth.setBounds(135, 90, 150,25);
       userDateOfBirth.setFont(new Font("Roboto", Font.PLAIN, 13));
       userDateOfBirth.setForeground(Color.decode("#47475E"));
       panel.add(userDateOfBirth);
       
       userPhone = new JLabel("Phone Number(s):");
       userPhone.setBounds(10, 110, 150,25);
       userPhone.setFont(new Font("Roboto", Font.BOLD, 13));
       userPhone.setForeground(Color.decode("#47475E"));
       panel.add(userPhone);

       String numbers = loanApplication.mobileNumb + " " + loanApplication.homePhone;
       userPhoneNos = new JLabel(numbers);
       userPhoneNos.setBounds(135, 110, 200,25);
       userPhoneNos.setFont(new Font("Roboto", Font.PLAIN, 13));
       userPhoneNos.setForeground(Color.decode("#47475E"));
       panel.add(userPhoneNos);
       
       userEmail = new JLabel("Email Address:");
       userEmail.setBounds(10, 130, 150,25);
       userEmail.setFont(new Font("Roboto", Font.BOLD, 13));
       userEmail.setForeground(Color.decode("#47475E"));
       panel.add(userEmail);
       
       userEmailAdd = new JLabel(loanApplication.emailAddress);
       userEmailAdd.setBounds(135, 130, 300,25);
       userEmailAdd.setFont(new Font("Roboto", Font.PLAIN, 13));
       userEmailAdd.setForeground(Color.decode("#47475E"));
       panel.add(userEmailAdd);
       
       userAdd = new JLabel("Address:");
       userAdd.setBounds(10, 150, 150,25);
       userAdd.setFont(new Font("Roboto", Font.BOLD, 13));
       userAdd.setForeground(Color.decode("#47475E"));
       panel.add(userAdd);

       String adL1 = loanApplication.houseNo + " " + loanApplication.addressL1;
       userAddress = new JLabel(adL1);
       userAddress.setBounds(135, 157, 120, 15);
       userAddress.setFont(new Font("Roboto", Font.PLAIN, 13));
       userAddress.setForeground(Color.decode("#47475E"));
       panel.add(userAddress);
       userAddress = new JLabel(loanApplication.addCity);
       userAddress.setBounds(135, 183, 120, 15);
       userAddress.setFont(new Font("Roboto", Font.PLAIN, 13));
       userAddress.setForeground(Color.decode("#47475E"));
       panel.add(userAddress);
       
       userPost = new JLabel("Postcode:");
       userPost.setBounds(10, 210, 150,25);
       userPost.setFont(new Font("Roboto", Font.BOLD, 13));
       userPost.setForeground(Color.decode("#47475E"));
       panel.add(userPost);
       
       userPostcode = new JLabel(loanApplication.addPost);
       userPostcode.setBounds(135, 210, 60, 25);
       userPostcode.setFont(new Font("Roboto", Font.PLAIN, 13));
       userPostcode.setForeground(Color.decode("#47475E"));
       panel.add(userPostcode);
       
       prisonNo = new JLabel("Prison Number:");
       prisonNo.setBounds(10, 230, 150,25);
       prisonNo.setFont(new Font("Roboto", Font.BOLD, 13));
       prisonNo.setForeground(Color.decode("#47475E"));
       panel.add(prisonNo);
       
       prisonNumber = new JLabel(loanApplication.inmateNumber);
       prisonNumber.setBounds(135, 230, 60, 25);
       prisonNumber.setFont(new Font("Roboto", Font.PLAIN, 13));
       prisonNumber.setForeground(Color.decode("#47475E"));
       panel.add(prisonNumber);
       
       borrow = new JLabel("Amount Borrowed:");
       borrow.setBounds(10, 260, 150,25);
       borrow.setFont(new Font("Roboto", Font.BOLD, 13));
       borrow.setForeground(Color.decode("#47475E"));
       panel.add(borrow);

       int loan = Integer.parseInt(loanApplication.loan);
       String t = formatter.format(loan);
       borrowAmount = new JLabel(t);
       borrowAmount.setBounds(135, 260, 60, 25);
       borrowAmount.setFont(new Font("Roboto", Font.PLAIN, 13));
       borrowAmount.setForeground(Color.decode("#47475E"));
       panel.add(borrowAmount);
       
       term = new JLabel("Loan Term:");
       term.setBounds(10, 280, 150,25);
       term.setFont(new Font("Roboto", Font.BOLD, 13));
       term.setForeground(Color.decode("#47475E"));
       panel.add(term);
       
       String q = loanApplication.length;
       termLength = new JLabel(q);
       termLength.setBounds(135, 280, 60, 25);
       termLength.setFont(new Font("Roboto", Font.PLAIN, 13));
       termLength.setForeground(Color.decode("#47475E"));
       panel.add(termLength);
       
       borrow = new JLabel("Payback Amount:");
       borrow.setBounds(10, 300, 150,25);
       borrow.setFont(new Font("Roboto", Font.BOLD, 13));
       borrow.setForeground(Color.decode("#47475E"));
       panel.add(borrow);
       
       String r = formatter.format(loanApplication.interest);
       borrowAmount = new JLabel(r);
       borrowAmount.setBounds(135, 300, 60, 25);
       borrowAmount.setFont(new Font("Roboto", Font.PLAIN, 13));
       borrowAmount.setForeground(Color.decode("#47475E"));
       panel.add(borrowAmount);
       
       title3 = new JLabel("Guarantor Details");
       title3.setBounds(10, 330, 150,25);
       title3.setFont(new Font("Roboto", Font.BOLD, 14));
       title3.setForeground(Color.decode("#653C94"));
       panel.add(title3);
       
       guarantorName = new JLabel("Full Name:");
       guarantorName.setBounds(10, 350, 150,25);
       guarantorName.setFont(new Font("Roboto", Font.BOLD, 13));
       guarantorName.setForeground(Color.decode("#47475E"));
       panel.add(guarantorName);
       
       guarantorFullName = new JLabel(loanAppGuarantor.firstname);
       guarantorFullName.setBounds(135, 350, 100, 25);
       guarantorFullName.setFont(new Font("Roboto", Font.PLAIN, 13));
       guarantorFullName.setForeground(Color.decode("#47475E"));
       panel.add(guarantorFullName);
       
       guarantorDOB = new JLabel("Date of Birth:");
       guarantorDOB.setBounds(10, 370, 150,25);
       guarantorDOB.setFont(new Font("Roboto", Font.BOLD, 13));
       guarantorDOB.setForeground(Color.decode("#47475E"));
       panel.add(guarantorDOB);
       
       guarantorDateOfBirth = new JLabel(loanAppGuarantor.dateOfBirth);
       guarantorDateOfBirth.setBounds(135, 370, 150,25);
       guarantorDateOfBirth.setFont(new Font("Roboto", Font.PLAIN, 13));
       guarantorDateOfBirth.setForeground(Color.decode("#47475E"));
       panel.add(guarantorDateOfBirth);
       
       guarantorPhone = new JLabel("Phone Number(s):");
       guarantorPhone.setBounds(10, 390, 150,25);
       guarantorPhone.setFont(new Font("Roboto", Font.BOLD, 13));
       guarantorPhone.setForeground(Color.decode("#47475E"));
       panel.add(guarantorPhone);

       String guaNumbers = loanAppGuarantor.mobileNumb + " " + loanAppGuarantor.homePhone;
       guarantorPhoneNo = new JLabel(guaNumbers);
       guarantorPhoneNo.setBounds(135, 390, 200,25);
       guarantorPhoneNo.setFont(new Font("Roboto", Font.PLAIN, 13));
       guarantorPhoneNo.setForeground(Color.decode("#47475E"));
       panel.add(guarantorPhoneNo);
       
       guarantorEmail = new JLabel("Email Address:");
       guarantorEmail.setBounds(10, 410, 150,25);
       guarantorEmail.setFont(new Font("Roboto", Font.BOLD, 13));
       guarantorEmail.setForeground(Color.decode("#47475E"));
       panel.add(guarantorEmail);
       
       guarantorEmailAdd = new JLabel(loanAppGuarantor.emailAddress);
       guarantorEmailAdd.setBounds(135, 410, 150,25);
       guarantorEmailAdd.setFont(new Font("Roboto", Font.PLAIN, 13));
       guarantorEmailAdd.setForeground(Color.decode("#47475E"));
       panel.add(guarantorEmailAdd);
       
       guarantorAdd = new JLabel("Address:");
       guarantorAdd.setBounds(10, 430, 150,25);
       guarantorAdd.setFont(new Font("Roboto", Font.BOLD, 13));
       guarantorAdd.setForeground(Color.decode("#47475E"));
       panel.add(guarantorAdd);

       String guaAdd = loanAppGuarantor.guaHouseNo + " " + loanAppGuarantor.guaAddL1;
       guarantorAddress = new JLabel(guaAdd);
       guarantorAddress.setBounds(135, 435, 120,15);
       guarantorAddress.setFont(new Font("Roboto", Font.PLAIN, 13));
       guarantorAddress.setForeground(Color.decode("#47475E"));
       panel.add(guarantorAddress);
       guarantorAddress = new JLabel(loanAppGuarantor.guaCity);
       guarantorAddress.setBounds(135, 460, 120,15);
       guarantorAddress.setFont(new Font("Roboto", Font.PLAIN, 13));
       guarantorAddress.setForeground(Color.decode("#47475E"));
       panel.add(guarantorAddress);
       
       guarantorPost = new JLabel("Postcode:");
       guarantorPost.setBounds(10, 485, 150,25);
       guarantorPost.setFont(new Font("Roboto", Font.BOLD, 13));
       guarantorPost.setForeground(Color.decode("#47475E"));
       panel.add(guarantorPost);
       
       guarantorPostcode = new JLabel(loanAppGuarantor.guaPost);
       guarantorPostcode.setBounds(135, 485, 60,25);
       guarantorPostcode.setFont(new Font("Roboto", Font.PLAIN, 13));
       guarantorPostcode.setForeground(Color.decode("#47475E"));
       panel.add(guarantorPostcode);
       
       guarantorRelation = new JLabel("Relationship:");
       guarantorRelation.setBounds(10, 505, 150,25);
       guarantorRelation.setFont(new Font("Roboto", Font.BOLD, 13));
       guarantorRelation.setForeground(Color.decode("#47475E"));
       panel.add(guarantorRelation);
       
       guarantorRelationship = new JLabel(loanAppGuarantor.getRelation);
       guarantorRelationship.setBounds(135, 505, 60,25);
       guarantorRelationship.setFont(new Font("Roboto", Font.PLAIN, 13));
       guarantorRelationship.setForeground(Color.decode("#47475E"));
       panel.add(guarantorRelationship);
       
       back = new JButton("Back");
       back.setBounds(145, 535,75, 25);
       back.setFont(new Font("Roboto", Font.BOLD, 12));
       back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       back.setBackground(Color.decode("#CAB8FF"));
       back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               loanAppGuarantor.loanAppGuarantorGUI(userID);
               frame.dispose();
           }
       });
       panel.add(back);
       
       continueButton = new JButton("Continue");
       continueButton.setBounds(225, 535,85, 25);
       continueButton.setFont(new Font("Roboto", Font.BOLD, 12));
       continueButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       continueButton.setBackground(Color.decode("#CAB8FF"));
       continueButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               loanAppTandCs.TandCGUI(userID);
               frame.dispose();
           }
       });
       panel.add(continueButton);
       frame.setVisible(true);
    
   }
    
}
