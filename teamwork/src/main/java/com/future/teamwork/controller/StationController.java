package com.future.teamwork.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.future.teamwork.dao.subway.LineDao;
import com.future.teamwork.dao.subway.StationDao;
import com.future.teamwork.domain.subway.Line;
import com.future.teamwork.domain.subway.Station;

@Controller
@RequestMapping("station")
public class StationController {

	@Autowired
	private StationDao stationDao;
	
	@Autowired
	private LineDao lineDao;
	
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(Integer id) {
        Line line5 = lineDao.getOne(id);
        for (Station station : line5.getStations()) {
			System.out.println(line5.getLineName() + "  " + station.getStationName());
			if( !station.getNextStations().isEmpty() ){
				System.out.println(" 关联下个站点   ");
				for(Station nestStation : station.getNextStations()) {
					System.out.print(nestStation.getStationName());
				}
			}
		}
        return "success";
	}
}
