package io.study.deneb.web;

import io.study.deneb.mapper.UserMapper;
import io.study.deneb.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  private final UserRepository repository;
  private final UserMapper mapper;

  public UserController(UserRepository repository, UserMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }


  @GetMapping
  public UserDto findByEmail(@PathVariable String email) {
    return repository
      .findByEmail(email)
      .map(mapper::of)
      .orElseThrow(() -> new RuntimeException("user not found"));
  }

}
