import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class loanAppTandCs {
   static JPanel panel1, panel2;
   static JFrame frame1, frame2;
   static JLabel titleTandC, termsAndCond, endMessage;
   static JCheckBox pleaseTick;
   static JButton finish, back, ok;
   static JTextArea terms;
   static boolean ticked;
   
   public static void TandCGUI(String userID){
       panel1 = new JPanel();
       frame1 = new JFrame("Loan Application");
              
       frame1.setSize(520,560);
       frame1.setLayout(null);
       frame1.setLocationRelativeTo(null);
       frame1.setBackground(Color.decode("#E6E6FA"));
       frame1.setContentPane(panel1);
       panel1.setLayout(null);
       panel1.setBackground(Color.decode("#E6E6FA"));
       frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       titleTandC = new JLabel("Terms and Conditions");
       titleTandC.setBounds(180, 5, 170, 25);
       titleTandC.setFont(new Font("Roboto", Font.BOLD, 15));
       titleTandC.setForeground(Color.decode("#653C94"));
       frame1.add(titleTandC);
       
       termsAndCond = new JLabel("Please thoroughly read through the terms and conditions of this loan before accepting");
       termsAndCond.setBounds(45, 25, 420, 25);
       termsAndCond.setFont(new Font("Roboto", Font.ITALIC, 11));
       termsAndCond.setForeground(Color.decode("#653C94"));
       frame1.add(termsAndCond);
              
       terms = new JTextArea();
       
       terms.setEditable(false);

       terms.setText("Terms and Conditions\n" +
               "Last updated: July 05, 2022\n" +
               "\n" +
               "Please read these terms and conditions carefully before using Our Service.\n" +
               "\n" +
               "Interpretation and Definitions\n" +
               "Interpretation\n" +
               "The words of which the initial letter is capitalized have meanings defined under the following conditions. The following definitions shall have the same meaning regardless of whether they appear in singular or in plural.\n" +
               "\n" +
               "Definitions\n" +
               "For the purposes of these Terms and Conditions:\n" +
               "\n" +
               "Application means the software program provided by the Company downloaded by You on any electronic device, named MicroLoanApp\n" +
               "\n" +
               "Application Store means the digital distribution service operated and developed by Apple Inc. (Apple App Store) or Google Inc. (Google Play Store) in which the Application has been downloaded.\n" +
               "\n" +
               "Affiliate means an entity that controls, is controlled by or is under common control with a party, where \"control\" means ownership of 50% or more of the shares, equity interest or other securities entitled to vote for election of directors or other managing authority.\n" +
               "\n" +
               "Country refers to: United Kingdom\n" +
               "\n" +
               "Company (referred to as either \"the Company\", \"We\", \"Us\" or \"Our\" in this Agreement) refers to MicroLoanApp, 4 Stratheden Road.\n" +
               "\n" +
               "Device means any device that can access the Service such as a computer, a cellphone or a digital tablet.\n" +
               "\n" +
               "Service refers to the Application.\n" +
               "\n" +
               "Terms and Conditions (also referred as \"Terms\") mean these Terms and Conditions that form the entire agreement between You and the Company regarding the use of the Service. This Terms and Conditions agreement has been created with the help of the TermsFeed Terms and Conditions Generator.\n" +
               "\n" +
               "Third-party Social Media Service means any services or content (including data, information, products or services) provided by a third-party that may be displayed, included or made available by the Service.\n" +
               "\n" +
               "You means the individual accessing or using the Service, or the company, or other legal entity on behalf of which such individual is accessing or using the Service, as applicable.\n" +
               "\n" +
               "Acknowledgment\n" +
               "These are the Terms and Conditions governing the use of this Service and the agreement that operates between You and the Company. These Terms and Conditions set out the rights and obligations of all users regarding the use of the Service.\n" +
               "\n" +
               "Your access to and use of the Service is conditioned on Your acceptance of and compliance with these Terms and Conditions. These Terms and Conditions apply to all visitors, users and others who access or use the Service.\n" +
               "\n" +
               "By accessing or using the Service You agree to be bound by these Terms and Conditions. If You disagree with any part of these Terms and Conditions then You may not access the Service.\n" +
               "\n" +
               "You represent that you are over the age of 18. The Company does not permit those under 18 to use the Service.\n" +
               "\n" +
               "Your access to and use of the Service is also conditioned on Your acceptance of and compliance with the Privacy Policy of the Company. Our Privacy Policy describes Our policies and procedures on the collection, use and disclosure of Your personal information when You use the Application or the Website and tells You about Your privacy rights and how the law protects You. Please read Our Privacy Policy carefully before using Our Service.\n" +
               "\n" +
               "Links to Other Websites\n" +
               "Our Service may contain links to third-party web sites or services that are not owned or controlled by the Company.\n" +
               "\n" +
               "The Company has no control over, and assumes no responsibility for, the content, privacy policies, or practices of any third party web sites or services. You further acknowledge and agree that the Company shall not be responsible or liable, directly or indirectly, for any damage or loss caused or alleged to be caused by or in connection with the use of or reliance on any such content, goods or services available on or through any such web sites or services.\n" +
               "\n" +
               "We strongly advise You to read the terms and conditions and privacy policies of any third-party web sites or services that You visit.\n" +
               "\n" +
               "Termination\n" +
               "We may terminate or suspend Your access immediately, without prior notice or liability, for any reason whatsoever, including without limitation if You breach these Terms and Conditions.\n" +
               "\n" +
               "Upon termination, Your right to use the Service will cease immediately.\n" +
               "\n" +
               "Limitation of Liability\n" +
               "Notwithstanding any damages that You might incur, the entire liability of the Company and any of its suppliers under any provision of this Terms and Your exclusive remedy for all of the foregoing shall be limited to the amount actually paid by You through the Service or 100 USD if You haven't purchased anything through the Service.\n" +
               "\n" +
               "To the maximum extent permitted by applicable law, in no event shall the Company or its suppliers be liable for any special, incidental, indirect, or consequential damages whatsoever (including, but not limited to, damages for loss of profits, loss of data or other information, for business interruption, for personal injury, loss of privacy arising out of or in any way related to the use of or inability to use the Service, third-party software and/or third-party hardware used with the Service, or otherwise in connection with any provision of this Terms), even if the Company or any supplier has been advised of the possibility of such damages and even if the remedy fails of its essential purpose.\n" +
               "\n" +
               "Some states do not allow the exclusion of implied warranties or limitation of liability for incidental or consequential damages, which means that some of the above limitations may not apply. In these states, each party's liability will be limited to the greatest extent permitted by law.\n" +
               "\n" +
               "\"AS IS\" and \"AS AVAILABLE\" Disclaimer\n" +
               "The Service is provided to You \"AS IS\" and \"AS AVAILABLE\" and with all faults and defects without warranty of any kind. To the maximum extent permitted under applicable law, the Company, on its own behalf and on behalf of its Affiliates and its and their respective licensors and service providers, expressly disclaims all warranties, whether express, implied, statutory or otherwise, with respect to the Service, including all implied warranties of merchantability, fitness for a particular purpose, title and non-infringement, and warranties that may arise out of course of dealing, course of performance, usage or trade practice. Without limitation to the foregoing, the Company provides no warranty or undertaking, and makes no representation of any kind that the Service will meet Your requirements, achieve any intended results, be compatible or work with any other software, applications, systems or services, operate without interruption, meet any performance or reliability standards or be error free or that any errors or defects can or will be corrected.\n" +
               "\n" +
               "Without limiting the foregoing, neither the Company nor any of the company's provider makes any representation or warranty of any kind, express or implied: (i) as to the operation or availability of the Service, or the information, content, and materials or products included thereon; (ii) that the Service will be uninterrupted or error-free; (iii) as to the accuracy, reliability, or currency of any information or content provided through the Service; or (iv) that the Service, its servers, the content, or e-mails sent from or on behalf of the Company are free of viruses, scripts, trojan horses, worms, malware, timebombs or other harmful components.\n" +
               "\n" +
               "Some jurisdictions do not allow the exclusion of certain types of warranties or limitations on applicable statutory rights of a consumer, so some or all of the above exclusions and limitations may not apply to You. But in such a case the exclusions and limitations set forth in this section shall be applied to the greatest extent enforceable under applicable law.\n" +
               "\n" +
               "Governing Law\n" +
               "The laws of the Country, excluding its conflicts of law rules, shall govern this Terms and Your use of the Service. Your use of the Application may also be subject to other local, state, national, or international laws.\n" +
               "\n" +
               "Disputes Resolution\n" +
               "If You have any concern or dispute about the Service, You agree to first try to resolve the dispute informally by contacting the Company.\n" +
               "\n" +
               "For European Union (EU) Users\n" +
               "If You are a European Union consumer, you will benefit from any mandatory provisions of the law of the country in which you are resident in.\n" +
               "\n" +
               "United States Legal Compliance\n" +
               "You represent and warrant that (i) You are not located in a country that is subject to the United States government embargo, or that has been designated by the United States government as a \"terrorist supporting\" country, and (ii) You are not listed on any United States government list of prohibited or restricted parties.\n" +
               "\n" +
               "Severability and Waiver\n" +
               "Severability\n" +
               "If any provision of these Terms is held to be unenforceable or invalid, such provision will be changed and interpreted to accomplish the objectives of such provision to the greatest extent possible under applicable law and the remaining provisions will continue in full force and effect.\n" +
               "\n" +
               "Waiver\n" +
               "Except as provided herein, the failure to exercise a right or to require performance of an obligation under these Terms shall not effect a party's ability to exercise such right or require such performance at any time thereafter nor shall the waiver of a breach constitute a waiver of any subsequent breach.\n" +
               "\n" +
               "Translation Interpretation\n" +
               "These Terms and Conditions may have been translated if We have made them available to You on our Service. You agree that the original English text shall prevail in the case of a dispute.\n" +
               "\n" +
               "Changes to These Terms and Conditions\n" +
               "We reserve the right, at Our sole discretion, to modify or replace these Terms at any time. If a revision is material We will make reasonable efforts to provide at least 30 days' notice prior to any new terms taking effect. What constitutes a material change will be determined at Our sole discretion.\n" +
               "\n" +
               "By continuing to access or use Our Service after those revisions become effective, You agree to be bound by the revised terms. If You do not agree to the new terms, in whole or in part, please stop using the website and the Service.\n" +
               "\n" +
               "Contact Us\n" +
               "If you have any questions about these Terms and Conditions, You can contact us:\n" +
               "\n" +
               "By email: microloanproject@yahoo.com");

       JScrollPane scroll = new JScrollPane(terms);
       scroll.setBounds(30, 50, 450, 400);
       frame1.add(scroll);

       pleaseTick = new JCheckBox("Please tick here to confirm that you read and agree with the terms and conditions");
       pleaseTick.setBounds(27, 450, 460, 30);
       pleaseTick.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       pleaseTick.setForeground(Color.decode("#47475E"));
       pleaseTick.setBackground(Color.decode("#E6E6FA"));
       pleaseTick.setFont(new Font("Roboto", Font.PLAIN, 12));
       frame1.add(pleaseTick);
       
       back = new JButton("Back");
       back.setBounds(325, 485,75, 25);
       back.setFont(new Font("Roboto", Font.BOLD, 12));
       back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       back.setBackground(Color.decode("#CAB8FF"));
       back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               //previous GUI will open
               loanAppConfirm.confirmGUI(userID);
                frame1.dispose(); 
           }
       });
       frame1.add(back);
                 
       finish = new JButton("Finish");
       finish.setBounds(405, 485, 80, 25);
       finish.setFont(new Font("Roboto", Font.BOLD, 12));
       finish.setBackground(Color.decode("#D3D3D3"));
                    
       pleaseTick.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
             if(pleaseTick.isSelected()){
                ticked = true;
                finish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                finish.setBackground(Color.decode("#CAB8FF"));
            }
            else{
             finish.setBackground(Color.decode("#D3D3D3"));   
            }
            
         }  
       });
       
       finish.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            if(ticked){
                endMessage();
                frame1.dispose();
            }
            else {
                JOptionPane.showMessageDialog(frame1, "Please read and accept the terms and conditions.");
            }
           }
        });
       
       frame1.add(finish);

       frame1.setVisible(true);
   }
   
   public static void endMessage(){
       panel2 = new JPanel();
       frame2 = new JFrame("Finish");
              
       frame2.setSize(255,130);
       panel2.setBackground(Color.decode("#E6E6FA"));
       frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame2.setLocationRelativeTo(null);
       panel2.setLayout(null);
       frame2.add(panel2);
       
       endMessage = new JLabel("<html>Thank you for submitting your application<br/>it has been sent to our team and you will<br/>receive an email from us shortly!<html>");
       endMessage.setBounds(7, 5, 240, 50);
       endMessage.setFont(new Font("Roboto", Font.PLAIN, 12));
       endMessage.setForeground(Color.decode("#653C94"));
       panel2.add(endMessage);
       
       ok = new JButton("Ok");
       ok.setBounds(80, 60,75, 25);
       ok.setFont(new Font("Roboto", Font.BOLD, 12));
       ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       ok.setBackground(Color.decode("#CAB8FF"));
       ok.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
       });
       panel2.add(ok);
       
       frame2.setVisible(true);
       
   }
}
