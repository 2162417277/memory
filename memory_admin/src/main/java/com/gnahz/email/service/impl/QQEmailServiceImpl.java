package com.gnahz.email.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gnahz.email.utils.EmailCommonsUtil;
import com.gnahz.mapper.GrowMapper;
import com.gnahz.pojo.Grow;
import com.gnahz.service.GrowService;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;



/**
 * @Author 张伟洁
 * Date:2024-01-28-17:52
 * @create 忆项目(小白)
 */

@Service
@Slf4j
@AllArgsConstructor
public class QQEmailServiceImpl {

    private final EmailCommonsUtil emailCommonsUtil;

    @Autowired
    GrowMapper growMapper;

    @Autowired
    GrowService growService;

    /**
     * 可上传邮件带html样式的还有图片
     * @param subjects
     * @param htmlS
     * @param mail
     * */

    public void sendCommonEmail(String subjects,String htmlS,String mail) {
        String subject = subjects;
        String html = htmlS;
        String[] toMail = new String[]{mail};
        String[] ccMail = new String[]{"2162417277@qq.com"};
        //File file = new File("C:\\Users\\Administrator\\Desktop\\11.jpg");
        try {
            emailCommonsUtil.sendEmail(subject, html, true, toMail, ccMail, null,null);
        } catch (EmailException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 邮箱不是html形式
     * @param format1
     */
    public void sendCommonEmaill(String format1) {
        List<String> mysqlNewDate = growService.InsertMysqlNewDate();

        // TODO  完成 根据输出语句发送邮件成功:629283068@qq.com，也就是说里面有循环，本来应该执行几次的只执行了一次
        for (String newDate : mysqlNewDate) {
            if(newDate.equals(format1)){
                QueryWrapper<Grow> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(Grow::getGrowNewTime,format1);
                List<Grow> grows = growMapper.selectList(queryWrapper);
                for (int i = 0; i < grows.size(); i++) {
                    String growTheme = grows.get(i).getGrowTheme();
                    String growContent = grows.get(i).getGrowContent();
                    String growMail = grows.get(i).getGrowMail();
                    String subject = growTheme;
                    String html = growContent;
                    String[] toMail = new String[]{growMail};
                    String[] ccMail = new String[]{"2162417277@qq.com"};
                    try {
                        emailCommonsUtil.sendEmail(subject, html, false, toMail, ccMail, null,null);
                        QueryWrapper<Grow> queryWrapper1 = new QueryWrapper<>();
                        queryWrapper1.lambda()
                                .eq(Grow::getGrowMail, toMail)
                                .eq(Grow::getGrowNewTime, format1);
                        growMapper.delete(queryWrapper);
                    } catch (EmailException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }

    }



    public boolean EmailTest(){
        String subject = "这是一个测试标题";
        String html = "<h1>统计数据如下所示：</h1>" +
                "<table border=\"1\">\n" +
                "  <tr>\n" +
                "    <th>月度销售额</th>\n" +
                "    <th>年度销售额</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>10000</td>\n" +
                "    <td>2000000</td>\n" +
                "  </tr>\n" +
                "</table>";
        String[] toMail = new String[]{"2214429990@qq.com","2162417277@qq.com","629283068@qq.com"};
        String[] ccMail = new String[]{"2162417277@qq.com"};
        File file = new File("C:\\Users\\Administrator\\Desktop\\11.jpg");
        try {
            emailCommonsUtil.sendEmail(subject,html,true,toMail,ccMail,null,new File[]{file});
        } catch (EmailException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
