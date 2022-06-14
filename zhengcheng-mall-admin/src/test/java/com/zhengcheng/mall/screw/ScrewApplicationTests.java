package com.zhengcheng.mall.screw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;

/**
 * ScrewApplicationTests
 *
 * @author quansheng1.zhang
 * @since 2022/5/10 18:20
 */
@SpringBootTest
public class ScrewApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        DataSource dataSourceMysql = applicationContext.getBean(DataSource.class);

        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("F:\\sql")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.HTML)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        // 生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder().version("1.0.4").description("生成文档信息描述")
                .dataSource(dataSourceMysql).engineConfig(engineConfig).produceConfig(getProcessConfig()).build();

        // 执行生成
        new DocumentationExecute(config).execute();
    }

    /**
     * 配置想要生成的表+ 配置想要忽略的表
     * @return 生成表配置
     */
    public static ProcessConfig getProcessConfig() {
        // 忽略表名
        List<String> ignoreTableName = Arrays.asList("base", "house", "leaf_alloc");
        // 忽略表前缀，如忽略a开头的数据库表
        //        List<String> ignorePrefix = Arrays.asList();
        // 忽略表后缀
        //        List<String> ignoreSuffix = Arrays.asList("_test", "czb_");

        return ProcessConfig.builder()
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(Arrays.asList("bbs_"))
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                //                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                //                .ignoreTableSuffix(ignoreSuffix)
                .build();
    }
}
