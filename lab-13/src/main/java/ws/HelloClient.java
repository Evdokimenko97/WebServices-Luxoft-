package ws;

import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import ws.hello.Hello;
import ws.util.PasswordProvider;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 13.01.14
 */
public class HelloClient {
    public static void main(String[] args) throws Exception {
        final URL wsdlLocation = HelloClient.class.getResource("/hello.wsdl");

        final Service service = Service.create(wsdlLocation, new QName("http://hello.ws/", "HelloService"));
        final Hello hello = service.getPort(new QName("http://hello.ws/", "HelloPort"), Hello.class);

        initWSS(hello);

        System.out.println(hello.sayHello("John"));
    }


    private static void initWSS(final Hello hello) {

    }
}

