package com.example.cesai.controller;


import com.example.cesai.controller.bean.CollectionBean;
import com.example.cesai.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    private Integer selectGroupNum=0;

    private List<List<CollectionBean>> collectionBeansList;

    private List<CollectionBean> collection30Beans;

    @GetMapping("/collection-list")
    public String collectionList(Model model, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            List<CollectionBean> collectionBeans = this.collectionService.getAll();
            collectionBeansList=splitList(collectionBeans,30);
            collection30Beans=collectionBeansList.get(selectGroupNum);

            model.addAttribute("collections", collection30Beans);
            log.info("collection-listModel为:{}", model);
            log.info("所有的collection为:{}", collection30Beans);

            return "collection-list";
        } else {
            return "alreadyQuit";
        }

    }

    @PostMapping("/createCollection")
    public String createCollection(CollectionBean collectionBean, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("collectionStory中的collectionBean为:{}", collectionBean);

            collectionBean.setCreateTime(new Date());
            this.collectionService.createCollection(collectionBean);

            return "redirect:collection-list?i=1022";
        } else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/editCollection")
    public String editCollection(Model loginManagerModel, Integer collectionId, CollectionBean beEditedCollectionBean) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("editCollection中的collectionId为:{}", collectionId);
            log.info("要被修改的collectionBean为:{}", beEditedCollectionBean);

            beEditedCollectionBean = this.collectionService.editCollection(beEditedCollectionBean);
            log.info("更改过后的collectionBean为:{}", beEditedCollectionBean);

            return "redirect:collection-list?i=1023";
        } else {
            return "alreadyQuit";
        }

    }

    @GetMapping("/deleteCollection/{collectionId}")
    public String deleteCollection(Model loginManagerModel, @PathVariable("collectionId") Integer collectionId){
        if(loginManagerBean!=null){
            loginManagerModel.addAttribute("loginManager",loginManagerBean);
            log.info("被删除的合辑ID为:{}",collectionId);

            CollectionBean beDeletedCollectionBean=this.collectionService.getByCollectionId(collectionId);
            String collectionPictureUrl=beDeletedCollectionBean.getCollectionPictureUrl();

            HttpResponse httpResponse=new HttpResponse();
            httpResponse.deleteUrlInServer(collectionPictureUrl);

            this.collectionService.deleteCollection(collectionId);

            return "redirect:../collection-list?i=1024";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectNextCollectionGroup")
    public String selectNextGroup(Model model){
        if (loginManagerBean!=null){
            if (selectGroupNum<collectionBeansList.size()-1){
                selectGroupNum++;
            }

            collection30Beans=collectionBeansList.get(selectGroupNum);//根据selectGroupNum来查找第i组

            model.addAttribute("collections", collection30Beans);
            log.info("collection-listModel为:{}", model);
            log.info("所有的collection为:{}", collection30Beans);
            return "redirect:collection-list";
        }else {
            return "alreadyQuit";
        }

    }

    @RequestMapping("/selectPreviousCollectionGroup")
    public String selectPreviousGroup(Model model){
        if (loginManagerBean!=null){
            if(selectGroupNum>0){
                selectGroupNum--;
            }
            collection30Beans=collectionBeansList.get(selectGroupNum);//根据selectGroupNum来查找第i组

            model.addAttribute("collections", collection30Beans);
            log.info("collection-listModel为:{}", model);
            log.info("所有的collection为:{}", collection30Beans);

            return "redirect:collection-list";
        }else {
            return "alreadyQuit";
        }
    }

    public List<List<CollectionBean>> splitList(List<CollectionBean> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<CollectionBean>> result = new ArrayList<List<CollectionBean>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<CollectionBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
