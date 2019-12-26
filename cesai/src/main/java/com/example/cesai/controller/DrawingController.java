package com.example.cesai.controller;

import com.example.cesai.controller.bean.DrawingBean;
import com.example.cesai.service.DrawingService;
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
public class DrawingController {
    @Autowired
    private DrawingService drawingService;

    private Integer selectGroupNum=0;

    private List<List<DrawingBean>> drawingBeansList;

    private List<DrawingBean> drawing30Beans;

    @GetMapping("/page-gallery")
    public String drawingList(Model model, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            List<DrawingBean> drawingBeans = this.drawingService.getAll();

            drawingBeansList=splitList(drawingBeans,30);
            drawing30Beans=drawingBeansList.get(selectGroupNum);

            model.addAttribute("drawings", drawing30Beans);
            log.info("page-galleryModel为:{}", model);
            log.info("所有的drawing为:{}", drawing30Beans);

            return "page-gallery";
        } else {
            return "alreadyQuit";
        }

    }

    @PostMapping("/createDrawing")
    public String createDrawing(DrawingBean drawingBean, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("createDrawing中的drawingBean为:{}", drawingBean);

            drawingBean.setCreateTime(new Date());
            this.drawingService.createDrawing(drawingBean);

            return "redirect:/page-gallery";
        } else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/editDrawing")
    public String editDrawing(Model loginManagerModel, Integer drawingId, DrawingBean beEditedDrawingBean) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("editDrawing中的drawingId为:{}", drawingId);
            log.info("要被修改的drawingBean为:{}", beEditedDrawingBean);

            beEditedDrawingBean = this.drawingService.editDrawing(beEditedDrawingBean);
            log.info("更改过后的drawingBean为:{}", beEditedDrawingBean);

            return "redirect:page-gallery";
        } else {
            return "alreadyQuit";
        }
    }

    @GetMapping("/deleteDrawing/{drawingId}")
    public String deleteDrawing(Model loginManagerModel, @PathVariable("drawingId") Integer drawingId){
        if(loginManagerBean!=null){
            loginManagerModel.addAttribute("loginManager",loginManagerBean);
            log.info("被删除的图画ID为:{}",drawingId);

            DrawingBean beDeletedDrawingBean=this.drawingService.getByDrawingId(drawingId);
            String drawingUrl=beDeletedDrawingBean.getDrawingImageUrl();

            HttpResponse httpResponse=new HttpResponse();
            httpResponse.deleteUrlInServer(drawingUrl);

            this.drawingService.deleteDrawing(drawingId);

            return "redirect:../page-gallery?i=1012";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectNextDrawingGroup")
    public String selectNextGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum<drawingBeansList.size()-1){
                selectGroupNum++;
            }

            drawing30Beans=drawingBeansList.get(selectGroupNum);

            model.addAttribute("drawings",drawing30Beans);
            log.info("drawing-listModel为:{}",model);
            log.info("所有的drawing为:{}",drawing30Beans);
            return "redirect:page-gallery";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectPreviousDrawingGroup")
    public String selectPreviousGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum>0){
                selectGroupNum--;
            }

            drawing30Beans=drawingBeansList.get(selectGroupNum);
            model.addAttribute("drawings",drawing30Beans);
            log.info("drawing-listModel为:{}",model);
            log.info("所有的drawing为:{}",drawing30Beans);
            return "redirect:page-gallery";
        }else {
            return "alreadyQuit";
        }
    }

    public List<List<DrawingBean>> splitList(List<DrawingBean> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<DrawingBean>> result = new ArrayList<List<DrawingBean>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<DrawingBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
