package com.example.ph23;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StageModify extends Stage {

    Inventory store ;
    HelloApplication hp = new HelloApplication();
    private Button btmcancl , btmupdate,msearch;
    private Label mlbBrandname,mlbBrand,mlbType,mlbQunt,mlbPrice,mlbmsg;
    private TextField mfitem,mfbrand,mftype,mfqunt,mfprice,mfbBrandname ;
    private GridPane gpdel;
    private ComboBox<String> comboBox;
    public StageModify(Inventory store){
        this.store=store;
        gpdel = new GridPane();
        comboBox = new ComboBox<>();
        gpdel.setStyle("-fx-background-color: #76817f;");
        comboBox.getItems().addAll("Brand", "Not Brand");
        gpdel.setHgap(5);
        gpdel.setVgap(5);
        mlbType = new Label("Item");
        mlbQunt = new Label("Quantity");
        mlbPrice = new Label("Price");
        mlbBrand = new Label("Brand");
        mfitem = new TextField();
        mfbrand = new TextField();
        mfprice = new TextField();
        mfqunt = new TextField();
        mlbmsg = new Label("the item is arlady exiset");
        mlbType.setFont(Font.font("", FontWeight.BOLD, 12));
        mlbQunt.setFont(Font.font("", FontWeight.BOLD, 12));
        mlbPrice.setFont(Font.font("", FontWeight.BOLD, 12));
        mlbBrand.setFont(Font.font("", FontWeight.BOLD, 12));
        btmupdate = new Button("Update");
        btmcancl = new Button("Cancel");
        msearch = new Button("Search");
        HBox h = new HBox(5);
        h.getChildren().addAll(btmupdate, btmcancl);
        gpdel.add(comboBox, 1, 0);
        gpdel.add(mlbType, 0, 1);
        gpdel.add(mlbQunt, 0, 2);
        gpdel.add(mlbPrice, 0, 3);
        gpdel.add(mlbBrand, 3, 1);
        gpdel.add(mfitem, 1, 1);
        gpdel.add(mfbrand, 4, 1);
        gpdel.add(mfprice, 1, 3);
        gpdel.add(mfqunt, 1, 2);
        gpdel.add(msearch, 3, 1);
        gpdel.add(h, 1, 5);
        mlbBrand.setVisible(false);
        mfbrand.setVisible(false);
        btupdate btupdate = new btupdate();
        btmupdate.setOnAction(btupdate);
        btmcancl.setOnAction(btupdate);
        comboBox.setOnAction(btupdate);
        msearch.setOnAction(btupdate);
        Scene scene2 = new Scene(gpdel, 465, 250);
        setTitle("Update item");
        setScene(scene2);
        show();
    }
    class btupdate implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            {
                try {
                    if ("Brand".equals(comboBox.getValue())){
                        System.out.println("brand");
                        mlbBrand.setVisible(true);
                        mfbrand.setVisible(true);
                        gpdel.getChildren().remove(msearch);
                        gpdel.add(msearch,5,1);
                    }
                    else if ("Not Brand".equals(comboBox.getValue())){
                        mlbBrand.setVisible(false);
                        mfbrand.setVisible(false);
                        gpdel.getChildren().remove(msearch);
                        gpdel.add(msearch,3,1);
                    }
                }catch (IllegalArgumentException e1){
                    System.out.print(e1.toString());
                }

            }
            if (e.getSource()==btmcancl){
                System.out.println("Cancel");
                close();
            }
            else if ((e.getSource()==btmupdate)){//to check of combobox select or not 
                if (comboBox.getValue()==(null)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setContentText("Please Select Brand or not !!");
                    alert.show();

                }
                else
                {
                    String type,qunt,price,brand;
                    type=mfitem.getText();
                    qunt=mfqunt.getText();
                    price=mfprice.getText();

                    try {				//to check of number valid or not
                        Integer.parseInt(qunt);
                        Double.parseDouble(price);
                    }catch (NumberFormatException e1){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error!");
                        alert.setContentText("Please add a valid value !");
                        alert.show();
                        System.out.println(e1.toString());
                        return;
                    }
                    {
                        if ("Not Brand".equals(comboBox.getValue())){
                            type=mfitem.getText();
                            qunt=mfqunt.getText();
                            price=mfprice.getText();
                            if (store.find(type,false)>=0){
                                store.update1(type,Double.parseDouble(price),Integer.parseInt(qunt));
                                getStore(store);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Done!");
                                alert.setContentText("the item "+type+" is Updated");
                                alert.show();
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Error!");
                                alert.setContentText("the item Not Found!!");
                                alert.show();
                            }


                        }
                        else if ("Brand".equals(comboBox.getValue())){
                            type=mfitem.getText();
                            qunt=mfqunt.getText();
                            price=mfprice.getText();
                            brand = mfbrand.getText();
                            if (store.findbrand(type,false,brand)>=0){
                                store.update1(brand,type,Double.parseDouble(price),Integer.parseInt(qunt));//to check if item found or not
                                getStore(store);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Done!");
                                alert.setContentText("the item "+type+" is Updated");
                                alert.show();
                            }
                            else{
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Error!");
                                alert.setContentText("the item Not Found!!");
                                alert.show();
                            }

                        }
                    }

                }
                System.out.println("Update");


            }
            else if ((e.getSource()==msearch)) {
                String item, brand;
                if (comboBox.getValue()==(null)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setContentText("Please Select Brand or not !!");
                    alert.show();

                }
                if ("Brand".equals(comboBox.getValue())) {//to display the label and the TextBox of brand
                    item = mfitem.getText();
                    brand = mfbrand.getText();
                    if (store.findbrand(item, false, brand) >= 0) {//to check if item found or not
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Done!");
                        alert.setContentText("the item is Found !");
                        alert.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error!");
                        alert.setContentText("the item is Not Found !");
                        alert.show();
                    }
                    System.out.println("Search");
                }

                else if ("Not Brand".equals(comboBox.getValue())){
                    item = mfitem.getText();

                    if (store.find(item,false)>=0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Done!");
                        alert.setContentText("the item is Found !");
                        alert.show();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error!");
                        alert.setContentText("the item is Not Found !");
                        alert.show();
                    }
                }
            }

        }
    }
    private Inventory getStore (Inventory store){
        this.store=(store);
        return this.store;
    }
    public Inventory store1(){//to return the store
        return this.store;
    }
}
