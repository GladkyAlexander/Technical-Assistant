package ru.great_larder.technical_assistant.database.general;

import android.os.Build;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

import ru.great_larder.technical_assistant.domain.Equipment;
import ru.great_larder.technical_assistant.domain.equipment.AcousticSpeaker;
import ru.great_larder.technical_assistant.domain.equipment.AudioAmplifier;
import ru.great_larder.technical_assistant.domain.equipment.AudioInterface;
import ru.great_larder.technical_assistant.domain.equipment.AudioProcessor;
import ru.great_larder.technical_assistant.domain.equipment.ControlProcessor;
import ru.great_larder.technical_assistant.domain.equipment.Laptop;
import ru.great_larder.technical_assistant.domain.equipment.MatrixSwitcher;
import ru.great_larder.technical_assistant.domain.equipment.MediaPlayer;
import ru.great_larder.technical_assistant.domain.equipment.Microphone;
import ru.great_larder.technical_assistant.domain.equipment.NetworkSwitch;
import ru.great_larder.technical_assistant.domain.equipment.Projector;
import ru.great_larder.technical_assistant.domain.equipment.TouchControlPanel;
import ru.great_larder.technical_assistant.domain.equipment.TvPanel;
import ru.great_larder.technical_assistant.domain.equipment.TvTuner;
import ru.great_larder.technical_assistant.domain.equipment.VideoReceiver;
import ru.great_larder.technical_assistant.domain.equipment.VideoTransmitter;
import ru.great_larder.technical_assistant.lang.Language;
import ru.great_larder.technical_assistant.lang.impl.LanguageImpl;

