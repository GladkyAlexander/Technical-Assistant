package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl;

import ru.greatlarder.technicalassistant.domain.Port;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.EquipmentRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.PortRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLitePort;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;


public class PortRepositoryImpl implements PortRepository {
    EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
    @Override
    public Port setPort(Port port) {
        createPortTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLitePort.INSERT_TABLE_PORT);
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

            cf.executeUpdate();
            closeDB();
            System.out.println("The equipment : " + port.getId() + " table is filled in !");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }

    @Override
    public Port getPort(int idEquipment) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        createPortTable();
        try {
            resultSet = statement.executeQuery(SQLitePort.READ_TABLE_PORT);

            while (resultSet.next()) {
                if (resultSet.getInt("idNetworkSwitch") == (idEquipment)){
                    hashMap = getHash(resultSet);
                }

            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return get(hashMap);
    }
    private Port get(HashMap<Integer, Integer> hashMap){
        Port port = new Port();
        if(hashMap.get(1) != null) {port.setPort1(equipmentRepository.getEquipmentById(hashMap.get(1), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(2) != null) {port.setPort2(equipmentRepository.getEquipmentById(hashMap.get(2), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(3) != null) {port.setPort3(equipmentRepository.getEquipmentById(hashMap.get(3), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(4) != null) {port.setPort4(equipmentRepository.getEquipmentById(hashMap.get(4), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(5) != null) {port.setPort5(equipmentRepository.getEquipmentById(hashMap.get(5), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(6) != null) {port.setPort6(equipmentRepository.getEquipmentById(hashMap.get(6), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(7) != null) {port.setPort7(equipmentRepository.getEquipmentById(hashMap.get(7), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(8) != null) {port.setPort8(equipmentRepository.getEquipmentById(hashMap.get(8), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(9) != null) {port.setPort9(equipmentRepository.getEquipmentById(hashMap.get(9), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(10) != null) {port.setPort10(equipmentRepository.getEquipmentById(hashMap.get(10), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(11) != null) {port.setPort11(equipmentRepository.getEquipmentById(hashMap.get(11), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(12) != null) {port.setPort12(equipmentRepository.getEquipmentById(hashMap.get(12), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(13) != null) {port.setPort13(equipmentRepository.getEquipmentById(hashMap.get(13), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(14) != null) {port.setPort14(equipmentRepository.getEquipmentById(hashMap.get(14), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(15) != null) {port.setPort15(equipmentRepository.getEquipmentById(hashMap.get(15), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(16) != null) {port.setPort16(equipmentRepository.getEquipmentById(hashMap.get(16), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(17) != null) {port.setPort17(equipmentRepository.getEquipmentById(hashMap.get(17), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(18) != null) {port.setPort18(equipmentRepository.getEquipmentById(hashMap.get(18), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(19) != null) {port.setPort19(equipmentRepository.getEquipmentById(hashMap.get(19), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(20) != null) {port.setPort20(equipmentRepository.getEquipmentById(hashMap.get(20), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(21) != null) {port.setPort21(equipmentRepository.getEquipmentById(hashMap.get(21), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(22) != null) {port.setPort22(equipmentRepository.getEquipmentById(hashMap.get(22), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(23) != null) {port.setPort23(equipmentRepository.getEquipmentById(hashMap.get(23), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(24) != null) {port.setPort24(equipmentRepository.getEquipmentById(hashMap.get(24), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(25) != null) {port.setPort25(equipmentRepository.getEquipmentById(hashMap.get(25), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(26) != null) {port.setPort26(equipmentRepository.getEquipmentById(hashMap.get(26), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(27) != null) {port.setPort27(equipmentRepository.getEquipmentById(hashMap.get(27), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(28) != null) {port.setPort28(equipmentRepository.getEquipmentById(hashMap.get(28), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(29) != null) {port.setPort29(equipmentRepository.getEquipmentById(hashMap.get(29), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(30) != null) {port.setPort30(equipmentRepository.getEquipmentById(hashMap.get(30), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(31) != null) {port.setPort31(equipmentRepository.getEquipmentById(hashMap.get(31), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(32) != null) {port.setPort32(equipmentRepository.getEquipmentById(hashMap.get(32), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(33) != null) {port.setPort33(equipmentRepository.getEquipmentById(hashMap.get(33), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(34) != null) {port.setPort34(equipmentRepository.getEquipmentById(hashMap.get(34), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(35) != null) {port.setPort35(equipmentRepository.getEquipmentById(hashMap.get(35), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(36) != null) {port.setPort36(equipmentRepository.getEquipmentById(hashMap.get(36), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(37) != null) {port.setPort37(equipmentRepository.getEquipmentById(hashMap.get(37), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(38) != null) {port.setPort38(equipmentRepository.getEquipmentById(hashMap.get(38), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(39) != null) {port.setPort39(equipmentRepository.getEquipmentById(hashMap.get(39), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(40) != null) {port.setPort40(equipmentRepository.getEquipmentById(hashMap.get(40), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(41) != null) {port.setPort41(equipmentRepository.getEquipmentById(hashMap.get(41), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(42) != null) {port.setPort42(equipmentRepository.getEquipmentById(hashMap.get(42), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(43) != null) {port.setPort43(equipmentRepository.getEquipmentById(hashMap.get(43), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(44) != null) {port.setPort44(equipmentRepository.getEquipmentById(hashMap.get(44), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(45) != null) {port.setPort45(equipmentRepository.getEquipmentById(hashMap.get(45), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(46) != null) {port.setPort46(equipmentRepository.getEquipmentById(hashMap.get(46), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(47) != null) {port.setPort47(equipmentRepository.getEquipmentById(hashMap.get(47), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(48) != null) {port.setPort48(equipmentRepository.getEquipmentById(hashMap.get(48), GlobalLinkStartEngineerController.getStartEngineerController().company.getNameCompany()));}
        if(hashMap.get(49) != null) {port.setId(hashMap.get(49));}
        if(hashMap.get(50) != null) {port.setIdNetworkSwitch(hashMap.get(50));}

        return port;
    }
    private HashMap<Integer, Integer> getHash(ResultSet resultSet) throws SQLException {

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, resultSet.getInt("port1"));
        hashMap.put(2, resultSet.getInt("port2"));
        hashMap.put(3, resultSet.getInt("port3"));
        hashMap.put(4, resultSet.getInt("port4"));
        hashMap.put(5, resultSet.getInt("port5"));
        hashMap.put(6, resultSet.getInt("port6"));
        hashMap.put(7, resultSet.getInt("port7"));
        hashMap.put(8, resultSet.getInt("port8"));
        hashMap.put(9, resultSet.getInt("port9"));
        hashMap.put(10, resultSet.getInt("port10"));
        hashMap.put(11, resultSet.getInt("port11"));
        hashMap.put(12, resultSet.getInt("port12"));
        hashMap.put(13, resultSet.getInt("port13"));
        hashMap.put(14, resultSet.getInt("port14"));
        hashMap.put(15, resultSet.getInt("port15"));
        hashMap.put(16, resultSet.getInt("port16"));
        hashMap.put(17, resultSet.getInt("port17"));
        hashMap.put(18, resultSet.getInt("port18"));
        hashMap.put(19, resultSet.getInt("port19"));
        hashMap.put(20, resultSet.getInt("port20"));
        hashMap.put(21, resultSet.getInt("port21"));
        hashMap.put(22, resultSet.getInt("port22"));
        hashMap.put(23, resultSet.getInt("port23"));
        hashMap.put(24, resultSet.getInt("port24"));
        hashMap.put(25, resultSet.getInt("port25"));
        hashMap.put(26, resultSet.getInt("port26"));
        hashMap.put(27, resultSet.getInt("port27"));
        hashMap.put(28, resultSet.getInt("port28"));
        hashMap.put(29, resultSet.getInt("port29"));
        hashMap.put(30, resultSet.getInt("port30"));
        hashMap.put(31, resultSet.getInt("port31"));
        hashMap.put(32, resultSet.getInt("port32"));
        hashMap.put(33, resultSet.getInt("port33"));
        hashMap.put(34, resultSet.getInt("port34"));
        hashMap.put(35, resultSet.getInt("port35"));
        hashMap.put(36, resultSet.getInt("port36"));
        hashMap.put(37, resultSet.getInt("port37"));
        hashMap.put(38, resultSet.getInt("port38"));
        hashMap.put(39, resultSet.getInt("port39"));
        hashMap.put(40, resultSet.getInt("port40"));
        hashMap.put(41, resultSet.getInt("port41"));
        hashMap.put(42, resultSet.getInt("port42"));
        hashMap.put(43, resultSet.getInt("port43"));
        hashMap.put(44, resultSet.getInt("port44"));
        hashMap.put(45, resultSet.getInt("port45"));
        hashMap.put(46, resultSet.getInt("port46"));
        hashMap.put(47, resultSet.getInt("port47"));
        hashMap.put(48, resultSet.getInt("port48"));

        hashMap.put(49, resultSet.getInt("id"));
        hashMap.put(50, resultSet.getInt("idNetworkSwitch"));

        return hashMap;

    }

    public Port change(int idPort, String column, Object value){
        createPortTable();
        String sql = "UPDATE port SET " + column + " = ? WHERE id = ?";

        try {
            PreparedStatement cf = connection.prepareStatement(sql);

            if(value instanceof String) {
                cf.setString(1, (String) value);
            }
            if(value instanceof Integer){
                cf.setInt(1, (Integer) value);
            }
            cf.setInt(2, idPort);

            cf.executeUpdate();

            System.out.println("The port table has been changed !");

            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
