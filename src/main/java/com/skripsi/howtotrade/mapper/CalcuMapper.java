package com.skripsi.howtotrade.mapper;

import java.util.List;
import java.util.Map;

import com.skripsi.howtotrade.model.Calculator;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Insert("INSERT INTO INVESTMENTPLANNING(USERID, DURATION, COINID, INVESTASIAWAL, INVESTASIPERBULAN, CALCTYPE, RESULT, CREATEDDATE, COINCODE) "
        + "VALUES (#{userId}, #{waktu}, null, #{investasiAwal},  #{perBulan}, #{jenisPerhitungan}, #{results}, CURRENT_TIMESTAMP, #{koin})")
    void insertCalculate(@Param("waktu") String waktu, @Param("investasiAwal") String investasiAwal, @Param("perBulan") String perBulan
    		, @Param("jenisPerhitungan") String jenisPerhitungan, @Param("results") String results, @Param("koin") String koin, 
    		@Param("userId") int userId);

    @Select("SELECT TO_CHAR(IP.CREATEDDATE,'DD Mon YYYY hh24:mi') AS CREATED, IP.*, "
        + "CASE "
        + "WHEN IP.CALCTYPE = '20_TOTAL' THEN 'Total Investasi (Tabung bulanan)' "
        + "WHEN IP.CALCTYPE = '30_COMP' THEN 'Total Investasi (Tabung sekali di awal)' "
        + "END AS JENIS "
        + "FROM INVESTMENTPLANNING IP LEFT JOIN COIN C ON IP.COINID = C.COINID "
        + "WHERE USERID = #{userId} "
        + "ORDER BY IP.CREATEDDATE DESC ")
    List<Map<String,String>> checkInvestmentData(int userId);

    @Select("SELECT COINRETURN FROM COIN")
    List<Double> getAllCoinReturn();

    @Select("SELECT COINCODE FROM COIN WHERE COINRETURN = #{coinReturn} ")
    List<String> getRecommendedCoin(int coinReturn);

    @Delete("DELETE FROM investmentplanning WHERE planningId = #{planningId}")
    void deletePlanning(@Param("planningId") int planningId);
}

