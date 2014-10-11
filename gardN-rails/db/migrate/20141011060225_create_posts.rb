class CreatePosts < ActiveRecord::Migration
  def change
    create_table :posts do |t|
      t.integer :user_id
      t.integer :plant_id
      t.decimal :longitude
      t.decimal :latitude
      t.string :instructions
      t.string :upkeep
      t.string :benefits
      t.string :tips

      t.timestamps
    end
  end
end
