package com.sanxiangbank.seckill.controller;

import com.sanxiangbank.seckill.util.Common;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index_page";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "logout_page";
    }

    @GetMapping("/deposit")
    public String deposit() {
        return "deposit_service";
    }

    @GetMapping("/loan")
    public String loan() {
        return "loan_service";
    }

    @GetMapping("/registry")
    public String registry(HttpSession session) {
        return "registry_page";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact_page";
    }

    @GetMapping("/about")
    public String about() {
        return "about_page";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog_page";
    }

    @GetMapping("/myinfo")
    public String myinfo() {
        return "myinfo_page";
    }

    @GetMapping("/myorder")
    public String myOrder() {
        return "order_page";
    }


}
