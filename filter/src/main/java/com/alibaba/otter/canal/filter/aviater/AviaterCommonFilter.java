package com.alibaba.otter.canal.filter.aviater;

import com.alibaba.otter.canal.filter.CanalEventFilter;
import com.alibaba.otter.canal.filter.exception.CanalFilterException;
import com.alibaba.otter.canal.filter.strategy.FilterStrategy;
import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.List;

/**
 * @author longtao.guan
 */
public class AviaterCommonFilter implements CanalEventFilter<CanalEntry.Entry> {

    List<FilterStrategy> filterStrategyList;

    public AviaterCommonFilter(List<FilterStrategy> filterStrategyList) {
        this.filterStrategyList = filterStrategyList;
    }

    @Override
    public boolean filter(CanalEntry.Entry event) throws CanalFilterException {
        for (FilterStrategy filterStrategy : filterStrategyList) {
            if (!filterStrategy.filter(event)) {
                return false;
            }
        }
        return true;
    }
}
