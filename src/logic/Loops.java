package logic;
import java.util.ArrayList;

public class Loops {

    private ArrayList<ArrayList<Integer>> loops = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Float> loops_gain = new ArrayList<Float>();

    private void dfs(int from, int distance, ArrayList<Integer> loop, boolean flag, float[][] gain) {
        if (from == distance && flag) {
            this.loops.add((ArrayList<Integer>) loop.clone());
            this.set_gain(loop, gain);
            loop.remove(loop.size() - 1);
            flag = false;
            return;
        }
        flag = true;
        for (int i = distance; i < gain.length; i++) {
            if (gain[from][i] !=0 && this.contain(loop, i)) {
                loop.add(i);
                this.dfs(i, distance, loop, flag, gain);
            }

        }
        loop.remove(loop.size() - 1);
        return;
    }

    private boolean contain(ArrayList<Integer> loop, int pivot) {
        for (int i = 1; i < loop.size(); i++) {
            if (pivot == loop.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void madeLoops( int distance, float[][] gain) {
        ArrayList<Integer> loop = new ArrayList<Integer>();
        if (distance > 0) {
            for (int i = 0; i < gain.length; i++) {
                loop.add(i);
                this.dfs(i, i, loop, false, gain);
            }
        }
    }

    private void set_gain(ArrayList<Integer> loop, float[][] gain) {
        float totGain = 1;
        for (int i = 0; i < loop.size() - 1; i++) {
            totGain *= gain[loop.get(i)][loop.get(i + 1)];
        }
        this.loops_gain.add(totGain);
    }

    public ArrayList<ArrayList<Integer>> getLoops() {
        return this.loops;
    }

    public ArrayList<Float> get_loops_gain() {
        return this.loops_gain;
    }
}
