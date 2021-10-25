package ws.hello.client;

import ws.hello.HelloService;
import ws.hello.SayHello;
import ws.hello.SayHelloResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBResult;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Response;
import javax.xml.ws.Service;
import java.util.concurrent.*;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 09.01.14
 */
public class HelloMessageAsyncClient {
}
