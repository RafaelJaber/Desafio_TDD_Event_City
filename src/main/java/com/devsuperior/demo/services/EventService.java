package com.devsuperior.demo.services;

import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;

    public EventService(
            EventRepository eventRepository,
            CityRepository cityRepository
    ) {
        this.eventRepository = eventRepository;
        this.cityRepository = cityRepository;
    }


}
