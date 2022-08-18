package com.mathewgv.library.service.impl;

import com.mathewgv.library.dao.transaction.TransactionFactory;
import com.mathewgv.library.service.ImageService;
import com.mathewgv.library.util.PropertiesUtil;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.Properties;

import static java.nio.file.StandardOpenOption.*;

public class ImageServiceImpl implements ImageService {

    private static final ImageServiceImpl INSTANCE = new ImageServiceImpl();

    private final String basePath = PropertiesUtil.get("image.base.url");

    private ImageServiceImpl() {
    }

    @Override
    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent) {
        var imageFullPath = Path.of(basePath, imagePath);
        try (imageContent) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @Override
    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        var imageFullPath = Path.of(basePath, imagePath);
        return Files.exists(imageFullPath)
                ? Optional.of(Files.newInputStream(imageFullPath))
                : Optional.empty();
    }

    public static ImageServiceImpl getInstance() {
        return INSTANCE;
    }
}
