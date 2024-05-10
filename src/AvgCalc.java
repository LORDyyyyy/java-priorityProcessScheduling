public class AvgCalc {
    private float ctAvg;
    private float tatAvg;
    private float wtAvg;
    private float rtAvg;

    public AvgCalc() {
        this.ctAvg = 0;
        this.tatAvg = 0;
        this.wtAvg = 0;
        this.rtAvg = 0;
    }

    public float getCt() {
        return ctAvg;
    }

    public void setCt(float ctAvg) {
        this.ctAvg = ctAvg;
    }

    public float getTat() {
        return tatAvg;
    }

    public void setTat(float tatAvg) {
        this.tatAvg = tatAvg;
    }

    public float getWt() {
        return wtAvg;
    }

    public void setWt(float wt) {
        this.wtAvg = wt;
    }

    public float getRt() {
        return rtAvg;
    }

    public void setRt(float rt) {
        this.rtAvg = rt;
    }
}
