package ru.greatlarder.technicalassistant.services.mail.impl;

import ru.greatlarder.technicalassistant.domain.Servers;
import ru.greatlarder.technicalassistant.services.mail.GetHostAndPortSSL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetHostAndPortSSLRuImpl implements GetHostAndPortSSL {
    
    @Override
    public Servers getServersByName(String nameServer) {
        return getListNameHost().get(nameServer);
    }
    
    @Override
    public List<String> getListServersName() {
        List<String> name = new ArrayList<>();
        for (Map.Entry<String, Servers> entry : getListNameHost().entrySet()) {
            name.add(entry.getValue().getNameServer());
        }
        return name;
    }
    
    @Override
    public String getServer(String nameServers, String protocol) {
        for (Map.Entry<String, Servers> entry : getListNameHost().entrySet()) {
            if(entry.getKey().equals(nameServers)) {
                if (protocol.equals("POP3")){
                    return entry.getValue().getServerPOP();
                }
                if(protocol.equals("IMAP")){
                    return entry.getValue().getServerIMAP();
                }
                if(protocol.equals("SMTP")){
                    return entry.getValue().getServerSMTP();
                }
            }
        }
        return null;
    }
    
    @Override
    public Integer getPortSSL(String nameServers, String protocol) {
        for (Map.Entry<String, Servers> entry : getListNameHost().entrySet()) {
            if(entry.getKey().equals(nameServers)) {
                if (protocol.equals("POP3")){
                    return entry.getValue().getPortSSLPOP();
                }
                if(protocol.equals("IMAP")){
                    return entry.getValue().getPortSSLIMAP();
                }
                if(protocol.equals("SMTP")){
                    return entry.getValue().getPortSMTP();
                }
            }
        }
        return null;
    }
    private Map<String, Servers> getListNameHost() {
        
        Map<String, Servers> map = new HashMap<>();
        map.put("Мастерхост", new Servers("Мастерхост", "pop.masterhost.ru", "imap.masterhost.ru", "smtp.masterhost.ru", 995, 993, 465));
        map.put("REG.RU", new Servers("REG.RU", "mail.hosting.reg.ru", "mail.hosting.reg.ru", "mail.hosting.reg.ru", 995, 993, 465));
        map.put("Timeweb", new Servers("Timeweb", "pop3.timeweb.ru", "imap.timeweb.ru", "smtp.timeweb.ru", 995, 993, 465));
        map.put("beget.com", new Servers("beget.com", "pop3.beget.com", "imap.beget.com", "smtp.beget.com", 995, 993, 465));
        map.put("nic.ru", new Servers("nic.ru", "mail.nic.ru", "mail.nic.ru", "mail.nic.ru", 995, 993, 465));
        map.put("ДЖИНО", new Servers("ДЖИНО", "mail.jino.ru", "mail.jino.ru", "smtp.jino.ru", null, 993, 465));
        map.put("Яндекс Почта", new Servers("Яндекс Почта", "pop.yandex.ru", "imap.yandex.ru", "smtp.yandex.ru", 995, 993, 465));
        map.put("Mail.ru", new Servers("Mail.ru", "pop.mail.ru", "imap.mail.ru", "smtp.mail.ru", 995, 993, 465));
        map.put("Gmail", new Servers("Gmail", "pop.gmail.com", "imap.gmail.com", "smtp.gmail.com", 995, 993, 465));
        map.put("Рамблер", new Servers("Рамблер", "pop.rambler.ru", "imap.rambler.ru", "smtp.rambler.ru", 995, 993, 465));
        map.put("ICloud", new Servers("ICloud", null, "imap.mail.me.com", "smtp.mail.me.com", null, 993, 587));
        map.put("Yahoo", new Servers("Yahoo", "pop.mail.yahoo.com", "imap.mail.yahoo.com", "smtp.mail.yahoo.com", 995, 993, 465));
        
        return map;
    }
}
