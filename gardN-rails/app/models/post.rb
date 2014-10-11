class Post < ActiveRecord::Base
    belongs_to :user
    has_many :ratings
    has_one :plant
		has_many :comments
end
