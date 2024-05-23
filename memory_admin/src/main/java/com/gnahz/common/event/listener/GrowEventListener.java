package com.gnahz.common.event.listener;


import com.gnahz.common.event.GrowReqEvent;

import com.gnahz.email.service.impl.NetEaseServiceImpl;
import com.gnahz.vo.req.GrowReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-03-17-13:28
 * @create 忆项目(小白)
 */
@Component
public class GrowEventListener {

    @Autowired
    private NetEaseServiceImpl netEaseService;

    @Async
    @TransactionalEventListener(classes = GrowReqEvent.class,phase = TransactionPhase.AFTER_COMMIT)
    public void send(GrowReqEvent event){
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        // 在当前时间上加一个月
        calendar.add(Calendar.MONTH,1);
        Date MonThTime = calendar.getTime();
        // 格式化日期输出
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String TwoTime = sdf.format(MonThTime);
        GrowReq growReq = event.getGrowReq();
        netEaseService.CurrentMail(TwoTime,growReq.getGrowTheme(),growReq.getGrowMail());
    }

}
