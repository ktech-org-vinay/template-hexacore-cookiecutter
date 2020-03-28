package com.ktech.{{ cookiecutter.package_name }}.core.model.entities;

import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Isni;
import com.ktech.{{ cookiecutter.package_name }}.core.model.values.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Artist {
  Name name;
  final Isni isni;
}
