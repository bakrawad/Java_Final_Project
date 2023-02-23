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

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StageStock extends Stage {
    Inventory store ;

    private Button btdcancl , btdelete,dsearch,btscans,btexport;

    private TextField tfstock;

    private TextArea ta;
    private CheckBox cfile,ctext;

    public StageStock(Inventory store){
        this.store = store;
        GridPane bp = new GridPane();
        bp.setStyle("-fx-background-color: #76817f;");
        btstock btstock = new btstock();
        Label lbtitle = new Label("The following options can be used to print a report");
        lbtitle.setFont(Font.font("", FontWeight.BOLD, 16));
        HBox hlb = new HBox();
        cfile = new CheckBox("Export a copy to file");
        ctext = new CheckBox("TextArea");
        btexport = new Button("Export");
        tfstock = new TextField();
        HBox htfs = new HBox(5);
        htfs.getChildren().add(tfstock);
        bp.add(htfs, 0, 4);
        bp.add(btexport, 0, 5);
        hlb.getChildren().add(lbtitle);
        ta = new TextArea();
        HBox hta = new HBox();
        btscans = new Button("Cancel");

        bp.add(hlb, 0, 0);
        bp.add(btscans, 0, 25);
        ta.setEditable(false);
        hta.getChildren().add(ta);

        bp.add(hta, 6, 10);
        HBox hcb = new HBox(5);
        hcb.getChildren().addAll(cfile, ctext);
        bp.add(hcb, 0, 1);
        ctext.setOnAction(btstock);
        cfile.setOnAction(btstock);
        btscans.setOnAction(btstock);
        btexport.setOnAction(btstock);
        btexport.setVisible(false);
        tfstock.setVisible(false);
        ta.setVisible(false);
        Scene scene = new Scene(bp, 900, 400);
      setScene(scene);
        setTitle("Stock Report");
        show();
    }
    class btstock implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            {

                if (ctext.isSelected()){
                    System.out.println("text");
                    ta.setVisible(true);
                    if (store.stocksum()>0){
                        ta.setText(store.stockReport1()+"\n"+"the Total value :"+store.stocksum());

                    }
                }
                if (cfile.isSelected()){
                    btexport.setVisible(true);
                    tfstock.setVisible(true);
                }
            }
            if (!ctext.isSelected()){
                ta.setVisible(false);
            }
            if (!cfile.isSelected()){
                btexport.setVisible(false);
                tfstock.setVisible(false);
            }
            if (e.getSource()==btscans){
                close();
            }
            if (e.getSource()==btexport){
                String se= tfstock.getText();
                String print = store.stockReport1();
                int num = store.stockReport1().length();
                if (se==null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setContentText("the file name is not valid");
                    alert.show();
                    se="";
                    return;

                }
                else {
                    try {
                        PrintWriter pw = new PrintWriter(se);
                        if (!(num ==0)){
                            pw.println(print);
                            pw.println("the Total value :"+store.stocksum());
                            pw.close();

                        }
                    } catch (FileNotFoundException ex) {//to check if the file name valid 
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error!");
                        alert.setContentText("the file name is not valid");
                        alert.show();
                        System.out.println(ex.toString());
                    }
                    catch (NullPointerException ex) {
                        System.out.println(ex.toString());
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Done!");
                    alert.setContentText("the file Is Export");
                    alert.show();
                }

            }
        }

    }
    private Inventory getStore (){
        return this.store;
    }
    public Inventory store1(){ //to return the store
        return this.store;
    }
}
