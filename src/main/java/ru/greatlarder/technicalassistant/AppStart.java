package ru.greatlarder.technicalassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.MainController;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.repository.UserRepository;
import ru.greatlarder.technicalassistant.repository.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.IOException;
import java.util.Objects;

public class AppStart extends Application {
    FileManager fileManager = new FileManagerImpl();
    UserRepository userRepository = new UserRepositoryImpl();

    private User getUser() {
        User user = userRepository.getUser();
        if (user.getId() == 0) {
            return null;
        } else return user;
    }

    @Override
    public void start(Stage stage) throws IOException {
        fileManager.createProjectDirectories();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/ru/greatlarder/technicalassistant/mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png")))));
        stage.setTitle("Technical Assistant");
        MainController controller = fxmlLoader.getController();
        controller.updateUser(getUser());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}