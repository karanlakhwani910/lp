package a3;

import java.io.*;
import java.util.*;

class Node{
    int ver;
    int weight;
    Node(int ver, int weight){
        this.ver = ver;
        this.weight = weight;
    }
}

class Graph{
    ArrayList<ArrayList<Node>> adjList;
    int vertices;

    Graph(int vertices){
        adjList = new ArrayList<>();
        this.vertices = vertices;

        for(int i = 0; i<vertices; i++){
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(int start, int end, int weight){
        adjList.get(start).add(new Node(end, weight));
        adjList.get(end).add(new Node(start, weight));
    }

    void printGraph(){
        for(int i = 0; i<vertices; i++){
            System.out.print(i+" -> [");
            for(Node item : adjList.get(i)){
                System.out.print("("+item.ver+", "+item.weight+")");
            }System.out.print("]");
            System.out.println();
        }
    }
}


public class Prims {


    static void primsAlgo(ArrayList<ArrayList<Node>> aListList, int vertices){
        int[] key = new int[vertices];
        int[] parent = new int[vertices];
        boolean[] mst = new boolean[vertices];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;

        for(int i = 0; i<vertices-1; i++){
            int mini = Integer.MAX_VALUE;
            int vertex = 0;
            for (int j = 0; j < vertices; j++) {
                if(mst[j] == false && key[j] < mini){
                    mini = j;
                    vertex = j;
                }
            }
            mst[vertex] = true;

            for (Node item : aListList.get(vertex)) {
                if(mst[item.ver] == false && item.weight < key[item.ver]){
                    parent[item.ver] = vertex;
                    key[item.ver] = item.weight;
                }
            }
        }

        for (int i = 1; i < vertices; i++) {
            System.out.println(parent[i] + " -> " + i);
        }

    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Create Graph

        System.out.println("Enter number of vertices: ");
        int vertices = Integer.parseInt(br.readLine().trim());
        Graph g = new Graph(vertices);

        System.out.println("Enter number of edges: ");
        int edges = Integer.parseInt(br.readLine().trim());

        String[] line ;
        System.out.println("Enter edges: ");
        for (int i = 0; i < edges; i++) {
            line = br.readLine().trim().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            g.addEdge(start, end, weight);
        }

        g.printGraph();

        primsAlgo(g.adjList, vertices);


        br.close();
    }
}
