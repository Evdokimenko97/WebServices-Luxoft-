package ws;

import ws.echo.EchoImpl;

import javax.xml.ws.Endpoint;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
public class EchoServiceLauncher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/echo", new EchoImpl());
    }
}
