package com.tsin.vueblog.service.impl;

import com.tsin.vueblog.entity.User;
import com.tsin.vueblog.mapper.UserMapper;
import com.tsin.vueblog.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
