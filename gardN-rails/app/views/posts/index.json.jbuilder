json.array!(@posts) do |post|
  json.extract! post, :id, :user_id, :plant_id, :longitude, :latitude, :instructions, :upkeep, :benifits, :tips
  json.url post_url(post, format: :json)
end
