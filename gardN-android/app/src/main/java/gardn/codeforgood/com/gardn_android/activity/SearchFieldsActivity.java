package gardn.codeforgood.com.gardn_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gardn.codeforgood.com.gardn_android.R;
import gardn.codeforgood.com.gardn_android.helper.HttpHelper;
import gardn.codeforgood.com.gardn_android.model.Plant;
import gardn.codeforgood.com.gardn_android.model.Post;
import gardn.codeforgood.com.gardn_android.model.User;

public class SearchFieldsActivity extends Activity {
    private List<Post> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_fields);

    postsList = new ArrayList<Post>();

    new AsyncTask<Void, Void, Boolean>(){
        JSONArray allJSONPosts;

        @Override
        protected Boolean doInBackground(Void... voids) {

            String uri = getString(R.string.conn) + "/posts/" +  ((EditText)findViewById(R.id.editText)).getText();

            try{
                allJSONPosts = HttpHelper.httpGet(uri).getJSONArray("posts");
            }catch(Exception e){
                System.out.print(e.getMessage());
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success){

            if(success){
                ArrayAdapter<Post> dataAdapter = new ArrayAdapter<Post>(SearchFieldsActivity.this,
                        android.R.layout.simple_list_item_1, postsList);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                resultListView = new ListView(SearchFieldsActivity.this);
//                resultListView.setAdapter(dataAdapter);
            }
            else {
                System.out.println("Generating posts failed :( ");
            }
        }
    }.execute(null, null, null);
}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_fields, menu);
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
