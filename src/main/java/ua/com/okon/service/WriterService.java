package ua.com.okon.service;

import ua.com.okon.model.Writer;
import ua.com.okon.repository.WriterRepository;

import java.util.List;

public class WriterService {

    private final WriterRepository writerRepository;


    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer findById(Long id) {
        return writerRepository.findById(id);
    }

    public List<Writer> findAll() {
        return writerRepository.findAll();
    }

    public void save(Writer writer) {

    }

    public void delete(Long id) {
    }

    public void update(Writer writer, Long id) {
    }
}
