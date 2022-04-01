package com.sanxiangbank.seckill.controller;

import com.sanxiangbank.seckill.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String index() {
        return "index_page";
    }

    @GetMapping("/deposit")
    public String deposit() {
        return "deposit_service";
    }

    @GetMapping("/loan")
    public String loan() {
        return "loan_service";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact_page";
    }

    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        session.setAttribute(Common.CUR_USER,null);
        return "redirect:index_page";
    }

}
