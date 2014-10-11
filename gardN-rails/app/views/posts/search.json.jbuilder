json.array!(@posts) do |post|
    json.extract! post, :id, :user_id, :plant_id, :longitude, :latitude, :instructions, :upkeep, :benifits, :tip, :common_name => Plant.find(:plant_id).common_name
  json.url post_url(post, format: :json)
end
