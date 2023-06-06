package ru.greatlarder.technicalassistant.services.mail;

import ru.greatlarder.technicalassistant.domain.Servers;

import java.util.List;

public interface GetHostAndPortSSL {
    Servers getServersByName(String nameServer);
    List<String> getListServersName();
    String getServer(String nameServers, String protocol);
    Integer getPortSSL(String nameServers, String protocol);
    
}
