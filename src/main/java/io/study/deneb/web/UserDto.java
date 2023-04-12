package io.study.deneb.web;

import java.time.ZonedDateTime;
import java.util.UUID;

public record UserDto(
  UUID id,
  String name,
  String email,
  ZonedDateTime createdAt
) {
}
