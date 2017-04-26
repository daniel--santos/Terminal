package terminal.hw;

import java.util.*;
import java.lang.*;
import java.io.*;

public class FloydWarshall {

    final static int INF = 99999, V = 5;

    void floydWarshall(int graph[][])
    {
        int dist[][] = new int[V][V];
        int i, j, k;

        /* Initialize the solution matrix same as input graph matrix.
           Or we can say the initial values of shortest distances
           are based on shortest paths considering no intermediate
           vertex. */
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

        /* Add all vertices one by one to the set of intermediate
           vertices.
          ---> Before start of a iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of a iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    String s1 = "d[" + i + "][" + j + "]";
                    String s2 = "d[" + i + "][" + k + "]";
                    String s3 = "d[" + k + "][" + j + "]";
                    StringBuilder saida = new StringBuilder();
                    saida.append(s1 + " > " + s2 + " + " + s3 + "  = ");
                    saida.append(dist[i][j] == INF ? "INF" : dist[i][j]);
                    saida.append(" > ");
                    saida.append(dist[i][k] == INF ? "INF" : dist[i][k]);
                    saida.append(" + ");
                    saida.append(dist[k][j] == INF ? "INF" : dist[k][j]);


                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        saida.append(" Troca ");
                    }

                    System.out.println(saida.toString());
                }
            }
        }

        // Print the shortest distance matrix
        printSolution(dist);
    }

    void printSolution(int dist[][])
    {
        System.out.println("Following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }

    // Driver program to test above function
    public static void main (String[] args)
    {
        /* Let us create the following weighted graph
           10
        (0)------->(3)
        |         /|\
        5 |          |
        |          | 1
        \|/         |
        (1)------->(2)
           3           */
        int graph[][] = { {0, 8, 3, 5, INF},
                          {8, 0, 2, INF, 5},
                          {INF, 1, 0, 3, 3},
                          {6, INF, INF, 0, 7},
                          {INF, 5, INF, INF, 0}
        };
        FloydWarshall a = new FloydWarshall();

        // Print the solution
        a.floydWarshall(graph);
    }
}
