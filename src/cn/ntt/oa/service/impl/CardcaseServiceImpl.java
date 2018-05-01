package cn.ntt.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.Cardcase;
import cn.ntt.oa.service.CardcaseService;

@Service
@Transactional
public class CardcaseServiceImpl extends DaoSupportImpl<Cardcase> implements
		CardcaseService {

}
