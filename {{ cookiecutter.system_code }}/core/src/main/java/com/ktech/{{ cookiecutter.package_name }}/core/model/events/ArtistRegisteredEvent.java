package com.ktech.{{ cookiecutter.package_name }}.core.model.events;

import com.ktech.{{ cookiecutter.package_name }}.core.model.entities.Artist;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Isni;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistRegisteredEvent {
  private Isni isni;
  private Name name;

  public static ArtistRegisteredEvent fromEntity(Artist entity) {
    return ArtistRegisteredEvent.builder().isni(entity.getIsni()).name(entity.getName()).build();
  }
}
