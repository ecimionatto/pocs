package controllers
import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import com.opeak.poc.model.Device
import com.opeak.poc.repository.DeviceRepository
import org.specs2.runner.JUnitRunner
import play.api.libs.json.Json._
import play.api.libs.json.JsObject

@RunWith(classOf[JUnitRunner])
class JsonControllerSpecs extends Specification with Mockito {

  "This is a specification to check JsonController"
  "JsonController should" ^
    "call deviceRepository" ! c().callDeviceRepository ^
    "json response is proper" ! c().jsonReponse

  case class c() extends Mockito {
    val deviceRepositoryMock = mock[DeviceRepository]
    var deviceFixture = new Device()
    deviceFixture.setMdi("123123123123")
    deviceFixture.setName("device 123123123123")

    deviceRepositoryMock.findById("123123123123") returns deviceFixture

    JsonController.deviceRepository = deviceRepositoryMock
    val jsonResponse = JsonController.getJsonDeviceFromRepository("123123123123").asInstanceOf[JsObject]

    def callDeviceRepository() = {

      there was one(deviceRepositoryMock).findById("123123123123")
    }

    def jsonReponse() = {
      print(toJson(jsonResponse))
      toJson(jsonResponse).toString() must be equalTo ("""{"device":{"mdi":"123123123123","name":"device 123123123123"}}""")

    }
  }
}