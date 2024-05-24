package io.github.gipo999.spi.filters;

import io.github.gipo999.spi.ImplementationParams;
import io.github.gipo999.spi.ImplementationsFilter;
import io.github.gipo999.spi.NamedService;
import io.github.gipo999.spi.PrioritizedService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CustomFilter implements ImplementationsFilter {

  @Override
  public <T extends PrioritizedService> List<T> filter(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {
    if (implementations == null || implementations.isEmpty() || params.isEmpty()) {
      return Collections.emptyList();
    }
    String key = params.get().getCacheKey();
    for (T impl : implementations) {
      String implName = impl.getServiceImplementationName();
      if (NamedService.IMPL_STANDARD_NAME.equals(implName) && "s".equals(key)) {
        return Collections.unmodifiableList(List.of(impl));
      } else if (NamedService.IMPL_CUSTOM_NAME.equals(implName) && "c".equals(key)) {
        return Collections.unmodifiableList(List.of(impl));
      }
    }

    return Collections.emptyList();
  }
}
