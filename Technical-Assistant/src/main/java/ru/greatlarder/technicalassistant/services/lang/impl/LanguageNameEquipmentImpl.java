package ru.greatlarder.technicalassistant.services.lang.impl;

import ru.greatlarder.technicalassistant.services.lang.LanguageNameEquipment;

import java.util.ArrayList;
import java.util.List;

public class LanguageNameEquipmentImpl implements LanguageNameEquipment {
    String en = "English";
    String ru = "Русский";
    @Override
    public String getProjector(String language) {
        if(language.equals(en)) return "Projector";
        if(language.equals(ru)) return "Проектор";
        return null;
    }

    @Override
    public String getMicrophone(String language) {
        if(language.equals(en)) return "Microphone";
        if(language.equals(ru)) return "Микрофон";
        return null;
    }

    @Override
    public String getNetworkSwitch(String language) {
        if(language.equals(en)) return "Network Switch";
        if(language.equals(ru)) return "Сетевой коммутатор";
        return null;
    }

    @Override
    public String getAcousticSpeaker(String language) {
        if(language.equals(en)) return "Acoustic speakers";
        if(language.equals(ru)) return "Акустические колонки";
        return null;
    }

    @Override
    public String getControlProcessor(String language) {
        if(language.equals(en)) return "Control Processor";
        if(language.equals(ru)) return "Процессор управления";
        return null;
    }

    @Override
    public String getAudioProcessor(String language) {
        if(language.equals(en)) return "Audio Processor";
        if(language.equals(ru)) return "Аудио процессор";
        return null;
    }

    @Override
    public String getAudioAmplifier(String language) {
        if(language.equals(en)) return "Audio amplifier";
        if(language.equals(ru)) return "Усилитель звука";
        return null;
    }

    @Override
    public String getAudioInterface(String language) {
        if(language.equals(en)) return "Audio Interface";
        if(language.equals(ru)) return "Аудио интерфейс";
        return null;
    }

    @Override
    public String getTvPanel(String language) {
        if(language.equals(en)) return "TV panel";
        if(language.equals(ru)) return "ТВ панель";
        return null;
    }

    @Override
    public String getTvTuner(String language) {
        if(language.equals(en)) return "TV Tuner";
        if(language.equals(ru)) return "ТВ тюнер";
        return null;
    }

    @Override
    public String getMediaPlayer(String language) {
        if(language.equals(en)) return "Media Player";
        if(language.equals(ru)) return "Медиа плеер";
        return null;
    }

    @Override
    public String getLaptop(String language) {
        if(language.equals(en)) return "Laptop";
        if(language.equals(ru)) return "Ноутбук";
        return null;
    }

    @Override
    public String getVideoTransmitter(String language) {
        if(language.equals(en)) return "Video transmitter";
        if(language.equals(ru)) return "Видео передатчик";
        return null;
    }

    @Override
    public String getVideoReceiver(String language) {
        if(language.equals(en)) return "Video receiver";
        if(language.equals(ru)) return "Видео приемник";
        return null;
    }

    @Override
    public String getMatrixSwitcher(String language) {
        if(language.equals(en)) return "Matrix Switch";
        if(language.equals(ru)) return "Матричный коммутатор";
        return null;
    }

    @Override
    public String getTouchControlPanel(String language) {
        if(language.equals(en)) return "Touch Control Pane";
        if(language.equals(ru)) return "Сенсорная панель управления";
        return null;
    }

    @Override
    public String getController(String language) {
        if(language.equals(en)) return "Controller";
        if(language.equals(ru)) return "Контроллер";
        return null;
    }
    
    @Override
    public List<String> getListNameEquipment(String language) {
        List<String> list = new ArrayList<>();
        if(language.equals(en)) {
            list.add("Acoustic Speaker");
            list.add("Audio Amplifier");
            list.add("Audio Interface");
            list.add("Audio Processor");
            list.add("Controller");
            list.add("Control Processor");
            list.add("Laptop");
            list.add("Matrix Switcher");
            list.add("Media Player");
            list.add("Microphone");
            list.add("Network Switch");
            list.add("Projector");
            list.add("Touch Control Panel");
            list.add("TV panel");
            list.add("TV tuner");
            list.add("Video receiver");
            list.add("Video transmitter");
        }
        if(language.equals(ru)) {
            list.add("Акустическая колонка");
            list.add("Аудио усилитель");
            list.add("Аудио интерфейс");
            list.add("Аудио процессор");
            list.add("Контроллер");
            list.add("Процессор управления");
            list.add("Компьютер");
            list.add("Матричный коммутатор");
            list.add("Медиа плеер");
            list.add("Микрофон");
            list.add("Сетевой коммутатор");
            list.add("Проектор");
            list.add("Сенсорная панель управления");
            list.add("ТВ панель");
            list.add("ТВ тюнер");
            list.add("Видео приемник");
            list.add("Видео передатчик");
        }
        return list;
    }
    public String getNameEquipment(String name) {
        
        List<String> england = new ArrayList<>();
        england.add("Acoustic Speaker");
        england.add("Audio Amplifier");
        england.add("Audio Interface");
        england.add("Audio Processor");
        england.add("Controller");
        england.add("Control Processor");
        england.add("Laptop");
        england.add("Matrix Switcher");
        england.add("Media Player");
        england.add("Microphone");
        england.add("Network Switch");
        england.add("Projector");
        england.add("Touch Control Panel");
        england.add("TV panel");
        england.add("TV tuner");
        england.add("Video receiver");
        england.add("Video transmitter");
        
        List<String> russia = new ArrayList<>();
        russia.add("Акустическая колонка");
        russia.add("Аудио усилитель");
        russia.add("Аудио интерфейс");
        russia.add("Аудио процессор");
        russia.add("Контроллер");
        russia.add("Процессор управления");
        russia.add("Компьютер");
        russia.add("Матричный коммутатор");
        russia.add("Медиа плеер");
        russia.add("Микрофон");
        russia.add("Сетевой коммутатор");
        russia.add("Проектор");
        russia.add("Сенсорная панель управления");
        russia.add("ТВ панель");
        russia.add("ТВ тюнер");
        russia.add("Видео приемник");
        russia.add("Видео передатчик");
        
        for (String e : england){
            if(e.equals(name)){
                return e;
            }
        }
        for (String r : russia){
            if(r.equals(name)){
                return r;
            }
        }
        return null;
    }
  
}
