package io.study.deneb.mapper;

import io.study.deneb.entity.User;
import io.study.deneb.web.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


  UserDto of(User user);

}
