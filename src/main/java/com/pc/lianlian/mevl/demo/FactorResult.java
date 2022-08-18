package com.pc.lianlian.mevl.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author pengchao
 * @since 2022/8/18 14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FactorResult {

    private Object value;

    private String code;
}
