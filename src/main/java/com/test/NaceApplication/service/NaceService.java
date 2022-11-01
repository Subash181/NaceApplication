package com.test.NaceApplication.service;

import com.test.NaceApplication.model.Nace;
import com.test.NaceApplication.repository.NaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NaceService {
    @Autowired
    private NaceRepository naceRepository;
    public Optional<Nace>getNaceByOrder(Integer order){
        return naceRepository.findById(order);
    }
    public Nace putNaceDetails(Nace nace){
        return naceRepository.save(nace);
    }
}
