package org.jboss.as.quickstarts.kitchensink.model;

import org.springframework.context.ApplicationEvent;
import org.jboss.as.quickstarts.kitchensink.model.Member;

public class MemberRegisteredEvent extends ApplicationEvent {

    private final Member member;

    public MemberRegisteredEvent(Object source, Member member) {
        super(source);
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
