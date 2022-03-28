package com.skripsi.howtotrade.mapper;

import java.util.List;
import java.util.Map;

import com.skripsi.howtotrade.model.Calculator;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CalcuMapper {
    @Select("SELECT COINCODE AS code, COINCODE ||' - '|| COINNAME AS desc FROM COIN WHERE ACTIVE = '1' ")
    List<Map<String,String>> getAllCoin();

    @Select("SELECT COINRETURN FROM COIN WHERE COINCODE = #{coinCode} ")
    int getCoinReturn(String coinCode);

    @Select("SELECT COINID FROM COIN WHERE COINCODE = #{coinCode} ")
    int getCoinId(String coinCode);

    @Insert("INSERT INTO INVESTMENTPLANNING(USERID, TARGET, DURATION, COINID, CURRENTBALANCE) "
        + "VALUES (#{userId}, #{totalInvestasi}, #{waktu}, #{coinId}, #{perBulan})")
    void insert(Calculator calc, int userId, int coinId);

    @Insert("INSERT INTO INVESTMENTPLANNING(USERID, TARGET, DURATION, COINID, CURRENTBALANCE) "
        + "VALUES (#{userId}, #{totalInvestasi}, #{waktu}, #{coinId}, #{perBulan})")
    void insertCalculate(Double totalInvestasi, int waktu, int userId, int coinId, int perBulan);

    @Select("SELECT TARGET, DURATION, COINCODE, CURRENTBALANCE "
        + "FROM INVESTMENTPLANNING IP LEFT JOIN COIN C ON IP.COINID = C.COINID "
        + "WHERE USERID = #{userId}")
    List<Map<String,String>> checkInvestmentData(int userId);
}
