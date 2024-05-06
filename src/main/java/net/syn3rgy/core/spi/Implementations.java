package net.syn3rgy.core.spi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

import org.slf4j.Logger;

public final class Implementations {

    private Implementations () {}

    private static final Logger LOGGER = Spi.LOGGER;
    private static final Map<String, List<PrioritizedService>> cache = new HashMap<> ();
    private static final Map<String, Map<String, List<PrioritizedService>>> paramsCache = new HashMap<> ();

    public static <T extends PrioritizedService> List<T> of (Class<T> clazz) {
        return of (clazz, null);
    }

    public static <T extends PrioritizedService> List<T> of (Class<T> clazz, ImplementationsFilter filter) {
        return of (clazz, filter, null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends PrioritizedService> List<T> of (Class<T> clazz, ImplementationsFilter filter, ImplementationParams params) {
        Map<String, List<PrioritizedService>> cacheObject;
        String cacheKey;
        String className = getClassName (clazz);
        if (params == null) {
            cacheObject = cache;
            cacheKey = className;
        } else {
            cacheKey = params.getCacheKey ();
            cacheObject = paramsCache.get (className);
            if (cacheObject == null) {
                cacheObject = new HashMap<> ();
                paramsCache.put (className, cacheObject);
            }
        }
        if (cacheObject.containsKey (cacheKey)) {
            return (List<T>) cacheObject.get (cacheKey);
        }
        List<T> selected = ofNew (clazz, filter, params);
        if (!selected.isEmpty ()) {
            cacheObject.put (cacheKey, (List<PrioritizedService>) selected);
        }
        return selected;
    }

    protected static <T extends NamedService> String getClassName (Class<T> clazz) { return clazz.getCanonicalName (); }

    public static <T extends PrioritizedService> List<T> ofNew (Class<T> clazz) {
        return ofNew (clazz, null);
    }

    public static <T extends PrioritizedService> List<T> ofNew (Class<T> clazz, ImplementationsFilter filter) {
        return ofNew (clazz, filter, null);
    }

    public static <T extends PrioritizedService> List<T> ofNew (Class<T> clazz, ImplementationsFilter filter, ImplementationParams params) {
        List<T> list = loadList (clazz);
        List<T> selected;
        if (list.isEmpty () || filter == null) {
            selected = list;
        } else {
            selected = Collections.unmodifiableList (filter.filter (clazz, list, Optional.ofNullable (params))); 
        }
        if (LOGGER.isDebugEnabled ()) {
            if (selected.isEmpty ()) {
                LOGGER.warn ("No implementation selected for : {}", getClassName (clazz));
            } else {
                LOGGER.debug ("Filtered implementations are :");
                for (T impl : selected) {
                    LOGGER.debug ("\t#{} {} : {}", impl.getPriority (), impl.getServiceImplementationName (), impl.getClass ().getName ());
                }
            }
        }
        return selected;
    }

    private static <T extends PrioritizedService> List<T> loadList (Class<T> clazz) {
        if (LOGGER.isDebugEnabled ()) { LOGGER.debug ("Loading implementations for class <{}> :", getClassName (clazz)); }
        final List<T> implementations = new ArrayList<> ();
        final ServiceLoader<? extends T> implementationsLoader = ServiceLoader.load (clazz);
        for (T impl : implementationsLoader) {
            implementations.add (impl);
        }
        Collections.sort (implementations);
        if (LOGGER.isDebugEnabled ()) {
            if (implementations.isEmpty ()) {
                LOGGER.debug ("\tNo implementations found.");
            } else {
                for (T impl : implementationsLoader) {
                    LOGGER.debug ("\t#{} {} : {}", impl.getPriority (), impl.getServiceImplementationName (), impl.getClass ().getName ());
                }
            }
        }
        return Collections.unmodifiableList (implementations);
    }

}
