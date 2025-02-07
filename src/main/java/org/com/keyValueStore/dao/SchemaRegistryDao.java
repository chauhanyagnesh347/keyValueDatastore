package org.com.keyValueStore.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.com.keyValueStore.exceptions.DataAccessException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SchemaRegistryDao {

    private static final Logger log = LogManager.getLogger(SchemaRegistryDao.class);
    Map<String, String> attributeDatatypeMap = new ConcurrentHashMap<>();

    public void initData() {
        attributeDatatypeMap.clear();
    }

    public String getDatatypeFor(String attributeKey) {
        absenceCheck(attributeKey);
        return attributeDatatypeMap.get(attributeKey);
    }

    public void registerDatatype(String attributeKey, String datatype) {
//        presenceCheck(attributeKey);
        attributeDatatypeMap.put(attributeKey, datatype);
    }


    private void absenceCheck(String attributeKey) {
        if (!attributeDatatypeMap.containsKey(attributeKey)) {
            throw new DataAccessException("Datatype for Attribute: " + attributeKey + " has not been registered yet.");
        }
    }

    private void presenceCheck(String attributeKey) {
        if (attributeDatatypeMap.containsKey(attributeKey)) {
            log.info("Datatype for Attribute: {} is already present.", attributeKey);
        }
    }

}
