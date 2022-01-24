package ua.com.okon.controller;

import ua.com.okon.model.Writer;
import ua.com.okon.service.WriterService;

import java.util.List;

public class WriterController {

    private final WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    public List<Writer> index() {
        return writerService.findAll();
    }
}
