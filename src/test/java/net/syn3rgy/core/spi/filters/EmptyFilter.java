package net.syn3rgy.core.spi.filters;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import net.syn3rgy.core.spi.ImplementationParams;
import net.syn3rgy.core.spi.ImplementationsFilter;
import net.syn3rgy.core.spi.PrioritizedService;

public class EmptyFilter implements ImplementationsFilter {

    @Override
    public <T extends PrioritizedService> List<T> filter (Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {
        return Collections.emptyList ();
    }


}
