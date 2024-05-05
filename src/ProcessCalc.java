public class ProcessCalc {

    private int proNo;

    private Integer st;
    private Integer ct;
    private Integer tat;
    private Integer wt;
    private Integer rt;

    public ProcessCalc(Integer proNo) {
        this.proNo = proNo;
        this.st = 0;
        this.ct = 0;
        this.tat = 0;
        this.wt = 0;
        this.rt = 0;
    }

    public Integer getProNo() {
        return proNo;
    }

    public void setProNo(Integer proNo) {
        this.proNo = proNo;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public Integer getCt() {
        return ct;
    }

    public void setCt(Integer ct) {
        this.ct = ct;
    }

    public Integer getTat() {
        return tat;
    }

    public void setTat(Integer tat) {
        this.tat = tat;
    }

    public Integer getWt() {
        return wt;
    }

    public void setWt(Integer wt) {
        this.wt = wt;
    }

    public Integer getRt() {
        return rt;
    }

    public void setRt(Integer rt) {
        this.rt = rt;
    }
}