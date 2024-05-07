package net.syn3rgy.core.spi;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface ImplementationSelector {

  <T extends NamedService> Optional<T> choose(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params);
} // ServiceProviderChooser
