package org.jboss.as.quickstarts.kitchensink.rest;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/members")
@Validated
public class MemberRestController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRegistration registration;

    // GET all members, ordered by name
    @GetMapping
    public List<Member> listAllMembers() {
        return memberRepository.findAllOrderedByName();
    }

    // GET member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> lookupMemberById(@PathVariable("id") long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // POST to create a new member
    @PostMapping
    public ResponseEntity<?> createMember( @RequestBody Member member) {
        try {
            // Validate and register the member
            validateMember(member);
            registration.register(member);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            return createViolationResponse(ce.getErrorMessage());
        }  catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
        }
    }

    // Validation logic for member
    private void validateMember(Member member) throws Exception {
        // Bean validation is already handled by @Valid, but you can add additional checks if needed

        // Check if email already exists
        if (emailAlreadyExists(member.getEmail())) {
            //(@UnknownKeyFor @NonNull @Initialized String message,
            // @UnknownKeyFor @NonNull @Initialized SQLException root,
            // @Nullable @UnknownKeyFor @Initialized String constraintName) {
            throw new Exception("Email already taken");
        }
    }

    // Create a response for validation errors
    private ResponseEntity<Map<String, String>> createViolationResponse(String violation) {
        Map<String, String> responseObj = new HashMap<>();

        responseObj.put(DataIntegrityViolationException.class.toString(), violation);
        return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
    }

    // Check if the email already exists in the database
    public boolean emailAlreadyExists(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}
