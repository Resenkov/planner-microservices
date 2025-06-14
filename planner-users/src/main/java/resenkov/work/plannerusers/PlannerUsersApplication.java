package resenkov.work.plannerusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"resenkov.work"})
@EntityScan("resenkov.work.plannerusers.entity")
@EnableJpaRepositories("resenkov.work.plannerusers.repository")
public class PlannerUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannerUsersApplication.class, args);
	}

}
