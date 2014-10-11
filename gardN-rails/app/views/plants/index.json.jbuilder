json.array!(@plants) do |plant|
  json.extract! plant, :id, :accepted_symbol, :synonym_symbol, :scientific_name, :common_name, :duration, :growth_habit, :growth_period, :flower_color, :flower_conspicuous, :height_mature, :lifespan, :drought_tolerance, :shade_tolerance, :bloom_period
  json.url plant_url(plant, format: :json)
end
