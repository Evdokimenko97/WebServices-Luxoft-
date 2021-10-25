package ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ws.hello.Hello;
import ws.hello.HelloFault_Exception;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 13.01.14
 */
public class HelloClient {

    private Hello hello;

    public void setClient(Hello hello) {
        this.hello = hello;
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:META-INF/cxf/cxf.xml",
                "classpath:client-context.xml");
        HelloClient me = context.getBean(HelloClient.class);
        me.run();
    }

    private void run() throws HelloFault_Exception {
        System.out.println(hello.sayHello("John"));
    }
}
