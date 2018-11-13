package cn.exrick.xboot.entity.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liangxin
 * @since 02/08/2018
 */
@Data //它就可以有下面几个注解的功能： @ToString、@Getter、@Setter、@EqualsAndHashCode、@NoArgsConstructor 。[mynote-master习得]
@NoArgsConstructor //无参构造函数注解
@AllArgsConstructor
//构造函数注解(参数的顺序与属性定义的顺序一致):注意的是，同时使用@Data 和 @AllArgsConstructor 后 ，默认的无参构造函数失效，如果需要它，要重新设置 @NoArgsConstructor
@Entity
@DynamicUpdate//默认为true:表示update对象的时候,生成动态的update语句,如果这个字段的值是null就不会被加入到update语句中,默认false。
@DynamicInsert//默认为true:同上：只会添加那些值为非空的。
//@Table(name="upms_testuser")
public class UpmsTestuser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="name",nullable = false)
    private String name;

    @Size(min = 2, max = 18, message = "密码长度为2-18位")
    private String pwd;
    private String email;

    @NotEmpty(message = "性别不能为空")
    private String sex;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String region;

}
