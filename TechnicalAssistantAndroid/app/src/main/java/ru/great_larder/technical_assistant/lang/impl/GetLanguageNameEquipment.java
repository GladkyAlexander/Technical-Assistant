package ru.great_larder.technical_assistant.lang.impl;


import ru.great_larder.technical_assistant.lang.GetLanguageName;

public class GetLanguageNameEquipment implements GetLanguageName {
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
        if(language.equals(ru)) return "Тв панель";
        return null;
    }

    @Override
    public String getTvTuner(String language) {
        if(language.equals(en)) return "TV Tuner";
        if(language.equals(ru)) return "Тв тюнер";
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
}
