package net.syn3rgy.core.spi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import org.slf4j.Logger;

public final class Implementation {

  private Implementation() {}

  private static final Logger LOGGER = Spi.LOGGER;
  private static final Map<String, NamedService> cache = new HashMap<>();
  private static final Map<String, Map<String, NamedService>> paramsCache = new HashMap<>();
  private static ImplementationSelector globalSelector;

  public static void setGlobalSelector(ImplementationSelector selector) {
    Implementation.globalSelector = selector;
  }

  private static ImplementationSelector getGlobalSelector() {
    if (globalSelector == null) {
      globalSelector = new DefaultImplementationSelector();
    }
    return globalSelector;
  }

  private static ImplementationSelector getSelector(ImplementationSelector selector) {
    if (selector != null) {
      return selector;
    }
    return getGlobalSelector();
  }

  public static <T extends NamedService> Optional<T> of(Class<T> clazz) {
    return of(clazz, null);
  }

  public static <T extends NamedService> Optional<T> of(
      Class<T> clazz, ImplementationSelector selector) {
    return of(clazz, selector, null);
  }

  @SuppressWarnings("unchecked")
  public static <T extends NamedService> Optional<T> of(
      Class<T> clazz, ImplementationSelector selector, ImplementationParams params) {
    Map<String, NamedService> cacheObject;
    String cacheKey;
    String className = getClassName(clazz);
    if (params == null) {
      cacheObject = cache;
      cacheKey = className;
    } else {
      cacheKey = params.getCacheKey();
      cacheObject = paramsCache.computeIfAbsent(className, k -> new HashMap<>());
    }
    if (cacheObject.containsKey(cacheKey)) {
      return Optional.of((T) cacheObject.get(cacheKey));
    }
    Optional<T> selected = ofNew(clazz, selector, params);
    selected.ifPresent(t -> cacheObject.put(cacheKey, t));
    return selected;
  }

  private static <T extends NamedService> String getClassName(Class<T> clazz) {
    return clazz.getCanonicalName();
  }

  public static <T extends NamedService> Optional<T> ofNew(Class<T> clazz) {
    return ofNew(clazz, null);
  }

  public static <T extends NamedService> Optional<T> ofNew(
      Class<T> clazz, ImplementationSelector selector) {
    return ofNew(clazz, selector, null);
  }

  public static <T extends NamedService> Optional<T> ofNew(
      Class<T> clazz, ImplementationSelector selector, ImplementationParams params) {
    List<T> list = loadList(clazz);
    Optional<T> selected;
    if (list.isEmpty()) {
      selected = Optional.empty();
    } else {
      selected = getSelector(selector).choose(clazz, list, Optional.ofNullable(params));
    }
    if (LOGGER.isDebugEnabled()) {
      if (selected.isPresent()) {
        LOGGER.debug(
            "Selected implementation is : {}", selected.get().getServiceImplementationName());
      } else {
        LOGGER.warn("No implementation selected for : {}", getClassName(clazz));
      }
    }
    return selected;
  }

  private static <T extends NamedService> List<T> loadList(Class<T> clazz) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Loading implementations for class <{}> :", getClassName(clazz));
    }
    final List<T> implementations = new ArrayList<>();
    final ServiceLoader<? extends T> implementationsLoader = ServiceLoader.load(clazz);
    for (T impl : implementationsLoader) {
      implementations.add(impl);
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("\t{} : {}", impl.getServiceImplementationName(), impl.getClass().getName());
      }
    }
    if (LOGGER.isDebugEnabled() && implementations.isEmpty()) {
      LOGGER.debug("\tNo implementations found.");
    }

    return Collections.unmodifiableList(implementations);
  }
}
