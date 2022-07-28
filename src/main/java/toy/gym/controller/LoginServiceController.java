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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
    public String memberDetail(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) throws ParseException {

        Long password = loginForm.getPassword();
        Optional<Member> foundPassword = memberRepository.findByPassword(password);

        Member member = foundPassword.get();
        if(member==null){
            bindingResult.reject("access Failed","입력하신 비밀번호와 일치하는 회원이 회원목록에 존재하지 않습니다.");
        }
        log.info("loginForm={}",loginForm);

        Date today = new Date();
        Calendar cal2= Calendar.getInstance();
        cal2.setTime(today);

        SimpleDateFormat sdf= new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초", Locale.KOREA);

        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(sdf.parse(member.getExdate()));

        long differance = (cal1.getTimeInMillis() - cal2.getTimeInMillis())/(1000*60*60*24);

        model.addAttribute("member",member);
        model.addAttribute("diff",differance);
        return "member/member";
    }
}
