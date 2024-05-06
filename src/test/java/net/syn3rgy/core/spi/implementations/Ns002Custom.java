package net.syn3rgy.core.spi.implementations;

import net.syn3rgy.core.spi.NamedService;
import net.syn3rgy.core.spi.interfaces.NamedService002;

public class Ns002Custom implements NamedService002 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }
}
