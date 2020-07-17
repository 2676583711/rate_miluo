package com.rate.system.rate_system.api.service.impl;

import com.rate.system.rate_system.api.dao.VerificationDao;
import com.rate.system.rate_system.api.entity.Verification;
import com.rate.system.rate_system.api.service.ApiLoginService;
import com.rate.system.rate_system.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiLoginServiceImpl implements ApiLoginService {

    @Autowired
    private VerificationDao verificationDao;


    @Override
    public void insert(Verification verification) {
        verificationDao.insertTemplate(verification, true);
    }

    @Override
    public Verification selectByUserId(Long userId) {
        Verification vf = verificationDao.selectByUserId(userId);
        return vf;
    }

    @Override
    public void updateType(Verification verification) {
        verificationDao.updateTemplateById(verification);
    }

    @Override
    public boolean appAutoCode(String code, Long userId) {
        Verification vf = verificationDao.appAutoCode(code);
        if (vf != null && vf.getUserId().equals(userId)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkLogin(String userId, String token) {
        if (StringUtils.isBlank(userId) || "undefined".equalsIgnoreCase(userId)) {
            return false;
        }
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        Verification verification = verificationDao.selectByUserId(id);
        if (verification == null) {
            return false;
        }
        if (verification.getCode().equals(token)) {
            return true;
        }
        return false;
    }

}
