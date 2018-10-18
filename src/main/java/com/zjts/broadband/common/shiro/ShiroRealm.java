//package com.zjts.broadband.common.shiro;
//
//import com.zjts.broadband.system.model.SysRole;
//import com.zjts.broadband.system.model.SysUser;
//import com.zjts.broadband.system.service.SysPermissionService;
//import com.zjts.broadband.system.service.SysRoleService;
//import com.zjts.broadband.system.service.SysUserService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * 自定义实现 ShiroRealm，包含认证和授权两大模块
// *
// * @author MrBird
// */
//public class ShiroRealm extends AuthorizingRealm {
//
//    @Autowired
//    private SysUserService sysUserService;
//    @Autowired
//    private SysRoleService sysRoleService;
//    @Autowired
//    private SysPermissionService sysPermissionService;
//
//    /**
//     * 授权模块，获取用户角色和权限
//     *
//     * @param principal principal
//     * @return AuthorizationInfo 权限信息
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
//
//        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        String userName = sysUser.getUsername();
//
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//
//        // 获取用户角色集
//        List<SysRole> roleList = this.sysRoleService.findUserRole(userName);
//        Set<String> roleSet = roleList.stream().map(SysRole::getRoleName).collect(Collectors.toSet());
//        simpleAuthorizationInfo.setRoles(roleSet);
//
//        // 获取用户权限集
//        List<Menu> permissionList = this.menuService.findUserPermissions(userName);
//        Set<String> permissionSet = new HashSet<>();
//        for (Menu m : permissionList) {
//            // 处理用户多权限 用逗号分隔
//            permissionSet.addAll(Arrays.asList(m.getPerms().split(",")));
//        }
//        simpleAuthorizationInfo.setStringPermissions(permissionSet);
//        return simpleAuthorizationInfo;
//    }
//
//    /**
//     * 用户认证
//     *
//     * @param token AuthenticationToken 身份认证 token
//     * @return AuthenticationInfo 身份认证信息
//     * @throws AuthenticationException 认证相关异常
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//
//        // 获取用户输入的用户名和密码
//        String userName = (String) token.getPrincipal();
//        String password = new String((char[]) token.getCredentials());
//
//        // 通过用户名到数据库查询用户信息
//        User user = this.userService.findByName(userName);
//
//        if (user == null) {
//            throw new UnknownAccountException("用户名或密码错误！");
//        }
//        if (!password.equals(user.getPassword())) {
//            throw new IncorrectCredentialsException("用户名或密码错误！");
//        }
//        if (User.STATUS_LOCK.equals(user.getStatus())) {
//            throw new LockedAccountException("账号已被锁定,请联系管理员！");
//        }
//        return new SimpleAuthenticationInfo(user, password, getName());
//    }
//
//}
