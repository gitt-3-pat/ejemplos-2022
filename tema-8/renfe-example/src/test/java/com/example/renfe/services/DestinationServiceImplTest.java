package com.example.renfe.services;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class DestinationServiceImplTest {

    final DestinationService service = new DestinationServiceImpl();

    @Test
    public void given_Destinationservice_when_call_getDestination_then_Ok() {

        //Given

        //When
        List<String> result = service.getDestinations();

        //Then
        var expectedList = List.of(
                "Valencia",
                "Sevilla",
                "Barcelona");
        then(result).isEqualTo(expectedList);
    }



}