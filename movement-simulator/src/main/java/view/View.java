package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class View {
    private Stage primaryStage;
    private Pane root;
    public View(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializePrimaryStage();
        this.primaryStage.show();
    }

    private void initializePrimaryStage() {
        this.root = new Pane();
        this.primaryStage.setFullScreen(true);
        this.root.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.root.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        this.primaryStage.setTitle("Movement-Simulator");
        this.primaryStage.setScene(new Scene(this.root));
    }
}
