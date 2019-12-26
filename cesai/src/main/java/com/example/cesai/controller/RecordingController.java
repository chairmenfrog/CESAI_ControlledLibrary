package com.example.cesai.controller;

import com.example.cesai.controller.bean.RecordingBean;
import com.example.cesai.controller.bean.StoryBean;
import com.example.cesai.controller.bean.UserBean;
import com.example.cesai.service.RecordingService;
import com.example.cesai.service.StoryService;
import com.example.cesai.service.UserService;
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
public class RecordingController {
    @Autowired
    private RecordingService recordingService;

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    private Integer selectGroupNum=0;

    private List<List<RecordingBean>> recordingBeansList;

    private List<RecordingBean> recording30Beans;

    @GetMapping("/recording-list")
    public String recordingList(Model model, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            List<RecordingBean> recordingBeans = this.recordingService.getAll();

            recordingBeansList=splitList(recordingBeans,30);
            recording30Beans=recordingBeansList.get(selectGroupNum);

            model.addAttribute("recordings", recording30Beans);
            log.info("recording-listModel为:{}", model);
            log.info("所有的recording为:{}", recording30Beans);

            return "recording-list";
        } else {
            return "alreadyQuit";
        }

    }

    @PostMapping("/createRecording")
    public String createRecording(RecordingBean recordingBean,Integer storyId,Integer userId, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("createRecording中的recordingBean为:{}", recordingBean);

            //故事id是否存在
            StoryBean isExistStoryBean=this.storyService.getByStoryId(storyId);
            log.info("createRecording中查找到的故事为:{}",isExistStoryBean);
            //用户id是否存在
            UserBean isExistUserBean=this.userService.getByUserId(userId);
            log.info("createRecording中查找到的用户为:{}",isExistUserBean);

            if (isExistStoryBean==null){
                return "redirect:recording-list?i=2009";
            }else if(isExistUserBean==null){
                return "redirect:recording-list?i=2010";
            }else {
                recordingBean.setCreateTime(new Date());
                this.recordingService.createRecording(recordingBean);
                return "redirect:recording-list?i=1013";
            }

        } else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/editRecording")
    public String editRecording(Model loginManagerModel,Integer storyId,Integer userId, Integer recordingId, RecordingBean beEditedRecordingBean) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("editRecording中的recordingId为:{}", recordingId);
            log.info("要被修改的recordingBean为:{}", beEditedRecordingBean);

            //故事id是否存在
            StoryBean isExistStoryBean=this.storyService.getByStoryId(storyId);
            //用户id是否存在
            UserBean isExistUserBean=this.userService.getByUserId(userId);

            if(isExistStoryBean==null){
                return "redirect:recording-list?i=2011";
            }else if(isExistUserBean==null){
                return "redirect:recording-list?i=2012";
            }else {
                beEditedRecordingBean.setUpdateTime(new Date());
                beEditedRecordingBean = this.recordingService.editRecording(beEditedRecordingBean);
                log.info("更改过后的recordingBean为:{}", beEditedRecordingBean);
                return "redirect:recording-list?i=1014";
            }
        } else {
            return "alreadyQuit";
        }
    }

    @GetMapping("/deleteRecording/{recordingId}")
    public String deleteRecording(Model loginManagerModel, @PathVariable("recordingId") Integer recordingId){
        if(loginManagerBean!=null){
            loginManagerModel.addAttribute("loginManager",loginManagerBean);
            log.info("被删除的录音ID为:{}",recordingId);

            RecordingBean beDeletedRecordingBean=this.recordingService.getByRecordingId(recordingId);
            String recordingUrl=beDeletedRecordingBean.getRecordingUrl();

            HttpResponse httpResponse=new HttpResponse();
            httpResponse.deleteUrlInServer(recordingUrl);

            this.recordingService.deleteRecording(recordingId);

            return "redirect:../recording-list?i=1015";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectNextRecordingGroup")
    public String selectNextGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum<recordingBeansList.size()-1){
                selectGroupNum++;
            }

            recording30Beans=recordingBeansList.get(selectGroupNum);

            model.addAttribute("recordings",recording30Beans);
            log.info("recording-listModel为:{}",model);
            log.info("所有的recording为:{}",recording30Beans);
            return "redirect:recording-list";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectPreviousRecordingGroup")
    public String selectPreviousGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum>0){
                selectGroupNum--;
            }

            recording30Beans=recordingBeansList.get(selectGroupNum);

            model.addAttribute("recordings",recording30Beans);
            log.info("recording-listModel为:{}",model);
            log.info("所有的recording为:{}",recording30Beans);
            return "redirect:recording-list";
        }else {
            return "alreadyQuit";
        }
    }

    public List<List<RecordingBean>> splitList(List<RecordingBean> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<RecordingBean>> result = new ArrayList<List<RecordingBean>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<RecordingBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
