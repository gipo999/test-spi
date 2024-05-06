package net.syn3rgy.core.spi;

@FunctionalInterface
public interface PrioritizedService extends NamedService, Comparable<PrioritizedService> {

  public static final int DEFAULT_PRIORIY = 500;

  default int getPriority() {
    return DEFAULT_PRIORIY;
  }

  @Override
  default int compareTo(PrioritizedService o) {
    return Integer.compare(getPriority(), o.getPriority());
  }
}
