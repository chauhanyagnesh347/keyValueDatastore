package org.com.keyValueStore.service;

import javafx.util.Pair;

import java.util.List;

public interface SchemaRegistryService {

    void registerSchema(List<Pair<String, String>> attributeList);
    boolean validateSchema(List<Pair<String, String>> attributeList);

}
