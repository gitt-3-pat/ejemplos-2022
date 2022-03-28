package edu.icai.gittpat.service.impl;

import edu.icai.gittpat.model.MyTable;
import edu.icai.gittpat.repository.MyTableRepository;
import edu.icai.gittpat.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private MyTableRepository myTableRepository;

    @Override
    public List<MyTable> getElements() {
        return StreamSupport.stream(myTableRepository.findAll().spliterator(), false).toList();
    }
}
