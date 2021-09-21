package com.tsin.vueblog.service.impl;

import com.tsin.vueblog.entity.Blog;
import com.tsin.vueblog.mapper.BlogMapper;
import com.tsin.vueblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tsin
 * @since 2021-09-12
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
