package com.blog.admin.controller;

import com.blog.admin.annotation.PassToken;
import com.blog.admin.entity.Result;
import com.blog.admin.model.LocaleMoles;
import com.blog.admin.model.MenuModel;
import com.blog.admin.utils.JwtUtil;
import com.blog.admin.utils.MenuTreeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuyang
 */
@RestController
public class AdminController {
    @PassToken
    @PostMapping("/login")
    Result login(@RequestParam(name = "username") String username,
                 @RequestParam(name = "password") String password) {
        String token = JwtUtil.sign(1, username, "admin");
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        Map<String, String> maps = new HashMap<>();
        maps.put("token", token);

        result.setData(maps);

        return result;
    }

    @PassToken
    @PostMapping("/register")
    Result register() {
        return new Result();
    }

    @PassToken
    @GetMapping("/menu")
    Result menu() {

        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");

        List<MenuModel> menuList = new ArrayList<>();

        /*插入一些数据*/
        menuList.add(new MenuModel(1L, 0L, "系统管理", "icon", 0, "key", "/admin", 1, 1000000, 100000));
        menuList.add(new MenuModel(2L, 1L, "权限管理", "icon", 0, "key", "/admin", 1, 1000000, 100000));
        menuList.add(new MenuModel(3L, 1L, "密码修改", "icon", 0, "key", "/admin", 1, 1000000, 100000));
        menuList.add(new MenuModel(4L, 1L, "新加用户", "icon", 0, "key", "/admin", 1, 1000000, 100000));
        menuList.add(new MenuModel(5L, 1L, "系统监控", "icon", 0, "key", "/admin", 1, 1000000, 100000));
        menuList.add(new MenuModel(6L, 3L, "在线用户", "icon", 0, "key", "/admin", 1, 1000000, 100000));
        menuList.add(new MenuModel(7L, 6L, "订阅区", "icon", 0, "key", "/admin", 1, 1000000, 100000));
        menuList.add(new MenuModel(8L, 5L, "未知领域", "icon", 0, "key", "/admin", 1, 1000000, 100000));
        ;
        /*让我们创建树*/
        MenuTreeUtil menuTreeUtils = new MenuTreeUtil(menuList);
        List<MenuModel> menuTreeList = menuTreeUtils.buildTree();


        result.setData(menuTreeList);
        return result;
    }

    @PassToken
    @GetMapping("/locales")
    Result locales() {
        List<LocaleMoles> menuList = new ArrayList<>();

        menuList.add(new LocaleMoles("menu.dashboard.title","仪表盘"));
        menuList.add(new LocaleMoles("menu.article.title", "文章管理"));
        menuList.add(new LocaleMoles("menu.publish_article.title", "发表文章"));

        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");

        result.setData(menuList);
        return result;
    }

    @PassToken
    @GetMapping("/routes")
    Result routes() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");

        List<LocaleMoles> menuList = new ArrayList<>();

        menuList.add(new LocaleMoles("login/index","仪表盘"));
        result.setData(menuList);
        return result;
    }
}
