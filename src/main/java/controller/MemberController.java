package controller;

import domain.Member;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class MemberController {

    @Resource
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "/register", method = RequestMethod.GET)   // value 給server抓到他的路徑 "http://locohost:8080/register"
    public ModelAndView register() {

        return new ModelAndView("register", "command", new Member());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)   // value 給server抓到他的路徑 "http://locohost:8080/login"
    public ModelAndView login() {

        return new ModelAndView("login", "command", new Member());
    }


//    -------------------------------------------------------------------------------------------------   //


    @RequestMapping(value = "/verify", method = RequestMethod.POST)  // method 設定成POST 網址才不會暴露出使使用者的帳號跟密碼
    public String verify(Member member, ModelMap model) {

        if(StringUtils.isEmpty(member.getUserName()) || StringUtils.isEmpty(member.getPassword())){
            model.addAttribute("command", new Member());
            return "login";
        }   // StringUtils 判斷member的 userName 跟 password 其中一項不能為空值
            // 回傳login介面

        MongoOperations mongoOperations = mongoTemplate;
        Query query = new Query();

        query.addCriteria(Criteria.where("userName").is(member.getUserName()));
        Member result = mongoOperations.findOne(query, Member.class);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  // 將密碼加密

        passwordEncoder.matches(result.getPassword(), member.getPassword());  // 比對密碼


        if (result == null) {
            model.addAttribute("command", new Member());
            return "login";
        } else {
            model.addAttribute("message", "Hello!  ");  // message對應到home.jsp檔的${message}
            model.addAttribute("userName", result.getUserName());   // userName對應到${userName}得到userName
            model.addAttribute("address", result.getAddress());
            return "home";
        }
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String process(Member member, ModelMap model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(hashPassword);
        MongoOperations mongoOperations = mongoTemplate;
        mongoOperations.save(member);

        model.addAttribute("message", "Hello!  ");  // message對應到home.jsp檔的${message}
        model.addAttribute("userName", member.getUserName());   // userName對應到${userName}得到userName
        model.addAttribute("address", member.getAddress());
        model.addAttribute("receivePaper", member.isReceivePaper());
        return "home";
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        MongoOperations mongoOperations = mongoTemplate;
        Query query = new Query();
        List<Member> memberList = mongoOperations.find(query, Member.class);
        String users = "";
        for (Member member : memberList) {
            users += "Username: " + member.getUserName() + "<br>" +
                    "YourPassword: " + member.getPassword() + "<br>" +
                    "YourAdress: " + member.getAddress() + "<br>";
        }  // 用 for loop 將使用者的資料取出來

        model.addAttribute("users", users);  // "users" 是給userlist.jsp的body讀到的
        return "userlist";                               //  後面的users是show出使用者帳號跟密碼
    }

}
