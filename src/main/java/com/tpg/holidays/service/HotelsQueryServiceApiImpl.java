package com.tpg.holidays.service;

import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.model.Hotel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class HotelsQueryServiceApiImpl implements HotelsQueryService {

    @Override
    public List<Hotel> searchForHolidaysByDestination(SearchRequest searchRequest) {

        return emptyList();
    }

    public HotelsQueryServiceApiImpl(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }

    private final RestTemplate restTemplate;
}
