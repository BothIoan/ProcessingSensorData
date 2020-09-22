import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class MonitoredData {
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String activity;

    MonitoredData(String startParams,String endParams, String activity){
        startTime = LocalDateTime.parse(startParams.replace(" ","T"));
        endTime = LocalDateTime.parse(endParams.replace(" ","T"));
        this.activity=activity;
    }
}
