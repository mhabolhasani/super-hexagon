package Users;
import java.util.ArrayList;

public class User {
    private String userName;
    private double bestRecord;
    private ArrayList<String> History;

    public User(String userName) {
        this.userName = userName;
    }

    public void setBestRecord(double bestRecord) {
        this.bestRecord = bestRecord;
    }

    public double getBestRecord() {
        return bestRecord;
    }

    public ArrayList<String> getHistory() {
        return History;
    }

    public void addToHistory(double Record) {
        if (Record > this.getBestRecord()) {
            this.setBestRecord(Record);
        }
        this.History.add(Record + "at :");
    }


}