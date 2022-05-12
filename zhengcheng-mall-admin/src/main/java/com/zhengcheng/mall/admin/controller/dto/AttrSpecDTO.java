package com.zhengcheng.mall.admin.controller.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AttrSpecDTO 商品属性，规格
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttrSpecDTO implements Serializable {
    private static final long serialVersionUID = -2443036451825285373L;

    private List<Attr>        attribute;

    private List<Spec>        spec;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Attr implements Serializable {
        private static final long serialVersionUID = 3230222718772783511L;

        private String            id;

        private String            title;

        private String            value;
        /**
         * 1-输入框，2-单选，3-多选
         */
        private String            type;

        private List<String>      options;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Spec implements Serializable {

        private static final long serialVersionUID = -8961350676837746120L;

        private String            id;

        private String            title;

        private List<String>      value;

        private List<Option>      options;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class Option implements Serializable {

            private static final long serialVersionUID = 6855641529645679221L;

            private String            id;

            private String            title;
        }
    }
}
