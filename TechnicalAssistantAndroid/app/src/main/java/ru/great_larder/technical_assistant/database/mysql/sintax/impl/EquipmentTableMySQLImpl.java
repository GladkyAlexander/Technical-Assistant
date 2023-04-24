package ru.great_larder.technical_assistant.database.mysql.sintax.impl;


import ru.great_larder.technical_assistant.database.mysql.sintax.EquipmentTableMySQL;

public class EquipmentTableMySQLImpl implements EquipmentTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`equipment` (" +
                "                id                                INT , " +
                "                name                              TEXT," +
                "                manufacturer                      TEXT," +
                "                model                             TEXT," +
                "                serialNumber                      TEXT," +
                "                macAddress                        TEXT," +
                "                macAddress1                       TEXT," +
                "                macAddress2                       TEXT," +
                "                macAddress3                       TEXT," +
                "                image                             TEXT," +
                "                room                              TEXT," +
                "                location                          TEXT," +
                "                dateWork                          DATE," +
                "                condition_e                       TEXT," +
                "                company                           TEXT," +
                "                manual                            TEXT," +
                "                login                             TEXT," +
                "                password                          TEXT," +
                "                ipAddress                         TEXT," +
                "                masc                              TEXT," +
                "                gateway                           TEXT," +
                "                timeWorkLampProjector             INT," +
                "                maximumLampOperatingTimeProjector INT," +
                "                frequencyMicrophone               TEXT," +
                "                ipAddressDante                    TEXT," +
                "                mascDante                         TEXT," +
                "                gatewayDante                      TEXT," +
                "                outletNumber                      TEXT," +
                "                portNumberInTheSwitch          INT," +
                "                idNetworkSwitcher               INT," +
                "                port1              INT," +
                "                port2              INT," +
                "                port3              INT," +
                "                port4              INT," +
                "                port5              INT," +
                "                port6              INT," +
                "                port7              INT," +
                "                port8              INT," +
                "                port9              INT," +
                "                port10             INT," +
                "                port11             INT," +
                "                port12             INT," +
                "                port13             INT," +
                "                port14             INT," +
                "                port15             INT," +
                "                port16             INT," +
                "                port17             INT," +
                "                port18             INT," +
                "                port19             INT," +
                "                port20             INT," +
                "                port21             INT," +
                "                port22             INT," +
                "                port23             INT," +
                "                port24             INT," +
                "                port25             INT," +
                "                port26             INT," +
                "                port27             INT," +
                "                port28             INT," +
                "                port29             INT," +
                "                port30             INT," +
                "                port31             INT," +
                "                port32             INT," +
                "                port33             INT," +
                "                port34             INT," +
                "                port35             INT," +
                "                port36             INT," +
                "                port37             INT," +
                "                port38             INT," +
                "                port39             INT," +
                "                port40             INT," +
                "                port41             INT," +
                "                port42             INT," +
                "                port43             INT," +
                "                port44             INT," +
                "                port45             INT," +
                "                port46             INT," +
                "                port47             INT," +
                "                port48             INT," +
                "                operating_system   TEXT," +
                "                diagonal           TEXT," +
                "                type               TEXT," +
                "                user_visibility_status     INT) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`equipment` (" +
                "                id," +
                "                name," +
                "                manufacturer," +
                "                model," +
                "                serialNumber," +
                "                macAddress," +
                "                macAddress1," +
                "                macAddress2," +
                "                macAddress3," +
                "                image," +
                "                room," +
                "                location," +
                "                dateWork," +
                "                condition_e," +
                "                company," +
                "                manual," +
                "                login," +
                "                password," +
                "                ipAddress," +
                "                masc," +
                "                gateway," +
                "                timeWorkLampProjector," +
                "                maximumLampOperatingTimeProjector," +
                "                frequencyMicrophone," +
                "                ipAddressDante," +
                "                mascDante," +
                "                gatewayDante," +
                "                outletNumber," +
                "                portNumberInTheSwitch," +
                "                idNetworkSwitcher," +
                "                port1," +
                "                port2," +
                "                port3," +
                "                port4," +
                "                port5," +
                "                port6," +
                "                port7," +
                "                port8," +
                "                port9," +
                "                port10," +
                "                port11," +
                "                port12," +
                "                port13," +
                "                port14," +
                "                port15," +
                "                port16," +
                "                port17," +
                "                port18," +
                "                port19," +
                "                port20," +
                "                port21," +
                "                port22," +
                "                port23," +
                "                port24," +
                "                port25," +
                "                port26," +
                "                port27," +
                "                port28," +
                "                port29," +
                "                port30," +
                "                port31," +
                "                port32," +
                "                port33," +
                "                port34," +
                "                port35," +
                "                port36," +
                "                port37," +
                "                port38," +
                "                port39," +
                "                port40," +
                "                port41," +
                "                port42," +
                "                port43," +
                "                port44," +
                "                port45," +
                "                port46," +
                "                port47," +
                "                port48," +
                "                operating_system," +
                "                diagonal," +
                "                type," +
                "                user_visibility_status)" +
                "        VALUES (?,?,?,?,?,?,?,?,?,?" +
                "               ,?,?,?,?,?,?,?,?,?,?" +
                "               ,?,?,?,?,?,?,?,?,?,?" +
                "               ,?,?,?,?,?,?,?,?,?,?" +
                "               ,?,?,?,?,?,?,?,?,?,?" +
                "               ,?,?,?,?,?,?,?,?,?,?" +
                "               ,?,?,?,?,?,?,?,?,?,?" +
                "               ,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`equipment` SET " +
                "                id = ?," +
                "                name = ?," +
                "                manufacturer = ?," +
                "                model = ?," +
                "                serialNumber = ?," +
                "                macAddress = ?," +
                "                macAddress1 = ?," +
                "                macAddress2 = ?," +
                "                macAddress3 = ?," +
                "                image = ?," +
                "                room = ?," +
                "                location = ?," +
                "                dateWork = ?," +
                "                condition_e = ?," +
                "                company = ?," +
                "                manual = ?," +
                "                login = ?," +
                "                password = ?," +
                "                ipAddress = ?," +
                "                masc = ?," +
                "                gateway = ?," +
                "                timeWorkLampProjector = ?," +
                "                maximumLampOperatingTimeProjector = ?," +
                "                frequencyMicrophone = ?," +
                "                ipAddressDante = ?," +
                "                mascDante = ?," +
                "                gatewayDante = ?," +
                "                outletNumber = ?," +
                "                portNumberInTheSwitch = ?," +
                "                idNetworkSwitcher = ?," +
                "                port1 = ?," +
                "                port2 = ?," +
                "                port3 = ?," +
                "                port4 = ?," +
                "                port5 = ?," +
                "                port6 = ?," +
                "                port7 = ?," +
                "                port8 = ?," +
                "                port9 = ?," +
                "                port10 = ?," +
                "                port11 = ?," +
                "                port12 = ?," +
                "                port13 = ?," +
                "                port14 = ?," +
                "                port15 = ?," +
                "                port16 = ?," +
                "                port17 = ?," +
                "                port18 = ?," +
                "                port19 = ?," +
                "                port20 = ?," +
                "                port21 = ?," +
                "                port22 = ?," +
                "                port23 = ?," +
                "                port24 = ?," +
                "                port25 = ?," +
                "                port26 = ?," +
                "                port27 = ?," +
                "                port28 = ?," +
                "                port29 = ?," +
                "                port30 = ?," +
                "                port31 = ?," +
                "                port32 = ?," +
                "                port33 = ?," +
                "                port34 = ?," +
                "                port35 = ?," +
                "                port36 = ?," +
                "                port37 = ?," +
                "                port38 = ?," +
                "                port39 = ?," +
                "                port40 = ?," +
                "                port41 = ?," +
                "                port42 = ?," +
                "                port43 = ?," +
                "                port44 = ?," +
                "                port45 = ?," +
                "                port46 = ?," +
                "                port47 = ?," +
                "                port48 = ?," +
                "                operating_system = ?," +
                "                diagonal = ?," +
                "                type = ?," +
                "                user_visibility_status = ? WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`equipment`";
    }
}
