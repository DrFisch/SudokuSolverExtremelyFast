package asudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuFileReader {
    public int[][] readSudokuFromFile(String filePath) {
        int[][] sudokuBoard = new int[9][9];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            int index = 0;
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    sudokuBoard[row][col] = Character.getNumericValue(line.charAt(index));
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sudokuBoard;
    }
    public int[][] readSudokuFromFile2(String filePath){
        int[][] sudokuBoard = new int[9][9];
        String stringi = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            for (String line = reader.readLine(); line != null; line = reader.readLine()){
                
                line.replace(" ","");
                stringi+=line;
            }
            int index = 0;
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    sudokuBoard[row][col] = Character.getNumericValue(stringi.charAt(index));
                    index++;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return sudokuBoard;
    }

    public static void main(String[] args) {
        String filePath = "sudoku.txt"; // Passe den Dateipfad entsprechend an
        SudokuFileReader reader = new SudokuFileReader();
        int[][] sudokuBoard = reader.readSudokuFromFile2(filePath);

        // Ausgabe des eingelesenen Sudoku-Boards
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(sudokuBoard[row][col] + " ");
            }
            System.out.println();
        }
    }
}
