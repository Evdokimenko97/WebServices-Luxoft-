package ws;

import ws.hello.HelloImpl;

import javax.xml.ws.Endpoint;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.01.14
 */
public class HelloServiceLauncher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/hello", new HelloImpl());
    }
}
