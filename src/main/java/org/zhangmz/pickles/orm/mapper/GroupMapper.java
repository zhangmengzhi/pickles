package org.zhangmz.pickles.orm.mapper;

import java.util.List;
import org.zhangmz.pickles.modules.vo.IdName;
import org.zhangmz.pickles.orm.MyMapper;
import org.zhangmz.pickles.orm.model.Group;

public interface GroupMapper extends MyMapper<Group> {
    
    /**
     * 根据条件查询
     * @param record
     * @return
     * 说明 ：MyMapper中可以自动生成这个方法，但是SQL语句不可修改
     * 不满足要求，这里重写这个方法
     */
    List<Group> select(Group record);    

    /**
     * 根据条件查询
     * @param record
     * @return 只查询id/name两个字段，用于下拉列表显示
     */
    List<IdName> selectIdName(Group record);
    
}