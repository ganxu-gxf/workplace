package com.example.gameSystem.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Listener - 创建日期、修改日期
 * 
 * @author hboxs Team
 * @version 1.0
 */
public class EntityListener {

	/**
	 * 保存前处理
	 * 
	 * @param entity
	 *            实体对象
	 */
	@PrePersist
	public void prePersist(BaseEntity<?> entity) {
		if(entity.getCreateDate() == null) {
			entity.setCreateDate(new Date());
		}
		entity.setModifyDate(new Date());
	}

	/**
	 * 更新前处理
	 * 
	 * @param entity
	 *            实体对象
	 */
	@PreUpdate
	public void preUpdate(BaseEntity<?> entity) {
		entity.setModifyDate(new Date());
	}

}