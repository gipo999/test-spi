package net.syn3rgy.core.spi;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.syn3rgy.core.spi.interfaces.NamedService001;
import net.syn3rgy.core.spi.interfaces.NamedService002;
import net.syn3rgy.core.spi.interfaces.NamedService003;
import net.syn3rgy.core.spi.interfaces.NamedService004;
import net.syn3rgy.core.spi.params.StdCustParams;
import net.syn3rgy.core.spi.selectors.CustomSelector;
import net.syn3rgy.core.spi.selectors.EmptySelector;
import org.junit.jupiter.api.Test;

class ImplementationTest {

  @Test
  void testStandardOnlyNoCache() {
    var named001 = Implementation.ofNew(NamedService001.class);
    assertTrue(named001.isPresent());
    if (named001.isPresent()) {
      assertTrue(named001.get().getClass().getName().endsWith("Standard"));
      var named001_clone = Implementation.ofNew(NamedService001.class);
      assertTrue(named001_clone.isPresent());
      assertNotSame(named001.get(), named001_clone.get());
    }
  }

  @Test
  void testCustomOnlyNoCache() {
    var named002 = Implementation.ofNew(NamedService002.class);
    assertTrue(named002.isPresent());
    if (named002.isPresent()) {
      assertTrue(named002.get().getClass().getName().endsWith("Custom"));
      var named002_clone = Implementation.ofNew(NamedService002.class);
      assertTrue(named002_clone.isPresent());
      assertNotSame(named002.get(), named002_clone.get());
    }
  }

  @Test
  void testBothOnlyNoCache() {
    var named003 = Implementation.ofNew(NamedService003.class);
    assertTrue(named003.isPresent());
    if (named003.isPresent()) {
      assertTrue(named003.get().getClass().getName().endsWith("Custom"));
      var named003_clone = Implementation.ofNew(NamedService003.class);
      assertTrue(named003_clone.isPresent());
      assertNotSame(named003.get(), named003_clone.get());
    }
  }

  @Test
  void testNoPresenceNoCache() {
    var named004 = Implementation.ofNew(NamedService004.class);
    assertTrue(named004.isEmpty());
  }

  @Test
  void testEmptySelectorNoCache() {
    var named001 = Implementation.ofNew(NamedService001.class, new EmptySelector());
    assertTrue(named001.isEmpty());
  }

  @Test
  void testGlobalEmptySelectorNoCache() {
    Implementation.setGlobalSelector(new EmptySelector());
    var named001 = Implementation.ofNew(NamedService001.class);
    assertTrue(named001.isEmpty());
    Implementation.setGlobalSelector(new DefaultImplementationSelector());
  }

  @Test
  void testCustomSelectorWithParamsNoCache() {
    var named003_std =
        Implementation.ofNew(NamedService003.class, new CustomSelector(), new StdCustParams("s"));
    assertTrue(named003_std.isPresent());
    assertTrue(named003_std.get().getClass().getName().endsWith("Standard"));
    var named003_std_clone =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("s"));
    assertNotSame(named003_std.get(), named003_std_clone.get());

    var named003_cus =
        Implementation.ofNew(NamedService003.class, new CustomSelector(), new StdCustParams("c"));
    assertTrue(named003_cus.isPresent());
    assertTrue(named003_cus.get().getClass().getName().endsWith("Custom"));
    var named003_cus_clone =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("c"));
    assertNotSame(named003_cus.get(), named003_cus_clone.get());

    var named003_no =
        Implementation.ofNew(NamedService003.class, new CustomSelector(), new StdCustParams("asd"));
    assertTrue(named003_no.isEmpty());
  }

  @Test
  void testStandardOnlyWithCache() {
    var named001 = Implementation.of(NamedService001.class);
    assertTrue(named001.isPresent());
    if (named001.isPresent()) {
      assertTrue(named001.get().getClass().getName().endsWith("Standard"));
      var named001_clone = Implementation.of(NamedService001.class);
      assertTrue(named001_clone.isPresent());
      assertSame(named001.get(), named001_clone.get());
    }
  }

  @Test
  void testCustomOnlyWithCache() {
    var named002 = Implementation.of(NamedService002.class);
    assertTrue(named002.isPresent());
    if (named002.isPresent()) {
      assertTrue(named002.get().getClass().getName().endsWith("Custom"));
      var named002_clone = Implementation.of(NamedService002.class);
      assertTrue(named002_clone.isPresent());
      assertSame(named002.get(), named002_clone.get());
    }
  }

  @Test
  void testBothOnlyWithCache() {
    var named003 = Implementation.of(NamedService003.class);
    assertTrue(named003.isPresent());
    if (named003.isPresent()) {
      assertTrue(named003.get().getClass().getName().endsWith("Custom"));
      var named003_clone = Implementation.of(NamedService003.class);
      assertTrue(named003_clone.isPresent());
      assertSame(named003.get(), named003_clone.get());
    }
  }

  @Test
  void testNoPresenceWithCache() {
    var named004 = Implementation.of(NamedService004.class);
    assertTrue(named004.isEmpty());
  }

  @Test
  void testEmptySelectorWithCache() {
    var named001 = Implementation.of(NamedService001.class, new EmptySelector());
    assertTrue(named001.isEmpty());
  }

  @Test
  void testGlobalEmptySelectorWithCache() {
    Implementation.setGlobalSelector(new EmptySelector());
    var named001 = Implementation.of(NamedService001.class);
    assertTrue(named001.isEmpty());
    Implementation.setGlobalSelector(new DefaultImplementationSelector());
  }

  @Test
  void testCustomSelectorWithParamsWithCache() {
    var named003_std =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("s"));
    assertTrue(named003_std.isPresent());
    assertTrue(named003_std.get().getClass().getName().endsWith("Standard"));
    var named003_std_clone =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("s"));
    assertSame(named003_std.get(), named003_std_clone.get());

    var named003_cus =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("c"));
    assertTrue(named003_cus.isPresent());
    assertTrue(named003_cus.get().getClass().getName().endsWith("Custom"));
    var named003_cus_clone =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("c"));
    assertSame(named003_cus.get(), named003_cus_clone.get());

    var named003_no =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("asd"));
    assertTrue(named003_no.isEmpty());
  }
}
