package com.ktech.{{ cookiecutter.package_name }}.core.ports.repositories;

import com.ktech.{{ cookiecutter.package_name }}.core.model.events.ArtistRegisteredEvent;

public interface ArtistSavedEventPublisher {
  void send(ArtistRegisteredEvent artistSavedEvent);
}
