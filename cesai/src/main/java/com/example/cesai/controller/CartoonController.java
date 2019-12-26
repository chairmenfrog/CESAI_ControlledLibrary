package com.example.cesai.controller;

import com.example.cesai.controller.bean.CartoonBean;
import com.example.cesai.controller.bean.CollectionBean;
import com.example.cesai.service.CartoonService;
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
public class CartoonController {
    @Autowired
    private CartoonService cartoonService;

    @Autowired
    private CollectionService collectionService;

    private Integer selectGroupNum=0;

    private List<List<CartoonBean>> cartoonBeansList;

    private List<CartoonBean> cartoon30Beans;

    @GetMapping("/cartoon-list")
    public String cartoonList(Model model, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            List<CartoonBean> cartoonBeans = this.cartoonService.getAll();

            cartoonBeansList=splitList(cartoonBeans,30);
            cartoon30Beans=cartoonBeansList.get(selectGroupNum);

            model.addAttribute("cartoons", cartoon30Beans);
            log.info("cartoon-listModel为:{}", model);
            log.info("所有的cartoon为:{}", cartoon30Beans);

            return "cartoon-list";
        } else {
            return "alreadyQuit";
        }

    }

    @PostMapping("/createCartoon")
    public String createCartoon(CartoonBean cartoonBean,Integer collectionId, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("createCartoon中的cartoonBean为:{}", cartoonBean);

            cartoonBean.setCreateTime(new Date());
            //查找合辑是否存在
            CollectionBean isExitCollectionBean=this.collectionService.getByCollectionId(collectionId);

            if(Integer.toString(collectionId).equals("")){//若没有所属合辑
                this.cartoonService.createCartoon(cartoonBean);
                return "redirect:cartoon-list?i=1019";
            }else if(isExitCollectionBean!=null){
                this.cartoonService.createCartoon(cartoonBean);
                return "redirect:cartoon-list?i=1019";
            }else {
                return "redirect:cartoon-list?i=2013";
            }
        } else {
            return "alreadyQuit";
        }
    }


    @RequestMapping("/editCartoon")
    public String editCartoon(Model loginManagerModel,Integer collectionId, Integer cartoonId, CartoonBean beEditedCartoonBean) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("editCartoon中的cartoonId为:{}", cartoonId);
            log.info("要被修改的cartoonBean为:{}", beEditedCartoonBean);

            beEditedCartoonBean.setUpdateTime(new Date());

            //查找合辑是否存在
            CollectionBean isExitCollectionBean=this.collectionService.getByCollectionId(collectionId);

            if(Integer.toString(collectionId).equals("")){//若没有所属合辑

                beEditedCartoonBean = this.cartoonService.editCartoon(beEditedCartoonBean);
                log.info("更改过后的cartoonBean为:{}", beEditedCartoonBean);
                return "redirect:cartoon-list?i=1020";
            }else if(isExitCollectionBean!=null){
                beEditedCartoonBean.setUpdateTime(new Date());
                beEditedCartoonBean = this.cartoonService.editCartoon(beEditedCartoonBean);
                log.info("更改过后的cartoonBean为:{}", beEditedCartoonBean);
                return "redirect:cartoon-list?i=1020";
            }else {
                return "redirect:cartoon-list?i=2014";
            }
        } else {
            return "alreadyQuit";
        }

    }

    @RequestMapping("/deleteCartoon/{cartoonId}")
    public String deleteCartoon(Model loginManagerModel, @PathVariable("cartoonId") Integer cartoonId){
        if (loginManagerBean!=null){
            loginManagerModel.addAttribute("loginManager",loginManagerBean);
            log.info("被删除的动画ID为:{}",cartoonId);
            CartoonBean beDeletedCartoonBean=this.cartoonService.getByCartoonId(cartoonId);
            String cartoonUrl=beDeletedCartoonBean.getCartoonUrl();
            String cartoonPictureUrl=beDeletedCartoonBean.getCartoonPictureUrl();

            HttpResponse httpResponse=new HttpResponse();
            httpResponse.deleteUrlInServer(cartoonUrl);
            httpResponse.deleteUrlInServer(cartoonPictureUrl);

            this.cartoonService.deleteCartoon(cartoonId);
            return "redirect:../cartoon-list?i=1021";

        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectNextCartoonGroup")
    public String selectNextGroup(Model model){
        if (loginManagerBean!=null){
            if (selectGroupNum<cartoonBeansList.size()-1){
                selectGroupNum++;
            }

            cartoon30Beans=cartoonBeansList.get(selectGroupNum);//根据selectGroupNum来查找第i组

            model.addAttribute("cartoons", cartoon30Beans);
            log.info("cartoon-listModel为:{}", model);
            log.info("所有的cartoon为:{}", cartoon30Beans);
            return "redirect:cartoon-list";
        }else {
            return "alreadyQuit";
        }

    }

    @RequestMapping("/selectPreviousCartoonGroup")
    public String selectPreviousGroup(Model model){
        if (loginManagerBean!=null){
            if(selectGroupNum>0){
                selectGroupNum--;
            }
            cartoon30Beans=cartoonBeansList.get(selectGroupNum);//根据selectGroupNum来查找第i组

            model.addAttribute("cartoons", cartoon30Beans);
            log.info("cartoon-listModel为:{}", model);
            log.info("所有的cartoon为:{}", cartoon30Beans);

            return "redirect:cartoon-list";
        }else {
            return "alreadyQuit";
        }
    }


    public List<List<CartoonBean>> splitList(List<CartoonBean> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<CartoonBean>> result = new ArrayList<List<CartoonBean>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<CartoonBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
