package controller;

import domain.Member;
import org.springframework.data.mongodb.core.MongoActionOperation;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by aso on 2017/6/11.
 */

@Controller
public class MemberController {

    @Resource
    private MongoTemplate mongoTemplate;
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){

        return new ModelAndView("register", "command", new Member());
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String process(Member member, ModelMap model){
        MongoOperations mongoOperations = mongoTemplate;
        mongoOperations.save(member);

        model.addAttribute("message", "Hello");
        model.addAttribute("userName", member.getUserName());
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model){
        MongoOperations mongoOperations = mongoTemplate;
        Query query = new Query();
        List<Member> memberList = mongoOperations.find(query, Member.class);
        String users = "";
        for(Member member: memberList){
            users += member.getUserName() + "<br>";
        }
        model.addAttribute("users", users);
        return "userlist";
    }

}
