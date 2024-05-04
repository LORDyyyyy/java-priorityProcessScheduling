public class AvgCalc {
    private int ctAvg;
    private int tatAvg;
    private int wtAvg;
    private int rtAvg;

    public AvgCalc() {
        this.ctAvg = 0;
        this.tatAvg = 0;
        this.wtAvg = 0;
        this.rtAvg = 0;
    }

    public int getCt() {
        return ctAvg;
    }

    public void setCt(int ctAvg) {
        this.ctAvg = ctAvg;
    }

    public int getTat() {
        return tatAvg;
    }

    public void setTat(int tatAvg) {
        this.tatAvg = tatAvg;
    }

    public int getWt() {
        return wtAvg;
    }

    public void setWt(int wt) {
        this.wtAvg = wt;
    }

    public int getRt() {
        return rtAvg;
    }

    public void setRt(int rt) {
        this.rtAvg = rt;
    }
}
