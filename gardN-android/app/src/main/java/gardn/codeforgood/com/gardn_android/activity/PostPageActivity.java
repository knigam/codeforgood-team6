package gardn.codeforgood.com.gardn_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gardn.codeforgood.com.gardn_android.R;
import gardn.codeforgood.com.gardn_android.helper.HttpHelper;
import gardn.codeforgood.com.gardn_android.model.Plant;
import gardn.codeforgood.com.gardn_android.model.Post;
import gardn.codeforgood.com.gardn_android.model.User;

public class PostPageActivity extends Activity {
    int postId;
    Post mPost;
    Random rand;
    List<Post> postsList = new ArrayList<Post>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        postId = intent.getIntExtra("postId", -1);
        mPost = new Post();
        mPost.setPost_id(postId);

        final String URI = getString(R.string.conn) + "/posts/" + postId + ".json";

        new AsyncTask<Void, Void, Boolean>(){
            JSONObject result;
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    result = HttpHelper.httpGet(URI);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if( result == null)
                    return false;
                else
                    return true;
            }
            @Override
            protected void onPostExecute(final Boolean success){
                if(success){
                    setContentView(R.layout.activity_post_page);
                    for(int j = 0; j<result.length(); j++){
                        try{
                            JSONObject obj = result.getJSONObject("post");

                            //create user for post
                            JSONObject user = obj.getJSONObject("user");
                            User newUser = new User(user.getInt("id"), user.getString("email"));

                            //create plant for post
                            JSONObject plant = obj.getJSONObject("plant");
                            Plant newPlant = new Plant(plant.getInt("id"));
                            newPlant.setAccepted_symbol(plant.getString("accepted_symbol"));
                            newPlant.setSynonym_symbol(plant.getString("synonym_symbol"));
                            newPlant.setScientific_name(plant.getString("scientific_name"));
                            newPlant.setCommon_name(plant.getString("common_name"));
                            ((TextView) findViewById(R.id.textView2)).setText(newPlant.getCommon_name());
                            newPlant.setDuration(plant.getString("duration"));
                            newPlant.setGrowth_habit(plant.getString("growth_habit"));
                            newPlant.setGrowth_period(plant.getString("growth_period"));
                            newPlant.setFlower_color(plant.getString("flower_color"));
                            newPlant.setFlower_conspicuous(plant.getBoolean("flower_conspicuous"));
                            newPlant.setHeight_mature(plant.getString("height_mature"));
                            newPlant.setLifespan(plant.getString("lifespan"));
                            newPlant.setDrought_tolerance(plant.getString("drought_tolerance"));
                            newPlant.setShade_tolerance(plant.getString("shade_tolerance"));
                            newPlant.setBloom_period(plant.getString("bloom_period"));

                            //create new Post
                            Post newPost = new Post(obj.getInt("id"), newUser);
                            newPost.setPlant(newPlant);
                            newPost.setUserRating(rand.nextInt(50)+50);
                            newPost.setLongitude(obj.getDouble("longitude"));
                            newPost.setLatitude(obj.getDouble("latitude"));
                            newPost.setInstructions(obj.getString("instructions"));
                            newPost.setUpkeep(obj.getString("upkeep"));
                            newPost.setBenefits(obj.getString("benefits"));
                            ((TextView) findViewById(R.id.textView3)).setText(newPost.getBenefits());
                            newPost.setTips(obj.getString("tips"));
                            System.out.println(newPost.toString());
                            ((TextView) findViewById(R.id.textView)).setText("Here's what " + newPost.getUser().getEmail() + " is doing!");

                            postsList.add(newPost);
                        }
                        catch (Exception e){
                            Post noPosts = new Post("No Posts :(");
                            postsList.add(0, noPosts);
                        }
                    }
                }
            }
        }.execute(null, null, null);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.post_page, menu);
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
