package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Members;
import com.example.firstproject.repository.MembersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MembersRepository membersRepository;

    @GetMapping("/signup")
    public String newMemberForm(){
        return "members/new";
    }

    @PostMapping("/members/join")
    public String createMember(MemberForm form){
        log.info(form.toString());
//        System.out.println(form.toString());

        // TODO: Transfer the DTO to an Entity
        Members member = form.toEntity();
        log.info(member.toString());
//        System.out.println(members.toString());

        // TODO: store the Entity into DB via repository
        Members newMember = membersRepository.save(member);
        log.info(member.toString());
//        System.out.println(members1.toString());
        return "redirect:/members/" + newMember.getId();
    }
    
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        Members memberEntity = membersRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        Iterable<Members> membersEntityList = membersRepository.findAll();
        model.addAttribute("memberList", membersEntityList);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Members memberEntity = membersRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form) {
        Members memberEntity = form.toEntity();
        Members target = membersRepository.findById(memberEntity.getId()).orElse(null);
        if(target != null){
            membersRepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getId();
    }

}
