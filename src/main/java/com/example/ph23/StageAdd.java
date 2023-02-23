package com.example.ph23;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StageAdd extends Stage {

    private RadioButton rbyes,rbno;
    Inventory store;
    private Button bticancl , btisave;
    private Label lbBrandname,lbBrand,lbType,lbQunt,lbPrice,lbmsg;
    private TextField fitem,fbrand,ftype,fqunt,fprice,fbBrandname ;
    private ToggleGroup group;
    private ComboBox<String> comboBox;
    HelloApplication hp = new HelloApplication();
    public RadioButton getRbyes() {
        return rbyes;
    }

    public void setRbyes(RadioButton rbyes) {
        this.rbyes = rbyes;
    }

    public RadioButton getRbno() {
        return rbno;
    }

    public void setRbno(RadioButton rbno) {
        this.rbno = rbno;
    }

    public Button getBticancl() {
        return bticancl;
    }

    public void setBticancl(Button bticancl) {
        this.bticancl = bticancl;
    }

    public Button getBtisave() {
        return btisave;
    }

    public void setBtisave(Button btisave) {
        this.btisave = btisave;
    }

    public StageAdd(Inventory store) {
    this.store=store;
        GridPane gp = new GridPane();
        gp.setVgap(5);
        gp.setHgap(5);
        gp.setStyle("-fx-background-color: #817f76;");
        rbno = new RadioButton("No");
        rbyes = new RadioButton("Yes");
        bticancl = new Button("Cancel");
        btisave = new Button("Save");
        lbBrandname = new Label("Brand Name");
        lbBrand = new Label("Brand");
        lbType = new Label("Type");
        lbQunt = new Label("Quantity");
        lbPrice = new Label("Price");
        fitem = new TextField();
        fbrand = new TextField();
        ftype = new TextField();
        fprice = new TextField();
        fqunt = new TextField();
        lbmsg = new Label("");
        fbBrandname = new TextField();
        lbBrandname.setFont(Font.font("", FontWeight.BOLD, 12));
        lbBrand.setFont(Font.font("", FontWeight.BOLD, 12));
        lbType.setFont(Font.font("", FontWeight.BOLD, 12));
        lbQunt.setFont(Font.font("", FontWeight.BOLD, 12));
        lbPrice.setFont(Font.font("", FontWeight.BOLD, 12));
        lbmsg.setFont(Font.font("", FontWeight.BOLD, 12));

        btnewitem btnewit = new btnewitem();
        gp.add(lbBrand, 0, 0);
        gp.add(lbType, 0, 1);
        gp.add(lbQunt, 0, 2);
        gp.add(lbPrice, 0, 3);
        gp.add(lbBrandname, 0, 4);
        gp.add(rbno, 1, 0);
        gp.add(rbyes, 3, 0);
        // gp.add(fitem ,0,0);
        //gp.add(fbrand ,4,1);
        gp.add(ftype, 4, 1);
        gp.add(fqunt, 4, 2);
        gp.add(fprice, 4, 3);
        gp.add(fbBrandname, 4, 4);
        gp.add(btisave, 3, 6);
        gp.add(bticancl, 4, 6);
        gp.add(lbmsg, 0, 8);
        fbBrandname.setVisible(false);
        lbBrandname.setVisible(false);
       rbyes.setOnAction(btnewit);
       rbno.setOnAction(btnewit);
       bticancl.setOnAction(btnewit);
        btisave.setOnAction(btnewit);
        group = new ToggleGroup();
        rbno.setToggleGroup(group);
        rbyes.setToggleGroup(group);
        Scene scene = new Scene(gp, 500, 250);
        setScene(scene);
        setTitle("Add New Item");
        show();
    }
    class btnewitem implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            {
                if (rbno.isSelected()) {
                    fbBrandname.setVisible(false);
                    lbBrandname.setVisible(false);
                } else if (rbyes.isSelected()) {
                    fbBrandname.setVisible(true);
                    lbBrandname.setVisible(true);
                }
            }
            if (e.getSource()==btisave){//to check if radio button selected
                if (!rbno.isSelected() && !rbyes.isSelected()) {	
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setContentText("Please Select Brand or not !!");
                    alert.show();
                }
                String type,qunt,price,brand;
                type=ftype.getText();
                qunt=fqunt.getText();
                price=fprice.getText();

                try {
                    Integer.parseInt(qunt);						//to check of number valid or not
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
                    if (rbno.isSelected()){
                        type=ftype.getText();
                        qunt=fqunt.getText();
                        price=fprice.getText();
                        if (ftype.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error!");
                            alert.setContentText("Please add a valid value !");
                            alert.show();
                            return;
                        }
                        if (store.find(type,false)<0){

                            store.newItem(type,Integer.parseInt(qunt),Double.parseDouble(price));
                            getStore(store);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Done!");
                            alert.setContentText("the item "+type+" is added");
                            alert.show();
                        }
                        else {
                            lbmsg.setText("the item "+type+" is already exists");
                        }


                    }
                    else if (rbyes.isSelected()){//to display the label and the TextBox of brand
                        type=ftype.getText();
                        qunt=fqunt.getText();
                        price=fprice.getText();
                        brand = fbBrandname.getText();
                        if (ftype.getText().equals("")||fbBrandname.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error!");
                            alert.setContentText("Please add a valid value !");
                            alert.show();
                            return;
                        }
                        if (store.findbrand(type,false,brand)<0){
                            store.newItem(brand,type,Integer.parseInt(qunt),Double.parseDouble(price));//to check if item found or not !
                            getStore(store);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Done!");
                            alert.setContentText("the item "+type+" is added");
                            alert.show();
                        }
                        else {
                            lbmsg.setText("the item "+type+" is already exists");
                        }

                    }
                }
                System.out.println("save");
                store.stockReport();
            }
            else if (e.getSource()==bticancl){
                System.out.println("Cancel");
                close();
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
