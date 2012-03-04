package com.codenunace.poc.repo
import com.codenuance.poc.model.User

class UserRepo {

  def save(user: User) = println("user " + user + " persisted")

}