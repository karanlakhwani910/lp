package a4;

import java.io.*;
import java.util.*;

public class NQueensBranch {
    static ArrayList<ArrayList<String>> res ;

    static ArrayList<String> construct(char[][] board, int n){
        ArrayList<String> mat = new ArrayList<>();

        for(int i = 0; i<n; i++){
            mat.add(new String(board[i]));
        }

        return mat;
    }

    static void helper(int col, char[][] board, int[] leftRow, int[] upperDiag, int[] lowerDiag, int n){
        if(col == n){
            res.add(construct(board, n));
            return;
        }
        for(int row = 0; row<n; row++){
            if(leftRow[row] == 0 && upperDiag[row+col]==0 && lowerDiag[col-row+n-1] == 0){
                leftRow[row] = 1;
                upperDiag[row+col] = 1;
                lowerDiag[col-row+n-1] = 1;
                board[row][col] = '1';
                helper(col+1, board, leftRow, upperDiag, lowerDiag, n);
                leftRow[row] = 0;
                upperDiag[row+col] = 0;
                lowerDiag[col-row+n-1] = 0;
                board[row][col] = '0';
            }
        }
    }

    static void nQueens(int n){
        res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] item : board) {
            Arrays.fill(item, '0');
        }
        int[] leftRow = new int[n];
        int[] upperDiag = new int[2*n-1];
        int[] lowerDiag = new int[2*n-1];
        helper(0, board, leftRow, upperDiag, lowerDiag, n);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        nQueens(n);

        for (ArrayList<String> item : res) {
            for(String i : item){
                System.out.println(i);
            }
            System.out.println();
        }

        br.close();
    }
}
