package com.kosi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/notice/list")
    public String noticePage() {
        return "/board/notice/list";
    }

    @GetMapping("/qna/list")
    public String qnaPage() {
        return "/board/qna/list";
    }

    /**
     * Business Registration Certificate
     * @return
     */
    @GetMapping("/brc/list")
    public String brcPage() {
        return "/board/brc/list";
    }

    @GetMapping("/faq/list")
    public String faqPage() {
        return "/board/faq/list";
    }

}
