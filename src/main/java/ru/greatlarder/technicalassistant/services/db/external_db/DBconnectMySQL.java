package ru.greatlarder.technicalassistant.services.db.external_db;

import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class DBconnectMySQL {
    public static String name_database = GlobalLinkMainController.getMainController().user.getNameDB();
    public static Connection connectionExternalDB;
    public static Statement statementExternalDB;
    public static ResultSet resultSetExternalDB;
    public static String CREATE_COMPANY_DB = "CREATE TABLE if not exists`" + name_database + "`.`company` ( id int ,nameCompany text, address text, curatorLastName text, curatorFirstName text," +
            "            phoneCurator text, mailCurator text," +
            "            websiteCompany text, logoCompany text, managerLastName text," +
            "            managerFirstName text, phoneManager text, mailManager text, " +
            "            engineerLastName text, engineerFirstName text, phoneEngineer text," +
            "                                           mailEngineer text) ENGINE = InnoDB";

    public static String INSERT_COMPANY_DB = "INSERT INTO `" + name_database + "`.`company` (" +
            " id," +
            " nameCompany," +
            " address," +
            " curatorLastName," +
            " curatorFirstName," +
            " phoneCurator," +
            " mailCurator," +
            " websiteCompany," +
            " logoCompany," +
            " managerLastName," +
            " managerFirstName," +
            " phoneManager," +
            " mailManager," +
            " engineerLastName," +
            " engineerFirstName," +
            " phoneEngineer," +
            " mailEngineer) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static String UPDATE_COMPANY_DB = "UPDATE `" + name_database + "`.`company` SET " +
            " id = ?," +
            " nameCompany = ?," +
            " address = ?," +
            " curatorLastName = ?," +
            " curatorFirstName = ?," +
            " phoneCurator = ?," +
            " mailCurator = ?," +
            " websiteCompany = ?," +
            " logoCompany = ?," +
            " managerLastName = ?," +
            " managerFirstName = ?," +
            " phoneManager = ?," +
            " mailManager = ?," +
            " engineerLastName = ?," +
            " engineerFirstName = ?," +
            " phoneEngineer = ?," +
            " mailEngineer = ? WHERE id = ?";

    public static String READ_TABLE_COMPANY = "SELECT * FROM `" + name_database + "`.`company`";
    public static String CLEAR_TABLE_COMPANY = "DELETE FROM `" + name_database + "`.`company`";

    public static String CREATE_EQUIPMENT_DB = "CREATE TABLE if not exists`" + name_database + "`.`equipment` (" +
            "                id                                INT , " +
            "                name                              TEXT," +
            "                manufacturer                      TEXT," +
            "                model                             TEXT," +
            "                serialNumber                      TEXT," +
            "                macAddress                        TEXT," +
            "                macAddress1                        TEXT," +
            "                macAddress2                        TEXT," +
            "                macAddress3                        TEXT," +
            "                image                             TEXT," +
            "                room                              TEXT," +
            "                location                          TEXT," +
            "                dateWork                          DATE," +
            "                condition_equipment               TEXT," +
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
            "                diagonal           TEXT) ENGINE = InnoDB";

    public static String INSERT_EQUIPMENT_DB = "INSERT INTO `" + name_database + "`.`equipment` (" +
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
            "                condition_equipment," +
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
            "                diagonal)" +
            "            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
            "               ,?,?,?,?,?,?,?,?,?,?" +
            "               ,?,?,?,?,?,?,?,?,?,?" +
            "               ,?,?,?,?,?,?,?,?,?,?" +
            "               ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static String UPDATE_EQUIPMENT_DB = "UPDATE `" + name_database + "`.`equipment` SET " +
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
            "                condition_equipment = ?," +
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
            "                diagonal = ?  WHERE id = ?";
    public static String READ_TABLE_EQUIPMENT = "SELECT * FROM `" + name_database + "`.`equipment`";
    public static String CLEAR_TABLE_EQUIPMENT = "DELETE FROM `" + name_database + "`.`equipment`";



    public static void getDBconnectMySQL() {

        String server = GlobalLinkMainController.getMainController().user.getServer();
        String port = GlobalLinkMainController.getMainController().user.getPort();
        String password = GlobalLinkMainController.getMainController().user.getPasswordDB();
        String nameDB = GlobalLinkMainController.getMainController().user.getNameDB();

        connectionExternalDB = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connectionExternalDB = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port ,  nameDB , password);
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public static void close(){

            try {
                if(resultSetExternalDB != null) resultSetExternalDB.close();
                if(statementExternalDB != null) statementExternalDB.close();
                if (connectionExternalDB != null) connectionExternalDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    public static void createCompanyTableExternalDB(){
        getDBconnectMySQL();
        try {
            statementExternalDB = connectionExternalDB.createStatement();
            statementExternalDB.execute(CREATE_COMPANY_DB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createEquipmentTableExternalDB(){
        getDBconnectMySQL();
        try {

            statementExternalDB = connectionExternalDB.createStatement();
            statementExternalDB.execute(CREATE_EQUIPMENT_DB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
