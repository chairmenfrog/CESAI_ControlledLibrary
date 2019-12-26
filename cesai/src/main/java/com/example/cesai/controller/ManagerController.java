package com.example.cesai.controller;

import com.example.cesai.controller.bean.ManagerBean;
import com.example.cesai.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;
import org.thymeleaf.expression.Lists;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    public static ManagerBean loginManagerBean;//已登录的用户

    private Integer selectGroupNum=0;//查询第selectGroupNum*30到(selectGroupNum+1)*30条信息

    private List<List<ManagerBean>> managerBeansList;//将列表分割为30一个组

    private List<ManagerBean> manager30Beans;//根据selectGroupNum来查找第i组

    @RequestMapping("/")
    public String index() {
        return "page-login";
    }


    @RequestMapping({"/login"})
    public String managerLogin(HttpServletRequest request,Model model){
        int loginNum = 1;
        if (loginManagerBean != null || loginNum == 1) {
            String managerName=request.getParameter("managerName");
            String managerPsw=request.getParameter("managerPsw");
            log.info("loginNum为:{}", loginNum);
            log.info("登陆界面输入管理员用户名为:{}", managerName);
            log.info("登陆界面输入密码为:{}", managerPsw);
            String managerPswMD5 = DigestUtils.md5DigestAsHex(managerPsw.getBytes());
            log.info("登录界面密码MD5加密后为:{}", managerPswMD5);

            Integer LOGIN_INFO=2001;
            LOGIN_INFO = managerService.login(managerName, managerPswMD5);
            log.info("LOGIN_SUCCESS为:{}",LOGIN_INFO);

            if (LOGIN_INFO == 1001) {//登录成功
                loginManagerBean = this.managerService.getByManagerName(managerName);
                log.info("该用户名的用户为:{}", loginManagerBean);
                model.addAttribute("loginManager", loginManagerBean);
                loginNum++;
                log.info("loginNum为:{}", loginNum);
                return "page-profile";
            } else if (LOGIN_INFO==2002){//管理员不存在
                return "redirect:page-login?i=2002";
            }else if (LOGIN_INFO==2003){//输入密码错误
                return "redirect:page-login?i=2003";
            }else{
                return "redirect:/page-login";
            }
        } else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/page-profile")
    public String goToPageProfile(Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            return "page-profile";
        } else {
            return "alreadyQuit";
        }
    }

    @GetMapping({"/page-login","/text-review/page-login","/deleteCartoon/page-login","/deleteManager/page-login"
    ,"/deleteCollection/page-login","/deleteDrawing/page-login","/deleteRecording/page-login","/deleteUser/page-login",
            "/deleteSong/page-login","/deleteStory/page-login"})
    public String goToPageLogin() {
        loginManagerBean = null;
        log.info("点击了退出按钮，跳转到“page-login”之后，loginManagerBean变为:{}", loginManagerBean);
        return "page-login";
    }

    @GetMapping("/admin-list")
    public String managerList(Model model, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);

            if (loginManagerBean.getManagerLev()!=0){
                return "redirect:page-profile?i=2015";
            }else {
                List<ManagerBean> managerBeans = this.managerService.getAll();
                managerBeansList=splitList(managerBeans,30);//将列表分割为30一个组
                manager30Beans=managerBeansList.get(selectGroupNum);//根据selectGroupNum来查找第i组

                model.addAttribute("managers", manager30Beans);
                log.info("admin-listModel为:{}", model);
                log.info("所有的30manager为:{}", manager30Beans);
               return "admin-list";
            }
        } else {
            return "alreadyQuit";
        }

    }

    @RequestMapping("/createManager")
    public String createManager(ManagerBean managerBean,String managerName, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("createManager中的managerBean为:{}", managerBean);

            managerBean.setManagerPsw(DigestUtils.md5DigestAsHex(managerBean.getManagerPsw().getBytes()));//转为MD5加密再存储
            managerBean.setCreateTime(new Date());

            //查找是否用户名已被设置，若已被设置，则创建失败
            ManagerBean isExistBean=this.managerService.getByManagerName(managerName);
            if(isExistBean==null){
                this.managerService.createManager(managerBean);
                return "redirect:admin-list?i=1003";
            }else {
                return "redirect:admin-list?i=2005";
            }
        } else {
            return "alreadyQuit";
        }

    }

    @RequestMapping("/changeManagerPassword")
    public String changeManagerPassword(String oldPsw, String newPsw, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("changeManagerPassword中的旧密码为:{}", oldPsw);
            log.info("changeManagerPassword中的新密码为:{}", newPsw);
            boolean ISOLDPASSWORD;//输入的原密码是否正确
            if (DigestUtils.md5DigestAsHex(oldPsw.getBytes()).equals(loginManagerBean.getManagerPsw())) {
                ISOLDPASSWORD = true;
            } else {
                ISOLDPASSWORD = false;
            }

            if (ISOLDPASSWORD == true) {//修改成功
                ManagerBean managerBean = loginManagerBean;
                managerBean.setManagerPsw(DigestUtils.md5DigestAsHex(newPsw.getBytes()));
                managerBean = this.managerService.editManager(managerBean);
                log.info("修改之后的用户密码为:{}", managerBean.getManagerPsw());
                return "redirect:page-login?i=1002";
            }else {//修改失败
                return "redirect:page-profile?i=2004";
            }
        } else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/editManager")
    public String editManager(Model loginManagerModel,String managerName,Integer managerId, ManagerBean beEditedManagerBean) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("editManager中的managerId为:{}", managerId);
            log.info("controller中要被修改为managerBean:{}", beEditedManagerBean);
            log.info("controller中newManagerPsw:{}", beEditedManagerBean.getManagerPsw());

            //查找是否用户名已被设置，若已被设置，则修改失败
            ManagerBean isExistBean=this.managerService.getByManagerName(managerName);
            //被修改的管理员的原用户名
            String oldManagerName=this.managerService.getByManagerId(managerId).getManagerName();
            if(isExistBean==null || oldManagerName.equals(managerName)){//若要改后的用户名不存在，或不修改用户名，则可以更改
                if(!beEditedManagerBean.getManagerPsw().equals("")){//如果要修改密码，将密码MD5存储
                    beEditedManagerBean.setManagerPsw(DigestUtils.md5DigestAsHex(beEditedManagerBean.getManagerPsw().getBytes()));//转为MD5加密再存储
                    log.info("进入了MD5。。。");
                }else {//若不修改密码，则存储原密码
                    beEditedManagerBean.setManagerPsw(this.managerService.getByManagerId(managerId).getManagerPsw());
                }
                beEditedManagerBean = this.managerService.editManager(beEditedManagerBean);
                log.info("更改过后的managerBean为:{}", beEditedManagerBean);
                return "redirect:admin-list?i=1004";
            }else {
                return "redirect:admin-list?i=2006";
            }
        } else {
            return "alreadyQuit";
        }
    }

    @GetMapping("/deleteManager/{managerId}")
    public String deleteManager(Model loginManagerModel,@PathVariable("managerId") Integer managerId){
        if(loginManagerBean!=null){
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("被删除的管理员ID为:{}",managerId);

            ManagerBean beDeletedManagerBean=this.managerService.getByManagerId(managerId);

            this.managerService.deleteManager(managerId);

            return "redirect:../admin-list?i=1005";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectNextManagerGroup")
    public String selectNextGroup(Model model){
        if (loginManagerBean!=null){
            if (selectGroupNum<managerBeansList.size()-1){
                selectGroupNum++;
            }

            manager30Beans=managerBeansList.get(selectGroupNum);//根据selectGroupNum来查找第i组

            model.addAttribute("managers", manager30Beans);
            log.info("admin-listModel为:{}", model);
            log.info("所有的manager为:{}", manager30Beans);
            return "redirect:admin-list";
        }else {
            return "alreadyQuit";
        }

    }

    @RequestMapping("/selectPreviousManagerGroup")
    public String selectPreviousGroup(Model model){
        if (loginManagerBean!=null){
            if(selectGroupNum>0){
                selectGroupNum--;
            }
            manager30Beans=managerBeansList.get(selectGroupNum);//根据selectGroupNum来查找第i组

            model.addAttribute("managers", manager30Beans);
            log.info("admin-listModel为:{}", model);
            log.info("所有的manager为:{}", manager30Beans);

            return "redirect:admin-list";
        }else {
            return "alreadyQuit";
        }
    }

    public List<List<ManagerBean>> splitList(List<ManagerBean> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<ManagerBean>> result = new ArrayList<List<ManagerBean>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<ManagerBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
