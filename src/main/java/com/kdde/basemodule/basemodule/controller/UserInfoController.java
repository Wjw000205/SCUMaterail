package com.kdde.basemodule.basemodule.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.dto.UserLoginDTO;
import com.kdde.basemodule.basemodule.dto.UserRegistDTO;
import com.kdde.basemodule.basemodule.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.kdde.basemodule.basemodule.entity.UserInfoEntity;
import com.kdde.basemodule.basemodule.service.UserInfoService;



/**
 * for connection test
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;


    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping(path = "/login")
    public R login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("登录请求");
        UserLoginVO userLoginVO =  userInfoService.login(userLoginDTO);
        return R.ok().put("logininfo",userLoginVO);

    }

    /**
     * 注册
     */
   @PostMapping("/register")
   public R register(@RequestBody UserRegistDTO userRegistDTO) {
       Boolean bool = userInfoService.regist(userRegistDTO);
       return R.ok().put("isSuccess",bool);
   }


    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 查询用户
     */
    @GetMapping("/info/{username}")
    public R info(@PathVariable("username") String username){

        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

		UserInfoEntity userInfo = userInfoService.getOne(queryWrapper);

        return R.ok().put("userInfo", userInfo).put("isSuccess",userInfo!=null);
    }



//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    public R update(@RequestBody UserRegistDTO userInfo) {
//        log.info("修改用户数据");
//        String username = userInfo.getUsername();
//        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);
//        UserInfoEntity user = userInfoService.getOne(queryWrapper);
//
//        if (user == null) {
//            return R.error("user not exist");
//        }
//
//        String password = userInfo.getPassword();
//        // 修正后的密码校验逻辑，添加空指针检查
//        if (password != null && (password.length() < 6 || password.length() > 16)) {
//            return R.error("invalid password");
//        }
//
//        // 手动设置要更新的字段，根据查询到的用户记录的id更新
//        user.setUsername(userInfo.getUsername());
//        if (password != null) {
//            user.setPassword(password);
//        }
//        user.setEmail(userInfo.getEmail());
//        user.setName(userInfo.getName());
//        userInfoService.save(user);
//
//        return R.ok().put("userInfo", user);
//    }
    /**
     * 重置密码 默认为123456
     */
    @RequestMapping("/resetpwd")
    public R reset(@RequestParam String username) {
        userInfoService.resetpwd(username);
        return R.ok();
    }

    /**
     * 修改密码
     *
     */
    @RequestMapping("/updated")
    public R updatepwd(@RequestParam String username, @RequestParam String password) {
        log.info("修改用户密码");
        userInfoService.resetpwdbyuser(username, password);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
