package controllers
import org.hamcrest.CoreMatchers
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Assert
import com.opeak.poc.model.Device
import org.junit.runners.JUnit4

@RunWith(classOf[JUnit4])
class DeviceControllerSpecs {

  def createDeviceFix(mdi: String, name: String): Device = {
    val device = new Device()
    device.setMdi(mdi);
    device.setName(name);
    return device
  }

  @Test
  def shouldMapDeviceLIstToDeviceToList() {

    val devices = new java.util.ArrayList().asInstanceOf[java.util.Collection[Device]]

    val device1 = createDeviceFix("device mdi1", "device name 1");
    devices.add(device1)
    val device2 = createDeviceFix("device mdi2", "device name 2");
    devices.add(device2)
    val device3 = createDeviceFix("device mdi3", "device name 3");
    devices.add(device3)

    val deviceTos = DeviceController.mapDevicesToDeviceTos(devices)

    Assert.assertThat(deviceTos.size, CoreMatchers.equalTo(3))

  }

}