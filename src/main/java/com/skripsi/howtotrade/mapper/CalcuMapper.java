package com.skripsi.howtotrade.mapper;

import java.util.List;
import java.util.Map;

import com.skripsi.howtotrade.model.Calculator;
import com.skripsi.howtotrade.model.Coin;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CalcuMapper {
    @Select("SELECT * FROM coin WHERE isactive = '1' ")
    List<Coin> getAllCoin();

    @Select("SELECT COINRETURN FROM COIN WHERE COINCODE = #{coinCode} ")
    int getCoinReturn(String coinCode);

    @Insert("INSERT INTO INVESTMENTPLANNING(USERID, DURATION, COINID, INVESTASIAWAL, INVESTASIPERBULAN, CALCTYPE, RESULT, CREATEDDATE, COINCODE) "
        + "VALUES (#{userId}, #{waktu}, null, #{investasiAwal},  #{perBulan}, #{jenisPerhitungan}, #{results}, CURRENT_TIMESTAMP, #{koin})")
    void insertCalculate(@Param("waktu") String waktu, @Param("investasiAwal") String investasiAwal, @Param("perBulan") String perBulan
    		, @Param("jenisPerhitungan") String jenisPerhitungan, @Param("results") String results, @Param("koin") String koin, 
    		@Param("userId") int userId);

    @Select("SELECT ca.*, ct.calculatortypename "
    		+ "FROM calculator ca "
    		+ "	LEFT JOIN coin co ON ca.coincode = co.coincode "
    		+ "	LEFT JOIN calculatortype ct on ct.calculatortypeid = ca.calculatortypeid "
    		+ "WHERE userid = #{userId} "
    		+ "ORDER BY ca.createddate DESC")
    List<Calculator> checkInvestmentData(int userId);
    
    // @Select("SELECT COINRETURN FROM COIN")
    // List<Double> getAllCoinReturn();

    // @Select("SELECT COINCODE FROM COIN WHERE COINRETURN = #{coinReturn} ")
    // List<String> getRecommendedCoin(int coinReturn);

    @Delete("DELETE FROM investmentplanning WHERE planningId = #{planningId}")
    void deletePlanning(@Param("planningId") int planningId);
}

