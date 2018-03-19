package com.wanma.ims.service.impl;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wanma.ims.common.domain.*;
import com.wanma.ims.common.domain.bo.AccountBalanceBO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.BatchResultDTO;
import com.wanma.ims.common.dto.ResultDTO;
import com.wanma.ims.constants.DownFileConstants;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.*;
import com.wanma.ims.service.*;
import com.wanma.ims.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.*;


@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserNormalMapper userNormalMapper;
    @Autowired
    private UserCompanyMapper userCompanyMapper;
    @Autowired
    private UserAdminMapper userAdminMapper;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private LevelService levelService;
    @Autowired
    private FinAccountService finAccountService;
    @Autowired
    private UserCardService userCardService;
    @Autowired
    private MultipartFileService multipartFileService;
    @Autowired
    private InitialService initialService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CarCompanyMapper carCompanyMapper;
    @Autowired
    private CarInfoMapper carInfoMapper;

    @Override
    public UserDO loginUser(UserDO user) {
        UserDO userDO = null;
        try {
            userDO = userMapper.loginUser(user);
            if (null == userDO || userDO.getUserLevel() == WanmaConstants.USER_LEVEL_SUPER) {
                return userDO;
            }
            userDO = findUser(userDO);
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl-loginUser ", e);
        }
        return userDO;
    }

    @Override
    public List<UserDO> getUserList(UserDO searchModel) {
        replaceSearchModel(searchModel);
        List<UserDO> userList = userMapper.selectUserList(searchModel);

        if (CollectionUtils.isEmpty(userList)) {
            return userList;
        }

        Set<Long> companyIdSet = new HashSet<>();
        Set<Long> levelIdSet = new HashSet<>();
        Set<String> provinceCodeSet = new HashSet<>();
        Set<String> cityCodeSet = new HashSet<>();
        Set<Long> carCompanyIdSet = new HashSet<>();
        Set<Long> carInfoIdSet = new HashSet<>();

        for (UserDO user : userList) {
            companyIdSet.add(user.getCpyId());
            levelIdSet.add(user.getLevelId());
            provinceCodeSet.add(user.getProvinceCode());
            cityCodeSet.add(user.getCityCode());
            carCompanyIdSet.add(user.getCarCompanyId());
            carInfoIdSet.add(user.getCarInfoId());
        }

        List<CompanyDO> companyList = companyService.getCompanyListByIds(new ArrayList<>(companyIdSet));
        List<LevelDO> levelList = levelService.getLevelListByIds(new ArrayList<>(levelIdSet));
        List<CarCompanyDO> carCompanyList = carCompanyMapper.selectCarCompanyByIds(new ArrayList<>(carCompanyIdSet));
        List<CarInfoDO> carInfoList = carInfoMapper.selectCarInfoByIds(new ArrayList<>(carInfoIdSet));

        Map<Long, CompanyDO> companyMap = new HashMap<>();
        Map<Long, LevelDO> levelMap = new HashMap<>();
        Map<String, ProvinceDO> provinceMap = initialService.getProvinceMapByIds(new ArrayList<>(provinceCodeSet));
        Map<String, CityDO> cityMap = initialService.getCityMapByIds(new ArrayList<>(cityCodeSet));
        Map<Long, CarCompanyDO> carCompanyMap = new HashMap<>();
        Map<Long, CarInfoDO> carInfoMap = new HashMap<>();

        if (CollectionUtils.isNotEmpty(companyList)) {
            companyMap = Maps.uniqueIndex(companyList, new Function<CompanyDO, Long>() {
                @Override
                public Long apply(CompanyDO input) {
                    return input.getCpyId();
                }
            });
        }

        if (CollectionUtils.isNotEmpty(levelList)) {
            levelMap = Maps.uniqueIndex(levelList, new Function<LevelDO, Long>() {
                @Override
                public Long apply(LevelDO input) {
                    return input.getLevelId();
                }
            });
        }

        if (CollectionUtils.isNotEmpty(carCompanyList)) {
            carCompanyMap = Maps.uniqueIndex(carCompanyList, new Function<CarCompanyDO, Long>() {
                @Override
                public Long apply(CarCompanyDO input) {
                    return input.getId();
                }
            });
        }

        if (CollectionUtils.isNotEmpty(carInfoList)) {
            carInfoMap = Maps.uniqueIndex(carInfoList, new Function<CarInfoDO, Long>() {
                @Nullable
                @Override
                public Long apply(CarInfoDO input) {
                    return input.getId();
                }
            });
        }

        for (UserDO user : userList) {
            CompanyDO company = companyMap.get(user.getCpyId());
            if (company != null) {
                user.setCpyName(company.getCpyName());
                user.setCpyNumber(company.getCpyNumber());
            } else {
                user.setCpyName("");
            }

            LevelDO level = levelMap.get(user.getLevelId());
            if (level != null) {
                user.setLevelName(level.getLevelName());
            } else {
                user.setLevelName("");
            }

            ProvinceDO province = provinceMap.get(user.getProvinceCode());
            if (province != null) {
                user.setUserAddress(province.getProvinceName());
            } else {
                user.setUserAddress("");
            }

            CityDO city = cityMap.get(user.getCityCode());
            if (city != null) {
                if (!user.getUserAddress().equals(city.getCityName())) {
                    user.setUserAddress(user.getUserAddress() + city.getCityName());
                }
            }

            CarCompanyDO carCompany = carCompanyMap.get(user.getCarCompanyId());
            if (carCompany != null) {
                user.setUserCar(carCompany.getName());
            } else {
                user.setUserCar("");
            }

            CarInfoDO carInfo = carInfoMap.get(user.getCarInfoId());
            if (carInfo != null) {
                user.setUserCar(user.getUserCar() + " " + carInfo.getStyleName());
            }
        }

        return userList;
    }

    @Override
    public long countUser(UserDO searchModel) {
        replaceSearchModel(searchModel);
        return userMapper.countUser(searchModel);
    }

    private void replaceSearchModel(UserDO searchModel) {
        searchModel.setUserAccount(ObjectUtil.emptyStrNvl(searchModel.getUserAccount()));
        searchModel.setUserName(ObjectUtil.emptyStrNvl(searchModel.getUserName()));
        searchModel.setProvinceCode(ObjectUtil.emptyStrNvl(searchModel.getProvinceCode()));
        searchModel.setCityCode(ObjectUtil.emptyStrNvl(searchModel.getCityCode()));

        if (searchModel.getTagId() == null) {
            return;
        }

        UserTagDO userTagSearchModel = new UserTagDO();
        userTagSearchModel.setTagId(searchModel.getTagId());

        List<UserTagDO> userTagList = tagService.getUserTagList(userTagSearchModel);
        List<Long> userIds = new ArrayList<>(Maps.uniqueIndex(userTagList, new Function<UserTagDO, Long>() {
            @Override
            public Long apply(UserTagDO input) {
                return input.getUserId();
            }
        }).keySet());

        if (CollectionUtils.isEmpty(userIds)) {
            userIds.add(-1L);
            searchModel.setUserIds(userIds);
        } else {
            if (searchModel.getUserIds() != null) {
                searchModel.getUserIds().addAll(userIds);
            } else {
                searchModel.setUserIds(userIds);
            }
        }
    }

    @Override
    @Transactional
    public BaseResultDTO addUserCompany(UserDO user, UserDO loginUser) throws Exception {
        if (!checkUserAccountIsUnique(user.getCpyId(), user.getUserAccount())) {
            LOGGER.warn("UserServiceImpl-addUserCompany userAccount is not unique|addUser={}|loginUser={}", user, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您输入的用户账号已存在，请更换账户名后再添加用户！");
        }

        //保存到用户基表
        saveBasicUser(user, loginUser);

        Long accountId;
        try {
            user.setSysType(WanmaConstants.SYS_TYPE_USER);
            accountId = finAccountService.addFinAccount(user);
            if (accountId == null) {
                userMapper.deleteByUserId(user.getUserId());
                LOGGER.error("UserServiceImpl-addUserCompany addFinAccount is error, accountId is null|addUser={}|loginUser={}", user, loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "添加用户发生异常，请刷新页面后重试！");
            }
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl-addUserCompany addFinAccount is error|addUser={}|loginUser={}", user, loginUser, e);
            throw e;
        }

        if (user.getCpyNumber() == 1000) {
            if (userNormalMapper.insertUserNormal(UserNormalDO.valueOf(user, accountId)) < 1) {
                LOGGER.error("UserServiceImpl-addUserCompany insertUserNormal is error|addUser={}|loginUser={}", user, loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "添加用户发生异常，请刷新页面后重试！");
            }
        } else {
            if (userCompanyMapper.insertUserCompany(UserCompanyDO.valueOf(user, accountId, loginUser)) < 1) {
                LOGGER.error("UserServiceImpl-addUserCompany insertUserCompany is error|addUser={}|loginUser={}", user, loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "添加用户发生异常，请刷新页面后重试！");
            }
        }

        return new BaseResultDTO();
    }

    @Transactional
    private void saveBasicUser(UserDO user, UserDO loginUser) {
        user.setUserPassword(MD5Util.Md5(WanmaConstants.DEFAULT_LOGIN_PASSWORD));
        if (user.getCpyNumber() == 1000) {
            user.setUserLevel(WanmaConstants.USER_LEVEL_NORMAL);
        } else {
            user.setUserLevel(WanmaConstants.USER_LEVEL_COMPANY);
        }
        user.setUserStatus(WanmaConstants.USER_STATUS_NORMAL);
        user.setUserHeadImg(WanmaConstants.DEFAULT_USER_HEAD_IMG);
        user.setCreator(loginUser.getUserAccount());
        user.setCreatorId(loginUser.getUserId());
        userMapper.insertUser(user);
    }

    @Override
    public void exportUser(HttpServletResponse response, UserDO searchModel, UserDO loginUser) {
        List<UserDO> userList = getExportUserList(searchModel);
        if (CollectionUtils.isEmpty(userList)) {
            LOGGER.warn("UserServiceImpl-exportUser exportUserList is empty|searchModel={}|loginUser={}", searchModel, loginUser);
            ErrorHtmlUtil.createErrorPage(response, "您导出的数据不存在，请修改正确的查询条件后再导出！");
            return;
        }

        List<String> attrList = Lists.newArrayList("userAccount", "cpyName", "levelName", "chUserStatus", "userAddress", "userName", "userCar", "userRealName", "userSex", "userPhone", "userIdcard");
        List<String> header = Lists.newArrayList("账号", "渠道", "等级", "状态", "城市", "昵称", "车辆", "姓名", "性别", "手机号", "身份证");
        ExcelExporterUtil.exportExcel(response, attrList, header, userList, UserDO.class, DownFileConstants.FILE_USER_EXPORT, DownFileConstants.FILE_USERS_EXPORT_SHEET);
    }

    private List<UserDO> getExportUserList(UserDO searchModel) {
        searchModel.setPager(null);
        List<UserDO> userList = getUserList(searchModel);
        Map<Integer, String> userStatusMap = new HashMap<>();
        userStatusMap.put(WanmaConstants.USER_STATUS_NORMAL, "正常");
        userStatusMap.put(WanmaConstants.USER_STATUS_FROZEN, "冻结");
        userStatusMap.put(WanmaConstants.USER_STATUS_DELETE, "删除");

        Map<Integer, String> userSexMap = new HashMap<>();
        userSexMap.put(0, "未知");
        userSexMap.put(1, "男");
        userSexMap.put(2, "女");

        for (UserDO user : userList) {
            user.setChUserStatus(userStatusMap.get(user.getUserStatus()));
            user.setChUserSex(userSexMap.get(user.getUserSex()));
        }

        return userList;
    }

    @Override
    @Transactional
    public BaseResultDTO resetLoginPassword(String userIds, UserDO loginUser) throws Exception {
        List<Long> userIdList = SplitterUtil.splitToLongList(userIds, ",", null);
        if (CollectionUtils.isEmpty(userIdList)) {
            LOGGER.warn("UserServiceImpl-resetLoginPassword userIds is null|userIds={}|loginUser={}", userIds, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "请选择要重置密码的用户！");
        }

        for (Long userId : userIdList) {
            modifyLoginPassword(userId, WanmaConstants.DEFAULT_LOGIN_PASSWORD, loginUser);
        }

        return new BaseResultDTO();
    }

    @Override
    @Transactional
    public BaseResultDTO modifyUser(UserDO user, UserDO loginUser) {
        UserDO oldUser = userMapper.selectUserByUserId(user.getUserId());
        if (oldUser == null) {
            LOGGER.warn("UserServiceImpl-modifyUser oldUser is null|updateUser={}|loginUser={}", user, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改用户信息失败，找不到该用户，请刷新页面后重试！");
        }

        user.setModifier(loginUser.getUserAccount());
        user.setUserName(ObjectUtil.emptyStrNvl(user.getUserName()));
        user.setUserRealName(ObjectUtil.emptyStrNvl(user.getUserRealName()));

        if (userMapper.updateByUserIdSelective(user) < 1) {
            LOGGER.error("UserServiceImpl-modifyUser is error, update to sql is error|newUser={}|loginUser={}", user, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改用户信息失败，请刷新页面后重试！");
        }

        BaseResultDTO result = new BaseResultDTO();
        if (user.getCpyType() == 0) {
            result = modifyUserNormal(user, loginUser);
        } else if (user.getCpyType() == 1) {
            result = modifyUserCompany(user, loginUser);
        }
        return result;
    }

    @Transactional
    private BaseResultDTO modifyUserNormal(UserDO user, UserDO loginUser) {
        UserNormalDO userNormal = new UserNormalDO();
        userNormal.setUserId(user.getUserId());
        userNormal.setNormName(user.getUserName());
        userNormal.setNormRealName(user.getUserRealName());
        userNormal.setNormPhone(user.getUserPhone());
        userNormal.setNormSex(user.getUserSex());
        userNormal.setNormPlateNum(user.getNormPlateNum());

        if (userNormalMapper.updateByUserIdSelective(userNormal) < 1) {
            LOGGER.error("UserServiceImpl-modifyUserNormal is error, update to sql is error|newUser={}|loginUser={}", user, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改用户信息失败，请刷新页面后重试！");
        }
        return new BaseResultDTO();

    }

    @Transactional
    private BaseResultDTO modifyUserCompany(UserDO user, UserDO loginUser) {
        UserCompanyDO userCompany = new UserCompanyDO();
        userCompany.setUserId(user.getUserId());
        userCompany.setUserName(user.getUserName());
        userCompany.setUserCpyName(user.getUserRealName());
        userCompany.setUserCpyPhone(user.getUserPhone());
        userCompany.setUserCpySex(user.getUserSex());
        userCompany.setUserPlateNum(user.getNormPlateNum());

        if (userCompanyMapper.updateByUserIdSelective(userCompany) < 1) {
            LOGGER.error("UserServiceImpl-modifyUserNormal is error, update to sql is error|newUser={}|loginUser={}", user, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改用户信息失败，请刷新页面后重试！");
        }
        return new BaseResultDTO();
    }

    @Override
    @Transactional
    public BaseResultDTO modifyUserLevel(UserDO user, UserDO loginUser) {
        UserDO oldUser = userMapper.selectUserByUserId(user.getUserId());
        if (oldUser == null) {
            LOGGER.warn("UserServiceImpl-modifyUserLevel oldUser is null|updateUser={}|loginUser={}", user, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改用户等级失败，找不到该用户，请刷新页面后重试！");
        }

        user.setModifier(loginUser.getUserAccount());

        if (userMapper.updateByUserIdSelective(user) < 1) {
            LOGGER.error("UserServiceImpl-modifyUserLevel is error, update to sql is error|newUser={}|loginUser={}", user, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改用户等级失败，请刷新页面后重试！");
        }

        return new BaseResultDTO();
    }

    @Override
    @Transactional
    public ResultDTO<String> modifyUserHeadImg(MultipartFile[] file, Long userId, UserDO loginUser) {
        ResultDTO<String> result = new ResultDTO<>();

        UserDO user = userMapper.selectUserByUserId(userId);
        if (user == null) {
            result.setSuccess(false);
            result.setResultCode(ResultCodeConstants.FAILED);
            result.setErrorDetail("修改头像失败，该用户不存在！");
            return result;
        }

        String referenceId = "" + userId;
        //非默认
        if (user.getUserHeadImg() == WanmaConstants.CUSTOMIZE_USER_HEAD_IMG) {
            List<String> oldFileUrlList = multipartFileService.getAllMultiUrl(WanmaConstants.USER_AVATAR, referenceId);
            multipartFileService.deleteMulti(oldFileUrlList, WanmaConstants.USER_AVATAR, referenceId, loginUser);
        }

        String results = multipartFileService.saveMultiFile(file, WanmaConstants.USER_AVATAR, referenceId, false, loginUser);

        List<String> fileUrlList = multipartFileService.getAllMultiUrl(WanmaConstants.USER_AVATAR, referenceId);
        if (WanmaConstants.PROCESS_STATUS_OK.equals(results)) {
            result.setModule(userId + "-" + fileUrlList.get(fileUrlList.size() - 1));
            UserDO modifyUser = new UserDO();
            modifyUser.setUserId(userId);
            modifyUser.setUserHeadImg(WanmaConstants.CUSTOMIZE_USER_HEAD_IMG);
            userMapper.updateByUserIdSelective(modifyUser);
        } else {
            result.setSuccess(false);
            result.setResultCode(ResultCodeConstants.FAILED);
            result.setErrorDetail("修改头像失败！");
            return result;
        }
        return result;
    }

    @Override
    @Transactional
    public BaseResultDTO modifyLoginPassword(Long userId, String password, UserDO loginUser) {
        if (Strings.isNullOrEmpty(password)) {
            LOGGER.warn("UserServiceImpl-modifyLoginPassword newPassword is null|oldUserId={}|loginUser={}", userId, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新的登录密码不能为空，请刷新页面后重试！");
        }

        UserDO oldUser = userMapper.selectUserByUserId(userId);
        if (oldUser == null) {
            LOGGER.warn("UserServiceImpl-modifyLoginPassword oldUser is null|oldUserId={}|loginUser={}", userId, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "要修改的用户不存在，请刷新页面后重试！");
        }

        UserDO updateUser = new UserDO();
        updateUser.setUserId(userId);
        updateUser.setUserPassword(MD5Util.Md5(password));
        updateUser.setModifier(loginUser.getUserAccount());
        if (userMapper.updateByUserIdSelective(updateUser) != 1) {
            LOGGER.error("UserServiceImpl-modifyLoginPassword update to sql is error|updateUserId={}|loginUser={}", userId, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改密码失败，请刷新页面后重试！");
        }

        return new BaseResultDTO();
    }

    @Override
    @Transactional
    public BaseResultDTO freezeUser(String userIds, UserDO loginUser) {
        List<Long> userIdList = SplitterUtil.splitToLongList(userIds, ",", null);
        if (CollectionUtils.isEmpty(userIdList)) {
            LOGGER.warn("UserServiceImpl-freezeUser userIds is null|userIds={}|loginUser={}", userIds, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您要禁用的用户不存在，请选择正确的用户后再禁用！");
        }

        List<UserDO> userList = userMapper.selectUserByUserIds(userIdList);
        if (CollectionUtils.isEmpty(userList)) {
            LOGGER.warn("UserServiceImpl-freezeUser userList is null|userIds={}|loginUser={}", userIds, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您要禁用的用户不存在，请选择正确的用户后再禁用！");
        }

        for (UserDO user : userList) {
            if (!Objects.equals(WanmaConstants.USER_STATUS_NORMAL, user.getUserStatus())) {
                LOGGER.warn("UserServiceImpl-freezeUser userStatus is not USER_STATUS_NORMAL|userId={}|userStatus={}|loginUser={}", user.getUserId(), user.getUserStatus(), loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您选择的用户" + user.getUserAccount() + "非可禁用状态，请选择正确的用户后再解禁！");
            }
        }

        //修改用户状态到禁用
        batchUpdateUserStatus(userList, WanmaConstants.USER_STATUS_FROZEN, loginUser);
        userCardService.disableCardByUserId(userIdList, loginUser);

        return new BaseResultDTO();
    }

    @Override
    @Transactional
    public BaseResultDTO unfreezeUser(String userIds, UserDO loginUser) {
        List<Long> userIdList = SplitterUtil.splitToLongList(userIds, ",", null);
        if (CollectionUtils.isEmpty(userIdList)) {
            LOGGER.warn("UserServiceImpl-unfreezeUser userIds is null|userIds={}|loginUser={}", userIds, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您要解禁的用户不存在，请选择正确的用户后再解禁！");
        }

        List<UserDO> userList = userMapper.selectUserByUserIds(userIdList);
        if (CollectionUtils.isEmpty(userList)) {
            LOGGER.warn("UserServiceImpl-unfreezeUser userList is null|userIds={}|loginUser={}", userIds, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您要解禁的用户不存在，请选择正确的用户后再解禁！");
        }

        for (UserDO user : userList) {
            if (!Objects.equals(WanmaConstants.USER_STATUS_FROZEN, user.getUserStatus())) {
                LOGGER.warn("UserServiceImpl-freezeUser userStatus is not USER_STATUS_FROZEN|userId={}|userStatus={}|loginUser={}", user.getUserId(), user.getUserStatus(), loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您选择的用户" + user.getUserAccount() + "非禁用状态，请选择正确的用户后再解禁！");
            }
        }

        //修改用户状态到解禁
        batchUpdateUserStatus(userList, WanmaConstants.USER_STATUS_NORMAL, loginUser);
        userCardService.releaseCardByUserId(userIdList, loginUser);

        return new BaseResultDTO();
    }

    @Transactional
    private void batchUpdateUserStatus(List<UserDO> userList, Integer userStatus, UserDO loginUser) {
        for (UserDO user : userList) {
            UserDO updateUser = new UserDO();
            updateUser.setUserId(user.getUserId());
            updateUser.setUserStatus(userStatus);
            updateUser.setModifier(loginUser.getUserAccount());
            userMapper.updateByUserIdSelective(updateUser);
        }
    }

    @Override
    public UserDO getUserIndex(Long userId, UserDO loginUsr) {
        UserDO searchModel = new UserDO();
        searchModel.setUserId(userId);
        List<UserDO> userList = getUserList(searchModel);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }

        UserDO user = userList.get(0);
        AccountBalanceBO balanceSearchModel = new AccountBalanceBO();
        balanceSearchModel.setUserId(user.getUserId());
        balanceSearchModel.setCpyType(user.getCpyType());
        String balance = "金额获取失败";
        try {
            balance = finAccountService.getFinAccountBalance4User(balanceSearchModel) + "";
        } catch (Exception e) {
            LOGGER.warn(this.getClass() + "-getUserIndex getFinAccountBalance4User is error|userId={}|loginUser={}", userId, loginUsr, e);
        }

        user.setUserBalance(balance);
        user.setUserHeadImgUrl("aa");//TODO:用户头像默认路径
        if (user.getUserHeadImg() == 1) {
            List<String> headImgUrls = multipartFileService.getAllMultiUrl(WanmaConstants.USER_AVATAR, userId + "");
            if (CollectionUtils.isNotEmpty(headImgUrls)) {
                user.setUserHeadImgUrl(headImgUrls.get(0));
            }
        }
        return user;
    }

    @Override
    public UserDO getUserById(Long userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public UserNormalDO getUserNormalByUserId(Long userId) {
        return userNormalMapper.selectUserNormalByUserId(userId);
    }

    @Override
    public UserCompanyDO getUserCompanyByUserId(Long userId) {
        return userCompanyMapper.selectUserCompanyByUserId(userId);
    }

    @Override
    public Map<LevelDO, Long> getCompanyIndexUser(Long cpyId, UserDO loginUsr) {
        Map<LevelDO, Long> result = new HashMap<>();

        List<Map<String, Long>> userCountList;

        CompanyDO company = companyService.getCompanyListById(cpyId);
        if (company.getCpyNumber() == 1000) {
            userCountList = userNormalMapper.countNormalUserByCpyId(cpyId);
        } else {
            userCountList = userCompanyMapper.countCompanyUserByCpyId(cpyId);
        }


        Map<Long, Long> userCountMap = new HashMap<>();
        for (Map<String, Long> map : userCountList) {
            userCountMap.put(map.get("level_id"), map.get("num"));
        }

        List<LevelDO> levelList = levelService.getLevelListByIds(new ArrayList<>(userCountMap.keySet()));
        Map<Long, LevelDO> levelMap = Maps.uniqueIndex(levelList, new Function<LevelDO, Long>() {
            @Override
            public Long apply(LevelDO input) {
                return input.getLevelId();
            }
        });

        long count = 0L;
        for (Map.Entry<Long, Long> entry : userCountMap.entrySet()) {
            result.put(levelMap.get(entry.getKey()), entry.getValue());
            count = count + entry.getValue();
        }

        LevelDO level = new LevelDO();
        level.setLevelName("用户总数");
        level.setLevelSeq("0");
        result.put(level, count);

        return result;
    }

    private UserDO findUser(UserDO userDO) {
        Integer userLevel = userDO.getUserLevel();
        if (userLevel == null) {
            userLevel = 0;
        }
        switch (userLevel) {
            case WanmaConstants.USER_LEVEL_ADMIN:
            case WanmaConstants.USER_LEVEL_WORK:
                UserAdminDO userAdminDO = userAdminMapper.selectUserAdminByUserId(userDO.getUserId());
                userDO = generalUserAdmin2UserDO(userDO, userAdminDO);
                break;
            default:
                break;
        }
        return userDO;
    }

    private UserDO generalUserAdmin2UserDO(UserDO userDO, UserAdminDO userAdminDO) {
        if (null != userAdminDO) {
            userDO.setUserName(userAdminDO.getAdminName());
            userDO.setUserPhone(userAdminDO.getUserPhone());
            userDO.setCpyId(userAdminDO.getCpyId());
        }
        return userDO;
    }

    @Override
    public boolean checkUserAccountIsUnique(Long cpyId, String userAccount) {
        return userMapper.selectUserByUserAccountAndCpyId(userAccount, cpyId) == null;
    }

	@Override
	@Transactional
	public BaseResultDTO addUserCompanyBatch(UserDO user, MultipartFile file,UserDO loginUser)throws Exception {
		 if (file == null || file.isEmpty()) {
	            LOGGER.warn(this.getClass() + "-importElectricPile is failed, multipartFile is null|loginUser={}", loginUser);
	            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "请导入正确的电桩excel文件！");
	      }
		 try {
			// 读取EXCEL
			 BatchResultDTO<List<String>> result = ExcelImportUtil.importListFromExcel(file);
			 if (result.isFailed()) {
	             return result;
	         }	
			 return processingData(result.getModule(), loginUser,user);
		} catch (Exception e) {
            LOGGER.error(this.getClass() + "-addUserCompanyBatch is error|fileName={}|loginUser={}", file.getOriginalFilename(), loginUser, e);
            throw new Exception("系统错误，批量新增用户失败！");
        }
	}
	
	private BaseResultDTO processingData(List<List<String>> data,UserDO loginUser, UserDO user) throws Exception{
		BaseResultDTO result = new BaseResultDTO();
		List<UserDO> userList = new ArrayList<UserDO>();
		List<String> userAccountlist = new ArrayList<>();
		//错误提示
		String errorString = "";
		// 第一次循环 判断账号是否符合
		data.remove(0);//第一行是行头 删除
		for (List<String> line : data) {
	            if (CollectionUtils.isEmpty(line)) {
	                continue;
	            }
	            userAccountlist.add(line.get(0));
	    }
		result = checkUserAccount(userAccountlist,user);
		if (result.isFailed()) {
			return result;
		}
		//第二次循环 判断其他参数格式 没有错误则增加到userList
		for (int i = 0; i < data.size(); i++) {
            if (CollectionUtils.isEmpty(data.get(i))) {
                continue;
            }
            UserDO userDO = new UserDO();
            //账号
            userDO.setUserAccount(data.get(i).get(0));
            //手机号
            if (!Strings.isNullOrEmpty(data.get(i).get(1))&&!data.get(i).get(1).matches(WanmaConstants.REGEXP_PURE_NUMBER_11)) {
				errorString+="第"+(i+1)+"行错误手机号格式错误;";
			}else {
				userDO.setUserPhone(data.get(i).get(1));
			}
            //用户地区编码
            if (!Strings.isNullOrEmpty(data.get(i).get(2))&&!data.get(i).get(2).matches(WanmaConstants.REGEXP_PURE_NUMBER_6)) {
				errorString+="第"+(i+1)+"行错误地区编码格式错误;";
			}else {
				userDO.setCityCode(data.get(i).get(2));
			}
            //姓名
            if (!Strings.isNullOrEmpty(data.get(i).get(3))&&!data.get(i).get(3).matches(WanmaConstants.REGEXP_USER_NAME)) {
				errorString+="第"+(i+1)+"行错误姓名格式错误;";
			}else {
				userDO.setUserCpyName(data.get(i).get(3)==null?"":data.get(i).get(3));
			}
            //性别
            if (!Strings.isNullOrEmpty(data.get(i).get(4))&&!data.get(i).get(4).matches(WanmaConstants.REGEXP_SEX)) {
				errorString+="第"+(i+1)+"行错误性别格式错误;";
			}else if ("男".equals(data.get(i).get(4))) {
				userDO.setUserSex(1);
			}else{
				userDO.setUserSex(2);
			}
            userList.add(userDO);
		}
		//excel格式没有错误则批量增加到数据库
		if (errorString!="") {
			return new BaseResultDTO(false, ResultCodeConstants.FAILED, errorString);
		}else {
			for (UserDO userDO : userList) {
                userDO.setCpyId(user.getCpyId());
		         userDO.setTradeType(user.getTradeType());
                userDO.setLevelId(user.getLevelId());
                userDO.setUserStatus(user.getUserStatus());
                userDO.setCpyNumber(user.getCpyNumber());
                result = addUserCompany(userDO, loginUser);
				if (result.isFailed()) {
					return result;
				}
			}
		}
		return result;
	}
	public BaseResultDTO checkUserAccount( List<String> list, UserDO user) {
		String errorString = "";
		for(int i=0 ;i<list.size();i++){//循环获取用户账号
			if (!list.get(i).matches(WanmaConstants.REGEXP_PURE_NUMBER_12)) {
				errorString+="第"+(i+1)+"行错误用户账号必须是12位的纯数字;";
			}else if (userMapper.selectUserByUserAccountAndCpyId(list.get(i),user.getCpyId())!=null) {
				errorString+="第"+(i+1)+"行错误账号已存在;";
			}else {
				for(int j=i+1;j<list.size();j++){
					if((new BigDecimal(list.get(i)).toString().equals(new BigDecimal(list.get(j)).toString()))){
						errorString+="第"+(i+1)+"行错误账号与excel其他账号重复;";
					}
				}
			}
		}
		if ("".equals(errorString)) {
			return new BaseResultDTO();
		}else {
			return new BaseResultDTO(false, ResultCodeConstants.FAILED, errorString);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
