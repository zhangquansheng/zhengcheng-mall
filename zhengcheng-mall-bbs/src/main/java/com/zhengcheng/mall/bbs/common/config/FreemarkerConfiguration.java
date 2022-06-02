package com.zhengcheng.mall.bbs.common.config;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.zhengcheng.mall.bbs.common.constant.BBSContant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * FreemarkerConfiguration
 *
 * @author :    zhangquansheng
 * @date :    2020/1/8 11:33
 */
@Slf4j
@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreemarkerConfiguration {

    @Autowired
    private freemarker.template.Configuration configuration;
    @Autowired
    private ServletContext                    servletContext;

    @PostConstruct
    public void setConfigure() throws Exception {
        configuration.setSharedVariable("base", servletContext.getContextPath());
        configuration.setSharedVariable("isAuthenticated", Boolean.FALSE);
        configuration.setSharedVariable("allJieCategoryId", BBSContant.ALL_JIE_CATEGORY_ID);
    }
}
