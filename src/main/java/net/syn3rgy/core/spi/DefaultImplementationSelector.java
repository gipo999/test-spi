package net.syn3rgy.core.spi;

import java.util.List;
import java.util.Optional;

public class DefaultImplementationSelector implements ImplementationSelector {

  @Override
  public <T extends NamedService> Optional<T> choose(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {
    if (implementations == null || implementations.isEmpty()) {
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
    if (custom != null) {
      return Optional.of(custom);
    } else if (standard != null) {
      return Optional.of(standard);
    }
    return Optional.empty();
  }
}
