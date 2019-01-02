package controller;

import abstractClasses.AbstractPlane;
import cudaUtils.CudaSceneDataBox;
import cudaUtils.CudaSceneMetadata;
import javafx.animation.Transition;
import javafx.stage.Stage;
import utility.Position;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private interfaces.View view;
    public Controller(Stage primaryStage) {
        this.view = new View(primaryStage);
    }

    public void setAnimation() {
        AbstractPlane plane = view.getPlane();
        final double DISTANCE = 12;
        final double FIRST_DISTANCE_FROM_TOP = 12;
        final int NUMBER_OF_ROWS = 10;
        final double AGENT_SIZE = 10;

        plane.setAgentsSize(AGENT_SIZE);
        List<Position> startPositions = new ArrayList<>();
        List<List<Position>> paths = new ArrayList<>();
        final double center = plane.getWidth() / 2;
        for (double distanceFromTop = FIRST_DISTANCE_FROM_TOP, loop = 0;
             loop <= NUMBER_OF_ROWS; distanceFromTop += DISTANCE, loop++) {
            for (double i = center; i + DISTANCE/2 < plane.getWidth(); i += DISTANCE) {
                startPositions.add(new Position(i, distanceFromTop));
                List<Position> moves = new ArrayList<>();
                for (double j = i; j >= center - (i - center) - 1; j -= 2) {
                    moves.add(new Position(j, distanceFromTop +
                            Math.sqrt(Math.pow(i-center, 2) - Math.pow(Math.abs(j-center), 2))));
                }
                moves.add(new Position(center - (i - center), distanceFromTop));
                paths.add(moves);
            }
        }

        plane.addAgents(startPositions);
        Transition transition = plane.getPathTranslateAgents(paths);

        setPlaneOnMouseClick(startPositions, plane, transition);
    }

    public void setAnimation(CudaSceneDataBox sceneDataBox){
        AbstractPlane plane = view.getPlane();



//        plane.setAgentsSize(AGENT_SIZE);
//        plane.addAgents(sceneDataBox.getStartPositions());
//        Transition transition = plane.getPathTranslateAgents(sceneDataBox.getPaths());
//
//        setPlaneOnMouseClick(sceneDataBox.getStartPositions(), plane, transition);
    }

    private void setPlaneOnMouseClick(List<Position> agents, AbstractPlane plane, Transition transition) {
        plane.setOnMouseClicked(event -> {
            transition.stop();
            plane.setAgentsPositions(agents);
            transition.playFromStart();
        });
    }
}
