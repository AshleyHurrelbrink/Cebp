package home.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public Button home_button, orderDesk_button;
    public Pane home_page, orderDesk_page;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_page.setVisible(true);
        orderDesk_page.setVisible(false);
    }

    public void onHomeButtonClick(ActionEvent event) throws IOException
    {
        home_page.setVisible(true);
        orderDesk_page.setVisible(false);
    }

    public void onOrderDeskButtonClick(ActionEvent event) throws IOException
    {
        home_page.setVisible(false);
        orderDesk_page.setVisible(true);
    }

}

