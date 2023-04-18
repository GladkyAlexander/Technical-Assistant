package ru.greatlarder.technicalassistant.services.database.sqlite.port;

import ru.greatlarder.technicalassistant.domain.PortsForDevices;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdatePortsForDevices;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLitePort;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class UpdatePortsForDevicesSQLite implements UpdatePortsForDevices {
    @Override
    public void updatePortsForDevices(User user, String nameCompany, PortsForDevices portsForDevices, Integer idPortsForDevices) {
        createPortTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLitePort.UPDATE_TABLE_PORT);
            if(portsForDevices.getPort1() != null) {cf.setInt(1, portsForDevices.getPort1().getId());}
            if(portsForDevices.getPort2() != null) {cf.setInt(2, portsForDevices.getPort2().getId());}
            if(portsForDevices.getPort3() != null) {cf.setInt(3, portsForDevices.getPort3().getId());}
            if(portsForDevices.getPort4() != null) {cf.setInt(4, portsForDevices.getPort4().getId());}
            if(portsForDevices.getPort5() != null) {cf.setInt(5, portsForDevices.getPort5().getId());}
            if(portsForDevices.getPort6() != null) {cf.setInt(6, portsForDevices.getPort6().getId());}
            if(portsForDevices.getPort7() != null) {cf.setInt(7, portsForDevices.getPort7().getId());}
            if(portsForDevices.getPort8() != null) {cf.setInt(8, portsForDevices.getPort8().getId());}
            if(portsForDevices.getPort9() != null) {cf.setInt(9, portsForDevices.getPort9().getId());}
            if(portsForDevices.getPort10() != null) {cf.setInt(10, portsForDevices.getPort10().getId());}
            if(portsForDevices.getPort11() != null) {cf.setInt(11, portsForDevices.getPort11().getId());}
            if(portsForDevices.getPort12() != null) {cf.setInt(12, portsForDevices.getPort12().getId());}
            if(portsForDevices.getPort13() != null) {cf.setInt(13, portsForDevices.getPort13().getId());}
            if(portsForDevices.getPort14() != null) {cf.setInt(14, portsForDevices.getPort14().getId());}
            if(portsForDevices.getPort15() != null) {cf.setInt(15, portsForDevices.getPort15().getId());}
            if(portsForDevices.getPort16() != null) {cf.setInt(16, portsForDevices.getPort16().getId());}
            if(portsForDevices.getPort17() != null) {cf.setInt(17, portsForDevices.getPort17().getId());}
            if(portsForDevices.getPort18() != null) {cf.setInt(18, portsForDevices.getPort18().getId());}
            if(portsForDevices.getPort19() != null) {cf.setInt(19, portsForDevices.getPort19().getId());}
            if(portsForDevices.getPort20() != null) {cf.setInt(20, portsForDevices.getPort20().getId());}
            if(portsForDevices.getPort21() != null) {cf.setInt(21, portsForDevices.getPort21().getId());}
            if(portsForDevices.getPort22() != null) {cf.setInt(22, portsForDevices.getPort22().getId());}
            if(portsForDevices.getPort23() != null) {cf.setInt(23, portsForDevices.getPort23().getId());}
            if(portsForDevices.getPort24() != null) {cf.setInt(24, portsForDevices.getPort24().getId());}
            if(portsForDevices.getPort25() != null) {cf.setInt(25, portsForDevices.getPort25().getId());}
            if(portsForDevices.getPort26() != null) {cf.setInt(26, portsForDevices.getPort26().getId());}
            if(portsForDevices.getPort27() != null) {cf.setInt(27, portsForDevices.getPort27().getId());}
            if(portsForDevices.getPort28() != null) {cf.setInt(28, portsForDevices.getPort28().getId());}
            if(portsForDevices.getPort29() != null) {cf.setInt(29, portsForDevices.getPort29().getId());}
            if(portsForDevices.getPort30() != null) {cf.setInt(30, portsForDevices.getPort30().getId());}
            if(portsForDevices.getPort31() != null) {cf.setInt(31, portsForDevices.getPort31().getId());}
            if(portsForDevices.getPort32() != null) {cf.setInt(32, portsForDevices.getPort32().getId());}
            if(portsForDevices.getPort33() != null) {cf.setInt(33, portsForDevices.getPort33().getId());}
            if(portsForDevices.getPort34() != null) {cf.setInt(34, portsForDevices.getPort34().getId());}
            if(portsForDevices.getPort35() != null) {cf.setInt(35, portsForDevices.getPort35().getId());}
            if(portsForDevices.getPort36() != null) {cf.setInt(36, portsForDevices.getPort36().getId());}
            if(portsForDevices.getPort37() != null) {cf.setInt(37, portsForDevices.getPort37().getId());}
            if(portsForDevices.getPort38() != null) {cf.setInt(38, portsForDevices.getPort38().getId());}
            if(portsForDevices.getPort39() != null) {cf.setInt(39, portsForDevices.getPort39().getId());}
            if(portsForDevices.getPort40() != null) {cf.setInt(40, portsForDevices.getPort40().getId());}
            if(portsForDevices.getPort41() != null) {cf.setInt(41, portsForDevices.getPort41().getId());}
            if(portsForDevices.getPort42() != null) {cf.setInt(42, portsForDevices.getPort42().getId());}
            if(portsForDevices.getPort43() != null) {cf.setInt(43, portsForDevices.getPort43().getId());}
            if(portsForDevices.getPort44() != null) {cf.setInt(44, portsForDevices.getPort44().getId());}
            if(portsForDevices.getPort45() != null) {cf.setInt(45, portsForDevices.getPort45().getId());}
            if(portsForDevices.getPort46() != null) {cf.setInt(46, portsForDevices.getPort46().getId());}
            if(portsForDevices.getPort47() != null) {cf.setInt(47, portsForDevices.getPort47().getId());}
            if(portsForDevices.getPort48() != null) {cf.setInt(48, portsForDevices.getPort48().getId());}

            cf.setInt(50, portsForDevices.getPort48().getId());

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
}
