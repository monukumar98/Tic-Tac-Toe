package Development;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {
	public static int BOARD_SIZE = 3;

	public static enum GameStatus {
		Incomplete, XWins, ZWins, Tie
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossTrun = true;

	public TicTacToe() {
		super.setTitle("TicTacToe");
		super.setSize(800, 800);
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Comic Sans", 1, 150);
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				JButton button = new JButton("");
				buttons[row][col] = button;
				button.setFont(font);
				super.add(button);
				button.addActionListener(this);

			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton ClickeButton = (JButton) e.getSource();
		makeMove(ClickeButton);
		GameStatus gs = this.getGameStatus();
		if (gs == GameStatus.Incomplete) {
			return;
		}
		declareWinner(gs);
	}

	private void makeMove(JButton clickeButton) {
		// TODO Auto-generated method stub
		String btntext = clickeButton.getText();
		if (btntext.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid Move");
		} else {
			if (crossTrun) {
				clickeButton.setText("X");
			} else {
				clickeButton.setText("0");
			}
			crossTrun = !crossTrun;
		}

	}

	private GameStatus getGameStatus() {
		// TODO Auto-generated method stub
		String text1 = "", text2 = "";
		int row = 0, col = 0;
		// text inside rows
		row = 0;
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				col++;
			}
			if (col == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return GameStatus.XWins;
				} else {
					return GameStatus.ZWins;
				}
			}
			row++;
		}
		// text inside the cols

		col = 0;
		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row + 1][col].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				row++;
			}
			if (row == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return GameStatus.XWins;
				} else {
					return GameStatus.ZWins;
				}
			}
			col++;
		}
		// text insde dignol1
		row = 0;
		col = 0;
		while (row < BOARD_SIZE - 1) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row + 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row++;
			col++;
		}
		if (row == BOARD_SIZE - 1) {
			if (text1.equals("x")) {
				return GameStatus.XWins;
			} else {
				return GameStatus.ZWins;
			}
		}
		// text in second diagonal
		row = BOARD_SIZE - 1;
		col = 0;
		while (row > 0) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row - 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row--;
			col++;
		}
		if (row == 0) {
			if (text1.equals("x")) {
				return GameStatus.XWins;
			} else {
				return GameStatus.ZWins;
			}
		}
		String txt = "";
		for (row = 0; row < BOARD_SIZE; row++) {
			for (col = 0; col < BOARD_SIZE; col++) {
				txt = buttons[row][col].getText();
				if (txt.length() == 0) {
					return GameStatus.Incomplete;
				}

			}
		}
		return GameStatus.Tie;
	}

	private void declareWinner(GameStatus gs) {
		// TODO Auto-generated method stub
		if (gs == GameStatus.XWins) {
			JOptionPane.showMessageDialog(this, "X Wins");

		} else if (gs == GameStatus.ZWins) {
			JOptionPane.showMessageDialog(this, "Z Wins");
		} else {
			JOptionPane.showMessageDialog(this, "Tie");
		}

	}

}
