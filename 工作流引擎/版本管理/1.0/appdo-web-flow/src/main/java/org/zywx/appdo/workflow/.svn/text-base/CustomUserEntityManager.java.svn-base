package org.zywx.appdo.workflow;

import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.stereotype.Component;

@Component
public class CustomUserEntityManager extends UserEntityManager {
    @Override
    public UserEntity findUserById(String userId) {
        return (UserEntity) super.findUserById(userId);
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        return super.findGroupsByUser(userId);
    }
}
