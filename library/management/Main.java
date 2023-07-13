package com.rgt.library.management;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        String filePath = "library"; // Replace with the actual file path
        LibraryController libraryController = new LibraryController(filePath);
        LibraryManagementSystemView libraryManagementSystemView = new LibraryManagementSystemView(libraryController);
        libraryManagementSystemView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
