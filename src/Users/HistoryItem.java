package Users;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryItem {
    private double record;
    private String dateTime;
    private String userName;
    public HistoryItem(double record , String username) {
        this.userName = username;
        this.record = record;
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public double getRecord() {
        return record;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "user : " + this.userName + " , Record: " + record + " , at " + dateTime;
    }
}
