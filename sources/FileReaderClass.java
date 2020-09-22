import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileReaderClass {
    private static Stream<String> inputStream;

    public static List<MonitoredData> getObjectsFromInput() throws IOException {
        List<MonitoredData> monitoredData = new ArrayList<>();
        inputStream = Files.lines(Paths.get("Activities.txt"));
        inputStream.forEach(x->{
            String[] mdArgs = x.split("\\t+");//not sure if tabs or spaces
            monitoredData.add(new MonitoredData(mdArgs[0],mdArgs[1],mdArgs[2]));
        });
        return monitoredData;
    }
}
