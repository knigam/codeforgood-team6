require 'mandrill'
class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable
  has_many :posts
  has_many :ratings
	has_many :comments
 
  private
  def send_email(text, email)
        m = Mandrill::API.new '1bIbtiJDlLgJjqw4YuPegw'
        message = {   
         :subject=> "Someone messaged you on gardN!",  
         :from_name=> "Room Mate",  
         :text=> text,  
         :to=>[  
           {   
             :email=> email
           }   
         ],  
         :html=>"<html> #{text} </html>",  
         :from_email=>"message@gardn.room-mate.me"  
        }   
        sending = m.messages.send message  
  end 

	def get_rating
		up = self.ratings.where(rating: 1)
		if self.ratings.count != 0
			total = up/self.ratings.count
		else
			total = 0
		end
	end
end
