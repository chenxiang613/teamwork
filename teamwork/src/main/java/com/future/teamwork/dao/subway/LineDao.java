package com.future.teamwork.dao.subway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.subway.Line;

@Repository
public interface LineDao extends JpaRepository<Line, Integer>{

}
