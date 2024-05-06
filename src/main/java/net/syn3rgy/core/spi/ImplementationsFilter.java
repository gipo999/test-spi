package net.syn3rgy.core.spi;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface ImplementationsFilter {
    
    <T extends PrioritizedService> List<T> filter (Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params);

}