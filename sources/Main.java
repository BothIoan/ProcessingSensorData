import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {
        List<MonitoredData> monitoredData =FileReaderClass.getObjectsFromInput();
        //Task1
        int[] startDays =monitoredData.stream().mapToInt(x->x.getStartTime().getDayOfYear()).toArray();
        int[] endDays = monitoredData.stream().mapToInt(x->x.getEndTime().getDayOfYear()).toArray();
        int[] allDays = new int[startDays.length+endDays.length];
        System.arraycopy(startDays,0,allDays,0,startDays.length);
        System.arraycopy(endDays,0,allDays,startDays.length,endDays.length);
        int[] distinctDays= Arrays.stream(allDays).distinct().toArray();
        long nrDays = Arrays.stream(distinctDays).count();
        //Task2
        Map<String,Integer> mapping  = new HashMap<>();
        List<String> allActivities = monitoredData.stream().map(MonitoredData::getActivity).distinct().collect(Collectors.toList());
        allActivities.stream().forEach(x->{
            Integer count = (int) monitoredData.stream().filter(y->y.getActivity().equals(x)).count();
            mapping.put(x,count);
        });
        //Task3
        Map<Integer,Map<String,Integer>> mapping2 = new HashMap<>();
        Arrays.stream(distinctDays).forEach(x->{
            List<MonitoredData> currentDayData = monitoredData.stream().filter(y->y.getStartTime().getDayOfYear() ==x||y.getEndTime().getDayOfYear()==x).collect(Collectors.toList());
            List<String> currentDayAct = currentDayData.stream().map(MonitoredData::getActivity).distinct().collect(Collectors.toList());
            Map <String,Integer> mapping3=new HashMap<>();
            currentDayAct.forEach(z->{
                Integer count =(int) currentDayData.stream().filter(a->a.getActivity().equals(z)).count();
                mapping3.put(z,count);
            });
            mapping2.put(x,mapping3);
        });
        //Task4
        Map<String, LocalTime> task5Mapping = new HashMap<>();
        allActivities.forEach(x-> {
            List<MonitoredData> dataOfActivity = monitoredData.stream().filter(y -> y.getActivity().equals(x)).collect(Collectors.toList());
            LocalTime localTime = dataOfActivity.stream().map(y -> {
                LocalDateTime startTime = y.getStartTime();
                return y.getEndTime().minusYears(startTime.getYear()).minusMonths(startTime.getMonthValue()).minusDays(startTime.getDayOfYear()).minusHours(startTime.getHour()).minusMinutes(startTime.getMinute()).minusSeconds(startTime.getMinute()).toLocalTime();
            }).reduce(LocalTime.MIDNIGHT, (a, b) -> a.plusHours(b.getHour()).plusMinutes(b.getMinute()).plusSeconds(b.getSecond()));
            task5Mapping.put(x, localTime);
        });
       List<String> filteredActivities =allActivities.stream().filter(x->{
           List<MonitoredData> dataOfActivity = monitoredData.stream().filter(y->y.getActivity().equals(x)).collect(Collectors.toList());
           long underFive = dataOfActivity.stream().filter(y->{
               return Duration.between(y.getStartTime(), y.getEndTime()).toMinutes() < 5;
           }).count();
           long moreThanFive = dataOfActivity.stream().filter(y->{
               return Duration.between(y.getStartTime(), y.getEndTime()).toMinutes() >= 5;
           }).count();
           return moreThanFive * 9 < underFive;
       }).collect(Collectors.toList());
        //Task6
        FileWriterClass.writeTask1(monitoredData);
        FileWriterClass.writeTask2(nrDays);
        FileWriterClass.writeTask3(mapping);
        FileWriterClass.writeTask4(mapping2);
        FileWriterClass.writeTask5(task5Mapping);
        FileWriterClass.writeTask6(filteredActivities);
    }
}
