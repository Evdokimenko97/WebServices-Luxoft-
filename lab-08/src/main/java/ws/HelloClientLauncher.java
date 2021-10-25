package ws;

import ws.hello.Hello;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
public class HelloClientLauncher {
    public static void main(String[] args) throws Exception {
        final Service service = Service.create(
                new URL("http://localhost:8080/ws/hello"),
                new QName("http://hello.ws/","HelloService"));

        final Hello hello = service.getPort(new QName("http://hello.ws/","HelloPort"), Hello.class);
        System.out.println(hello.sayHello("John"));
    }
}
