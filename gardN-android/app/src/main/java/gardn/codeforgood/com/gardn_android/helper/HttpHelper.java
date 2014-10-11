package gardn.codeforgood.com.gardn_android.helper;

import android.content.Context;

import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by kushal on 2/23/14.
 */
public class HttpHelper {

    private static HttpHelper ourInstance = new HttpHelper();
    public static HttpHelper getInstance() { return ourInstance; }

    private static PersistentCookieStore mCookie;
    private static DefaultHttpClient mHttpClient;
    private static boolean initialized = false;
    private HttpHelper(){
    }

    /**
     * This method sets up ourInstance for use. Initialize must be run at least once before any other
     * methods are called on ourInstance
     * @param context
     */
    public void initialize(Context context) {
        this.mCookie = new PersistentCookieStore(context);
        HttpsCertAuth.getInstance().initKeyStore(context, "server.crt");
        this.mHttpClient = HttpsCertAuth.getInstance().getHttpsClient();
        this.mHttpClient.setCookieStore(mCookie);
        this.initialized = true;
    }

    /**
     * Determines if ourInstance has been initialized and then returns an error or DefaultHttpClient
     * @return
     */
    private static DefaultHttpClient getHttpClient() {
        if (initialized == false)
            throw new RuntimeException("Make sure ourInstance of HttpHelper has been Initialized");
        else
            return mHttpClient;
    }

    /**
     * Creates a JSON Object based on String key/value mappings
     *
     * @param map
     * @return
     */
    public static JSONObject jsonBuilder(Map<String, String> map) {
        JSONObject json = new JSONObject();
        try {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sends a JSON Object to a given uri via HTTP POST and converts the response into a JSON
     *
     * @param uri
     * @param json
     * @return
     */
    public static JSONObject httpPost(String uri, JSONObject json) throws Exception{
            DefaultHttpClient httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new StringEntity(json.toString()));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            return httpToJson(httpClient.execute(httpPost));
    }

    /**
     * Sends an HTTP DELETE request to uri and returns the response if it is a JSON
     * @param uri
     * @return
     */
    public static JSONObject httpDelete(String uri) throws Exception{
        DefaultHttpClient httpClient = getHttpClient();
        HttpDelete httpDelete = new HttpDelete(uri);
        try {
            return httpToJson(httpClient.execute(httpDelete));
        } catch (IOException e) {
            throw new Exception("Can't complete httpDelete request");
        }
    }

    /**
     * Sends an HTTP GET request to uri and returns the response if it is a JSON
     * @param uri
     * @return
     */
    public static JSONObject httpGet(String uri) throws Exception{
        DefaultHttpClient httpclient = getHttpClient();
        HttpGet httpget = new HttpGet(uri);
        try {
            return httpToJson(httpclient.execute(httpget));
        } catch (IOException e) {
            throw new Exception("Can't complete httpGet request");
        }
    }

    /**
     * Attempts to convert an HTTP Response into a string
     * and then create and return a JSON Object from that string
     *
     * @param response
     * @return
     */
    public static JSONObject httpToJson(HttpResponse response) throws Exception{
        try {
            String json = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject == null)
                throw new Exception("Returned JSON is Null");
            return jsonObject;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        } catch (JSONException e) {
            throw new Exception(e.getMessage());
        }
    }
}