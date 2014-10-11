class SessionsController < Devise::SessionsController
  skip_before_filter :verify_authenticity_token #, :only => [:create, :failure]
  before_filter :authenticate_user!, :only => :destroy
	respond_to :json
  
  def create
    resource = warden.authenticate!(:scope => resource_name, :recall => "#{controller_path}#failure")
    yield resource if block_given?
    sign_in(resource_name, resource)
    cookie = User.serialize_into_cookie(resource)
    return render :json => {:success => true, :id => resource.id, :email => resource.email, :message => "Signed in"}
  end

  def destroy
    Devise.sign_out_all_scopes ? sign_out : sign_out(resource_name)
    render :json => {:success => true}
  end

  def failure
    warden.custom_failure!
    return render :json => {:success => false, :message => "Incorrect username or password"}
  end 
end
