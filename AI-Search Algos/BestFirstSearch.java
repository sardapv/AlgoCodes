package AI;

/**
 * Created by pranav on 02/10/17.
 */
import org.graphstream.graph.*;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.*;

public class BestFirstSearch
{
    private PriorityQueue<Vertex> priorityQueue;
    private int numberOfNodes;
    public static HashMap<Character,Integer> heuristics = new HashMap<Character, Integer>();
    public static int N = 6;
    static ArrayList<Character> finalNodes = new ArrayList<>();
    public BestFirstSearch(int numberOfNodes)
    {
        this.numberOfNodes = numberOfNodes;
        this.priorityQueue = new PriorityQueue<Vertex>(this.numberOfNodes,
                new Vertex());
    }

    public void bestFirstSearch(int adjacencyMatrix[][], int[] heuristicvalues,int source)
    {
        int evaluationNode;
        int destinationNode;
        int visited[] = new int [numberOfNodes + 1];
        this.heuristicvalues = heuristicvalues;

        priorityQueue.add(new Vertex(source, this.heuristicvalues[source]));
        visited[source] = 1;

        while (!priorityQueue.isEmpty())
        {
            evaluationNode = getNodeWithMinimumHeuristicValue();
            destinationNode = 1;
            finalNodes.add((char)(64+evaluationNode));
            System.out.print(evaluationNode + "\t");
            while (destinationNode <= numberOfNodes)
            {
                Vertex vertex = new Vertex(destinationNode,this.heuristicvalues[destinationNode]);
                if ((adjacencyMatrix[evaluationNode][destinationNode] != -1
                        && evaluationNode != destinationNode)&& visited[destinationNode] == 0)
                {
                    priorityQueue.add(vertex);
                    visited[destinationNode] = 1;
                }
                destinationNode++;
            }
        }
    }

    private int getNodeWithMinimumHeuristicValue()
    {
        Vertex vertex = priorityQueue.remove();
        return vertex.node;
    }
    static int edges[][] = {
        {-1,-1,-1,-1,-1,-1},
        {-1,0, -1, 1, 1, -1, 1},
        {-1,-1, 0, -1, 1, 1, 1},
        {-1,1, -1, 0, 1, -1, -1},
        {-1,1, 1, 1, 0, 1, -1},
        {-1,-1, 1, -1, 1, 0, -1},
        {-1,1, 1, -1, -1, -1, 0}
    };
    static int number_of_vertices;
    static int source = 0;
    static int heuristicvalues[] = {-1,2, 3, 1, 4, 0,10};
    public static void main(String... arg) throws InterruptedException {

        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        try
        {
            number_of_vertices = 6;
            source = 6;
            BestFirstSearch bestFirstSearch = new BestFirstSearch(number_of_vertices);
            bestFirstSearch.bestFirstSearch(edges, heuristicvalues,source);

        } catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input Format");
        }
        drawGraph();
    }
    public static void drawGraph() throws InterruptedException {
        Graph graph = new SingleGraph("tutorial 1");
        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        String styleSheet =

                "node.marked {" +
                        "size: 50px;text-size:18px; fill-color: green; stroke-mode: plain; stroke-color: #999; shadow-mode: gradient-radial; shadow-width: 10px; shadow-color: #999, white; shadow-offset: 3px, -3px;"+
                        "}" +
                        "node{" +
                        "	fill-color: red;size: 50px;text-size:18px;stroke-mode: plain; stroke-color: #999;" +
                        "}"+"edge { stroke-width: 1px;text-size:18px;text-alignment:under; stroke-mode: plain;shape: line; fill-color: black; arrow-size: 10px, 10px; }"+
                        "edge.marked { shape: angle; arrow-shape: none; size: 16px; fill-color: #443;}";

        for (int i = 1; i <= N;i++)
            heuristics.put((char) (64 +i),heuristicvalues[i]);


        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();


        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if((edges[i][j] != -1) && (edges[i][j] != 0)) {
                    char temp = (char) (64+i);
                    char temp2 = (char) (64+j);
                    graph.addEdge(""+temp+temp2, ""+temp, ""+temp2, false);
                }
            }
        }
        for (org.graphstream.graph.Node node : graph.getNodeSet()) {
            char[] c= node.getId().toCharArray();
            node.addAttribute("ui.label", node.getId()+"("+heuristics.get(c[0])+")");
        }
//        for (Edge edge : graph.getEdgeSet()) {
//            edge.addAttribute("ui.label", edges[edge.getId().charAt(0)-64][edge.getId().charAt(1)-64]);
//        }

        boolean firstTime = true;
        Character prev = null;
        Thread.sleep(1000);
        for (Character s: finalNodes){
            System.out.println(s);
            Thread.sleep(1000);
            graph.getNode(""+s).setAttribute("ui.class", "marked");
            firstTime = false;
            prev= s;

        }
    }
}


class Vertex implements Comparator<Vertex> {
    public int heuristicvalue;
    public int node;

    public Vertex(int node, int heuristicvalue) {
        this.heuristicvalue = heuristicvalue;
        this.node = node;
    }

    public Vertex() {

    }

    @Override
    public int compare(Vertex vertex1, Vertex vertex2) {
        if (vertex1.heuristicvalue < vertex2.heuristicvalue)
            return -1;
        if (vertex1.heuristicvalue > vertex2.heuristicvalue)
            return 1;
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex) {
            Vertex node = (Vertex) obj;
            if (this.node == node.node) {
                return true;
            }
        }
        return false;
    }
}