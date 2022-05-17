package com.example.demo.dto.user_role;

import com.example.demo.vo.TableVO;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConditionalURDTO extends TableVO {
    private String userName;
}
