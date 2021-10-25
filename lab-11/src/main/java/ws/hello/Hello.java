package ws.hello;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
@WebService
public interface Hello {
    @WebMethod
    String sayHello(@WebParam String name) throws HelloException;
}
