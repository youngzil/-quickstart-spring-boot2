package com.asiainfo.aifgw.security.oauth2;

import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/12/23 19:33
 */
//@Configuration
public class JettyConfig {
    @Value("${server2.ssl.key-store}")
    private String keyStore;
    @Value("${server2.ssl.key-store-password}")
    private String keyStorePassword;
    @Value("${server2.ssl.key-store-type}")
    private String keyStoreType;
    @Value("${server2.ssl.key-alias}")
    private String keyAlias;
    @Value("${server2.ssl.key-password}")
    private String keyPassword;
    @Value("${server.port}")
    private int port;

    /**
     * 通过构造工厂造1个jetty
     */
    @Bean
    public ServletWebServerFactory servletContainer() {
        JettyServletWebServerFactory jetty = new JettyServletWebServerFactory();
        customizeJetty(jetty);
        return jetty;
    }

    private void customizeJetty(JettyServletWebServerFactory container) {

        container.addServerCustomizers(new JettyServerCustomizer() {

            @Override
            public void customize(Server server) {

                // HTTP
                ServerConnector connector = new ServerConnector(server);
                connector.setPort(8080);

                // 添加HTTPS配置
                SslContextFactory sslContextFactory = new SslContextFactory();
                sslContextFactory.setKeyStorePath(keyStore);
                sslContextFactory.setKeyStorePassword(keyStorePassword);
                sslContextFactory.setKeyStoreType(keyStoreType);

                HttpConfiguration https = new HttpConfiguration();
                https.addCustomizer(new SecureRequestCustomizer());

                ServerConnector sslConnector = new ServerConnector(
                    server,
                    new SslConnectionFactory(sslContextFactory, "http/1.1"),
                    new HttpConnectionFactory(https));
                sslConnector.setPort(8443);

                server.setConnectors(new Connector[]{connector, sslConnector});

            }
        });
    }

    /**
     * 为jetty服务器开通http端口和https,并配置线程
     */
    private void customizeJetty2(JettyServletWebServerFactory container) {
        container.addServerCustomizers((Server server) -> {
            //配置线程
            threadPool(server);

            // 添加HTTP配置
            //            ServerConnector connector = new ServerConnector(server);

//            HttpConfiguration config2 = new HttpConfiguration();
//            config2.setSecureScheme(HttpScheme.HTTP.asString());
//
//            ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(config2));
//            connector.setPort(8888);

            //            Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
            //
            //            connector.setScheme("http");
            //
            //            connector.setPort(8080);
            //
            //            connector.setSecure(false);
            //
            //            connector.setRedirectPort(8033);
            //
            //            return connector;

            //            Server server = new Server();
            //
            //            //Create a connector on port 80 to listen for HTTP requests
            //            SelectChannelConnector httpConnector = new SelectChannelConnector();
            //            httpConnector.setPort(80);
            //            server.addConnector(httpConnector);

            //            //Create a connector on port 443 to listen for HTTPS requests
            //            SslSocketConnector httpsConnector = new SslSocketConnector();
            //            httpsConnector.setPort(443);
            //            httpsConnector.setKeystore("name_of_the_keystore");
            //            httpsConnector.setPassword("password_for_the_keystore");
            //            httpsConnector.setKeyPassword("password_for_the_key");
            //            server.addConnector(httpsConnector);
            //
            //            //Redirect the HTTP requests to HTTPS port
            //            httpConnector.setConfidentialPort(443);




            // HTTP Configuration
            HttpConfiguration http = new HttpConfiguration();
            http.addCustomizer(new SecureRequestCustomizer());

            // Configuration for HTTPS redirect
            http.setSecurePort(port);
            http.setSecureScheme("https");
            ServerConnector connector = new ServerConnector(server);
            connector.addConnectionFactory(new HttpConnectionFactory(http));
            // Setting HTTP port
            connector.setPort(8080);


            // 添加HTTPS配置
            SslContextFactory sslContextFactory = new SslContextFactory();
            sslContextFactory.setKeyStorePath(keyStore);
            sslContextFactory.setKeyStorePassword(keyStorePassword);
            sslContextFactory.setKeyStoreType(keyStoreType);

            // HTTPS configuration
            HttpConfiguration httpsConfig = new HttpConfiguration();
            httpsConfig.setSecureScheme(HttpScheme.HTTPS.asString());
            httpsConfig.addCustomizer(new SecureRequestCustomizer());

            ServerConnector sslConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                new HttpConnectionFactory(httpsConfig));
            sslConnector.setPort(port);

            // Setting HTTP and HTTPS connectors
//            server.setConnectors(new Connector[] {connector});
            server.setConnectors(new Connector[] {connector, sslConnector});
        });
    }

    /**
     * jetty线程配置
     */
    private void threadPool(Server server) {
        // connections
        final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
        //默认最大线程连接数200
        threadPool.setMaxThreads(300);
        //默认最小线程连接数8
        threadPool.setMinThreads(15);
        //默认线程最大空闲时间60000ms
        threadPool.setIdleTimeout(60000);
    }

    /**
     * 配置文件上传
     */
   /* @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("100MB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("200MB");
        return factory.createMultipartConfig();
    }*/

}
