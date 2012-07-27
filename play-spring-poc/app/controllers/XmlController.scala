package controllers
import com.opeak.poc.model.Device
import com.opeak.poc.repository.DeviceRepository
import com.sun.jersey.api.json.JSONJAXBContext
import play.api.mvc.Action
import play.api.mvc.Controller
import service.SpringContext
import support.JaxbSupport
import support.JaxbXml
import javax.xml.bind.JAXBContext
import java.io.ByteArrayOutputStream
import play.api.mvc.Codec
import javax.xml.bind.Marshaller
import play.api.mvc.SimpleResult
import play.api.mvc.ResponseHeader
import play.api.libs.iteratee.Enumerator
import javax.ws.rs.core.MediaType

object XmlController extends Controller with JaxbSupport {
  implicit val context = new JSONJAXBContext(classOf[Device])
  val deviceRepository = SpringContext.applicationContext.getBean("deviceRepository").asInstanceOf[DeviceRepository]

  def createDevice = Action(jaxb.parse.xml[Device]) { request =>
    val device: Device = request.body
    deviceRepository.createDevice(device)
    Ok
  }

  def getDevice(id: String) = Action {
    val device: Device = deviceRepository.findById(id)
    Ok(JaxbXml(device))
  }

  def getAll = Action {
    val devices = deviceRepository.findAll().toArray().toSeq
    val xmlDevices = new StringBuilder("<devices>")
    var marshaller = context.createMarshaller()

    devices.foreach(d =>
      xmlDevices.append(new String(toXmlBytes(d))))

    SimpleResult(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> MediaType.APPLICATION_XML)),
      body = Enumerator(xmlDevices.append("</devices>").toString()))
  }

  def toXmlBytes(obj: AnyRef)(implicit codec: Codec, context: JAXBContext): Array[Byte] = {
    val marshaller = context.createMarshaller()
    val stream = new ByteArrayOutputStream

    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
    marshaller.setProperty(Marshaller.JAXB_ENCODING, codec.charset)
    marshaller.marshal(obj, stream)

    stream.toByteArray
  }

}