package com.project.trash.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class LogUtils {

    public void info(Object msg) {
        log.info(msg.toString());
    }

    public void warn(Object msg) {
        log.warn(msg.toString());
    }

    public void error(Object msg) {
        log.error(msg.toString());
    }

    public void error(Exception e) {
        log.error(e.getMessage());
    }

    public void debug(Object msg) {
        log.debug(msg.toString());
    }

    public void trace(Object msg) {
        log.trace(msg.toString());
    }
}
