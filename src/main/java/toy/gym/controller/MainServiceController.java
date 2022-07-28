package toy.gym.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toy.gym.domain.form.MembersForm;
import toy.gym.domain.member.Member;
import toy.gym.domain.repository.MemberRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainServiceController {
   private final MemberRepository memberRepository;

    @GetMapping("/")
    String mainForm(){
        log.info("called");
        return "index";
    }


}
