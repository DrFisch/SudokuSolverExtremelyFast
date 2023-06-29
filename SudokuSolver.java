package asudoku;

public class SudokuSolver {
    private static final int SIZE = 9;
    private static final int EMPTY_CELL = 0;
    
    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = EMPTY_CELL;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValidMove(int[][] board, int row, int col, int num) {
        // Prüfen, ob die Zahl in der Zeile gültig ist
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        
        // Prüfen, ob die Zahl in der Spalte gültig ist
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        
        // Prüfen, ob die Zahl im 3x3-Unterquadrat gültig ist
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String filePath = "sudoku.txt"; // Passe den Dateipfad entsprechend an
        SudokuFileReader reader = new SudokuFileReader();
        int[][] board = reader.readSudokuFromFile2(filePath);
        
        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            System.out.println("Gelöstes Sudoku:");
            solver.printBoard(board);
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
        long endTime = System.nanoTime();
        long diff = endTime-startTime;
        System.out.println(diff/1000000 + " millisekunden");
    }
}
