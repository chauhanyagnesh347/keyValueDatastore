package org.com.keyValueStore.util;

import static java.util.Objects.nonNull;

public class SchemaUtil {

    public static boolean isInteger(String value) {
        if(nonNull(value)) {
            try{
                Integer parsedValue = Integer.valueOf(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isDouble(String value) {
        if(nonNull(value)) {
            try{
                Double parsedValue = Double.valueOf(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isBoolean(String value) {
        if(nonNull(value)) {
            return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
        } else {
            return false;
        }
    }


    public static String identifyDatatype(String value) {
        if(isInteger(value)) {
            return "Integer";
        } else if(isDouble(value)) {
            return "Double";
        } else if(isBoolean(value)) {
            return "Boolean";
        } else {
            return "String";
        }

    }

}
