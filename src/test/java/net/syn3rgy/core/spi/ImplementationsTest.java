package net.syn3rgy.core.spi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.syn3rgy.core.spi.filters.CustomFilter;
import net.syn3rgy.core.spi.filters.EmptyFilter;
import net.syn3rgy.core.spi.interfaces.PrioritizedService001;
import net.syn3rgy.core.spi.interfaces.PrioritizedService002;
import net.syn3rgy.core.spi.interfaces.PrioritizedService003;
import net.syn3rgy.core.spi.interfaces.PrioritizedService004;
import net.syn3rgy.core.spi.params.StdCustParams;

class ImplementationsTest {
// TODO sostituire named -> prioritized
    @Test
    void testStandardOnlyNoCache () {
        var named001 = Implementations.ofNew (PrioritizedService001.class);
        assertFalse (named001.isEmpty ());
        assertEquals (1, named001.size ());
        assertTrue (named001.get (0).getClass ().getName ().endsWith ("Standard"));
        var named001_clone = Implementations.ofNew (PrioritizedService001.class);
        assertFalse (named001_clone.isEmpty ());
        assertEquals (1, named001_clone.size ());
        assertNotSame (named001.get (0), named001_clone.get (0));
    }
    
    @Test
    void testCustomOnlyNoCache () {
        var named002 = Implementations.ofNew (PrioritizedService002.class);
        assertFalse (named002.isEmpty ());
        assertEquals (1, named002.size ());
        assertTrue (named002.get (0).getClass ().getName ().endsWith ("Custom"));
        var named002_clone = Implementations.ofNew (PrioritizedService002.class);
        assertFalse (named002_clone.isEmpty ());
        assertEquals (1, named002_clone.size ());
        assertNotSame (named002.get (0), named002_clone.get (0));
    }
    
    @Test
    void testBothOnlyNoCache () {
        var named003 = Implementations.ofNew (PrioritizedService003.class);
        assertFalse (named003.isEmpty ());
        assertEquals (2, named003.size ());
        assertTrue (named003.get (0).getClass ().getName ().endsWith ("Custom"));
        assertTrue (named003.get (1).getClass ().getName ().endsWith ("Standard"));
        var named003_clone = Implementations.ofNew (PrioritizedService003.class);
        assertFalse (named003_clone.isEmpty ());
        assertEquals (2, named003_clone.size ());
        assertNotSame (named003.get (0), named003_clone.get (0));
        assertNotSame (named003.get (1), named003_clone.get (1));
    }
    
    @Test
    void testNoPresenceNoCache () {
        var named004 = Implementations.ofNew (PrioritizedService004.class);
        assertTrue (named004.isEmpty ());
    }
    
    @Test
    void testEmptySelectorNoCache () {
        var named001 = Implementations.ofNew (PrioritizedService001.class, new EmptyFilter ());
        assertTrue (named001.isEmpty ());
    }
    
    @Test
    void testCustomSelectorWithParamsNoCache () {
        var named003_std = Implementations.ofNew (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("s"));
        assertEquals (1, named003_std.size ());
        assertTrue (named003_std.get (0).getClass ().getName ().endsWith ("Standard"));
        var named003_std_clone = Implementations.ofNew (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("s"));
        assertEquals (1, named003_std_clone.size ());
        assertTrue (named003_std_clone.get (0).getClass ().getName ().endsWith ("Standard"));
        assertNotSame (named003_std.get (0), named003_std_clone.get (0));
        
        var named003_cus = Implementations.ofNew (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("c"));
        assertEquals (1, named003_cus.size ());
        assertTrue (named003_cus.get (0).getClass ().getName ().endsWith ("Custom"));
        var named003_cus_clone = Implementations.ofNew (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("c"));
        assertEquals (1, named003_cus_clone.size ());
        assertTrue (named003_cus_clone.get (0).getClass ().getName ().endsWith ("Custom"));
        assertNotSame (named003_cus.get (0), named003_cus_clone.get (0));
        
        var named003_no = Implementations.ofNew (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("asd"));
        assertTrue (named003_no.isEmpty ());
    }
    
    @Test
    void testStandardOnlyWithCache () {
        var named001 = Implementations.of (PrioritizedService001.class);
        assertFalse (named001.isEmpty ());
        assertEquals (1, named001.size ());
        assertTrue (named001.get (0).getClass ().getName ().endsWith ("Standard"));
        var named001_clone = Implementations.of (PrioritizedService001.class);
        assertFalse (named001_clone.isEmpty ());
        assertEquals (1, named001_clone.size ());
        assertSame (named001.get (0), named001_clone.get (0));
    }
    
    @Test
    void testCustomOnlyWithCache () {
        var named002 = Implementations.of (PrioritizedService002.class);
        assertFalse (named002.isEmpty ());
        assertEquals (1, named002.size ());
        assertTrue (named002.get (0).getClass ().getName ().endsWith ("Custom"));
        var named002_clone = Implementations.of (PrioritizedService002.class);
        assertFalse (named002_clone.isEmpty ());
        assertEquals (1, named002_clone.size ());
        assertSame (named002.get (0), named002_clone.get (0));
    }
    
    @Test
    void testBothOnlyWithCache () {
        var named003 = Implementations.of (PrioritizedService003.class);
        assertFalse (named003.isEmpty ());
        assertEquals (2, named003.size ());
        assertTrue (named003.get (0).getClass ().getName ().endsWith ("Custom"));
        assertTrue (named003.get (1).getClass ().getName ().endsWith ("Standard"));
        var named003_clone = Implementations.of (PrioritizedService003.class);
        assertFalse (named003_clone.isEmpty ());
        assertEquals (2, named003_clone.size ());
        assertSame (named003.get (0), named003_clone.get (0));
        assertSame (named003.get (1), named003_clone.get (1));
    }
    
    @Test
    void testNoPresenceWithCache () {
        var named004 = Implementations.of (PrioritizedService004.class);
        assertTrue (named004.isEmpty ());
    }
    
    @Test
    void testEmptySelectorWithCache () {
        var named001 = Implementations.of (PrioritizedService001.class, new EmptyFilter ());
        assertTrue (named001.isEmpty ());
    }
    
    @Test
    void testCustomSelectorWithParamsWithCache () {
        var named003_std = Implementations.of (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("s"));
        assertEquals (1, named003_std.size ());
        assertTrue (named003_std.get (0).getClass ().getName ().endsWith ("Standard"));
        var named003_std_clone = Implementations.of (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("s"));
        assertEquals (1, named003_std_clone.size ());
        assertTrue (named003_std_clone.get (0).getClass ().getName ().endsWith ("Standard"));
        assertSame (named003_std.get (0), named003_std_clone.get (0));
        
        var named003_cus = Implementations.of (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("c"));
        assertEquals (1, named003_cus.size ());
        assertTrue (named003_cus.get (0).getClass ().getName ().endsWith ("Custom"));
        var named003_cus_clone = Implementations.of (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("c"));
        assertEquals (1, named003_cus_clone.size ());
        assertTrue (named003_cus_clone.get (0).getClass ().getName ().endsWith ("Custom"));
        assertSame (named003_cus.get (0), named003_cus_clone.get (0));
        
        var named003_no = Implementations.of (PrioritizedService003.class, new CustomFilter (), new StdCustParams ("asd"));
        assertTrue (named003_no.isEmpty ());
    }

}
