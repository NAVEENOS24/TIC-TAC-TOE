import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    private final JFrame frame;
    private final JPanel panel;
    private final JButton[] buttons;
    private char currentPlayer;
    private final char[][] board;
    
    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        panel = new JPanel();
        buttons = new JButton[9];
        currentPlayer = 'X';
        board = new char[3][3];

        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        initializeBoard();
        currentPlayer = 'X';
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        int index = -1;
        for (int i = 0; i < 9; i++) {
            if (buttonClicked == buttons[i]) {
                index = i;
                break;
            }
        }

        int row = index / 3;
        int col = index % 3;

        if (board[row][col] == '-') {
            board[row][col] = currentPlayer;
            buttonClicked.setText(String.valueOf(currentPlayer));
            buttonClicked.setEnabled(false);

            if (isWinner()) {
                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                resetBoard();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(frame, "The game is a draw!");
                resetBoard();
            } else {
                switchPlayer();
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinner() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }

    public JButton[] getButtons() {
        return buttons;
    }
}
