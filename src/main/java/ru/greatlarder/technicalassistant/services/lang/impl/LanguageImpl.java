package ru.greatlarder.technicalassistant.services.lang.impl;

import ru.greatlarder.technicalassistant.services.lang.Language;

import java.util.ArrayList;
import java.util.List;

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
            infoText = "Здравствуйте! Мы - это стартап. Для обращения к нам можете воспользоваться информацией приведенной ниже.  ";
        } else if (language.equals(en)) {
            infoText = "Hello! We are a startup. To contact us, you can use the information provided below.";
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
    public String THE_WORDS_SHOULD(String language) {
        String theWS = "";
        if (language.equals(ru)) {
            theWS = "Слова должны быть разделены пробелами. Точек, тире или других знаков быть не должно.";
        } else if (language.equals(en)) {
            theWS = "The words should be separated by spaces. There should be no dots, dashes or other signs.";
        }
        return theWS;
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
            meeting = "встречи";
        } else if (language.equals(en)) {
            meeting = "meetings";
        }
        return meeting;
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
            listPost.add("Installer");
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
            listPost.add("Монтажник");
            //listPost.add("Специалист IT");
            //listPost.add("Представитель заказчика");
            //listPost.add("Директор");
            //listPost.add("Администратор");
        }

        return listPost;
    }
}
