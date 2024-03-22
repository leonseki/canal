package com.alibaba.otter.canal.filter.strategy;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @author longtao.guan
 */
public class EventTypeFilterStrategy implements FilterStrategy{
    @Override
    public boolean filter(CanalEntry.Entry event) {
        return !CanalEntry.EventType.DELETE.equals(event.getHeader().getEventType());

    }
}
