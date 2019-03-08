package com.future.teamwork;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.future.teamwork.dao.UserDao;
import com.future.teamwork.dao.subway.LineDao;
import com.future.teamwork.dao.subway.StationDao;
import com.future.teamwork.domain.subway.Line;
import com.future.teamwork.domain.subway.Station;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamworkApplicationTests {

	   @Test
	    public void contextLoads() {
	    }

	    @Autowired
	    private UserDao userDao;

	    @Autowired
		private StationDao stationDao;
		
		@Autowired
		private LineDao lineDao;
	    
	    @Test
	    public void saveLine() {
//	        Line line3 = new Line();
//	        line3.setLineName("line3");
//	        line3.setComment("line3");
//	        lineDao.save(line3);
	        
	        Line line5 = lineDao.getOne(5);
	        for (Station station : line5.getStations()) {
				System.out.println(line5.getLineName() + "  " + station.getStationName());
			}
	    }
	    
	    
//	    @Test
//	    public void saveStation() {
//	        Station station = new Station();
//	        station.setStationName("车陂南");
//	        station.setIsInterchange(1);
//	        stationDao.save(station);
//	        
//	    }
	    
//
//	    @Test
//	    public void update() {
//	        User user = new User();
//	        user.setId(1);
//	        user.setAge(18);
//	        user.setUsername("李四");
//	        testUserDao.save(user);
//	    }
//
//	    @Test
//	    public void select() {
//	        Optional<User> user = testUserDao.findById(1);
//	        System.out.println(user);
//	    }
//
//	    @Test
//	    public void delete() {
//	        testUserDao.deleteById(1);
//	    }
//	

}

