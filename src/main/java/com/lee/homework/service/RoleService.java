package com.lee.homework.service;

import com.lee.homework.mapper.RoleMapper;
import com.lee.homework.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/13 16:35
 * @Description
 **/
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Map<String, Object> selectAllRoles() {
        List<Role> roleList = roleMapper.selectAll();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(roleList.size() > 0) {
            resultMap.put("code", 200);
            resultMap.put("result", roleList);
        } else {
            resultMap.put("code", 400);
        }
        return resultMap;
    }

}
