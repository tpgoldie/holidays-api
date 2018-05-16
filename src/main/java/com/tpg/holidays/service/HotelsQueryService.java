package com.tpg.holidays.service;

import com.tpg.holidays.model.Hotel;
import com.tpg.holidays.wui.controllers.SearchRequest;

import java.util.List;

public interface HotelsQueryService {

    List<Hotel> searchForHolidaysByDestination(SearchRequest searchRequest);
}
