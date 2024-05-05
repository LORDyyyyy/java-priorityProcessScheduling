public class AvgCalc {
    private Double ctAvg;
    private Double tatAvg;
    private Double wtAvg;
    private Double rtAvg;

    public AvgCalc() {
        this.ctAvg = 0.0;
        this.tatAvg = 0.0;
        this.wtAvg = 0.0;
        this.rtAvg = 0.0;
    }

    public Double getCt() {
        return ctAvg;
    }

    public void setCt(Double ctAvg) {
        this.ctAvg = ctAvg;
    }

    public Double getTat() {
        return tatAvg;
    }

    public void setTat(Double tatAvg) {
        this.tatAvg = tatAvg;
    }

    public Double getWt() {
        return wtAvg;
    }

    public void setWt(Double wt) {
        this.wtAvg = wt;
    }

    public Double getRt() {
        return rtAvg;
    }

    public void setRt(Double rt) {
        this.rtAvg = rt;
    }
}
