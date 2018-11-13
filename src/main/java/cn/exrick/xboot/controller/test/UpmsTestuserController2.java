package cn.exrick.xboot.controller.test;

import cn.exrick.xboot.service.UpmsTestuserService;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.exrick.xboot.common.vo2.PageUtil;
import cn.exrick.xboot.common.utils.ResultUtil;
import cn.exrick.xboot.common.vo2.PageVo;
import cn.exrick.xboot.common.vo.Result;
import cn.exrick.xboot.exception.RestResponse;
import cn.exrick.xboot.entity.test.UpmsTestuser;
import cn.exrick.xboot.dao.test.UpmsTestuserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Predicate;
import java.util.*;

import static org.beetl.ext.spring.BeetlSpringViewResolver.redirect;


/**
 * @author liangxin
 * @since 02/08/2018
 */
@Controller
@RequestMapping("/upmsTestuserController")
public class UpmsTestuserController2 {

    @Autowired
    UpmsTestuserService upmsTestuserService;
    @Autowired
    UpmsTestuserDao upmsTestuserRepo;




    @RequestMapping(value ="/delete/po/list" , method = RequestMethod.POST)
    @ResponseBody
    public RestResponse deletePoList(@RequestBody List<UpmsTestuser> beans) {
    //@RequestBody List<Map<String, Object>> params
    //@RequestBody List<UpmsTestuser> params
        try {
            upmsTestuserService.delete(beans);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResponse.SUCCESS_WITHOUT_MSG;
    }


    @RequestMapping(value ="/add" , method = RequestMethod.POST)
    @ResponseBody
    public RestResponse add(@RequestBody Map<String, Object> params ) {
        Map<String, Object> m1 = (Map<String, Object>) params;

        String s1 = JSON.toJSONString(m1);
        JSONObject jsonObject1 = JSON.parseObject(s1);
        UpmsTestuser theUser = JSON.toJavaObject(jsonObject1, UpmsTestuser.class);
        try {
            upmsTestuserService.save(theUser);
        } catch (Exception e) {
            //new XbootException("");
            return new RestResponse(false, e.getMessage());
        }
        return RestResponse.SUCCESS_WITHOUT_MSG;
    }


    //List<Map<String, Object>> listmap, List<UpmsTestuser> lists
    @RequestMapping(value ="/update" , method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(
//          @RequestParam(value = "theEditId",required = true) String id,
            @RequestBody Map<String, Object> params ) {
//        if(StrUtil.isNotBlank(id)){ //update
//        }

        String theEditId = params.get("theEditId")+"";

        //BeanUtil JsonUtils ConvertUtil_diy
        Map<String, Object> m1 = (Map<String, Object>) params.get("bean");
////        List<Map<String, Object>> m2 = (List<Map<String, Object>>) params.get("bean");
//        BeanUtil.mapToBean(m1, UpmsTestuser.class);
//        JsonUtils.mapToBean(m1, UpmsTestuser.class);

        String s1 = JSON.toJSONString(m1);
        JSONObject jsonObject1 = JSON.parseObject(s1);
        UpmsTestuser theUser = JSON.toJavaObject(jsonObject1, UpmsTestuser.class);
        try {
            upmsTestuserService.save(theUser);
        } catch (Exception e) {
            //new XbootException("");
            return new RestResponse(false, e.getMessage());
        }
        return RestResponse.SUCCESS_WITHOUT_MSG;
    }


    //只为测试接收 前提提交复杂的 参数
    @RequestMapping(value ="/saveData_test_post" , method = RequestMethod.POST)
    @ResponseBody
    public void saveData(@RequestBody Map<String, Object> params) throws Exception {
        Map<String, Object> ChoisedOne_dim1Code_map = (Map<String, Object>) params.get("FKArrayOne");
        String targetExplain  = ChoisedOne_dim1Code_map.get("targetExplain")+"";
        String cat_three_code = ChoisedOne_dim1Code_map.get("cat_three_code")+"";
        List<Map<String, Object>> list = (List<Map<String, Object>>) params.get("dialogDatas");

    }


    @RequestMapping(value ="/get/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public UpmsTestuser getUserById(@PathVariable("id")String id){
        Optional<UpmsTestuser> one = upmsTestuserRepo.findById(Integer.parseInt(id));
        return one.get();
    }







    @RequestMapping(value ="/getstu" , method = RequestMethod.POST)
    @ResponseBody
    public Result<Page<UpmsTestuser>> getTestuser( //@RequestParam(defaultValue = "",  value="parameter") Map parameter,
//                                                  @ModelAttribute SearchVo searchVo,
//                                               @RequestParam(defaultValue = "0", value="pageNum") int pageNum,      //offset,   //页码
//                                               @RequestParam(defaultValue = "10",value="pageSize")int pageSize      //limit,    //每页显示数量
//                                                   @ModelAttribute PageVo pageVo
                                                   @RequestBody PageVo pageVo
    ) {

        Pageable pageable = PageUtil.initPage(pageVo);
        Page<UpmsTestuser> page = upmsTestuserService.findByCondition(pageVo, pageable);

        return new ResultUtil<Page<UpmsTestuser>>().setData(page);
//        return null;
    }

    @RequestMapping(value="/turnPage/main",method = RequestMethod.GET)
    public String turnPage(){
        return redirect("/upmsTestuserController/test3");
//        return "/testuser/testuserEdit";
    }

    // http://localhost:1911/upmsTestuserController/toLoginPage
    // http://localhost:1911/pages/com.moudle1.project1/testuser/testuser_edit.html
    @RequestMapping(value="/toLoginPage" )
    public ModelAndView toLoginPage(Model model){
//        return new ModelAndView("user/login");
        return new ModelAndView("testuser/testuserEdit1.html");
    }

    // http://localhost:1911/upmsTestuserController/test3
    //http://localhost:1911/public/com.moudle1/static/js/common/public.js
    //http://localhost:1911/common/public.js
    @RequestMapping(value="/test3",method = RequestMethod.GET)
    public ModelAndView toPage3(Model model){
//        StringUtils.equalsAny()
        List<UpmsTestuser> all = upmsTestuserRepo.findAll();
        if(CollectionUtils.isEmpty(all)){
            upmsTestuserService.init();
        }
        return new ModelAndView("testuser/testuserEdit1.html");
//        return new ModelAndView("/test3.html"); // /test3/test3.html

//        return new ModelAndView("searcher_plus/sp.html");//高级搜索 界面

    }

    @RequestMapping(value="/test2",method = RequestMethod.GET)
    public ModelAndView toPage2(Model model){
        return new ModelAndView("/test2.html");// /test2
    }
    @RequestMapping(value="/test1",method = RequestMethod.GET)
    public ModelAndView toPage1(Model model){
        return new ModelAndView("/test1.html"); // /test1
    }





}
