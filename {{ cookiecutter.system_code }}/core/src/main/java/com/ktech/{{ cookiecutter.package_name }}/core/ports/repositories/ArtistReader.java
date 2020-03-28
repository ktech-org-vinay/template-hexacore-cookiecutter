package com.ktech.{{ cookiecutter.package_name }}.core.ports.repositories;

import com.ktech.{{ cookiecutter.package_name }}.core.model.entities.Artist;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Isni;
import java.io.IOException;

public interface ArtistReader {
  Artist findArtistByIdentifier(Isni isni) throws IOException;
}
