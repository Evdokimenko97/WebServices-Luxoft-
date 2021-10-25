package ws;

import ws.echo.EchoProviderImpl;

import javax.xml.ws.Endpoint;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
public class EchoServiceProviderLauncher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/echo", new EchoProviderImpl());
    }
}
