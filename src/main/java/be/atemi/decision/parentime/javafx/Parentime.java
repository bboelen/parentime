package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.DummyParentime;
import be.atemi.decision.parentime.helper.CircleFileReader;
import be.atemi.decision.parentime.james.BestCirclePlanningSolution;
import be.atemi.decision.parentime.james.CirclePlanningBestSolution;
import be.atemi.decision.parentime.james.valuetype.SearchAlgorithm;
import be.atemi.decision.parentime.javafx.fxgraph.CircleGraph;
import be.atemi.decision.parentime.javafx.planning.ChromosomeCirclePlanning;
import be.atemi.decision.parentime.javafx.planning.SolutionCirclePlanning;
import be.atemi.decision.parentime.jenetics.BestCirclePlanningGenotype;
import be.atemi.decision.parentime.jenetics.CirclePlanningGenotype;
import be.atemi.decision.parentime.model.Circle;
import com.fxgraph.graph.Graph;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class Parentime extends Application {

    private Desktop desktop = Desktop.getDesktop();
    public static Circle circle;
    private String filename;

    private VBox dataBox = new VBox();
    private VBox planningVNS;
    private VBox planningGA;
    private VBox agendaBox;

    private BorderPane border;

    @Override
    public void start(Stage primaryStage) throws Exception {

        circle = CircleFileReader.getInstance().read("efc.xml");

        filename = "efc.xml";

        primaryStage.setTitle("Parentime");

        border = new BorderPane();
        HBox hbox = addToolBox(primaryStage);
        border.setTop(hbox);
        addStackPane(hbox);

        border.setCenter(addRelationGraphBox(circle));
        agendaBox = addAgendaBox(circle);
        dataBox.getChildren().add(agendaBox);
        border.setRight(dataBox);

        primaryStage.setScene(new Scene(border, 1500, 800));
        primaryStage.show();
    }

    public HBox addToolBox(Stage primaryStage) {

        FileChooser fileChooser = new FileChooser();

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonOpen = new Button("Open a circle...");
        buttonOpen.setPrefSize(100, 20);
        buttonOpen.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                openCircle(file);
            }
        });

        Button buttonCompute = new Button("Compute...");
        buttonCompute.setPrefSize(100, 20);
        buttonCompute.setOnMouseClicked(event -> {

            dataBox.getChildren().clear();
            dataBox.getChildren().add(agendaBox);

            BestCirclePlanningSolution resultVNS = CirclePlanningBestSolution.compute(Parentime.circle, DummyParentime.variableNeighbourhoodSearchConstraints(),
                    1, SearchAlgorithm.VARIABLE_NEIGHBOURHOOD_SEARCH, 10);
            planningVNS = addPlanning("Plannings (VNS)", new SolutionCirclePlanning(resultVNS.getBestSolution(), Parentime.circle.getStepfamilies()));
            dataBox.getChildren().add(planningVNS);

            BestCirclePlanningGenotype resultAG = CirclePlanningGenotype.compute(Parentime.circle, DummyParentime.geneticAlgorithmConstraints());
            planningGA = addPlanning("Plannings (GA)", new ChromosomeCirclePlanning(resultAG.getGenotype(), Parentime.circle.getStepfamilies()));
            dataBox.getChildren().add(planningGA);
        });

        hbox.getChildren().addAll(buttonOpen, buttonCompute);

        return hbox;
    }

    public void addStackPane(HBox hb) {
        StackPane stack = new StackPane();
        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop[]{
                        new Stop(0, Color.web("#4977A3")),
                        new Stop(0.5, Color.web("#B0C6DA")),
                        new Stop(1, Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);

        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));

        stack.getChildren().addAll(helpIcon, helpText);
        stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); // Center "?"

        hb.getChildren().add(stack);            // Add to HBox from Example 1-2
        HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space
    }

    public VBox addRelationGraphBox(Circle circle) {

        Text title = new Text(filename);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        VBox vbox = new VBox();
        Graph graph = new CircleGraph(circle);
        vbox.getChildren().add(title);
        vbox.getChildren().add(graph.getCanvas());
        return vbox;
    }

    public VBox addAgendaBox(Circle circle) {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Agendas");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        AgendaGrid agendas = new AgendaGrid(circle);

        vbox.getChildren().add(title);
        vbox.getChildren().add(agendas);

        return vbox;

    }

    public VBox addPlanning(String text, CirclePlanning planning) {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text(text);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        vbox.getChildren().add(title);
        vbox.getChildren().add(planning);

        return vbox;
    }

    private void openCircle(File file) {

        circle = CircleFileReader.getInstance().read(file);

        filename = file.getPath();

        if(agendaBox != null) {
            agendaBox.getChildren().clear();
        }

        dataBox.getChildren().clear();

        border.setCenter(addRelationGraphBox(circle));
        agendaBox = addAgendaBox(circle);
        dataBox.getChildren().add(agendaBox);
        border.setRight(dataBox);
    }
}
