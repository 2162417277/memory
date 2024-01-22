package com.gnahz.domin;

import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author 张伟洁
 * Date:2024-01-22-9:21
 * @create 忆项目(小白)
 * 用户信息
 * 用户是否启用
 */
public class MyUserDetails implements UserDetails {

    // 用户信息
    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 用户是否启用状态
     * @return
     */
    @Override
    public boolean isEnabled() {
        return user.getUserLogic() == 0;
    }

    public User getUser() {
        return user;
    }
}
