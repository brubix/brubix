package com.brubix.brubixservice.controller.boarding.school;

import com.brubix.brubixservice.constant.ApplicationConstant;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/board/school"})
@Api(tags = {ApplicationConstant.BOARDING_TAG}, description = StringUtils.SPACE)
public class SchoolBoardingController {

}
