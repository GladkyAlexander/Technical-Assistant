package ru.greatlarder.technicalassistant.services.mail;

import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.MailSettings;

import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Mail {

    private final String mail;
    private final String password;
    private final String host;
    private MailSettings mailSettings;

    public Mail(MailSettings mailSettings) {
        this.mailSettings = mailSettings;
        this.mail = mailSettings.getMailMonitoring();
        this.password = mailSettings.getPasswordMailMonitoring();
        this.host = mailSettings.getHostMailMonitoring();
    }

    public List<Affairs> getListOfTasks() {
        List<Affairs> taskList = new ArrayList<>();

        Properties prop = new Properties();
        prop.put("mail.store.protocol", "imaps");
        Session.getDefaultInstance(prop);
        Store store = null;
        Folder inbox = null;

            try {
                store = Session.getInstance(prop).getStore();
                store.connect(host, mail, password);

                inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);
                int count = inbox.getMessageCount();
                Message[] messages = inbox.getMessages(1, count);

                for (Message message : messages) {
                    if (message.getSubject() != null) {
                        String stra = message.getSubject();
                        boolean start = stra.startsWith(mailSettings.getSubjectOfTheLetter());
                        if (start) {
                            try {
                                LoadTask loadTask = new LoadTask();
                                taskList.add(loadTask.getTask(new GetMulti().getText(message)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (MessagingException e) {
             e.printStackTrace();
            } finally {
                if (inbox != null) {
                    try {
                        inbox.close(false);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                if (store != null) {
                    try {
                        store.close();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
        return taskList;
    }

}
