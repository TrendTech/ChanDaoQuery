package com.ChanDao.task;

import com.ChanDao.Dao.Imp.BugDaoImp;
import com.ChanDao.Dao.Imp.ProductDaoImp;
import com.ChanDao.Dao.Imp.SeverityDaoImp;
import com.ChanDao.enums.BUG_TYPE;
import com.ChanDao.enums.SEVERITY;
import com.ChanDao.model.Bug;
import com.ChanDao.model.Severity;
import com.ChanDao.utils.DateUtil;
import com.ChanDao.utils.TelegramUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * Author ajiang
 * Created 2019/11/24 15:33
 */


@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@PropertySource("classpath:application.properties")
public class ScheduleChanDaoTask {
    @Value("${ChanDaoID}")
    private String ChanDaoID;
    @Value("${BOT_CHAT_ID}")
    private int BOT_CHAT_ID ;
    @Scheduled(cron = "${chandao.schedule}")
    private void configureTasks() throws IOException {
        runChanDaoTask();
    }

    public void runChanDaoTask() throws IOException {
        List ids = Arrays.asList(ChanDaoID.split(","));
        for(Object id:ids){
            int product = Integer.parseInt(id.toString());
            List<HashMap<String,Object>> BugList = new ArrayList<HashMap<String, Object>>();
            List<String> assigned = null;
            ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
            BugDaoImp bugDaoImp = (BugDaoImp)context.getBean("bugDaoImp");
            SeverityDaoImp severityDaoImp = (SeverityDaoImp)context.getBean("severityDaoImp");
            assigned = bugDaoImp.queryAssignedTo(product);
            for(String as:assigned){
                if(as == null) continue;
                HashMap<String,Object> bugSummary = new HashMap<String,Object>();
                bugSummary.put(BUG_TYPE.ASSIGN.getName(),as);
                List<Bug> bugs = bugDaoImp.queryAssignedToBugs(product,as);
                bugSummary.put(BUG_TYPE.NUMBER.getName(),bugs.size());
                List<Severity> severities = severityDaoImp.queryBugSeverity(product,as);
                bugSummary.put(BUG_TYPE.SEVERITY.getName(), ScheduleChanDaoTask.severityDescription(severities));
                int reopenNum = 0;
                bugSummary.put(BUG_TYPE.LONGUNRESOLVED.getName(), DateUtil.parseMilliseconds(1000*DateUtil.getDifference(new Date(bugs.get(0).getAssignedDate().getTime()),new Date(),0)));
                long summaySeconds = 0;
                for(Bug bug:bugs){
                    if(bug.getActivatedCount() != 0){
                        reopenNum++;
                    }
                    java.sql.Timestamp assignedDate = bug.getAssignedDate();
                    summaySeconds += DateUtil.getDifference(new Date(assignedDate.getTime()),new Date(),0);
                }
                bugSummary.put(BUG_TYPE.ALLUNRESOLVED.getName(),DateUtil.parseMilliseconds(1000*summaySeconds));
                bugSummary.put(BUG_TYPE.REOPEN.getName(),reopenNum);
                BugList.add(bugSummary);
            }
            displayBugSummay(product,BugList);
        }
    }

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

    public void displayBugSummay(int product,List<HashMap<String,Object>> BugList) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        ProductDaoImp productDaoImp = (ProductDaoImp)context.getBean("productDaoImp");
        StringBuilder displaySummary = new StringBuilder();
        String productName = productDaoImp.queryProductName(product);
        displaySummary.append(productName+" - 激活Bug: \n");
        displaySummary.append(BUG_TYPE.ASSIGN.getName()+" | ")
                .append(BUG_TYPE.NUMBER.getName()+" | ")
                .append(BUG_TYPE.SEVERITY.getName()+" | ")
                .append(BUG_TYPE.REOPEN.getName()+" | ")
                .append(BUG_TYPE.LONGUNRESOLVED.getName()+" | ")
                .append(BUG_TYPE.ALLUNRESOLVED.getName()+"\n");
        for(HashMap<String,Object> hm:BugList){
            displaySummary.append(hm.get(BUG_TYPE.ASSIGN.getName())
                    +" | "+hm.get(BUG_TYPE.NUMBER.getName())
                    +" | "+hm.get(BUG_TYPE.SEVERITY.getName())
                    +" | "+hm.get(BUG_TYPE.REOPEN.getName())
                    +" | "+hm.get(BUG_TYPE.LONGUNRESOLVED.getName())
                    +" | "+hm.get(BUG_TYPE.ALLUNRESOLVED.getName()) +"\n");
        }
        TelegramUtil.sendMessageRequest(BOT_CHAT_ID,displaySummary.toString());
    }
}
