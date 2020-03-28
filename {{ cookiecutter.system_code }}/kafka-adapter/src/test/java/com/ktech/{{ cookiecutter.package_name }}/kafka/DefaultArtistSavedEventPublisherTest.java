package com.ktech.{{ cookiecutter.package_name }}.kafka;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.ktech.{{ cookiecutter.package_name }}.core.model.events.ArtistRegisteredEvent;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Isni;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Name;
import com.ktech.{{ cookiecutter.package_name }}.kafka.events.avro.ArtistSavedEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.concurrent.SettableListenableFuture;

@ExtendWith(MockitoExtension.class)
class DefaultArtistSavedEventPublisherTest {
  @Mock
  KafkaTemplate<String, ArtistSavedEvent> kafkaTemplate;
  @Mock
  KafkaAdapterProperties props;

  @InjectMocks
  DefaultArtistSavedEventPublisher publisher;

  @Test
  public void givenValidArtistSavedEvent_whenSendIsInvoked_shouldInvokeKafkaTemplate() {
    SettableListenableFuture<SendResult<String, ArtistSavedEvent>> future =
        new SettableListenableFuture<>();
    future.set(
        new SendResult<>(
            new ProducerRecord<>(
                "DUMMY",
                "DUMMY",
                ArtistSavedEvent.newBuilder()
                    .setName(
                        com.ktech.{{ cookiecutter.package_name }}.kafka.events.avro.Name.newBuilder()
                            .setGivenName("Apple")
                            .setSurName(",Inc.")
                            .build())
                    .setIsni(
                        com.ktech.{{ cookiecutter.package_name }}.kafka.events.avro.Isni.newBuilder()
                            .setCode("US0378331005")
                            .build())
                    .build()),
            null));

    // BEGIN can be moved to @Setup
    when(kafkaTemplate.send(any(GenericMessage.class))).thenReturn(future);

    KafkaAdapterProperties.Publisher publisherProps = new KafkaAdapterProperties.Publisher();
    publisherProps.setSyncSendTimeoutms(1000);
    when(props.getPublisher()).thenReturn(publisherProps);
    // END @Setup

    ArtistRegisteredEvent event =
        ArtistRegisteredEvent.builder()
            .isni(new Isni("US0378331005"))
            .name(new Name("Apple", ",Inc."))
            .build();

    publisher.send(event);
    verify(kafkaTemplate).send(any(GenericMessage.class));
  }
}
