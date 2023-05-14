package net.sparja.text;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MapHelper {
    public static <V, K> Map<V, Set<K>> invert(Map<K, V> map) {
        return  map.entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toSet())));
    }

    public static<KL, V, KR> List<JoinedLine<KL,V, KR>> join(Map<KL, V> leftMap, Map<V, KR> invertedMap) {
        return leftMap
                .entrySet()
                .stream()
                .map(e -> new JoinedLine<>(e.getKey(), e.getValue(), invertedMap.get(e.getValue())))
                .collect(Collectors.toList());

    }

}
