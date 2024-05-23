package com.gnahz.pojo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 张伟洁
 * Date:2024-03-17-19:00
 * @create 忆项目(小白)
 */
@AllArgsConstructor
@Getter
public enum YesOrNoEnums {
    YES(0,"是"),
    No(1,"否"),
    ;

    private final Integer status;
    private final String desc;
}
