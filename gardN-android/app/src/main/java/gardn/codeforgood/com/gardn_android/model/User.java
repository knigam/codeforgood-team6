package gardn.codeforgood.com.gardn_android.model;

/**
 * Created by kushal on 10/10/14.
 */
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import gardn.codeforgood.com.gardn_android.R;
import gardn.codeforgood.com.gardn_android.exception.MyException;
import gardn.codeforgood.com.gardn_android.helper.HttpHelper;

/**
 * Created by kushal on 3/5/14.
 */
public class User {
    private static User ourInstance = new User();

    public static User getInstance() {
        return ourInstance;
    }

    private final String USER_EMAIL = "user_email";
    private final String USER_ID = "user_id";
    private int id;
    private String email;

    public User(){

    }
    public User(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public String getEmail() {
        return this.email;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public int getId() {
        return this.id;
    }

    /**
     * This will only sign out user from backend. This method should only be called through
     * an async task, and local user data must be cleared after the task executes.
     *
     * @param URI
     * @return
     */
    public boolean signOut(final String URI) throws MyException {
        JSONObject result = null;
        try {
            result = HttpHelper.httpDelete(URI);
        } catch (Exception e) {
            throw new MyException(e);
        }
        try {
            if (result.getString("success").equals("true")) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}