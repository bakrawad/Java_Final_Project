package com.example.ph23;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;

public class HelloApplication extends Application {
    Inventory store = new Inventory("groceries");
    private Button btadd,btmodify,btdel,btstock,btfilechoser;

    private Stage stage5 = new Stage();
    private CheckBox cfile,ctext;

    public void start(Stage stage) throws IOException {
        Image image = new Image("inventoryManagement.jpg");
        ImageView vie = new ImageView(image);
        vie.setFitHeight(250);
        vie.setFitWidth(300);
        vie.setRotate(40);
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: #76817f;");
       // Pane p = new Pane();
        btadd = new Button("Add");
        btmodify = new Button("Modify");
        btdel = new Button("Delete");
        btstock = new Button("Stock Report");
        btfilechoser = new Button("choose a file");
        btmain btmain = new btmain();
        btadd.setOnAction(btmain);
        btmodify.setOnAction(btmain);
        btdel.setOnAction(btmain);
        btstock.setOnAction(btmain);
        btfilechoser.setOnAction(btmain);
        Label lbonman = new Label("Inentory Management System : Comp 2311 project,Phase 2");
        lbonman.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
        HBox hlb = new HBox();
        hlb.getChildren().add(lbonman);
        hlb.setAlignment(Pos.CENTER);
        bp.setTop(hlb);
        bp.setCenter(vie);
        HBox hbut = new HBox(10);
        hbut.getChildren().addAll(btadd,btmodify,btdel,btstock,btfilechoser);
        bp.setBottom(hbut);
        hbut.setAlignment(Pos.CENTER);
        Scene scene = new Scene(bp,500, 500);
        stage.setTitle("\"Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    class btmain implements EventHandler<ActionEvent> {// handler for button in main stage

        @Override
        public void handle(ActionEvent e) {
            if (e.getSource() == btadd) {


                StageAdd stageAdd = new StageAdd(store);//open stage add item
                store= stageAdd.store1();
            } else if (e.getSource() == btdel) {
                System.out.println("delete");
                StageDelete stageDelete = new StageDelete(store); //open stage delete
                store= stageDelete.store1(); // return the store

            } else if (e.getSource() == btmodify) {
                System.out.println("modify");

        StageModify stageModify = new StageModify(store);//open stage modify
                store= stageModify.store1();

            } else if (e.getSource() == btstock) {
                System.out.println("stock");
                StageStock stageStock = new StageStock(store); // open stage Stock Report
                store= stageStock.store1();


            } else if (e.getSource() == btfilechoser) {
                FileChooser fc = new FileChooser();
                File file = fc.showOpenDialog(stage5);// open stage file chooser
                if (file != null) {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        String s [] = new String[3];
                        while ((line = br.readLine()) != null) {
                            s = line.split(":");
                            if (s[3].equals(";")){
                                store.newItem(s[0], Integer.parseInt(s[2]), Double.parseDouble(s[1]));
                            }else {
                                store.newItem(s[3],s[0], Integer.parseInt(s[2]), Double.parseDouble(s[1]));
                            }


                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Done!");
                        alert.setContentText("the item has been added!!");
                        alert.show();
                        br.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();

                    }
                    catch (Exception e1) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error!");
                        alert.setContentText("Please Select a valid file !!");
                        alert.show();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        launch();
    }
}