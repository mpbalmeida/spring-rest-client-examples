package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domains.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ApiService {

    List<User> listUsers(Integer limit);

    Flux<User> listUsers(Mono<Integer> limit);
}
