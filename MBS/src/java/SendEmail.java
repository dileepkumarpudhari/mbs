
/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DileepKumar
 */

import java.io.UnsupportedEncodingException;  
import java.util.Properties;  
import java.util.logging.Level;  
import java.util.logging.Logger;  
import javax.mail.Authenticator;  
import javax.mail.Message; 
import javax.mail.Multipart;
import javax.mail.MessagingException; 
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage; 
import javax.mail.internet.MimeMultipart;


public class SendEmail {
       
    private final String SMTP_HOST = "smtp.gmail.com";  
    private final String FROM_ADDRESS = "mbsmanager1@gmail.com";  
    private final String PASSWORD = "Dimpu@13";  
    private final String FROM_NAME = "Dileep Kumar";  
    private String email;
    private String password;
    private String hash;
    private String fname;
    private String filepath;
  
    public void sendMail(String mail, String Password, String Hash, String Fname) { 
        
        email= mail;
        password= Password;
        hash=Hash;
        fname= Fname;
        
        try {  
            Properties props = new Properties();  
            props.put("mail.smtp.host", SMTP_HOST);  
            props.put("mail.smtp.auth", "true");  
            props.put("mail.debug", "false");  
            props.put("mail.smtp.ssl.enable", "true");  
  
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);
                }
            });
          
            
            Message msg = new MimeMessage(session); 
            msg.setFrom(new InternetAddress(FROM_ADDRESS));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            
            msg.setSubject("(NNTR) MBS: Employee Password and Activation Link");
            String message= "Hi "+fname+",\n\n"+"Welcome to MBS, below are your password and Activation link details: \n" + "Username :"+email+ "\n" + "Password :" +password + "\nclick on below Link to activate your account \n" + "http://localhost:7001/MBS/ActivateAccountServlet?key1="+email+ "&key2="+hash+"\n \n"+ "Regards, \nMBS Manager";
            msg.setText(message);
//            msg.setText("Welcome to MBS, below are your password and Activation link details: ");
//            msg.setText("Username :"+email);
//            msg.setText("Passowrd :" +password);
//            msg.setText("click on below Link to activate your account");
//            msg.setText("http://localhost:7001/MBS/ActivateAccountServlet?key1="+email+ "&key2="+hash);
            
        //    String message= "Hi "+fname+",\n"+"Welcome to MBS, below are your password and Activation link details: \n" + "Username :"+email+ "\n" + "Passowrd :" +password + "\nclick on below Link to activate your account \n" + "http://localhost:7001/MBS/ActivateAccountServlet?key1="+email+ "&key2="+hash+"\n \n"+ "Regards, \nMBS Manager"; 
            
        //    msg.setContent(message, "plain/text");
            
            System.out.println(message);
            Transport.send(msg);
            System.out.println("Accept message sent");
  
           // return "success";
           // } 
            
        }
        catch(Exception e)
        {
            System.out.println("Error in Sendmail "+e);
        }
  
  
             
        // return "error";    
    }  
  
    class SocialAuth extends Authenticator {  
  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
  
            return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);  
  
        }  
    }  
    
    public void sendRejectMail(String mail,String Fname) { 
        
        email= mail;
        
        
        fname= Fname;
        
        try {  
            Properties props = new Properties();  
            props.put("mail.smtp.host", SMTP_HOST);  
            props.put("mail.smtp.auth", "true");  
            props.put("mail.debug", "false");  
            props.put("mail.smtp.ssl.enable", "true");  
  
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);
                }
            });
          
            
            Message msg = new MimeMessage(session); 
            msg.setFrom(new InternetAddress(FROM_ADDRESS));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            
            msg.setSubject("(NNTR) MBS: Employee Rejected");
            
//            msg.setText("Hi "+fname +" ,");
//            msg.setText("Welcome to MBS, below are your password and Activation link details: ");
//            msg.setText("Username :"+email);
//            msg.setText("Passowrd :" +password);
//            msg.setText("click on below Link to activate your account");
//            msg.setText("http://localhost:7001/MBS/ActivateAccountServlet?key1="+email+ "&key2="+hash);
            
//            String message= "Hi "+fname+"\n"+"Welcome to MBS, below are your password and Activation link details: \n" + "Username :"+email+ "\n" + "Passowrd :" +password + "\nclick on below Link to activate your account \n" + "http://localhost:7001/MBS/ActivateAccountServlet?key1="+email+ "&key2="+hash+"\n \n"+ "Regards, \n MBS Manager"; 
            
            String rmsg= "Hi "+fname+",\n\n"+ "Unfortunately we are moving with aonther person, but will have your details with us and let you know if we have any further jobs.\n\nRegards,\nMBS Manager";

            msg.setContent(rmsg, "plain/text");
            
            System.out.println(rmsg);
            Transport.send(msg);
            System.out.println("Reject message sent");
  
           // return "success";
           // } 
            
        }
        catch(Exception e)
        {
            System.out.println("Error in SendRejectEmail "+e);
        }
  
  
             
        // return "error";    
    } 
    
     public String sendPaySlip(String mail,String Fname) { 
        
         
         System.out.println("Inside Payslip mail");
        email= mail;
        fname= Fname;
        String filepath= "C:\\Users\\dilee\\Documents\\NetBeansProjects\\MBS\\web\\Payslips\\"+fname+".txt";
        
        System.out.println(filepath);
        
        try {  
            Properties props = new Properties();  
            props.put("mail.smtp.host", SMTP_HOST);  
            props.put("mail.smtp.auth", "true");  
            props.put("mail.debug", "false");  
            props.put("mail.smtp.ssl.enable", "true");  
  
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);
                }
            });
          
            
            Message msg = new MimeMessage(session); 
            msg.setFrom(new InternetAddress(FROM_ADDRESS));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            
            msg.setSubject("(NNTR) MBS: Employee Paylsip");
            String rmsg= "Hi "+fname+",\n\n"+ "\n Attached is your Payslip. Please meet me in case of any issues"+"\n\n Regards,\nMBS Manager";
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(rmsg, "text/html");
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            
            System.out.println(filepath);
            
            MimeBodyPart attachPart = new MimeBodyPart();
                try
                {
                    attachPart.attachFile(filepath);
                    
                }
                catch(Exception e)
                {
                    System.out.println("Error from sending attachments "+e);
                }
                multipart.addBodyPart(attachPart);
            
         
            msg.setContent(multipart);
            //System.out.println(rmsg);
            Transport.send(msg);
            System.out.println("Payslip with attachment, message sent");
  
            return "success";
           // } 
            
        }
        catch(Exception e)
        {
            System.out.println("Error in SendPayslip "+e);
        }
  
  
             
         return "error";    
    } 
    
    
    
    
    
    
}  

