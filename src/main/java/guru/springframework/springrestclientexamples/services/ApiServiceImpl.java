package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domains.User;
import guru.springframework.api.domains.UserData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private final RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> listUsers(Integer limit) {

        UserData userData = restTemplate.getForObject("http://private-anon-a4e9684223-apifaketory.apiary-mock.com/api/user?limit=" + limit, UserData.class);
        return userData.getData();
    }
}
