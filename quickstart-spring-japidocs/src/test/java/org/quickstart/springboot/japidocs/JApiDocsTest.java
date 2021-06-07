package org.quickstart.springboot.japidocs;


import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/8/13 14:30
 */
public class JApiDocsTest {

  // 用不起来，报错

  public static void main(String[] args) {
    DocsConfig config = new DocsConfig();
    config.setProjectPath("your springboot project path"); // root project path
    config.setProjectName("ProjectName"); // project name
    config.setApiVersion("V1.0");       // api version
    config.setDocsPath("your api docs path"); // api docs target path
    config.setAutoGenerate(Boolean.TRUE);  // auto generate
    // Docs.buildHtmlDocs(config); // execute to generate

    // DocsConfig config = new DocsConfig();
    config.setProjectPath("/Users/yangzl/git/quickstart-spring-boot2/quickstart-spring-japidocs"); // 项目根目录
    config.setProjectName("security"); // 项目名称
    config.setApiVersion("V1.0");       // 声明该API的版本
    config.setDocsPath("/Users/yangzl/apidoc"); // 生成API 文档所在目录
    config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
    Docs.buildHtmlDocs(config); // 执行生成文档
  }

}
