package com.mathewgv.library.service;

import java.io.InputStream;
import java.util.Optional;

public interface ImageService {

    void upload(String imagePath, InputStream imageContent);

    Optional<InputStream> get(String imagePath);
}
