package ru.greatlarder.technicalassistant.services.mail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.greatlarder.technicalassistant.domain.Letter;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.MailSettingsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.MailSettingsRepositoryImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Properties;

public class HTMLLetter {
    private User user;
    private final String username;
    private final String password;
    private final String host;

    public HTMLLetter(User user) {
        this.user = user;
        MailSettingsRepository mailSettingsRepository = new MailSettingsRepositoryImpl();
        this.username = mailSettingsRepository.getMailSettingsByIdUser(user.getId()).getMailMonitoring();
        this.password = mailSettingsRepository.getMailSettingsByIdUser(user.getId()).getPasswordMailMonitoring();
        this.host = mailSettingsRepository.getMailSettingsByIdUser(user.getId()).getHostMailMonitoring();
    }


    public String sendLetter(Letter letter){

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", "25");

        try {
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(letter.getTo()));
            mimeMessage.setSubject(letter.getTopic());
            try {
                mimeMessage.setContent(getLetter(mimeMessage, letter), "text/html");
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

            Transport.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
        return "ok";
    }

    private String getLetter(MimeMessage mimeMessage, Letter letter) throws MessagingException, IOException, URISyntaxException {

        File file = new File(Objects.requireNonNull(getClass().getResource("/ru/greatlarder/technicalassistant/html/letter_task.html")).toURI());
        Document document = Jsoup.parse(file);
        Element date_event = document.getElementById("p_date_event");
        Element time_start = document.getElementById("p_time_start");
        Element room = document.getElementById("p_room");
        Element event = document.getElementById("p_event");
        Element customer = document.getElementById("p_customer");
        Element seating_arrangements = document.getElementById("p_seating_arrangements");
        Element number_of_participants = document.getElementById("p_number_of_participants");
        Element equipment = document.getElementById("p_equipment");
        Element note = document.getElementById("p_note");

        date_event.text(letter.getDateStart());
        time_start.text(letter.getTimeStart());
        room.text(letter.getRoom());
        event.text(letter.getNameEvent());
        customer.text(letter.getCustomer());
        seating_arrangements.text(letter.getSeatingArrangements());
        number_of_participants.text(letter.getNumberOfParticipants());
        equipment.text(letter.getEquipmentList().toString());
        note.text(letter.getNote());

        return document.html();
    }
}
