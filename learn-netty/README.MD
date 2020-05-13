同步---异步
阻塞---非阻塞
##### java I/O模型
    1. BIO  同步阻塞
        编程流程：
        服务端启动ServerSocket
        服务端会阻塞在accept()方法中，直到有连接
        客户端连接Server
        然后服务端会阻塞在read()方法中，直到连接断开
    2. NIO  同步非阻塞
        通过缓冲区实现非阻塞
        通道（Channel）
        缓冲区（Buffer）
        选择器（Selector）
        缓冲区主要针对的对象是通道，
        通道可以往缓冲区中读或者写数据
        选择器针对的是通道，可以监听通道的事件，
        来达到及时处理，不会浪费资源的目的
        
        1. 每个Channel对应一个buffer
        2. 一个Selector会对应一个线程
        3. 一个线程可以对应多个Channel
        4. 一个Channel可以理解为一个连接
        5. 多个Channel可以注册到Selector上
        6. Selector选择哪个Channel，是由事件决定的
        7. Buffer就是一块内存块，低层是一个数组
        8. 数据的读取和写入都是通过Buffer，通过Buffer的flip()方法进行切换
        9. Channel是双向的，和流不一样
        
        Channel有：
            FileChannel：用于文件的读写
            DataGramChannel：用于UDP的数据读写
            ServerSocketChannel和SocketChannel：用于TCP的数据读写
            
        一般情况下，当客户端连接服务端时，服务端会有一个ServerSocketChannel
        然后这个ServerSocketChannel会生成一个和这个客户端绑定的SocketChannel
        然后将这个SocketChannel注册到Selector上
            
                
        
        
    3. AIO  异步非阻塞
http 2.0 多路复用，一个连接同时带有多个请求
Ctrl + h 查看子类

<span style="color:red">问题：服务器在读取Channel中的数据时，也是通过Buffer的吗？</span>

    //FIXME 注意的是，读和写的方向，还有输入和输出的对应方向
    //FIXME 输出对应的是写针对于某个目标对象来说是入，另外两个相反
