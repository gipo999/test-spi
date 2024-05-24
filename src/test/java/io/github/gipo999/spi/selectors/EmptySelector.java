package io.github.gipo999.spi.selectors;

import io.github.gipo999.spi.ImplementationParams;
import io.github.gipo999.spi.ImplementationSelector;
import io.github.gipo999.spi.NamedService;
import java.util.List;
import java.util.Optional;

public class EmptySelector implements ImplementationSelector {

  @Override
  public <T extends NamedService> Optional<T> choose(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {
    return Optional.empty();
  }
}
