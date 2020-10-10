### 多实例的统一配置中心，可以用nginx反向代理实现

引入eureka虽然可以实现多实例，但是所有系统都要升级到spring cloud的体系，涉及的升级工作比较多。

只想使用spring cloud config，还想要高可用，可以借助nginx的负载均衡实现，client配置nginx指定的域名，nginx再负载均衡到多个配置中心服务。