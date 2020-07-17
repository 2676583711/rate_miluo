package com.rate.web.statistic.vo;

import com.rate.web.desktop.entity.MapMarker;
import lombok.Data;

/**
 * @program: rate_miluo_parent
 * @ClassName: WaterWarn
 * @description:
 * @author: chenh
 * @create: 2020-07-16 16:14
 **/
@Data
public class WaterWarn extends MapMarker {

    private String dateTime;
    private Integer waterSort;

}
