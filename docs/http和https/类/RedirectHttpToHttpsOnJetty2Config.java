package com.asiainfo.aifgw.security.oauth2;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.SecuredRedirectHandler;
import org.eclipse.jetty.util.URIUtil;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/12/23 22:28
 */
//@Configuration
public class RedirectHttpToHttpsOnJetty2Config {


    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();

        HttpToHttpsJettyConfiguration jettyConfiguration = new HttpToHttpsJettyConfiguration();
        factory.addConfigurations(jettyConfiguration);
        factory.addServerCustomizers(new JettyServerCustomizer() {
            @Override
            public void customize(Server server) {
//                final HttpConnectionFactory httpConnectionFactory = server.getConnectors()[0].getConnectionFactory(HttpConnectionFactory.class);
//
//                final ServerConnector httpConnector = new ServerConnector(server, httpConnectionFactory);
//                httpConnector.setPort(8080 /* HTTP */);
//                server.addConnector(httpConnector);


                HttpConfiguration http = new HttpConfiguration();
                http.addCustomizer(new SecureRequestCustomizer());

                // Configuration for HTTPS redirect
                http.setSecurePort(9001);
                http.setSecureScheme("https");
                ServerConnector connector = new ServerConnector(server);
                connector.addConnectionFactory(new HttpConnectionFactory(http));
                // Setting HTTP port
                connector.setPort(8080);

                server.addConnector(connector);
//

//                final HandlerList handlerList = new HandlerList();
//                handlerList.addHandler(new SecuredRedirectHandler());
//                handlerList.addHandler(jettyConfiguration.);
//                for(Handler handler : server.getHandlers())
//                    handlerList.addHandler(handler);
//                server.setHandler(handlerList);
            }
        });
        return factory;
    }



//    @Bean
    public ConfigurableServletWebServerFactory webServerFactory2() {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        factory.addServerCustomizers(new JettyServerCustomizer() {

            @Override
            public void customize(Server server) {
//                ServerConnector connector = new ServerConnector(server);
//                connector.setPort(8080);
//                server.addConnector(connector);



                HttpConfiguration http = new HttpConfiguration();
                http.addCustomizer(new SecureRequestCustomizer());

                // Configuration for HTTPS redirect
                http.setSecurePort(9001);
                http.setSecureScheme("https");
                ServerConnector connector = new ServerConnector(server);
                connector.addConnectionFactory(new HttpConnectionFactory(http));
                // Setting HTTP port
                connector.setPort(8080);

                server.addConnector(connector);

            }
        });
        return factory;
    }
}
