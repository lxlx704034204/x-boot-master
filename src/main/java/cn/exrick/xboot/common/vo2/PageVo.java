package cn.exrick.xboot.common.vo2;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Exrickx
 */
@Data
@NoArgsConstructor //无参构造函数注解
@AllArgsConstructor
public class PageVo { //implements Serializable

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页号")
    private int pageNumber;

    @ApiModelProperty(value = "页面大小")
    private int pageSize;

    @ApiModelProperty(value = "排序字段")
    private String sort;

    @ApiModelProperty(value = "排序方式 asc/desc")
    private String order;

    /**
     * 查询条件
     */
    private Object query;

}
