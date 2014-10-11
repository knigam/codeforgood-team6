class RegistrationsController < Devise::RegistrationsController
  skip_before_filter :verify_authenticity_token #, :only => :create
	before_filter :update_sanitized_params  
	respond_to :json

	def update_sanitized_params
  	devise_parameter_sanitizer.for(:sign_up) {|u| u.permit(:password_confirmation, :email, :password)}
	end

  def create
    build_resource(sign_up_params)
    if resource.save
      yield resource if block_given?
      if resource.active_for_authentication?
        sign_up(resource_name, resource)
				return render :json => {:success => true, :id => resource.id, :email => resource.email, :message => "User Created"}
      else
				return render :json => {:success => false, :message => "Signed up but inactive"}
      end
    else
      clean_up_passwords resource
      return render :json => {:success => false, :message => "Username is already in use"}
    end
  end
  
  def destroy
		user = current_user
		if user.destroy
			return render :json => {:success => true}
		else
			return render :json => {:success => false}
		end
	end	
end
