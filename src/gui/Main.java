package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GameTable;

public class Main extends Application{
	
    private GameTable game;
    private GuiController gC;
    private int run;

    public Main() {
    	run = 0;
        game = new GameTable(2, 2, "", 0, 0, run);
        gC = new GuiController(game);
    }

    public static void main(String[] args) throws Exception {
    	launch(args);
    }


	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPane.fxml"));
		fxmlLoader.setController(gC);
		
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("SNAKE & LADDERS");
		primaryStage.show();
		primaryStage.setMaximized(true);
		
		gC.loadMenu();
	}


}
