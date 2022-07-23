package toy.gym.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberAddController {
    @GetMapping("/add")
    public String addForm(){
        return "member/addForm";
    }
    @GetMapping("/members")
    public String membersForm(){
        return "member/members";
    }

}
