package com.ktech.{{ cookiecutter.package_name }}.api;

import com.ktech.{{ cookiecutter.package_name }}.api.controllers.ArtistController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationSmokeTest {
  @Autowired
  ArtistController artistController;

  @Test
  void contextLoads() {
    Assertions.assertThat(artistController).isNotNull();
  }
}
