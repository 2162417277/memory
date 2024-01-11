package com.gnahz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gnahz.pojo.Past;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:37
 * @create 忆项目(小白)
 */
@Mapper
public interface PastMapper extends BaseMapper<Past> {
    /**
     * 根据条件传入消息,为null或为空表示未修改
     * @param id
     * @param pastTheme
     * @param pastContent
     * @param pastVideo
     * @param pastOldTime
     */
    void PastUpdate(@Param("id") Integer id
                    , @Param("pastTheme") String pastTheme
                    , @Param("pastContent") String pastContent
                    , @Param("pastVideo")String pastVideo
                    , @Param("pastOldTime")Date pastOldTime);
}
