package io.github.gipo999.spi.implementations;

import io.github.gipo999.spi.NamedService;
import io.github.gipo999.spi.interfaces.NamedService002;

public class Ns002Custom implements NamedService002 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }
}
