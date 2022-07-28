package toy.gym.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toy.gym.domain.form.MembersForm;
import toy.gym.domain.form.SignUpForm;
import toy.gym.domain.member.Member;
import toy.gym.domain.member.Subscribe;
import toy.gym.domain.repository.MemberRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
@Slf4j
public class MemberServiceController {
    private final MemberRepository memberRepository;

   /* @GetMapping("/add")
    public String addForm(@ModelAttribute("signUpForm") SignUpForm signUpForm,Model model){

        model.addAttribute("subscribeTypes",Subscribe.values());
        return "member/addForm";
    }*/

    @GetMapping("/add")
    public String addFORM(SignUpForm signUpForm,Model model){
        model.addAttribute("signUpForm",new SignUpForm());
        model.addAttribute("subscribeTypes",Subscribe.values());
        return "member/addForm";
    }

    //ItemTypes.valuse()를 호출 시 enum 값들을 배열로 넘겨준다
//    @ModelAttribute("subscribeTypes")
//    public Subscribe[] itemTypes(){
//        return Subscribe.values();
//    }


    @PostMapping("/add")
    public String SignUp( @ModelAttribute("signUpForm") SignUpForm signUpForm, BindingResult bindingResult, Model model){
        
        if( bindingResult.hasErrors() ) {
           bindingResult.reject("sendingFailed","다시 입력해주십시오");
       }

        String name = signUpForm.getMemberName();
        Long password = signUpForm.getPassword();
        Subscribe subscribe = signUpForm.getSubscribe();
        Integer duration = subscribe.getDuration();


        String pattern="yyyy년 MM월 dd일 hh시 mm분 ss초";
        //date객체로 오늘 날짜의 스트링 구하기 형식은 매개변수로 지정가능
        Date today= new Date();
        SimpleDateFormat sdf =new SimpleDateFormat(pattern, Locale.KOREA);
        String todayString = sdf.format(today);

        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(today);
        //출력
        System.out.println("todayString = " + todayString);


        //date객체 Calendar로 변환하기
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(today);

        //캘린더에 날짜 더하기
        cal2.add(Calendar.MONTH,subscribe.getDuration());
        //데이트에 더한 캘린더 정보 넘겨주기
        Date exDate = new Date(cal2.getTimeInMillis());
        //만료일 구하기
        SimpleDateFormat sdf2=new SimpleDateFormat(pattern,Locale.KOREA);
        String exdate = sdf2.format(exDate);

        //시간차 구하기
        long difference = (cal2.getTimeInMillis() - cal1.getTimeInMillis())/(1000*60*60*24);


        Member member = new Member(name,password,subscribe,todayString,exdate);
        memberRepository.save(member);

        model.addAttribute("diff",difference);
        model.addAttribute("member",member);
        return "member/member";
    }

    @GetMapping("/members")
    public String showMembersList(Model model) throws ParseException {
        List<MembersForm> list =new ArrayList<>();

        List<Member> all = memberRepository.findAll();
        int n= all.size();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초", Locale.KOREA);
        Date today =new Date();
        Calendar cal1= Calendar.getInstance();
        cal1.setTime(today);
        Calendar cal2 =Calendar.getInstance();

        for(int i=0;i<n;i++){
            String name = all.get(i).getName();
            Long id = all.get(i).getId();
            String exdate = all.get(i).getExdate();
            cal2.setTime( sdf.parse(exdate) );
            long difference = (cal1.getTimeInMillis() - cal2.getTimeInMillis())/(1000*60*60*24);
            list.add(new MembersForm(id,name,difference));
        }

        model.addAttribute("items",list);
        return "member/members";
    }


}
