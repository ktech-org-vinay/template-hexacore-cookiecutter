package com.ktech.{{ cookiecutter.package_name }}.api.services;

import com.ktech.{{ cookiecutter.package_name }}.api.dto.ArtistContract;
import com.ktech.{{ cookiecutter.package_name }}.core.model.entities.Artist;
import com.ktech.{{ cookiecutter.package_name }}.core.model.events.ArtistRegisteredEvent;
import com.ktech.{{ cookiecutter.package_name }}.core.ports.repositories.ArtistSavedEventPublisher;
import com.ktech.{{ cookiecutter.package_name }}.core.ports.repositories.ArtistWriter;
import java.io.IOException;

public class ArtistService {
  private ArtistWriter artistWriter;
  private ArtistSavedEventPublisher artistSavedEventPublisher;

  public ArtistService(
      ArtistWriter artistWriter, ArtistSavedEventPublisher artistSavedEventPublisher) {
    this.artistWriter = artistWriter;
    this.artistSavedEventPublisher = artistSavedEventPublisher;
  }

  public void save(ArtistContract artistContract) throws IOException {
    Artist artist = artistContract.toEntity();
    artistWriter.saveArtist(artist);
    artistSavedEventPublisher.send(ArtistRegisteredEvent.fromEntity(artist));
  }
}
