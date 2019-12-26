package com.example.cesai.controller;

import com.example.cesai.controller.bean.SongBean;
import com.example.cesai.service.SongService;
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
public class SongController {
    @Autowired
    private SongService songService;

    private Integer selectGroupNum=0;

    private List<List<SongBean>> songBeansList;

    private List<SongBean> song30Beans;

    @GetMapping("/song-list")
    public String songList(Model model, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            List<SongBean> songBeans = this.songService.getAll();

            songBeansList=splitList(songBeans,30);
            song30Beans=songBeansList.get(selectGroupNum);

            model.addAttribute("songs", song30Beans);
            log.info("song-listModel为:{}", model);
            log.info("所有的song为:{}", song30Beans);

            return "song-list";
        } else {
            return "alreadyQuit";
        }

    }

    @PostMapping("/createSong")
    public String createSong(SongBean songBean, Model loginManagerModel) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("createSong中的songBean为:{}", songBean);

            songBean.setCreateTime(new Date());
            this.songService.createSong(songBean);

            return "redirect:song-list?i=1016";
        } else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/editSong")
    public String editSong(Model loginManagerModel, Integer songId, SongBean beEidtedSongBean) {
        if (loginManagerBean != null) {
            loginManagerModel.addAttribute("loginManager", loginManagerBean);
            log.info("editSong中的songId为:{}", songId);
            log.info("要被修改的songBean为:{}", beEidtedSongBean);

            beEidtedSongBean = this.songService.editSong(beEidtedSongBean);
            log.info("更改过后的songBean为:{}", beEidtedSongBean);

            return "redirect:song-list?i=1017";
        } else {
            return "alreadyQuit";
        }
    }

    @GetMapping("/deleteSong/{songId}")
    public String deleteSong(Model loginManagerModel, @PathVariable("songId") Integer songId){
        if(loginManagerBean!=null){
            loginManagerModel.addAttribute("loginManager",loginManagerBean);
            log.info("被删除的儿歌ID为:{}",songId);

            SongBean beDeletedSongBean=this.songService.getBySongId(songId);
            String songUrl=beDeletedSongBean.getSongUrl();
            String songPictureUrl=beDeletedSongBean.getSongPictureUrl();

            HttpResponse httpResponse=new HttpResponse();
            httpResponse.deleteUrlInServer(songUrl);
            httpResponse.deleteUrlInServer(songPictureUrl);

            this.songService.deleteSong(songId);

            return "redirect:../song-list?i=1018";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectNextSongGroup")
    public String selectNextGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum<songBeansList.size()-1){
                selectGroupNum++;
            }

            song30Beans=songBeansList.get(selectGroupNum);

            model.addAttribute("songs",song30Beans);
            log.info("song-listModel为:{}",model);
            log.info("所有的song为:{}",song30Beans);
            return "redirt:song-list";
        }else {
            return "alreadyQuit";
        }
    }

    @RequestMapping("/selectPreviousSongGroup")
    public String selectPreviousGroup(Model model){
        if(loginManagerBean!=null){
            if(selectGroupNum>0){
                selectGroupNum--;
            }

            song30Beans=songBeansList.get(selectGroupNum);

            model.addAttribute("songs",song30Beans);
            log.info("song-listModel为:{}",model);
            log.info("所有的song为:{}",song30Beans);
            return "redirt:song-list";
        }else {
            return "alreadyQuit";
        }
    }

    public List<List<SongBean>> splitList(List<SongBean> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<SongBean>> result = new ArrayList<List<SongBean>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<SongBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
