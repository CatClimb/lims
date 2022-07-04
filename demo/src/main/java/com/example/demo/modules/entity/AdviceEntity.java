package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import lombok.*;

@Data

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AdviceEntity extends TableVO {
//id
//ad_type
//ad_content
//user_name
        @Alias("类型")
        private Integer id;
        @Alias("类型")
        private String adType;
        @Alias("内容")
        private String adContent;
        @Alias("用户")
        private String userName;
}
