package io.github.gipo999.spi.implementations;

import io.github.gipo999.spi.NamedService;
import io.github.gipo999.spi.interfaces.PrioritizedService001;

public class Ps001Standard implements PrioritizedService001 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_STANDARD_NAME;
  }
}
