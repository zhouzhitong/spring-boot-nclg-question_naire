package com.nclg.mapper;

import com.nclg.entity.ExamInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (ExamInfo)表数据库访问层
 *
 * @author 周志通
 * @since 2020-09-20 15:38:01
 */
@Mapper
public interface ExamInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamInfo getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param examInfo 实例对象
     * @return 对象列表
     */
    List<ExamInfo> listByEntity(ExamInfo examInfo);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param examInfo 实例对象
     * @return 对象列表
     */
    ExamInfo getByEntity(ExamInfo examInfo);

    /**
     * 通过实体不为空的属性作为or like筛选条件查询列表
     *
     * @param examInfo 实例对象
     * @return 对象列表
     */
    List<ExamInfo> listByEntityLike(ExamInfo examInfo);

    /**
     * 通过Id列表作为筛选条件查询列表
     *
     * @param list
     * @return 对象列表
     */
    List<ExamInfo> listByIds(List<Long> list);

    /**
     * 新增实体属性不为null的列
     *
     * @param examInfo 实例对象
     * @return 影响行数
     */
    int insert(ExamInfo examInfo);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例对象list集合
     * @return 影响行数
     */
    int insertBatch(List<ExamInfo> list);

    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param examInfo 实例对象
     * @return 影响行数
     */
    int update(ExamInfo examInfo);

    /**
     * 通过主键修改实体列表，列表长度不能为0
     * 注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int updateBatch(List<ExamInfo> list);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过实体非空属性批量删除
     *
     * @param examInfo 实体
     * @return 影响行数
     */
    int deleteByEntity(ExamInfo examInfo);

    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 主键列表
     * @return 影响行数
     */
    int deleteByIds(List<Long> list);

    /**
     * 获取整张表的行数
     *
     * @return 统计行数
     */
    int countAll();

    /**
     * 通过实体不为空的属性作为筛选条件查询行数
     *
     * @param examInfo 实体
     * @return 统计行数
     */
    int countByEntity(ExamInfo examInfo);

}