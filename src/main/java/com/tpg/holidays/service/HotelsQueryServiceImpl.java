package com.tpg.holidays.service;

import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class HotelsQueryServiceImpl implements HotelsQueryService {

    @Override
    public List<Hotel> searchForHolidaysByDestination(SearchRequest searchRequest) {

        return emptyList();
    }
}
