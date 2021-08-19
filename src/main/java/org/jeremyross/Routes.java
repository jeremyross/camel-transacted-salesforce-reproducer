package org.jeremyross;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Routes extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        // Calling route has a salesforce operation (required to reproduce)
        from("direct:route1")
            .to("salesforce:query?rawPayload=true&sObjectQuery=Select Id From Contact Limit 1")
            .to("direct:route2");

        // any salesforce operation in this route will time out
        from("direct:route2")
            .transacted()
            .to("salesforce:query?rawPayload=true&sObjectQuery=Select Id From Contact Limit 1");
    }
}
