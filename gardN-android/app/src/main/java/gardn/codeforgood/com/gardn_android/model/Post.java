package gardn.codeforgood.com.gardn_android.model;

/**
 * Created by Yasasvi on 10/11/14.
 */
public class Post {
    private int user;
    private int plant_id;
    private Double longitude;
    private Double latitude;
    private String instructions;
    private String upkeep;
    private String benefits;
    private String tips;

    private Post(){
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

    public int getUser(){
        return this.user;
    }

    public int getPlant_id(){
        return this.plant_id;
    }

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
