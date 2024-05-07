package net.syn3rgy.core.spi.selectors;

import java.util.List;
import java.util.Optional;
import net.syn3rgy.core.spi.ImplementationParams;
import net.syn3rgy.core.spi.ImplementationSelector;
import net.syn3rgy.core.spi.NamedService;

public class CustomSelector implements ImplementationSelector {

  @Override
  public <T extends NamedService> Optional<T> choose(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {
    if (implementations == null || implementations.isEmpty() || params.isEmpty()) {
      return Optional.empty();
    }
    T standard = null;
    T custom = null;
    for (T impl : implementations) {
      String implName = impl.getServiceImplementationName();
      if (NamedService.IMPL_STANDARD_NAME.equals(implName)) {
        standard = impl;
      } else if (NamedService.IMPL_CUSTOM_NAME.equals(implName)) {
        custom = impl;
      }
    }
    String key = params.get().getCacheKey();
    if (key.equals("s")) {
      return Optional.ofNullable(standard);
    } else if (key.equals("c")) {
      return Optional.ofNullable(custom);
    }
    return Optional.empty();
  }
}
