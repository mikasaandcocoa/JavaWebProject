package course.javaweb.web.controller.api;

import course.javaweb.model.AsyncData;
import course.javaweb.model.User;
import course.javaweb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class LoginApi {
    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap login(@RequestParam("userName") String userName, @RequestParam("password") String password, ModelMap map, HttpSession httpSession) {
        System.out.println("user in LoginApi: " + userName + ", " + password);
        User loginUser = loginService.login(userName, password);
        System.out.println("user in LoginApi: " + loginUser);
        if (loginUser != null) {
            map.addAttribute("code", 200);
            map.addAttribute("message", "success");
            map.addAttribute("result", true);
            httpSession.setAttribute("user", loginUser);
        } else {
            httpSession.removeAttribute("user");
            map.addAttribute("code", 400);
            map.addAttribute("message", "failed");
            map.addAttribute("result", false);
        }
        return map;
    }

}
