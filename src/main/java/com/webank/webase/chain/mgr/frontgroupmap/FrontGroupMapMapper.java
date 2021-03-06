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
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FrontGroupMapMapper {

    int add(TbFrontGroupMap tbFrontGroupMap);

    int getCount(MapListParam mapListParam);
    
    int removeByChainId(@Param("chainId") Integer chainId);
    
    int removeByFrontId(@Param("frontId") Integer frontId);

    int removeByGroupId(@Param("chainId") Integer chainId, @Param("groupId") Integer groupId);

    List<FrontGroup> getList(MapListParam mapListParam);
}
