package home.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import main.Production;
import workers.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public Button home_button, orderDesk_button;
    public Pane home_page, orderDesk_page1, orderDesk_page2;
    public Slider nrDesk_slider;
    @FXML
    public Label wood_value;
    public Label desk_value;
    public Label top_value;
    public Label shelf_value;
    public Label legs_value;
    public ProgressBar top_progress;
    public ProgressBar shelf_progress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_page.setVisible(true);
        orderDesk_page1.setVisible(false);
        orderDesk_page2.setVisible(false);
    }


    public void onHomeButtonClick(ActionEvent event) throws IOException {
        home_page.setVisible(true);
        orderDesk_page1.setVisible(false);
        orderDesk_page2.setVisible(false);
    }

    public void onOrderDeskButtonClick(ActionEvent event) throws IOException {
        home_page.setVisible(false);
        orderDesk_page1.setVisible(true);
        orderDesk_page2.setVisible(false);
    }

    public void onSubmitNrDeskButtonClick(ActionEvent event) throws IOException {
        home_page.setVisible(false);
        orderDesk_page1.setVisible(false);
        orderDesk_page2.setVisible(true);


        Production prod = Production.createProduction();
        Production.DESIRED_DESKS = (int) nrDesk_slider.getValue();
        TopDesigner topDesigner= new TopDesigner(prod);
        ShelfAssembler shelfAssembler = new ShelfAssembler(prod);

        prod.addWorker(new WoodCutter(prod));
        prod.addWorker(new LegCarver(prod));
        prod.addWorker(shelfAssembler);
        prod.addWorker(topDesigner);
        prod.addWorker(new DeskAssembler(prod));
        prod.startWorkline();


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            desk_value.setText(String.valueOf(prod.getDesks()));
                            wood_value.setText(String.valueOf(prod.getWood()));
                            top_value.setText(String.valueOf(prod.getTops()));
                            shelf_value.setText(String.valueOf(prod.getShelves()));
                            legs_value.setText(String.valueOf(prod.getLegs()));

                            top_progress.setProgress(topDesigner.getSavedWood() / 4.0);
                            shelf_progress.setProgress(shelfAssembler.getSavedWood() / 10.0);
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        break;
                    }

                    if (desk_value.getText().equals(String.valueOf(Production.DESIRED_DESKS))) {
                        callNotif();
                        return;
                    }

                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void callNotif()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Your desks are assembled and ready for delivery");
                alert.setContentText("this is great");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.exit(0);
                    }
                });
            }
        });
    }
}






