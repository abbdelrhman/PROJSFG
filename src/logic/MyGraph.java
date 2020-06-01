package logic;
import java.util.ArrayList;



public class MyGraph {

    private float[][] gain;

    private Paths path = new Paths();

    public Paths getPath() {
        return path;

    }
    private Loops loop = new Loops();

    public Loops getLoop() {
        return loop;

    }
    private Non_Touched_loops ntl = new Non_Touched_loops();

    public Non_Touched_loops getNon_Touched_loops() {
        return ntl;

    }
    private Calc_Delta delta = new Calc_Delta();

    public Calc_Delta getDelta() {
        return delta;

    }

    private Calc_Deltas deltas = new Calc_Deltas();

    public Calc_Deltas getDeltas() {
        return deltas;

    }

    public MyGraph(int nV , float[][] graph) {
        //this.graph = new boolean[nV][nV];
        this.gain = graph;
    }

    /**public boolean[][] getGraph() {
     return this.graph;
     }*/

    public float[][] get_gain() {
        return this.gain;
    }

    /**public void addEdge(int f, int t, int gain) {
     if (gain != 0) {
     this.graph[f][t] = true;
     this.gain[f][t] = gain;
     }
     }*/

    public void setLoops_and_Pathes() {

        this.path.madePathes(this.gain.length, this.gain);
        this.loop.madeLoops(this.gain.length, this.gain);
        this.ntl.madeNonTouchedLoops(loop.getLoops(), loop.get_loops_gain());
        this.delta.setDelta(ntl.getLoopsGain());
        this.deltas.setDeltas(this.path, this.ntl);
    }

    /**public void printPaths() {
     ArrayList<ArrayList<Integer>> list = path.getPaths();
     for (int i = 0; i < list.size(); i++) {
     for (int j = 0; j < list.get(i).size(); j++) {
     System.out.print(list.get(i).get(j) + "=>");
     }
     System.out.print(path.get_paths_gain().get(i));
     System.out.println();
     }
     System.out.println("-------------------------------------------------");
     }*/

    /**public void printloops() {
     ArrayList<ArrayList<Integer>> list = loop.getLoops();
     for (int i = 0; i < list.size(); i++) {
     for (int j = 0; j < list.get(i).size(); j++) {
     System.out.print(list.get(i).get(j) + "=>");
     }
     System.out.print(loop.get_loops_gain().get(i));
     System.out.println();
     }
     System.out.println("-------------------------------------------------");
     }*/

    /**public void printNonTouchedLoops() {
     ArrayList<ArrayList<ArrayList<Integer>>> nt = ntl.getNonTouched();
     for (int i = 0; i < nt.size(); i++) {
     for (int j = 0; j < nt.get(i).size(); j++) {
     for (int k = 0; k < nt.get(i).get(j).size(); k++) {
     System.out.print(nt.get(i).get(j).get(k) + "=>");
     }
     System.out.print(ntl.getLoopsGain().get(i).get(j));
     System.out.println();
     }
     System.out.println();
     System.out.println();
     System.out.println();
     }
     System.out.println(this.delta.getDelta());
     System.out.println(this.deltas.getDeltas());
     }*/

}
