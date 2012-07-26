package controllers
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api._
import service.SpringContext
import com.opeak.poc.repository.DeviceRepository
import play.api.libs.json.Json._

object JsonController extends Controller {

  val deviceRepository = SpringContext.applicationContext.getBean("deviceRepository").asInstanceOf[DeviceRepository]

  def getDevice(deviceId: String) = Action {
    val device = deviceRepository.findById(deviceId)
    if (device != null) {

      val jsonObject = toJson(
        Map(
          "device" ->
            toJson(
              Map(
                "mdi" -> toJson(device.getMdi()),
                "name" -> toJson(device.getName())))))
      Ok(toJson(jsonObject))

    } else NoContent
  }

}