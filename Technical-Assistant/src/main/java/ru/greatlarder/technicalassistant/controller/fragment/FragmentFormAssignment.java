package ru.greatlarder.technicalassistant.controller.fragment;

import jakarta.mail.MessagingException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import jfxtras.scene.control.LocalDateTimeTextField;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListBookingEquipment;
import ru.greatlarder.technicalassistant.services.database.SetBookingEquipment;
import ru.greatlarder.technicalassistant.services.database.mysql.booking_equipment.ListBookingEquipmentByIdEquipmentMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.booking_equipment.SetBookingEquipmentMySQL;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartReceptionController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.mail.SendAnEmail;
import ru.greatlarder.technicalassistant.services.mail.impl.SendEmailEquipmentRental;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class FragmentFormAssignment implements Initializable {
    @FXML public Label nameEquipment;
    @FXML public Label serialNumberEquipment;
    @FXML public Label locationEquipment;
    @FXML public BorderPane borderPane;
    @FXML public WebView webView;
    @FXML public Label labelLastName;
    @FXML public TextField textFileLastName;
    @FXML public Label labelFirstName;
    @FXML public Label labelDepartment;
    @FXML public Label labelTypeOfEvent;
    @FXML public Label labelPosition;
    @FXML public Label labelPhoneNumber;
    @FXML public Label labelLocation;
    @FXML public Label labelPartner;
    @FXML public TextField textFiledFirstName;
    @FXML public TextField textFiledDepartment;
    @FXML public TextField texFiledTypeOfEvent;
    @FXML public TextField textFiledPosition;
    @FXML public TextField textFiledPhoneNumber;
    @FXML public TextField textFieldLocation;
    @FXML public TextField textFieldPartner;
    @FXML public VBox vBox;
    @FXML public LocalDateTimeTextField dataTimePickerStart;
    @FXML public ImageView imgEquipment;
    @FXML public LocalDateTimeTextField dataTimePickerEnd;
    @FXML public Button btnSave;
    @FXML public Label labelToWhom;
    @FXML public TextField textFieldToWhom;
    @FXML public Label labelTopic;
    @FXML public TextField textFieldTopic;
    Equipment equipment;
    Language language = new LanguageImpl();
    String lang;
    User user;
    Company company;
    FileManager fileManager = new FileManagerImpl();
    BookingEquipment bookingEquipment;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lang = GlobalLinkMainController.getMainController().getLang();
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        setLanguagePage(lang);
        fillOutTheForm();
        loadDataTimePicker();
    }
    public void loadFragment(Equipment equipment){
        this.equipment = equipment;
        nameEquipment.setText(equipment.getName());
        serialNumberEquipment.setText(equipment.getSerialNumber());
        locationEquipment.setText(equipment.getLocation());
        imgEquipment.setImage(new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/ru/greatlarder/technicalassistant/images/equipment_img/" + equipment.getImage()))));
        textFieldTopic.setText(equipment.getName());
    }
    private void loadDataTimePicker(){
        
        dataTimePickerStart.setPromptText(language.SELECT_THE_START_OF_THE_EVENT(lang));
        dataTimePickerEnd.setPromptText(language.SELECT_THE_END_OF_THE_EVENT(lang));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task<List<LocalDate>> task = new Task<>() {
            @Override
            protected List<LocalDate> call() {
                GetListBookingEquipment bookingEquipmentList = new ListBookingEquipmentByIdEquipmentMySQL();
                List<BookingEquipment> list = bookingEquipmentList.getListBookingEquipments(user, company.getNameCompany(), String.valueOf(equipment.getId()));
                List<LocalDate> localDates = new ArrayList<>();
                for (BookingEquipment b : list) {
                    localDates.add(b.getDate());
                }
                return localDates;
            }
        };
        task.setOnSucceeded((t)->{
            List<LocalDate> localDates = task.getValue();
            for (LocalDate date: localDates) {
                if(date.equals(LocalDate.now())){
                    dataTimePickerStart.setPromptText(language.THE_EQUIPMENT_IS_BUSY_TODAY(lang));
                }
                dataTimePickerStart.disabledLocalDateTimes().add(date.atStartOfDay());
            }
            dataTimePickerStart.withLocale(Locale.UK);
            dataTimePickerStart.setDateTimeFormatter(formatter);
            dataTimePickerStart.textProperty().addListener((observable, oldValue, newValue)->{
                LocalDateTime dateTime = LocalDateTime.parse(newValue, formatter);
                
                LocalDate ld = LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
                LocalTime lt = LocalTime.of(dateTime.getHour(), dateTime.getMinute());
                
                Document document = webView.getEngine().getDocument();
                Element p_date_from = document.getElementById("p_date_from");
                Element p_time_from = document.getElementById("p_time_from");
                
                p_date_from.setTextContent(String.valueOf(ld));
                p_time_from.setTextContent(String.valueOf(lt));
                bookingEquipment = new BookingEquipment();
                bookingEquipment.setDate(ld);
            });
            
            dataTimePickerEnd.withLocale(Locale.UK);
            dataTimePickerEnd.setDateTimeFormatter(formatter);
            dataTimePickerEnd.textProperty().addListener((observable, oldValue, newValue)->{
                LocalDateTime dateTimeTill = LocalDateTime.parse(newValue, formatter);
                LocalDate localDateEnd = LocalDate.of(dateTimeTill.getYear(), dateTimeTill.getMonth(), dateTimeTill.getDayOfMonth());
                LocalTime localTimeEnd = LocalTime.of(dateTimeTill.getHour(), dateTimeTill.getMinute());
                
                Document document = webView.getEngine().getDocument();
                Element p_date_till = document.getElementById("p_date_till");
                Element p_time_till = document.getElementById("p_time_till");
                
                p_date_till.setTextContent(String.valueOf(localDateEnd));
                p_time_till.setTextContent(String.valueOf(localTimeEnd));
            });
            
            
        });
        Platform.runLater(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });
    }
    private void setLanguagePage(String lang) {
        nameEquipment.setText(language.EQUIPMENT(lang));
        serialNumberEquipment.setText(language.SERIAL_NUMBER(lang));
        locationEquipment.setText(language.LOCATION(lang));
        labelLastName.setText(language.LAST_NAME(lang));
        labelFirstName.setText(language.FIRST_NAME(lang));
        labelDepartment.setText(language.DEPARTMENT(lang));
        labelTypeOfEvent.setText(language.TYPES_OF_EVENTS(lang));
        labelPosition.setText(language.POST(lang));
        labelPhoneNumber.setText(language.PHONE(lang));
        labelLocation.setText(language.LOCATION(lang));
        labelPartner.setText(language.PARTNER(lang)+"/"+ language.MANAGER(lang));
        labelToWhom.setText(language.TO_WHOM(lang));
        labelTopic.setText(language.TOPIC(lang));
        btnSave.setText(language.SAVE(lang));
    }

    public void fillOutTheForm() {
        if(lang.equals("Русский")){
            webView.getEngine().load(String.valueOf(getClass()
                .getResource("/ru/greatlarder/technicalassistant/html/formAssignmentRu.html")));
        }
        if(lang.equals("English")) {
            webView.getEngine().load(String.valueOf(getClass()
                .getResource("/ru/greatlarder/technicalassistant/html/formAssignmentEn.html")));
        }
        webView.getEngine().documentProperty().addListener((v, o, n)->{
            if(n != null){
                loadHTMLData(n);
            }
        });
    }
    private void loadHTMLData(Document document){
        Element meta = document.createElement("meta");
        meta.setAttribute("charset", "UTF-8");
        Element item = document.getElementById("item");
        Element model = document.getElementById("model");
        Element serialNumber = document.getElementById("serial_number");
        Element nameCompany1 = document.getElementById("nameCompanyP1");
        Element nameCompany2 = document.getElementById("nameCompanyP2");
        Element logoCompany = document.getElementById("logo_company");
        
        item.setTextContent(equipment.getName());
        model.setTextContent(equipment.getModel());
        serialNumber.setTextContent(equipment.getSerialNumber());
        nameCompany1.setTextContent(company.getNameCompany());
        nameCompany2.setTextContent(company.getNameCompany());
        if(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/html/img") != null){
            String url = "/ru/greatlarder/technicalassistant/html/img" + company.getLogoCompany();
            
            logoCompany.getAttributeNode("src").setValue(url);
            
        } else if(fileManager.getUrlFileImage(company.getLogoCompany()) != null){
            try {
                FileUtils.copyFile(new File(fileManager.getUrlFileImage(company.getLogoCompany())), new File("/ru/greatlarder/technicalassistant/html/img" + company.getLogoCompany()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = "/ru/greatlarder/technicalassistant/html/img" + company.getLogoCompany();
            logoCompany.getAttributeNode("src").setValue(url);
        }
    }
    public void save() throws MessagingException {
        PrinterJob job = PrinterJob.createPrinterJob();
        webView.getEngine().print(job);
        job.endJob();
        
        if(!textFieldToWhom.getText().isEmpty()) {
            SetBookingEquipment setBookingEquipment = new SetBookingEquipmentMySQL();
            bookingEquipment.setIdEquipment(equipment.getId());
            bookingEquipment.setConditionBooking(1);
            setBookingEquipment.setBookingEquipment(user
                , GlobalLinkMainController.getMainController().getCompany().getNameCompany(),  bookingEquipment);
            textFieldToWhom.setStyle(new TextField().getStyle());
            String[] address = textFieldToWhom.getText().split(",");
            SendAnEmail sendAnEmail = new SendEmailEquipmentRental();
            Document document = webView.getEngine().getDocument();
            Element delete = document.getElementById("delete");
            delete.getParentNode().removeChild(delete);
            
            Element img = document.getElementById("logo_company");
            String urlImage = img.getAttributeNode("src").getValue();
            
            img.getParentNode().removeChild(img);
            
            String html = (String) webView.getEngine().executeScript("document.documentElement.outerHTML");
            sendAnEmail.sendEmail(user, html, new ArrayList<>(Arrays.asList(address)), textFieldTopic.getText(), urlImage);
            
            ((BorderPane)borderPane.getParent()).getChildren().remove(borderPane);
            GlobalLinkStartReceptionController.getStartReceptionController().openPortableDevices();
        } else {
            textFieldToWhom.setStyle(StyleSRC.STYLE_DANGER);
        }
        
    }
    
    public void onKeyReleasedLastName() {
        Document document = webView.getEngine().getDocument();
            Element p_lastName = document.getElementById("p_lastName");
            assert p_lastName != null;
            p_lastName.setTextContent(textFileLastName.getText());
    }
    
    public void keyReleasedFirstName() {
        Document document = webView.getEngine().getDocument();
        Element p_firstName = document.getElementById("p_firstName");
        assert p_firstName != null;
        p_firstName.setTextContent(textFiledFirstName.getText());
    }
    
    public void keyReleasedDepartment() {
        Document document = webView.getEngine().getDocument();
        Element p_department = document.getElementById("p_department");
        assert p_department != null;
        p_department.setTextContent(textFiledDepartment.getText());
    }
    
    public void keyReleasedTypeOfEvent() {
        Document document = webView.getEngine().getDocument();
        Element p_type_of_event = document.getElementById("p_type_of_event");
        assert p_type_of_event != null;
        p_type_of_event.setTextContent(texFiledTypeOfEvent.getText());
    }
    
    public void keyReleasedPost() {
        Document document = webView.getEngine().getDocument();
        Element p_position = document.getElementById("p_position");
        assert p_position != null;
        p_position.setTextContent(textFiledPosition.getText());
    }
    
    public void keyReleasedPhoneNumber() {
        Document document = webView.getEngine().getDocument();
        Element p_phone = document.getElementById("p_phone");
        assert p_phone != null;
        p_phone.setTextContent(textFiledPhoneNumber.getText());
    }
    
    public void keyReleasedLocation() {
        Document document = webView.getEngine().getDocument();
        Element p_location = document.getElementById("p_location");
        assert p_location != null;
        p_location.setTextContent(textFieldLocation.getText());
    }
    
    public void keyReleasedPartner() {
        Document document = webView.getEngine().getDocument();
        Element p_manager = document.getElementById("p_manager");
        assert p_manager != null;
        p_manager.setTextContent(textFieldPartner.getText());
    }
    
}
