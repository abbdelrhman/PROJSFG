package logic;
import java.util.ArrayList;

public class Non_Touched_loops {

    private ArrayList<ArrayList<ArrayList<Integer>>> nt = new ArrayList<ArrayList<ArrayList<Integer>>>();
    private ArrayList<ArrayList<ArrayList<Integer>>> ntIndexes = new ArrayList<ArrayList<ArrayList<Integer>>>();
    private ArrayList<ArrayList<Float>> all_loops_gain = new ArrayList<ArrayList<Float>>();

    public void madeNonTouchedLoops(ArrayList<ArrayList<Integer>> loops, ArrayList<Float> loopsGain) {
        ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> tempIndex = new ArrayList<ArrayList<Integer>>();
        ArrayList<Float> tempGains = new ArrayList<Float>();
        this.nt.add(loops);
        this.loopsName(loops.size());
        this.all_loops_gain.add(loopsGain);
        for (int k = 0; k < nt.size(); k++) {
            for (int i = 0; i < nt.get(0).size(); i++) {
                ArrayList<Integer> first = nt.get(0).get(i);
                ArrayList<Integer> indexFirst = ntIndexes.get(0).get(i);
                for (int j = this.get_j(first,k); j < nt.get(k).size(); j++) {
                    ArrayList<Integer> last = nt.get(k).get(j);
                    ArrayList<Integer> indexLast = ntIndexes.get(k).get(j);
                    if (!first.get(0).equals(last.get(0))) {
                        if (this.nonTouched(first, last)) {
                            temp.add(this.add(first, last));
                            tempIndex.add(this.add(indexFirst, indexLast));
                            tempGains.add(all_loops_gain.get(0).get(i) * all_loops_gain.get(k).get(j));
                        }
                    }
                }
            }
            if (!temp.isEmpty()) {
                this.nt.add((ArrayList<ArrayList<Integer>>) temp.clone());
                this.ntIndexes.add((ArrayList<ArrayList<Integer>>) tempIndex.clone());
                this.all_loops_gain.add((ArrayList<Float>) tempGains.clone());
                temp.clear();
                tempIndex.clear();
                tempGains.clear();
            } else {
                break;
            }
        }
    }

    private void loopsName(int n) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> tempB = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            temp.add(i);
            tempB.add((ArrayList<Integer>) temp.clone());
            temp.clear();
        }
        this.ntIndexes.add((ArrayList<ArrayList<Integer>>) tempB.clone());
    }

    private int get_j(ArrayList<Integer> first,int k) {
        int i = 0;
        for (i = 0; i < nt.get(k).size(); i++) {
            if (nt.get(k).get(i).get(0) > first.get(0)) {
                return i;
            }
        }
        return i;
    }

    private ArrayList<Integer> add(ArrayList<Integer> first, ArrayList<Integer> last) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < first.size(); i++) {
            list.add(first.get(i));
        }
        for (int i = 0; i < last.size(); i++) {
            list.add(last.get(i));
        }
        return list;

    }

    private boolean nonTouched(ArrayList<Integer> first, ArrayList<Integer> last) {
        for (int i = 0; i < first.size(); i++) {
            int x = first.get(i);
            for (int j = 0; j < last.size(); j++) {
                int y = last.get(j);
                if (x == y) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getNonTouched() {
        return this.nt;
    }

    public ArrayList<ArrayList<Float>> getLoopsGain() {
        return this.all_loops_gain;
    }


    public ArrayList<ArrayList<ArrayList<Integer>>> getNonTouchedLoopsName() {
        return this.ntIndexes;
    }
}
