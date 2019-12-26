package com.example.cesai.controller;


import com.example.cesai.controller.bean.StoryBean;
import com.example.cesai.service.StoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.cesai.controller.ManagerController.loginManagerBean;

@Controller
@Slf4j
public class StoryController {
    @Autowired
    private StoryService storyService;

    private Integer selectGroupNum=0;

    private List<List<StoryBean>> storyBeansList;

    private List<StoryBean> story30Beans;

    @GetMapping("/story-list")
    public String storyList(Model model, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            List<StoryBean> storyBeans = this.storyService.getAll();

            storyBeansList=splitList(storyBeans,30);
            story30Beans=storyBeansList.get(selectGroupNum);

            model.addAttribute("stories", story30Beans);
            log.info("story-listModel为:{}", model);
            log.info("所有的story为:{}", story30Beans);

            return "story-list";
        } else {
            return "alreadyQuit";
        }

    }

    @GetMapping("/add-story")
    public String goToAddStory(Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            return "add-story";
        } else {
            return "alreadyQuit";
        }

    }

    @GetMapping("/text-review/{storyId}")
    public String goToTextReview(Model model, Model loginManagerModel, @PathVariable("storyId") Integer storyId) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);

            StoryBean storyBean = this.storyService.getByStoryId(storyId);
            model.addAttribute("reviewedStory", storyBean);
            return "text-review";
        } else {
            return "alreadyQuit";
        }
    }

    @PostMapping("/createStory")
    public String createStory(StoryBean storyBean, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("createStory中的storyBean为:{}", storyBean);

            storyBean.setCreateTime(new Date());
            this.storyService.createStory(storyBean);

            return "redirect:story-list?i=1009";
        } else {
            return "alreadyQuit";
        }

    }

    @RequestMapping("/editStory")
    public String editStory(Model loginManagerModel, Integer storyId, StoryBean beEditedStoryBean) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("editStory中的storyId为:{}", storyId);
            log.info("要被修改的storyBean为:{}", beEditedStoryBean);

            beEditedStoryBean = this.storyService.editStory(beEditedStoryBean);
            log.info("更改过后的storyBean为:{}", beEditedStoryBean);

            return "redirect:story-list?i=1010";
        } else {
            return "alreadyQuit";
        }
    }

    @GetMapping("/deleteStory/{storyId}")
    public String deleteStory(Model loginManagerModel,@PathVariable("storyId") Integer storyId){
        if(loginManagerBean!=null){
            loginManagerModel.addAttribute("loginManager",loginManagerBean);
            log.info("被删除的童话ID为:{}",storyId);

            StoryBean beDeletedStoryBean=this.storyService.getByStoryId(storyId);
            String storyPictureUrl=beDeletedStoryBean.getStoryPictureUrl();

            HttpResponse httpResponse=new HttpResponse();
            httpResponse.deleteUrlInServer(storyPictureUrl);

            this.storyService.deleteStory(storyId);

            return "redirect:../story-list?i=1011";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectNextStoryGroup")
    public String selectNextGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum<storyBeansList.size()-1){
                selectGroupNum++;
            }

            story30Beans=storyBeansList.get(selectGroupNum);

            model.addAttribute("stories",story30Beans);
            log.info("story-listModel为:{}",model);
            log.info("所有的story为:{}",story30Beans);
            return "redirect:story-list";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectPreviousStoryGroup")
    public String selectPreviousGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum<storyBeansList.size()-1){
                selectGroupNum--;
            }

            story30Beans=storyBeansList.get(selectGroupNum);

            model.addAttribute("stories",story30Beans);
            log.info("story-listModel为:{}",model);
            log.info("所有的story为:{}",story30Beans);
            return "redirect:story-list";
        }else {
            return "alreadyQuit";
        }
    }

    public List<List<StoryBean>> splitList(List<StoryBean> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<StoryBean>> result = new ArrayList<List<StoryBean>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<StoryBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
