package org.jboss.as.quickstarts.kitchensink.data;

import org.jboss.as.quickstarts.kitchensink.model.Member;

import org.jboss.as.quickstarts.kitchensink.model.MemberRegisteredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberListProducer {

    @Autowired
    private MemberRepository memberRepository;

    private List<Member> members;

    // Method to retrieve all members ordered by name
    public List<Member> getMembers() {
        if (members == null) {
            retrieveAllMembersOrderedByName();
        }
        return members;
    }

    // Event listener to handle member list updates when new members are registered
    @EventListener
    public void onMemberListChanged(MemberRegisteredEvent event) {
        retrieveAllMembersOrderedByName();
    }

    public void retrieveAllMembersOrderedByName() {
        // Assuming you have a method in MemberRepository that orders members by name
        members = memberRepository.findAllOrderedByName();
    }
}
