import java.util.Arrays;

public class NonPriorityScheduling {
    private int n;
    private int[] arrivalTime;
    private int[] burstTime;
    private int[] priority;

    public NonPriorityScheduling(int[] arrivalTime, int[] burstTime, int[] priority) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.n = arrivalTime.length;
    }

    public int[] calculateCompletionTime() {
        int[] completionTime = new int[n];

        int CPU = 0;
        int allTime = 0;

        int[] ATt = Arrays.copyOf(arrivalTime, n);
        int NoP = n;
        int[] PPt = Arrays.copyOf(priority, n);

        int LAT = Arrays.stream(arrivalTime).max().getAsInt();
        int MAX_P = Arrays.stream(priority).max().getAsInt();

        int ATi = 0;
        int P1 = PPt[0];
        int P2 = PPt[0];

        while (NoP > 0 && CPU <= 1000) {
            int j = -1;
            for (int i = 0; i < n; i++) {
                if ((ATt[i] <= CPU) && (ATt[i] != (LAT + 10))) {
                    if (PPt[i] != (MAX_P + 1)) {
                        P2 = PPt[i];
                        j = 1;

                        if (P2 < P1) {
                            j = 1;
                            ATi = i;
                            P1 = PPt[i];
                            P2 = PPt[i];
                        }
                    }
                }
            }

            if (j == -1) {
                CPU = CPU + 1;
                continue;
            } else {
                CPU = CPU + burstTime[ATi];
                completionTime[ATi] = CPU;
                ATt[ATi] = LAT + 10;
                j = -1;
                PPt[ATi] = MAX_P + 1;
                ATi = 0;
                P1 = MAX_P + 1;
                P2 = MAX_P + 1;
                NoP = NoP - 1;
            }
        }

        return completionTime;
    }

    public int[] calculateWaitingTime() {
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        int CPU = 0;
        int allTime = 0;

        int[] ATt = Arrays.copyOf(arrivalTime, n);
        int NoP = n;
        int[] PPt = Arrays.copyOf(priority, n);

        int LAT = Arrays.stream(arrivalTime).max().getAsInt();
        int MAX_P = Arrays.stream(priority).max().getAsInt();

        int ATi = 0;
        int P1 = PPt[0];
        int P2 = PPt[0];

        while (NoP > 0 && CPU <= 1000) {
            int j = -1;
            for (int i = 0; i < n; i++) {
                if ((ATt[i] <= CPU) && (ATt[i] != (LAT + 10))) {
                    if (PPt[i] != (MAX_P + 1)) {
                        P2 = PPt[i];
                        j = 1;

                        if (P2 < P1) {
                            j = 1;
                            ATi = i;
                            P1 = PPt[i];
                            P2 = PPt[i];
                        }
                    }
                }
            }

            if (j == -1) {
                CPU = CPU + 1;
                continue;
            } else {
                waitingTime[ATi] = CPU - ATt[ATi];
                CPU = CPU + burstTime[ATi];
                turnaroundTime[ATi] = CPU - ATt[ATi];
                ATt[ATi] = LAT + 10;
                j = -1;
                PPt[ATi] = MAX_P + 1;
                ATi = 0;
                P1 = MAX_P + 1;
                P2 = MAX_P + 1;
                NoP = NoP - 1;
            }
        }

        return waitingTime;
    }

    public int[] calculateTurnaroundTime() {
        int[] turnaroundTime = new int[n];
        int[] waitingTime = calculateWaitingTime();
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = burstTime[i] + waitingTime[i];
        }
        return turnaroundTime;
    }

    public float calculateAverageWaitingTime() {
        int[] waitingTime = calculateWaitingTime();
        float avgWT = 0;
        for (int i = 0; i < n; i++) {
            avgWT += waitingTime[i];
        }
        return avgWT / n;
    }

    public float calculateAverageTurnaroundTime() {
        int[] turnaroundTime = calculateTurnaroundTime();
        float avgTAT = 0;
        for (int i = 0; i < n; i++) {
            avgTAT += turnaroundTime[i];
        }
        return avgTAT / n;
    }

    public static void test(String[] args) {
        int[] arrivaltime = { 0, 1, 3, 4, 5, 6, 10 };
        int[] bursttime = { 8, 2, 4, 1, 6, 5, 1 };
        int[] priority = { 3, 4, 4, 5, 2, 6, 1 };

        NonPriorityScheduling scheduler = new NonPriorityScheduling(arrivaltime, bursttime, priority);
        int[] waitingTime = scheduler.calculateWaitingTime();
        int[] turnaroundTime = scheduler.calculateTurnaroundTime();
        int[] CompletionTime = scheduler.calculateCompletionTime();

        float avgWT = scheduler.calculateAverageWaitingTime();
        float avgTAT = scheduler.calculateAverageTurnaroundTime();
        float avgRT = scheduler.calculateAverageWaitingTime();

        System.out.println(
                "\nProcess_Number\tBurst_Time\tPriority\tArrival_Time\tWaiting_Time\tTurnaround_Time\tCompletion_Time\tresponse_Time\n\n");
        for (int i = 0; i < scheduler.n; i++) {
            System.out.println("P" + (i + 1) + "\t\t" + bursttime[i] + "\t\t" + priority[i] + "\t\t" + arrivaltime[i]
                    + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + CompletionTime[i] + "\t\t"
                    + waitingTime[i]);
        }

        System.out.println("Average waiting time = " + avgWT);
        System.out.println("Average turnaround time = " + avgTAT);
        System.out.println("Average Response time = " + avgRT);
    }
}
