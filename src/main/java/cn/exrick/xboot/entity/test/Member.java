package cn.exrick.xboot.entity.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import org.springframework.data.annotation.Transient;
/**
 * @author liangxin
 * @since 29/08/2018
 */
@Entity
@Table(name = "t_member")
public class Member {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "member_id", length = 36)
    public String id;

    /**
     * 注册日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Temporal(TemporalType.DATE)
    @Column(name = "`date`")
    public Date date;

    /**
     * 姓名
     */
    @Column(name = "`name`", length = 50)
    public String name;
    @Column(name = "`place`", length = 255)
    public String place;

    /**
     * 性别
     */
    @Column(name = "sex")
    public Integer sex;

    public Member() {}
    public Member(Date date, String name, Integer sex, String place) {
        this.date = date;
        this.name = name;
        this.sex = sex;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
