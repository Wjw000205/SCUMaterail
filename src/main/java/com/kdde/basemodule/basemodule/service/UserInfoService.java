package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.dto.UserLoginDTO;
import com.kdde.basemodule.basemodule.dto.UserRegistDTO;
import com.kdde.basemodule.basemodule.entity.UserInfoEntity;
import com.kdde.basemodule.basemodule.vo.UserLoginVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * for connection test
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
public interface UserInfoService extends IService<UserInfoEntity> {


    PageUtils queryPage(Map<String, Object> params);

    UserLoginVO login(UserLoginDTO userLoginDTO, HttpSession session);

    Boolean regist(UserRegistDTO userRegistDTO);


    void resetpwd(String username);

    void resetpwdbyuser(String username, String pwd);
}

