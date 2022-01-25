package io.github.bw.boot.thrift.server.autoconfigure;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties
@ConditionalOnClass({Registration.class})
@ConditionalOnBean(Registration.class)
public class ThriftCloudMetadataAutoConfiguration {

  @Autowired
  private Registration registration;

  @Autowired
  private ThriftServerProperties thriftServerProperties;

  @PostConstruct
  public void init() {
    if (registration != null) {
      registration.getMetadata()
          .put(ThriftServerProperties.THRIFT_CLOUD_PORT, Integer.toString(thriftServerProperties.getPort()));
    }
  }

}
