package controllers
import scala.collection.JavaConversions.collectionAsScalaIterable

import com.opeak.poc.model.Device
import com.opeak.poc.repository.DeviceRepository

import models.DeviceTo
import play.api.data.Forms.nonEmptyText
import play.api.data.Form
import play.api.mvc.Action
import play.api.mvc.Controller
import service.SpringContext

object DeviceController extends Controller {

  val deviceRepository =
    SpringContext.applicationContext.getBean("deviceRepository").asInstanceOf[DeviceRepository]

  val deviceForm = Form(
    "name" -> nonEmptyText)

  def index = Action {
    Ok("Device Controller")
  }

  def mapDevicesToDeviceTos(devices: java.util.Collection[Device]): Set[DeviceTo] = {
    var deviceSet = devices.toSet[Device]
    deviceSet.map((d: Device) => new DeviceTo(d.getMdi(), d.getName())): Set[DeviceTo]
  }

  def devices = Action {
    val deviceTos = mapDevicesToDeviceTos(deviceRepository.findAll())
    Ok(views.html.index(deviceTos.toList, deviceForm))
  }

  def newDevice = Action { implicit request =>

    deviceForm.bindFromRequest().fold(
      errors => BadRequest(views.html.index(mapDevicesToDeviceTos(deviceRepository.findAll()).toList, deviceForm)),
      name => {
        val device = new Device()
        device.setName(name)
        deviceRepository.createDevice(device)
        Redirect(routes.DeviceController.devices())
      })

  }

  def deleteDevice(string: Long) = TODO

}