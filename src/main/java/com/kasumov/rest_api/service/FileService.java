package com.kasumov.rest_api.service;

import com.kasumov.rest_api.model.File;

import java.io.InputStream;

public interface FileService extends GenericService<File, Integer> {

    File uploadFile(InputStream inputStream, Integer userId);
}
