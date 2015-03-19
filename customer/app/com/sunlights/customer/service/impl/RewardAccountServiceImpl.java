package com.sunlights.customer.service.impl;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.customer.dal.RewardAccountDao;
import com.sunlights.customer.dal.RewardAccountDetailsDao;
import com.sunlights.customer.dal.impl.RewardAccountDaoImpl;
import com.sunlights.customer.dal.impl.RewardAccountDetailsDaoImpl;
import com.sunlights.customer.service.RewardAccountService;
import models.Fund;
import models.RewardAccountBalance;
import models.RewardAccountDetails;
import play.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: RewardAccountBalanceServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class RewardAccountServiceImpl implements RewardAccountService {
    private RewardAccountDao rewardAccountBalanceDao = new RewardAccountDaoImpl();
    private RewardAccountDetailsDao rewardAccountDetailsDao = new RewardAccountDetailsDaoImpl();


    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public RewardAccountBalance createRewardAccount(String customerId) {
        Timestamp currentTime = DBHelper.getCurrentTime();

        RewardAccountBalance rewardAccountBalance = new RewardAccountBalance();
        rewardAccountBalance.setCustomerId(customerId);
        rewardAccountBalance.setCreateTime(currentTime);
        rewardAccountBalanceDao.createRewardAccount(rewardAccountBalance);
        return rewardAccountBalance;
    }

    @Override
    public RewardAccountBalance findRewardAccountByCustomerId(String customerId) {
        return rewardAccountBalanceDao.findRewardAccountByCustomerId(customerId);
    }

    @Override
    public void updateRewardAccount(String custId, String scene, String rewardType, long amt, String fundFlowType) {
        RewardAccountBalance rewardAccountBalance = findRewardAccountByCustomerId(custId);

        RewardAccountDetails rewardAccountDetails = new RewardAccountDetails();
        rewardAccountDetails.setActivityType(scene);
        rewardAccountDetails.setCustomerId(custId);
        rewardAccountDetails.setFundFlowType(fundFlowType);
        rewardAccountDetails.setRewardType(rewardType);
        rewardAccountDetails.setIncomeExpendBalance(amt);
        rewardAccountDetails.setRewordSequence(createSeq());

        if(RewardAccountDetails.FundFlowType.INCOME.getType().equals(fundFlowType)) {
            rewardAccountBalance.setRewardAccountBalance(rewardAccountBalance.getRewardAccountBalance() + amt);
            rewardAccountBalance.setRewardIncomeBalance(rewardAccountBalance.getRewardIncomeBalance() + amt);
        } else {
            rewardAccountBalance.setRewardAccountBalance(rewardAccountBalance.getRewardAccountBalance() - amt);
            rewardAccountBalance.setRewardExpendBalance(rewardAccountBalance.getRewardExpendBalance() + amt);
        }

        rewardAccountDetails.setRewardAmountBalance(rewardAccountBalance.getRewardAccountBalance());

        rewardAccountDetailsDao.doInsert(rewardAccountDetails);

        rewardAccountBalanceDao.updateRewardAccount(rewardAccountBalance);

        Logger.debug("更新奖励账户成功");
    }

    private String createSeq() {
        String date = CommonUtil.dateToString(new Date(), CommonUtil.YYYYMMDDHHMMSS);

        return "R" + date + atomicInteger.get();
    }
}
