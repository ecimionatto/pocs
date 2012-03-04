package com.codenuance.poc.service
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import com.codenuance.poc.model.Address
import com.codenuance.poc.model.User
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UserServiceSpec extends FeatureSpec with GivenWhenThen with ShouldMatchers {

  feature("Manager saves customer") {

    info("As a store manager")
    info("I want to create customers providing address")
    info("so in the future I can send discount coupons")

    scenario("create new user and address") {
      given("Given the customer X does not exist")
      var user = new User("Eric Cartman")
      val address = new Address("101 Main Street", "South Park", "31222", "CO");
      user.addAddress(address)

      assert(user.address === address)

      when("When manager saves new user X")
      var service = new UserService
      var savedUser = service.save(user)

      then("Then create new user in the database")
      savedUser should not equal (null)
    }

    scenario("customer exists update address") {
      given("Given the customer X exists")
      var user = new User("Eric Cartman")
      user.addAddress(new Address("101 Main Street", "South Park", "31222", "CO"))
      val service = new UserService
      service.save(user)

      when("When manager customer X")
      and("And customer a has different address")
      var newAddress = new Address("2002 Ocean Blvd", "South Park", "31222", "CO")
      user = new User("Eric Cartman")
      user.addAddress(newAddress)
      var savedUser = service.save(user)

      then("Then update address in the database")
      user.address should be(newAddress)
    }

    scenario("customer exists alert manager") {
      given("Given the customer X exists")
      var user = new User("Eric Cartman")
      user.addAddress(new Address("2002 Ocean Blvd", "South Park", "31222", "CO"))
      val service = new UserService
      service.save(user)

      when("When manager saves customer X")
      and("And customer a has different address")
      var newAddress = new Address("2002 Ocean Blvd", "South Park", "31222", "CO")
      user = new User("Eric Cartman")
      user.addAddress(newAddress)

      then("""Then return message "User already updated""")
      intercept[IllegalArgumentException] {
        user.addAddress(newAddress)
      }

    }

  }
}
  
