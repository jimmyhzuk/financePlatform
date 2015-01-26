package com.sunlights.customer.dal;

import com.sunlights.customer.vo.CustomerVo;
import com.sunlights.common.vo.MsgSettingVo;
import models.Customer;
import models.CustomerGesture;
import models.CustomerMsgSetting;
import models.CustomerSession;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerManageDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface CustomerDao {
  public String getCustomerIdSeq();

  public Customer getCustomerByMobile(String mobile);

  public Customer getCustomerByCustomerId(String customerId);

  public Customer saveCustomer(Customer customer);

  public Customer updateCustomer(Customer customer);

  public CustomerVo getCustomerVoByPhoneNo(String mobilePhoneNo, String deviceNo);

  public CustomerVo getCustomerVoByIdCardNo(String idCardNo, String userName);

  public CustomerSession findCustomerSessionByToken(String token, Timestamp nMin);

  public CustomerSession findCustomerSessionByCustomerId(String customerId, String deviceNo);

  public CustomerSession saveCustomerSession(CustomerSession customerSession);

  public CustomerSession updateCustomerSession(CustomerSession customerSession);

  public CustomerGesture saveCustomerGesture(CustomerGesture customerGesture);

  public CustomerGesture updateCustomerGesture(CustomerGesture customerGesture);

  public CustomerGesture findCustomerGestureByDeviceNo(String customerId, String deviceNo);

    /**
     * 通过被推荐人查询推荐人信息
     * @param customerId 被推荐人
     * @return
     */
  public Customer findRecommenderInfo(String customerId);

    /**
     * 查询客户所属aiias 记录
     * @param customerId
     * @return
     */
    public List<String> findAliasByCustomerId(String customerId);

    /**
     * 通过customerId查询 当前客户在所有手机registrationId 集合
     * @param customerId
     * @return
     */
    public List<MsgSettingVo>  findRegistrationIdsByCustomerId(String customerId);
    public CustomerMsgSetting findCustomerMsgSetting(String registrationId, String deviceNo);
    public CustomerMsgSetting updateCustomerMsgSetting(CustomerMsgSetting customerMsgSetting);
    public CustomerMsgSetting createCustomerMsgSetting(CustomerMsgSetting customerMsgSetting);



}
