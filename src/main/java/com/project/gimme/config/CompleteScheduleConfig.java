package com.project.gimme.config;

import com.project.gimme.pojo.MsgBot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:41
 */
@Configuration
@EnableScheduling
public class CompleteScheduleConfig implements SchedulingConfigurer {

    @Mapper
    public interface CronMapper {
        @Select("select * from msg_bot limit 1")
        MsgBot getMsgBot();
    }

    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行定时任务2: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    MsgBot msgBot = cronMapper.getMsgBot();
                    if (msgBot != null) {
                        String cron = msgBot.getCron();
                        //2.2 合法性校验.
                        if (StringUtils.isEmpty(cron)) {
                            // Omitted Code ..
                            //TODO:待实现
                        }
                        //2.3 返回执行周期(Date)
                        return new CronTrigger(cron).nextExecutionTime(triggerContext);
                    } else {
                        return null;
                    }
                }
        );
    }

}
