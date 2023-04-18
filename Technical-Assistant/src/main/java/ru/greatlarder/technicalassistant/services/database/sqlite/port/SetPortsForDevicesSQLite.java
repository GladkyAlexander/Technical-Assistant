package ru.greatlarder.technicalassistant.services.database.sqlite.port;

import ru.greatlarder.technicalassistant.domain.PortsForDevices;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetPortsForDevices;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLitePort;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class SetPortsForDevicesSQLite implements SetPortsForDevices {
    @Override
    public Integer setPortsForDevices(User user, String nameCompany, PortsForDevices port) {
        Integer idPort = null;
        createPortTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLitePort.INSERT_TABLE_PORT, Statement.RETURN_GENERATED_KEYS);
            cf.setInt(1, port.getIdNetworkSwitch());
            if(port.getPort1() != null) {cf.setInt(2, port.getPort1().getId());}
            if(port.getPort2() != null) {cf.setInt(3, port.getPort2().getId());}
            if(port.getPort3() != null) {cf.setInt(4, port.getPort3().getId());}
            if(port.getPort4() != null) {cf.setInt(5, port.getPort4().getId());}
            if(port.getPort5() != null) {cf.setInt(6, port.getPort5().getId());}
            if(port.getPort6() != null) {cf.setInt(7, port.getPort6().getId());}
            if(port.getPort7() != null) {cf.setInt(8, port.getPort7().getId());}
            if(port.getPort8() != null) {cf.setInt(9, port.getPort8().getId());}
            if(port.getPort9() != null) {cf.setInt(10, port.getPort9().getId());}
            if(port.getPort10() != null) {cf.setInt(11, port.getPort10().getId());}
            if(port.getPort11() != null) {cf.setInt(12, port.getPort11().getId());}
            if(port.getPort12() != null) {cf.setInt(13, port.getPort12().getId());}
            if(port.getPort13() != null) {cf.setInt(14, port.getPort13().getId());}
            if(port.getPort14() != null) {cf.setInt(15, port.getPort14().getId());}
            if(port.getPort15() != null) {cf.setInt(16, port.getPort15().getId());}
            if(port.getPort16() != null) {cf.setInt(17, port.getPort16().getId());}
            if(port.getPort17() != null) {cf.setInt(18, port.getPort17().getId());}
            if(port.getPort18() != null) {cf.setInt(19, port.getPort18().getId());}
            if(port.getPort19() != null) {cf.setInt(20, port.getPort19().getId());}
            if(port.getPort20() != null) {cf.setInt(21, port.getPort20().getId());}
            if(port.getPort21() != null) {cf.setInt(22, port.getPort21().getId());}
            if(port.getPort22() != null) {cf.setInt(23, port.getPort22().getId());}
            if(port.getPort23() != null) {cf.setInt(24, port.getPort23().getId());}
            if(port.getPort24() != null) {cf.setInt(25, port.getPort24().getId());}
            if(port.getPort25() != null) {cf.setInt(26, port.getPort25().getId());}
            if(port.getPort26() != null) {cf.setInt(27, port.getPort26().getId());}
            if(port.getPort27() != null) {cf.setInt(28, port.getPort27().getId());}
            if(port.getPort28() != null) {cf.setInt(29, port.getPort28().getId());}
            if(port.getPort29() != null) {cf.setInt(30, port.getPort29().getId());}
            if(port.getPort30() != null) {cf.setInt(31, port.getPort30().getId());}
            if(port.getPort31() != null) {cf.setInt(32, port.getPort31().getId());}
            if(port.getPort32() != null) {cf.setInt(33, port.getPort32().getId());}
            if(port.getPort33() != null) {cf.setInt(34, port.getPort33().getId());}
            if(port.getPort34() != null) {cf.setInt(35, port.getPort34().getId());}
            if(port.getPort35() != null) {cf.setInt(36, port.getPort35().getId());}
            if(port.getPort36() != null) {cf.setInt(37, port.getPort36().getId());}
            if(port.getPort37() != null) {cf.setInt(38, port.getPort37().getId());}
            if(port.getPort38() != null) {cf.setInt(39, port.getPort38().getId());}
            if(port.getPort39() != null) {cf.setInt(40, port.getPort39().getId());}
            if(port.getPort40() != null) {cf.setInt(41, port.getPort40().getId());}
            if(port.getPort41() != null) {cf.setInt(42, port.getPort41().getId());}
            if(port.getPort42() != null) {cf.setInt(43, port.getPort42().getId());}
            if(port.getPort43() != null) {cf.setInt(44, port.getPort43().getId());}
            if(port.getPort44() != null) {cf.setInt(45, port.getPort44().getId());}
            if(port.getPort45() != null) {cf.setInt(46, port.getPort45().getId());}
            if(port.getPort46() != null) {cf.setInt(47, port.getPort46().getId());}
            if(port.getPort47() != null) {cf.setInt(48, port.getPort47().getId());}
            if(port.getPort48() != null) {cf.setInt(49, port.getPort48().getId());}

            if(cf.executeUpdate() > 0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    idPort = rs.getInt(1);
                }
            }

            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return idPort;
    }
}
