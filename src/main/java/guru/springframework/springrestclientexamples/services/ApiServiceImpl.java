package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domains.User;
import guru.springframework.api.domains.UserData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Override
    public List<User> listUsers(Integer limit) {

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .queryParam("limit", limit);

        UserData
                userData = restTemplate.getForObject(builder.toUriString(), UserData.class);
        return userData.getData();
    }

    @Override
    public Flux<User> listUsers(Mono<Integer> limit) {
        return WebClient.create(apiUrl)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("limit", limit.subscribe()).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(UserData.class))
                .flatMapIterable(UserData::getData);
    }
}
