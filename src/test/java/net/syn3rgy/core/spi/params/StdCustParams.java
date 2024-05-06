package net.syn3rgy.core.spi.params;

import net.syn3rgy.core.spi.ImplementationParams;

public class StdCustParams implements ImplementationParams {

    private String param;
    
    public StdCustParams (String param) {
        this.param = param;
    }
    
    @Override
    public String getCacheKey () { 
        return param;
    }

}
