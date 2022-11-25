package ru.greatlarder.technicalassistant.services.database.sqlite.repository;


import ru.greatlarder.technicalassistant.domain.Port;

public interface PortRepository {
    Port setPort(Port port);
    Port getPort(int idPort);
    Port change(int idPort, String column, Object value);

}
