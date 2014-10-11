class State < ActiveRecord::Base
    has_and_belongs_to_many :plant
end
