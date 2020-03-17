package com.chuyue.service;

import com.chuyue.mapper.DeptMapper;
import com.chuyue.pojo.Dept;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.ast.Var;
import org.hibernate.engine.jdbc.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl implements DeptService {

    private static final Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAllDept(int page, int rows) {
        logger.debug("分页查询全部部门信息");

        int currIndex = (page - 1) * rows;
        int pageSize = rows;

        //创建分页插件
        PageHelper.startPage(currIndex, pageSize);

        PageInfo<Dept> deptPageInfo = new PageInfo<>(deptMapper.findAllDept());
        List<Dept> list = deptPageInfo.getList();
        return list;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    @Override
    public int updateDeptByIds(String ids, String status) {
        int result = 0;
        for (String id : ids.split(",")) {
            deptMapper.updateDeptByIds(id, status);
            result++;
        }
        return result;
    }

    //   @Override
//    public List<Dept> findAllDeptByPage(int page, int rows) {
//        //  page == 1  rows == 2      【0,1】      (1 -1) * 2 ==0    1 * 2  = 2
//        //  page == 2  rows == 2      【2,3】      (2 -1) * 2 == 2   2 * 2 = 4
//        //  page == 3  rows == 2      【4,5】
//        //  page == 4  rows == 2      【6,7】      firstindex = (page - 1) * row   lastindex = page * row
//        //
//        //   1.把所以员工的信息都查出来
//        List<Dept> allDept = deptMapper.findAllDept();
//        int firstIndex = (page - 1) * rows;
//        int lastIndex = page * rows;
//        //2.page == 1 rows == 2
//        return allDept.subList(firstIndex, lastIndex);
//    }

    @Override
    public List<Dept> findAllDeptByPage(int page, int rows) {
        //  page == 1  rows == 2      【0,1】      (1 -1) * 2 ==0    1 * 2  = 2
        //  page == 2  rows == 2      【2,3】      (2 -1) * 2 == 2   2 * 2 = 4
        //  page == 3  rows == 2      【4,5】
        //  page == 4  rows == 2      【6,7】      firstindex = (page - 1) * row   lastindex = page * row
        //
        //   1.把所以员工的信息都查出来
        //        select * from dept_p limit #{currIndex} , #{rows}
        int currIndex = (page -1) * rows;
        int pageSize = rows;
        List<Dept> deptList = deptMapper.findAllDeptByLimit(currIndex, pageSize);
        return deptList;
    }
}
