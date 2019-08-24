package com.lee.homework.realm;

import com.lee.homework.mapper.UserMapper;
import com.lee.homework.model.User;
import com.lee.homework.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/13 9:15
 * @Description
 **/
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.通过token对象获取用户名
        String username = (String) token.getPrincipal();
        // 2.根据用户名从数据库中查询是否存在用户
        User user = userMapper.selectUserByUsername(username);
        // 3.判断user对象是否存在
        if (user == null) {
            throw new UnknownAccountException("暂时无法找到用户，请重新输入！");
        }
        // 4.创建SimpleAuthenticationInfo对象
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        // 5.把用户存入到session中(把密码置空)
        Session session = SecurityUtils.getSubject().getSession();
        user.setPassword(null);
        session.setAttribute("user", user);
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        // 1.根据PrincipalCollection对象获取认证阶段传递过来的参数
        String username = (String)principal.getPrimaryPrincipal();
        // 2.根据用户名查询所有的角色信息
        List<String> roleList = userMapper.selectRoleByUsername(username);
        // 3.判断roleList是否为null
        // 4.创建SimpleAuthorizationInfo对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 5.调用menuService获取到角色信息的List集合的长度
        Map<String, Object> mapResult = menuService.selectMenusByUsername(username);
        if(roleList.size() > 0) {
            if(roleList.size() == (Integer)mapResult.get("rolesSize")) {
                // 该用户下有角色
                info.addRoles(roleList);
            } else {
                return null;
            }

        }
        // 5.通过username查询出所有的权限信息
        List<String> permissionList = userMapper.selectPermissionByUsername(username);
        if(permissionList.size() > 0) {
            info.addStringPermissions(permissionList);
        }
        return info;
    }
}
