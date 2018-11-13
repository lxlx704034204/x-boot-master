package cn.exrick.xboot.serviceimpl;

import cn.exrick.xboot.common.vo2.PageVo;
import cn.exrick.xboot.dao.test.UpmsTestuserDao;
import cn.exrick.xboot.entity.test.UpmsTestuser;
import cn.exrick.xboot.service.UpmsTestuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author liangxin
 * @since 26/08/2018
 */
@Service
@Transactional
public class UpmsTestuserServiceImpl implements UpmsTestuserService {
    @Autowired
    UpmsTestuserDao upmsTestuserRepo;




    @Override
    public Page<UpmsTestuser> findByCondition(PageVo pageVo, Pageable pageable) {

        LinkedHashMap searcher = (LinkedHashMap) pageVo.getQuery();

        Specification<UpmsTestuser> spec = (root, query, cb) -> {

            //https://www.cnblogs.com/dreamroute/p/5173896.html
//            Join abMap = root.join("classPlanSend", JoinType.INNER);
//            query.groupBy(root.get("pkg")).orderBy(cb.desc(root.get("createTime")));


            Path<String> usernameField = root.get("name");

            List<Predicate> list = new ArrayList<Predicate>();

            if(null !=searcher.get("name") && !"".equals(searcher.get("name"))){
                list.add(cb.like(usernameField,'%'+ searcher.get("name").toString() +'%'));
            }
            if(null !=searcher.get("region") && !"".equals(searcher.get("region"))){
                list.add(cb.equal(root.get("region"),searcher.get("region").toString()));
            }
            Predicate[] arr = new Predicate[list.size()];
            query.where(list.toArray(arr));
            return null;
        };



//        Specification<UpmsTestuser> spec = new Specification<UpmsTestuser>() {
////            @Nullable
//            @Override
//            public Predicate toPredicate(Root<UpmsTestuser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//
//                Path<String> usernameField = root.get("name");
////                Path<String> mobileField = root.get("regin");//页面seacher无此字段的时候不要写，否则报错！
//
//
//                List<Predicate> list = new ArrayList<Predicate>();
//
////                //模糊搜素
//                if(null !=searcher.get("name") && !"".equals(searcher.get("name"))){
//                    list.add(cb.like(usernameField,'%'+ searcher.get("name").toString() +'%'));
//                }
//
////                if(StrUtil.isNotBlank(user.getUsername())){
////                    list.add(cb.like(usernameField,'%'+user.getUsername()+'%'));
////                }
////                if(StrUtil.isNotBlank(user.getMobile())){
////                    list.add(cb.like(mobileField,'%'+user.getMobile()+'%'));
////                }
////                //性别
////                if(user.getSex()!=null){
////                    list.add(cb.equal(sexField, user.getSex()));
////                }
////                //类型
////                if(user.getType()!=null){
////                    list.add(cb.equal(typeField, user.getType()));
////                }
////                //状态
////                if(user.getStatus()!=null){
////                    list.add(cb.equal(statusField, user.getStatus()));
////                }
////                //创建时间
////                if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
////                    Date start = DateUtil.parse(searchVo.getStartDate());
////                    Date end = DateUtil.parse(searchVo.getEndDate());
////                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
////                }
//
//                Predicate[] arr = new Predicate[list.size()];
//                query.where(list.toArray(arr));
//                return null;
//            }
//        };

        Page<UpmsTestuser> page = upmsTestuserRepo.findAll(spec, pageable);
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UpmsTestuser po){
        upmsTestuserRepo.save(po);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<UpmsTestuser> pos){
        upmsTestuserRepo.deleteAll(pos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void init(){

        upmsTestuserRepo.save(new UpmsTestuser(10,"张3-name","张3-pwd","张3-email","1", 20, new Date(),"1"));
        upmsTestuserRepo.save(new UpmsTestuser(11,"张4-name","张4-pwd","张4-email","0", 21, new Date(),"2"));
        upmsTestuserRepo.save(new UpmsTestuser(12,"张5-name","张5-pwd","张5-email","1", 22, new Date(),"1"));
    }
}
