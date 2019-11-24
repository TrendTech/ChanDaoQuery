package com.ChanDao.Dao;

import com.ChanDao.model.Bug;
import java.util.List;

/**
 * Author ajiang
 */

public interface BugDao {
    public List<Bug> allbug(int product);
    public List<String> queryAssignedTo(int product);
    public List<Bug> queryAssignedToBugs(int product,String assignedTo);

}
