package com.example.renfe.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Override
    public List<String> getDestinations() {

        return List.of(
                "Valencia",
                "Sevilla",
                "Barcelona");
    }
}
