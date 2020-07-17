package com.rate.web.connected.offdata.dao;

import com.rate.web.connected.offdata.entity.TempEntity;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/6
 */
@Repository
@SqlResource("connected.offline.offData")
public interface OffDataDao {
    List<TempEntity> findAirStatusList(Map<String, Object> params);

    List<TempEntity> findAirDataList(Map<String, Object> params);

    List<TempEntity> findPolluteStatusList(Map<String, Object> params);

    List<TempEntity> findPolluteDataList(Map<String, Object> params);

    List<TempEntity> findWaterPlantStatusList(Map<String, Object> params);

    List<TempEntity> findWaterPlantDataList(Map<String, Object> params);

    List<TempEntity> findWaterAutoStatusList(Map<String, Object> params);

    List<TempEntity> findWaterAutoDataList(Map<String, Object> params);
}
