package ru.greatlarder.technicalassistant.services.database.mysql.equipment;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.*;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.EquipmentTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.EquipmentTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.UpdateEquipment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEquipmentMySQL implements UpdateEquipment {
    @Override
    public void updateEquipment(User user, Company company, Equipment equipment) {
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createEquipmentTableMySQL();
        EquipmentTableMySQL equipmentTableMySQL = new EquipmentTableMySQLImpl();

        try {
            PreparedStatement cf = connection.connectionMySQL.prepareStatement(equipmentTableMySQL.UPDATE(user.getNameDB()));

            cf.setInt(1, equipment.getId());
            cf.setString(2, equipment.getName());
            cf.setString(3, equipment.getManufacturer());
            cf.setString(4, equipment.getModel());
            cf.setString(5, equipment.getSerialNumber());
            cf.setString(6, equipment.getMacAddress());
            cf.setString(7, equipment.getMacAddress1());
            cf.setString(8, equipment.getMacAddress2());
            cf.setString(9, equipment.getMacAddress3());
            cf.setString(10, equipment.getImage());
            cf.setString(11, equipment.getRoom());
            cf.setString(12, equipment.getLocation());
            cf.setDate(13, Date.valueOf(equipment.getDateWork()));
            cf.setString(14, equipment.getCondition());
            cf.setString(15, equipment.getCompany());
            cf.setString(16, equipment.getManual());
            cf.setString(17, equipment.getLogin());
            cf.setString(18, equipment.getPassword());
            cf.setString(19, equipment.getIpAddress());
            cf.setString(20, equipment.getMasc());
            cf.setString(21, equipment.getGateway());

            if (equipment instanceof Projector) {
                cf.setInt(22, ((Projector) equipment).getTimeWorkLampProjector());
                if (((Projector) equipment).getMaximumLampOperatingTimeProjector() != null) {
                    cf.setInt(23, ((Projector) equipment).getMaximumLampOperatingTimeProjector());
                }
            } else {
                cf.setInt(22, 0);
                cf.setInt(23, 0);
            }
            if (equipment instanceof Microphone) {
                cf.setString(24, ((Microphone) equipment).getFrequency());
            } else {
                cf.setString(24, null);
            }
            cf.setString(25, equipment.getDanteIpAddress());
            cf.setString(26, equipment.getDanteMasc());
            cf.setString(27, equipment.getDanteGateway());
            cf.setString(28, equipment.getOutletNumber());
            if (equipment.getPortNumberInTheSwitch() != null) {
                cf.setInt(29, equipment.getPortNumberInTheSwitch());
            } else {
                cf.setInt(29, 0);
            }
            if (equipment.getIdNetworkSwitcher() != null) {
                cf.setInt(30, equipment.getIdNetworkSwitcher());
            } else {
                cf.setInt(30, 0);
            }
            if (equipment instanceof NetworkSwitch) {
                if (((NetworkSwitch) equipment).getPort1() != null) {
                    cf.setInt(31, ((NetworkSwitch) equipment).getPort1().getId());
                }
                if (((NetworkSwitch) equipment).getPort2() != null) {
                    cf.setInt(32, ((NetworkSwitch) equipment).getPort2().getId());
                }
                if (((NetworkSwitch) equipment).getPort3() != null) {
                    cf.setInt(33, ((NetworkSwitch) equipment).getPort3().getId());
                }
                if (((NetworkSwitch) equipment).getPort4() != null) {
                    cf.setInt(34, ((NetworkSwitch) equipment).getPort4().getId());
                }
                if (((NetworkSwitch) equipment).getPort5() != null) {
                    cf.setInt(35, ((NetworkSwitch) equipment).getPort5().getId());
                }
                if (((NetworkSwitch) equipment).getPort6() != null) {
                    cf.setInt(36, ((NetworkSwitch) equipment).getPort6().getId());
                }
                if (((NetworkSwitch) equipment).getPort7() != null) {
                    cf.setInt(37, ((NetworkSwitch) equipment).getPort7().getId());
                }
                if (((NetworkSwitch) equipment).getPort8() != null) {
                    cf.setInt(38, ((NetworkSwitch) equipment).getPort8().getId());
                }
                if (((NetworkSwitch) equipment).getPort9() != null) {
                    cf.setInt(39, ((NetworkSwitch) equipment).getPort9().getId());
                }
                if (((NetworkSwitch) equipment).getPort10() != null) {
                    cf.setInt(40, ((NetworkSwitch) equipment).getPort10().getId());
                }
                if (((NetworkSwitch) equipment).getPort11() != null) {
                    cf.setInt(41, ((NetworkSwitch) equipment).getPort11().getId());
                }
                if (((NetworkSwitch) equipment).getPort12() != null) {
                    cf.setInt(42, ((NetworkSwitch) equipment).getPort12().getId());
                }
                if (((NetworkSwitch) equipment).getPort13() != null) {
                    cf.setInt(43, ((NetworkSwitch) equipment).getPort13().getId());
                }
                if (((NetworkSwitch) equipment).getPort14() != null) {
                    cf.setInt(44, ((NetworkSwitch) equipment).getPort14().getId());
                }
                if (((NetworkSwitch) equipment).getPort15() != null) {
                    cf.setInt(45, ((NetworkSwitch) equipment).getPort15().getId());
                }
                if (((NetworkSwitch) equipment).getPort16() != null) {
                    cf.setInt(46, ((NetworkSwitch) equipment).getPort16().getId());
                }
                if (((NetworkSwitch) equipment).getPort17() != null) {
                    cf.setInt(47, ((NetworkSwitch) equipment).getPort17().getId());
                }
                if (((NetworkSwitch) equipment).getPort18() != null) {
                    cf.setInt(48, ((NetworkSwitch) equipment).getPort18().getId());
                }
                if (((NetworkSwitch) equipment).getPort19() != null) {
                    cf.setInt(49, ((NetworkSwitch) equipment).getPort19().getId());
                }
                if (((NetworkSwitch) equipment).getPort20() != null) {
                    cf.setInt(50, ((NetworkSwitch) equipment).getPort20().getId());
                }
                if (((NetworkSwitch) equipment).getPort21() != null) {
                    cf.setInt(51, ((NetworkSwitch) equipment).getPort21().getId());
                }
                if (((NetworkSwitch) equipment).getPort22() != null) {
                    cf.setInt(52, ((NetworkSwitch) equipment).getPort22().getId());
                }
                if (((NetworkSwitch) equipment).getPort23() != null) {
                    cf.setInt(53, ((NetworkSwitch) equipment).getPort23().getId());
                }
                if (((NetworkSwitch) equipment).getPort24() != null) {
                    cf.setInt(54, ((NetworkSwitch) equipment).getPort24().getId());
                }
                if (((NetworkSwitch) equipment).getPort25() != null) {
                    cf.setInt(55, ((NetworkSwitch) equipment).getPort25().getId());
                }
                if (((NetworkSwitch) equipment).getPort26() != null) {
                    cf.setInt(56, ((NetworkSwitch) equipment).getPort26().getId());
                }
                if (((NetworkSwitch) equipment).getPort27() != null) {
                    cf.setInt(57, ((NetworkSwitch) equipment).getPort27().getId());
                }
                if (((NetworkSwitch) equipment).getPort28() != null) {
                    cf.setInt(58, ((NetworkSwitch) equipment).getPort28().getId());
                }
                if (((NetworkSwitch) equipment).getPort29() != null) {
                    cf.setInt(59, ((NetworkSwitch) equipment).getPort29().getId());
                }
                if (((NetworkSwitch) equipment).getPort30() != null) {
                    cf.setInt(60, ((NetworkSwitch) equipment).getPort30().getId());
                }
                if (((NetworkSwitch) equipment).getPort31() != null) {
                    cf.setInt(61, ((NetworkSwitch) equipment).getPort31().getId());
                }
                if (((NetworkSwitch) equipment).getPort32() != null) {
                    cf.setInt(62, ((NetworkSwitch) equipment).getPort32().getId());
                }
                if (((NetworkSwitch) equipment).getPort33() != null) {
                    cf.setInt(63, ((NetworkSwitch) equipment).getPort33().getId());
                }
                if (((NetworkSwitch) equipment).getPort34() != null) {
                    cf.setInt(64, ((NetworkSwitch) equipment).getPort34().getId());
                }
                if (((NetworkSwitch) equipment).getPort35() != null) {
                    cf.setInt(65, ((NetworkSwitch) equipment).getPort35().getId());
                }
                if (((NetworkSwitch) equipment).getPort36() != null) {
                    cf.setInt(66, ((NetworkSwitch) equipment).getPort36().getId());
                }
                if (((NetworkSwitch) equipment).getPort37() != null) {
                    cf.setInt(67, ((NetworkSwitch) equipment).getPort37().getId());
                }
                if (((NetworkSwitch) equipment).getPort38() != null) {
                    cf.setInt(68, ((NetworkSwitch) equipment).getPort38().getId());
                }
                if (((NetworkSwitch) equipment).getPort39() != null) {
                    cf.setInt(69, ((NetworkSwitch) equipment).getPort39().getId());
                }
                if (((NetworkSwitch) equipment).getPort40() != null) {
                    cf.setInt(70, ((NetworkSwitch) equipment).getPort40().getId());
                }
                if (((NetworkSwitch) equipment).getPort41() != null) {
                    cf.setInt(71, ((NetworkSwitch) equipment).getPort41().getId());
                }
                if (((NetworkSwitch) equipment).getPort42() != null) {
                    cf.setInt(72, ((NetworkSwitch) equipment).getPort42().getId());
                }
                if (((NetworkSwitch) equipment).getPort43() != null) {
                    cf.setInt(73, ((NetworkSwitch) equipment).getPort43().getId());
                }
                if (((NetworkSwitch) equipment).getPort44() != null) {
                    cf.setInt(74, ((NetworkSwitch) equipment).getPort44().getId());
                }
                if (((NetworkSwitch) equipment).getPort45() != null) {
                    cf.setInt(75, ((NetworkSwitch) equipment).getPort45().getId());
                }
                if (((NetworkSwitch) equipment).getPort46() != null) {
                    cf.setInt(76, ((NetworkSwitch) equipment).getPort46().getId());
                }
                if (((NetworkSwitch) equipment).getPort47() != null) {
                    cf.setInt(77, ((NetworkSwitch) equipment).getPort47().getId());
                }
                if (((NetworkSwitch) equipment).getPort48() != null) {
                    cf.setInt(78, ((NetworkSwitch) equipment).getPort48().getId());
                }

            } else {
                cf.setInt(31, 0);
                cf.setInt(32, 0);
                cf.setInt(33, 0);
                cf.setInt(34, 0);
                cf.setInt(35, 0);
                cf.setInt(36, 0);
                cf.setInt(37, 0);
                cf.setInt(38, 0);
                cf.setInt(39, 0);
                cf.setInt(40, 0);
                cf.setInt(41, 0);
                cf.setInt(42, 0);
                cf.setInt(43, 0);
                cf.setInt(44, 0);
                cf.setInt(45, 0);
                cf.setInt(46, 0);
                cf.setInt(47, 0);
                cf.setInt(48, 0);
                cf.setInt(49, 0);
                cf.setInt(50, 0);
                cf.setInt(51, 0);
                cf.setInt(52, 0);
                cf.setInt(53, 0);
                cf.setInt(54, 0);
                cf.setInt(55, 0);
                cf.setInt(56, 0);
                cf.setInt(57, 0);
                cf.setInt(58, 0);
                cf.setInt(59, 0);
                cf.setInt(60, 0);
                cf.setInt(61, 0);
                cf.setInt(62, 0);
                cf.setInt(63, 0);
                cf.setInt(64, 0);
                cf.setInt(65, 0);
                cf.setInt(66, 0);
                cf.setInt(67, 0);
                cf.setInt(68, 0);
                cf.setInt(69, 0);
                cf.setInt(70, 0);
                cf.setInt(71, 0);
                cf.setInt(72, 0);
                cf.setInt(73, 0);
                cf.setInt(74, 0);
                cf.setInt(75, 0);
                cf.setInt(76, 0);
                cf.setInt(77, 0);
                cf.setInt(78, 0);
            }
            if (equipment instanceof Laptop) {
                cf.setString(79, ((Laptop) equipment).getOs());
            } else cf.setString(79, null);
            if (equipment instanceof TouchControlPanel) {
                cf.setString(80, ((TouchControlPanel) equipment).getDiagonal());
            } else cf.setString(80, null);
            if (equipment instanceof TvPanel) {
                cf.setString(80, ((TvPanel) equipment).getDiagonal());
            } else cf.setString(80, null);
            cf.setString(81, equipment.getType());
            if(!equipment.isUserVisibility()){
                cf.setInt(82, 0);
            }else cf.setInt(82, 1);

            cf.setInt(83, equipment.getId());
            cf.executeUpdate();
            connection.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQLDatabase();
        }
    }
}
