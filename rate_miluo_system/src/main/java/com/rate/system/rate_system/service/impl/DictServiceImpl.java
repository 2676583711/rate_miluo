package com.rate.system.rate_system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.system.rate_system.dao.DictDao;
import com.rate.system.rate_system.entity.Dict;
import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.service.DictService;
import com.rate.system.rate_system.utils.StringUtils;


@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictDao dictDao;

    @Override
    public Dict get(Long id) {
        return dictDao.single(id);
    }

    @Override
    public List<Dict> list(Map<String, Object> map) {
    	
        return dictDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return dictDao.count(map);
    }

    @Override
    public int save(Dict dict) {
        return dictDao.insertReturnKey(dict).getInt();
    }

    @Override
    public int update(Dict dict) {
        return dictDao.updateTemplateById(dict);
    }

    @Override
    public int remove(Long id) {
        return dictDao.deleteById(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
    	int count = 0;
		for(int i=0;i<ids.length;i++) {
			dictDao.deleteById(ids[i]);
			count++;
		}
		return count;
    }

    @Override

    public List<Dict> listType() {
        return dictDao.listType();
    }

    @Override
    public String getName(String type, String value) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("type", type);
        param.put("value", value);
        String rString = dictDao.list(param).get(0).getName();
        return rString;
    }

    @Override
    public List<Dict> getHobbyList(User userDO) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "hobby");
        List<Dict> hobbyList = dictDao.list(param);

        if (StringUtils.isNotEmpty(userDO.getHobby())) {
            String userHobbys[] = userDO.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (Dict hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemarks("true");
                    break;
                }
            }
        }

        return hobbyList;
    }

    @Override
    public List<Dict> getSexList() {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "sex");
        return dictDao.list(param);
    }

    @Override
    public List<Dict> listByType(String type) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", type);
        return dictDao.list(param);
    }

	@Override
	public List<Dict> getTypes(String code) {
		return dictDao.createQuery().andEq("type", code).select();
	}

	@Override
	public PageQuery<Dict> queryByCondition(PageQuery<Dict> paras) {
		// TODO Auto-generated method stub
		return dictDao.queryByCondition(paras);
	}

}
