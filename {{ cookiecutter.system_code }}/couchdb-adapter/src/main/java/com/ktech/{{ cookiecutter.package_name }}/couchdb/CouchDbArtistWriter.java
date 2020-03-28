package com.ktech.{{ cookiecutter.package_name }}.couchdb;

import com.ktech.{{ cookiecutter.package_name }}.core.model.entities.Artist;
import com.ktech.{{ cookiecutter.package_name }}.core.ports.repositories.ArtistWriter;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.ektorp.CouchDbConnector;

@AllArgsConstructor
public class CouchDbArtistWriter implements ArtistWriter {
  private CouchDbConnector connector;

  @Override
  public void saveArtist(Artist artist) {
    Map<String, Object> artistMap = new HashMap<>();
    String id = artist.getIsni().toString();
    artistMap.put("_id", id);
    artistMap.put("artist", artist);

    if (connector.contains(id)) {
      artistMap.put("_rev", connector.getCurrentRevision(id));
    }

    connector.update(artistMap);
  }
}
