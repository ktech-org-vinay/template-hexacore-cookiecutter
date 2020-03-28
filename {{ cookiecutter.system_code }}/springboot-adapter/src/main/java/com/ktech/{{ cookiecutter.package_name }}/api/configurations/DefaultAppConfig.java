package com.ktech.{{ cookiecutter.package_name }}.api.configurations;

import com.ktech.{{ cookiecutter.package_name }}.api.services.ArtistService;
import com.ktech.{{ cookiecutter.package_name }}.core.ports.repositories.ArtistSavedEventPublisher;
import com.ktech.{{ cookiecutter.package_name }}.core.ports.repositories.ArtistWriter;
import com.ktech.{{ cookiecutter.package_name }}.couchdb.CouchDbArtistWriter;
import com.ktech.{{ cookiecutter.package_name }}.kafka.KafkaAdapterConfig;
import org.ektorp.CouchDbConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(KafkaAdapterConfig.class)
public class DefaultAppConfig {


  @Bean
  public ArtistService artistService(ArtistWriter artistWriter, ArtistSavedEventPublisher artistSavedEventPublisher) {
    return new ArtistService(artistWriter, artistSavedEventPublisher);
  }

  @Bean
  public ArtistWriter artistWriter(CouchDbConnector couchDbConnector) {
    return new CouchDbArtistWriter(couchDbConnector);
  }


}
