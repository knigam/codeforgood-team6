# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20141011060533) do

  create_table "plants", force: true do |t|
    t.string   "accepted_symbol"
    t.string   "synonym_symbol"
    t.string   "scientific_name"
    t.string   "common_name"
    t.string   "duration"
    t.string   "growth_habit"
    t.string   "growth_period"
    t.string   "flower_color"
    t.boolean  "flower_conspicuous"
    t.string   "height_mature"
    t.string   "lifespan"
    t.string   "drought_tolerance"
    t.string   "shade_tolerance"
    t.string   "bloom_period"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "plants_states", id: false, force: true do |t|
    t.integer "plant_id"
    t.integer "state_id"
  end

  create_table "posts", force: true do |t|
    t.integer  "user_id"
    t.integer  "plant_id"
    t.decimal  "longitude"
    t.decimal  "latitude"
    t.string   "instructions"
    t.string   "upkeep"
    t.string   "benifits"
    t.string   "tips"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "ratings", force: true do |t|
    t.integer  "user_id"
    t.integer  "post_id"
    t.integer  "rating"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "states", force: true do |t|
    t.string   "name"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "users", force: true do |t|
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.string   "display_name"
    t.integer  "rank"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "users", ["email"], name: "index_users_on_email", unique: true
  add_index "users", ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true

end
