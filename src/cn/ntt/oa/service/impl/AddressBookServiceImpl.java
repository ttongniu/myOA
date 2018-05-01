package cn.ntt.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.AddressBook;
import cn.ntt.oa.service.AddressBookService;

@Service
@Transactional
public class AddressBookServiceImpl extends DaoSupportImpl<AddressBook>
		implements AddressBookService {

}
