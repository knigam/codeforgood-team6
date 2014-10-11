require 'mandrill'
class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable
  has_many :posts
  has_many :ratings
 
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

end
