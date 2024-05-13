package net.syn3rgy.core.spi;

@FunctionalInterface
public interface NamedService {

  String IMPL_STANDARD_NAME = "standard";
  String IMPL_CUSTOM_NAME = "custom";

  String getServiceImplementationName();
}
