/**
 * Copyright 2014-2019  the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.chain.mgr.frontgroupmap;

import com.webank.webase.chain.mgr.frontgroupmap.entity.FrontGroup;
import com.webank.webase.chain.mgr.frontgroupmap.entity.MapListParam;
import com.webank.webase.chain.mgr.frontgroupmap.entity.TbFrontGroupMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FrontGroupMapService {

    @Autowired
    private FrontGroupMapMapper frontGroupMapMapper;

    /**
     * add new mapping
     */
    public TbFrontGroupMap newFrontGroup(Integer chainId, Integer frontId, Integer groupId) {
        TbFrontGroupMap tbFrontGroupMap = new TbFrontGroupMap(chainId, frontId, groupId);

        //add db
        frontGroupMapMapper.add(tbFrontGroupMap);
        return tbFrontGroupMap;
    }

    /**
     * get map count
     */
    public int getCount(MapListParam param) {
        return frontGroupMapMapper.getCount(param);
    }

    /**
     * remove by chainId
     */
    public void removeByChainId(int chainId) {
        if (chainId == 0) {
            return;
        }
        //remove by chainId
        frontGroupMapMapper.removeByChainId(chainId);
    }
    
    /**
     * remove by groupId
     */
    public void removeByGroupId(int chainId, int groupId) {
        if (chainId == 0 || groupId == 0) {
            return;
        }
        //remove by groupId
        frontGroupMapMapper.removeByGroupId(chainId, groupId);
    }

    /**
     * remove by frontId
     */
    public void removeByFrontId(int frontId) {
        if (frontId == 0) {
            return;
        }
        //remove by frontId
        frontGroupMapMapper.removeByFrontId(frontId);
    }

    /**
     * get map list by groupId
     */
    public List<FrontGroup> listByGroupId(int groupId) {
        if (groupId == 0) {
            return null;
        }
        MapListParam param = new MapListParam();
        param.setGroupId(groupId);
        return getList(param);
    }

    /**
     * get map list
     */
    public List<FrontGroup> getList(MapListParam mapListParam) {
        return frontGroupMapMapper.getList(mapListParam);
    }
}
