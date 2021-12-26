package com.denglitong.organizationservice.event.source;

import com.denglitong.organizationservice.event.model.OrganizationChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static com.denglitong.organizationservice.utils.UserContextHolder.getContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/11/7
 */
@Component
public class SimpleSourceBean {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);
    
    private Source source;

    @Autowired
    public void setSource(Source source) {
        this.source = source;
    }

    public void publishOrgChange(String action, String orgId) {
        logger.info("Sending Kafka message {} for Organization Id: {}", action, orgId);
        OrganizationChangeModel change = new OrganizationChangeModel()
                .withType(OrganizationChangeModel.class.getTypeName())
                .withAction(action)
                .withOrganizationId(orgId)
                .withCorrelationId(getContext().getCorrelationId());

        source.output().send(MessageBuilder.withPayload(change).build());
    }
}
