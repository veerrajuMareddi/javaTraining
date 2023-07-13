package com.rgt.library.management;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateBookView {
    private final LibraryController libraryController;
    private final Book bookToUpdate;

    public UpdateBookView(LibraryController libraryController, Book bookToUpdate) {
        this.libraryController = libraryController;
        this.bookToUpdate = bookToUpdate;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Update Book");

        // Create UI components
        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField(bookToUpdate.getTitle());

        Label authorLabel = new Label("Author:");
        TextField authorField = new TextField(bookToUpdate.getAuthor());

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField(bookToUpdate.getIsbn());

        Button updateButton = new Button("Update");
        Button cancelButton = new Button("Cancel");

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.add(titleLabel, 0, 0);
        gridPane.add(titleField, 1, 0);
        gridPane.add(authorLabel, 0, 1);
        gridPane.add(authorField, 1, 1);
        gridPane.add(isbnLabel, 0, 2);
        gridPane.add(isbnField, 1, 2);
        gridPane.add(updateButton, 0, 3);
        gridPane.add(cancelButton, 1, 3);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();

        // Event handling
        updateButton.setOnAction(event -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();

            // Validate input
            if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
                return;
            }

            // Update the book
            bookToUpdate.setTitle(title);
            bookToUpdate.setAuthor(author);
            bookToUpdate.setIsbn(isbn);

            // Update the book in the library
            boolean success = libraryController.updateBook(bookToUpdate);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully!");
                stage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update the book!");
            }
        });

        cancelButton.setOnAction(event -> stage.close());
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
