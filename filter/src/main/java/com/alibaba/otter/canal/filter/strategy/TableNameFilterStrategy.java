package com.alibaba.otter.canal.filter.strategy;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @author longtao.guan
 */
public class TableNameFilterStrategy implements FilterStrategy{
    @Override
    public boolean filter(CanalEntry.Entry event) {
        return event.getHeader().getTableName().startsWith("user");
    }
}
