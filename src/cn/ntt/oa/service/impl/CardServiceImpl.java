package cn.ntt.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.Card;
import cn.ntt.oa.service.CardService;

@Service
@Transactional
public class CardServiceImpl extends DaoSupportImpl<Card> implements
		CardService {

}
