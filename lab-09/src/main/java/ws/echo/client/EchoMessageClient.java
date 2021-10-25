package ws.echo.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 09.01.14
 */
public class EchoMessageClient {
    public static void main(String[] args) throws Exception {

    }

    /**
     * Request class
     */
    @XmlRootElement(name = "echo", namespace = "http://echo.ws/")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class EchoRequest {
        @XmlElement(name = "arg0")
        protected String message;
        @XmlElement(name = "arg1")
        protected int count;
    }

    /**
     * Response class
     */
    @XmlRootElement(name = "echoResponse", namespace = "http://echo.ws/")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class EchoResponse {
        @XmlElement(name = "arg0")
        protected String message;
    }
}
