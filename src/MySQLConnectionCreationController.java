import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro8.JMetro;

public class MySQLConnectionCreationController {

    @FXML
    private JFXTextField hostField;

    @FXML
    private JFXTextField portField;

    @FXML
    private JFXTextField databaseField;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField pwdField;

    @FXML
    private JFXButton finishBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    void backBtnClicked(ActionEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("SelectConnectionType.fxml"));
        Scene connectionForm = new Scene(parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(connectionForm);
        window.show();
    }

    @FXML
    void finishBtnClicked(ActionEvent event) {
    	DatabaseType.setDBType("mysql");

    	String connectionStr = "jdbc:mysql://" + hostField.getText() + ":" + portField.getText() + "/"
    			+ databaseField.getText() + "," + usernameField.getText()+ "," + pwdField.getText();

    	System.out.println(connectionStr);

    	try {
			DBClientManager.getClient().establishConnection(connectionStr, "mysql");
		} catch (ClassNotFoundException | SQLException e1) {
			System.err.println("Failed to establish connection!");
			e1.printStackTrace();
			System.exit(0);
		}


    	Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("MainApplicationView.fxml"));
		}
		catch (IOException e) {
			System.err.println("MainApplicationView.fxml could not be found!");
			e.printStackTrace();
			System.exit(0);
		}
		DatabaseType.setDBType("mysql");
    	Scene nextForm = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextForm);
        window.show();

    }
}
