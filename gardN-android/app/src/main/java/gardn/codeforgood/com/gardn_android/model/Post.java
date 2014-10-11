package gardn.codeforgood.com.gardn_android.model;

/**
 * Created by Yasasvi on 10/11/14.
 */
public class Post {
    private int post_id;
    private User user;
    private Plant plant;
    private Double longitude;
    private Double latitude;
    private String instructions;
    private String upkeep;
    private String benefits;
    private String tips;
    private int userRating;


    public Post(){
    }

    public Post(String str){
        this.post_id = 0;
        this.user = null;
        this.instructions = str;
    }

    public Post(int post_id, User user){
        this.post_id = post_id;
        this.user = user;
    }

    public int setUserRating(int userRating) {
        this.userRating = userRating;
        return userRating;
    }

    public int getUserRating(){
        return this.userRating;
    }

    public String setInstructions(String instructions){
        this.instructions = instructions;
        return instructions;
    }

    public String setUpkeep(String upkeep){
        this.upkeep = upkeep;
        return upkeep;
    }

    public String setBenefits(String benefits) {
        this.benefits = benefits;
        return benefits;
    }

    public String setTips(String tips){
        this.tips = tips;
        return tips;
    }

    public Double setLongitude(Double longitude){
        this.longitude = longitude;
        return longitude;
    }

    public Double setLatitude(Double latitude){
        this.latitude = latitude;
        return latitude;
    }

    public Plant setPlant(Plant plant){
        this.plant=plant;
        return plant;
    }

    public int getPost_id(){ return this.post_id; }

    public User getUser(){
        return this.user;
    }

    public Plant getPlant() { return this.plant; }

    public String getInstructions(){
        return this.instructions;
    }

    public String getUpkeep(){
        return this.upkeep;
    }

    public String getBenefits(){
        return this.benefits;
    }

    public String getTips() {
        return this.tips;
    }

}
