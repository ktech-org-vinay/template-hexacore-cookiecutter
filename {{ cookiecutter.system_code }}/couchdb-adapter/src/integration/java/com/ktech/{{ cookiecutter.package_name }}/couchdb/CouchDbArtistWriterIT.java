package com.ktech.{{ cookiecutter.package_name }}.couchdb;

import static com.ktech.{{ cookiecutter.package_name }}.couchdb.CouchDbFactory.DbCreationMode.CREATE_IF_NOT_EXIST;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.ktech.{{ cookiecutter.package_name }}.core.model.entities.Artist;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Isni;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Name;
import java.net.MalformedURLException;
import java.net.URL;
import org.ektorp.CouchDbConnector;
import org.junit.jupiter.api.Test;

public class CouchDbArtistWriterIT {
  private static final String DB_URL = "http://localhost:5984";
  private static final String DB_NAME = "{{cookiecutter.system_code}}db";

  @Test
  void givenValidConnectorAndArtist_whenSaving_shouldSave() throws MalformedURLException {
    Artist artist = new Artist(new Name("Apple", "Inc."), new Isni("US0378331005"));
    CouchDbConnector connector = CouchDbFactory.createConnector(new URL(DB_URL), DB_NAME, CREATE_IF_NOT_EXIST);

    assertThatCode(() -> new CouchDbArtistWriter(connector).saveArtist(artist))
        .doesNotThrowAnyException();
  }
}
