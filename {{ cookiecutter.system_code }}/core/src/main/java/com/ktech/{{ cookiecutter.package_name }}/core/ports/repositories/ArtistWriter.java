package com.ktech.{{ cookiecutter.package_name }}.core.ports.repositories;

import com.ktech.{{ cookiecutter.package_name }}.core.model.entities.Artist;
import java.io.IOException;

public interface ArtistWriter {
  void saveArtist(Artist artist) throws IOException;
}
