package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class Client extends Application {

    private ObservableList<Person> tutors = FXCollections.observableArrayList();
    private Set<Person> children = new HashSet<>();
    private Set<Person> stepfamilies = new HashSet<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Parentime");
        VBox root = new VBox();
        addMenuBar(root);
        addTabPane(root);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void addMenuBar(VBox root) {
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        root.getChildren().add(menuBar);
    }

    public void addTabPane(VBox root) {
        TabPane tabs = new TabPane();
        Tab tabTutors = new Tab();
        tabTutors.setText("Tutors");
        tabTutors.setContent(tutors());
        Tab tabChildren = new Tab();
        tabChildren.setText("Children");
        Tab tabStepfamilies = new Tab();
        tabStepfamilies.setText("Stepfamilies");
        tabStepfamilies.setContent(stepfamilies());
        Tab tabCalendars = new Tab();
        tabCalendars.setText("Calendars");
        tabs.getTabs().addAll(tabTutors, tabChildren, tabStepfamilies, tabCalendars);
        root.getChildren().add(tabs);
    }

    private Pane tutors() {

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));

        ListView<Person> lvList = new ListView<Person>();
        lvList.setItems(tutors);
        lvList.setMaxHeight(Control.USE_PREF_SIZE);
        lvList.setPrefWidth(150.0);

        border.setLeft(lvList);
//        border.setRight(createButtonColumn());
//        border.setBottom(createButtonRow());  // Uses a tile pane for sizing
//        border.setBottom(createButtonBox());  // Uses an HBox, no sizing

        return border;
    }

    private Pane stepfamilies() {

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));

        ListView<String> lvList = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Hot dog", "Hamburger", "French fries",
                "Carrot sticks", "Chicken salad");
        lvList.setItems(items);
        lvList.setMaxHeight(Control.USE_PREF_SIZE);
        lvList.setPrefWidth(150.0);

        border.setLeft(lvList);
//        border.setRight(createButtonColumn());
//        border.setBottom(createButtonRow());  // Uses a tile pane for sizing
//        border.setBottom(createButtonBox());  // Uses an HBox, no sizing

        return border;
    }
}

