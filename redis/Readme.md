#Redis 安装
###1.window
**redis-server.exe  redis.windows.conf**
另起一个cmd窗口,进入redis目录 使用命令 **redis-cli.exe -h 127.0.0.1 -p 6379**
###2.linux
下载linux .tar.gz安装包
解压 **tar -xzvf redis-x.x.x.tar.gz**
移动到安装目录 例如 **mv redis-x.x.x /usr/local/xxxxx**
安装**make**
    **make install**

前面3步应该没有问题，主要的问题是执行make的时候，出现了异常。
**1.异常一**：
make[2]: cc: Command not found
异常原因：没有安装gcc
解决方案：**yum install gcc-c++**
**2.异常二：**
zmalloc.h:51:31: error: jemalloc/jemalloc.h: No such file or directory
异常原因：一些编译依赖或原来编译遗留出现的问题
解决方案：**make distclean**。清理一下，然后再make。

在make成功以后，需要make test。在make test出现异常。
**3.异常三：**
couldn't execute "tclsh8.5": no such file or directory
异常原因：没有安装tcl
解决方案：**yum install -y tcl**。

在make成功以后，会在src目录下多出一些可执行文件：redis-server，redis-cli等等。

方便期间用cp命令复制到usr目录下运行。
**cp redis-server /usr/local/bin/
cp redis-cli /usr/local/bin/**
###3.运行
**redis-server**
另起窗口 运行**redis-cli**