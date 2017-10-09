package AI;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.HashMap;

public class AStar {
  
    public static HashMap<Character,Integer> heuristics = new HashMap<Character, Integer>();
    static int N = 5;
    static int edges[][]= {
            {-1, 5, 2, -1, -1},
            {-1, -1, -1, 1, -1},
            {-1, -1, -1, 3, 5},
            {-1, -1, -1, -1, 3},
            {-1, -1, -1, -1, -1},
    };
    public static void main(String args[]) throws InterruptedException {
        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graph graph = new SingleGraph("tutorial 1");
        String styleSheet =

                "node.marked {" +
                        "size: 50px;text-size:18px; fill-color: green; stroke-mode: plain; stroke-color: #999; shadow-mode: gradient-radial; shadow-width: 10px; shadow-color: #999, white; shadow-offset: 3px, -3px;"+
                        "}" +
                        "node{" +
                        "	fill-color: red;size: 50px;text-size:18px;stroke-mode: plain; stroke-color: #999;" +
                        "}"+"edge { stroke-width: 1px;text-size:18px;text-alignment:under; stroke-mode: plain;shape: line; fill-color: black; arrow-size: 10px, 10px; }"+
                        "edge.marked { shape: angle; arrow-shape: none; size: 16px; fill-color: #443;}";
        
        heuristics.put('A',10);
        heuristics.put('B',10);
        heuristics.put('C',5);
        heuristics.put('D',15);
        heuristics.put('E',0);

        Node[] nodes = new Node[N];

        for (int i = 0;i<N;i++) {
            nodes[i] = new Node();
            nodes[i].label = (char) (65 +i);
        }
        for (int i = 0;i<N;i++) {
            for (int j = 0; j <N; j++) {
                if(edges[i][j]>0) {
                     nodes[i].adjList.add(nodes[j]);
                     nodes[i].cost.add(edges[i][j]);
                     nodes[i].goalDist = heuristics.get(nodes[i].label);
                }
            }
        }
        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();
        char finalNodes[] = new char[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if((edges[i][j] != -1) && (edges[i][j] != 0)) {
                    char temp = (char) (65+i);
                    char temp2 = (char) (65+j);
                    graph.addEdge(""+temp+temp2, ""+temp, ""+temp2, true);
                }
            }
        }
        for (org.graphstream.graph.Node node : graph.getNodeSet()) {
            char[] c= node.getId().toCharArray();
            node.addAttribute("ui.label", node.getId()+"("+heuristics.get(c[0])+")");
        }
        for (Edge edge : graph.getEdgeSet()) {
            edge.addAttribute("ui.label", edges[edge.getId().charAt(0)-65][edge.getId().charAt(1)-65]);
        }
        Node n = nodes[0];
        ArrayList<Character> finalList = new ArrayList<>();
        finalList.add(n.label);
        while(n.goalDist != 0) {
            n = n.bestAdj();
           // System.out.print(" -> " +n.label);
            finalList.add(n.label);
        }
        boolean firstTime = true;
        Character prev = null;
        for (Character s: finalList){
            char path = s;
            Thread.sleep(1000);
            if(!firstTime) {
                Edge edge = graph.getEdge("" + prev + path);
                Thread.sleep(1000);
                edge.setAttribute("ui.class", "marked");
            }
            graph.getNode(""+path).setAttribute("ui.class", "marked");
            firstTime = false;
            prev= path;

        }
    }
}

class Node {
    char label;
    ArrayList<Node> adjList = new ArrayList<Node>();
    ArrayList<Integer> cost = new ArrayList<Integer>();
    int goalDist;

    Node bestAdj() {
        int cheap = cheapest(this.adjList, this.cost);
        return adjList.get(cheap);

    }

    int cheapest(ArrayList<Node> node, ArrayList<Integer> costs) {
        int ans = 0;
        for(int i = 1; i < costs.size(); i++) {
            if((node.get(i).goalDist + costs.get(i)) > (node.get(i-1).goalDist + costs.get(i-1))) {
                ans  = i-1;
            } else {
                ans = i;
            }
        }
        return ans;
    }
}