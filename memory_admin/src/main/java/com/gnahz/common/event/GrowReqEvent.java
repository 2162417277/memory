package com.gnahz.common.event;


import com.gnahz.vo.req.GrowReq;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author 张伟洁
 * Date:2024-03-17-13:23
 * @create 忆项目(小白)
 */
@Getter
public class GrowReqEvent extends ApplicationEvent {
    private GrowReq growReq;
    public GrowReqEvent(Object source,GrowReq growReq) {
        super(source);
        this.growReq = growReq;
    }

}
