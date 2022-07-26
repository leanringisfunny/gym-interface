package toy.gym.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toy.gym.domain.form.SignUpForm;
import toy.gym.domain.member.Member;
import toy.gym.domain.member.Subscribe;
import toy.gym.domain.repository.MemberRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberServiceController {
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("signUpForm") SignUpForm signUpForm,Model model){

        model.addAttribute("subscribeTypes",Subscribe.values());
        return "member/addForm";
    }

    //ItemTypes.valuse()를 호출 시 enum 값들을 배열로 넘겨준다
//    @ModelAttribute("subscribeTypes")
//    public Subscribe[] itemTypes(){
//        return Subscribe.values();
//    }


    @PostMapping("/add")
    public String SignUp(@ModelAttribute("signUpForm") SignUpForm signUpForm,BindingResult bindingResult,Model model){
        
        
        if( bindingResult.hasErrors() ) {
            bindingResult.reject("sendingFailed","다시 입력해주십시오");
        }
        
        String name = signUpForm.getName();
        Long password = signUpForm.getPassword();
        Subscribe subscribe = signUpForm.getSubscribe();
        Integer duration = subscribe.getDuration();


        Calendar time = Calendar.getInstance();
        int y = time.get(Calendar.YEAR);
        int m= time.get(Calendar.MONTH);
        int d = time.get(Calendar.DAY_OF_MONTH);
        int min = time.get(Calendar.MINUTE);
        int sec = time.get(Calendar.SECOND);

        System.out.println(y);
        System.out.println(m);
        System.out.println(d);
        System.out.println(min);
        System.out.println(sec);

        Calendar cur = new GregorianCalendar(y, m, d, min, sec);
        time.add(Calendar.MONTH,duration);

        Member member = new Member(name,password,subscribe,cur,time);

        memberRepository.save(member);
        model.addAttribute("member",member);
        return "member/members";
    }


    @GetMapping("/members")
    public String membersForm(){

        return "meber/members";
    }

}
