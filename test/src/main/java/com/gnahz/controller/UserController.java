package com.gnahz.controller;



import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.gnahz.pojo.UserPO;
import com.gnahz.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @Author 张伟洁
 * Date:2024-01-14-19:53
 * @create 忆项目(小白)
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/page")
    public Page<UserPO> selectPage(String name, Integer currentPage, Integer pageSize) {
        Page<UserPO> userPage = new Page<>(currentPage, pageSize);
        return new LambdaQueryChainWrapper<>(userService.getBaseMapper())
                .like(StringUtils.isNotBlank(name), UserPO::getName,  name).orderBy(true, false, UserPO::getCreateTime).page(userPage);
    }


    @GetMapping("/info/{id}")
    public UserPO info(@PathVariable String id) {
        return userService.getById(id);
    }

    @PostMapping("/save")
    public Boolean userSave(@RequestBody UserPO user) {
        user.setCreateTime(new Date());
        return userService.save(user);
    }

    @PostMapping("/update")
    public Boolean userUpdate(@RequestBody UserPO user) {
        return userService.updateById(user);
    }

    @PostMapping("/delete")
    public Boolean userDelete(@RequestBody List<String> ids) {
        return userService.removeByIds(ids);
    }
}
