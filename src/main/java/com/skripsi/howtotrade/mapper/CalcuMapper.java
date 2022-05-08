package com.skripsi.howtotrade.mapper;

import java.util.List;

import com.skripsi.howtotrade.model.Calculator;
import com.skripsi.howtotrade.model.CalculatorType;
import com.skripsi.howtotrade.model.Coin;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CalcuMapper {
    @Select("SELECT * FROM COIN WHERE isActive = '1' ")
    List<Coin> getAllCoin();
    
    @Select("SELECT * FROM COIN C INNER JOIN CALCULATOR CA ON C.COINCODE = CA.COINCODE WHERE CA.CALCULATORID = #{calculatorId} ")
    Coin getCoinByCalculatorId(int calculatorId);
    
    @Select("SELECT * FROM CALCULATORTYPE CT INNER JOIN CALCULATOR CA ON CT.CALCULATORTYPEID = CA.CALCULATORTYPEID WHERE CA.CALCULATORID = #{calculatorId} ")
    CalculatorType getCalcTypeByCalculatorId(int calculatorId);

    @Select("SELECT * FROM CALCULATORTYPE ")
    List<CalculatorType> getAllCalculateType();

    @Select("SELECT COINRETURN FROM COIN WHERE COINCODE = #{coinCode} ")
    int getCoinReturn(String coinCode);

    // @Select("SELECT COINID FROM COIN WHERE COINCODE = #{coinCode} ")
    // int getCoinId(String coinCode);

    // @Insert("INSERT INTO INVESTMENTPLANNING(USERID, TARGET, DURATION, COINID, CURRENTBALANCE) "
    //     + "VALUES (#{userId}, #{totalInvestasi}, #{waktu}, #{coinId}, #{perBulan})")
    // void insert(Calculator calc, int userId, int coinId);

    @Insert("INSERT INTO CALCULATOR(userid, coincode, calculatortypeid, duration, investasiawal, investasiperbulan, result, createddate) "
        + "VALUES (#{userId}, #{coinCode}, #{calculatorTypeId}, #{duration}, #{investasiAwal}, #{investasiPerbulan}, #{result}, CURRENT_TIMESTAMP)")
    void insertCalculate(@Param("duration") String duration, @Param("investasiAwal") String investasiAwal, @Param("investasiPerbulan") String investasiPerbulan
    		, @Param("result") String result, @Param("coinCode") String coinCode, 
    		@Param("userId") int userId, @Param("calculatorTypeId") int calculatorTypeId);

    @Select("SELECT TO_CHAR(CA.CREATEDDATE,'DD Mon YYYY hh24:mi') AS CREATED, CA.*, CT.* "
        + "FROM CALCULATOR CA LEFT JOIN COIN C ON CA.COINCODE = C.COINCODE "
        + "INNER JOIN CALCULATORTYPE CT ON CA.CALCULATORTYPEID = CT.CALCULATORTYPEID "
        + "WHERE USERID = #{userId} "
        + "ORDER BY CA.CREATEDDATE DESC ")
    List<Calculator> checkInvestmentData(int userId);

    // @Select("SELECT COINRETURN FROM COIN")
    // List<Double> getAllCoinReturn();

    // @Select("SELECT COINCODE FROM COIN WHERE COINRETURN = #{coinReturn} ")
    // List<String> getRecommendedCoin(int coinReturn);

    @Delete("DELETE FROM CALCULATOR WHERE calculatorId = #{calculatorId}")
    void deletePlanning(@Param("calculatorId") int calculatorId);
}

