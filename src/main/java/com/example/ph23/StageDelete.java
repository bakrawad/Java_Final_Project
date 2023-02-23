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

public class StageDelete extends Stage {
    Inventory store ;
    HelloApplication hp = new HelloApplication();
    private Button btdcancl , btdelete,dsearch,btscans,btexport;

    private Label dlbBrandname,dlbBrand,dlbType,dlbQunt,dlbPrice,dlbmsg;

    private TextField dfitem,dfbrand,dftype,dfqunt,dfprice,dfbBrandname ;

    private GridPane gpdel;

    private ComboBox<String> comboBox;
    public StageDelete(Inventory store){
        this.store = store;
        System.out.println("delete");
        gpdel = new GridPane();
        gpdel.setStyle("-fx-background-color: #76817f;");
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Brand", "Not Brand");
        gpdel.setHgap(5);
        gpdel.setVgap(5);
        dlbType = new Label("Item");
        dlbQunt = new Label("Quantity");
        dlbPrice = new Label("Price");
        dlbBrand = new Label("Brand");
        dfitem = new TextField();
        dfbrand = new TextField();
        dfprice = new TextField();
        dfqunt = new TextField();
        dlbmsg = new Label("the item is arlady exiset");
        dlbType.setFont(Font.font("", FontWeight.BOLD, 12));
        dlbQunt.setFont(Font.font("", FontWeight.BOLD, 12));
        dlbPrice.setFont(Font.font("", FontWeight.BOLD, 12));
        dlbBrand.setFont(Font.font("", FontWeight.BOLD, 12));
        btdelete = new Button("Delete");
        btdcancl = new Button("Cancel");
        dsearch = new Button("Search");
        HBox h = new HBox(5);
        h.getChildren().addAll(btdelete, btdcancl);
        gpdel.add(comboBox, 1, 0);
        gpdel.add(dlbType, 0, 1);
        gpdel.add(dlbQunt, 0, 2);
        gpdel.add(dlbPrice, 0, 3);
        gpdel.add(dlbBrand, 3, 1);
        gpdel.add(dfitem, 1, 1);
        gpdel.add(dfbrand, 4, 1);
        gpdel.add(dfprice, 1, 3);
        gpdel.add(dfqunt, 1, 2);
        gpdel.add(dsearch, 3, 1);
        gpdel.add(h, 1, 5);
        dlbBrand.setVisible(false);
        dfbrand.setVisible(false);
        btdelete btdelet = new btdelete();
        btdelete.setOnAction(btdelet);
        btdcancl.setOnAction(btdelet);
        comboBox.setOnAction(btdelet);
        dsearch.setOnAction(btdelet);
        Scene scene2 = new Scene(gpdel, 465, 250);
        setTitle("Delete item");
        setScene(scene2);
        show();
    }
    class btdelete implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            {
                try {
                    if ("Brand".equals(comboBox.getValue())){
                        System.out.println("brand");
                        dlbBrand.setVisible(true);
                        dfbrand.setVisible(true);
                        gpdel.getChildren().remove(dsearch);
                        gpdel.add(dsearch,5,1);
                    }
                    else if ("Not Brand".equals(comboBox.getValue())){//to hide the label and Textbox the brand
                        dlbBrand.setVisible(false);
                        dfbrand.setVisible(false);
                        gpdel.getChildren().remove(dsearch);
                        gpdel.add(dsearch,3,1);
                    }
                }catch (IllegalArgumentException e1){
                    System.out.print(e1.toString());
                }

            }
            if (e.getSource()==btdcancl){
                System.out.println("Cancel");
                close();
            }
            else if ((e.getSource()==btdelete)){//to check of combobox select or not 
                if (comboBox.getValue()==(null)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setContentText("Please Select Brand or not !!");
                    alert.show();

                }
                else
                {
                    String type,qunt,price,brand;
                    type=dfitem.getText();
                    qunt=dfqunt.getText();
                    price=dfprice.getText();

                    try {						//to check of number valid or not
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
                            type=dfitem.getText();
                            qunt=dfqunt.getText();
                            price=dfprice.getText();
                            if (store.find(type,false)>=0){
                                store.Delete(type,Double.parseDouble(price),Integer.parseInt(qunt));//to check if item found or not
                                getStore(store);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Done!");
                                alert.setContentText("the item "+type+" is Deleted");
                                alert.show();
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Error!");
                                alert.setContentText("the item Not Found!!");
                                alert.show();
                            }


                        }
                        else if ("Brand".equals(comboBox.getValue())){//to display the label and the TextBox of brand
                            type=dfitem.getText();
                            qunt=dfqunt.getText();
                            price=dfprice.getText();
                            brand = dfbrand.getText();
                            if (store.findbrand(type,false,brand)>=0){
                                store.Delete(brand,type,Double.parseDouble(price),Integer.parseInt(qunt));//to check if item found or not
                                getStore(store);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Done!");
                                alert.setContentText("the item "+type+" is Deleted");
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
                System.out.println("Delete");

            }
            else if ((e.getSource()==dsearch)){
                String item, brand;
                if (comboBox.getValue()==(null)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setContentText("Please Select Brand or not !!");
                    alert.show();

                }
                if ("Brand".equals(comboBox.getValue())) {
                    item = dfitem.getText();
                    brand = dfbrand.getText();
                    if (store.findbrand(item, false, brand) >= 0) {
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
                    item = dfitem.getText();

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
                System.out.println("Search");
            }

        }
    }
    private Inventory getStore (Inventory store){
        this.store=store;
        return this.store;
    }
    public Inventory store1(){//to return the store
        return this.store;
    }
}
