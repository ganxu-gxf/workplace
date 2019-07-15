package com.example.gameSystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 基类
 *
 * @author hboxs Team
 * @version 1.0
 */
@EntityListeners(EntityListener.class)
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

	private static final Long serialVersionUID = 1L;

	/** ID */

	private ID id;

	/** 创建日期 */
	private Date createDate;

	/** 修改日期 */
	private Date modifyDate;

	/**
	 * 获取ID
	 *
	 * @return ID
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public ID getId() {
		return id;
	}

	/**
	 * 设置ID
	 *
	 * @param id
	 *            ID
	 */
	public void setId(ID id) {
		this.id = id;
	}

	/**
	 * 获取创建日期
	 *
	 * @return 创建日期
	 */
	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置创建日期
	 *
	 * @param createDate
	 *            创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取修改日期
	 *
	 * @return 修改日期
	 */
	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * 设置修改日期
	 *
	 * @param modifyDate
	 *            修改日期
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * 重写toString方法
	 *
	 * @return 字符串
	 */
	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", getClass().getName(), getId());
	}

	/**
	 * 重写equals方法
	 *
	 * @param obj
	 *            对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		BaseEntity<?> other = (BaseEntity<?>) obj;
		return getId() != null && getId().equals(other.getId());
	}

	/**
	 * 重写hashCode方法
	 *
	 * @return HashCode
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += getId() != null ? getId().hashCode() * 31 : 0;
		return hashCode;
	}

}
