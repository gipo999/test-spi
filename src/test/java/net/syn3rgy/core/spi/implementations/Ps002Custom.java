package net.syn3rgy.core.spi.implementations;

import net.syn3rgy.core.spi.NamedService;
import net.syn3rgy.core.spi.interfaces.PrioritizedService002;

public class Ps002Custom implements PrioritizedService002 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }
}
