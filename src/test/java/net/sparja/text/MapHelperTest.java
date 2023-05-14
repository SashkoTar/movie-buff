package net.sparja.text;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapHelperTest {

    @Test
    public void shouldReturnInvertedMap() {
        Map<String, String> map = new HashMap<String, String>() {{
           put("k1", "v1");
           put("k2", "v2");
           put("k3", "v2");
        }};

        Map<String, Set<String>> invertedMap = MapHelper.invert(map);

        assertTrue(invertedMap.get("v1").contains("k1"));
        assertEquals(invertedMap.get("v1").size(), 1);
        assertTrue(invertedMap.get("v2").contains("k2"));
        assertTrue(invertedMap.get("v2").contains("k3"));
        assertEquals(invertedMap.get("v2").size(), 2);
    }

    @Test
    public void shouldJoinTables() {
        Map<String, String> leftMap = new HashMap<String, String>() {{
            put("k1", "v1");
            put("k2", "v2");
            //put("k3", "v2");
        }};

        Map<String, Set<String>> invertedMap = new HashMap<String, Set<String>>() {{
            put("v1", set("k_1"));
            put("v2", set("k_3", "k_2"));
        }};

        List<JoinedLine<String, String, Set<String>>> joinedData = MapHelper.join(leftMap, invertedMap);

        Optional<JoinedLine<String, String, Set<String>>> line = findLine(joinedData, "k1" );

        assertTrue(line.get().getRightKey().contains("k_1"));
        assertEquals(line.get().getRightKey().size(), 1);

        line = findLine(joinedData, "k2" );
        assertTrue(line.get().getRightKey().contains("k_2"));
        assertTrue(line.get().getRightKey().contains("k_3"));
        assertEquals(line.get().getRightKey().size(), 2);
    }

    private Set<String> set(String... es) {
       return Arrays.stream(es).collect(Collectors.toSet());
    }

    private Optional<JoinedLine<String, String, Set<String>>> findLine(List<JoinedLine<String, String, Set<String>>> joinedData, String key) {
       return joinedData.stream().filter(line -> line.getLeftKey().equals(key)).findFirst();
    }
 }
