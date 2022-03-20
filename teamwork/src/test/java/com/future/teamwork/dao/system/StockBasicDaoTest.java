package com.future.teamwork.dao.system;

import com.alibaba.fastjson.JSON;
import com.future.teamwork.TeamworkApplication;
import com.future.teamwork.domain.stock.StockBasicDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockBasicDaoTest {

    @Autowired
    StockBasicDao stockBasicDao;

    @Test
    public void setStockBasicDao() {
        Optional<StockBasicDo> stockBasicDo = stockBasicDao.findById(1L);
        System.out.println("JSON.toJSONString(stockBasicDo) = " + JSON.toJSONString(stockBasicDo));

    }
}