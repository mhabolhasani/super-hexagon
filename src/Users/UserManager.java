package Users;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import Menu.*;

public class UserManager {
    private ArrayList<User> users;
    private String filePath;
    public UserManager(String filePath) {
        this.filePath = filePath;
        this.users = new ArrayList<>();
        loadFromFile();
    }

    public User getOrCreateUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return user;
            }
        }

        User newUser = new User(userName);
        users.add(newUser);
        saveToFile();
        return newUser;
    }

    public void saveToFile() {
        if(Setting.isSaveable()) {
            try {
                Gson gson = new Gson();
                FileWriter writer = new FileWriter(filePath);
                gson.toJson(users, writer);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadFromFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                users = new ArrayList<>();
                return;
            }

            Gson gson = new Gson();
            FileReader reader = new FileReader(filePath);
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            users = gson.fromJson(reader, userListType);
            reader.close();

            if (users == null) {
                users = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            users = new ArrayList<>();
        }
    }
    public double getBest(){
        double bestRecord = 0;
        for(User user : getAllUsers()){
            if(user.getBestRecord() > bestRecord){
                bestRecord = user.getBestRecord();
            }
        }
        return bestRecord;
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }
}
