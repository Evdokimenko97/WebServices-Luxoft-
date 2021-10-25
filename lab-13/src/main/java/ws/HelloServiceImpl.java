package ws;

import ws.hello.Hello;
import ws.hello.HelloFault;
import ws.hello.HelloFault_Exception;

import javax.jws.WebService;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 13.01.14
 */
@WebService(
        endpointInterface = "ws.hello.Hello",
        serviceName = "HelloService",
        portName = "HelloPort")
public class HelloServiceImpl implements Hello {

    @Override
    public String sayHello(final String name) throws HelloFault_Exception {
        if (name == null || name.length() == 0) {
            final String message = "Name cannot be null or empty.";

            final HelloFault fault = new HelloFault();
            fault.setMessage(message);

            throw new HelloFault_Exception(message, fault);
        }

        return String.format("Hello %1$s!", name);
    }
}
