package com.codenuance.poc.service
import com.codenuance.poc.model.User
import com.codenunace.poc.repo.UserRepo

class UserService {

  var userRepo = new UserRepo

  def save(user: User) {
    userRepo.save(user)
  }

}