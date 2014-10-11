class CreatePlants < ActiveRecord::Migration
  def change
    create_table :plants do |t|
      t.string :accepted_symbol
      t.string :synonym_symbol
      t.string :scientific_name
      t.string :common_name
      t.string :duration
      t.string :growth_habit
      t.string :growth_period
      t.string :flower_color
      t.boolean :flower_conspicuous
      t.string :height_mature
      t.string :lifespan
      t.string :drought_tolerance
      t.string :shade_tolerance
      t.string :bloom_period

      t.timestamps
    end
  end
end
