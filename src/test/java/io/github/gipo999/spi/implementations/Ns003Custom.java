package io.github.gipo999.spi.implementations;

import io.github.gipo999.spi.NamedService;
import io.github.gipo999.spi.interfaces.NamedService003;

public class Ns003Custom implements NamedService003 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }
}
