package ru.greatlarder.technicalassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.MainController;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListUser;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.GetListUserSQLite;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AppStart extends Application {
    FileManager fileManager = new FileManagerImpl();
    
    private User getUser() {
        GetListUser getListUser = new GetListUserSQLite();
        List<User> user = getListUser.getListUser(null, null);
        if (user.size() == 1){
            return user.get(0);
        } else return null;
    }

    @Override
    public void start(Stage stage) throws IOException {
            fileManager.createProjectDirectories();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/ru/greatlarder/technicalassistant/mainPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 820);

            stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png")))));
            stage.setTitle("Technical Assistant");

            MainController controller = fxmlLoader.getController();
            controller.startAccount(getUser());
            stage.setScene(scene);
            stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}