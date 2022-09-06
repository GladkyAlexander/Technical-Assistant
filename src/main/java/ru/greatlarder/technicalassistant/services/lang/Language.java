package ru.greatlarder.technicalassistant.services.lang;

import java.util.List;

public interface Language {
    String ADDED(String language);
    String WILL_NOT_BE_ADDED(String language);
    String PROJECTOR(String language);
    String MICROPHONE(String language);
    String CONTROL_PROCESSOR(String language);
    String AUDIO_PROCESSOR(String language);
    String AUDIO_AMPLIFIER(String language);
    String ACOUSTIC_SPEAKER(String language);
    String AUDIO_INTERFACE(String language);
    String TV_PANEL(String language);
    String TV_TUNER(String language);
    String MEDIA_PLAYER(String language);
    String LAPTOP(String language);
    String VIDEO_TRANSMITTER(String language);
    String VIDEO_RECEIVER(String language);
    String MATRIX_SWITCHER(String language);
    String NETWORK_SWITCH(String language);
    String TOUCH_CONTROL_PANEL(String language);
    String PATHS_PANEL(String language);
    String POST(String language);
    String CONFIGURE_PERSONAL_DATA(String language);
    String ADD_A_COMPANY (String language);
    String REGISTRY(String language);
    String SAVE(String language);
    String LAST_NAME(String language);
    String FIRST_NAME(String language);
    String MAIL_PASSWORD(String language);
    String HOST(String language);
    String WEBSITE (String language);
    String PHONE(String language);
    String EMAIL(String language);
    String INFO_TEXT (String language);
    String FILL_IN_THE_DB(String language);
    String FAULTY (String language);
    String PREPARED_FOR_DISPOSAL (String language);
    String DISPOSED_OF (String language);
    String SPARE_PARTS_INCLUDED (String language);
    String MAIL_FOR_MONITORING_EMAILS(String language);
    String EMAIL_PASSWORD(String language);
    String SUBJECT_OF_THE_LETTER(String language);
    String THE_WORDS_SHOULD(String language);
    String EXAMPLE(String language);
    String DATE(String language);
    String WHITESPACE(String language);
    String TIME(String language);
    String MEETINGS(String language);
    String OFFICE(String language);
    String CUSTOMER(String language);
    String ROOM(String language);
    String THEN_AN_ARBITRARY(String language);
    String DATE_STORAGE_LOCATION(String language);
    String APPLICATION_FOLDER(String language);
    String DATABASE_HOSTING_FOLDER(String language);
    String SELECT_A_LANGUAGE(String language);
    String MAIL_SETTINGS(String language);
    String NO_DEVICE_WITH_THIS_SERIAL_NUMBER(String language);
    String ENTER_THE_SERIAL_NUMBER(String language);
    String TASK_TEXT(String language);
    String OUTLET_NUMBER(String language);
    String PORT_NUMBER_IN_THE_SWITCH(String language);
    String HOME_PAGE(String language);
    String DOCUMENTATION(String language);
    String SETTINGS(String language);
    String INFORMATION(String language);
    String CHOOSE_A_COMPANY(String language);
    String WE_ARE_GLAD_OF_YOUR_CHOICE(String language);
    String START_BY_REGISTERING_ON_THE_SETTINGS_PAGE(String language);
    String NUMBER_OF_TOOLS(String language);
    String THE_NUMBER_OF_DEVICES_OPERATING_FOR_MORE_THAN_5_YEARS(String language);
    String NUMBER_OF_FAULTY_DEVICES(String language);
    String NUMBER_OF_DEVICES(String language);
    String GENERATE_A_ZIP_DOCUMENT(String language);
    String GENERATE_A_TIMESHEET(String language);
    String GENERATE_A_DOCUMENT_OF_THE_OPERATION_OF_THE_PROJECTOR_LAMP(String language);
    String NAME_COMPANY(String language);
    String COMPANY_ADDRESS(String language);
    String CONTACT_PERSON(String language);
    String REPRESENTATIVES_PHONE_NUMBER(String language);
    String REPRESENTATIVES_EMAIL(String language);
    String COMPANY_LOGO(String language);
    String MANAGER(String language);
    String ENGINEER(String language);
    String MANAGER_PHONE(String language);
    String ENGINEER_PHONE(String language);
    String ENGINEER_MAIL(String language);
    String MANAGER_MAIL(String language);
    String ALREADY_HAVE(String language);
    String RESPONSIBLE_MANAGER(String language);
    String RESPONSIBLE_ENGINEER(String language);
    String SELECT_A_COUNTRY(String language);
    String PROFILE_SETTINGS (String language);
    List<String> status_sheet(String language);
    List<String> LIST_POST(String language);
    String ACCOUNTING(String lang);
    String MODEL(String lang);

