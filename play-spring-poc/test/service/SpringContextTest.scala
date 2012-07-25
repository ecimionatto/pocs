package service;
import org.hamcrest.CoreMatchers
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Assert
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.junit.runners.JUnit4

@RunWith(classOf[JUnit4])
class SpringContextTest {

  @Test
  def shouldStartSpringContext_AndGetDevice() {
    var factory: ClassPathXmlApplicationContext = SpringContext.applicationContext.asInstanceOf[ClassPathXmlApplicationContext]
    var deviceRepository = factory.getBean("deviceRepository")
    Assert.assertThat(deviceRepository, CoreMatchers.notNullValue())
  }

}
