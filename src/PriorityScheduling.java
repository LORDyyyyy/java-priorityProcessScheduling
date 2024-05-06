import java.util.ArrayList;
import java.util.Arrays;

public class PriorityScheduling {
    public static int totalprocess;
    public static Process[] proc;

    public static void setTotalprocess(int num) {
        totalprocess = num;
    }

    public static void initprocess() {
        proc = new Process[totalprocess];
    }

    static boolean comp(Process a, Process b) {
        if (a.at == b.at) {
            return a.pr < b.pr;
        } else {
            return a.at < b.at;
        }
    }

    static int[] get_wt_time() {
        int wt[] = new int[totalprocess];
        int service[] = new int[totalprocess];
        service[0] = proc[0].at;
        wt[0] = 0;

        for (int i = 1; i < totalprocess; i++) {
            service[i] = proc[i - 1].bt + service[i - 1];
            wt[i] = service[i] - proc[i].at;
            if (wt[i] < 0) {
                wt[i] = 0;
            }
        }
        return wt;
    }

    static int[] get_tat_time(int wt[]) {
        int tat[] = new int[totalprocess];
        for (int i = 0; i < totalprocess; i++) {
            tat[i] = proc[i].bt + wt[i];
        }
        return tat;
    }

    static int[] get_rt_time(int stime[]) {
        int rt[] = new int[totalprocess];
        for (int i = 0; i < totalprocess; i++) {
            rt[i] = stime[i] - proc[i].at;
        }
        return rt;
    }

    static Integer[] convert(int[] intArray) {

        Integer[] integerArray = new Integer[intArray.length];

        for (int i = 0; i < intArray.length; i++) {
            integerArray[i] = Integer.valueOf(intArray[i]);
        }
        return integerArray;
    }

    public static ArrayList<Object[]> findgc() {

        Arrays.sort(proc, (obj1, obj2) -> {
            if (obj1.at > obj2.at)
                return 1;
            if (obj1.at < obj2.at)
                return -1;
            if (obj1.pr > obj2.pr)
                return 1;
            if (obj1.pr < obj2.pr)
                return -1;
            return 0;
        });

        ArrayList<Object[]> output = new ArrayList<>();

        Double wavg = 0.0, tavg = 0.0, cavg = 0.0, ravg = 0.0;

        int sortedprocess[] = new int[totalprocess];

        for (int i = 0; i < totalprocess; i++) {
            sortedprocess[i] = proc[i].pno;
        }

        int wt[] = get_wt_time();
        int tat[] = get_tat_time(wt);

        int stime[] = new int[totalprocess];
        int ctime[] = new int[totalprocess];

        stime[0] = proc[0].at;
        ctime[0] = stime[0] + tat[0];

        for (int i = 1; i < totalprocess; i++) {
            stime[i] = ctime[i - 1];
            ctime[i] = stime[i] + tat[i] - wt[i];
        }

        int rt[] = get_rt_time(stime);

        for (int i = 0; i < totalprocess; i++) {
            wavg += wt[i];
            tavg += tat[i];
            cavg += ctime[i];
            ravg += rt[i];
        }

        Double avg[] = {
                wavg / totalprocess,
                tavg / totalprocess,
                cavg / totalprocess,
                ravg / totalprocess
        };

        output.add(convert(sortedprocess));
        output.add(convert(ctime));
        output.add(convert(tat));
        output.add(convert(wt));
        output.add(convert(rt));
        output.add(convert(stime));
        output.add(avg);

        return output;
    }

    class Process {
        int at, bt, pr, pno;

        Process(int pno, int at, int bt, int pr) {
            this.pno = pno;
            this.pr = pr;
            this.at = at;
            this.bt = bt;
        }
    }
}
