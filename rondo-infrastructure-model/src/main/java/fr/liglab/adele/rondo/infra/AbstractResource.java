package fr.liglab.adele.rondo.infra;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 11:05 AM
 */
public abstract class AbstractResource<T extends AbstractResource<T>> {

    private String name;
    private String state;
    private List<Property> properties;

    protected abstract T self();

    public static <L> FluentList<L> list(L... items) {
        return new FluentList<L>(items);
    }

    public static <K, V> FluentMap<K, V> map(Pair<K, V>... pairs) {
        return new FluentMap<K, V>(pairs);
    }

    public static <K, V> Pair<K, V> pair(K k, V v) {
        return new Pair<K, V>(k, v);
    }

    protected AbstractResource(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public String state() {
        return state;
    }

    public Map<String, Object> properties() {
        HashMap<String, Object> props = new HashMap<String, Object>();
        if (properties != null) {
            for (Property p : properties) {
                props.put(p.name, p.value);
            }
        }
        return props;
    }

    public T name(String name) {
        this.name = name;
        return self();
    }

    public T state(String state) {
        this.state = state;
        return self();
    }

    public Property<Object> with(String propertyName) {
        if (this.properties == null) {
            this.properties = new ArrayList<Property>();
        }
        Property<Object> property = new Property<Object>(propertyName);
        this.properties.add(property);
        return property;
    }

    public static class FluentList<L> extends ArrayList<L> {

        public FluentList() {
            super(new ArrayList<L>());
        }

        public FluentList(L... items) {
            this();
            addAll(Arrays.asList(items));
        }

        public FluentList<L> with(L o) {
            add(o);
            return this;
        }
    }

    public static class FluentMap<K, V> extends LinkedHashMap<K, V> {

        public FluentMap() {
            super(new LinkedHashMap<K, V>());
        }

        public FluentMap(Pair<? extends K, ? extends V>... pairs) {
            this();
            with(pairs);
        }

        public FluentMap<K, V> with(Pair<? extends K, ? extends V>... pairs) {
            for (Pair<? extends K, ? extends V> pair : pairs) {
                this.put(pair.key, pair.value);
            }
            return this;
        }

        public FluentMap<K, V> putAt(K k, V value) {
            this.put(k, value);
            return this;
        }
    }

    public static class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public class Property<P> {
        private final String name;
        private P value;

        Property(String name) {
            this.name = name;
        }

        public T setto(P value) {
            this.value = value;
            return self();
        }

        public P get() {
            return value;
        }

        @Override
        public String toString() {
            return name + " : " + value;
        }
    }

    @Override
    public String toString() {
        return name() + " - " + state() + " - " + properties();
    }
}
