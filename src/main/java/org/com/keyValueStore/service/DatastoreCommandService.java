package org.com.keyValueStore.service;

import javafx.util.Pair;
import org.com.keyValueStore.model.KeyValue;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DatastoreCommandService {

    KeyValue get(String keyString);
    void put(String keyString, List<Pair<String, String>> attributeList);
    void delete(String keyString);
    Set<String> search(String attributeKey, String attributeValue);
    Set<String> keys();
    void exit();

}
