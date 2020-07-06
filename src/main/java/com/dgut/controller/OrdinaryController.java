package com.dgut.controller;

import com.dgut.domain.Mask;
import com.dgut.domain.PagingVO;
import com.dgut.domain.ReservationCustom;
import com.dgut.domain.ReservationVo;
import com.dgut.service.MaskService;
import com.dgut.service.ReservationService;
import com.dgut.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/ordinary")
public class OrdinaryController {

    @Resource(name = "maskServiceImpl")
    private MaskService maskService;

    @Resource(name = "reservationServiceImpl")
    private ReservationService reservationService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<口罩信息管理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    // 信息显示
    @RequestMapping("/showMask")
    public String showMask(Model model, Integer page) throws Exception {

        List<Mask> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(maskService.maskCount());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = maskService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = maskService.findByPaging(page);
        }

        model.addAttribute("maskList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "/ordinary/showMask";
    }

    //搜索口罩
    @RequestMapping(value = "/queryMask", method = {RequestMethod.POST})
    private String queryMask(String findByName, Model model) throws Exception {

        List<Mask> list = maskService.findByName(findByName);

        model.addAttribute("maskList", list);
        return "/ordinary/showMask";
    }

    //查询接下来的15天内所有已被预约的口罩记录
    @RequestMapping("/showRecord")
    public String findAllReservation(Model model, Integer page) throws Exception {
        List<ReservationVo> list = null;

        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(reservationService.reserveCount());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = reservationService.findAllByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = reservationService.findAllByPaging(page);
        }

        model.addAttribute("recordList", list);
        model.addAttribute("pagingVo", pagingVO);

        return "/ordinary/showRecord";
    }

    //搜索预约人
    @RequestMapping(value = "/queryByUser", method = {RequestMethod.POST})
    private String queryUser(String findByName, Model model) throws Exception {

        List<ReservationVo> list = reservationService.queryByUser(findByName);

        model.addAttribute("recordList", list);

        return "/ordinary/showRecord";
    }

    //预约口罩页面跳转
    @RequestMapping(value = "/reserveMask", method = RequestMethod.GET)
    public String reserveMaskUI(Model model) throws Exception {
        //从数据库查询出所有口罩信息回显到页面
        List<Mask> list = maskService.nameList();
        model.addAttribute("nameList", list);

        return "/ordinary/reserveMask";
    }

    //预约口罩功能实现
    @RequestMapping(value = "/reserveMask", method = RequestMethod.POST)
    public String reserveMask(ReservationCustom reservationCustom) throws Exception {

        reservationService.addReservation(reservationCustom);

        return "redirect:/ordinary/showRecord";
    }

    //取消预约申请页面跳转
    @RequestMapping(value = "/cancelApplication",method = RequestMethod.GET)
    public String cancelApplicationUI(String user,Model model) throws Exception{
        List<ReservationCustom> list=reservationService.findByUser(user);
        model.addAttribute("reserveList",list);

        return "/ordinary/cancelApplication";
    }

    //取消预约申请业务实现
    @RequestMapping("/cancelApply")
    public String cancelApplication(Integer id) throws Exception{
        reservationService.cancelApplication(id);

        return "redirect:/ordinary/showRecord";
    }

}
