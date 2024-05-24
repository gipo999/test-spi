package io.github.gipo999.spi.implementations;

import io.github.gipo999.spi.NamedService;
import io.github.gipo999.spi.interfaces.PrioritizedService003;

public class Ps003Custom implements PrioritizedService003 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }

  @Override
  public int getPriority() {
    return 1;
  }
}
