package a3;

import java.io.*;

public class SelectionSort{
    static void selectionSort(int n, int[] arr){
        int small ;
        int idx ;
        for (int i = 0; i < n; i++) {
            small = Integer.MAX_VALUE;
            idx = -1;
            for (int j = i; j < n; j++) {
                if(arr[j] < small){
                    small = arr[j];
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter size of array: ");
        int n = Integer.parseInt(br.readLine().trim());

        String[] line = br.readLine().trim().split(" ");
        int[] arr = new int[n];

        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(line[i]);
        }

        selectionSort(n, arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }System.out.println();

    }
}