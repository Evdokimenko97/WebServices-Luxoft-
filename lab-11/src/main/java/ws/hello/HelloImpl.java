package ws.hello;

import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
@WebService(
        endpointInterface = "ws.hello.Hello",
        serviceName = "HelloService",
        portName = "HelloPort"
)
@HandlerChain(file = "/service-handlers.xml")
public class HelloImpl implements Hello {
    @Override
    public String sayHello(String name) throws HelloException {
        if (name == null || name.length() == 0) {
            throw new HelloException("Name cannot be NULL or empty!");
        }

        return String.format("Hello %1$s!", name);
    }
}
