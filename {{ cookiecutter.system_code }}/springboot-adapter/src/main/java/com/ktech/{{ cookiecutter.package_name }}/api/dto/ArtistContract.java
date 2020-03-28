package com.ktech.{{ cookiecutter.package_name }}.api.dto;

import com.ktech.{{ cookiecutter.package_name }}.core.model.entities.Artist;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Isni;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArtistContract {
  private String givenName;
  private String surname;
  private String isinCode;

  public Artist toEntity() {
    return new Artist(new Name(givenName, surname), new Isni(isinCode));
  }
}
