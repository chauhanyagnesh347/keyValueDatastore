package org.com.keyValueStore.util;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatastoreUtil {

    public static Map<String, Object> convertListOfAttribToMap(List<Pair<String, String>> attributeList) {
        return attributeList
                .stream()
                .collect(Collectors.toMap(pair -> pair.getKey(),
                        pair -> pair.getValue()));
    }
}
