package org.jboss.as.quickstarts.kitchensink.service;

import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.model.MemberRegisteredEvent;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.slf4j.Logger;


@Service
public class MemberRegistration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberRegistration.class);

    @Autowired
    private MemberRepository em;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public void register(Member member) throws Exception {
        LOGGER.info("Registering " + member.getName());
        em.save(member);

        // Fire an event using Spring's ApplicationEventPublisher
        eventPublisher.publishEvent(new MemberRegisteredEvent(this, member));
    }
}
