package org.com.keyValueStore.service.implementation;

import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.com.keyValueStore.dao.KeyValueStoreDao;
import org.com.keyValueStore.exceptions.DatastoreApplicationException;
import org.com.keyValueStore.model.KeyValue;
import org.com.keyValueStore.service.DatastoreCommandService;
import org.com.keyValueStore.service.SchemaRegistryService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.com.keyValueStore.util.DatastoreUtil.convertListOfAttribToMap;

public class DatastoreService implements DatastoreCommandService {

    private static final Logger log = LogManager.getLogger(DatastoreService.class);
    private final KeyValueStoreDao keyValueStoreDao;
    private final SchemaRegistryService schemaRegistryService;

    public DatastoreService(KeyValueStoreDao keyValueStoreDao, SchemaRegistryService schemaRegistryService) {
        this.keyValueStoreDao = keyValueStoreDao;
        this.schemaRegistryService = schemaRegistryService;
    }

    @Override
    public KeyValue get(String keyString) {

        KeyValue keyValue = keyValueStoreDao.getForKey(keyString);
        log.info("{}", keyValue.toString());
        return keyValue;
    }

    @Override
    public void put(String keyString, List<Pair<String, String>> attributeList) {
        if (!keyValueStoreDao.isEmpty() && !schemaRegistryService.validateSchema(attributeList)) {
            log.error("Data Type Error");
            throw new DatastoreApplicationException("Data Type Error");
        }
        Map<String, Object> attributeMap = convertListOfAttribToMap(attributeList);
        schemaRegistryService.registerSchema(attributeList);
        KeyValue keyValue = new KeyValue(keyString, attributeMap);
        keyValueStoreDao.putForKey(keyString, keyValue);
    }

    @Override
    public void delete(String keyString) {
        keyValueStoreDao.deleteForKey(keyString);
    }

    @Override
    public Set<String> search(String attributeKey, String attributeValue) {
        Set<String> keySet = keyValueStoreDao.getKeysForAttribute(attributeKey, attributeValue);
        log.info("");
        keySet.forEach(key -> System.out.print(key + ", "));
        return keySet;
    }

    @Override
    public Set<String> keys() {
        Set<String> keySet = keyValueStoreDao.getKeys();
        log.info("");
        keySet.forEach(key -> System.out.print(key + ", "));
        return keySet;
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
