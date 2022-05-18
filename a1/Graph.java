package a1;

import java.util.*;
import java.io.*;

public class Graph{
    ArrayList<ArrayList<Integer>> adjList;
    int vertices;
    Graph(int vertices){
        this.vertices = vertices;
        adjList = new ArrayList<>();

        for(int i = 0; i<= vertices; i++){
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(int start, int end){
        adjList.get(start).add(end);
        adjList.get(end).add(start);
    }

    void bfsHelper(Queue<Integer> queue, boolean[] visited){
        if(queue.isEmpty()){
            return;
        }
        int top = queue.poll();
        System.out.print(top+" ");
        for(int item : adjList.get(top)){
            if(!visited[item]) {
                queue.add(item);
                visited[item] = true;
            }
        }
        bfsHelper(queue, visited);
    }

    void bfsRecursive(int src){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        boolean[] visited = new boolean[vertices+1];
        visited[src] = true;
        bfsHelper(queue, visited);
    }

    void printGraph(){
        for(int i = 0; i <= vertices; i++){
            System.out.println(i+" -> "+adjList.get(i));
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Enter number of vertices: ");
        int vertices = Integer.parseInt(br.readLine().trim());

        Graph g = new Graph(vertices);

        System.out.println("Enter number of edges: ");
        int edges = Integer.parseInt(br.readLine().trim());

        System.out.println("Enter edges: ");
        String[] line;
        for(int i = 0; i<edges; i++){
            line = br.readLine().trim().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            g.addEdge(start, end);
        }
        g.printGraph();

        System.out.println("BFS enter start vertex: ");
        int startvertex = Integer.parseInt(br.readLine().trim());
        g.bfsRecursive(startvertex);

        br.close();
    }
}