package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainPaneController implements Initializable {

	@FXML
	private Label firstCell;

	@FXML
	private Label secondCell;

	@FXML
	private Label thirdCell;

	@FXML
	private Label fourthCell;

	@FXML
	private Label fifthCell;

	@FXML
	private Label sixthCell;

	@FXML
	private Label seventhCell;

	@FXML
	private Label eighthCell;

	@FXML
	private Label ninthCell;

	private final static String PLAYER_X = "X";
	private final static String PLAYER_O = "O";
	private String actualPlayer = null;
	private String[][] area = new String[3][3];

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		firstCell.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> addElement(firstCell, 0, 0));
		secondCell.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> addElement(secondCell, 0, 1));
		thirdCell.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> addElement(thirdCell, 0, 2));
		fourthCell.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> addElement(fourthCell, 1, 0));
		fifthCell.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> addElement(fifthCell, 1, 1));
		sixthCell.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> addElement(sixthCell, 1, 2));
		seventhCell.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> addElement(seventhCell, 2, 0));
		eighthCell.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> addElement(eighthCell, 2, 1));
		ninthCell.addEventFilter(MouseEvent.MOUSE_CLICKED, x -> addElement(ninthCell, 2, 2));
	}

	public void addElement(Label label, int i, int j) {
		if (label.getText().isEmpty()) {
			if (actualPlayer == PLAYER_X || actualPlayer == null) {
				label.setText(PLAYER_X);
				actualPlayer = PLAYER_O;
				area[i][j] = PLAYER_X;
			} else if (actualPlayer == PLAYER_O) {
				label.setText(PLAYER_O);
				actualPlayer = PLAYER_X;
				area[i][j] = PLAYER_O;
			}
		}
		isWin(PLAYER_X);
		isWin(PLAYER_O);
		isTie();
	}

	public void isWin(String player) {
		int count;

		for (int i = 0; i < 3; i++) {
			count = 0;
			for (int j = 0; j < 3; j++) {
				if (area[i][j] == player)
					count++;
			}
			if (count == 3)
				System.out.println("WYGRAL " + player);
		}

		for (int i = 0; i < 3; i++) {
			count = 0;
			for (int j = 0; j < 3; j++) {
				if (area[j][i] == player)
					count++;
			}
			if (count == 3)
				System.out.println("WYGRAL " + player);
		}

		if (area[0][0] == player && area[1][1] == player && area[2][2] == player)
			System.out.println("WYGRAL " + player);

		if (area[2][0] == player && area[1][1] == player && area[0][2] == player)
			System.out.println("WYGRAL " + player);
	}

	public void isTie() {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (area[i][j] == PLAYER_X || area[i][j] == PLAYER_O)
					count++;
			}
		}
		if (count == 9)
			System.out.println("REMIS");
	}
}