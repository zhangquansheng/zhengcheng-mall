package com.zhengcheng.mall.bbs.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * 帖子专栏
 *
 * @author zqs
 * @version 3.0
 */
@Entity
@Table(name = "jie_category")
public class JieCategory extends OrderEntity {

    private static final long serialVersionUID = 7632857280017649740L;
    /**
     * 名称
     */
    private String            name;

    /**
     * 帖子
     */
    private Set<Jie>          jies             = new HashSet<Jie>();

    /**
     * 获取名称
     *
     * @return 名称
     */
    @Column(nullable = false, length = 100)
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取帖子
     *
     * @return 帖子
     */
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Jie> getJies() {
        return jies;
    }

    /**
     * 设置帖子
     *
     * @param jies 帖子
     */
    public void setJies(Set<Jie> jies) {
        this.jies = jies;
    }
}
