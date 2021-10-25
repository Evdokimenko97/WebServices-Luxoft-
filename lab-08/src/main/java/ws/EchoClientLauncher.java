package ws;

import ws.echo.Echo;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
public class EchoClientLauncher {
    public static void main(String[] args) throws Exception {
        final Service service = Service.create(
                new URL("http://localhost:8080/ws/echo"),
                new QName("http://echo.ws/","EchoService"));
        final Echo echo = service.getPort(new QName("http://echo.ws/","EchoPort"), Echo.class);

        final Holder<String> message = new Holder<String>("A");
        echo.echo(message, 3);

        System.out.println(message.value);
    }
}
