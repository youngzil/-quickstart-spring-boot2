

Tomcat9.0.45


有AbstractEndpoint处理：3个实现
AprEndpoint
Nio2Endpoint
NioEndpoint

Processor处理：
Http11Processor
。。。


SocketProcessorBase

SocketProcessorBase.run
ApplicationFilterChain.doFilter ------> internalDoFilter ------> filter.doFilter ------> ApplicationFilterChain.doFilter
                                                         ------> DispatcherServlet.service ------> HandlerAdapter.handle(具体实现RequestMappingHandlerAdapter)（然后invokeHandlerMethod） ------> ServletInvocableHandlerMethod.invokeAndHandle(处理实际调用并且获取处理结果returnValue）（invokeForRequest：1、getMethodArgumentValues，2、doInvoke（反射调用））) ------> HandlerMethodReturnValueHandler.handleReturnValue(具体实现HandlerMethodReturnValueHandlerComposite) ------> RequestResponseBodyMethodProcessor.handleReturnValue



