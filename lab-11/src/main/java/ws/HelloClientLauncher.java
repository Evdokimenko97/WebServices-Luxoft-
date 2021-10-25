package ws;

import ws.hello.HelloService;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.01.14
 */
public class HelloClientLauncher {
    public static void main(final String[] args) throws Exception {
        final HelloService helloService = new HelloService();
        System.out.println(helloService.getHelloPort().sayHello("John"));
    }
}
