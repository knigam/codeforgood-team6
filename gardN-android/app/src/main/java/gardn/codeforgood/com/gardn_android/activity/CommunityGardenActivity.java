package gardn.codeforgood.com.gardn_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gardn.codeforgood.com.gardn_android.R;
import gardn.codeforgood.com.gardn_android.helper.HttpHelper;

public class CommunityGardenActivity extends Activity {
    private ListView resultListView;
    private JSONObject array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_garden);

        new AsyncTask<Void, Void, Boolean>(){

            @Override
            protected Boolean doInBackground(Void... voids) {
                String posts = null;

                posts = getString(R.string.posts_index);
                try{
                    array = HttpHelper.httpGet(posts);
                }catch(Exception e){
                    System.out.print(e.getMessage());
                    return false;
                }
                return true;

            }

            @Override
            protected void onPostExecute(final Boolean success){
                List<String> posts = new ArrayList<String>();
//                for(int i = 0; i < array.length(); i++){
//                    try {
//                        JSONObject json = array.getJSONObject(i);
//                        posts.add(json.getString("common_name"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }

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
        if (id == R.id.action_post) {
            Intent intentMain = new Intent(CommunityGardenActivity.this,
                                            NewPostActivity.class);
            CommunityGardenActivity.this.startActivity(intentMain);

            return true;
        }
        if (id == R.id.action_viewProf) {
            return true;
        }
        if (id == R.id.action_donate) {

            Uri uri = Uri.parse("https://online.nwf.org/site/Donation2?df_id=32500&32500.donation=form1&s_subsrc=Web_Header_Donate_06302014_Wrapper_CONTROL");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

            return true;
        }
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_logout) {
            return true;
        }

        return true;
        }
    }

