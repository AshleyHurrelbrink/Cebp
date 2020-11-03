package home.controllers;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import main.WoodWorker;
import other.ConfigReader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public Button home_button, orderDesk_button;
    public Pane home_page, orderDesk_page1, orderDesk_page2;
    public Slider nrDesk_slider;
    @FXML
    public Label wood_value;
    WoodWorker mng;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_page.setVisible(true);
        orderDesk_page1.setVisible(false);
        orderDesk_page2.setVisible(false);
    }


    public void onHomeButtonClick(ActionEvent event) throws IOException
    {
        home_page.setVisible(true);
        orderDesk_page1.setVisible(false);
        orderDesk_page2.setVisible(false);
    }

    public void onOrderDeskButtonClick(ActionEvent event) throws IOException
    {
        home_page.setVisible(false);
        orderDesk_page1.setVisible(true);
        orderDesk_page2.setVisible(false);
    }

    public void onSubmitNrDeskButtonClick(ActionEvent event) throws IOException
    {
        home_page.setVisible(false);
        orderDesk_page1.setVisible(false);
        orderDesk_page2.setVisible(true);
        mng = new WoodWorker();
        
        //TO DO: wood_value should update value
        wood_value.setText(String.valueOf(mng.wood));
        mng.manageSteps((int)nrDesk_slider.getValue());
    }

}

