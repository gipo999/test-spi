package net.syn3rgy.core.spi.implementations;

import net.syn3rgy.core.spi.NamedService;
import net.syn3rgy.core.spi.interfaces.PrioritizedService003;

public class Ps003Custom implements PrioritizedService003 {

    @Override
    public String getServiceImplementationName () {
        return NamedService.IMPL_CUSTOM_NAME;
    }

    @Override
    public int getPriority () {
        return 1;
    }

}
