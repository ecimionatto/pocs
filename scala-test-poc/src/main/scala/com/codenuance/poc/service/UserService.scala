package com.codenuance.poc.service
import javax.ws.rs.Path
import javax.ws.rs.POST
import com.codenuance.poc.model.User

@Path ("user")
class UserService {

  @POST
  def create(user: User) {
    
  }
  
  
  
}