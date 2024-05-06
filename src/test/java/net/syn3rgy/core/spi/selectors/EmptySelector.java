package net.syn3rgy.core.spi.selectors;

import java.util.List;
import java.util.Optional;
import net.syn3rgy.core.spi.ImplementationParams;
import net.syn3rgy.core.spi.ImplementationSelector;
import net.syn3rgy.core.spi.NamedService;

public class EmptySelector implements ImplementationSelector {

  @Override
  public <T extends NamedService> Optional<T> choose(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {
    return Optional.empty();
  }
}
