package com.iolll.nicesome.core;

import com.iolll.nicesome.db.UserRepository;
import com.iolll.nicesome.model.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取和删除 token 的请求地址，在 Restful 设计中其实就对应着登录和退出登录的资源映射
 * @author ScienJus
 * @date 2015/7/30.
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private TokenManager tokenManager;
//
//    @RequestMapping (method = RequestMethod.POST)
//    public ResponseEntity login (@RequestParam String username, @RequestParam String password) {
//        Assert.notNull (username, "username can not be empty");
//        Assert.notNull (password, "password can not be empty");
//
//        User user = userRepository.findByUsername (username);
//        if (user == null || // 未注册
//                !user.getPassword ().equals (password)) { // 密码错误
//            // 提示用户名或密码错误
//            return new ResponseEntity (ResultModel.error (ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
//        }
//        // 生成一个 token，保存用户登录状态
//        TokenModel model = tokenManager.createToken (user.getId ());
//        return new ResponseEntity (ResultModel.ok (model), HttpStatus.OK);
//    }
//
//    @RequestMapping (method = RequestMethod.DELETE)
//    @Authorization
//    public ResponseEntity logout (@CurrentUser User user) {
//        tokenManager.deleteToken (user.getId ());
//        return new ResponseEntity (ResultModel.ok (), HttpStatus.OK);
//    }

}