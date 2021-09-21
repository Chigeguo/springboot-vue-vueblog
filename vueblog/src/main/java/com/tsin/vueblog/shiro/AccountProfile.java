package com.tsin.vueblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tsin
 * @since 2021/9/12-18:54
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
    private String avatar;
}

