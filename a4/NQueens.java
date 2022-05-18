package a4;
import java.io.*;
import java.util.*;

public class NQueens {
    static ArrayList<ArrayList<String>> res;

    static ArrayList<String> construct(char[][] board, int n){
        ArrayList<String> mat = new ArrayList<>();

        for(int i = 0; i<n; i++){
            mat.add(new String(board[i]));
        }

        return mat;
    }
    static boolean validate(char[][] board, int row, int col) {
        int duprow = row;
        int dupcol = col;
        while (row >= 0 && col >= 0) {
            if (board[row][col] == '1') return false;
            row--;
            col--;
        }

        row = duprow;
        col = dupcol;
        while (col >= 0) {
            if (board[row][col] == '1') return false;
            col--;
        }

        row = duprow;
        col = dupcol;
        while (col >= 0 && row < board.length) {
            if (board[row][col] == '1') return false;
            col--;
            row++;
        }
        return true;
    }

    static void helper(int col, char[][] board, int n){
        if(col == n){
            res.add(construct(board, n));
            return ;
        }


        for(int row = 0; row<n; row++){
            if(validate(board, row, col)){
                board[row][col] = '1';
                helper(col+1, board, n);
                board[row][col] = '0';
            }
        }
    }

    static void nQueens(int n){
        res = new ArrayList<>();
        char[][] board = new char[n][n];
        for(int i = 0; i<n; i++){
            Arrays.fill(board[i], '0');
        }
        helper(0, board, n);
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
