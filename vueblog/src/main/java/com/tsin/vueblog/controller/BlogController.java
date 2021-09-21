package com.tsin.vueblog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsin.vueblog.common.lang.Result;
import com.tsin.vueblog.entity.Blog;
import com.tsin.vueblog.service.BlogService;
import com.tsin.vueblog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tsin
 * @since 2021-09-12
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * 查找所有
     *
     * @param currentPage 当前页
     * @return 页面信息
     */
    @GetMapping("find")
    public Result find(Integer currentPage, Integer pageSize) {
        if (currentPage == null || currentPage < 1) currentPage = 1;
        IPage<Blog> iPage = new Page<>(currentPage, pageSize);
        IPage<Blog> page = blogService.page(iPage, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        return Result.success(blog);
    }


    @PostMapping("/edit")
    @RequiresAuthentication
    public Result edit(@Validated @RequestBody Blog blog) {
        System.out.println(blog.toString());
        Blog temp = null;
        //编辑
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
        } else {
            //发表
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return Result.success("操作成功");
    }


}
