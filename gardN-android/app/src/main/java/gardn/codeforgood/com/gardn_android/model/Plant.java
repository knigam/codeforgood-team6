package gardn.codeforgood.com.gardn_android.model;

/**
 * Created by kushal on 10/10/14.
 */
public class Plant {
    private int plant_id;
    private String accepted_symbol;
    private String synonym_symbol;
    private String scientific_name;
    private String common_name;
    private String duration;
    private String growth_habit;
    private String growth_period;
    private String flower_color;
    private boolean flower_conspicuous;
    private String height_mature;
    private String lifespan;
    private String drought_tolerance;
    private String shade_tolerance;
    private String bloom_period;

    public Plant(){

    }

    public Plant(int plant_id){
        this.plant_id = plant_id;
    }

    public int getPlant_id(){
        return this.plant_id;
    }

    public String getAccepted_symbol() {
        return accepted_symbol;
    }

    public void setAccepted_symbol(String accepted_symbol) {
        this.accepted_symbol = accepted_symbol;
    }

    public String getSynonym_symbol() {
        return synonym_symbol;
    }

    public void setSynonym_symbol(String synonym_symbol) {
        this.synonym_symbol = synonym_symbol;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGrowth_habit() {
        return growth_habit;
    }

    public void setGrowth_habit(String growth_habit) {
        this.growth_habit = growth_habit;
    }

    public String getGrowth_period() {
        return growth_period;
    }

    public void setGrowth_period(String growth_period) {
        this.growth_period = growth_period;
    }

    public String getFlower_color() {
        return flower_color;
    }

    public void setFlower_color(String flower_color) {
        this.flower_color = flower_color;
    }

    public boolean isFlower_conspicuous() {
        return flower_conspicuous;
    }

    public void setFlower_conspicuous(boolean flower_conspicuous) {
        this.flower_conspicuous = flower_conspicuous;
    }

    public String getHeight_mature() {
        return height_mature;
    }

    public void setHeight_mature(String height_mature) {
        this.height_mature = height_mature;
    }

    public String getLifespan() {
        return lifespan;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }

    public String getDrought_tolerance() {
        return drought_tolerance;
    }

    public void setDrought_tolerance(String drought_tolerance) {
        this.drought_tolerance = drought_tolerance;
    }

    public String getShade_tolerance() {
        return shade_tolerance;
    }

    public void setShade_tolerance(String shade_tolerance) {
        this.shade_tolerance = shade_tolerance;
    }

    public String getBloom_period() {
        return bloom_period;
    }

    public void setBloom_period(String bloom_period) {
        this.bloom_period = bloom_period;
    }

}
