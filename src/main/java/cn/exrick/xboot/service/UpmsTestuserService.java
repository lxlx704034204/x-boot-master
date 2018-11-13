package cn.exrick.xboot.service;

import cn.exrick.xboot.common.vo2.PageVo;
import cn.exrick.xboot.entity.test.UpmsTestuser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liangxin
 * @since 26/08/2018
 */
public interface UpmsTestuserService {

    Page<UpmsTestuser> findByCondition(PageVo pageVo, Pageable pageable);

    @Transactional(rollbackFor = Exception.class)
    void save(UpmsTestuser po);

    @Transactional(rollbackFor = Exception.class)
    void delete(List<UpmsTestuser> pos);

    @Transactional(rollbackFor = Exception.class)
    void init();
}
