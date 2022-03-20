package com.future.teamwork.dao.system;

import com.future.teamwork.domain.Role;
import com.future.teamwork.domain.stock.StockBasicDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockBasicDao extends JpaRepository<StockBasicDo, Long>{

}
