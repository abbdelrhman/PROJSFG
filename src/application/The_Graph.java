package application;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

public class The_Graph {

    private Graph graph;
    private SpriteManager sman;
    private Sprite s;
    public The_Graph() {
        graph = new SingleGraph("SFG");



    }
    public void createGraphNodes(int num_of_nodes) {

        for(int counter =1 ; counter <= num_of_nodes ; counter++) {
            int X = counter ;
            int Y = counter%2;
            Node n =graph.addNode("X"+counter);
            n.setAttribute("xyz", X, Y, 0);
            n.addAttribute("ui.style", "text-alignment: under;");
            n.addAttribute("ui.label", "X"+counter);
            n.addAttribute("ui.label", "X"+counter);

        }
    }

    public void createGraphBranches(float[][] graphArray,int num_of_nodes) {
        for(int row =0 ; row < num_of_nodes;row++) {
            for(int col =0 ; col <num_of_nodes ; col ++) {
                if(graphArray[row][col] != 0) {
                    Edge e = graph.addEdge("X"+(row+1)+"X"+(col+1), "X"+(row+1), "X"+(col+1),true);
                    e.addAttribute("ui.label", graphArray[row][col]);
                    e.addAttribute("ui.style", "text-alignment: under;");
                    e.addAttribute("ui.style", "shape: cubic-curve;");

                }
            }
        }

    }

    public void ShowGraph() {
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.display().disableAutoLayout();
    }



}
