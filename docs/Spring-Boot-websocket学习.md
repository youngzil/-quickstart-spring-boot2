https://github.com/sockjs/sockjs-client
https://github.com/sockjs/sockjs-protocol

https://sockjs.github.io/sockjs-protocol/sockjs-protocol-0.3.html

https://spring.io/guides/gs/messaging-stomp-websocket/


https://www.oschina.net/p/sockjs
https://pypi.org/project/sockjs/
https://www.npmjs.com/package/sockjs

http://stomp.github.io/stomp-specification-1.1.html






websocket服务：后台 + 前台


后台实现websocket有两种方式：使用继承类、使用注解；注解方式比较方便，一下代码中使用注解方式来进行演示。

声明websocket地址类似Spring MVC中的@controller注解类似，websocket使用@ServerEndpoint来进行声明接口：@ServerEndpoint(value="/websocket/{paraName}") ; 其中 “ { } ”用来表示带参数的连接，如果需要获取{}中的参数在参数列表中增加：@PathParam("paraName") Integer userId 。则连接地址形如：ws://localhost:8080/project-name/websocket/8，其中每个连接可以设置不同的paraName的值。

注解、成员数据介绍：
1.@OnOpen
public void onOpen(Session session) throws IOException{ } -------有连接时的触发函数。 我们可以在用户连接时记录用户的连接带的参数，只需在参数列表中增加参数：@PathParam("paraName") String paraName。

2.@OnClose
public void onClose(){ } ------连接关闭时的调用方法。

3.@OnMessage
public void onMessage(String message, Session session) { } -------收到消息时调用的函数，其中Session是每个websocket特有的数据成员，详情见4.

4.Session ----每个Session代表了两个web socket断点的会话；当websocket握手成功后，websocket就会提供一个打开的Session，可以通过这个Session来对另一个端点发送数据；如果Session关闭后发送数据将会报错。

5.Session.getBasicRemote().sendText("message") -------向该Session连接的用户发送字符串数据。

6.@OnError
public void onError(Session session, Throwable error) { } --------发生意外错误时调用的函数。


使用websocket有两种方式：1是使用sockjs，2是使用h5的标准。使用Html5标准自然更方便简单，所以记录的是配合h5的使用方法。


1.WebSocket,STOMP,SockJS含义
WebSocket：WebSocket是HTML5开始提供的一种在单个 TCP 连接上进行全双工通讯的协议。

SockJS:SockJS 是 WebSocket 技术的一种模拟。为了应对许多浏览器不支持WebSocket协议的问题，设计了备选SockJs。开启并使SockJS后，它会优先选用Websocket协议作为传输协议，如果浏览器不支持Websocket协议，则会在其他方案中，选择一个较好的协议进行通讯。

STOMP:用于定义websocket的消息体格式.



SockJS是一个JavaScript库，提供跨浏览器JavaScript的API，创建了一个低延迟、全双工的浏览器和web服务器之间通信通道。
服务端：sockjs-node（https://github.com/sockjs/sockjs-node）
客户端：sockjs-clien（https://github.com/sockjs/sockjs-client）
node-static（https://www.npmjs.com/package/node-static）是Node.js兼容HTTP静态文件服务器处理模块，提供内置的缓存支持。


SockJS：
SockJS 是一个浏览器上运行的 JavaScript 库，如果浏览器不支持 WebSocket，该库可以模拟对 WebSocket 的支持，实现浏览器和 Web 服务器之间低延迟、全双工、跨域的通讯通道。
Stomp
Stomp 提供了客户端和代理之间进行广泛消息传输的框架。Stomp 是一个非常简单而且易用的通讯协议实现，尽管代理端的编写可能非常复杂，但是编写一个 Stomp 客户端却是很简单的事情，另外你可以使用 Telnet 来与你的 Stomp 代理进行交互。




一.WebSocket简单介绍
　　随着互联网的发展，传统的HTTP协议已经很难满足Web应用日益复杂的需求了。近年来，随着HTML5的诞生，WebSocket协议被提出，它实现了浏览器与服务器的全双工通信，扩展了浏览器与服务端的通信功能，使服务端也能主动向客户端发送数据。

　　我们知道，传统的HTTP协议是无状态的，每次请求（request）都要由客户端（如 浏览器）主动发起，服务端进行处理后返回response结果，而服务端很难主动向客户端发送数据；这种客户端是主动方，服务端是被动方的传统Web模式 对于信息变化不频繁的Web应用来说造成的麻烦较小，而对于涉及实时信息的Web应用却带来了很大的不便，如带有即时通信、实时数据、订阅推送等功能的应 用。在WebSocket规范提出之前，开发人员若要实现这些实时性较强的功能，经常会使用折衷的解决方法：轮询（polling）和Comet技术。其实后者本质上也是一种轮询，只不过有所改进。

　　轮询是最原始的实现实时Web应用的解决方案。轮询技术要求客户端以设定的时间间隔周期性地向服务端发送请求，频繁地查询是否有新的数据改动。明显地，这种方法会导致过多不必要的请求，浪费流量和服务器资源。

　　Comet技术又可以分为长轮询和流技术。长轮询改进了上述的轮询技术，减小了无用的请求。它会为某些数据设定过期时间，当数据过期后才会向服务端发送请求；这种机制适合数据的改动不是特别频繁的情况。流技术通常是指客户端使用一个隐藏的窗口与服务端建立一个HTTP长连接，服务端会不断更新连接状态以保持HTTP长连接存活；这样的话，服务端就可以通过这条长连接主动将数据发送给客户端；流技术在大并发环境下，可能会考验到服务端的性能。

　　这两种技术都是基于请求-应答模式，都不算是真正意义上的实时技术；它们的每一次请求、应答，都浪费了一定流量在相同的头部信息上，并且开发复杂度也较大。

　　伴随着HTML5推出的WebSocket，真正实现了Web的实时通信，使B/S模式具备了C/S模式的实时通信能力。WebSocket的工作流程是这 样的：浏览器通过JavaScript向服务端发出建立WebSocket连接的请求，在WebSocket连接建立成功后，客户端和服务端就可以通过 TCP连接传输数据。因为WebSocket连接本质上是TCP连接，不需要每次传输都带上重复的头部数据，所以它的数据传输量比轮询和Comet技术小 了很多。本文不详细地介绍WebSocket规范，主要介绍下WebSocket在Java Web中的实现。

　　JavaEE 7中出了JSR-356:Java API for WebSocket规范。不少Web容器，如Tomcat,Nginx,Jetty等都支持WebSocket。Tomcat从7.0.27开始支持 WebSocket，从7.0.47开始支持JSR-356，下面的Demo代码也是需要部署在Tomcat7.0.47以上的版本才能运行。





参考
https://blog.csdn.net/ligang2585116/article/details/47277917
https://www.cnblogs.com/betterboyz/p/8669879.html
https://blog.csdn.net/qq_28988969/article/details/78113463
https://spring.io/guides/gs/messaging-stomp-websocket/
https://blog.csdn.net/tengxing007/article/details/77961355
https://blog.csdn.net/j903829182/article/details/78342941
https://blog.csdn.net/u012702547/article/details/53816326


