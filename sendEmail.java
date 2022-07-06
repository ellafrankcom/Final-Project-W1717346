import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

    public class sendEmail
    {
        public static void sendEmail(String emailAdd, String emailSubject, String messageDialog)
        {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
            properties.put("mail.smtp.port", 465);
            properties.put("mail.smtp.auth", true);
            properties.put("mail.smtp.ssl.enable", true);

            String username = "microloanproject@yahoo.com";
            String password = "xfwg\n" +
                    "hxlu\n" +
                    "nvur\n" +
                    "uiqe";
            String fromEmailAddress = "microloanproject@yahoo.com";
            String toEmailAddress = emailAdd;
            String subject = emailSubject;
            String emailMessage = messageDialog;
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return  new PasswordAuthentication(username, password);
                }
            });



            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmailAddress));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
                message.setSubject(subject);
                message.setText(emailMessage);
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.mail.yahoo.com", username, password);
                transport.send(message);

            } catch (MessagingException e){
                throw new RuntimeException(e);
            }
        }

    }
