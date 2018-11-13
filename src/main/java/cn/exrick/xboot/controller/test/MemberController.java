package cn.exrick.xboot.controller.test;

import cn.exrick.xboot.common.vo2.ExecuteDTO;
import cn.exrick.xboot.common.vo2.PageQueryParamDTO;
import cn.exrick.xboot.common.vo2.PageResultDTO;
import cn.exrick.xboot.dao.test.MemberRepository;
import cn.exrick.xboot.entity.test.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * @author liangxin
 * @since 29/08/2018
 */
@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberRepository repository;

    // http://localhost:1911/member/
    @RequestMapping(value="/",method = RequestMethod.GET)
    public ModelAndView getUserById(Model model){
        return new ModelAndView("member/memberList.html");
    }

    @PostMapping("loadPage")
    public PageResultDTO loadPage(@RequestBody PageQueryParamDTO params) {

///*		@Override
//		public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                //声明并创建MyOrder的CriteriaQuery对象
//                CriteriaQuery<User> q1 = cb.createQuery(User.class);
//                //连接的时候，要以声明的根查询对象（这里是root，也可以自己创建）进行join
//                //Join<Z,X>是Join生成的对象，这里的Z是被连接的对象，X是目标对象，
//                //  连接的属性字段是被连接的对象在目标对象的属性，这里是我们在User内声明的IdentityCard
//                //join的第二个参数是可选的，默认是JoinType.INNER(内连接 inner join)，也可以是JoinType.LEFT（左外连接 left join）
//                Join<IdentityCard, User> myOrderJoin = root.join("identityCard", JoinType.INNER);
//                //用CriteriaQuery对象拼接查询条件，这里只增加了一个查询条件，cId=1
//                q1.select(myOrderJoin).where(cb.equal(root.get("cId"), 1),
//                        root.get("id").in(1,2,4),
//                        cb.equal(root.get("identityCard").get("firstName"),"Jack")////对象identityCard的firstName=Jack
//                );// in 查询，支持集合Collection
//                //通过getRestriction获得Predicate对象
//                Predicate p1 = q1.getRestriction();
//                //返回对象
//                return p1;*/
//
//                /*//复杂条件
//                List<Predicate> predicatesList = new ArrayList<>();
//                //--------------------------------------------
//                //查询条件示例
//                //equal示例
//                if (!StringUtils.isEmpty(name)){
//                    Predicate namePredicate = cb.equal(root.get("name"), name);
//                    predicatesList.add(namePredicate);
//                }
//                //like示例
//                if (!StringUtils.isEmpty(nickName)){
//                    Predicate nickNamePredicate = cb.like(root.get("nickName"), '%'+nickName+'%');
//                    predicatesList.add(nickNamePredicate);
//                }
//                //between示例
//                if (birthday != null) {
//                    Predicate birthdayPredicate = cb.between(root.get("birthday"), birthday, new Date());
//                    predicatesList.add(birthdayPredicate);
//                }
//                //关联表查询示例
//                if (!StringUtils.isEmpty(courseName)) {
//                    Join<Student,Teacher> joinTeacher = root.join("teachers",JoinType.LEFT);
//                    Predicate coursePredicate = cb.equal(joinTeacher.get("courseName"), courseName);
//                    predicatesList.add(coursePredicate);
//                }
//                //复杂条件组合示例
//                if (chineseScore!=0 && mathScore!=0 && englishScore!=0 && performancePoints!=0) {
//                    Join<Student,Examination> joinExam = root.join("exams",JoinType.LEFT);
//                    Predicate predicateExamChinese = cb.ge(joinExam.get("chineseScore"),chineseScore);
//                    Predicate predicateExamMath = cb.ge(joinExam.get("mathScore"),mathScore);
//                    Predicate predicateExamEnglish = cb.ge(joinExam.get("englishScore"),englishScore);
//                    Predicate predicateExamPerformance = cb.ge(joinExam.get("performancePoints"),performancePoints);
//                    //组合
//                    Predicate predicateExam = cb.or(predicateExamChinese,predicateExamEnglish,predicateExamMath);
//                    Predicate predicateExamAll = cb.and(predicateExamPerformance,predicateExam);
//                    predicatesList.add(predicateExamAll);
//                }
//                //--------------------------------------------
//                //排序示例(先根据学号排序，后根据姓名排序)
//                query.orderBy(cb.asc(root.get("studentNumber")),cb.asc(root.get("name")));
//                //--------------------------------------------
//                //最终将查询条件拼好然后return
//                Predicate[] predicates = new Predicate[predicatesList.size()];
//                return cb.and(predicatesList.toArray(predicates));
//
//			return null;
//		}
//	};
//*/








        //方法:1：
//		Specification<Member> spec = new Specification<Member>() {
//			@Override
//			public Predicate toPredicate (Root < Member > root, CriteriaQuery < ?>query, CriteriaBuilder cb){
//				Predicate p1 = cb.like(root.get("name").as(String.class),  "%" + searcher.get("name")+ "%" );
//				Predicate p2 = cb.like(root.get("place").as(String.class), "%" + searcher.get("place").toString()+ "%" );
//				//把Predicate应用到CriteriaQuery中去,因为还可以给CriteriaQuery添加其他的功能，比如排序、分组啥的
//				query.where(cb.and(p1, p2));
//				return query.getRestriction();
//			}
//		};
        //方法2：
        LinkedHashMap searcher = (LinkedHashMap) params.getQuery();
        List<Predicate> predicatesList = new ArrayList<>();

        //Specification复杂用法： https://github.com/mpv945/springmvc/blob/780ed8f3a2e61501564817c0b1027b27bc9c9bd4/springmvc5/src/main/java/org/haijun/study/service/impl/UserServiceImpl.java
        // https://blog.csdn.net/baijunzhijiang_01/article/details/51557125
        // 	http://www.cnblogs.com/ymf23/p/6653417.html
        // 动态查询条件
        Specification<Member> spec = (root, query, cb) -> {
            if (null !=searcher.get("name") && !"".equals(searcher.get("name"))) {
                Predicate p1 = cb.like(root.get("name").as(String.class),  "%" + searcher.get("name")+ "%" );
                Predicate p2 = cb.like(root.get("place").as(String.class), "%" + searcher.get("place").toString()+ "%" );
//				query.where(cb.and(p1));
//				query.where(cb.and(p1, p2));
                predicatesList.add(p1);
            }
            if (searcher.get("place") != null && !"".equals(searcher.get("place"))) {
                Predicate p2 = cb.like(root.get("place").as(String.class), "%" + searcher.get("place").toString()+ "%" );
                predicatesList.add(p2);
            }
            //最终将查询条件拼好然后return
            Predicate[] predicates = new Predicate[predicatesList.size()];
            return cb.and(predicatesList.toArray(predicates));

//			return null;
        };
        Pageable pageable = new PageRequest(params.getPage() - 1, params.getSize());
        Page<Member> pageResult = repository.findAll(spec, pageable);

        PageResultDTO pages = new PageResultDTO(pageResult.getTotalElements(), pageResult.getContent());
        return pages;
    }

    /*
     *
     * 版本更新说明:
     * Spring Data JPA 1.xx:
     * 根据ID查询使用的是:T findOne(ID var)
     * 需要对返回值进行null判断,以判断是否能根据ID查询到对应的对象
     * Spring Data JPA 2.xx:
     * 根据ID查询使用的方法是:Optional<T> findById(ID id)-->T t = Optional<T>.get();
     * Optional<T>是非null的,但是如果查不到的话,它的get方法会报错,no value present;
     * 所以在进行get之前,需要使用Optional.isPresent()方法进行判断
     *
     */
    @GetMapping("get/{id}")
    public Member get(@PathVariable String id) {
        Optional<Member> bean = repository.findById(id);
        return bean.get();
    }

    @PostMapping("save")
    public ExecuteDTO save(@RequestBody Member entity) {
        if (entity.date == null) {
            entity.date = new Date();
        }
        repository.save(entity);
        return new ExecuteDTO(true, "保存成功", entity.id);
    }

    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable String id) {
        repository.deleteById(id);
        return new ExecuteDTO(true, "删除成功", id);
    }

}

