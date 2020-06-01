package logic;
import java.util.ArrayList;

public class Paths {

    private ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Float> paths_gain = new ArrayList<Float>();

    private void dfs(int from, int distance, ArrayList<Integer> path, float[][] gain) {
        if (from == distance) {
            this.paths.add((ArrayList<Integer>) path.clone());
            this.set_gain(path, gain);
            path.remove(path.size() - 1);
            return;
        }
        for (int i = from + 1; i < gain.length; i++) {
            if (gain[from][i] !=0) {
                path.add(i);
                this.dfs(i, distance, path, gain);
            }

        }
        path.remove(path.size() - 1);
        return;
    }

    public void madePathes( int distance, float[][] gain) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        if (distance > 0) {
            path.add(0);
            this.dfs(0, distance - 1,  path, gain);
        }
    }

    public ArrayList<ArrayList<Integer>> getPaths() {
        return this.paths;
    }

    private void set_gain(ArrayList<Integer> path, float[][] gain) {
        float totGain = 1;
        for (int i = 0; i < path.size() - 1; i++) {
            totGain *= gain[path.get(i)][path.get(i + 1)];
        }
        this.paths_gain.add(totGain);
    }

    public ArrayList<Float> get_paths_gain() {
        return this.paths_gain;
    }
}
