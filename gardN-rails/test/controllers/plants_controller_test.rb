require 'test_helper'

class PlantsControllerTest < ActionController::TestCase
  setup do
    @plant = plants(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:plants)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create plant" do
    assert_difference('Plant.count') do
      post :create, plant: { accepted_symbol: @plant.accepted_symbol, bloom_period: @plant.bloom_period, common_name: @plant.common_name, drought_tolerance: @plant.drought_tolerance, duration: @plant.duration, flower_color: @plant.flower_color, flower_conspicuous: @plant.flower_conspicuous, growth_habit: @plant.growth_habit, growth_period: @plant.growth_period, height_mature: @plant.height_mature, lifespan: @plant.lifespan, native_status: @plant.native_status, scientific_name: @plant.scientific_name, shade_tolerance: @plant.shade_tolerance, state: @plant.state, synonym_symbol: @plant.synonym_symbol }
    end

    assert_redirected_to plant_path(assigns(:plant))
  end

  test "should show plant" do
    get :show, id: @plant
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @plant
    assert_response :success
  end

  test "should update plant" do
    patch :update, id: @plant, plant: { accepted_symbol: @plant.accepted_symbol, bloom_period: @plant.bloom_period, common_name: @plant.common_name, drought_tolerance: @plant.drought_tolerance, duration: @plant.duration, flower_color: @plant.flower_color, flower_conspicuous: @plant.flower_conspicuous, growth_habit: @plant.growth_habit, growth_period: @plant.growth_period, height_mature: @plant.height_mature, lifespan: @plant.lifespan, native_status: @plant.native_status, scientific_name: @plant.scientific_name, shade_tolerance: @plant.shade_tolerance, state: @plant.state, synonym_symbol: @plant.synonym_symbol }
    assert_redirected_to plant_path(assigns(:plant))
  end

  test "should destroy plant" do
    assert_difference('Plant.count', -1) do
      delete :destroy, id: @plant
    end

    assert_redirected_to plants_path
  end
end
