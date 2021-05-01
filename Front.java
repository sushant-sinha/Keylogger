import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.*;
import java.util.concurrent.CountDownLatch;


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
        Button btn = new Button("Enter Password");
        Button rbtn = new Button("Start");

        rbtn.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: 60px; " +
                        "-fx-min-height: 60px; " +
                        "-fx-max-width: 60px; " +
                        "-fx-max-height: 60px;"
        );

        Server s=new Server();
        Client c = new Client();

        Scene scene = new Scene(root, 1500, 800);
        Scene scene1 = new Scene(root1, 1500, 800);
        root.setHgap(25);
        root.setVgap(50);
        root1.setHgap(25);
        root1.setVgap(50);
        root.add(btn, 27, 7);
        root1.add(rbtn, 30, 3);
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
                int i = 0;
                if (rbtn.getText().equals("Start")) {
                    System.out.println("Starting Client");
                    i++;

                    s.start();

                    c.start();
                    rbtn.setText("Stop");

                }
                if (i == 0) {
                    System.out.println("Stoping");
                    c.stop();
                    s.stop();

                    rbtn.setText("Start");
                }
            }
        });

    }

}
/*
ok
 */