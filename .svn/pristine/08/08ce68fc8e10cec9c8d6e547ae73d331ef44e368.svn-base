package com.wanma.ims.controller.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.wanma.ims.common.domain.LevelDO;

/**
 * Created by xyc on 2017/6/16.
 * 用户总数vo
 */
public class UserCountVO {
    private String levelName;

    private Integer levelSeq;

    private Long count;

    public static List<UserCountVO> getUserVOList(Map<LevelDO, Long> map) {
        List<UserCountVO> voList = new ArrayList<>();
        for (Map.Entry<LevelDO, Long> entry : map.entrySet()) {
            UserCountVO vo = new UserCountVO();
            if(null != entry.getKey()){
            	 vo.setLevelName(entry.getKey().getLevelName());
                 vo.setLevelSeq(Integer.valueOf(entry.getKey().getLevelSeq()));
                 vo.setCount(entry.getValue());
            }
            voList.add(vo);
        }

        Collections.sort(voList, new Comparator<UserCountVO>() {
            @Override
            public int compare(UserCountVO o1, UserCountVO o2) {
            	if(null != o1.getLevelSeq() && null != o2.getLevelSeq()){
            		return o1.getLevelSeq() - o2.getLevelSeq();
            	}
            	return 0;
            	
            }
        });
        return voList;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelSeq() {
        return levelSeq;
    }

    public void setLevelSeq(Integer levelSeq) {
        this.levelSeq = levelSeq;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
