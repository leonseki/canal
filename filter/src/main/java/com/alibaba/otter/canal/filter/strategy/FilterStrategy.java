package com.alibaba.otter.canal.filter.strategy;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @author longtao.guan
 */
public interface FilterStrategy {
    boolean filter(CanalEntry.Entry event);
}
