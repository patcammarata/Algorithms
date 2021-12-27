import java.util.*;

// A class to demonstrate recursive backtracking using the N-Queens puzzle.
public class NQueens {

	public static void main(String[] args) {
		nQueensAllSolutions(8);
		nQueensSingleSolution(8);
	}

	public static void nQueensSingleSolution(int n) {
		System.out.println(
				" Here's one single solution to " + n + "-Queens:");
		ChessBoard board = new ChessBoard(n);
		oneSolution(board, 0);
	}

	// Uncomment the last line to see a list of valid solutions.
	public static void nQueensAllSolutions(int n) {
		ChessBoard board = new ChessBoard(n);
		List<ChessBoard> allValidSolutions = new ArrayList<>();
		allSolutions(board, allValidSolutions, 0);
		System.out.print("There are " + allValidSolutions.size()
				+ " distinct solutions to the " + n + "-Queens Puzzle.");
		if (n > 8) {
			System.out.println(" Sheesh.");
		}
		// System.out.println(allValidSolutions);
	}

	// This is a backtracking algorithm that enumerates all possible solutions
	// to N-Queens.
	public static void allSolutions(ChessBoard board,
			List<ChessBoard> allValidSolutions, int row) {
		// We found a solution!
		if (row == board.size()) {
			allValidSolutions.add(board.deepCopy());
			return;
		} else {
			for (int col = 0; col < board.size(); col++) {
				// We can drastically improve the runtime by pruning the search
				// space.
				// To prune it, we will only make a choice (i.e. place a queen)
				// if the queen's placement is valid given the current state of
				// the board.
				if (board.safe(row, col)) {
					// Make a choice.
					board.placeQueen(row, col);
					// Explore subsequent choices.
					allSolutions(board, allValidSolutions, row + 1);
					// Undo the choice.
					board.removeQueen(row, col);
				}
			}
			return;
		}
	}

	// Sometimes, we want to stop our search once we find a single acceptable
	// solution.
	// This is a backtracking algorithm that terminates once it finds a valid
	// solution.
	public static boolean oneSolution(ChessBoard board, int row) {
		// Dead end (solution). Time to backtrack.
		if (row == board.size()) {
			System.out.println(board.toString());
			return true;
		} else {
			for (int col = 0; col < board.size(); col++) {
				// Again, using pruning here to only examine valid choices.
				if (board.safe(row, col)) {
					board.placeQueen(row, col);
					// Return true without bothering to explore other choices in
					// the current row.
					// The true value propagates up the decision tree.
					if (oneSolution(board, row + 1) == true) {
						return true;
					}
					board.removeQueen(row, col);
				}
			}
			// Return false only after you have exhausted all choices without
			// luck.
			return false;
		}
	}

}

// Object to simulate a chess board.
class ChessBoard {

	char[][] board;
	int n;

	public ChessBoard(int n) {
		if (n < 0) {
			this.n = 0;
		} else {
			this.n = n;
		}
		this.board = new char[this.n][this.n];
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				board[row][col] = '.';
			}
		}
	}

	public int size() {
		return n;
	}

	public ChessBoard deepCopy() {
		ChessBoard copy = new ChessBoard(n);
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				copy.board[row][col] = board[row][col];
			}
		}
		return copy;
	}

	public void placeQueen(int row, int col) {
		if (!safe(row, col)) {
			System.out.println("(Row " + row + ", " + "Column " + col
					+ ") is not a valid placement.");
		} else {
			board[row][col] = 'Q';
		}
	}

	public void removeQueen(int row, int col) {
		if (row >= 0 && row < n && col >= 0 && col < n) {
			board[row][col] = '.';
		}
	}

	public boolean safe(int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < n
				&& legalPlacement(row, col);
	}

	private boolean legalPlacement(int row, int col) {
		return checkRow(row) && checkCol(col) && checkLeftDiagonal(row, col)
				&& checkRightDiagonal(row, col);
	}

	private boolean checkRow(int row) {
		char[] currentRow = board[row];
		for (int c = 0; c < n; c++) {
			if (currentRow[c] == 'Q') {
				return false;
			}
		}
		return true;
	}

	private boolean checkCol(int col) {
		for (int r = 0; r < n; r++) {
			if (board[r][col] == 'Q') {
				return false;
			}
		}
		return true;
	}

	private boolean checkLeftDiagonal(int row, int col) {
		int r = row;
		int c = col;
		while (r >= 0 && c >= 0) {
			if (board[r][c] == 'Q') {
				return false;
			}
			r--;
			c--;
		}
		r = row;
		c = col;
		while (r < n && c < n) {
			if (board[r][c] == 'Q') {
				return false;
			}
			r++;
			c++;
		}
		return true;
	}

	private boolean checkRightDiagonal(int row, int col) {
		int r = row;
		int c = col;
		while (r >= 0 && c < n) {
			if (board[r][c] == 'Q') {
				return false;
			}
			r--;
			c++;
		}
		r = row;
		c = col;
		while (r < n && c >= 0) {
			if (board[r][c] == 'Q') {
				return false;
			}
			r++;
			c--;
		}
		return true;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		for (int i = 0; i < n; i++) {
			builder.append("[");
			for (int j = 0; j < n - 1; j++) {
				builder.append(board[i][j]);
				builder.append(" ");
			}
			builder.append(board[i][n - 1]);
			builder.append("]\n");
		}
		return builder.toString();
	}

}