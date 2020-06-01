package logic;
import java.util.ArrayList;

public class Calc_Delta {

    private float delta = 1;

    public float getDelta() {
        return delta;
    }

    public void setDelta(ArrayList<ArrayList<Float>> loopsGain) {
        int sum = 0;
        for (int i = 0; i < loopsGain.size(); i++) {
            for (int j = 0; j < loopsGain.get(i).size(); j++) {
                sum += loopsGain.get(i).get(j);
            }
            if (i % 2 == 0) {
                this.delta -= sum;
            } else {
                this.delta += sum;
            }
            sum = 0;
        }
    }
}
