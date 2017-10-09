package AI;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by pranav on 01/10/17.
 */
public class GraphExplore {
    public static void main(String args[]) {
        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new GraphExplore();
    }
    Graph graph = new SingleGraph("tutorial 1");
    public GraphExplore() {


        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        graph.addEdge("AI", "A", "I",true);
        graph.addEdge("AC", "A", "C",true);
        graph.addEdge("CF", "C", "F",true);
        graph.addEdge("IB", "I", "B",true);
        graph.addEdge("ID", "I", "D",true);
        graph.addEdge("CD", "C", "D",true);
        graph.addEdge("DF", "D", "F",true);
        graph.addEdge("BD", "B", "D",true);

//        graph.addEdge("01", "0", "1",true);
//        graph.addEdge("02", "0", "2",true);
//        graph.addEdge("12", "1", "2",true);
//        graph.addEdge("20", "2", "0",true);
//        graph.addEdge("23", "2", "3",true);
//        graph.addEdge("33", "3", "3",true);
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        explore(graph.getNode("A"));
    }

    public void explore(Node source) {
        //Iterator<? extends Node> k = source.getBreadthFirstIterator();
        Iterator<? extends Node> k = source.getDepthFirstIterator();
        Collection<Edge> edgeSet = source.getEdgeSet();
        Iterator<? extends Edge> ke = null;
        Node prev = null;
        sleep();
        boolean firsttime = true;
            while (k.hasNext()) {
            Node next = k.next();
            if(firsttime == false){
                //System.out.println(edgeSet);
                Edge edge = graph.getEdge(prev.getId()+next.getId());
                if(edge == null) {
                    edgeSet = next.getEdgeSet();
                    ke = edgeSet.iterator();
                    edge = graph.getEdge(ke.next().toString().substring(3,7).replace("->",""));
                }
                edge.setAttribute("ui.class", "marked");
            }
            next.setAttribute("ui.class", "marked");
            sleep();
            prev = next;
            firsttime = false;
        }
    }

    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }
    protected String styleSheet =

            "node.marked {" +
                    "size: 45px;text-size:18px; fill-color: green; stroke-mode: plain; stroke-color: #999; shadow-mode: gradient-radial; shadow-width: 10px; shadow-color: #999, white; shadow-offset: 3px, -3px;"+
                    "}" +
                    "node{" +
                    "	fill-color: red;size: 45px;text-size:18px;stroke-mode: plain; stroke-color: #999;" +
                    "}"+"edge { stroke-width: 1px; stroke-mode: plain;shape: line; fill-color: black; arrow-size: 10px, 10px; }"+
            "edge.marked { shape: angle; arrow-shape: none; size: 16px; fill-color: #443;}";
}