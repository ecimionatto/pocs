package com.codenuance.poc.model
import com.codenuance.poc.model.Address

class User(name: String) {
  var address: Address = null

  def addAddress(address: Address) {
    if (this.address != null && this.address.equals(address)) {
      throw new IllegalArgumentException("address already updated!")
    }
    this.address = address
  }

}