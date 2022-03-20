package com.future.teamwork.domain.stock;

import com.future.teamwork.domain.BaseEntity;
import com.future.teamwork.domain.Person;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Chen Xiang
 * @CreateDate: 2022/3/20 20:43
 * @Version:
 */

@Entity
@Table(name = "stk_stock_basic")
@Data
public class StockBasicDo extends BaseEntity implements Serializable {

    @Column(name = "ts_code")
    private String tsCode;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "area")
    private String area;

    @Column(name = "industry")
    private String industry;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "enname")
    private String enname;

    @Column(name = "cnspell")
    private String cnspell;

    @Column(name = "market")
    private String market;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "curr_type")
    private String currType;

    @Column(name = "list_status")
    private String listStatus;

    @Column(name = "list_date")
    private String listDate;

    @Column(name = "delist_date")
    private String delistDate;

    @Column(name = "is_hs")
    private String isHs;


}
