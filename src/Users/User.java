package Users;

import java.util.ArrayList;

public class User {
    private String userName;
    private double bestRecord;
    private ArrayList<HistoryItem> history;

    public User(String userName) {
        this.userName = userName;
        this.bestRecord = 0;
        this.history = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<HistoryItem> getHistory() {
        return history;
    }

    public double getBestRecord() {
        return bestRecord;
    }

    private void setBestRecord(double bestRecord) {
        if(this.getBestRecord() < bestRecord){
            this.bestRecord = bestRecord;
        }
    }

    private void addToHistory(double record) {
        HistoryItem item = new HistoryItem(record, this.getUserName());
        this.history.add(item);
    }

    public void updateUser(double record){
        setBestRecord(record);
        addToHistory(record);
    }
}
