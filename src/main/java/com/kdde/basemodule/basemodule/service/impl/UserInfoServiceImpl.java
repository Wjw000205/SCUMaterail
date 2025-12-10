package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.common.constant.PasswordConstant;
import com.kdde.basemodule.basemodule.common.exception.AccountExitsException;
import com.kdde.basemodule.basemodule.common.exception.AccountNotFoundException;
import com.kdde.basemodule.basemodule.common.exception.InvalidRegistInfoException;
import com.kdde.basemodule.basemodule.common.exception.PasswordEditFailedException;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.dto.CheckCode;
import com.kdde.basemodule.basemodule.dto.UserLoginDTO;
import com.kdde.basemodule.basemodule.dto.UserRegistDTO;
import com.kdde.basemodule.basemodule.vo.UserLoginVO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.Query;
import com.kdde.basemodule.basemodule.dao.UserInfoDao;
import com.kdde.basemodule.basemodule.entity.UserInfoEntity;
import com.kdde.basemodule.basemodule.service.UserInfoService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserInfoEntity> page = this.page(
                new Query<UserInfoEntity>().getPage(params),
                new QueryWrapper<UserInfoEntity>()
        );
        return new PageUtils(page);
    }



    /**
     * 登录
     * @param userLoginDTO
     * @return
     */
    @Override
    public UserLoginVO login(@RequestBody UserLoginDTO userLoginDTO,HttpSession session) {

        UserLoginVO userLoginVO = new UserLoginVO();

        //获取图形验证码
        String checkCode = userLoginDTO.getCheckCode();
        if (checkCode == null||!CheckCode.check_equals(checkCode, (String) session.getAttribute("check_code_key"))){
            System.out.println("------------------------");
            System.out.println(checkCode);
            System.out.println(session.getAttribute("check_code_key"));
            userLoginVO.setSatus(4);//status 4: 验证码错误
            return userLoginVO;
        }
        session.removeAttribute("check_code_key");
        String username = userLoginDTO.getUsername();
        //获取md5加密后的密码
        String password = DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes());
        String email = userLoginDTO.getEmail();



        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        UserInfoEntity user = baseMapper.selectOne(queryWrapper);

        if(user == null){
            userLoginVO.setSatus(2);
        }

        else if(!password.equals(user.getPassword())){
            userLoginVO.setSatus(3);
        }
        else{
            userLoginVO.setSatus(1);
            userLoginVO.setUsername(user.getUsername());
            userLoginVO.setName(user.getName());
            userLoginVO.setRole(user.getRole());
        }
        return userLoginVO;
    }

    @Override
    public Boolean regist(UserRegistDTO userRegistDTO) {
        String username = userRegistDTO.getUsername();
        String password = userRegistDTO.getPassword();
        String passwordConfirm = userRegistDTO.getPasswordConfirm();

        if(username==null ||password == null || !password.equals(passwordConfirm)){
            throw new InvalidRegistInfoException("错误的注册信息");
        }

        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        UserInfoEntity user = baseMapper.selectOne(queryWrapper);

        if(user != null){
            throw new AccountExitsException("账号已经存在");
        }


        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUsername(userRegistDTO.getUsername());
        //把密码进行md5加密
        userInfoEntity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfoEntity.setEmail(userRegistDTO.getEmail());
        userInfoEntity.setName(userRegistDTO.getName());
        //默认注册设为普通用户
        userInfoEntity.setRole(1);
        save(userInfoEntity);
        return true;
    }

    @Override
    public void resetpwd(String username) {
        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        UserInfoEntity user = baseMapper.selectOne(queryWrapper);
        if(user == null){
            throw new PasswordEditFailedException("用户不存在, 无法修改");
        }
        user.setPassword(PasswordConstant.DEFAULT_PASSWORD);
        updateById(user);
    }

    @Override
    public void resetpwdbyuser(String username, String password) {
        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        UserInfoEntity user = baseMapper.selectOne(queryWrapper);
        if(user == null) {
            throw new PasswordEditFailedException("用户不存在, 无法修改");
        }
        // 修正后的密码校验逻辑，添加空指针检查
        if (password == null || password.length() < 6 || password.length() > 16) {
            throw new PasswordEditFailedException("无效密码");
        }
        user.setPassword(password);
        updateById(user);
    }


}