# Spring Interceptor

* In Spring, when a request is sent to spring controller, 
  it will have to pass through Interceptors (0 or more) before being processed by Controller.
* Spring Interceptor is a concept that is rather similar to Servlet Filter. 
* Spring Interceptor is only applied to requests that are sending to a Controller.

For example, you can use an interceptor to add the request header before sending the
request to the controller and add the response header before sending the response to the client.

To work with interceptor,
you need to create @Component class that supports it and it should implement the HandlerInterceptor interface.

The following are the three methods you should know about while working on Interceptors −

* preHandle() method − This is used to perform operations before sending the request to the controller. This method should return true to return the response to the client.
* postHandle() method − This is used to perform operations before sending the response to the client.
* afterCompletion() method − This is used to perform operations after completing the request and response.

```
                |
Request         |
======>  (1)    |-------------------->   PreHandle (2)   --------------->                            
                |
Response        |
<======  (5)    |                        PostHandle (4)  --------------->      MainController (3)
                |
                |      VIEW     <-------
                |
                |
                |                      afterCompletion (6) ---------------> 



```
