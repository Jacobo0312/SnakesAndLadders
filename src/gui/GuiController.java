package gui;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Cell;
import model.GameTable;

public class GuiController {

	private GameTable game;

	@FXML
	private VBox mainPane;

	@FXML
	private TextField columnText;

	@FXML
	private TextField rowText;

	@FXML
	private TextField snakeText;

	@FXML
	private TextField ladderText;

	@FXML
	private TextField playerText;

	@FXML
	private GridPane gPane;

	@FXML
	private BorderPane borderPane;

	private GridPane grid;
	
	private BorderPane bp;

	@FXML
	private VBox gridBox;

	private Label dices;

	@FXML
	private ListView<String> listScores;
	
	private String result;
	
	public GuiController(GameTable game) {
		this.game = game;
	}

	public void loadMenu() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		fxmlLoader.setController(this);

		Parent menu = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(menu);

		Stage stage = (Stage) mainPane.getScene().getWindow();
		borderPane.prefHeightProperty().bind(stage.heightProperty());
		borderPane.prefWidthProperty().bind(stage.widthProperty());
	}

	@FXML
	public void prepareGame() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Prepare.fxml"));
		fxmlLoader.setController(this);

		Parent prepare = fxmlLoader.load();
		borderPane.setCenter(prepare);
	}

	@FXML
	public void playGame() {
		int cols = Integer.parseInt(columnText.getText());
		int rows = Integer.parseInt(rowText.getText());
		int snakes = Integer.parseInt(snakeText.getText());
		int ladders = Integer.parseInt(ladderText.getText());
		String players = playerText.getText();

		try {
			chargeGame(cols, rows, snakes, ladders, players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addToGrid(int val) {
		if (val <= 0) {

		} else {
			Cell cell = game.searchCell(val, game.getFirst());
			GridPane.setValignment(cell.getLabel(), VPos.CENTER);
			GridPane.setHalignment(cell.getLabel(), HPos.CENTER);
			grid.getChildren().add(cell.getLabel());
			addToGrid(val - 1);
		}
	}

	private boolean verifySize(int rows, int cols, int snakes, int ladders) {
		int val = rows * cols;
		int elements = (snakes + ladders) * 2;
		if (val > elements + rows + cols) {
			return true;
		}
		System.out.println("\nDIMENSIONES NO VALIDAS\n");
		return false;
	}

	public void chargeGame(int c, int r, int s, int l, String pls) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
		fxmlLoader.setController(this);

		Parent newGame = fxmlLoader.load();
		borderPane.setCenter(newGame);
		if (verifySize(r, c, s, l)) {
			game = new GameTable(r, c, pls, l, s, 0);
			bp = new BorderPane();
			Button throwDice = new Button();
			dices = new Label("");
			dices.setStyle("-fx-text-fill: #00ccff; -fx-font-size: 20px; -fx-padding: 20");
			throwDice.setText("Lanza los dados!");
			// throwDice.setStyle("-fx-padding: 20");
			throwDice.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					// Lanza los dados!
					System.out.println("Lanzó!");
					play();
					//simul();
				}
			});
			grid = new GridPane();
			grid.setMinHeight(700);
			grid.setMinWidth(900);
			addToGrid(game.getVal());
			System.out.println("JUGADORES:\n" + game.getPlayerList().toString());
			System.out.println(game.toString2());
			System.out.println(game);
			grid.setStyle("-fx-background-color:  #00ccff; -fx-grid-lines-visible: true;");
			game.addVal(game.getFirst(), 1);
			BorderPane.setAlignment(dices, Pos.CENTER);
			BorderPane.setMargin(dices, new Insets(12, 12, 12, 12)); // optional
			bp.setTop(dices);
			bp.setCenter(grid);
			BorderPane.setAlignment(throwDice, Pos.CENTER);
			BorderPane.setMargin(throwDice, new Insets(12, 12, 12, 12)); // optional
			bp.setBottom(throwDice);
			gridBox.getChildren().setAll(bp);
			VBox.setVgrow(bp, Priority.ALWAYS);
			sizeColumns(c, 0);
			sizeRows(r, 0);
			fixFirst();
			//GridPane copy = grid;
			VBox buttonBox = createBox();
			bp.setRight(buttonBox);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("Dimesiones invalidas");

			alert.showAndWait();
			loadMenu();

		}
	}
	
	public VBox createBox() {
		VBox vBox = new VBox();
		Button simul = new Button();
		simul.setText("Simul");
		simul.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				simul();
				
			}
		});
		Button num = new Button();
		num.setText("Num");
		num.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				//
				num();
				bp.setCenter(grid);
			}
		});
		Button menu = new Button();
		menu.setText("Menu");
		menu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				try {
					loadMenu();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		vBox.getChildren().addAll(simul, num, menu);
		vBox.setSpacing(25);
		return vBox;
	}

	public void play() {
		System.out.print("Presione enter para lanzar los dados: ");

		if (!game.isPlayerWon()) {
			dices.setText(game.move());
			System.out.println(dices.getText());
			System.out.println(game);
			
			if (game.isPlayerWon()){
				winner();
			}
		}
	}

	private void winner() {
		/*System.out.println("El jugador " + game.getTurn().getToken() + " ha ganado el juego, con "
				+ game.getTurn().getMoves() + " movimientos.\n");
		System.out.println("Ingrese el nombre o nickname del jugador");*/

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Ganador!");
		dialog.setHeaderText("Sos un campeón!");
		dialog.setContentText("Por favor, ingresa tu nombre:");
		/*Optional<String> result = dialog.showAndWait();
		System.out.println(dialog.getResult());*/
		dialog.show();
		/*String result = "";*/
		/*dialog.setOnHidden(evt -> this.result = dialog.getResult().toString());*/
		result = "";
		dialog.setOnCloseRequest(evt ->  {
			String line;
			String resultIn;
			resultIn = dialog.getResult();
			System.out.println(resultIn + " Esta cinta!");
			setValues(resultIn);
			System.out.println(result + "RESULTADO!");
			if (result != null && !result.equals("")) {				
				if (!result.isEmpty()/*.isPresent()*/) {
					line = result/*.get()*/;
					try {
						game.saveWinners(game.getTurn(), line);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("ERROR!");
				alert.setContentText("No has insertado el nombre. Se cancela tu puntaje");
				alert.showAndWait();
			}
			try {
				loadMenu();
			} catch (IOException e) {
				e.printStackTrace();
			}
			});

		// Volver a cargar el inicio

	}
	
	public void setValues(String value) {
		result = value;
	}

	public void num() {
		Dialog<Node> dialog = new Dialog<Node>();
		dialog.setTitle("Grilla!");
		dialog.setHeaderText("Estado actual del juego");
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
		closeButton.managedProperty().bind(closeButton.visibleProperty());
		closeButton.setVisible(false);
		game.setNum(game.getVal());
		dialog.getDialogPane().setContent(grid);
		dialog.showAndWait();
		game.restoreNum(game.getVal());
	}


	public void simul() {

		System.out.print("Simulador");

		/*
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		if (!game.isPlayerWon()) {
			dices.setText(game.move());
			System.out.println(dices.getText());
			System.out.println(game);
			
			/*if (game.isPlayerWon()) {
				game.move();
			}*/

			Timeline contador = new Timeline(
					//new KeyFrame(Duration.seconds(2), acciÃ³n -> simul()));
					new KeyFrame(Duration.seconds(2), action -> simul()));
			contador.play();
			
		//simul();
			if (game.isPlayerWon()) {
				contador.stop();
				winner();
			}
	}

	}

	public void fixFirst() {
		if (game.getRows() % 2 == 0) {
			game.getFirst().setVal(game.getVal());
		} else {
			game.getFirst().setVal(game.getVal() - (game.getCols() - 1));
		}
	}

	public void sizeColumns(int cols, int alt) {
		if (alt >= cols) {

		} else {
			ColumnConstraints cc = new ColumnConstraints();
			//cc.setPrefWidth(100);
			cc.setPercentWidth(100d / cols);
			grid.getColumnConstraints().add(cc);
			sizeColumns(cols, alt + 1);
		}
	}

	public void sizeRows(int rows, int alt) {
		if (alt >= rows) {

		} else {
			RowConstraints rc = new RowConstraints();
			rc.setPrefHeight(200);
			//rc.setPercentHeight(alt);
			grid.getRowConstraints().add(rc);
			sizeRows(rows, alt + 1);
		}
	}

	@FXML
	public EventHandler<ActionEvent> changeVal() {
		return null;

	}

	@FXML
	public void showScores() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableScores.fxml"));
		fxmlLoader.setController(this);

		try {
			Parent scores = fxmlLoader.load();
			borderPane.setCenter(scores);
			initializeTableView();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * System.out.println("Entrando a Scores!");
		 * System.out.println(game.returnClassScores());
		 */
	}

	@FXML
	public void initializeTableView() {

		listScores.getItems().add(game.returnClassScores());

	}

	@FXML
	public void updateLabels() {

	}

	@FXML
	public void exitProgram() {
		Platform.exit();
	}

}