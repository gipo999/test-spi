package net.syn3rgy.core.spi;

import java.util.List;
import java.util.Optional;

/** Interface for selecting an implementation of a service. */
@FunctionalInterface
public interface ImplementationSelector {

  /**
   * Chooses an implementation of a service.
   *
   * @param clazz the class of the service
   * @param implementations the implementations of the service
   * @param params the parameters for choosing the implementation
   * @return the chosen implementation
   * @param <T> the type of the service
   */
  <T extends NamedService> Optional<T> choose(

      /** The class of the service */
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params);
} // ServiceProviderChooser
