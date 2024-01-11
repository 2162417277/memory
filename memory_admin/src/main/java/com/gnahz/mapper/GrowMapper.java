package com.gnahz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gnahz.pojo.Grow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:37
 * @create 忆项目(小白)
 */
@Mapper
public interface GrowMapper extends BaseMapper<Grow> {
     List<Grow> queryAllByPage(@Param("id")Integer id);

    /**
     * 主题,内容,视频\图片,发送时间,邮件地址,手机号,写者姓名,发送者姓名
     * @param growId
     * @param growTheme
     * @param growContent
     * @param growVideo
     * @param growOldTime
     * @param growNewTime
     * @param growMail
     * @param growTelephone
     * @param writeName
     * @param readName
     */
    void GrowUpdate(@Param("growId") Integer growId,//表ID
                    @Param("growTheme") String growTheme,//主题,
                    @Param("growContent") String growContent,//内容,
                    @Param("growVideo") String growVideo,//视频\图片,
                    @Param("growOldTime") Date growOldTime,//创建时间
                    @Param("growNewTime") Date growNewTime,//发送时间,
                    @Param("growMail") String growMail,//邮件地址,
                    @Param("growTelephone") String growTelephone,//手机号,
                    @Param("writeName") String writeName,//写者姓名,
                    @Param("readName") String readName);//发送者姓名
}
