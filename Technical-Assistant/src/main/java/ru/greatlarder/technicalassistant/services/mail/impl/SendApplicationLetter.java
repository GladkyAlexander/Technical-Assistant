package ru.greatlarder.technicalassistant.services.mail.impl;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetMailSettings;
import ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings.GetMailSettingsByIdUserSQLite;
import ru.greatlarder.technicalassistant.services.mail.SendAnEmail;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SendApplicationLetter implements SendAnEmail {
    
    @Override
    public String sendEmail(User user, String document, List<String> to, String them, String urlImage) throws MessagingException {
        GetMailSettings getMailSettings = new GetMailSettingsByIdUserSQLite();
        String username = getMailSettings.getMailSettings(user, String.valueOf(user.getId())).getMailMonitoring();
        String password = getMailSettings.getMailSettings(user, String.valueOf(user.getId())).getPasswordMailMonitoring();
        String host = getMailSettings.getMailSettings(user, String.valueOf(user.getId())).getHostMailMonitoring();
        
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", "25");
        
        for (String addressTo : to){
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addressTo));
            mimeMessage.setSubject(them);
            mimeMessage.setSentDate(new Date());
            
            Multipart multipart = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setText(document, "utf-8", "html");
            multipart.addBodyPart(htmlPart);
            MimeBodyPart imgPart = new MimeBodyPart();
            try {
                imgPart.attachFile(urlImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgPart.setContentID("<some-image-cid>");
            multipart.addBodyPart(imgPart);
            
            mimeMessage.setContent(multipart);
            
            Transport.send(mimeMessage);
        }
        return "ok";
    }
}
