package gardn.codeforgood.com.gardn_android.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gardn.codeforgood.com.gardn_android.R;
import gardn.codeforgood.com.gardn_android.helper.HttpHelper;

public class CommunityGardenActivity extends Activity {
    private ListView resultListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_garden);

        new AsyncTask<Void, Void, Boolean>(){
            JSONArray array;

            @Override
            protected Boolean doInBackground(Void... voids) {

                String uri = getString(R.string.conn) + getString(R.string.posts_index);
                try{
                    array = HttpHelper.httpGet(uri).getJSONArray("posts");
                }catch(Exception e){
                    System.out.print(e.getMessage());
                    return false;
                }
                return true;

            }

            @Override
            protected void onPostExecute(final Boolean success){
                List<String> posts = new ArrayList<String>();
                for(int i = 0; i < array.length(); i++){
                    try {
                        JSONObject json = array.getJSONObject(i);
                        posts.add(json.getString("common_name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if(success){
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CommunityGardenActivity.this,
                            android.R.layout.simple_list_item_1 );
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    resultListView.setAdapter(dataAdapter);
                }
                else {

                }
            }
        }.execute(null, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.community_garden, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
