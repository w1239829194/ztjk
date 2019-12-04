package com.mmall.util;

import org.apache.commons.lang3.StringUtils;

public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    // 0
    // 0.1
    // 0.1.2
    // 0.1.3
    // 0.4
    public static String calculateLevel(String parentLevel, int parentId) {
        //判断是否为空或null
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            //用指定分隔符拼接元素
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
