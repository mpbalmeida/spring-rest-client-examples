package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domains.User;

import java.util.List;

public interface ApiService {

    List<User> listUsers(Integer limit);
}