    String MANUFACTURER(String lang);

    String SERIAL_NUMBER(String lang);

    String MAC_ADDRESS(String lang);

    String LOGIN(String lang);

    String PASSWORD(String lang);

    String LOCATION_IN_THE_ROOM(String lang);

    String START_DATE_OF_OPERATION(String lang);

    String CONDITION(String lang);

    String INSTRUCTION(String lang);

    String IP_ADDRESS(String lang);

    String MAXIMUM_LAMP_OPERATING_TIME(String lang);

    String FREQUENCY(String lang);

    String IP_ADDRESS_DANTE(String lang);
    String ENTER_THE_FIRST_WORD(String language);

    String SELECT_A_NETWORK_SWITCH(String lang);

    String SOCKET_NUMBER(String lang);

    String DIAGONAL(String lang);

    String INCHES(String lang);

    String ENTER_THE_NAME_OF_THE_OPERATING_SYSTEM(String lang);

    String SELECT_THE_NAME_OF_THE_EQUIPMENT(String lang);

    String SELECT_THE_DEVICE_STATUS(String lang);

    String TODAY_DATE_WILL_BE_SET_RU(String lang);

    String EQUIPMENT(Language language);

    String FILL_IN_THE_FIELDS(String lang);
    String PROJECTOR_RU = "Проектор";
    String MICROPHONE_RU = "Микрофон";
    String CONTROL_PROCESSOR_RU = "Процессор управления";
    String AUDIO_PROCESSOR_RU = "Аудио процессор";
    String AUDIO_AMPLIFIER_RU = "Аудио усилитель";
    String ACOUSTIC_SPEAKER_RU = "Акустическая колонка";
    String AUDIO_INTERFACE_RU = "Аудио интерфейс";
    String TV_PANEL_RU = "Телевизионная панель";
    String TV_TUNER_RU = "Телевизионный тюнер";
    String MEDIA_PLAYER_RU = "Медиа плеер";
    String LAPTOP_RU = "Ноутбук";
    String VIDEO_TRANSMITTER_RU = "Видео передатчик";
    String VIDEO_RECEIVER_RU = "Видео приемник";
    String MATRIX_SWITCHER_RU = "Матричный коммутатор";
    String NETWORK_SWITCH_RU = "Сетевой коммутатор";
    String TOUCH_CONTROL_PANEL_RU = "Сенсорная панель управления";
    String PATHS_PANEL_RU = "Патч панель";
    String LOCATION(String lang);
    String NAME(String lang);

    String MANUAL(String lang);

    String WEB_INTERFACE(String lang);

    String DEFECT(String lang);

    String NEW_LOCATION(String lang);

    String NEW_ROOM(String lang);

    String NEW_PASSWORD(String lang);

    String NEW_LOGIN(String lang);

    String NEW_IP_ADDRESS(String lang);

    String NEW_MASC(String lang);

    String NEW_GATEWAY(String lang);

    String NEW_IP_ADDRESS_DANTE(String lang);

    String NEW_MASC_DANTE(String lang);

    String NEW_GATEWAY_DANTE(String lang);

    String CHANGE_FREQUENCY(String lang);

    String NEW_TIME_WORK_LAMP_PROJECTOR(String lang);

    String CHANGE_CONDITION(String lang);

    String ADD(String lang1);

    String ALL_ACTIVE_APPLICATIONS(String lang);

    String ALL_APPLICATIONS(String lang);

    String NAME_TOOL(String lang);

    String SATISFACTORY(String lang);

    String TOOL_WITH_SN(String lang);

    String NOT_ADDED(String lang);

    String A_TOOL_WITH_THIS_SERIAL_NUMBER_HAS_ALREADY_BEEN_ADDED(String lang);

    String REPAIR(String lang);

    String ENTER_THE_DATA(String lang);

    String ENTER(String lang);

    String MALFUNCTION(String lang);

    String INITIATORS_FULL_NAME(String lang);

    String CHOOSE_A_ROOM(String lang);

    String SELECT_EQUIPMENT_BY_SERIAL_NUMBER(String lang);

    String FIXED(String lang);

    String NOTE(String lang);

    String HOW_FIXED(String lang);

    String SPECIFY_THE_REASON(String lang);

    String ACTIVE_DEFECTS(String lang);

    String ALL_DEFECTS(String lang);

    String OPERATING_HOURS(String lang);
}
