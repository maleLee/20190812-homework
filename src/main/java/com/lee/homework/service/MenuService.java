package com.lee.homework.service;

import com.lee.homework.mapper.MenuMapper;
import com.lee.homework.mapper.UserMapper;
import com.lee.homework.utils.JSONUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/13 9:36
 * @Description
 **/
@Service
public class MenuService {

    @Value("${menu_key}")
    private String menu;

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @author Seven Lee
     * @description
     *      在数据库中查询菜单信息
     * @param [username]
     * @date 2019/8/13
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @throws
    **/
    public Map<String, Object> selectMenusByUsername(String username) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 1.查询出所有的角色信息
        List<String> roleList = userMapper.selectRoleByUsername(username);
        // 2.根据角色信息去查询菜单信息
        List<String> menuList = menuMapper.selectMenusByUsername(username);
        if(menuList.size() > 0) {
            resultMap.put("code", 200);
            resultMap.put("result", menuList);
            resultMap.put("rolesSize", roleList.size());
        } else {
            resultMap.put("code", 404);
        }
        return resultMap;

    }

    /**
     * @author Seven Lee
     * @description
     *      从缓存中取出菜单信息
     *      menu:图书管理
     *      图书管理员登录--->没有问题图书管理
     *      学生管理员登录--->图书管理菜单
     *      动态获取redis的key(根据用户所登录的角色获取redis的key--->拿到menu信息)
     *      1.动态实现redis的key
     *      2.shiro角色的double check
     * @param [username]
     * @date 2019/8/13
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @throws
    **/
    @RequiresRoles({""})
    public Map<String, Object> selectMenusByUsername(RedisService redisService, String username) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 1.从redis中取出菜单信息
        String menuString = redisService.get(menu);
        if("".equals(menuString) || null == menuString) {
            // 2.从数据库中根据用户名查询出所有的菜单信息
            List<String> menuList = menuMapper.selectMenusByUsername(username);
            // 3.判断menuList的长度(需要知道数据库中有没有数据)
            if(menuList.size() > 0) {
                // 数据库中有菜单数据
                String setResult = redisService.set(menu, JSONUtil.toJSONString(menuList));
                // 4.继续判断，判断是否存入成功
                if("OK".equals(setResult.toUpperCase())) {
                    resultMap.put("code", 200);
                    resultMap.put("result", menuList);
                } else {
                    redisService.set(menu, JSONUtil.toJSONString(menuList));
                    // 不需要继续处理！！！
                    resultMap.put("code", 200);
                    resultMap.put("result", menuList);
                }
            } else {
                resultMap.put("code", 404);
            }
        } else {
            resultMap.put("code", 200);
            resultMap.put("result", JSONUtil.parseArray(menuString, String.class));
        }
        return resultMap;
    }

    public String selectMenuNameByChinese(String menuChineseName) {
        return menuMapper.selectMenuNameByChinese(menuChineseName);
    }

}
