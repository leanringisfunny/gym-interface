package toy.gym.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toy.gym.domain.form.LoginForm;
import toy.gym.domain.member.Member;
import toy.gym.domain.repository.MemberRepository;
import toy.gym.domain.repository.MemoryMemberRepository;

import java.util.Optional;
@Controller
@RequiredArgsConstructor
@Slf4j
//@RequestMapping("/login")
public class LoginServiceController {

    private final MemberRepository memberRepository;


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){
        //model.addAttribute("loginForm",new LoginForm());
        return "login/loginForm";
    }


    @PostMapping("/login")
    public String memberDetail(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model){

        /*Long password = loginForm.getPassword();
        Optional<Member> foundPassword = memberRepository.findByPassword(password);

        Member member = foundPassword.get();
        if(member==null){
            bindingResult.reject("access Failed","입력하신 비밀번호와 일치하는 회원이 회원목록에 존재하지 않습니다.");
        }*/
        log.info("loginForm={}",loginForm);

        Member member = new Member();
        member.setPassword(loginForm.getPassword());
        member.setName("쿠쿠");
        model.addAttribute("member",member);
        return "member/member";
    }
}
