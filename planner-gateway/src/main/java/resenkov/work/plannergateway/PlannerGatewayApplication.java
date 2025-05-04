package resenkov.work.plannergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PlannerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlannerGatewayApplication.class, args);
    }

}