public class GetEquipmentServiceImpl implements GetEquipmentService {
    Language language = new LanguageImpl();
    @Override
    public Equipment getEquipment(ResultSet resultSet) throws SQLException {

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
        equipment.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            equipment.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        equipment.setCompany(resultSet.getString("company"));
        equipment.setManual(resultSet.getString("manual"));
        equipment.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        equipment.setDanteMasc(resultSet.getString("mascDante"));
        equipment.setDanteGateway(resultSet.getString("gatewayDante"));
        equipment.setOutletNumber(resultSet.getString("outletNumber"));
        equipment.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        equipment.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        equipment.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
        equipment.setUserVisibility(false);
        } else equipment.setUserVisibility(true);
        return equipment;
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
        projector.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            projector.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        projector.setCompany(resultSet.getString("company"));
        projector.setManual(resultSet.getString("manual"));
        projector.setOutletNumber(resultSet.getString("outletNumber"));
        projector.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        projector.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        projector.setTimeWorkLampProjector(resultSet.getInt("timeWorkLampProjector"));
        projector.setMaximumLampOperatingTimeProjector(resultSet.getInt("maximumLampOperatingTimeProjector"));
        projector.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            projector.setUserVisibility(false);
        } else projector.setUserVisibility(true);

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
        microphone.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            microphone.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        microphone.setCompany(resultSet.getString("company"));
        microphone.setManual(resultSet.getString("manual"));
        microphone.setOutletNumber(resultSet.getString("outletNumber"));
        microphone.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        microphone.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        microphone.setFrequency(resultSet.getString("frequencyMicrophone"));
        microphone.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            microphone.setUserVisibility(false);
        } else microphone.setUserVisibility(true);

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
        networkSwitch.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            networkSwitch.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        networkSwitch.setCompany(resultSet.getString("company"));
        networkSwitch.setManual(resultSet.getString("manual"));
        networkSwitch.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        networkSwitch.setDanteMasc(resultSet.getString("mascDante"));
        networkSwitch.setDanteGateway(resultSet.getString("gatewayDante"));
        networkSwitch.setOutletNumber(resultSet.getString("outletNumber"));
        networkSwitch.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            networkSwitch.setUserVisibility(false);
        } else networkSwitch.setUserVisibility(true);

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
        acousticSpeaker.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            acousticSpeaker.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        acousticSpeaker.setCompany(resultSet.getString("company"));
        acousticSpeaker.setManual(resultSet.getString("manual"));
        acousticSpeaker.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        acousticSpeaker.setDanteMasc(resultSet.getString("mascDante"));
        acousticSpeaker.setDanteGateway(resultSet.getString("gatewayDante"));
        acousticSpeaker.setOutletNumber(resultSet.getString("outletNumber"));
        acousticSpeaker.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        acousticSpeaker.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        acousticSpeaker.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            acousticSpeaker.setUserVisibility(false);
        } else acousticSpeaker.setUserVisibility(true);
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
        audioAmplifier.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioAmplifier.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        audioAmplifier.setCompany(resultSet.getString("company"));
        audioAmplifier.setManual(resultSet.getString("manual"));
        audioAmplifier.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        audioAmplifier.setDanteMasc(resultSet.getString("mascDante"));
        audioAmplifier.setDanteGateway(resultSet.getString("gatewayDante"));
        audioAmplifier.setOutletNumber(resultSet.getString("outletNumber"));
        audioAmplifier.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        audioAmplifier.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        audioAmplifier.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            audioAmplifier.setUserVisibility(false);
        } else audioAmplifier.setUserVisibility(true);

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
        audioInterface.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioInterface.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        audioInterface.setCompany(resultSet.getString("company"));
        audioInterface.setManual(resultSet.getString("manual"));
        audioInterface.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        audioInterface.setDanteMasc(resultSet.getString("mascDante"));
        audioInterface.setDanteGateway(resultSet.getString("gatewayDante"));
        audioInterface.setOutletNumber(resultSet.getString("outletNumber"));
        audioInterface.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        audioInterface.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        audioInterface.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            audioInterface.setUserVisibility(false);
        } else audioInterface.setUserVisibility(true);
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
        audioProcessor.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioProcessor.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        audioProcessor.setCompany(resultSet.getString("company"));
        audioProcessor.setManual(resultSet.getString("manual"));
        audioProcessor.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        audioProcessor.setDanteMasc(resultSet.getString("mascDante"));
        audioProcessor.setDanteGateway(resultSet.getString("gatewayDante"));
        audioProcessor.setOutletNumber(resultSet.getString("outletNumber"));
        audioProcessor.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        audioProcessor.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        audioProcessor.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            audioProcessor.setUserVisibility(false);
        } else audioProcessor.setUserVisibility(true);
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
        controlProcessor.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            controlProcessor.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        controlProcessor.setCompany(resultSet.getString("company"));
        controlProcessor.setManual(resultSet.getString("manual"));
        controlProcessor.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        controlProcessor.setDanteMasc(resultSet.getString("mascDante"));
        controlProcessor.setDanteGateway(resultSet.getString("gatewayDante"));
        controlProcessor.setOutletNumber(resultSet.getString("outletNumber"));
        controlProcessor.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        controlProcessor.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        controlProcessor.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            controlProcessor.setUserVisibility(false);
        } else controlProcessor.setUserVisibility(true);
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
        laptop.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            laptop.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        laptop.setCompany(resultSet.getString("company"));
        laptop.setManual(resultSet.getString("manual"));
        laptop.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        laptop.setDanteMasc(resultSet.getString("mascDante"));
        laptop.setDanteGateway(resultSet.getString("gatewayDante"));
        laptop.setOutletNumber(resultSet.getString("outletNumber"));
        laptop.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        laptop.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        laptop.setOs(resultSet.getString("operating_system"));
        laptop.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            laptop.setUserVisibility(false);
        } else laptop.setUserVisibility(true);
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
        matrixSwitcher.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            matrixSwitcher.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        matrixSwitcher.setCompany(resultSet.getString("company"));
        matrixSwitcher.setManual(resultSet.getString("manual"));
        matrixSwitcher.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        matrixSwitcher.setDanteMasc(resultSet.getString("mascDante"));
        matrixSwitcher.setDanteGateway(resultSet.getString("gatewayDante"));
        matrixSwitcher.setOutletNumber(resultSet.getString("outletNumber"));
        matrixSwitcher.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        matrixSwitcher.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        matrixSwitcher.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            matrixSwitcher.setUserVisibility(false);
        } else matrixSwitcher.setUserVisibility(true);
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
        mediaPlayer.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mediaPlayer.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        mediaPlayer.setCompany(resultSet.getString("company"));
        mediaPlayer.setManual(resultSet.getString("manual"));
        mediaPlayer.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        mediaPlayer.setDanteMasc(resultSet.getString("mascDante"));
        mediaPlayer.setDanteGateway(resultSet.getString("gatewayDante"));
        mediaPlayer.setOutletNumber(resultSet.getString("outletNumber"));
        mediaPlayer.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        mediaPlayer.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        mediaPlayer.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            mediaPlayer.setUserVisibility(false);
        } else mediaPlayer.setUserVisibility(true);
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
        touchControlPanel.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            touchControlPanel.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        touchControlPanel.setCompany(resultSet.getString("company"));
        touchControlPanel.setManual(resultSet.getString("manual"));
        touchControlPanel.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        touchControlPanel.setDanteMasc(resultSet.getString("mascDante"));
        touchControlPanel.setDanteGateway(resultSet.getString("gatewayDante"));
        touchControlPanel.setOutletNumber(resultSet.getString("outletNumber"));
        touchControlPanel.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        touchControlPanel.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        touchControlPanel.setDiagonal(resultSet.getString("diagonal"));
        touchControlPanel.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            touchControlPanel.setUserVisibility(false);
        } else touchControlPanel.setUserVisibility(true);
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
        tvPanel.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvPanel.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        tvPanel.setCompany(resultSet.getString("company"));
        tvPanel.setManual(resultSet.getString("manual"));
        tvPanel.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        tvPanel.setDanteMasc(resultSet.getString("mascDante"));
        tvPanel.setDanteGateway(resultSet.getString("gatewayDante"));
        tvPanel.setOutletNumber(resultSet.getString("outletNumber"));
        tvPanel.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        tvPanel.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        tvPanel.setDiagonal(resultSet.getString("diagonal"));
        tvPanel.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            tvPanel.setUserVisibility(false);
        } else tvPanel.setUserVisibility(true);
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
        tvTuner.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvTuner.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        tvTuner.setCompany(resultSet.getString("company"));
        tvTuner.setManual(resultSet.getString("manual"));
        tvTuner.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        tvTuner.setDanteMasc(resultSet.getString("mascDante"));
        tvTuner.setDanteGateway(resultSet.getString("gatewayDante"));
        tvTuner.setOutletNumber(resultSet.getString("outletNumber"));
        tvTuner.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        tvTuner.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        tvTuner.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            tvTuner.setUserVisibility(false);
        } else tvTuner.setUserVisibility(true);
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
        videoReceiver.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            videoReceiver.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        videoReceiver.setCompany(resultSet.getString("company"));
        videoReceiver.setManual(resultSet.getString("manual"));
        videoReceiver.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        videoReceiver.setDanteMasc(resultSet.getString("mascDante"));
        videoReceiver.setDanteGateway(resultSet.getString("gatewayDante"));
        videoReceiver.setOutletNumber(resultSet.getString("outletNumber"));
        videoReceiver.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        videoReceiver.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        videoReceiver.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            videoReceiver.setUserVisibility(false);
        } else videoReceiver.setUserVisibility(true);
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
        videoTransmitter.setCondition(resultSet.getString("condition_e"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            videoTransmitter.setDateWork(resultSet.getDate("dateWork").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        videoTransmitter.setCompany(resultSet.getString("company"));
        videoTransmitter.setManual(resultSet.getString("manual"));
        videoTransmitter.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        videoTransmitter.setDanteMasc(resultSet.getString("mascDante"));
        videoTransmitter.setDanteGateway(resultSet.getString("gatewayDante"));
        videoTransmitter.setOutletNumber(resultSet.getString("outletNumber"));
        videoTransmitter.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        videoTransmitter.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        videoTransmitter.setType(resultSet.getString("type"));
        if(resultSet.getInt("user_visibility_status") == 0){
            videoTransmitter.setUserVisibility(false);
        } else videoTransmitter.setUserVisibility(true);
        return videoTransmitter;
    }
}
