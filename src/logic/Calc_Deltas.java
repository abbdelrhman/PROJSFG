package logic;
import java.util.ArrayList;



public class Calc_Deltas {

    private ArrayList<ArrayList<ArrayList<Integer>>> ntPL = new ArrayList<ArrayList<ArrayList<Integer>>>();
    private ArrayList<Float> ntPLGain = new ArrayList<Float>();

    public void setDeltas(Paths path, Non_Touched_loops ntl) {
        for (int i = 0; i < path.getPaths().size(); i++) {
            ArrayList<ArrayList<Float>> tempGain_2 = new ArrayList<ArrayList<Float>>();
            ArrayList<Integer> tempPath = path.getPaths().get(i);
            ArrayList<Integer> tempSummation = new ArrayList<Integer>();
            for (int j = 0; j < ntl.getNonTouched().size(); j++) {
                ArrayList<Float> tempGain = new ArrayList<Float>();
                int sum = 0;
                for (int k = 0; k < ntl.getNonTouched().get(j).size(); k++) {
                    ArrayList<Integer> tempLoop = ntl.getNonTouched().get(j).get(k);
                    if (nonTouched(tempPath, tempLoop)) {
                        tempGain.add(ntl.getLoopsGain().get(j).get(k));
                        sum += ntl.getLoopsGain().get(j).get(k);
                    }
                }
                tempSummation.add(sum);
                sum = 0;
                tempGain_2.add((ArrayList<Float>) tempGain.clone());
            }
            this.ntPLGain.add(this.calcDeltas(tempSummation));
            tempSummation.clear();
            this.ntPL.add((ArrayList<ArrayList<Integer>>) tempGain_2.clone());
        }
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

    public ArrayList<ArrayList<ArrayList<Integer>>> getNonTouchedPathesWithLoops() {
        return this.ntPL;
    }

    public ArrayList<Float> getDeltas() {
        return this.ntPLGain;
    }

    private float calcDeltas(ArrayList<Integer> tempSummation) {
        float delta = 1;
        for (int i = 0; i < tempSummation.size(); i++) {
            if (i % 2 == 0) {
                delta -= tempSummation.get(i);
            } else {
                delta += tempSummation.get(i);
            }
        }
        return delta;
    }
}
