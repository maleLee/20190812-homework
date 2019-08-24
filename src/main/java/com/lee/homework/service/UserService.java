package com.lee.homework.service;

import com.lee.homework.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/13 16:02
 * @Description
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> selectRolesByUsername(String username) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<String> roleList = userMapper.selectRoleByUsername(username);
        if(roleList.size() > 0) {
            resultMap.put("code", 200);
            resultMap.put("result", roleList);
        } else {
            resultMap.put("code", 500);
        }
        return resultMap;
    }

}
