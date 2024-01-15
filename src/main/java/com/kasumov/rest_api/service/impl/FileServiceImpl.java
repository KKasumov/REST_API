package com.kasumov.rest_api.service.impl;

import com.kasumov.rest_api.repository.FileRepository;
import com.kasumov.rest_api.model.Event;
import com.kasumov.rest_api.model.File;
import com.kasumov.rest_api.model.User;
import com.kasumov.rest_api.service.EventService;
import com.kasumov.rest_api.service.FileService;
import com.kasumov.rest_api.service.UserService;
import com.kasumov.rest_api.utils.GetFileNameUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.servlet.annotation.MultipartConfig;
import java.io.InputStream;
import java.util.List;

@MultipartConfig
@NoArgsConstructor
@AllArgsConstructor
public class FileServiceImpl implements FileService {
    private UserService userService;
    private EventService eventService;
    private FileRepository fileRepository;

    @Override
    public File getById(Integer id) {
        return fileRepository.getById(id);
    }

    @Override
    public List<File> getAll() {
        return fileRepository.getAll();
    }

    @Override
    public File create(File file) {
        return fileRepository.create(file);
    }

    @Override
    public File update(File file) {
        return fileRepository.update(file);
    }

    @Override
    public void deleteById(Integer id) {
        fileRepository.deleteById(id);
    }

    @Override
    public File uploadFile(InputStream inputStream, Integer userId) {

        String fileName = GetFileNameUtil.getFileName(inputStream);
        File file = new File();
        file.setFileName(fileName);
        file.setFilePath("C:\\Soft\\TesT\\" + fileName);
        User user = userService.getById(userId);
        Event event = new Event();
        event.setUser(user);
        event.setFile(file);
        eventService.create(event);
        return file;
    }
}

