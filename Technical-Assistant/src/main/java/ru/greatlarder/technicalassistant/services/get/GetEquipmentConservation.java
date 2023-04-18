package ru.greatlarder.technicalassistant.services.get;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.*;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.check_equipment.CheckIpAddress;
import ru.greatlarder.technicalassistant.services.check.check_equipment.CheckIpAddressDante;
import ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite.CheckingIpDanteForEmploymentInTheDatabaseSQLite;
import ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite.CheckingIpForEmploymentInTheDatabaseSQLite;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentBySerialNumberSQLite;
import ru.greatlarder.technicalassistant.services.lang.GetLanguageName;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.GetLanguageNameEquipment;

import java.time.LocalDate;

public class GetEquipmentConservation {
    GetLanguageName language = new GetLanguageNameEquipment();

    public Equipment getEquipment(User user, String value){

        Equipment result = null;

        switch (value) {
            case Language.PROJECTOR_RU ->{
                result = new Projector();
                result.setName(language.getProjector(user.getLanguage()));
            }
            case Language.MICROPHONE_RU -> {
                result = new Microphone();
                result.setName(language.getMicrophone(user.getLanguage()));
            }
            case Language.NETWORK_SWITCH_RU ->{
                result = new NetworkSwitch();
                result.setName(language.getNetworkSwitch(user.getLanguage()));
            }
            case Language.ACOUSTIC_SPEAKER_RU ->{
                result = new AcousticSpeaker();
                result.setName(language.getAcousticSpeaker(user.getLanguage()));
            }
            case Language.CONTROL_PROCESSOR_RU ->{
                result = new ControlProcessor();
                result.setName(language.getControlProcessor(user.getLanguage()));
            }
            case Language.AUDIO_PROCESSOR_RU ->{
                result = new AudioProcessor();
                result.setName(language.getAudioProcessor(user.getLanguage()));
            }
            case Language.AUDIO_AMPLIFIER_RU ->{
                result = new AudioAmplifier();
                result.setName(language.getAudioAmplifier(user.getLanguage()));
            }
            case Language.AUDIO_INTERFACE_RU ->{
                result = new AudioInterface();
                result.setName(language.getAudioInterface(user.getLanguage()));
            }
            case Language.TV_PANEL_RU ->{
                result = new TvPanel();
                result.setName(language.getTvPanel(user.getLanguage()));
            }
            case Language.TV_TUNER_RU ->{
                result = new TvTuner();
                result.setName(language.getTvTuner(user.getLanguage()));
            }
            case Language.MEDIA_PLAYER_RU ->{
                result = new MediaPlayer();
                result.setName(language.getMediaPlayer(user.getLanguage()));
            }
            case Language.LAPTOP_RU ->{
                result = new Laptop();
                result.setName(language.getLaptop(user.getLanguage()));
            }
            case Language.VIDEO_TRANSMITTER_RU ->{
                result = new VideoTransmitter();
                result.setName(language.getVideoTransmitter(user.getLanguage()));
            }
            case Language.VIDEO_RECEIVER_RU ->{
                result = new VideoReceiver();
                result.setName(language.getVideoReceiver(user.getLanguage()));
            }
            case Language.MATRIX_SWITCHER_RU ->{
                result = new MatrixSwitcher();
                result.setName(language.getMatrixSwitcher(user.getLanguage()));
            }
            case Language.TOUCH_CONTROL_PANEL_RU ->{
                result = new TouchControlPanel();
                result.setName(language.getTouchControlPanel(user.getLanguage()));
            }
            case Language.CONTROLLER_RU ->{
                result = new Controller();
                result.setName(language.getController(user.getLanguage()));
            }

        }
        return result;
    }
    public Equipment loadEquipment(User user, Company company,Equipment equipment) {
        Equipment returnEquipment = null;
        if (equipment instanceof Projector) {
            returnEquipment = new Projector();
            ((Projector) returnEquipment).setTimeWorkLampProjector(0);
            returnEquipment.setImage("projector.png");
            ((Projector) returnEquipment).setMaximumLampOperatingTimeProjector(((Projector) equipment).getMaximumLampOperatingTimeProjector());
        }
        if (equipment instanceof Microphone) {
            returnEquipment = new Microphone();
            ((Microphone) returnEquipment).setFrequency(((Microphone) equipment).getFrequency());
            returnEquipment.setImage("microphone.png");
        }
        if (equipment instanceof NetworkSwitch) {
            returnEquipment = new NetworkSwitch();
            returnEquipment.setImage("network_switch.png");
        }
        if (equipment instanceof AcousticSpeaker) {
            returnEquipment = new AcousticSpeaker();
            returnEquipment.setImage("as.png");
        }
        if (equipment instanceof ControlProcessor) {
            returnEquipment = new ControlProcessor();
            returnEquipment.setImage("control_processor.png");
        }
        if (equipment instanceof AudioProcessor) {
            returnEquipment = new AudioProcessor();
            returnEquipment.setImage("audio_processor.png");
        }
        if (equipment instanceof AudioAmplifier) {
            returnEquipment = new AudioAmplifier();
            returnEquipment.setImage("audio_amplifer.png");
        }
        if (equipment instanceof AudioInterface) {
            returnEquipment = new AudioInterface();
            returnEquipment.setImage("audio_interface.png");
        }
        if (equipment instanceof TvPanel) {
            returnEquipment = new TvPanel();
            returnEquipment.setImage("tv_panel.png");
            ((TvPanel) returnEquipment).setDiagonal(((TvPanel) equipment).getDiagonal());
        }
        if (equipment instanceof TvTuner) {
            returnEquipment = new TvTuner();
            returnEquipment.setImage("tv_tuner.png");
        }
        if (equipment instanceof MediaPlayer) {
            returnEquipment = new MediaPlayer();
            if(equipment.getImage() != null){
                returnEquipment.setImage(equipment.getImage());
            }else returnEquipment.setImage("media_player.png");
        }
        if (equipment instanceof Laptop) {
            returnEquipment = new Laptop();
            returnEquipment.setImage("laptop.png");
            ((Laptop) returnEquipment).setOs(((Laptop) equipment).getOs());
        }
        if (equipment instanceof VideoTransmitter) {
            returnEquipment = new VideoTransmitter();
            returnEquipment.setImage("tx_rx.jpg");
        }
        if (equipment instanceof VideoReceiver) {
            returnEquipment = new VideoReceiver();
            returnEquipment.setImage("tx_rx.jpg");
        }
        if (equipment instanceof MatrixSwitcher) {
            returnEquipment = new MatrixSwitcher();
            returnEquipment.setImage("matrix_switcher.jpg");
        }
        if (equipment instanceof TouchControlPanel) {
            returnEquipment = new TouchControlPanel();
            returnEquipment.setImage("control_patch_panel.png");
            ((TouchControlPanel) returnEquipment).setDiagonal(((TouchControlPanel) equipment).getDiagonal());
        }
        if (equipment instanceof Controller) {
            returnEquipment = new Controller();
            returnEquipment.setImage("control_processor.png");
        }

        assert returnEquipment != null;
        returnEquipment.setName(equipment.getName());
        returnEquipment.setModel(equipment.getModel());
        returnEquipment.setManufacturer(equipment.getManufacturer());
        returnEquipment.setSerialNumber(equipment.getSerialNumber());
        returnEquipment.setMacAddress(equipment.getMacAddress());
        returnEquipment.setMacAddress1(equipment.getMacAddress1());
        returnEquipment.setMacAddress2(equipment.getMacAddress2());
        returnEquipment.setMacAddress3(equipment.getMacAddress3());
        returnEquipment.setLogin(equipment.getLogin());
        returnEquipment.setPassword(equipment.getPassword());
        returnEquipment.setRoom(equipment.getRoom());
        returnEquipment.setLocation(equipment.getLocation());
        if (equipment.getDateWork() == null) {
            returnEquipment.setDateWork(LocalDate.now());
        } else returnEquipment.setDateWork(equipment.getDateWork());
        CheckIpAddress checkIpAddress = new CheckingIpForEmploymentInTheDatabaseSQLite();
        if (equipment.getIpAddress() != null
        && !checkIpAddress.checkIpAddress(user, company, equipment.getIpAddress())) {
            returnEquipment.setIpAddress(equipment.getIpAddress());
        } else returnEquipment.setIpAddress(null);

        returnEquipment.setMasc(equipment.getMasc());
        returnEquipment.setGateway(equipment.getGateway());
        CheckIpAddressDante checkIpAddressDante = new CheckingIpDanteForEmploymentInTheDatabaseSQLite();
        if (equipment.getDanteIpAddress() != null
                && !checkIpAddressDante.checkIpAddress(user, company, equipment.getDanteIpAddress())) {
            returnEquipment.setDanteIpAddress(equipment.getDanteIpAddress());
        } else returnEquipment.setDanteIpAddress(null);

        returnEquipment.setDanteMasc(equipment.getDanteMasc());
        returnEquipment.setDanteGateway(equipment.getDanteGateway());
        returnEquipment.setCondition(equipment.getCondition());
        returnEquipment.setCompany(company.getNameCompany());
        returnEquipment.setManual(equipment.getManual());

        GetEquipment getEquipment = new EquipmentBySerialNumberSQLite();
        if (equipment.getIdNetworkSwitcher() != null) {
            returnEquipment.setIdNetworkSwitcher((getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getIdNetworkSwitcher())).getId()));
            returnEquipment.setPortNumberInTheSwitch(equipment.getPortNumberInTheSwitch());
            returnEquipment.setOutletNumber(equipment.getOutletNumber());
        }

        if(returnEquipment.getSerialNumber() != null || !returnEquipment.getSerialNumber().isEmpty()){
            return returnEquipment;
        } else return null;
    }
}
