package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Members;
import com.example.firstproject.repository.MembersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MembersRepository membersRepository;

    @GetMapping("/members/new")
    public String newMemberForm(){
        return "members/new";
    }

    @PostMapping("/members/join")
    public String createMember(MemberForm form){
        log.info(form.toString());
//        System.out.println(form.toString());

        // TODO: Transfer the DTO to an Entity
        Members members = form.toEntity();
        log.info(members.toString());
//        System.out.println(members.toString());

        // TODO: store the Entity into DB via repository
        Members members1 = membersRepository.save(members);
        log.info(members1.toString());
//        System.out.println(members1.toString());
        return "";

    }

}
