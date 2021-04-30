import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javax.swing.JOptionPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;

public class Front extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root=new GridPane();
        GridPane root1=new GridPane();
        Button btn=new Button("Enter Password");
        Scene scene=new Scene(root,3000,800);
        Scene scene1=new Scene(root1,3000,800);
        root.setHgap(50);
        root.setVgap(50);
        root.add(btn,18,7);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KEY LOGGER");
        primaryStage.show();

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                int i=0;
                while(i<3){
                    i++;
                    String input = JOptionPane.showInputDialog(null,"ENTER PASSWORD: ","SECURED ENTRY",JOptionPane.INFORMATION_MESSAGE);
                    if(input.equals("1605409"))
                    {
                       primaryStage.setScene(scene1);
                        break;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "WRONG PASSWORD....TRY AGAIN "+(3-i)+" CHANCES LEFT", "OOPS", JOptionPane.ERROR_MESSAGE);
                    }
                    if(i==3){System.exit(0);}
                }

            }
        });


        }
}
