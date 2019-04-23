package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.DummyParentime;
import be.atemi.decision.parentime.helper.CircleFileReader;
import be.atemi.decision.parentime.james.BestCirclePlanningSolution;
import be.atemi.decision.parentime.james.CirclePlanningBestSolution;
import be.atemi.decision.parentime.james.valuetype.SearchAlgorithm;
import be.atemi.decision.parentime.javafx.planning.ChromosomeCirclePlanning;
import be.atemi.decision.parentime.javafx.planning.SolutionCirclePlanning;
import be.atemi.decision.parentime.jenetics.BestCirclePlanningGenotype;
import be.atemi.decision.parentime.jenetics.CirclePlanningGenotype;
import be.atemi.decision.parentime.model.Circle;
import com.fxgraph.cells.RectangleCell;
import com.fxgraph.cells.TriangleCell;
import com.fxgraph.edges.CorneredEdge;
import com.fxgraph.edges.DoubleCorneredEdge;
import com.fxgraph.edges.Edge;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.RandomLayout;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Parentime extends Application {

    VBox dataBox = new VBox();
    CirclePlanning planningVNS;
    CirclePlanning planningGA;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Parentime");

        Circle circle = CircleFileReader.getInstance().read("efc.xml");

        BorderPane border = new BorderPane();
        HBox hbox = addHBox(circle);
        border.setTop(hbox);
        //border.setLeft(addRelationGraphBox(circle));
        addStackPane(hbox);         // Add stack to HBox in top region

        border.setCenter(addRelationGraphBox(circle));

        dataBox.getChildren().add(addAgendaBox(circle));

        border.setRight(dataBox);

        primaryStage.setScene(new Scene(border));
        primaryStage.show();

    }

    public HBox addHBox(Circle circle) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonOpen = new Button("Open...");
        buttonOpen.setPrefSize(100, 20);

        Button buttonVNS = new Button("VNS");
        buttonVNS.setPrefSize(100, 20);
        buttonVNS.setOnMouseClicked(event -> {

            BestCirclePlanningSolution resultVNS = CirclePlanningBestSolution.compute(circle, DummyParentime.variableNeighbourhoodSearchConstraints(),
                    1, SearchAlgorithm.VARIABLE_NEIGHBOURHOOD_SEARCH, 10);

            planningVNS = new SolutionCirclePlanning(resultVNS.getBestSolution(), circle.getStepfamilies());

            dataBox.getChildren().add(planningVNS);
        });

        Button buttonGA = new Button("GA");
        buttonGA.setPrefSize(100, 20);
        buttonGA.setOnMouseClicked(event -> {

            BestCirclePlanningGenotype resultAG = CirclePlanningGenotype.compute(circle, DummyParentime.geneticAlgorithmConstraints());

            planningGA = new ChromosomeCirclePlanning(resultAG.getGenotype(), circle.getStepfamilies());

            dataBox.getChildren().add(planningGA);
        });

        hbox.getChildren().addAll(buttonOpen, buttonVNS, buttonGA);

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

        VBox vbox = new VBox();

        Graph graph = new Graph();
        final Model model = graph.getModel();

        graph.beginUpdate();

        final ICell cellA = new RectangleCell();
        final ICell cellB = new RectangleCell();
        final ICell cellC = new RectangleCell();
        final ICell cellD = new TriangleCell();
        final ICell cellE = new TriangleCell();
        final ICell cellF = new RectangleCell();
        final ICell cellG = new RectangleCell();

        model.addCell(cellA);
        model.addCell(cellB);
        model.addCell(cellC);
        model.addCell(cellD);
        model.addCell(cellE);
        model.addCell(cellF);
        model.addCell(cellG);

        final Edge edgeAB = new Edge(cellA, cellB);
        edgeAB.textProperty().set("Edges can have text too!");
        model.addEdge(edgeAB);
        final CorneredEdge edgeAC = new CorneredEdge(cellA, cellC, Orientation.HORIZONTAL);
        edgeAC.textProperty().set("Edges can have corners too!");
        model.addEdge(edgeAC);
        model.addEdge(cellB, cellD);
        final DoubleCorneredEdge edgeBE = new DoubleCorneredEdge(cellB, cellE, Orientation.HORIZONTAL);
        edgeBE.textProperty().set("You can implement custom edges and nodes too!");
        model.addEdge(edgeBE);
        model.addEdge(cellC, cellF);
        model.addEdge(cellC, cellG);

        graph.endUpdate();

        graph.layout(new RandomLayout());

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

    public FlowPane addFlowPane() {


        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setStyle("-fx-background-color: DAE6F3;");

        ImageView pages[] = new ImageView[8];
//        for (int i=0; i<8; i++) {
//            pages[i] = new ImageView(
//                    new Image(Parentime.class.getResourceAsStream(
//                            "graphics/chart_"+(i+1)+".png")));
//            flow.getChildren().add(pages[i]);
//        }

        return flow;
    }
}
