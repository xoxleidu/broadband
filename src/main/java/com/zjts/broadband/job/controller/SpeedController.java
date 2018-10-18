package com.zjts.broadband.job.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.ip.ReqSpeedAdd;
import com.zjts.broadband.job.model.Speed;
import com.zjts.broadband.job.service.SpeedService;
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
 *  前端控制器
 * </p>
 *
 * @author han zq
 * @since 2018-09-14
 */
@Api(tags = "ip限速管理")
@RestController
@RequestMapping("/speedmanage")
public class SpeedController  extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private SpeedService speedService;

    /**
     *          新增ip限速
     * @param reqSpeed speed 对象
     * @return
     */
    @ApiOperation("新增ip限速")
    @PostMapping("/addIpSpeed")
    @Transactional
    public APIResponse addSpeed(@RequestBody @Validated ReqSpeedAdd reqSpeed,
                                BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response){
            if (bindingResult.hasErrors())
                return parameterVerification(bindingResult);
        try {
            logger.info("SpeedController 新增ip限速 ");
            Speed speed = new Speed();
            BeanUtils.copyProperties(reqSpeed,speed);
            boolean a = speedService.insert(speed);
            if(a){
                return APIResponse.success();
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return  APIResponse.error(CodeEnum.SAVE_ERROR,e.getMessage());
        }
        return null;
    }

    /**
     *     根据id删除限速规则
     * @param  id
     * @return
     */
    @ApiOperation("删除限速规则")
    @PostMapping("/deleteIpSpeedByIpSegmentId")
    @Transactional
    public APIResponse deleteIpSpeedByIpSegmentId(@RequestBody  Integer id,
                                                  HttpServletRequest request, HttpServletResponse response){
        try {
            logger.info("SpeedController 删除ip号段 ");
            boolean a = speedService.deleteById(id);
            if (a)
                    return APIResponse.success();

        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return  APIResponse.error(CodeEnum.ERROR,e.getMessage());
        }
        return null;
    }

    /**
     *  修改限速规则
     * @param speed 要修改的speed对象
     * @return
     */
    @ApiOperation("修改限速规则")
    @PostMapping("/updateIpSpeedById")
    @Transactional
    public APIResponse updateIpSpeedById(@RequestBody @Validated Speed speed,
                                         HttpServletRequest request, HttpServletResponse response){

        try {

            logger.info("SpeedController 修改限速规则 ");
            boolean a = speedService.updateById(speed);
            if (a)
                return APIResponse.success();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return  APIResponse.error(CodeEnum.ERROR,e.getMessage());
        }
        return null;
    }

    /**
     *      根据ip号段的id查看限速规则
     * @param ipId
     * @return
     */
    @ApiOperation("查看限速规则")
    @PostMapping("/findSpeedByIpSegmentId")
    public APIResponse findSpeedByIpSegmentId(@RequestBody Integer ipId,
                                              HttpServletRequest request, HttpServletResponse response){
        try {
            logger.info("SpeedController 查看限速规则 ");
            List<Speed> list = speedService.selectList(new EntityWrapper<Speed>().eq("ip_id",ipId));
            if(list.size() >0)
            return APIResponse.success(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return  APIResponse.error(CodeEnum.ERROR,e.getMessage());
        }
       return null;
    }

}
