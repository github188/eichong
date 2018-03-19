package com.wanma.ims.service;

import com.wanma.ims.common.domain.TagDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.UserTagDO;
import com.wanma.ims.common.dto.BaseResultDTO;

import java.util.List;

/**
 * Created by xyc on 2017/7/21.
 * 标签逻辑处理接口
 */
public interface TagService {

    /**
     * 获取用户所有标签
     */
    List<UserTagDO> getUserTagList(UserTagDO searchModel);

    /**
     * 获取标签列表
     */
    List<TagDO> getTagList(TagDO searchModel);

    /**
     * 获取标签总数
     */
    long countTag(TagDO searchModel);

    /**
     * 获取标签列表
     */
    TagDO getTag(Long tagId);

    /**
     * 新增单个标签
     */
    BaseResultDTO addTag(TagDO tag, UserDO loginUser);

    /**
     * 新增单个用户标签
     */
    BaseResultDTO addUserTag(UserTagDO userTag, UserDO loginUser);

    /**
     * 修改单个标签
     */
    BaseResultDTO modifyTag(TagDO tag, UserDO loginUser);

    /**
     * 删除单个标签
     */
    BaseResultDTO delTag(String tagIds, UserDO loginUser) throws Exception;

    /**
     * 删除用户标签
     */
    BaseResultDTO delUserTag(String userTagIds, UserDO loginUser);
}
