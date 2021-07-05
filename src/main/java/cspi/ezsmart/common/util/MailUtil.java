 package cspi.ezsmart.common.util;
 
 import cspi.ezsmart.common.util.Utilities;
 import java.io.File;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.Date;
 import java.util.List;
 import java.util.Properties;
 import javax.activation.DataHandler;
 import javax.activation.FileDataSource;
 import javax.mail.Address;
 import javax.mail.Authenticator;
 import javax.mail.BodyPart;
 import javax.mail.Message;
 import javax.mail.Multipart;
 import javax.mail.Session;
 import javax.mail.internet.InternetAddress;
 import javax.mail.internet.MimeBodyPart;
 import javax.mail.internet.MimeMessage;
 import javax.mail.internet.MimeMultipart;
 import javax.mail.internet.MimeUtility;
 
 public class MailUtil {
   public static boolean sendMail(String emailTo, String subject, String body, List<String> attFiles, List<String> dispnames) throws Exception {
     boolean ssl = Utilities.getPropertyBoolean("smtp.ssl");
     String host = Utilities.getProperty("smtp.host");
     int port = Utilities.getPropertyInt("smtp.port");
     
     String userTmp = Utilities.getProperty("smtp.user");
     String user = Utilities.decrypt(userTmp);
     
     String pwdTmp = Utilities.getProperty("smtp.pwd");
     String password = Utilities.decrypt(pwdTmp);
     
     String nameFrom = Utilities.getProperty("smtp.sender.name");
     String emailFrom = Utilities.getProperty("smtp.sender.email");
     Properties props = new Properties();
     props.put("mail.transport.protocol", "smtp");
     props.put("mail.smtp.host", host);
     props.put("mail.smtp.port", Integer.valueOf(port));
     props.put("mail.smtp.auth", "true");
     if (ssl) {
       props.put("mail.smtp.ssl.enable", "true");
       props.put("mail.smtp.ssl.trust", host);
       
       props.put("mail.smtp.socketFactory.port", "465");
       props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
       props.put("mail.smtp.socketFactory.fallback", "false");
     } 
     Session session = null;
     if (Utilities.isEmpty(user) || Utilities.isEmpty(password)) {
       session = Session.getInstance(props);
     } else {
//       session = Session.getDefaultInstance(props, (Authenticator)new Object(user, password));
     } 
 
 
 
 
 
     
     MimeMultipart mimeMultipart = new MimeMultipart();
     
     MimeBodyPart mTextPart = new MimeBodyPart();
     mTextPart.setText(body, "UTF-8", "html");
     mimeMultipart.addBodyPart((BodyPart)mTextPart);
     for (int i = 0; attFiles != null && i < attFiles.size(); i++) {
       String fileName = attFiles.get(i);
       File file = new File(fileName);
       String name = file.getName();
       if (dispnames != null && dispnames.size() > i) {
         name = dispnames.get(i);
       }
       MimeBodyPart bodyPart = new MimeBodyPart();
       bodyPart.setFileName(MimeUtility.encodeText(name, "UTF-8", "B"));
       bodyPart.setDescription(name, "UTF-8");
       FileDataSource fs = new FileDataSource(file);
       DataHandler dh = new DataHandler(fs);
       bodyPart.setDataHandler(dh);
       Path path = Paths.get(file.getCanonicalPath(), new String[0]);
       String mime = Files.probeContentType(path);
       bodyPart.addHeader("Content-Type", mime);
       mimeMultipart.addBodyPart((BodyPart)bodyPart);
     } 
     MimeMessage msg = new MimeMessage(session);
     
     InternetAddress fromAddress = new InternetAddress(emailFrom, nameFrom);
     msg.setFrom((Address)fromAddress);
     msg.setSender((Address)fromAddress);
     InternetAddress toAddress = new InternetAddress(emailTo);
     msg.setRecipient(Message.RecipientType.TO, (Address)toAddress);
     msg.setSubject(subject);
     
     msg.setContent((Multipart)mimeMultipart);
     msg.setSentDate(new Date());
//     Transport.send((Message)msg);
     return true;
   }
 }
