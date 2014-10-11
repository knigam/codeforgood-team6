class CreateStates < ActiveRecord::Migration
  def change
    create_table :states do |t|
      t.string :name

      t.timestamps
    end
    create_table :plants_states, id: false do |t|
        t.integer :plant_id
        t.integer :state_id
    end
  end
end
