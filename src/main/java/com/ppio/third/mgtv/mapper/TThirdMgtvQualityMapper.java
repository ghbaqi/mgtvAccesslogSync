package com.ppio.third.mgtv.mapper;

import com.ppio.third.mgtv.entity.TThirdMgtvQuality;

public interface TThirdMgtvQualityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_third_mgtv_quality
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_third_mgtv_quality
     *
     * @mbg.generated
     */
    int insert(TThirdMgtvQuality record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_third_mgtv_quality
     *
     * @mbg.generated
     */
    int insertSelective(TThirdMgtvQuality record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_third_mgtv_quality
     *
     * @mbg.generated
     */
    TThirdMgtvQuality selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_third_mgtv_quality
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TThirdMgtvQuality record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_third_mgtv_quality
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TThirdMgtvQuality record);
}