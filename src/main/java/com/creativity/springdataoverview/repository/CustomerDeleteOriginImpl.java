package com.creativity.springdataoverview.repository;

import javax.persistence.EntityManager;

public class CustomerDeleteOriginImpl implements CustomerDeleteOrigin {

    private EntityManager entityManager;

    public CustomerDeleteOriginImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void deleteByOrigin(String origin) {
        entityManager.createNativeQuery("DELETE FROM TBL_FLIGHT WHERE origin = ? ")
                .setParameter(1, origin)
                .executeUpdate();
    }
}
