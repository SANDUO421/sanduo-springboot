# 微服务监控
1. [SpringBoot 2.x Prometheus Grafana实现应用可视化监控](https://blog.csdn.net/qq_22097749/article/details/80936842)
2. [prometheus获取Consul上注册的服务](https://blog.csdn.net/zl1zl2zl3/article/details/77188426)
```yaml
global:
  scrape_interval: 10s
  scrape_timeout: 10s
  evaluation_interval: 10m
scrape_configs:
- job_name: prometheus
  honor_timestamps: true
  scrape_interval: 5s
  scrape_timeout: 5s
  metrics_path: /monitoring/actuator/prometheus
  scheme: http
  static_configs:
  - targets:
    - 192.168.10.24:8090
- job_name: consul-prometheus
  honor_timestamps: true
  scrape_interval: 10s
  scrape_timeout: 10s
  metrics_path: /metrics
  scheme: http
  consul_sd_configs:
  - server: 192.168.10.6:8500
    tag_separator: ','
    scheme: http
    allow_stale: true
    refresh_interval: 30s
  relabel_configs:
  - source_labels: [__meta_consul_tags]
    separator: ;
    regex: .*prometheus-target.*
    replacement: $1
    action: keep
- job_name: consul
  honor_timestamps: true
  scrape_interval: 10s
  scrape_timeout: 10s
  metrics_path: /metrics
  scheme: http
  consul_sd_configs:
  - server: 192.168.10.6:8500
    tag_separator: ','
    scheme: http
    allow_stale: true
    refresh_interval: 30s
  relabel_configs:
  - source_labels: [__metrics_path__]
    separator: ;
    regex: /metrics
    target_label: __metrics_path__
    replacement: /actuator/prometheus # 解决Endpoint的状态为DOWN
    action: replace
```
3.[熔断监控Hystrix Dashboard和Turbine](https://blog.csdn.net/ityouknow/article/details/72625646)
4.
