package net.syn3rgy.core.spi;

@FunctionalInterface
public interface NamedService {

  public static final String IMPL_STANDARD_NAME = "standard";
  public static final String IMPL_CUSTOM_NAME = "custom";

  String getServiceImplementationName();
}
