package com.codenuance.poc.service
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen

class UserServiceSpec extends FeatureSpec with GivenWhenThen {

  feature("Customer save customer") {

    info("As a store manager")
    info("I want to create customers providing address")
    info("the future I can send customer coupons")

    scenario("create new user and address") {
      given("Given the customer X does not exist")
      when("When manager saves new user X")
      then("Then create new user in the database")
      pending
    }

    scenario("customer exists update address") {
      given("Given the customer X exists")
      when("When manager saves new customer X")
      then("And customer a has different address")
      and("Then update address in the database")
      pending
    }

    scenario("customer exists alert manager") {
      given("Given the customer X exists")
      when("When manager saves new customer X")
      then("And customer a has same address")
      and("""Then return message "User already updated""")
      pending
    }

  }
}
  
