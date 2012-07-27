package controllers
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter

import com.opeak.poc.model.Device
import com.opeak.poc.repository.DeviceRepository
import com.sun.jersey.api.json.JSONJAXBContext

import javax.ws.rs.core.MediaType
import play.api.libs.iteratee.Enumerator
import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.mvc.Action
import play.api.mvc.Codec
import play.api.mvc.Controller
import play.api.mvc.ResponseHeader
import play.api.mvc.SimpleResult
import service.SpringContext

object JsonController extends Controller {

  var deviceRepository = SpringContext.applicationContext.getBean("deviceRepository").asInstanceOf[DeviceRepository]
  implicit val context = new JSONJAXBContext(classOf[Device])

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

  def getAll = Action {
    val devices = deviceRepository.findAll().toArray().toSeq
    val jsonDevices = new StringBuilder(""""device": [""")
    var marshaller = context.createMarshaller()

    devices.foreach(d =>
      jsonDevices.append(new String(toJsonBytes(d))))

    SimpleResult(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> MediaType.APPLICATION_JSON)),
      body = Enumerator(jsonDevices.append("]").toString()))
  }

  def toJsonBytes(obj: AnyRef)(implicit codec: Codec, context: JSONJAXBContext): Array[Byte] = {
    val marshaller = context.createJSONMarshaller()
    val stream = new ByteArrayOutputStream
    val writer = new OutputStreamWriter(stream, codec.charset)

    marshaller.marshallToJSON(obj, writer)

    writer.flush()
    stream.toByteArray
  }

}