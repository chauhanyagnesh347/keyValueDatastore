package org.com.keyValueStore.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class KeyValue {

    String keyString;
    Map<String, Object> attributeMap;

    public KeyValue(String keyString, Map<String, Object> attributeMap) {
        this.keyString = keyString;
        this.attributeMap = attributeMap;
    }

    public String getKeyString() {
        return keyString;
    }

    public Map<String, Object> getAttributeMap() {
        return attributeMap;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public void setAttributeMap(Map<String, Object> attributeMap) {
        this.attributeMap = attributeMap;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.attributeMap.forEach((key, value) -> stringBuilder.append(key).append(": ").append(value).append(", "));
        return stringBuilder.substring(0, stringBuilder.length()-2);
    }
}
