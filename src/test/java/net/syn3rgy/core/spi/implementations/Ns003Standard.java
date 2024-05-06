package net.syn3rgy.core.spi.implementations;

import net.syn3rgy.core.spi.NamedService;
import net.syn3rgy.core.spi.interfaces.NamedService003;

public class Ns003Standard implements NamedService003 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_STANDARD_NAME;
  }
}
