package cn.ntt.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.Schedule;
import cn.ntt.oa.service.ScheduleService;

@Service
@Transactional
public class ScheduleServiceImpl extends DaoSupportImpl<Schedule> implements
		ScheduleService {

}
