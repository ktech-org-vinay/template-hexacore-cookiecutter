package com.ktech.{{ cookiecutter.package_name }}.kafka;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.ktech.{{ cookiecutter.package_name }}.core.model.events.ArtistRegisteredEvent;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Isni;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Name;
import com.ktech.{{ cookiecutter.package_name }}.core.ports.repositories.ArtistSavedEventPublisher;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {KafkaAdapterConfig.class})
@EnableAutoConfiguration
public class DefaultArtistSavedEventReceiverIT {
  @Autowired
  private ArtistSavedEventPublisher publisher;
  @Autowired
  DefaultArtistSavedEventReceiver receiver;

  @Test
  public void givenArtistSavedEventReceiverListening_whenArtistSavedEventIsSent_shouldReceiveEvent() {
    assertThat(receiver.getLatch().getCount()).isEqualTo(1);

    ArtistRegisteredEvent event =
        ArtistRegisteredEvent.builder()
            .name(new Name("Apple", ", Inc."))
            .isni(new Isni("98989898"))
            .build();
    assertThatCode(() -> publisher.send(event)).doesNotThrowAnyException();
    assertThatCode(() -> receiver.getLatch().await(2, TimeUnit.SECONDS)).doesNotThrowAnyException();
  }
}
