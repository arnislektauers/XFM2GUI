package uk.ac.aber.les35;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import uk.ac.aber.les35.initializers.MenuInitialiser;
import uk.ac.aber.les35.functionhandlers.SerialCommandHandler;
import uk.ac.aber.les35.functionhandlers.MenuEventHandlers;
import uk.ac.aber.les35.functionhandlers.ParamValueChangeHandler;

import java.io.IOException;

public class Main extends Application {

    String[] serialPortNameList = SerialPortList.getPortNames();
    SerialPort serialPort;
    SerialCommandHandler serialCommandHandler = new SerialCommandHandler(serialPort);

    Boolean LIVE_CHANGES = false;

    @Override
    public void start(Stage primaryStage) throws IOException, SerialPortException {
        MenuInitialiser menuInitialiser = new MenuInitialiser(serialCommandHandler, serialPort, serialPortNameList);
        Scene scene = menuInitialiser.initializeScene();
        ParamValueChangeHandler.setSerialCommandHandler(serialCommandHandler);

        LIVE_CHANGES = false;
        MenuEventHandlers.setAllIntFieldValues(serialCommandHandler.getAllValues());
        LIVE_CHANGES = true;

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("XFM2GUI");
        primaryStage.show();

        serialCommandHandler.setLIVE_CHANGES(true);
    }

    public static void main(String[] args) {
        launch(args);
    }















}
