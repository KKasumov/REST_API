package com.kasumov.rest_api.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class GetFileNameUtil {

    public static String getFileName(InputStream inputStream) {
        String result;
        try {
            result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fileName = result.substring(0, result.lastIndexOf('"'));
        fileName = new StringBuffer(fileName).reverse().toString();
        fileName = fileName.substring(0, fileName.indexOf('"'));
        fileName = new StringBuffer(fileName).reverse().toString();
        return fileName;
    }
}
