package com.ChanDao;

import com.ChanDao.Dao.Imp.BugDaoImp;
import com.ChanDao.Dao.Imp.ProductDaoImp;
import com.ChanDao.Dao.Imp.SeverityDaoImp;
import com.ChanDao.enums.SEVERITY;
import com.ChanDao.model.Bug;
import com.ChanDao.model.Severity;
import com.ChanDao.utils.DateUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Author ajiang
 */

public class Maintest {

    public static StringBuilder severityDescription(List<Severity> severities){
        StringBuilder severityDescription = new StringBuilder();
        for(Severity seve:severities){
            switch (seve.getSeverity()){
                case 1:
                    severityDescription.append(SEVERITY.阻塞+":"+seve.getSummary()+" ");
                    break;
                case 2:
                    severityDescription.append(SEVERITY.严重+":"+seve.getSummary()+" ");
                    break;
                case 3:
                    severityDescription.append(SEVERITY.普通+":"+seve.getSummary()+" ");
                    break;
                case 4:
                    severityDescription.append(SEVERITY.轻微 +":"+seve.getSummary()+" ");
                    break;
            }
        }
        return  severityDescription;
    }

    public static void displayBugSummay(int product,List<HashMap<String,Object>> BugList){
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        ProductDaoImp productDaoImp = (ProductDaoImp)context.getBean("productDaoImp");
        StringBuilder displaySummary = new StringBuilder();
        String productName = productDaoImp.queryProductName(product);
        displaySummary.append(productName+"-激活Bug: \n");
        displaySummary.append("指派 | 数量 | 严重级别 | 重新激活 | 最长未解决 | 累计未解决 \n");
        for(HashMap<String,Object> hm:BugList){
            displaySummary.append(hm.get("AssignedTo")+" | "+hm.get("Number")+" | "+hm.get("Severity")+" | "+hm.get("Reopen")
                    +" | "+hm.get("LongTimeUnresolved")+" | "+hm.get("UnresolvedSummary")+"\n");
        }
        System.out.println(displaySummary);
    }

    public static void main(String[] args) {
        final int product = 18;
        List<HashMap<String,Object>> BugList = new ArrayList<HashMap<String, Object>>();
        List<String> assigned = null;
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        BugDaoImp bugDaoImp = (BugDaoImp)context.getBean("bugDaoImp");
        SeverityDaoImp severityDaoImp = (SeverityDaoImp)context.getBean("severityDaoImp");
        assigned = bugDaoImp.queryAssignedTo(product);
        for(String as:assigned){
            if(as == null) continue;
            HashMap<String,Object> bugSummary = new HashMap<String,Object>();
            bugSummary.put("AssignedTo",as);
            List<Bug> bugs = bugDaoImp.queryAssignedToBugs(product,as);
            bugSummary.put("Number",bugs.size());
            List<Severity> severities = severityDaoImp.queryBugSeverity(product,as);
            bugSummary.put("Severity",Maintest.severityDescription(severities));
            int reopenNum = 0;
            bugSummary.put("LongTimeUnresolved",DateUtil.parseMilliseconds(1000*DateUtil.getDifference(new Date(bugs.get(0).getAssignedDate().getTime()),new Date(),0)));
            long summaySeconds = 0;
            for(Bug bug:bugs){
                if(bug.getActivatedCount() != 0){
                    reopenNum++;
                }
                java.sql.Timestamp assignedDate = bug.getAssignedDate();
                summaySeconds += DateUtil.getDifference(new Date(assignedDate.getTime()),new Date(),0);
            }
            bugSummary.put("UnresolvedSummary",DateUtil.parseMilliseconds(1000*summaySeconds));
            bugSummary.put("Reopen",reopenNum);
            BugList.add(bugSummary);
        }
        Maintest.displayBugSummay(product,BugList);
    }
}