package ru.greatlarder.technicalassistant.services.mail.impl;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetMailSettings;
import ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings.GetMailSettingsByIdUserSQLite;
import ru.greatlarder.technicalassistant.services.mail.SendAnEmail;

import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SendApplicationLetter implements SendAnEmail {
    
    @Override
    public void sendEmail(User user, String document, List<String> to, String them, String urlImage) throws MessagingException {
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
            //mimeMessage.setContent(document, "text/html; charset=UTF-8");
            
            Multipart multi = new MimeMultipart();
            BodyPart html = new MimeBodyPart();
            html.setContent(document, "text/html; charset=utf-8");
            multi.addBodyPart(html);
            mimeMessage.setContent(multi);
            
            Transport.send(mimeMessage);
        }
    }
}
