package com.ChanDao.Dao;

import com.ChanDao.model.Severity;
import java.util.List;

/**
 * Author ajiang
 * Created 2019/11/24 10:15
 */
public interface SeverityDao {
    public List<Severity> queryBugSeverity(int product, String assignedTo);
}
