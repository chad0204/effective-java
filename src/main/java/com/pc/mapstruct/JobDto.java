package com.pc.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author pengchao
 * @since 2022/9/2 14:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    String name;
}
