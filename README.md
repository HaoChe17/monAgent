# monAgent
这是自己写的一个监控应用的软件。通过ps命令来获取应用pid，然后通过jdk的命令来获取gc信息，通过Linux的pidstat、iostat、free、df等命令来获取系统和应用监控数据。
有一个可通过rmiClient或monClient来获取这个应用的数据，它们之间通过rmi实现通信，该项目返回的json格式的数据。
