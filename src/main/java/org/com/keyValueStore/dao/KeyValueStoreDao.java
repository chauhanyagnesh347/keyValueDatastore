package org.com.keyValueStore.dao;

import org.com.keyValueStore.exceptions.DataAccessException;
import org.com.keyValueStore.model.KeyValue;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class KeyValueStoreDao {

    private Map<String, KeyValue> keyIndexedData = new ConcurrentHashMap<>();


    public void initData() {
        keyIndexedData.clear();
    }

    public KeyValue getForKey(String keyString) {
        existanceCheck(keyString);
        return keyIndexedData.get(keyString);
    }

    public boolean isEmpty() {
        return keyIndexedData.isEmpty();
    }

    public void putForKey(String keyString, KeyValue keyValue) {
        keyIndexedData.put(keyString, keyValue);
    }

    public void deleteForKey(String keyString) {
//        existanceCheck(keyString);
        keyIndexedData.remove(keyString);
    }

    public Set<String> getKeys() {
        return keyIndexedData.keySet().stream().sorted().collect(Collectors.toSet());
    }

    public Set<String> getKeysForAttribute(String attributeKey, String attributeValue) {

        Set<String> matchedKeySet = keyIndexedData
                .entrySet()
                .stream()
                .filter(kv -> {
                    return kv.getValue().getAttributeMap().get(attributeKey).toString().equals(attributeValue);
                })
                .map(kv -> kv.getKey())
                .sorted().collect(Collectors.toSet());

        return matchedKeySet;
    }

    private void existanceCheck(String keyString) {
        if (!keyIndexedData.containsKey(keyString)) {
            throw new DataAccessException("No entry found for " + keyString);
        }
    }

}
