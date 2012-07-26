package controllers
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api._
import service.SpringContext
import com.opeak.poc.repository.DeviceRepository
import play.api.libs.json.Json._
import play.api.libs.json.JsValue

object JsonController extends Controller {

  var deviceRepository = SpringContext.applicationContext.getBean("deviceRepository").asInstanceOf[DeviceRepository]

  def getJsonDeviceFromRepository(id: String): JsValue = {
    val device = deviceRepository.findById(id)
    var jsonObject = {

      if (device != null) {
        return toJson(
          Map(
            "device" ->
              toJson(
                Map(
                  "mdi" -> toJson(device.getMdi()),
                  "name" -> toJson(device.getName())))))
      } else {
        return toJson("")
      }
    }
    return jsonObject
  }

  def getDevice(deviceId: String) = Action {
    Ok(toJson(getJsonDeviceFromRepository(deviceId)))
  }

}