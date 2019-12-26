package com.example.cesai.controller;

import com.example.cesai.controller.bean.UserBean;
import com.example.cesai.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.cesai.controller.ManagerController.loginManagerBean;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    private Integer selectGroupNum=0;

    private List<List<UserBean>> userBeansList;

    private List<UserBean> user30Beans;

    @GetMapping("/user-list")
    public String userList(Model model, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            List<UserBean> userBeans = this.userService.getAll();

            userBeansList=splitList(userBeans,30);
            user30Beans=userBeansList.get(selectGroupNum);

            model.addAttribute("users", user30Beans);
            log.info("user-listModel为:{}", model);
            log.info("所有的user为:{}", user30Beans);

            return "user-list";
        } else {
            return "alreadyQuit";
        }

    }

    @PostMapping("/createUser")
    public String createUser(UserBean userBean,String userName,Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("createUser中的userBean为:{}", userBean);

            userBean.setUserPsw(DigestUtils.md5DigestAsHex(userBean.getUserPsw().getBytes()));//转为MD5加密再存储
            userBean.setCreateTime(new Date());

            //查找是否用户名已被设置，若已被设置，则创建失败
            UserBean isExistBean=this.userService.getByUserName(userName);
            if(isExistBean==null){
                this.userService.createUser(userBean);
                return "redirect:/user-list?i=1006";
            }else {
                return "redirect:/user-list?i=2007";
            }
        } else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/editUser")
    public String editUser(Model loginManagerModel,String userName,Integer userId, UserBean beEditedUserBean) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("editUser中的userId为:{}", userId);
            log.info("要被修改的userBean为:{}", beEditedUserBean);

            //查找是否用户名已被设置，若已被设置，则创建失败
            UserBean isExistBean=this.userService.getByUserName(userName);
            //被修改的管理员的原用户名
            String oldUserName=this.userService.getByUserId(userId).getUserName();
            if(isExistBean==null || oldUserName.equals(userName)){//若要改后的用户名不存在，或不修改用户名，则可以更改
                if(!beEditedUserBean.getUserPsw().equals("")){//如果要修改密码，将密码MD5存储
                    beEditedUserBean.setUserPsw(DigestUtils.md5DigestAsHex(beEditedUserBean.getUserPsw().getBytes()));//转为MD5加密再存储
                    log.info("进入了MD5。。。");
                }else {//若不修改密码，则存储原密码
                    beEditedUserBean.setUserPsw(this.userService.getByUserId(userId).getUserPsw());
                }

                beEditedUserBean = this.userService.editUser(beEditedUserBean);
                log.info("更改过后的userBean为:{}", beEditedUserBean);

                return "redirect:user-list?i=1007";
            }else {
                return "redirect:user-list?i=2008";
            }
        } else {
            return "alreadyQuit";
        }
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(Model loginManagerModel, @PathVariable("userId") Integer userId){
        if(loginManagerBean!=null){
            loginManagerModel.addAttribute("loginManager",loginManagerBean);
            log.info("被删除的用户ID为:{}",userId);

            UserBean beDeletedUserBean=this.userService.getByUserId(userId);

            this.userService.deleteUser(userId);

            return "redirect:../user-list?i=1008";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectNextUserGroup")
    public String selectNextGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum<userBeansList.size()-1){
                selectGroupNum++;
            }

            user30Beans=userBeansList.get(selectGroupNum);

            model.addAttribute("users",user30Beans);
            log.info("user-listModel为:{}",model);
            log.info("所有的user为:{}",user30Beans);
            return "redirect:user-list";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectPreviousUserGroup")
    public String selectPreviousGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum>0){
                selectGroupNum--;
            }

            user30Beans=userBeansList.get(selectGroupNum);

            model.addAttribute("users",user30Beans);
            log.info("user-listModel为:{}",model);
            log.info("所有的user为:{}",user30Beans);
            return "redirect:user-list";
        }else {
            return "alreadyQuit";
        }
    }


    public List<List<UserBean>> splitList(List<UserBean> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<UserBean>> result = new ArrayList<List<UserBean>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<UserBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
