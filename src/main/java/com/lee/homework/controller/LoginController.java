package com.lee.homework.controller;

import com.lee.homework.model.Book;
import com.lee.homework.model.Role;
import com.lee.homework.model.User;
import com.lee.homework.model.vo.BookVo;
import com.lee.homework.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/13 9:15
 * @Description
 *      在realm的授权阶段查出的角色信息可能和认证成功后在controller查询出的角色信息不一样，如何处理？
 **/
@Controller
public class LoginController {

    @Autowired
    private MenuService menuServicel;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String turnIndexPage(ModelMap modelMap) {
        // 通过角色查询出所有的菜单信息
        // 通过service查询出所有的该用户的角色菜单
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
        Map<String, Object> resultMap = menuServicel.selectMenusByUsername(redisService, user.getUsername());
        String role = "";
        if(200 == (Integer) resultMap.get("code")) {
            Map<String, Object> resultMap2 = userService.selectRolesByUsername(user.getUsername());
            if(200 == (Integer)resultMap2.get("code")) {
                List<String> result = (List<String>)resultMap2.get("result");
                for(String roles : result) {
                    // 第一次循环book_manager
                    // 第二次循环book_manageruser_manager
                    // 最终结果：role-->"book_manager,user_manager"
                    role = role + roles+",";

                }
                // role:book_manager,user_manager,
                // 自己研究substring的-1截取
                role = role.substring(0 ,role.lastIndexOf(",")).trim();
                modelMap.addAttribute("role", role);
            }
            List<String> result = (List<String>)resultMap.get("result");
            modelMap.addAttribute("menuList", result);
        } else {
            return "error";
        }
        return "index";
    }

    @RequestMapping("/turnPage")
    public String turnPage(String menuName, ModelMap modelMap) {
        String menuResult = menuServicel.selectMenuNameByChinese(menuName);
        String[] menus = menuResult.split("_");
        String tableName = menus[0];
        // menuResult:user  book
        List<BookVo> bookList = bookService.selectAll(tableName);
        if(bookList.size() > 0) {
            modelMap.addAttribute("bookList", bookList);
            modelMap.addAttribute("tableName", tableName);
        }
        return "list";
    }

}
