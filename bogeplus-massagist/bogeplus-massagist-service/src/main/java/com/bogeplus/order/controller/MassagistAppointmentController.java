package com.bogeplus.order.controller;

import cn.hutool.core.collection.CollUtil;
import com.bogeplus.common.util.Result;
import com.bogeplus.order.dto.MassagistAppointmentDTO;
import com.bogeplus.order.service.IMassagistAppointmentService;
import com.bogeplus.order.vo.MassagistAppointmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/massagistAppointment")
@Api(value = "技师预约接口", tags =  "技师预约接口")
public class MassagistAppointmentController {
    @Autowired
    private IMassagistAppointmentService massagistAppointmentService;

    @PostMapping("/saveAppointment")
    @ApiOperation(value = "保存技师预约")
    public Result saveAppointment(@RequestBody MassagistAppointmentDTO appointmentDTO) {
        if (appointmentDTO.getAppointmentDate().isBefore(LocalDate.now())) {
            return Result.faild("预约日期不能小于当前日期");
        }
        if (appointmentDTO.getAppointmentDate().isAfter(LocalDate.now().plusDays(3))) {
            return Result.faild("预约日期只能是未来三天以内");
        }
        if (appointmentDTO.getAppointmentHour() < 8) {
            return Result.faild("预约时间只能是8~24点");
        }

        Boolean bool = massagistAppointmentService.saveAppointment(appointmentDTO.getMassagistId(),
                appointmentDTO.getAppointmentDate(), appointmentDTO.getAppointmentHour());
        return Result.success(bool);
    }

    @PostMapping("/getAppointments")
    @ApiOperation(value = "获取技师预约")
    public Result getAppointments(@RequestBody MassagistAppointmentDTO appointmentDTO) {
        MassagistAppointmentVO massagistAppointmentVO = new MassagistAppointmentVO();
        massagistAppointmentVO.setAppointments(massagistAppointmentService.getAppointments(appointmentDTO.getMassagistId(),
                appointmentDTO.getAppointmentDate()));
        if (CollUtil.isEmpty(massagistAppointmentVO.getAppointments())) {
            return Result.faild("该技师当天没有预约");
        }
        return Result.success(massagistAppointmentVO);
    }

    @PostMapping("/removeAppointment")
    @ApiOperation(value = "移除技师预约")
    public Result removeAppointment(@RequestBody MassagistAppointmentDTO appointmentDTO) {
        if (appointmentDTO.getAppointmentDate().isBefore(LocalDate.now())) {
            return Result.faild("预约日期不能小于当前日期");
        }
        if (appointmentDTO.getAppointmentDate().isAfter(LocalDate.now().plusDays(3))) {
            return Result.faild("预约日期只能是未来三天以内");
        }
        if (appointmentDTO.getAppointmentHour() < 8) {
            return Result.faild("预约时间只能是8~24点");
        }

        return Result.success(massagistAppointmentService.removeAppointment(appointmentDTO.getMassagistId(),
                appointmentDTO.getAppointmentDate(), appointmentDTO.getAppointmentHour()));
    }
}
