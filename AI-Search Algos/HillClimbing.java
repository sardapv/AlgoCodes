package AI;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by pranav on 01/10/17.
 */
//import json;

public class HillClimbing {
    static int N = 5;
    int edges[][]= {
            {0, 4, 3, -1, -1},
            {-1, 5, 0, 6, 7},
            {-1, 0, -1, 1, 2},
            {-1, -1, -1, 0, 1},
            {-1, -1, -1, -1, 0},
    };
    Graph graph = new SingleGraph("tutorial 1");
    char prev;
    public HashMap<Character,Integer> heuristic = new HashMap<Character, Integer>();

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        String styleSheet =

                "node.marked {" +
                        "size: 50px;text-size:18px; fill-color: green; stroke-mode: plain; stroke-color: #999; shadow-mode: gradient-radial; shadow-width: 10px; shadow-color: #999, white; shadow-offset: 3px, -3px;"+
                        "}" +
                        "node{" +
                        "	fill-color: red;size: 50px;text-size:18px;stroke-mode: plain; stroke-color: #999;" +
                        "}"+"edge { stroke-width: 1px;text-size:18px;text-alignment:under; stroke-mode: plain;shape: line; fill-color: black; arrow-size: 10px, 10px; }"+
                        "edge.marked { shape: angle; arrow-shape: none; size: 16px; fill-color: #443;}";
        HillClimbing hc = new HillClimbing();
        hc.heuristic.put('A',10);
        hc.heuristic.put('B',5);
        hc.heuristic.put('C',4);
        hc.heuristic.put('D',3);
        hc.heuristic.put('E',0);

        hc.graph.addAttribute("ui.stylesheet", styleSheet);
        hc.graph.setAutoCreate(true);
        hc.graph.setStrict(false);
        hc.graph.display();
        char finalNodes[] = new char[N];
        hc.hill_climb('A',hc.edges,N,hc.heuristic);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if((hc.edges[i][j] != -1) && (hc.edges[i][j] != 0)) {
                    char temp = (char) (65+i);
                    char temp2 = (char) (65+j);
                    hc.graph.addEdge(""+temp+temp2, ""+temp, ""+temp2, true);
                }
            }
        }
        for (Node node : hc.graph.getNodeSet()) {
            char[] c= node.getId().toCharArray();
            node.addAttribute("ui.label", node.getId()+"("+hc.heuristic.get(c[0])+")");
        }
        for (Edge edge : hc.graph.getEdgeSet()) {
            edge.addAttribute("ui.label", hc.edges[edge.getId().charAt(0)-65][edge.getId().charAt(1)-65]);
        }

        boolean firstTime = true;
        for (Object s: finalPath){
            char path = (char) s;
            System.out.print(path);
            Thread.sleep(1000);
            if(!firstTime) {
                Edge edge = hc.graph.getEdge("" + hc.prev + path);
                Thread.sleep(1000);
                edge.setAttribute("ui.class", "marked");
            }
            hc.graph.getNode(""+path).setAttribute("ui.class", "marked");
            firstTime = false;
            hc.prev= path;

        }
    }
    static ArrayList<Character>finalPath = new ArrayList<>();
    public void hill_climb(Character start, int edges[][], int N, HashMap<Character,Integer>heuristic){
    Character current_node = start;
        for (int i = 0; i < N ; i++)
            for (int j = 0; j < N; j++) {
                int temp = edges[i][j];
                if(!finalPath.contains(current_node))
                    finalPath.add(current_node);
                if(temp !=0 && temp != -1 && heuristic.get((char)(65+j)) < heuristic.get(current_node)){
                    current_node = (char)(j+'A');
                    break;
                }
            }
    }
}
