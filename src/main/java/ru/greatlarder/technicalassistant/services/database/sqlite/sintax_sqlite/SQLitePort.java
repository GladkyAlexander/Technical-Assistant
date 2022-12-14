package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLitePort {
    String CREATE_TABLE_PORT = "CREATE TABLE if not exists 'port' (" +
            "    'id'                              INTEGER  PRIMARY KEY AUTOINCREMENT, " +
            "    'idNetworkSwitch'    INTEGER," +
            "    'port1'              INTEGER," +
            "    'port2'              INTEGER," +
            "    'port3'              INTEGER," +
            "    'port4'              INTEGER," +
            "    'port5'              INTEGER," +
            "    'port6'              INTEGER," +
            "    'port7'              INTEGER," +
            "    'port8'              INTEGER," +
            "    'port9'              INTEGER," +
            "    'port10'             INTEGER," +
            "    'port11'             INTEGER," +
            "    'port12'             INTEGER," +
            "    'port13'             INTEGER," +
            "    'port14'             INTEGER," +
            "    'port15'             INTEGER," +
            "    'port16'             INTEGER," +
            "    'port17'             INTEGER," +
            "    'port18'             INTEGER," +
            "    'port19'             INTEGER," +
            "    'port20'             INTEGER," +
            "    'port21'             INTEGER," +
            "    'port22'             INTEGER," +
            "    'port23'             INTEGER," +
            "    'port24'             INTEGER," +
            "    'port25'             INTEGER," +
            "    'port26'             INTEGER," +
            "    'port27'             INTEGER," +
            "    'port28'             INTEGER," +
            "    'port29'             INTEGER," +
            "    'port30'             INTEGER," +
            "    'port31'             INTEGER," +
            "    'port32'             INTEGER," +
            "    'port33'             INTEGER," +
            "    'port34'             INTEGER," +
            "    'port35'             INTEGER," +
            "    'port36'             INTEGER," +
            "    'port37'             INTEGER," +
            "    'port38'             INTEGER," +
            "    'port39'             INTEGER," +
            "    'port40'             INTEGER," +
            "    'port41'             INTEGER," +
            "    'port42'             INTEGER," +
            "    'port43'             INTEGER," +
            "    'port44'             INTEGER," +
            "    'port45'             INTEGER," +
            "    'port46'             INTEGER," +
            "    'port47'             INTEGER," +
            "    'port48'             INTEGER);";

    String INSERT_TABLE_PORT = "INSERT INTO port ( " +
            "    idNetworkSwitch," +
            "    port1," +
            "    port2," +
            "    port3," +
            "    port4," +
            "    port5," +
            "    port6," +
            "    port7," +
            "    port8," +
            "    port9," +
            "    port10," +
            "    port11," +
            "    port12," +
            "    port13," +
            "    port14," +
            "    port15," +
            "    port16," +
            "    port17," +
            "    port18," +
            "    port19," +
            "    port20," +
            "    port21," +
            "    port22," +
            "    port23," +
            "    port24," +
            "    port25," +
            "    port26," +
            "    port27," +
            "    port28," +
            "    port29," +
            "    port30," +
            "    port31," +
            "    port32," +
            "    port33," +
            "    port34," +
            "    port35," +
            "    port36," +
            "    port37," +
            "    port38," +
            "    port39," +
            "    port40," +
            "    port41," +
            "    port42," +
            "    port43," +
            "    port44," +
            "    port45," +
            "    port46," +
            "    port47," +
            "    port48) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?" +
            ",?,?,?,?,?,?,?,?,?,?" +
            ",?,?,?,?,?,?,?,?,?,?" +
            ",?,?,?,?,?,?,?,?,?,?" +
            ",?,?,?,?,?,?,?,?,?)";

    String READ_TABLE_PORT = "SELECT * FROM port";
}
