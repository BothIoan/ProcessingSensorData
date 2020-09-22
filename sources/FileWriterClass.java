import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class FileWriterClass {
    private static BufferedWriter bufferedWriter;
    private static void initWriter(String name) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(name, true));
    }

    public static void writeTask1(List<MonitoredData> data) throws IOException {
        initWriter("task_1.txt");
        data.forEach(x->{
            try {
                bufferedWriter.append("start: ").append(x.getStartTime().toString()).append("end:").append(x.getEndTime().toString()).append("activity:").append(x.getActivity()).append("\n");
            } catch (IOException ignored) { }
        });
        bufferedWriter.close();
    }

    public static void writeTask2(long data) throws IOException {
        initWriter("task_2.txt");
        bufferedWriter.write("Count: "+ data);
        bufferedWriter.close();
    }
    public static void writeTask3(Map<String, Integer> mapping) throws IOException {
        initWriter("task_3.txt");
        bufferedWriter.write(mapping.toString());
        bufferedWriter.close();
    }

    public static void writeTask4(Map<Integer, Map<String, Integer>> mapping2) throws IOException {
        initWriter("task_4.txt");
        bufferedWriter.write(mapping2.toString());
        bufferedWriter.close();
    }

    public static void writeTask5(Map<String, LocalTime> task5Mapping) throws IOException {
        initWriter("task_5.txt");
        bufferedWriter.write(task5Mapping.toString());
        bufferedWriter.close();
    }

    public static void writeTask6(List<String> filteredActivities) throws IOException {
        initWriter("task_6.txt");
        bufferedWriter.write(filteredActivities.toString());
        bufferedWriter.close();
    }
}
