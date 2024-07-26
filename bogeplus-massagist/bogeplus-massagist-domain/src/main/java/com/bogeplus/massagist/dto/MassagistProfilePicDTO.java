package com.bogeplus.massagist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "技师头像DTO", description = "技师头像DTO")
public class MassagistProfilePicDTO {
    @ApiModelProperty(value = "技师ID", required = true, example = "1")
    private Long id;
    @ApiModelProperty(value = "技师头像URL", required = false, example = "http://example.com/profile.jpg")
    private String profilePicture;
}
