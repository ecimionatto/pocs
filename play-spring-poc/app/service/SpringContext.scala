package service
import org.springframework.context.support.ClassPathXmlApplicationContext

object SpringContext {

  val applicationContext: ClassPathXmlApplicationContext =
    new ClassPathXmlApplicationContext("application-context.xml").asInstanceOf[ClassPathXmlApplicationContext]

}