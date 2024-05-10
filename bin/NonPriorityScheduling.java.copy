import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonPriorityScheduling {
    public static Integer totalprocess;
    public static Process[] proc;

    public static void setTotalprocess(Integer num) {
        totalprocess = num;
    }

    public static void initprocess() {
        proc = new Process[totalprocess];
    }

    static Integer[] get_rt_time(Integer stime[]) {
        Integer rt[] = new Integer[totalprocess];
        for (Integer i = 0; i < totalprocess; i++) {
            rt[i] = stime[i] - proc[i].at;
        }
        return rt;
    }

    static Integer[] get_BT(Integer noOfPro) {
        Integer bt[] = new Integer[totalprocess];
        for (Integer i = 0; i < totalprocess; i++) {
            bt[i] = proc[i].bt;
        }
        return bt;
    }

    static Integer[] get_AT(Integer noOfPro) {
        Integer at[] = new Integer[totalprocess];
        for (Integer i = 0; i < totalprocess; i++) {
            at[i] = proc[i].at;
        }
        return at;
    }

    static Integer[] get_PR(Integer noOfPro) {
        Integer pr[] = new Integer[totalprocess];
        for (Integer i = 0; i < totalprocess; i++) {
            pr[i] = proc[i].pr;
        }
        return pr;
    }

    static Integer[] convert(Integer[] IntegerArray) {

        Integer[] IntegeregerArray = new Integer[IntegerArray.length];

        for (Integer i = 0; i < IntegerArray.length; i++) {
            IntegeregerArray[i] = Integer.valueOf(IntegerArray[i]);
        }
        return IntegeregerArray;
    }

    public static ArrayList<Object[]> calc_wt_rt_tat_st_ct() {
        ArrayList<Object[]> list = new ArrayList<Object[]>();
        Integer wt[] = new Integer[totalprocess];
        Integer tat[] = new Integer[totalprocess];
        Integer stime[] = new Integer[totalprocess];
        Integer ct[] = new Integer[totalprocess];
        Integer bt[] = get_BT(totalprocess);
        Integer at[] = get_AT(totalprocess);
        Integer pr[] = get_PR(totalprocess);
        Integer temp, temp1;
        Integer totalwt = 0, totaltat = 0;
        Integer totalct = 0, totalrt = 0;

        double avgwt = 0.0, avgtat = 0.0;
        double avgrt = 0.0, avgct = 0.0;

        for (Integer i = 0; i < totalprocess; i++) {
            for (Integer j = i + 1; j < totalprocess; j++) {
                if (at[i] > at[j]) {
                    temp = at[i];
                    temp1 = bt[i];
                    at[i] = at[j];
                    bt[i] = bt[j];
                    at[j] = temp;
                    bt[j] = temp1;
                    temp = pr[i];
                    pr[i] = pr[j];
                    pr[j] = temp;
                    temp = i;
                    i = j;
                    j = temp;
                }
            }
        }

        for (Integer i = 0; i < totalprocess; i++) {
            stime[i] = 0;
            wt[i] = 0;
            ct[i] = 0;
        }

        for (Integer i = 0; i < totalprocess; i++) {
            stime[i] = i == 0 ? at[i] : ct[i - 1];
            wt[i] = i == 0 ? 0 : ct[i - 1] - at[i];
            ct[i] = stime[i] + bt[i];
            tat[i] = ct[i] - at[i];
            totalwt += wt[i];
            totaltat += tat[i];
            totalct += ct[i];
        }

        Integer[] rt = get_rt_time(stime);

        for (Integer i = 0; i < totalprocess; i++) {
            totalrt += rt[i];
        }

        list.add(stime);
        list.add(ct);
        list.add(tat);
        list.add(wt);
        list.add(rt);
        list.add(stime);

        avgwt = totalwt / totalprocess;
        avgtat = totaltat / totalprocess;
        avgct = totalct / totalprocess;
        avgrt = totalrt / totalprocess;

        list.add(new Double[] { avgwt, avgtat, avgct, avgrt });

        for (Integer i = 0; i < totalprocess; i++) {
            System.out.println(
                    "P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + stime[i] + "\t" + ct[i] + "\t" + tat[i] + "\t"
                            + wt[i]);
        }

        return list;
    }

    class Process {
        Integer at, bt, pr, pno;

        Process(Integer pno, Integer at, Integer bt, Integer pr) {
            this.pno = pno;
            this.pr = pr;
            this.at = at;
            this.bt = bt;
        }
    }
}
