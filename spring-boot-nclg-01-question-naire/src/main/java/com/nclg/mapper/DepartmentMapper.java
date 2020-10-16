package com.nclg.mapper;

import com.nclg.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Department)表数据库访问层
 *
 * @author 周志通
 * @since 2020-10-11 15:35:31
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Department getById(Integer id);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param department 实例对象
     * @return 对象列表
     */
    List<Department> listByEntity(Department department);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param department 实例对象
     * @return 对象列表
     */
    Department getByEntity(Department department);

    /**
     * 通过实体不为空的属性作为or like筛选条件查询列表
     *
     * @param department 实例对象
     * @return 对象列表
     */
    List<Department> listByEntityLike(Department department);

    /**
     * 通过Id列表作为筛选条件查询列表
     *
     * @param list
     * @return 对象列表
     */
    List<Department> listByIds(List<Integer> list);

    /**
     * 新增实体属性不为null的列
     *
     * @param department 实例对象
     * @return 影响行数
     */
    int insert(Department department);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例对象list集合
     * @return 影响行数
     */
    int insertBatch(List<Department> list);

    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param department 实例对象
     * @return 影响行数
     */
    int update(Department department);

    /**
     * 通过主键修改实体列表，列表长度不能为0
     * 注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int updateBatch(List<Department> list);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过实体非空属性批量删除
     *
     * @param department 实体
     * @return 影响行数
     */
    int deleteByEntity(Department department);

    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 主键列表
     * @return 影响行数
     */
    int deleteByIds(List<Integer> list);

    /**
     * 获取整张表的行数
     *
     * @return 统计行数
     */
    int countAll();

    /**
     * 通过实体不为空的属性作为筛选条件查询行数
     *
     * @param department 实体
     * @return 统计行数
     */
    int countByEntity(Department department);

}