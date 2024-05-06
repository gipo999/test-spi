package net.syn3rgy.core.spi.implementations;

import net.syn3rgy.core.spi.NamedService;
import net.syn3rgy.core.spi.interfaces.PrioritizedService003;

public class Ps003Standard implements PrioritizedService003 {

    @Override
    public String getServiceImplementationName () {
        return NamedService.IMPL_STANDARD_NAME;
    }

}
