package ru.greatlarder.technicalassistant.repository.impl;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.*;
import ru.greatlarder.technicalassistant.repository.ExternalDatabase;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.db.external_db.DBconnectMySQL.*;

public class ExternalDatabaseRepositoryImpl implements ExternalDatabase {

    Company company;
    Language language = new LanguageImpl();

    @Override
    public void sendCompanyDetailis(Company company) {
        this.company = company;

        createCompanyTableExternalDB();
        try {
            PreparedStatement clear = connectionExternalDB.prepareStatement(CLEAR_TABLE_COMPANY);
            clear.executeUpdate();
            PreparedStatement clearEquipment = connectionExternalDB.prepareStatement(CLEAR_TABLE_EQUIPMENT);
            clearEquipment.executeUpdate();
            PreparedStatement cf = connectionExternalDB.prepareStatement(INSERT_COMPANY_DB);
            cf.setInt(1, company.getId());
            cf.setString(2, company.getNameCompany());
            cf.setString(3, company.getAddress());
            cf.setString(4, company.getCuratorLastName());
            cf.setString(5, company.getCuratorFirstName());
            cf.setString(6, company.getPhoneCurator());
            cf.setString(7, company.getMailCurator());
            cf.setString(8, company.getWebsiteCompany());
            cf.setString(9, company.getLogoCompany());
            cf.setString(10, company.getManagerLastName());
            cf.setString(11, company.getManagerFirstName());
            cf.setString(12, company.getPhoneManager());
            cf.setString(13, company.getMailManager());
            cf.setString(14, company.getEngineerLastName());
            cf.setString(15, company.getEngineerFirstName());
            cf.setString(16, company.getPhoneEngineer());
            cf.setString(17, company.getMailEngineer());

            cf.executeUpdate();
            close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }


        for (Equipment equipment : company.getEquipmentList()) {
            createEquipmentTableExternalDB();
            try {
                PreparedStatement cf = connectionExternalDB.prepareStatement(INSERT_EQUIPMENT_DB);
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

                cf.executeUpdate();

                close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
    }

    @Override
    public List<Company> acceptDataFromAllCompanies() {
        return getListCompanyExternal();
    }

    @Override
    public void updateCompanyExternal(Company company) {
        this.company = company;

        createCompanyTableExternalDB();
        try {
            PreparedStatement cf = connectionExternalDB.prepareStatement(UPDATE_COMPANY_DB);
            cf.setInt(1, company.getId());
            cf.setString(2, company.getNameCompany());
            cf.setString(3, company.getAddress());
            cf.setString(4, company.getCuratorLastName());
            cf.setString(5, company.getCuratorFirstName());
            cf.setString(6, company.getPhoneCurator());
            cf.setString(7, company.getMailCurator());
            cf.setString(8, company.getWebsiteCompany());
            cf.setString(9, company.getLogoCompany());
            cf.setString(10, company.getManagerLastName());
            cf.setString(11, company.getManagerFirstName());
            cf.setString(12, company.getPhoneManager());
            cf.setString(13, company.getMailManager());
            cf.setString(14, company.getEngineerLastName());
            cf.setString(15, company.getEngineerFirstName());
            cf.setString(16, company.getPhoneEngineer());
            cf.setString(17, company.getMailEngineer());

            cf.setInt(18, company.getId());

            cf.executeUpdate();
            close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }


        for (Equipment equipment : company.getEquipmentList()) {
            createEquipmentTableExternalDB();
            try {
                PreparedStatement cf = connectionExternalDB.prepareStatement(UPDATE_EQUIPMENT_DB);
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

                cf.setInt(81, equipment.getId());

                cf.executeUpdate();
                close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
    }

    @Override
    public void updateEquipmentExternal(Equipment equipment) {
        createEquipmentTableExternalDB();
        try {
            PreparedStatement cf = connectionExternalDB.prepareStatement(UPDATE_EQUIPMENT_DB);
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

            cf.setInt(81, equipment.getId());

            cf.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public Company getCompanyForNameCompany(String nameCompany) {
        List<Company> companies = new ArrayList<Company>(getListCompanyExternal());

        for (Company company1 : companies){

            if(company1.getNameCompany().equals(nameCompany)) return company1;

        }
        return null;
    }

    @Override
    public void changeDataEquipment(Equipment equipment) {
        createEquipmentTableExternalDB();
        try {
        PreparedStatement cf = connectionExternalDB.prepareStatement(UPDATE_EQUIPMENT_DB);
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

        cf.setInt(81, equipment.getId());

        cf.executeUpdate();

        close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private List<Company> getListCompanyExternal(){
        List<Company> companies = new ArrayList<>();
        createCompanyTableExternalDB();
        try {
            resultSetExternalDB = statementExternalDB.executeQuery(READ_TABLE_COMPANY);
            while (resultSetExternalDB.next()){
                Company company1 = new Company();
                company1.setId(resultSetExternalDB.getInt("id"));
                company1.setNameCompany(resultSetExternalDB.getString("nameCompany"));
                company1.setAddress(resultSetExternalDB.getString("address"));
                company1.setCuratorLastName(resultSetExternalDB.getString("curatorLastName"));
                company1.setCuratorFirstName(resultSetExternalDB.getString("curatorFirstName"));
                company1.setPhoneCurator(resultSetExternalDB.getString("phoneCurator"));
                company1.setMailCurator(resultSetExternalDB.getString("mailCurator"));
                company1.setWebsiteCompany(resultSetExternalDB.getString("websiteCompany"));
                company1.setLogoCompany(resultSetExternalDB.getString("logoCompany"));
                company1.setManagerLastName(resultSetExternalDB.getString("managerLastName"));
                company1.setManagerFirstName(resultSetExternalDB.getString("managerFirstName"));
                company1.setPhoneManager(resultSetExternalDB.getString("phoneManager"));
                company1.setMailManager(resultSetExternalDB.getString("mailManager"));
                company1.setEngineerLastName(resultSetExternalDB.getString("engineerLastName"));
                company1.setEngineerFirstName(resultSetExternalDB.getString("engineerFirstName"));
                company1.setPhoneEngineer(resultSetExternalDB.getString("phoneEngineer"));
                company1.setMailEngineer(resultSetExternalDB.getString("mailEngineer"));
                companies.add(company1);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        List<Equipment> equipments = new ArrayList<>();
        createEquipmentTableExternalDB();

        try {
            resultSetExternalDB = statementExternalDB.executeQuery(READ_TABLE_EQUIPMENT);
            while (resultSetExternalDB.next()){
                Equipment equipment = getEquipment(resultSetExternalDB);
                equipments.add(equipment);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return getListCompany(companies, equipments);
    }
    private Projector getProjector(ResultSet resultSet) throws SQLException {
        Projector projector = new Projector();
        projector.setId(resultSet.getInt("id"));
        projector.setName(resultSet.getString("name"));
        projector.setModel(resultSet.getString("model"));
        projector.setManufacturer(resultSet.getString("manufacturer"));
        projector.setSerialNumber(resultSet.getString("serialNumber"));
        projector.setIpAddress(resultSet.getString("ipAddress"));
        projector.setMasc(resultSet.getString("masc"));
        projector.setGateway(resultSet.getString("gateway"));
        projector.setMacAddress(resultSet.getString("macAddress"));
        projector.setLogin(resultSet.getString("login"));
        projector.setPassword(resultSet.getString("password"));
        projector.setImage(resultSet.getString("image"));
        projector.setRoom(resultSet.getString("room"));
        projector.setLocation(resultSet.getString("location"));
        projector.setCondition(resultSet.getString("condition_equipment"));
        projector.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        projector.setCompany(resultSet.getString("company"));
        projector.setManual(resultSet.getString("manual"));
        projector.setOutletNumber(resultSet.getString("outletNumber"));
        projector.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        projector.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        projector.setTimeWorkLampProjector(resultSet.getInt("timeWorkLampProjector"));
        projector.setMaximumLampOperatingTimeProjector(resultSet.getInt("maximumLampOperatingTimeProjector"));

        return projector;
    }

    private Microphone getMicrophone(ResultSet resultSet) throws SQLException {
        Microphone microphone = new Microphone();
        microphone.setId(resultSet.getInt("id"));
        microphone.setName(resultSet.getString("name"));
        microphone.setModel(resultSet.getString("model"));
        microphone.setManufacturer(resultSet.getString("manufacturer"));
        microphone.setSerialNumber(resultSet.getString("serialNumber"));
        microphone.setMacAddress(resultSet.getString("macAddress"));
        microphone.setLogin(resultSet.getString("login"));
        microphone.setPassword(resultSet.getString("password"));
        microphone.setImage(resultSet.getString("image"));
        microphone.setRoom(resultSet.getString("room"));
        microphone.setLocation(resultSet.getString("location"));
        microphone.setCondition(resultSet.getString("condition_equipment"));
        microphone.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        microphone.setCompany(resultSet.getString("company"));
        microphone.setManual(resultSet.getString("manual"));
        microphone.setOutletNumber(resultSet.getString("outletNumber"));
        microphone.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        microphone.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        microphone.setFrequency(resultSet.getString("frequencyMicrophone"));

        return microphone;
    }
    private NetworkSwitch getNetworkSwitch(ResultSet resultSet) throws SQLException {
        NetworkSwitch networkSwitch = new NetworkSwitch();

        networkSwitch.setId(resultSet.getInt("id"));
        networkSwitch.setName(resultSet.getString("name"));
        networkSwitch.setModel(resultSet.getString("model"));
        networkSwitch.setManufacturer(resultSet.getString("manufacturer"));
        networkSwitch.setSerialNumber(resultSet.getString("serialNumber"));
        networkSwitch.setIpAddress(resultSet.getString("ipAddress"));
        networkSwitch.setMasc(resultSet.getString("masc"));
        networkSwitch.setGateway(resultSet.getString("gateway"));
        networkSwitch.setMacAddress(resultSet.getString("macAddress"));
        networkSwitch.setLogin(resultSet.getString("login"));
        networkSwitch.setPassword(resultSet.getString("password"));
        networkSwitch.setImage(resultSet.getString("image"));
        networkSwitch.setRoom(resultSet.getString("room"));
        networkSwitch.setLocation(resultSet.getString("location"));
        networkSwitch.setCondition(resultSet.getString("condition_equipment"));
        networkSwitch.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        networkSwitch.setCompany(resultSet.getString("company"));
        networkSwitch.setManual(resultSet.getString("manual"));
        networkSwitch.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        networkSwitch.setDanteMasc(resultSet.getString("mascDante"));
        networkSwitch.setDanteGateway(resultSet.getString("gatewayDante"));
        networkSwitch.setOutletNumber(resultSet.getString("outletNumber"));

        return networkSwitch;
    }
    private AcousticSpeaker getAcousticSpeaker(ResultSet resultSet) throws SQLException {
        AcousticSpeaker acousticSpeaker = new AcousticSpeaker();
        acousticSpeaker.setId(resultSet.getInt("id"));
        acousticSpeaker.setName(resultSet.getString("name"));
        acousticSpeaker.setModel(resultSet.getString("model"));
        acousticSpeaker.setManufacturer(resultSet.getString("manufacturer"));
        acousticSpeaker.setSerialNumber(resultSet.getString("serialNumber"));
        acousticSpeaker.setIpAddress(resultSet.getString("ipAddress"));
        acousticSpeaker.setMasc(resultSet.getString("masc"));
        acousticSpeaker.setGateway(resultSet.getString("gateway"));
        acousticSpeaker.setMacAddress(resultSet.getString("macAddress"));
        acousticSpeaker.setMacAddress1(resultSet.getString("macAddress1"));
        acousticSpeaker.setMacAddress2(resultSet.getString("macAddress2"));
        acousticSpeaker.setMacAddress3(resultSet.getString("macAddress3"));
        acousticSpeaker.setLogin(resultSet.getString("login"));
        acousticSpeaker.setPassword(resultSet.getString("password"));
        acousticSpeaker.setImage(resultSet.getString("image"));
        acousticSpeaker.setRoom(resultSet.getString("room"));
        acousticSpeaker.setLocation(resultSet.getString("location"));
        acousticSpeaker.setCondition(resultSet.getString("condition_equipment"));
        acousticSpeaker.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        acousticSpeaker.setCompany(resultSet.getString("company"));
        acousticSpeaker.setManual(resultSet.getString("manual"));
        acousticSpeaker.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        acousticSpeaker.setDanteMasc(resultSet.getString("mascDante"));
        acousticSpeaker.setDanteGateway(resultSet.getString("gatewayDante"));
        acousticSpeaker.setOutletNumber(resultSet.getString("outletNumber"));
        acousticSpeaker.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        acousticSpeaker.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return acousticSpeaker;
    }
    private AudioAmplifier getAudioAmplifier(ResultSet resultSet) throws SQLException {
        AudioAmplifier audioAmplifier = new AudioAmplifier();
        audioAmplifier.setId(resultSet.getInt("id"));
        audioAmplifier.setName(resultSet.getString("name"));
        audioAmplifier.setModel(resultSet.getString("model"));
        audioAmplifier.setManufacturer(resultSet.getString("manufacturer"));
        audioAmplifier.setSerialNumber(resultSet.getString("serialNumber"));
        audioAmplifier.setIpAddress(resultSet.getString("ipAddress"));
        audioAmplifier.setMasc(resultSet.getString("masc"));
        audioAmplifier.setGateway(resultSet.getString("gateway"));
        audioAmplifier.setMacAddress(resultSet.getString("macAddress"));
        audioAmplifier.setMacAddress1(resultSet.getString("macAddress1"));
        audioAmplifier.setMacAddress2(resultSet.getString("macAddress2"));
        audioAmplifier.setMacAddress3(resultSet.getString("macAddress3"));
        audioAmplifier.setLogin(resultSet.getString("login"));
        audioAmplifier.setPassword(resultSet.getString("password"));
        audioAmplifier.setImage(resultSet.getString("image"));
        audioAmplifier.setRoom(resultSet.getString("room"));
        audioAmplifier.setLocation(resultSet.getString("location"));
        audioAmplifier.setCondition(resultSet.getString("condition_equipment"));
        audioAmplifier.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        audioAmplifier.setCompany(resultSet.getString("company"));
        audioAmplifier.setManual(resultSet.getString("manual"));
        audioAmplifier.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        audioAmplifier.setDanteMasc(resultSet.getString("mascDante"));
        audioAmplifier.setDanteGateway(resultSet.getString("gatewayDante"));
        audioAmplifier.setOutletNumber(resultSet.getString("outletNumber"));
        audioAmplifier.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        audioAmplifier.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return audioAmplifier;
    }
    private AudioInterface getAudioInterface(ResultSet resultSet) throws SQLException {
        AudioInterface audioInterface = new AudioInterface();
        audioInterface.setId(resultSet.getInt("id"));
        audioInterface.setName(resultSet.getString("name"));
        audioInterface.setModel(resultSet.getString("model"));
        audioInterface.setManufacturer(resultSet.getString("manufacturer"));
        audioInterface.setSerialNumber(resultSet.getString("serialNumber"));
        audioInterface.setIpAddress(resultSet.getString("ipAddress"));
        audioInterface.setMasc(resultSet.getString("masc"));
        audioInterface.setGateway(resultSet.getString("gateway"));
        audioInterface.setMacAddress(resultSet.getString("macAddress"));
        audioInterface.setMacAddress1(resultSet.getString("macAddress1"));
        audioInterface.setMacAddress2(resultSet.getString("macAddress2"));
        audioInterface.setMacAddress3(resultSet.getString("macAddress3"));
        audioInterface.setLogin(resultSet.getString("login"));
        audioInterface.setPassword(resultSet.getString("password"));
        audioInterface.setImage(resultSet.getString("image"));
        audioInterface.setRoom(resultSet.getString("room"));
        audioInterface.setLocation(resultSet.getString("location"));
        audioInterface.setCondition(resultSet.getString("condition_equipment"));
        audioInterface.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        audioInterface.setCompany(resultSet.getString("company"));
        audioInterface.setManual(resultSet.getString("manual"));
        audioInterface.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        audioInterface.setDanteMasc(resultSet.getString("mascDante"));
        audioInterface.setDanteGateway(resultSet.getString("gatewayDante"));
        audioInterface.setOutletNumber(resultSet.getString("outletNumber"));
        audioInterface.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        audioInterface.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return  audioInterface;
    }
    private AudioProcessor getAudioProcessor(ResultSet resultSet) throws SQLException {
        AudioProcessor audioProcessor = new AudioProcessor();
        audioProcessor.setId(resultSet.getInt("id"));
        audioProcessor.setName(resultSet.getString("name"));
        audioProcessor.setModel(resultSet.getString("model"));
        audioProcessor.setManufacturer(resultSet.getString("manufacturer"));
        audioProcessor.setSerialNumber(resultSet.getString("serialNumber"));
        audioProcessor.setIpAddress(resultSet.getString("ipAddress"));
        audioProcessor.setMasc(resultSet.getString("masc"));
        audioProcessor.setGateway(resultSet.getString("gateway"));
        audioProcessor.setMacAddress(resultSet.getString("macAddress"));
        audioProcessor.setMacAddress1(resultSet.getString("macAddress1"));
        audioProcessor.setMacAddress2(resultSet.getString("macAddress2"));
        audioProcessor.setMacAddress3(resultSet.getString("macAddress3"));
        audioProcessor.setLogin(resultSet.getString("login"));
        audioProcessor.setPassword(resultSet.getString("password"));
        audioProcessor.setImage(resultSet.getString("image"));
        audioProcessor.setRoom(resultSet.getString("room"));
        audioProcessor.setLocation(resultSet.getString("location"));
        audioProcessor.setCondition(resultSet.getString("condition_equipment"));
        audioProcessor.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        audioProcessor.setCompany(resultSet.getString("company"));
        audioProcessor.setManual(resultSet.getString("manual"));
        audioProcessor.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        audioProcessor.setDanteMasc(resultSet.getString("mascDante"));
        audioProcessor.setDanteGateway(resultSet.getString("gatewayDante"));
        audioProcessor.setOutletNumber(resultSet.getString("outletNumber"));
        audioProcessor.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        audioProcessor.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return  audioProcessor;
    }
    private ControlProcessor getControlProcessor(ResultSet resultSet) throws SQLException {
        ControlProcessor controlProcessor = new ControlProcessor();
        controlProcessor.setId(resultSet.getInt("id"));
        controlProcessor.setName(resultSet.getString("name"));
        controlProcessor.setModel(resultSet.getString("model"));
        controlProcessor.setManufacturer(resultSet.getString("manufacturer"));
        controlProcessor.setSerialNumber(resultSet.getString("serialNumber"));
        controlProcessor.setIpAddress(resultSet.getString("ipAddress"));
        controlProcessor.setMasc(resultSet.getString("masc"));
        controlProcessor.setGateway(resultSet.getString("gateway"));
        controlProcessor.setMacAddress(resultSet.getString("macAddress"));
        controlProcessor.setMacAddress1(resultSet.getString("macAddress1"));
        controlProcessor.setMacAddress2(resultSet.getString("macAddress2"));
        controlProcessor.setMacAddress3(resultSet.getString("macAddress3"));
        controlProcessor.setLogin(resultSet.getString("login"));
        controlProcessor.setPassword(resultSet.getString("password"));
        controlProcessor.setImage(resultSet.getString("image"));
        controlProcessor.setRoom(resultSet.getString("room"));
        controlProcessor.setLocation(resultSet.getString("location"));
        controlProcessor.setCondition(resultSet.getString("condition_equipment"));
        controlProcessor.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        controlProcessor.setCompany(resultSet.getString("company"));
        controlProcessor.setManual(resultSet.getString("manual"));
        controlProcessor.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        controlProcessor.setDanteMasc(resultSet.getString("mascDante"));
        controlProcessor.setDanteGateway(resultSet.getString("gatewayDante"));
        controlProcessor.setOutletNumber(resultSet.getString("outletNumber"));
        controlProcessor.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        controlProcessor.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return controlProcessor;
    }
    private Laptop getLaptop(ResultSet resultSet) throws SQLException {
        Laptop laptop = new Laptop();
        laptop.setId(resultSet.getInt("id"));
        laptop.setName(resultSet.getString("name"));
        laptop.setModel(resultSet.getString("model"));
        laptop.setManufacturer(resultSet.getString("manufacturer"));
        laptop.setSerialNumber(resultSet.getString("serialNumber"));
        laptop.setIpAddress(resultSet.getString("ipAddress"));
        laptop.setMasc(resultSet.getString("masc"));
        laptop.setGateway(resultSet.getString("gateway"));
        laptop.setMacAddress(resultSet.getString("macAddress"));
        laptop.setMacAddress1(resultSet.getString("macAddress1"));
        laptop.setMacAddress2(resultSet.getString("macAddress2"));
        laptop.setMacAddress3(resultSet.getString("macAddress3"));
        laptop.setLogin(resultSet.getString("login"));
        laptop.setPassword(resultSet.getString("password"));
        laptop.setImage(resultSet.getString("image"));
        laptop.setRoom(resultSet.getString("room"));
        laptop.setLocation(resultSet.getString("location"));
        laptop.setCondition(resultSet.getString("condition_equipment"));
        laptop.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        laptop.setCompany(resultSet.getString("company"));
        laptop.setManual(resultSet.getString("manual"));
        laptop.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        laptop.setDanteMasc(resultSet.getString("mascDante"));
        laptop.setDanteGateway(resultSet.getString("gatewayDante"));
        laptop.setOutletNumber(resultSet.getString("outletNumber"));
        laptop.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        laptop.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        laptop.setOs(resultSet.getString("operating_system"));
        return  laptop;
    }
    private MatrixSwitcher getMatrixSwitcher(ResultSet resultSet) throws SQLException {
        MatrixSwitcher matrixSwitcher = new MatrixSwitcher();
        matrixSwitcher.setId(resultSet.getInt("id"));
        matrixSwitcher.setName(resultSet.getString("name"));
        matrixSwitcher.setModel(resultSet.getString("model"));
        matrixSwitcher.setManufacturer(resultSet.getString("manufacturer"));
        matrixSwitcher.setSerialNumber(resultSet.getString("serialNumber"));
        matrixSwitcher.setIpAddress(resultSet.getString("ipAddress"));
        matrixSwitcher.setMasc(resultSet.getString("masc"));
        matrixSwitcher.setGateway(resultSet.getString("gateway"));
        matrixSwitcher.setMacAddress(resultSet.getString("macAddress"));
        matrixSwitcher.setMacAddress1(resultSet.getString("macAddress1"));
        matrixSwitcher.setMacAddress2(resultSet.getString("macAddress2"));
        matrixSwitcher.setMacAddress3(resultSet.getString("macAddress3"));
        matrixSwitcher.setLogin(resultSet.getString("login"));
        matrixSwitcher.setPassword(resultSet.getString("password"));
        matrixSwitcher.setImage(resultSet.getString("image"));
        matrixSwitcher.setRoom(resultSet.getString("room"));
        matrixSwitcher.setLocation(resultSet.getString("location"));
        matrixSwitcher.setCondition(resultSet.getString("condition_equipment"));
        matrixSwitcher.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        matrixSwitcher.setCompany(resultSet.getString("company"));
        matrixSwitcher.setManual(resultSet.getString("manual"));
        matrixSwitcher.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        matrixSwitcher.setDanteMasc(resultSet.getString("mascDante"));
        matrixSwitcher.setDanteGateway(resultSet.getString("gatewayDante"));
        matrixSwitcher.setOutletNumber(resultSet.getString("outletNumber"));
        matrixSwitcher.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        matrixSwitcher.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return  matrixSwitcher;
    }
    private MediaPlayer getMediaPlayer(ResultSet resultSet) throws SQLException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setId(resultSet.getInt("id"));
        mediaPlayer.setName(resultSet.getString("name"));
        mediaPlayer.setModel(resultSet.getString("model"));
        mediaPlayer.setManufacturer(resultSet.getString("manufacturer"));
        mediaPlayer.setSerialNumber(resultSet.getString("serialNumber"));
        mediaPlayer.setIpAddress(resultSet.getString("ipAddress"));
        mediaPlayer.setMasc(resultSet.getString("masc"));
        mediaPlayer.setGateway(resultSet.getString("gateway"));
        mediaPlayer.setMacAddress(resultSet.getString("macAddress"));
        mediaPlayer.setMacAddress1(resultSet.getString("macAddress1"));
        mediaPlayer.setMacAddress2(resultSet.getString("macAddress2"));
        mediaPlayer.setMacAddress3(resultSet.getString("macAddress3"));
        mediaPlayer.setLogin(resultSet.getString("login"));
        mediaPlayer.setPassword(resultSet.getString("password"));
        mediaPlayer.setImage(resultSet.getString("image"));
        mediaPlayer.setRoom(resultSet.getString("room"));
        mediaPlayer.setLocation(resultSet.getString("location"));
        mediaPlayer.setCondition(resultSet.getString("condition_equipment"));
        mediaPlayer.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        mediaPlayer.setCompany(resultSet.getString("company"));
        mediaPlayer.setManual(resultSet.getString("manual"));
        mediaPlayer.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        mediaPlayer.setDanteMasc(resultSet.getString("mascDante"));
        mediaPlayer.setDanteGateway(resultSet.getString("gatewayDante"));
        mediaPlayer.setOutletNumber(resultSet.getString("outletNumber"));
        mediaPlayer.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        mediaPlayer.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return mediaPlayer;
    }
    private TouchControlPanel getTouchControlPanel(ResultSet resultSet) throws SQLException {
        TouchControlPanel touchControlPanel = new TouchControlPanel();
        touchControlPanel.setId(resultSet.getInt("id"));
        touchControlPanel.setName(resultSet.getString("name"));
        touchControlPanel.setModel(resultSet.getString("model"));
        touchControlPanel.setManufacturer(resultSet.getString("manufacturer"));
        touchControlPanel.setSerialNumber(resultSet.getString("serialNumber"));
        touchControlPanel.setIpAddress(resultSet.getString("ipAddress"));
        touchControlPanel.setMasc(resultSet.getString("masc"));
        touchControlPanel.setGateway(resultSet.getString("gateway"));
        touchControlPanel.setMacAddress(resultSet.getString("macAddress"));
        touchControlPanel.setMacAddress1(resultSet.getString("macAddress1"));
        touchControlPanel.setMacAddress2(resultSet.getString("macAddress2"));
        touchControlPanel.setMacAddress3(resultSet.getString("macAddress3"));
        touchControlPanel.setLogin(resultSet.getString("login"));
        touchControlPanel.setPassword(resultSet.getString("password"));
        touchControlPanel.setImage(resultSet.getString("image"));
        touchControlPanel.setRoom(resultSet.getString("room"));
        touchControlPanel.setLocation(resultSet.getString("location"));
        touchControlPanel.setCondition(resultSet.getString("condition_equipment"));
        touchControlPanel.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        touchControlPanel.setCompany(resultSet.getString("company"));
        touchControlPanel.setManual(resultSet.getString("manual"));
        touchControlPanel.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        touchControlPanel.setDanteMasc(resultSet.getString("mascDante"));
        touchControlPanel.setDanteGateway(resultSet.getString("gatewayDante"));
        touchControlPanel.setOutletNumber(resultSet.getString("outletNumber"));
        touchControlPanel.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        touchControlPanel.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        touchControlPanel.setDiagonal(resultSet.getString("diagonal"));
        return touchControlPanel;
    }
    private TvPanel getTvPanel(ResultSet resultSet) throws SQLException {
        TvPanel tvPanel = new TvPanel();
        tvPanel.setId(resultSet.getInt("id"));
        tvPanel.setName(resultSet.getString("name"));
        tvPanel.setModel(resultSet.getString("model"));
        tvPanel.setManufacturer(resultSet.getString("manufacturer"));
        tvPanel.setSerialNumber(resultSet.getString("serialNumber"));
        tvPanel.setIpAddress(resultSet.getString("ipAddress"));
        tvPanel.setMasc(resultSet.getString("masc"));
        tvPanel.setGateway(resultSet.getString("gateway"));
        tvPanel.setMacAddress(resultSet.getString("macAddress"));
        tvPanel.setMacAddress1(resultSet.getString("macAddress1"));
        tvPanel.setMacAddress2(resultSet.getString("macAddress2"));
        tvPanel.setMacAddress3(resultSet.getString("macAddress3"));
        tvPanel.setLogin(resultSet.getString("login"));
        tvPanel.setPassword(resultSet.getString("password"));
        tvPanel.setImage(resultSet.getString("image"));
        tvPanel.setRoom(resultSet.getString("room"));
        tvPanel.setLocation(resultSet.getString("location"));
        tvPanel.setCondition(resultSet.getString("condition_equipment"));
        tvPanel.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        tvPanel.setCompany(resultSet.getString("company"));
        tvPanel.setManual(resultSet.getString("manual"));
        tvPanel.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        tvPanel.setDanteMasc(resultSet.getString("mascDante"));
        tvPanel.setDanteGateway(resultSet.getString("gatewayDante"));
        tvPanel.setOutletNumber(resultSet.getString("outletNumber"));
        tvPanel.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        tvPanel.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        tvPanel.setDiagonal(resultSet.getString("diagonal"));
        return tvPanel;
    }
    private TvTuner getTvTuner(ResultSet resultSet) throws SQLException {
        TvTuner tvTuner = new TvTuner();
        tvTuner.setId(resultSet.getInt("id"));
        tvTuner.setName(resultSet.getString("name"));
        tvTuner.setModel(resultSet.getString("model"));
        tvTuner.setManufacturer(resultSet.getString("manufacturer"));
        tvTuner.setSerialNumber(resultSet.getString("serialNumber"));
        tvTuner.setIpAddress(resultSet.getString("ipAddress"));
        tvTuner.setMasc(resultSet.getString("masc"));
        tvTuner.setGateway(resultSet.getString("gateway"));
        tvTuner.setMacAddress(resultSet.getString("macAddress"));
        tvTuner.setMacAddress1(resultSet.getString("macAddress1"));
        tvTuner.setMacAddress2(resultSet.getString("macAddress2"));
        tvTuner.setMacAddress3(resultSet.getString("macAddress3"));
        tvTuner.setLogin(resultSet.getString("login"));
        tvTuner.setPassword(resultSet.getString("password"));
        tvTuner.setImage(resultSet.getString("image"));
        tvTuner.setRoom(resultSet.getString("room"));
        tvTuner.setLocation(resultSet.getString("location"));
        tvTuner.setCondition(resultSet.getString("condition_equipment"));
        tvTuner.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        tvTuner.setCompany(resultSet.getString("company"));
        tvTuner.setManual(resultSet.getString("manual"));
        tvTuner.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        tvTuner.setDanteMasc(resultSet.getString("mascDante"));
        tvTuner.setDanteGateway(resultSet.getString("gatewayDante"));
        tvTuner.setOutletNumber(resultSet.getString("outletNumber"));
        tvTuner.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        tvTuner.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return tvTuner;
    }
    private VideoReceiver getVideoReceiver(ResultSet resultSet) throws SQLException {
        VideoReceiver videoReceiver = new VideoReceiver();
        videoReceiver.setId(resultSet.getInt("id"));
        videoReceiver.setName(resultSet.getString("name"));
        videoReceiver.setModel(resultSet.getString("model"));
        videoReceiver.setManufacturer(resultSet.getString("manufacturer"));
        videoReceiver.setSerialNumber(resultSet.getString("serialNumber"));
        videoReceiver.setIpAddress(resultSet.getString("ipAddress"));
        videoReceiver.setMasc(resultSet.getString("masc"));
        videoReceiver.setGateway(resultSet.getString("gateway"));
        videoReceiver.setMacAddress(resultSet.getString("macAddress"));
        videoReceiver.setMacAddress1(resultSet.getString("macAddress1"));
        videoReceiver.setMacAddress2(resultSet.getString("macAddress2"));
        videoReceiver.setMacAddress3(resultSet.getString("macAddress3"));
        videoReceiver.setLogin(resultSet.getString("login"));
        videoReceiver.setPassword(resultSet.getString("password"));
        videoReceiver.setImage(resultSet.getString("image"));
        videoReceiver.setRoom(resultSet.getString("room"));
        videoReceiver.setLocation(resultSet.getString("location"));
        videoReceiver.setCondition(resultSet.getString("condition_equipment"));
        videoReceiver.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        videoReceiver.setCompany(resultSet.getString("company"));
        videoReceiver.setManual(resultSet.getString("manual"));
        videoReceiver.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        videoReceiver.setDanteMasc(resultSet.getString("mascDante"));
        videoReceiver.setDanteGateway(resultSet.getString("gatewayDante"));
        videoReceiver.setOutletNumber(resultSet.getString("outletNumber"));
        videoReceiver.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        videoReceiver.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return videoReceiver;
    }
    private VideoTransmitter getVideoTransmitter(ResultSet resultSet) throws SQLException {
        VideoTransmitter videoTransmitter = new VideoTransmitter();
        videoTransmitter.setId(resultSet.getInt("id"));
        videoTransmitter.setName(resultSet.getString("name"));
        videoTransmitter.setModel(resultSet.getString("model"));
        videoTransmitter.setManufacturer(resultSet.getString("manufacturer"));
        videoTransmitter.setSerialNumber(resultSet.getString("serialNumber"));
        videoTransmitter.setIpAddress(resultSet.getString("ipAddress"));
        videoTransmitter.setMasc(resultSet.getString("masc"));
        videoTransmitter.setGateway(resultSet.getString("gateway"));
        videoTransmitter.setMacAddress(resultSet.getString("macAddress"));
        videoTransmitter.setMacAddress1(resultSet.getString("macAddress1"));
        videoTransmitter.setMacAddress2(resultSet.getString("macAddress2"));
        videoTransmitter.setMacAddress3(resultSet.getString("macAddress3"));
        videoTransmitter.setLogin(resultSet.getString("login"));
        videoTransmitter.setPassword(resultSet.getString("password"));
        videoTransmitter.setImage(resultSet.getString("image"));
        videoTransmitter.setRoom(resultSet.getString("room"));
        videoTransmitter.setLocation(resultSet.getString("location"));
        videoTransmitter.setCondition(resultSet.getString("condition_equipment"));
        videoTransmitter.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        videoTransmitter.setCompany(resultSet.getString("company"));
        videoTransmitter.setManual(resultSet.getString("manual"));
        videoTransmitter.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        videoTransmitter.setDanteMasc(resultSet.getString("mascDante"));
        videoTransmitter.setDanteGateway(resultSet.getString("gatewayDante"));
        videoTransmitter.setOutletNumber(resultSet.getString("outletNumber"));
        videoTransmitter.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        videoTransmitter.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        return videoTransmitter;
    }

    private Equipment getEquipment(ResultSet resultSet) throws SQLException {
        Equipment equipment = new Equipment();

        if (resultSet.getString("name").equals(language.PROJECTOR("Русский"))
                || resultSet.getString("name").equals(language.PROJECTOR("English"))) {
            equipment = getProjector(resultSet);
        }
        if (resultSet.getString("name").equals(language.MICROPHONE("Русский"))
                || resultSet.getString("name").equals(language.MICROPHONE("English"))) {
            equipment = getMicrophone(resultSet);
        }
        if (resultSet.getString("name").equals(language.NETWORK_SWITCH("Русский"))
                || resultSet.getString("name").equals(language.NETWORK_SWITCH("English"))) {
            equipment = getNetworkSwitch(resultSet);
        }
        if (resultSet.getString("name").equals(language.ACOUSTIC_SPEAKER("Русский"))
                || resultSet.getString("name").equals(language.ACOUSTIC_SPEAKER("English"))) {
            equipment = getAcousticSpeaker(resultSet);
        }
        if (resultSet.getString("name").equals(language.AUDIO_AMPLIFIER("Русский"))
                || resultSet.getString("name").equals(language.AUDIO_AMPLIFIER("English"))) {
            equipment = getAudioAmplifier(resultSet);
        }
        if (resultSet.getString("name").equals(language.AUDIO_INTERFACE("Русский"))
                || resultSet.getString("name").equals(language.AUDIO_INTERFACE("English"))) {
            equipment = getAudioInterface(resultSet);
        }
        if (resultSet.getString("name").equals(language.AUDIO_PROCESSOR("Русский"))
                || resultSet.getString("name").equals(language.AUDIO_PROCESSOR("English"))) {
            equipment = getAudioProcessor(resultSet);
        }
        if (resultSet.getString("name").equals(language.CONTROL_PROCESSOR("Русский"))
                || resultSet.getString("name").equals(language.CONTROL_PROCESSOR("English"))) {
            equipment = getControlProcessor(resultSet);
        }
        if (resultSet.getString("name").equals(language.LAPTOP("Русский"))
                || resultSet.getString("name").equals(language.LAPTOP("English"))) {
            equipment = getLaptop(resultSet);
        }
        if (resultSet.getString("name").equals(language.MATRIX_SWITCHER("Русский"))
                || resultSet.getString("name").equals(language.MATRIX_SWITCHER("English"))) {
            equipment = getMatrixSwitcher(resultSet);
        }
        if (resultSet.getString("name").equals(language.MEDIA_PLAYER("Русский"))
                || resultSet.getString("name").equals(language.MEDIA_PLAYER("English"))) {
            equipment = getMediaPlayer(resultSet);
        }
        if (resultSet.getString("name").equals(language.TOUCH_CONTROL_PANEL("Русский"))
                || resultSet.getString("name").equals(language.TOUCH_CONTROL_PANEL("English"))) {
            equipment = getTouchControlPanel(resultSet);
        }
        if (resultSet.getString("name").equals(language.TV_PANEL("Русский"))
                || resultSet.getString("name").equals(language.TV_PANEL("English"))) {
            equipment = getTvPanel(resultSet);
        }
        if (resultSet.getString("name").equals(language.TV_TUNER("Русский"))
                || resultSet.getString("name").equals(language.TV_TUNER("English"))) {
            equipment = getTvTuner(resultSet);
        }
        if (resultSet.getString("name").equals(language.VIDEO_RECEIVER("Русский"))
                || resultSet.getString("name").equals(language.VIDEO_RECEIVER("English"))) {
            equipment = getVideoReceiver(resultSet);
        }
        if (resultSet.getString("name").equals(language.VIDEO_TRANSMITTER("Русский"))
                || resultSet.getString("name").equals(language.VIDEO_TRANSMITTER("English"))) {
            equipment = getVideoTransmitter(resultSet);
        }
        equipment.setId(resultSet.getInt("id"));
        equipment.setName(resultSet.getString("name"));
        equipment.setModel(resultSet.getString("model"));
        equipment.setManufacturer(resultSet.getString("manufacturer"));
        equipment.setSerialNumber(resultSet.getString("serialNumber"));
        equipment.setIpAddress(resultSet.getString("ipAddress"));
        equipment.setMasc(resultSet.getString("masc"));
        equipment.setGateway(resultSet.getString("gateway"));
        equipment.setMacAddress(resultSet.getString("macAddress"));
        equipment.setMacAddress1(resultSet.getString("macAddress1"));
        equipment.setMacAddress2(resultSet.getString("macAddress2"));
        equipment.setMacAddress3(resultSet.getString("macAddress3"));
        equipment.setLogin(resultSet.getString("login"));
        equipment.setPassword(resultSet.getString("password"));
        equipment.setImage(resultSet.getString("image"));
        equipment.setRoom(resultSet.getString("room"));
        equipment.setLocation(resultSet.getString("location"));
        equipment.setCondition(resultSet.getString("condition_equipment"));
        equipment.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        equipment.setCompany(resultSet.getString("company"));
        equipment.setManual(resultSet.getString("manual"));
        equipment.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        equipment.setDanteMasc(resultSet.getString("mascDante"));
        equipment.setDanteGateway(resultSet.getString("gatewayDante"));
        equipment.setOutletNumber(resultSet.getString("outletNumber"));
        equipment.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        equipment.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));

        return equipment;
    }

    private List<Company> getListCompany(List<Company> list, List<Equipment> listE){
        List<Company> returnList = new ArrayList<Company>();
        for (Company company1 : list){
            List<Equipment> e1 = new ArrayList<Equipment>();
            for (Equipment equipment1 : listE){
                if(company1.getNameCompany().equals(equipment1.getCompany())){
                    e1.add(equipment1);
                }
            }
            company1.setEquipmentList(e1);
            returnList.add(company1);
        }
        return returnList;
    }
}
