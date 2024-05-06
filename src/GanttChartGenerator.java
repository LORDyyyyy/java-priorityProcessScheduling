import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Process;

public class GanttChartGenerator {
    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }

    public static String generateGanttChart(int numProcesses, int[] arrivalTimes, int[] burstTimes, int[] priorities) {
        try {
            String[] command = { "python", "src/gen_gantt_chart.py" };

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream());

            writer.write((numProcesses + "\n"));
            writer.write(arrayToString(arrivalTimes));
            writer.write(arrayToString(burstTimes));
            writer.write(arrayToString(priorities));
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            String imagePath = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                imagePath = line;
            }

            process.waitFor();

            return imagePath;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}