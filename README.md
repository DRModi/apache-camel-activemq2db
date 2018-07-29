http://camel.apache.org/running-camel-standalone-and-have-it-keep-running.html

Running Camel standalone and have it keep running
If you are using Camel as a standalone Java application, then Camel provides a Main class you can reuse to more easily boot up Camel and keep it running until the JVM terminates, for example when pressing ctrl + c.

The Main class is provided in the following two components

camel-core JAR in the org.apache.camel.Main class (requires Camel 2.6)
camel-spring JAR in the org.apache.camel.spring.Main class
Using camel-core JAR example
Available as of Camel 2.6

The following example shows how you can create your main class, named MainExample and use the Main class from Camel.


Error rendering macro 'code': Invalid value specified for parameter 'java.lang.NullPointerException'


============================================================================

public class MainExample {

    private Main main;

    public static void main(String[] args) throws Exception {
        MainExample example = new MainExample();
        example.boot();
    }

============================================================================

    public void boot() throws Exception {
        // create a Main instance
        main = new Main();
        // bind MyBean into the registry
        main.bind("foo", new MyBean());
        // add routes
        main.addRouteBuilder(new MyRouteBuilder());
        // add event listener
        main.addMainListener(new Events());
        // set the properties from a file
        main.setPropertyPlaceholderLocations("example.properties");
        // run until you terminate the JVM
        System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
        main.run();
    }

============================================================================

    private static class MyRouteBuilder extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("timer:foo?delay={{millisecs}}")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("Invoked timer at " + new Date());
                    }
                })
                .bean("foo");
        }
    }


    public static class MyBean {
        public void callMe() {
            System.out.println("MyBean.callMe method has been called");
        }
    }

    public static class Events extends MainListenerSupport {

        @Override
        public void afterStart(MainSupport main) {
            System.out.println("MainExample with Camel is now started!");
        }

        @Override
        public void beforeStop(MainSupport main) {
            System.out.println("MainExample with Camel is now being stopped!");
        }
    }
}
Using camel-spring JAR example
This would be similar to the camel-core JAR example, however you would use the Main class from the org.apache.camel.spring package.
The Main class from camel-spring JAR has many more options and is prepared for booting Camel from Spring XML files.

============================================================================

