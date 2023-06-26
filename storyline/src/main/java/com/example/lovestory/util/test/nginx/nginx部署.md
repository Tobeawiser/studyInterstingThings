1 解压tar -zxvf nginx-1.19.1.tar.gz 2 进入 nginx-1.19.1 3 make 4 make install 5 ./configure --prefix=/home/hexocrs/nginx 6
进入 /nginx目录 启动 ./sbin/nginx

7 查看进程nginx是否启动 ps -ef | grep nginx

8 对conf.nginx 进行相应配置