package com.codenuance.poc.model

class Address(_address1: String, _city: String, _zip: String, _state: String) {
  var address2: String = ""
  def address1 = _address1
  def city = _city
  def zip = _zip
  def state = _state

  override def equals(that: Any): Boolean = {
    if (that.isInstanceOf[Address]) {
      val thatAddress = that.asInstanceOf[Address]
      return thatAddress.address1 == address1 && thatAddress.city == city && thatAddress.zip == zip && thatAddress.state == state
    }
    return false
  }
}