package net.syn3rgy.core.spi.implementations;

import net.syn3rgy.core.spi.NamedService;
import net.syn3rgy.core.spi.interfaces.PrioritizedService001;

public class Ps001Standard implements PrioritizedService001 {

    @Override
    public String getServiceImplementationName () {
        return NamedService.IMPL_STANDARD_NAME;
    }

}
