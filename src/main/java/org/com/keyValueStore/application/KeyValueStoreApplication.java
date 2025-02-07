package org.com.keyValueStore.application;

import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.com.keyValueStore.dao.KeyValueStoreDao;
import org.com.keyValueStore.dao.SchemaRegistryDao;
import org.com.keyValueStore.service.SchemaRegistryService;
import org.com.keyValueStore.service.implementation.DatastoreService;
import org.com.keyValueStore.service.implementation.SchemaRegistryServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KeyValueStoreApplication {

    private static final Logger log = LogManager.getLogger(KeyValueStoreApplication.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SchemaRegistryDao schemaRegistryDao = new SchemaRegistryDao();
        schemaRegistryDao.initData();
        KeyValueStoreDao keyValueStoreDao = new KeyValueStoreDao();
        keyValueStoreDao.initData();
        SchemaRegistryService schemaRegistryService = new SchemaRegistryServiceImpl(schemaRegistryDao);
        DatastoreService datastoreService = new DatastoreService(keyValueStoreDao, schemaRegistryService);



        while (true) {


            String inputCommand = scanner.nextLine();
            String[] parsedCommand = inputCommand.split(" ");
            String command = parsedCommand[0];

        try {


            switch (command) {
                case "get":
                    datastoreService.get(parsedCommand[1]);
                    break;
                case "put":
                    List<Pair<String, String>> attributeList = new ArrayList<>();
                    for (int index = 2; index < parsedCommand.length; index += 2) {
                        attributeList.add(new Pair<>(parsedCommand[index], parsedCommand[index + 1]));
                    }
                    datastoreService.put(parsedCommand[1], attributeList);
                    break;
                case "keys":
                    datastoreService.keys();
                    break;
                case "delete":
                    datastoreService.delete(parsedCommand[1]);
                    break;
                case "search":
                    datastoreService.search(parsedCommand[1], parsedCommand[2]);
                    break;
                case "exit":
                    datastoreService.exit();
                    break;
            }
        } catch (Exception e) {
            log.error("Exception occured: {}, {}", e.getClass(), e.getMessage());
        }
        }
    }

}
