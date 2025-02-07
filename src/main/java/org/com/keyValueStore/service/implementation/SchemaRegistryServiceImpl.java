package org.com.keyValueStore.service.implementation;

import javafx.util.Pair;
import org.com.keyValueStore.dao.SchemaRegistryDao;
import org.com.keyValueStore.service.SchemaRegistryService;

import java.util.List;
import java.util.stream.Collectors;

import static org.com.keyValueStore.util.SchemaUtil.identifyDatatype;

public class SchemaRegistryServiceImpl implements SchemaRegistryService {


    SchemaRegistryDao schemaRegistryDao;

    public SchemaRegistryServiceImpl(SchemaRegistryDao schemaRegistryDao) {
        this.schemaRegistryDao = schemaRegistryDao;
    }

    @Override
    public void registerSchema(List<Pair<String, String>> attributeList) {
        attributeList.forEach(att -> {
            schemaRegistryDao.registerDatatype(att.getKey(), identifyDatatype(att.getValue()));
        });
    }

    @Override
    public boolean validateSchema(List<Pair<String, String>> attributeList) {

        List<Pair<String, String>> mismatchedAttributes = attributeList.stream().filter(att ->
            !schemaRegistryDao.getDatatypeFor(att.getKey()).equals(identifyDatatype(att.getValue()))).collect(Collectors.toList());

        return mismatchedAttributes.isEmpty();
    }
}
