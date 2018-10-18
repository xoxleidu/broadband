package com.zjts.broadband.job.controller;

import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.log.ControllerLog;
import com.zjts.broadband.common.log.ServiceLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.ip.ReqIpSegmentAdd;
import com.zjts.broadband.job.model.IpSegment;
import com.zjts.broadband.job.service.IpSegmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 小区ip段的速度管理表,编辑ip的上传速度与下载速度 前端控制器
 * </p>
 *
 * @author han zq
 * @since 2018-09-13
 */
@Api(tags = "ip号段管理")
@RestController
@RequestMapping("/ipmanage")
public class IpSegmentController extends BaseController {

    private   Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IpSegmentService ipSegmentService;

    /**
     *      新增ip号段
     * @param reqIpSegment  IpSegment对象
     * @return
     */
    @ControllerLog(description="测试新增ip号段")
    @ApiOperation("新增ip号段")
    @PostMapping("/addIpSegment")
    @Transactional
    public APIResponse addIpSegment(@RequestBody
         @Validated ReqIpSegmentAdd reqIpSegment,
                                    BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response){
        if (bindingResult.hasErrors())
            return parameterVerification(bindingResult);
        try{
            logger.debug("ipManage 新增ip号段 ");
            IpSegment ipSegment = new IpSegment();
            BeanUtils.copyProperties(reqIpSegment,ipSegment);
            int a = ipSegmentService.addIpSegment(ipSegment);
            if(a>0)
            return APIResponse.success();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return  APIResponse.error(CodeEnum.SAVE_ERROR,e.getMessage());
        }

        return APIResponse.error(CodeEnum.SAVE_ERROR);
    }

    /**
     * 通过id 或者名称 对ip号段进行停用操作
     * @param Id  号段id
     * @return
     */
    @ApiOperation("通过id停用ip号段")
    @PostMapping("/forbidIpSegment")
    @Transactional
    public APIResponse forbidIpSegment( @RequestBody Integer Id,
            HttpServletRequest request, HttpServletResponse response){
        try{
            logger.debug("ipManage 通过名称或者id停用ip号段 ");
            boolean a = ipSegmentService.forbidIpSegmentById(Id);
            if(a)
                return APIResponse.success();

        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return  APIResponse.error(CodeEnum.SAVE_ERROR,e.getMessage());
        }

        return null;

    }

    /**
     *  通过id对数据进行修改
     * @param
     * @return
     */
    @ApiOperation("修改ip号段数据")
    @PostMapping("/updateIpSegmentDataById")
    @Transactional
    public APIResponse updateIpSegmentDataById( @RequestBody @Validated ReqIpSegmentAdd reqIpSegment,BindingResult bindingResult,
           HttpServletRequest request, HttpServletResponse response){
        if (bindingResult.hasErrors())
            return parameterVerification(bindingResult);
        try{
            logger.debug("ipManage 修改ip号段数据 ");
            IpSegment ipSegment = new IpSegment();
            BeanUtils.copyProperties(reqIpSegment,ipSegment);
            int a = ipSegmentService.updateIpSegmentDataById(ipSegment);
            if(a>0)
                return APIResponse.success();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return  APIResponse.error(CodeEnum.SAVE_ERROR,e.getMessage());
        }

        return APIResponse.error(CodeEnum.SAVE_ERROR);
    }

    /**
     *      分页显示所有的ip号段信息
     *
     * @param pageCurrent 查看的页数
     * @param pageSize 每页存放的记录条数
     * @return
     */
    @ApiOperation("查看所有的ip号段,分页显示")
    @PostMapping("/findAllIpSegmentByPage")
    public APIResponse findAllIpSegmentByPage( @RequestBody  Integer pageCurrent, Integer pageSize,
            HttpServletRequest request, HttpServletResponse response){

        try {
            logger.debug("ipManage 查看所有的ip号段 ");
            List list = ipSegmentService.findAllIpSegmentByPage(pageCurrent,pageSize);
            if(list.size()>0)
                return APIResponse.success(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return  APIResponse.error(CodeEnum.FIND_NULL_ERROR,e.getMessage());
        }

        return APIResponse.error(CodeEnum.FIND_NULL_ERROR);

    }

    /**
     *        通过id查找ip号段
     * @param id
     * @return
     */
    @ApiOperation("通过id查找ip号段")
    @PostMapping("/findIpSegmentById")
    public APIResponse findIpSegmentById (@RequestBody Integer id,
           HttpServletRequest request, HttpServletResponse response){
       try{
           logger.debug("ipsegmentController 通过id查找ip号段");
       IpSegment ipSegment =  ipSegmentService.selectById(id);
       if(ipSegment ==null)
           throw  new RuntimeException("ip号段不存在,请重新输入!");
       return  APIResponse.success(ipSegment);
       }catch (Exception e){
           e.printStackTrace();
           logger.error(e.getMessage(),e);
           return APIResponse.error(CodeEnum.ERROR,"查询失败,或信息不存在!");
       }

    }

    /**
     *      通过名称查找ip号段
     * @param ipName  ip号段 名称
     * @return
     */
    @ApiOperation("通过名称查找ip号段")
    @PostMapping("/findIpSegmentByIpNAme")
    public APIResponse findIpSegmentByIpNAme( @RequestBody String ipName,
             HttpServletRequest request, HttpServletResponse response){
        try{
            logger.debug("ipsegmentController 通过名称查找ip号段");
            List list  =  ipSegmentService.findIpSegmentByIpName(ipName);

            return APIResponse.success(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return APIResponse.error(CodeEnum.ERROR,"查询失败,或信息不存在!");
        }

    }

}















