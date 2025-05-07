package resenkov.work.plannerutils.rest.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import resenkov.work.plannerutils.entity.User;


@Component
public class UserWebClientBuilder {

    private static final String baseUrlUser = "http://localhost:8765/planner-users/user/";
    private static final String baseUrlData = "http://localhost:8765/planner-todo/data/";

    public boolean userExists(Long userId) {

        try {

            User user = WebClient.create(baseUrlUser)
                    .post()
                    .uri("id")
                    .bodyValue(userId)
                    .retrieve()
                    .bodyToFlux(User.class)
                    .blockFirst(); // блокирует поток до получения 1й записи

            if (user != null) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public Flux<User> userExistsAsync(Long userId) {

        Flux<User> fluxUser = WebClient.create(baseUrlUser)
                .post()
                .uri("id")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(User.class);

        return fluxUser;

    }

    public Flux<Boolean> initUserData(Long userId) {

        Flux<Boolean> fluxUser = WebClient.create(baseUrlData)
                .post()
                .uri("init")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(Boolean.class);

        return fluxUser;

    }
}
