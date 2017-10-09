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

public class SteepestAscentHC {
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
    public HashMap<Character,Integer> heuristics = new HashMap<Character, Integer>();

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
        SteepestAscentHC sahc = new SteepestAscentHC();
        sahc.heuristics.put('A',10);
        sahc.heuristics.put('B',4);
        sahc.heuristics.put('C',5);
        sahc.heuristics.put('D',3);
        sahc.heuristics.put('E',0);

        sahc.graph.addAttribute("ui.stylesheet", styleSheet);
        sahc.graph.setAutoCreate(true);
        sahc.graph.setStrict(false);
        sahc.graph.display();
        char finalNodes[] = new char[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if((sahc.edges[i][j] != -1) && (sahc.edges[i][j] != 0)) {
                    char temp = (char) (65+i);
                    char temp2 = (char) (65+j);
                    sahc.graph.addEdge(""+temp+temp2, ""+temp, ""+temp2, true);
                }
            }
        }
        for (Node node : sahc.graph.getNodeSet()) {
            char[] c= node.getId().toCharArray();
            node.addAttribute("ui.label", node.getId()+"("+sahc.heuristics.get(c[0])+")");
        }
        for (Edge edge : sahc.graph.getEdgeSet()) {
            edge.addAttribute("ui.label", sahc.edges[edge.getId().charAt(0)-65][edge.getId().charAt(1)-65]);
        }

        boolean firstTime = true;
        for (Object s: sahc.sahc()){
            int p  = (Integer)s;
            char path = (char) p;
            System.out.print(path);
            Thread.sleep(1000);
            if(!firstTime) {
                Edge edge = sahc.graph.getEdge("" + sahc.prev + path);
                Thread.sleep(1000);
                edge.setAttribute("ui.class", "marked");
            }
            sahc.graph.getNode(""+path).setAttribute("ui.class", "marked");
            firstTime = false;
            sahc.prev= path;

        }




    }
    public int getSmallestNeighbours(int nextElem, int arrOfArr[][], ArrayList<Integer>  visited, int  heuristic[]) {
        int i = nextElem - 'A';
        int x = 0;
        int p = 0;
        Arrays.sort(heuristic);
        int smallest = heuristic[heuristic.length-1];
        for (int j  = 0; j < arrOfArr.length;j++) {
            if (arrOfArr[i][j] > 0) {
                if (smallest > heuristic[x]) {
                    smallest = heuristic[x];
                }
                p = x;
            }
            x += 1;
        }
       char  data = Character.valueOf((char) (p + 'A'));
        if(!visited.contains(data))
        return data;
      return 0;
    }

    public Object[] sahc() {
        char visited[];
        int heuristicVals[] = {10,4,5,3,0};

        int goal = 0;
        int start = 'A';
        for(char key:heuristics.keySet()){
            if(heuristics.get(key) == 0)
                goal = key;
        }

        ArrayList<Integer>finalPath = new ArrayList<Integer>();
        finalPath.add(start);
        int curr = start;

        while(!finalPath.contains(goal)){
            curr = getSmallestNeighbours(curr, edges, finalPath, heuristicVals);
            if (curr == 0)
                break;
            finalPath.add(curr);
        }
        //System.out.println(finalPath);
        return finalPath.toArray();
    }
}
