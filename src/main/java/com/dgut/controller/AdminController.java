package com.dgut.controller;

import com.dgut.domain.*;
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
@RequestMapping("/admin")
public class AdminController {

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

        return "/admin/showMask";
    }

    //搜索口罩
    @RequestMapping(value = "/queryMask", method = {RequestMethod.POST})
    private String queryMask(String findByName, Model model) throws Exception {

        List<Mask> list = maskService.findByName(findByName);

        model.addAttribute("maskList", list);
        return "/admin/showMask";
    }

    //添加口罩页面跳转
    @RequestMapping(value = "/addMask", method = {RequestMethod.GET})
    public String addMask(Model model) throws Exception {

        return "/admin/addMask";
    }

    //添加口罩业务实现
    @RequestMapping(value = "/addMask", method = {RequestMethod.POST})
    public String addMask(Mask mask, Model model) throws Exception {

        maskService.add(mask);

        return "admin/showMask";
    }

    // 修改口罩信息页面显示
    @RequestMapping(value = "/editMask", method = {RequestMethod.GET})
    public String editMaskUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "admin/showMask";
        }
        Mask mask = maskService.findById(id);
        model.addAttribute("maskList", mask);

        return "/admin/editMask";
    }

    // 修改口罩信息页面处理
    @RequestMapping(value = "/editMask", method = {RequestMethod.POST})
    public String editMask(Mask mask) throws Exception {
        maskService.updateById(mask);

        //重定向
        return "admin/showMask";
    }

    // 删除口罩信息
    @RequestMapping("/removeMask")
    public String removeMask(Integer id) throws Exception {
        if (id == null) {
            //加入没有带教师id就进来的话就返回教师显示页面
            return "/admin/showMask";
        }
        maskService.removeById(id);

        return "admin/showMask";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<口罩预约管理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //查询所有待审核预约记录
    @RequestMapping("/showReservation")
    public String findAllReservation(Model model,Integer page) throws Exception {
        List<ReservationVo> list = null;

        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(reservationService.reservationCount());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = reservationService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = reservationService.findByPaging(page);
        }

        model.addAttribute("reservationList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "/admin/showReservation";
    }

    //搜索预约人
    @RequestMapping(value = "queryUser", method = {RequestMethod.POST})
    private String queryUser(String findByName, Model model) throws Exception {

        List<Reservation> list = reservationService.findByName(findByName);

        model.addAttribute("reservationList", list);
        return "/admin/showReservation";
    }

    //同意口罩申请
    @RequestMapping("/reviewReservation")
    public String reviewReservation(Integer id) throws Exception{
        reservationService.reviewReservation(id);
        return "redirect:/admin/showReservation";
    }

    //查询所有审核通过预约记录
    @RequestMapping("/showRecord")
    public String findRecord(Model model,Integer page) throws Exception{
        List<ReservationVo> list = null;

        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(reservationService.reservationPassCount());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = reservationService.findRecord(1);
        } else {
            pagingVO.setToPageNo(page);
            list = reservationService.findRecord(page);
        }

        model.addAttribute("recordList", list);
        model.addAttribute("pagingVo", pagingVO);

        return "/admin/showRecord";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<用户信息管理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //添加新用户
    @RequestMapping(value = "/userRegister", method = {RequestMethod.GET})
    public String userRegister(Model model) throws Exception {
        return "/admin/userRegister";
    }

    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public String userRegister(User user) throws Exception{
        if(user.getId()!=null && user.getId()!="" && user.getPassword()!=null && user.getPassword()!=""){
            userService.addNewUser(user);
        }
        return "redirect:/admin/userRegister";
    }
}
