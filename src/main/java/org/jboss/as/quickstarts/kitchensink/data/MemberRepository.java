package org.jboss.as.quickstarts.kitchensink.data;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Find all members, ordered by name
    @Query("SELECT m FROM Member m ORDER BY m.name")
    List<Member> findAllOrderedByName();

    // Find member by email
    Optional<Member> findByEmail(String email);
}
