package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

    /*
    * Algorithm:
    - Use Scanner to receive the number n as the number of rows/columns of the chessboard
    * (create the chessboard as an array with the corresponding number of rows and columns,
    * the cell with the default value -1 means empty cell).
    *
    - Use a loop to try to place the first queen in NxN positions (the box on the chessboard
    * that forgets the queen receives the value 1):
        + For each queen placed, call the markProhibitedSquaresOnBoard function to mark cells
        * on the chessboard that cannot be placed with another queen (these cells contain the value 0).
        *
        + Continue to look for the position of an empty cell on the chessboard (the cell has
        * a value of -1) and continue to place the queen (assign the value to that empty cell
        * as 1) and continue to call the markProhibitedSquaresOnBoard function. This process
        * repeats until there are no empty cells left (no cells left on the board with value -1).
        *
    - Use an ArrayList<String> to store the chessboard, use the findBoardWithMostOnes function
    * to print the chessboard with the most number of "1" (queen) appearances.
    * */

public class Main {
    public static void main(String[] args) {
        // Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.print("Value n: ");
        int n = scanner.nextInt();

        // Create ArrayList<String> to save all board variants
        ArrayList<String> mapArrayListBoards = new ArrayList<>();

//        int arrayTry[][] = {{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, 1, -1, -1}};
//        createBoard(arrayTry, 4, 3, 1, mapArrayListBoards).forEach(ele -> System.out.println(ele));
//        createAllBoardVariants(mapArrayListBoards, n).forEach(ele -> System.out.println(ele));
        System.out.println(findBoardWithMostOnes(createAllBoardVariants(mapArrayListBoards, n)));

    }

    // Place Queen  at nxn positions and save the boards
    public static ArrayList<String> createAllBoardVariants(ArrayList<String> mapArrayListBoards, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] boardVariantTemp = create2DArrayAndPlaceQueen(n, i, j);
//                System.out.println(print2DArray(boardVariantTemp));
                createBoard(boardVariantTemp, n, i, j, mapArrayListBoards);
            }
        }
        return mapArrayListBoards;
    }

    // Find final board for each Queen's Position
    public static ArrayList<String> createBoard(int[][] chessBoard, int n, int queenRow, int queenColum, ArrayList<String> arrayListBoardVariants) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chessBoard[i][j] == 1) {
                    chessBoard = iterateQueenOnBoard(chessBoard, i, j, n);
//                        chessBoard = markProhibitedSquaresOnBoard(chessBoard, i, j, n);

                } else if (chessBoard[i][j] == -1) {
                    if (i == queenRow || j == queenColum || i - j == queenRow - queenColum || i + j == queenRow + queenColum) {
                        continue;
                    } else {
                        chessBoard = iterateQueenOnBoard(chessBoard, i, j, n);
//                            chessBoard = markProhibitedSquaresOnBoard(chessBoard, i, j, n);
                    }
                }
            }
        }


        arrayListBoardVariants.add(print2DArray(chessBoard));
        return arrayListBoardVariants;
    }

    // Create array[][] n x n and place a Queen
    public static int[][] create2DArrayAndPlaceQueen(int n, int queenRow, int queenColumn) {
        int[][] array2D = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == queenRow && j == queenColumn) {
                    array2D[i][j] = 1; // Place A Queen
                } else {
                    array2D[i][j] = -1; // -1 means square empty
                }
            }
        }

        return array2D;
    }

    // Place a queen (as number 1) on board
    public static int[][] iterateQueenOnBoard(int[][] chessBoard, int queenRow, int queenColumn, int n) {
        chessBoard[queenRow][queenColumn] = 1;
        return markProhibitedSquaresOnBoard(chessBoard, queenRow, queenColumn, n);
//        return chessBoard;
    }

    // Mark Prohibited Squares On Board
    public static int[][] markProhibitedSquaresOnBoard(int[][] chessBoard, int numberRow, int numberColumn, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chessBoard[i][j] == -1) { // If a square is empty then mark it prohibited
                    if (i == numberRow) { // Mark for Rows
                        if (chessBoard[i][j] != 1) {
                            chessBoard[i][j] = 0; // 0 means prohibited squares
                        }
                    }

                    if (j == numberColumn) { // Mark for Columns
                        if (chessBoard[i][j] != 1) {
                            chessBoard[i][j] = 0; // 0 means prohibited squares
                        }
                    }

                    if (i == numberRow || j == numberColumn || i - j == numberRow - numberColumn || i + j == numberRow + numberColumn) {
                        if (chessBoard[i][j] != 1) {
                            chessBoard[i][j] = 0; // 0 means prohibited squares
                        }
                    }
                }
            }
        }

        return chessBoard;
    }

    // Create array[][] n x n
    public static int[][] create2DArray(int n) {
        int[][] array2D = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array2D[i][j] = -1; // -1 means square empty
            }
        }

        return array2D;
    }

    // Save Array[][] as String
    public static String print2DArray(int[][] array2D) {
        String mapBoard = "";

        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[i].length; j++) {
                mapBoard += array2D[i][j] + "\t";
            }

            mapBoard += "\n";
        }
        mapBoard += "---------------";

        return mapBoard;
    }

    public static int countQueenOccurrences(int[][] chessBoard, int n) {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chessBoard[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static Boolean checkIfSquareEmpty(int[][] chessBoard, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chessBoard[i][j] == -1) { // -1 means square on board is empty
                    return true;
                }
            }
        }

        return false;
    }

    public static String findBoardWithMostOnes(ArrayList<String> boards) {
        Map<String, Integer> countOnesMap = new HashMap<>();

        for (String board : boards) {
            int countOnes = (int) board.chars().filter(ch -> ch == '1').count();
            countOnesMap.put(board, countOnes);
        }

        String maxOnesBoard = boards.get(0);
        int maxOnesCount = countOnesMap.get(maxOnesBoard);

        for (String board : boards) {
            int currentCount = countOnesMap.get(board);
            if (currentCount > maxOnesCount) {
                maxOnesCount = currentCount;
                maxOnesBoard = board;
            }
        }

        return maxOnesBoard;
    }

}