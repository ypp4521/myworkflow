package org.zywx.appdo.workflow;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserEntityManagerFactory implements SessionFactory {

	@Autowired
    private CustomUserEntityManager customUserEntityManager;

    @Override
    public Class<?> getSessionType() {
       //注意此处也必须为Activiti原生类
        return UserIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return customUserEntityManager;
    }

    @Autowired
    public void setCustomUserEntityManager(CustomUserEntityManager customUserEntityManager) {
        this.customUserEntityManager = customUserEntityManager;
    }
}