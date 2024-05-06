package net.syn3rgy.core.spi.implementations;

import net.syn3rgy.core.spi.NamedService;
import net.syn3rgy.core.spi.interfaces.NamedService001;

public class Ns001Standard implements NamedService001 {

    @Override
    public String getServiceImplementationName () {
        return NamedService.IMPL_STANDARD_NAME;
    }

}
