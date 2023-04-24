package ru.great_larder.technical_assistant.lang.impl;


import java.util.ArrayList;
import java.util.List;

import ru.great_larder.technical_assistant.lang.Language;

public class LanguageImpl implements Language {
    String en = "English";
    String ru = "Русский";

    @Override
    public String ADDED(String language) {
        String added = "";
        if (language.equals(ru)) {
            added = "Добавлен!";
        } else if (language.equals(en)) {
            added = "Added!";
        }
        return added;
    }

    @Override
    public String WILL_NOT_BE_ADDED(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Не будет добавлено!";
        } else if (language.equals(en)) {
            will_not = "Will not be added!";
        }
        return will_not;
    }

    @Override
    public String PROJECTOR(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Проектор";
        } else if (language.equals(en)) {
            will_not = "Projector";
        }
        return will_not;
    }

    @Override
    public String MICROPHONE(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Микрофон";
        } else if (language.equals(en)) {
            will_not = "Microphone";
        }
        return will_not;
    }

    @Override
    public String CONTROL_PROCESSOR(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Процессор управления";
        } else if (language.equals(en)) {
            will_not = "Control processor";
        }
        return will_not;
    }

    @Override
    public String AUDIO_PROCESSOR(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Аудио процессор";
        } else if (language.equals(en)) {
            will_not = "Audio processor";
        }
        return will_not;
    }

    @Override
    public String AUDIO_AMPLIFIER(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Аудио усилитель";
        } else if (language.equals(en)) {
            will_not = "Audio amplifier";
        }
        return will_not;
    }

    @Override
    public String ACOUSTIC_SPEAKER(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Акустическая колонка";
        } else if (language.equals(en)) {
            will_not = "Audio speaker";
        }
        return will_not;
    }

    @Override
    public String AUDIO_INTERFACE(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Аудио интерфейс";
        } else if (language.equals(en)) {
            will_not = "Audio interface";
        }
        return will_not;
    }

    @Override
    public String TV_PANEL(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Телевизионная панель";
        } else if (language.equals(en)) {
            will_not = "TV panel";
        }
        return will_not;
    }

    @Override
    public String TV_TUNER(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Телевизионный тюнер";
        } else if (language.equals(en)) {
            will_not = "TV tuner";
        }
        return will_not;
    }

    @Override
    public String MEDIA_PLAYER(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Медиа плеер";
        } else if (language.equals(en)) {
            will_not = "Media player";
        }
        return will_not;
    }

    @Override
    public String LAPTOP(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Ноутбук";
        } else if (language.equals(en)) {
            will_not = "Laptop";
        }
        return will_not;
    }

    @Override
    public String VIDEO_TRANSMITTER(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Видео передатчик";
        } else if (language.equals(en)) {
            will_not = "Video transmitter";
        }
        return will_not;
    }

    @Override
    public String VIDEO_RECEIVER(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Видео приемник";
        } else if (language.equals(en)) {
            will_not = "Video receiver";
        }
        return will_not;
    }

    @Override
    public String MATRIX_SWITCHER(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Матричный коммутатор";
        } else if (language.equals(en)) {
            will_not = "Matrix switcher";
        }
        return will_not;
    }

    @Override
    public String NETWORK_SWITCH(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Сетевой коммутатор";
        } else if (language.equals(en)) {
            will_not = "Network switch";
        }
        return will_not;
    }

    @Override
    public String TOUCH_CONTROL_PANEL(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Сенсорная панель управления";
        } else if (language.equals(en)) {
            will_not = "Touch control panel";
        }
        return will_not;
    }

    @Override
    public String PATHS_PANEL(String language) {
        String will_not = "";
        if (language.equals(ru)) {
            will_not = "Патч панель";
        } else if (language.equals(en)) {
            will_not = "Paths panel";
        }
        return will_not;
    }

    @Override
    public String POST(String language) {
        String post = "";
        if (language.equals(ru)) {
            post = "Должность";
        } else if (language.equals(en)) {
            post = "Post";
        }
        return post;
    }

    @Override
    public String CONFIGURE_PERSONAL_DATA(String language) {
        String configure_personal_data = "";
        if (language.equals(ru)) {
            configure_personal_data = "Изменить личные данные";
        } else if (language.equals(en)) {
            configure_personal_data = "Configure personal data";
        }
        return configure_personal_data;
    }

    @Override
    public String ADD_A_COMPANY(String language) {
        String add = "";
        if (language.equals(ru)) {
            add = "Добавить компанию";
        } else if (language.equals(en)) {
            add = "Add a company";
        }
        return add;
    }

    @Override
    public String REGISTRY(String language) {
        String registry = "";
        if (language.equals(ru)) {
            registry = "Зарегистрируйтесь";
        } else if (language.equals(en)) {
            registry = "Register";
        }
        return registry;
    }

    @Override
    public String SAVE(String language) {
        String save = "";
        if (language.equals(ru)) {
            save = "Сохранить";
        } else if (language.equals(en)) {
            save = "Save";
        }
        return save;
    }

    @Override
    public String LAST_NAME(String language) {
        String lastName = "";
        if (language.equals(ru)) {
            lastName = "Фамилия";
        } else if (language.equals(en)) {
            lastName = "Last name";
        }
        return lastName;
    }

    @Override
    public String FIRST_NAME(String language) {
        String firstName = "";
        if (language.equals(ru)) {
            firstName = "Имя";
        } else if (language.equals(en)) {
            firstName = "First name";
        }
        return firstName;
    }

    @Override
    public String MAIL_PASSWORD(String language) {
        String mailPassword = "";
        if (language.equals(ru)) {
            mailPassword = "Пароль для электронной почты";
        } else if (language.equals(en)) {
            mailPassword = "Email password";
        }
        return mailPassword;
    }

    @Override
    public String HOST(String language) {
        String host = "";
        if (language.equals(ru)) {
            host = "Хост";
        } else if (language.equals(en)) {
            host = "Host";
        }
        return host;
    }

    @Override
    public String WEBSITE(String language) {
        String website = "";
        if (language.equals(ru)) {
            website = "Вэб сайт";
        } else if (language.equals(en)) {
            website = "Website";
        }
        return website;
    }

    @Override
    public String PHONE(String language) {
        String phone = "";
        if (language.equals(ru)) {
            phone = "Телефон";
        } else if (language.equals(en)) {
            phone = "Phone";
        }
        return phone;
    }

    @Override
    public String EMAIL(String language) {
        String email = "";
        if (language.equals(ru)) {
            email = "Электронный адрес";
        } else if (language.equals(en)) {
            email = "Email";
        }
        return email;
    }

    @Override
    public String INFO_TEXT(String language) {
        String infoText = "";
        if (language.equals(ru)) {
            infoText = "Здравствуйте! По любым вопросам обращайтесь по приведенным ниже контактам.  ";
        } else if (language.equals(en)) {
            infoText = "Hello! For any questions, please contact the contacts listed below.  ";
        }
        return infoText;
    }

    @Override
    public String FILL_IN_THE_DB(String language) {
        String fill_in_the_database = "";
        if (language.equals(ru)) {
            fill_in_the_database = "Заполните базу данных! Или выберите компанию из списка!";
        } else if (language.equals(en)) {
            fill_in_the_database = "Fill in the database! Or choose a company from the list !";
        }
        return fill_in_the_database;
    }

    @Override
    public String FAULTY(String language) {
        String faulty = "";
        if (language.equals(ru)) {
            faulty = "Неисправно";
        } else if (language.equals(en)) {
            faulty = "Faulty";
        }
        return faulty;
    }

    @Override
    public String PREPARED_FOR_DISPOSAL(String language) {
        String prepared_for_disposal = "";
        if (language.equals(ru)) {
            prepared_for_disposal = "Подготовлено к утилизации";
        } else if (language.equals(en)) {
            prepared_for_disposal = "Prepared for disposal";
        }
        return prepared_for_disposal;
    }

    @Override
    public String DISPOSED_OF(String language) {
        String disposed_of = "";
        if (language.equals(ru)) {
            disposed_of = "Утилизировано";
        } else if (language.equals(en)) {
            disposed_of = "Disposed of";
        }
        return disposed_of;
    }

    @Override
    public String SPARE_PARTS_INCLUDED(String language) {
        String spare_parts_included = "";
        if (language.equals(ru)) {
            spare_parts_included = "ЗИП";
        } else if (language.equals(en)) {
            spare_parts_included = "Spare parts included";
        }
        return spare_parts_included;
    }

    @Override
    public String MAIL_FOR_MONITORING_EMAILS(String language) {
        String mfme = "";
        if (language.equals(ru)) {
            mfme = "Почта для мониторинга писем";
        } else if (language.equals(en)) {
            mfme = "Mail for monitoring emails";
        }
        return mfme;
    }

    @Override
    public String EMAIL_PASSWORD(String language) {
        String ep = "";
        if (language.equals(ru)) {
            ep = "Пароль от почты";
        } else if (language.equals(en)) {
            ep = "Email password";
        }
        return ep;
    }

    @Override
    public String SUBJECT_OF_THE_LETTER(String language) {
        String sotl = "";
        if (language.equals(ru)) {
            sotl = "Тема письма";
        } else if (language.equals(en)) {
            sotl = "Subject of the letter";
        }
        return sotl;
    }

    @Override
    public String ENTER_THE_FIRST_WORD(String language) {
        String etfw = "";
        if (language.equals(ru)) {
            etfw = "Введите первое слово темы письма по которому будет происходить выборка писем.";
        } else if (language.equals(en)) {
            etfw = "Enter the first word of the subject of the letter for which the selection of letters will take place.";
        }
        return etfw;
    }

    @Override
    public String SELECT_A_NETWORK_SWITCH(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Выберите сетевой коммутатор";
        } else if (lang.equals(en)) {
            theWS = "Select a network switch";
        }
        return theWS;
    }

    @Override
    public String SOCKET_NUMBER(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Номер розетки";
        } else if (lang.equals(en)) {
            theWS = "Socket Number";
        }
        return theWS;
    }

    @Override
    public String DIAGONAL(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Диагональ";
        } else if (lang.equals(en)) {
            theWS = "Diagonal";
        }
        return theWS;
    }

    @Override
    public String INCHES(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Дюймов";
        } else if (lang.equals(en)) {
            theWS = "Inches";
        }
        return theWS;
    }

    @Override
    public String ENTER_THE_NAME_OF_THE_OPERATING_SYSTEM(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Введите название Операционной системы";
        } else if (lang.equals(en)) {
            theWS = "Enter the name of the operating system";
        }
        return theWS;
    }

    @Override
    public String SELECT_THE_NAME_OF_THE_EQUIPMENT(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Выберите название оборудования";
        } else if (lang.equals(en)) {
            theWS = "Select the name of the equipment";
        }
        return theWS;
    }

    @Override
    public String SELECT_THE_DEVICE_STATUS(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Выберите состояние устройства";
        } else if (lang.equals(en)) {
            theWS = "Select the device status";
        }
        return theWS;
    }

    @Override
    public String TODAY_DATE_WILL_BE_SET_RU(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Будет установлена сегодняшняя дата !";
        } else if (lang.equals(en)) {
            theWS = "Today 's date will be set !";
        }
        return theWS;
    }

    @Override
    public String EQUIPMENT(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Оборудование";
        } else if (lang.equals(en)) {
            theWS = "Equipment";
        }
        return theWS;
    }

    @Override
    public String FILL_IN_THE_FIELDS(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Заполните поля";
        } else if (lang.equals(en)) {
            theWS = "Fill in the fields";
        }
        return theWS;
    }

    @Override
    public String LOCATION(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Расположение";
        } else if (lang.equals(en)) {
            theWS = "Location";
        }
        return theWS;
    }

    @Override
    public String NAME(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Наименование";
        } else if (lang.equals(en)) {
            theWS = "Name";
        }
        return theWS;
    }

    @Override
    public String MANUAL(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Мануал";
        } else if (lang.equals(en)) {
            theWS = "Manual";
        }
        return theWS;
    }

    @Override
    public String WEB_INTERFACE(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "WEB интерфейс";
        } else if (lang.equals(en)) {
            theWS = "WEB interface";
        }
        return theWS;
    }

    @Override
    public String DEFECT(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Дефект";
        } else if (lang.equals(en)) {
            theWS = "Defect";
        }
        return theWS;
    }

    @Override
    public String NEW_LOCATION(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Новое расположение";
        } else if (lang.equals(en)) {
            theWS = "New location";
        }
        return theWS;
    }

    @Override
    public String NEW_ROOM(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Новое помещение";
        } else if (lang.equals(en)) {
            theWS = "New room";
        }
        return theWS;
    }

    @Override
    public String NEW_PASSWORD(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Новый пароль";
        } else if (lang.equals(en)) {
            theWS = "New password";
        }
        return theWS;
    }

    @Override
    public String NEW_LOGIN(String lang) {
        String theWS = "";
        if (lang.equals(ru)) {
            theWS = "Новый логин";
        } else if (lang.equals(en)) {
            theWS = "New login";
        }
        return theWS;
    }

    @Override
    public String NEW_IP_ADDRESS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Новый IP адрес (TCP/IPv4)";
        } else if (lang.equals(en)) {
            ps = "New IP address (TCP/IPv4)";
        }
        return ps;
    }

    @Override
    public String NEW_MASC(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Новая MASC";
        } else if (lang.equals(en)) {
            ps = "New MASC";
        }
        return ps;
    }

    @Override
    public String NEW_GATEWAY(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Новый Gateway";
        } else if (lang.equals(en)) {
            ps = "New Gateway";
        }
        return ps;
    }

    @Override
    public String NEW_IP_ADDRESS_DANTE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Новый IP адрес Dante (TCP/IPv4)";
        } else if (lang.equals(en)) {
            ps = "New IP address Dante (TCP/IPv4)";
        }
        return ps;
    }

    @Override
    public String NEW_MASC_DANTE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Новая MASC Dante";
        } else if (lang.equals(en)) {
            ps = "New MASC Dante";
        }
        return ps;
    }

    @Override
    public String NEW_GATEWAY_DANTE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Новый Gateway Dante";
        } else if (lang.equals(en)) {
            ps = "New Gateway Dante";
        }
        return ps;
    }

    @Override
    public String CHANGE_FREQUENCY(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Измените частоту";
        } else if (lang.equals(en)) {
            ps = "Change the frequency";
        }
        return ps;
    }

    @Override
    public String NEW_TIME_WORK_LAMP_PROJECTOR(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Новое время работы лампы проектора";
        } else if (lang.equals(en)) {
            ps = "New working time of the projector lamp";
        }
        return ps;
    }

    @Override
    public String CHANGE_CONDITION(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Измените состояние";
        } else if (lang.equals(en)) {
            ps = "Change the state";
        }
        return ps;
    }

    @Override
    public String ADD(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Добавить";
        } else if (lang.equals(en)) {
            ps = "Add";
        }
        return ps;
    }

    @Override
    public String ALL_ACTIVE_APPLICATIONS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Все активные заявки";
        } else if (lang.equals(en)) {
            ps = "All active applications";
        }
        return ps;
    }

    @Override
    public String ALL_APPLICATIONS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Все заявки";
        } else if (lang.equals(en)) {
            ps = "All applications";
        }
        return ps;
    }

    @Override
    public String NAME_TOOL(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Название инструмента";
        } else if (lang.equals(en)) {
            ps = "Tool name";
        }
        return ps;
    }

    @Override
    public String SATISFACTORY(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Удовлетворительное";
        } else if (lang.equals(en)) {
            ps = "Satisfactory";
        }
        return ps;
    }

    @Override
    public String TOOL_WITH_SN(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Инструмент с S/N";
        } else if (lang.equals(en)) {
            ps = "Tool with S/N";
        }
        return ps;
    }

    @Override
    public String NOT_ADDED(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Не добавлено";
        } else if (lang.equals(en)) {
            ps = "Not added";
        }
        return ps;
    }

    @Override
    public String A_TOOL_WITH_THIS_SERIAL_NUMBER_HAS_ALREADY_BEEN_ADDED(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Инструмент с таким серийным номером уже добавлен";
        } else if (lang.equals(en)) {
            ps = "A tool with this serial number has already been added";
        }
        return ps;
    }

    @Override
    public String REPAIR(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Ремонт";
        } else if (lang.equals(en)) {
            ps = "Repair";
        }
        return ps;
    }

    @Override
    public String ENTER_THE_DATA(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Введите данные";
        } else if (lang.equals(en)) {
            ps = "Enter the data";
        }
        return ps;
    }

    @Override
    public String ENTER(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Войти";
        } else if (lang.equals(en)) {
            ps = "Enter";
        }
        return ps;
    }

    @Override
    public String MALFUNCTION(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Неисправность (опишите проблему)";
        } else if (lang.equals(en)) {
            ps = "Malfunction (describe the problem)";
        }
        return ps;
    }

    @Override
    public String INITIATORS_FULL_NAME(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "ФИО инициатора";
        } else if (lang.equals(en)) {
            ps = "Initiator's full name";
        }
        return ps;
    }

    @Override
    public String CHOOSE_A_ROOM(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Выберите помещение";
        } else if (lang.equals(en)) {
            ps = "Choose a room";
        }
        return ps;
    }

    @Override
    public String SELECT_EQUIPMENT_BY_SERIAL_NUMBER(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Выберите  оборудование по серийному номеру";
        } else if (lang.equals(en)) {
            ps = "Select equipment by serial number";
        }
        return ps;
    }

    @Override
    public String FIXED(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Исправлено";
        } else if (lang.equals(en)) {
            ps = "Fixed";
        }
        return ps;
    }

    @Override
    public String NOTE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Примечание";
        } else if (lang.equals(en)) {
            ps = "Note";
        }
        return ps;
    }

    @Override
    public String HOW_FIXED(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Как исправлено";
        } else if (lang.equals(en)) {
            ps = "How Fixed";
        }
        return ps;
    }

    @Override
    public String SPECIFY_THE_REASON(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Укажите причину";
        } else if (lang.equals(en)) {
            ps = "Specify the reason";
        }
        return ps;
    }

    @Override
    public String ACTIVE_DEFECTS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Активные дефекты";
        } else if (lang.equals(en)) {
            ps = "Active defects";
        }
        return ps;
    }

    @Override
    public String ALL_DEFECTS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Все дефекты";
        } else if (lang.equals(en)) {
            ps = "All defects";
        }
        return ps;
    }

    @Override
    public String OPERATING_HOURS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Часы наработки";
        } else if (lang.equals(en)) {
            ps = "Operating hours";
        }
        return ps;
    }

    @Override
    public String APPLICATION(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Заявки";
        } else if (lang.equals(en)) {
            ps = "Application";
        }
        return ps;
    }

    @Override
    public String DATABASE_SETTINGS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Настройки базы данных";
        } else if (lang.equals(en)) {
            ps = "database settings";
        }
        return ps;
    }

    @Override
    public String EXIT(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Выйти";
        } else if (lang.equals(en)) {
            ps = "Exit";
        }
        return ps;
    }

    @Override
    public String RECEPTION_SECRETARY(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Секретарь приемной";
        } else if (lang.equals(en)) {
            ps = "Reception secretary";
        }
        return ps;
    }

    @Override
    public String EXTERNAL_DATABASE_SETTINGS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Настройки внешней базы данных";
        } else if (lang.equals(en)) {
            ps = "External database settings";
        }
        return ps;
    }

    @Override
    public String SERVER_HOSTNAME(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Сервер/Имя хоста";
        } else if (lang.equals(en)) {
            ps = "Server/Hostname";
        }
        return ps;
    }

    @Override
    public String PORT(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Порт";
        } else if (lang.equals(en)) {
            ps = "Port";
        }
        return ps;
    }

    @Override
    public String DATABASE_NAME(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Имя базы данных";
        } else if (lang.equals(en)) {
            ps = "Database name";
        }
        return ps;
    }

    @Override
    public String USER(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Пользователь";
        } else if (lang.equals(en)) {
            ps = "User";
        }
        return ps;
    }

    @Override
    public String UPDATE_DATA_IN_AN_EXTERNAL_DATABASE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Обновить данные во внешней базе";
        } else if (lang.equals(en)) {
            ps = "Update data in an external database";
        }
        return ps;
    }

    @Override
    public String GET_DATA_FROM_AN_EXTERNAL_DATABASE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Получить данные из внешней базы данных";
        } else if (lang.equals(en)) {
            ps = "Get data from an external database";
        }
        return ps;
    }

    @Override
    public String THE_DATA_IS_UP_TO_DATE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Данные актуальны";
        } else if (lang.equals(en)) {
            ps = "The data is up to date";
        }
        return ps;
    }

    @Override
    public String COMPARE_DATA(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Сравнить данные";
        } else if (lang.equals(en)) {
            ps = "Compare data";
        }
        return ps;
    }

    @Override
    public String CHANGE_DATA(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Изменить данные";
        } else if (lang.equals(en)) {
            ps = "Change Data";
        }
        return ps;
    }

    @Override
    public String LOCAL_STORAGE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Локальное хранилище";
        } else if (lang.equals(en)) {
            ps = "Local storage";
        }
        return ps;
    }

    @Override
    public String EXTERNAL_STORAGE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Внешнее хранилище";
        } else if (lang.equals(en)) {
            ps = "External storage";
        }
        return ps;
    }

    @Override
    public String THE_EXTERNAL_DATABASE_IS_EMPTY(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Внешняя база данных пуста или не получилось подключиться.";
        } else if (lang.equals(en)) {
            ps = "The external database is empty or failed to connect.";
        }
        return ps;
    }

    @Override
    public String SAVE_THIS_DATA_TO(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Сохранить эти данные в";
        } else if (lang.equals(en)) {
            ps = "Save this data to";
        }
        return ps;
    }

    @Override
    public String DATABASE_USER_NAME(String lange) {
        String ps = "";
        if (lange.equals(ru)) {
            ps = "Имя пользователя базы данных";
        } else if (lange.equals(en)) {
            ps = "Database user name";
        }
        return ps;
    }

    @Override
    public String PASSWORD_DATABASE(String lange) {
        String ps = "";
        if (lange.equals(ru)) {
            ps = "Пароль базы данных";
        } else if (lange.equals(en)) {
            ps = "database password";
        }
        return ps;
    }

    @Override
    public String CONTROLLER(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Контроллер";
        } else if (lang.equals(en)) {
            ps = "Controller";
        }
        return ps;
    }

    @Override
    public String SETTINGS_EXTERNAL_DATABASE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Настройки внешней базы данных";
        } else if (lang.equals(en)) {
            ps = "Settings external database";
        }
        return ps;
    }

    @Override
    public String THE_COMPANY_YOU_WORK_FOR(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Компания в которой работаете";
        } else if (lang.equals(en)) {
            ps = "The company you work for";
        }
        return ps;
    }

    @Override
    public String ROOMS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Комнаты";
        } else if (lang.equals(en)) {
            ps = "Rooms";
        }
        return ps;
    }

    @Override
    public String FILL_IN_THE_DATABASE_INFORMATION_ON_THE_sETTINGS_PAGE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Заполните информацию о базе данных на странице настройки.";
        } else if (lang.equals(en)) {
            ps = "Fill in the database information on the Settings page.";
        }
        return ps;
    }

    @Override
    public String MONTH(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Месяц";
        } else if (lang.equals(en)) {
            ps = "Month";
        }
        return ps;
    }

    @Override
    public String DATA_UPDATED(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Данные обновлены";
        } else if (lang.equals(en)) {
            ps = "Data updated";
        }
        return ps;
    }

    @Override
    public String COMPANY_DATA_SETTINGS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Настройки данных компании";
        } else if (lang.equals(en)) {
            ps = "Company Data Settings";
        }
        return ps;
    }

    @Override
    public String TYPES_OF_EVENTS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Виды мероприятий";
        } else if (lang.equals(en)) {
            ps = "Types of events";
        }
        return ps;
    }

    @Override
    public String ALL_ROOMS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Все комнаты";
        } else if (lang.equals(en)) {
            ps = "All rooms";
        }
        return ps;
    }

    @Override
    public String ALL_EVENTS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Все мероприятия";
        } else if (lang.equals(en)) {
            ps = "All events";
        }
        return ps;
    }

    @Override
    public String EXAMPLE(String language) {
        String example = "";
        if (language.equals(ru)) {
            example = "Пример";
        } else if (language.equals(en)) {
            example = "Example";
        }
        return example;
    }

    @Override
    public String DATE(String language) {
        String date = "";
        if (language.equals(ru)) {
            date = "Дата";
        } else if (language.equals(en)) {
            date = "Date";
        }
        return date;
    }

    @Override
    public String WHITESPACE(String language) {
        String whitespace = "";
        if (language.equals(ru)) {
            whitespace = "Пробел";
        } else if (language.equals(en)) {
            whitespace = "Whitespace";
        }
        return whitespace;
    }

    @Override
    public String TIME(String language) {
        String time = "";
        if (language.equals(ru)) {
            time = "Время";
        } else if (language.equals(en)) {
            time = "Time";
        }
        return time;
    }

    @Override
    public String MEETINGS(String language) {
        String meeting = "";
        if (language.equals(ru)) {
            meeting = "Встречи";
        } else if (language.equals(en)) {
            meeting = "Meetings";
        }
        return meeting;
    }
    @Override
    public String MEETING(String language) {
        String meeting = "";
        if (language.equals(ru)) {
            meeting = "Встреча";
        } else if (language.equals(en)) {
            meeting = "Meeting";
        }
        return meeting;
    }

    @Override
    public String EVENT_NAME(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Название мероприятия";
        } else if (lang.equals(en)) {
            meeting = "Event name";
        }
        return meeting;
    }

    @Override
    public String EVENT_LOGO(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Логотип мероприятия";
        } else if (lang.equals(en)) {
            meeting = "Event logo";
        }
        return meeting;
    }

    @Override
    public String PRESENTATION(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Презентация";
        } else if (lang.equals(en)) {
            meeting = "Presentation";
        }
        return meeting;
    }

    @Override
    public String DATA_UPLOADED(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Данные загружены";
        } else if (lang.equals(en)) {
            meeting = "The data is loaded";
        }
        return meeting;
    }

    @Override
    public String UPDATE_THE_LIST_OF_ROOMS_IN_THE_EXTERNAL_DATABASE(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Обновите список комнат во внешней базе данных";
        } else if (lang.equals(en)) {
            meeting = "Update the list of rooms in the external database";
        }
        return meeting;
    }

    @Override
    public String COMPANY_DATA(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Данные о компании";
        } else if (lang.equals(en)) {
            meeting = "Company data";
        }
        return meeting;
    }

    @Override
    public String LIST_OF_ROOMS(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Лист комнат";
        } else if (lang.equals(en)) {
            meeting = "List of rooms";
        }
        return meeting;
    }

    @Override
    public String SEATING_LIST(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Лист рассадок";
        } else if (lang.equals(en)) {
            meeting = "Seating list";
        }
        return meeting;
    }

    @Override
    public String LIST_OF_EVENTS(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Лист мероприятий";
        } else if (lang.equals(en)) {
            meeting = "List of events";
        }
        return meeting;
    }

    @Override
    public String DO_NOT_DISCONNECT_THE_CONNECTION(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Не отключайте соединение";
        } else if (lang.equals(en)) {
            meeting = "Do not disconnect the connection";
        }
        return meeting;
    }

    @Override
    public String START_EVENT(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Начало мероприятия";
        } else if (lang.equals(en)) {
            meeting = "Start the event";
        }
        return meeting;
    }

    @Override
    public String END_EVENT(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Конец мероприятия";
        } else if (lang.equals(en)) {
            meeting = "End the event";
        }
        return meeting;
    }

    @Override
    public String SEATING_ARRANGEMENTS(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Рассадка";
        } else if (lang.equals(en)) {
            meeting = "Seating arrangements";
        }
        return meeting;
    }

    @Override
    public String NUMBER_OF_PARTICIPANTS(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Количество участников";
        } else if (lang.equals(en)) {
            meeting = "Number of participants";
        }
        return meeting;
    }

    @Override
    public String LAST_NAME_CUSTOMER(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Фамилия клиента";
        } else if (lang.equals(en)) {
            meeting = "Last name customer";
        }
        return meeting;
    }

    @Override
    public String FIRST_NAME_CUSTOMER(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Имя клиента";
        } else if (lang.equals(en)) {
            meeting = "First name customer";
        }
        return meeting;
    }

    @Override
    public String TYPE_NAME(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Название типа";
        } else if (lang.equals(en)) {
            meeting = "Type name";
        }
        return meeting;
    }

    @Override
    public String IMAGE(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Изображение";
        } else if (lang.equals(en)) {
            meeting = "Image";
        }
        return meeting;
    }

    @Override
    public String TYPES_OF_FURNITURE_ARRANGEMENT(String lang) {
        String meeting = "";
        if (lang.equals(ru)) {
            meeting = "Типы расположения мебели";
        } else if (lang.equals(en)) {
            meeting = "Types of furniture arrangement";
        }
        return meeting;
    }


    @Override
    public String SAVE_AND_SEND(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Сохранить и Отправить";
        } else if (lang.equals(en)) {
            office = "Save and Send";
        }
        return office;
    }

    @Override
    public String TOPIC(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Тема";
        } else if (lang.equals(en)) {
            office = "Topic";
        }
        return office;
    }

    @Override
    public String TO(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "К";
        } else if (lang.equals(en)) {
            office = "To";
        }
        return office;
    }

    @Override
    public String FROM(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "От";
        } else if (lang.equals(en)) {
            office = "From";
        }
        return office;
    }
    
    @Override
    public String EVENT(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Мероприятие";
        } else if (lang.equals(en)) {
            office = "Event";
        }
        return office;
    }

    @Override
    public String CHECK_YOUR_INTERNET_CONNECTION(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Проверьте соединение с интернетом";
        } else if (lang.equals(en)) {
            office = "Check your internet connection";
        }
        return office;
    }

    @Override
    public String MORE_DETAILED(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Подробнее";
        } else if (lang.equals(en)) {
            office = "More detailed";
        }
        return office;
    }

    @Override
    public String START(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Старт";
        } else if (lang.equals(en)) {
            office = "Start";
        }
        return office;
    }

    @Override
    public String PORTABLE_DEVICES(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Переносные устройства";
        } else if (lang.equals(en)) {
            office = "Portable devices";
        }
        return office;
    }

    @Override
    public String CHANGE_COMPANY(String lan) {
        String office = "";
        if (lan.equals(ru)) {
            office = "Сменить компанию";
        } else if (lan.equals(en)) {
            office = "Change company";
        }
        return office;
    }

    @Override
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

    @Override
    public String COMPANY_DATA_NOT_FOUND(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Данные о компаниях не найдены";
        } else if (lang.equals(en)) {
            office = "Company data not found";
        }
        return office;
    }

    @Override
    public String SHOW(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Показать";
        } else if (lang.equals(en)) {
            office = "Show";
        }
        return office;
    }

    @Override
    public String DIFFERENCES(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Различий";
        } else if (lang.equals(en)) {
            office = "Differences";
        }
        return office;
    }

    @Override
    public String FILL_OUT_THE_FORM(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Заполнить форму";
        } else if (lang.equals(en)) {
            office = "Fill out the form";
        }
        return office;
    }

    @Override
    public String RESET(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Сброс";
        } else if (lang.equals(en)) {
            office = "Reset";
        }
        return office;
    }

    @Override
    public String TO_BOOK(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Забронировать";
        } else if (lang.equals(en)) {
            office = "To book";
        }
        return office;
    }

    @Override
    public String THE_EQUIPMENT_IS_BUSY_TODAY(String lang) {
        String office = "";
        if (lang.equals(ru)) {
            office = "Сегодня оборудование занято";
        } else if (lang.equals(en)) {
            office = "The equipment is busy today";
        }
        return office;
    }

    @Override
    public String SERVER(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Сервер";
        } else if (lang.equals(en)) {
            server = "Server";
        }
        return server;
    }

    @Override
    public String FTP_SERVER_SETTINGS(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Настройки сервера FTP";
        } else if (lang.equals(en)) {
            server = "FTP Server Settings";
        }
        return server;
    }

    @Override
    public String USER_VISIBILITY(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Видимость пользователю";
        } else if (lang.equals(en)) {
            server = "User visibility";
        }
        return server;
    }

    @Override
    public String SELECT_THE_DEVICE_TYPE(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Выберите тип устройства";
        } else if (lang.equals(en)) {
            server = "Select the device type";
        }
        return server;
    }

    @Override
    public String SELECT_DEVICE_VISIBILITY_FOR_THE_USER(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Выберите видимость устройства для пользователя";
        } else if (lang.equals(en)) {
            server = "Select device visibility for the user";
        }
        return server;
    }

    @Override
    public String ADD_AN_EVENT(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Добавить мероприятие";
        } else if (lang.equals(en)) {
            server = "Add an event";
        }
        return server;
    }
    
    @Override
    public String DEPARTMENT(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Отдел";
        } else if (lang.equals(en)) {
            server = "Department";
        }
        return server;
    }
    
    @Override
    public String PARTNER(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Партнер";
        } else if (lang.equals(en)) {
            server = "Partner";
        }
        return server;
    }
    
    @Override
    public String SELECT_THE_START_OF_THE_EVENT(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Выберите начало мероприятия";
        } else if (lang.equals(en)) {
            server = "Select the start of the event";
        }
        return server;
    }
    
    @Override
    public String SELECT_THE_END_OF_THE_EVENT(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Выберите конец мероприятия";
        } else if (lang.equals(en)) {
            server = "Select the end of the event";
        }
        return server;
    }
    
    @Override
    public String TO_WHOM(String lang) {
        String server = "";
        if (lang.equals(ru)) {
            server = "Кому";
        } else if (lang.equals(en)) {
            server = "Whom";
        }
        return server;
    }
    
    @Override
    public String OFFICE(String language) {
        String office = "";
        if (language.equals(ru)) {
            office = "Офис";
        } else if (language.equals(en)) {
            office = "Office";
        }
        return office;
    }

    @Override
    public String CUSTOMER(String language) {
        String customer = "";
        if (language.equals(ru)) {
            customer = "Заказчик";
        } else if (language.equals(en)) {
            customer = "Customer";
        }
        return customer;
    }

    @Override
    public String ROOM(String language) {
        String room = "";
        if (language.equals(ru)) {
            room = "Комната";
        } else if (language.equals(en)) {
            room = "Room";
        }
        return room;
    }

    @Override
    public String THEN_AN_ARBITRARY(String language) {
        String taa = "";
        if (language.equals(ru)) {
            taa = "Дальше произвольный текст";
        } else if (language.equals(en)) {
            taa = "Then an arbitrary text";
        }
        return taa;
    }

    @Override
    public String DATE_STORAGE_LOCATION(String language) {
        String dsl = "";
        if (language.equals(ru)) {
            dsl = "Место хранения данных";
        } else if (language.equals(en)) {
            dsl = "Data storage location";
        }
        return dsl;
    }

    @Override
    public String APPLICATION_FOLDER(String language) {
        String af = "";
        if (language.equals(ru)) {
            af = "Папка приложения";
        } else if (language.equals(en)) {
            af = "Application Folder";
        }
        return af;
    }

    @Override
    public String DATABASE_HOSTING_FOLDER(String language) {
        String dhf = "";
        if (language.equals(ru)) {
            dhf = "Папка размещения базы данных";
        } else if (language.equals(en)) {
            dhf = "Database Hosting Folder";
        }
        return dhf;
    }

    @Override
    public String SELECT_A_LANGUAGE(String language) {
        String sal = "";
        if (language.equals(ru)) {
            sal = "Выберите язык";
        } else if (language.equals(en)) {
            sal = "Select a language";
        }
        return sal;
    }

    @Override
    public String MAIL_SETTINGS(String language) {
        String ms = "";
        if (language.equals(ru)) {
            ms = "Настройки почты";
        } else if (language.equals(en)) {
            ms = "Mail Settings";
        }
        return ms;
    }

    @Override
    public String NO_DEVICE_WITH_THIS_SERIAL_NUMBER(String language) {
        String ndwtsn = "";
        if (language.equals(ru)) {
            ndwtsn = "Устройства с таким серийным номером не найдено!";
        } else if (language.equals(en)) {
            ndwtsn = "No device with this serial number was found!";
        }
        return ndwtsn;
    }

    @Override
    public String ENTER_THE_SERIAL_NUMBER(String language) {
        String etsn = "";
        if (language.equals(ru)) {
            etsn = "Введите серийный номер устройства!";
        } else if (language.equals(en)) {
            etsn = "Enter the serial number of the device!";
        }
        return etsn;
    }

    @Override
    public String TASK_TEXT(String language) {
        String task_text = "";
        if (language.equals(ru)) {
            task_text = "Текст задачи";
        } else if (language.equals(en)) {
            task_text = "Task text";
        }
        return task_text;
    }

    @Override
    public String OUTLET_NUMBER(String language) {
        String outlet = "";
        if (language.equals(ru)) {
            outlet = "Номер розетки";
        } else if (language.equals(en)) {
            outlet = "Outlet number";
        }
        return outlet;
    }

    @Override
    public String PORT_NUMBER_IN_THE_SWITCH(String language) {
        String pnits = "";
        if (language.equals(ru)) {
            pnits = "Номер порта в коммутаторе";
        } else if (language.equals(en)) {
            pnits = "Port number in the switch";
        }
        return pnits;
    }

    @Override
    public String HOME_PAGE(String language) {
        String hp = "";
        if (language.equals(ru)) {
            hp = "Домашняя страница";
        } else if (language.equals(en)) {
            hp = "Home page";
        }
        return hp;
    }

    @Override
    public String DOCUMENTATION(String language) {
        String doc = "";
        if (language.equals(ru)) {
            doc = "Документация";
        } else if (language.equals(en)) {
            doc = "Documentation";
        }
        return doc;
    }

    @Override
    public String SETTINGS(String language) {
        String settings = "";
        if (language.equals(ru)) {
            settings = "Настройки";
        } else if (language.equals(en)) {
            settings = "Settings";
        }
        return settings;
    }

    @Override
    public String INFORMATION(String language) {
        String info = "";
        if (language.equals(ru)) {
            info = "Информация";
        } else if (language.equals(en)) {
            info = "Information";
        }
        return info;
    }

    @Override
    public String CHOOSE_A_COMPANY(String language) {
        String cac = "";
        if (language.equals(ru)) {
            cac = "Выберите компанию";
        } else if (language.equals(en)) {
            cac = "Choose a company";
        }
        return cac;
    }

    @Override
    public String WE_ARE_GLAD_OF_YOUR_CHOICE(String language) {
        String we = "";
        if (language.equals(ru)) {
            we = "Мы рады Вашему выбору";
        } else if (language.equals(en)) {
            we = "We are glad of your choice";
        }
        return we;
    }

    @Override
    public String START_BY_REGISTERING_ON_THE_SETTINGS_PAGE(String language) {
        String sbot = "";
        if (language.equals(ru)) {
            sbot = "Начните с регистрации первой компании на странице настройки";
        } else if (language.equals(en)) {
            sbot = "Start by registering the first company on the settings page";
        }
        return sbot;
    }

    @Override
    public String NUMBER_OF_TOOLS(String language) {
        String not = "";
        if (language.equals(ru)) {
            not = "Количество инструментов";
        } else if (language.equals(en)) {
            not = "Number of tools";
        }
        return not;
    }

    @Override
    public String THE_NUMBER_OF_DEVICES_OPERATING_FOR_MORE_THAN_5_YEARS(String language) {
        String tnodo = "";
        if (language.equals(ru)) {
            tnodo = "Количество устройств работающих более 5 лет";
        } else if (language.equals(en)) {
            tnodo = "The number of devices operating for more than 5 years";
        }
        return tnodo;
    }

    @Override
    public String NUMBER_OF_FAULTY_DEVICES(String language) {
        String nofd = "";
        if (language.equals(ru)) {
            nofd = "Количество неисправных устройств";
        } else if (language.equals(en)) {
            nofd = "Number of faulty devices";
        }
        return nofd;
    }

    @Override
    public String NUMBER_OF_DEVICES(String language) {
        String nod = "";
        if (language.equals(ru)) {
            nod = "Количество устройств";
        } else if (language.equals(en)) {
            nod = "Number of devices";
        }
        return nod;
    }

    @Override
    public String GENERATE_A_ZIP_DOCUMENT(String language) {
        String gazd = "";
        if (language.equals(ru)) {
            gazd = "Сформировать документ ЗИП";
        } else if (language.equals(en)) {
            gazd = "Generate a ZIP document";
        }
        return gazd;
    }

    @Override
    public String GENERATE_A_TIMESHEET(String language) {
        String gat = "";
        if (language.equals(ru)) {
            gat = "Сформировать табель учета рабочего времени";
        } else if (language.equals(en)) {
            gat = "Generate a timesheet";
        }
        return gat;
    }

    @Override
    public String GENERATE_A_DOCUMENT_OF_THE_OPERATION_OF_THE_PROJECTOR_LAMP(String language) {
        String gadoto = "";
        if (language.equals(ru)) {
            gadoto = "Сформировать документ работы ламп проектора";
        } else if (language.equals(en)) {
            gadoto = "Generate a document of the operation of the projector lamps";
        }
        return gadoto;
    }

    @Override
    public String NAME_COMPANY(String language) {
        String nameCompany = "";
        if (language.equals(ru)) {
            nameCompany = "Название компании";
        } else if (language.equals(en)) {
            nameCompany = "Company Name";
        }
        return nameCompany;
    }

    @Override
    public String COMPANY_ADDRESS(String language) {
        String companyAddress = "";
        if (language.equals(ru)) {
            companyAddress = "Адрес компании";
        } else if (language.equals(en)) {
            companyAddress = "Company address";
        }
        return companyAddress;
    }

    @Override
    public String CONTACT_PERSON(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Связное лицо (представитель)";
        } else if (language.equals(en)) {
            cp = "Contact person (representative)";
        }
        return cp;
    }

    @Override
    public String REPRESENTATIVES_PHONE_NUMBER(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Телефон представителя";
        } else if (language.equals(en)) {
            cp = "Representative's phone number";
        }
        return cp;
    }

    @Override
    public String REPRESENTATIVES_EMAIL(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Почта представителя";
        } else if (language.equals(en)) {
            cp = "Representative's email";
        }
        return cp;
    }

    @Override
    public String COMPANY_LOGO(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Логотип компании";
        } else if (language.equals(en)) {
            cp = "Company logo";
        }
        return cp;
    }

    @Override
    public String MANAGER(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Менеджер";
        } else if (language.equals(en)) {
            cp = "Manager";
        }
        return cp;
    }

    @Override
    public String ENGINEER(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Инженер";
        } else if (language.equals(en)) {
            cp = "Engineer";
        }
        return cp;
    }

    @Override
    public String MANAGER_PHONE(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Телефон менеджера";
        } else if (language.equals(en)) {
            cp = "Manager's phone number";
        }
        return cp;
    }

    @Override
    public String ENGINEER_PHONE(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Телефон инженера";
        } else if (language.equals(en)) {
            cp = "Engineer's phone number";
        }
        return cp;
    }

    @Override
    public String ENGINEER_MAIL(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Почта инженера";
        } else if (language.equals(en)) {
            cp = "Engineer's mail";
        }
        return cp;
    }

    @Override
    public String MANAGER_MAIL(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Почта менеджера";
        } else if (language.equals(en)) {
            cp = "Manager's email";
        }
        return cp;
    }

    @Override
    public String ALREADY_HAVE(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Уже существует";
        } else if (language.equals(en)) {
            cp = "Already exists";
        }
        return cp;
    }

    @Override
    public String RESPONSIBLE_MANAGER(String language) {
            String cp = "";
            if (language.equals(ru)) {
                cp = "Ответственный менеджер";
            } else if (language.equals(en)) {
                cp = "Responsible Manager";
            }
            return cp;
    }

    @Override
    public String RESPONSIBLE_ENGINEER(String language) {
        String cp = "";
        if (language.equals(ru)) {
            cp = "Ответственный инженер";
        } else if (language.equals(en)) {
            cp = "Responsible Engineer";
        }
        return cp;
    }

    @Override
    public String SELECT_A_COUNTRY(String language) {
        String sac = "";
        if (language.equals(ru)) {
            sac = "Выберите страну";
        } else if (language.equals(en)) {
            sac = "Select a country";
        }
        return sac;
    }

    @Override
    public String PROFILE_SETTINGS(String language) {
        String ps = "";
        if (language.equals(ru)) {
            ps = "Настройки профиля";
        } else if (language.equals(en)) {
            ps = "Profile Settings";
        }
        return ps;
    }

    @Override
    public List<String> status_sheet(String language) {

        List<String> list = new ArrayList<>();

        if (language.equals(en)) {
            list.add("Satisfactory");
            list.add("Faulty");
            list.add("Prepared of disposal");
            list.add("Disposed of");
            list.add("SPI");
        }
        if (language.equals(ru)) {
            list.add("Удовлетворительное");
            list.add("Неисправно");
            list.add("Подготовлено к утилизации");
            list.add("Утилизировано");
            list.add("ЗИП");
        }

        return list;
    }

    @Override
    public List<String> LIST_POST(String language) {
        List<String> listPost = new ArrayList<>();

        if (language.equals(en)) {
            listPost.add("Engineer");
            // listPost.add("Manager");
            // listPost.add("Head of Department");
            listPost.add("Reception Secretary");
            // listPost.add("Assistant Manager");
            //listPost.add("Installer");
            // listPost.add("IT Specialist");
            //listPost.add("Customer's representative");
            //listPost.add("Director");
            //listPost.add("Administrator");
        }
        if (language.equals(ru)) {
            listPost.add("Инженер");
            //listPost.add("Менеджер");
            //listPost.add("Начальник отдела");
            listPost.add("Секретарь приемной");
            //listPost.add("Помощник руководителя");
            //listPost.add("Монтажник");
            //listPost.add("Специалист IT");
            //listPost.add("Представитель заказчика");
            //listPost.add("Директор");
            //listPost.add("Администратор");
        }

        return listPost;
    }

    @Override
    public String ACCOUNTING(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Учет";
        } else if (lang.equals(en)) {
            ps = "Accounting";
        }
        return ps;
    }

    @Override
    public String MODEL(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Модель";
        } else if (lang.equals(en)) {
            ps = "Model";
        }
        return ps;
    }

    @Override
    public String MANUFACTURER(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Производитель";
        } else if (lang.equals(en)) {
            ps = "Manufacturer";
        }
        return ps;
    }

    @Override
    public String SERIAL_NUMBER(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Серийный номер";
        } else if (lang.equals(en)) {
            ps = "Serial number";
        }
        return ps;
    }

    @Override
    public String MAC_ADDRESS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "MAC адрес";
        } else if (lang.equals(en)) {
            ps = "MAC address";
        }
        return ps;
    }

    @Override
    public String LOGIN(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Логин";
        } else if (lang.equals(en)) {
            ps = "Login";
        }
        return ps;
    }

    @Override
    public String PASSWORD(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Пароль";
        } else if (lang.equals(en)) {
            ps = "Password";
        }
        return ps;
    }

    @Override
    public String LOCATION_IN_THE_ROOM(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Расположение в помещении";
        } else if (lang.equals(en)) {
            ps = "Location in the room";
        }
        return ps;
    }

    @Override
    public String START_DATE_OF_OPERATION(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Дата начала эксплуатации";
        } else if (lang.equals(en)) {
            ps = "Start date of operation";
        }
        return ps;
    }

    @Override
    public String CONDITION(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Состояние";
        } else if (lang.equals(en)) {
            ps = "Condition";
        }
        return ps;
    }

    @Override
    public String INSTRUCTION(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Инструкция в формате PDF";
        } else if (lang.equals(en)) {
            ps = "Instructions in PDF format";
        }
        return ps;
    }

    @Override
    public String IP_ADDRESS(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "IP адрес (TCP/IPv4)";
        } else if (lang.equals(en)) {
            ps = "IP address (TCP/IPv4)";
        }
        return ps;
    }

    @Override
    public String MAXIMUM_LAMP_OPERATING_TIME(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Максимальное время работы лампы";
        } else if (lang.equals(en)) {
            ps = "Maximum lamp operating time";
        }
        return ps;
    }

    @Override
    public String FREQUENCY(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "Введите частоту";
        } else if (lang.equals(en)) {
            ps = "Enter the frequency";
        }
        return ps;
    }

    @Override
    public String IP_ADDRESS_DANTE(String lang) {
        String ps = "";
        if (lang.equals(ru)) {
            ps = "IP адрес (TCP/IPv4) Dante";
        } else if (lang.equals(en)) {
            ps = "IP address (TCP/IPv4) Dante";
        }
        return ps;
    }
}
