package com.rgt.library.management;

import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LibraryManagementSystemView {
    private final LibraryController libraryController;

    public LibraryManagementSystemView(LibraryController libraryController) {
        this.libraryController = libraryController;
    }

    public void show() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Library Management System");

        // Create UI components
        TableView<Book> bookTable = new TableView<>();
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        bookTable.getColumns().addAll(titleColumn, authorColumn, isbnColumn);

        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(bookTable, 0, 0, 3, 1);
        gridPane.add(addButton, 0, 1);
        gridPane.add(updateButton, 1, 1);
        gridPane.add(deleteButton, 2, 1);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event handling
        addButton.setOnAction(event -> {
            AddBookView addBookView = new AddBookView(libraryController);
            addBookView.show();
        });

        updateButton.setOnAction(event -> {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                UpdateBookView updateBookView = new UpdateBookView(libraryController, selectedBook);
                updateBookView.show();
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "No book selected!");
            }
        });

        deleteButton.setOnAction(event -> {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                boolean deleted = libraryController.deleteBook(selectedBook);
                if (deleted) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Book deleted successfully!");
                    bookTable.getItems().remove(selectedBook);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete book!");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "No book selected!");
            }
        });

        // Load initial data
        List<Book> books = libraryController.getAllBooks();
        bookTable.getItems().addAll(books);
        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
        authorColumn.setCellValueFactory(data -> data.getValue().authorProperty());
        isbnColumn.setCellValueFactory(data -> data.getValue().isbnProperty());
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}

