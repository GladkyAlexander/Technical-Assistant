package ru.greatlarder.technicalassistant.services.mail;

import jakarta.mail.MessagingException;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface SendAnEmail {
    void sendEmail(User user, String doc, List<String> to, String them, String urlImage) throws MessagingException;
}
