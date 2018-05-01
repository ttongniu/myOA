package cn.ntt.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.Folder;
import cn.ntt.oa.service.FolderService;

@Service
@Transactional
public class FolderServiceImpl extends DaoSupportImpl<Folder> implements
		FolderService {

}
