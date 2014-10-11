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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gardn.codeforgood.com.gardn_android.R;
import gardn.codeforgood.com.gardn_android.helper.HttpHelper;
import gardn.codeforgood.com.gardn_android.model.Plant;
import gardn.codeforgood.com.gardn_android.model.Post;
import gardn.codeforgood.com.gardn_android.model.User;

public class CommunityGardenActivity extends Activity {
    private ListView resultListView;
    private List<Post> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_garden);

        postsList = new ArrayList<Post>();

        new AsyncTask<Void, Void, Boolean>(){
            JSONArray allJSONPosts;

            @Override
            protected Boolean doInBackground(Void... voids) {

                String uri = getString(R.string.conn) + getString(R.string.posts_index);
                try{
                    allJSONPosts = HttpHelper.httpGet(uri).getJSONArray("posts");
                }catch(Exception e){
                    System.out.print(e.getMessage());
                    return false;
                }

                for(int j = 0; j<allJSONPosts.length(); j++){
                    try{
                        JSONObject obj = allJSONPosts.getJSONObject(j);

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
                        newPost.setLongitude(obj.getDouble("longitude"));
                        newPost.setLatitude(obj.getDouble("latitude"));
                        newPost.setInstructions(obj.getString("instructions"));
                        newPost.setUpkeep(obj.getString("upkeep"));
                        newPost.setBenefits(obj.getString("benefits"));
                        newPost.setTips(obj.getString("tips"));

                        postsList.add(newPost);
                    }
                    catch (Exception e){
                        Post noPosts = new Post("No Posts :(");
                        postsList.add(0, noPosts);
                    }

                }
                return true;

            }

            @Override
            protected void onPostExecute(final Boolean success){

                if(success){
                    ArrayAdapter<Post> dataAdapter = new ArrayAdapter<Post>(CommunityGardenActivity.this,
                            android.R.layout.simple_list_item_1, postsList);

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

