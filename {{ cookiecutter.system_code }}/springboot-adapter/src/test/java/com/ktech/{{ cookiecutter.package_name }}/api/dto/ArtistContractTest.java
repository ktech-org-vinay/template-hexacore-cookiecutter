package com.ktech.{{ cookiecutter.package_name }}.api.dto;

import com.ktech.{{ cookiecutter.package_name }}.core.model.entities.Artist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class ArtistContractTest {
  @Test
  public void givenValidArtistDTOFields_whenConverterIsInvoked_shouldReturnValidEntity() {
    ArtistContract artistContract = new ArtistContract("Apple", "Inc.", "US0378331005");

    Assertions.assertThat(artistContract.toEntity()).isNotNull().isInstanceOf(Artist.class);
  }
}