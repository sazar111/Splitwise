package com.example.splitwise.controlers;

import com.example.splitwise.dto.*;
import com.example.splitwise.services.SettleUpUserService;
import org.springframework.stereotype.Controller;

@Controller
public class SettleUpController {
    SettleUpUserService settleUpUserService;

    public SettleUpController(SettleUpUserService settleUpUserService) {
        this.settleUpUserService = settleUpUserService;
    }

    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto settleUpGroupRequestDto){
        return null;
    }

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto settleUpUserRequestDto){
        SettleUpUserResponseDto settleUpUserResponseDto = new SettleUpUserResponseDto();
        try {
            settleUpUserResponseDto.setSuggestedExpenses(settleUpUserService.settleUpUser(settleUpUserRequestDto.getUserId()));
            settleUpUserResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            settleUpUserResponseDto.setResponseStatus(ResponseStatus.FAILED);
        }
        return settleUpUserResponseDto;
    }
}
