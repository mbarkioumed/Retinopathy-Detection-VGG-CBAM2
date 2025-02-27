package org.example.controller;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ImageStorage {
    private static final Map<String, byte[]> IMAGE_STORE = new ConcurrentHashMap<>();

    public static String saveImage(byte[] imageData) {
        String id = UUID.randomUUID().toString();
        IMAGE_STORE.put(id, imageData);
        return id;
    }

    public static byte[] getImage(String id) {
        return IMAGE_STORE.get(id);
    }
}