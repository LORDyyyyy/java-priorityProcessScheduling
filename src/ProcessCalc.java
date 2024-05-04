public class ProcessCalc {

    private int proNo;

    private int ct;
    private int tat;
    private int wt;
    private int rt;

    public ProcessCalc(int proNo) {
        this.proNo = proNo;
        this.ct = 0;
        this.tat = 0;
        this.wt = 0;
        this.rt = 0;
    }

    public int getProNo() {
        return proNo;
    }

    public void setProNo(int proNo) {
        this.proNo = proNo;
    }

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public int getTat() {
        return tat;
    }

    public void setTat(int tat) {
        this.tat = tat;
    }

    public int getWt() {
        return wt;
    }

    public void setWt(int wt) {
        this.wt = wt;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }
}