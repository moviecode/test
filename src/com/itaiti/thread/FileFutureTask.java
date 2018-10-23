package com.itaiti.thread;


import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;

/**
 * 
 */
public class FileFutureTask extends FutureTask<FileConfig> {

    private Logger logger = LoggerManager.getLogger(FileFutureTask.class);

//    private static final int PAGE_SIZE=100;
    public FileFutureTask(Callable<FileConfig> callable) {
        super(callable);
    }

    @Override
    protected void done() {
        try {
            logger.info("FileFutureTask start");
            FileConfig fc = get();
            List<String> ss = fc.getUserIds();
            logger.info("FileFutureTask size{}",ss.size());
            for(String s : ss) {
            	logger.info("userId:{}", s);
            }
            /*int pages = 0;
            if(ss.size()%PAGE_SIZE>0){
                pages = ss.size()/PAGE_SIZE +1;
            }else{
                pages = ss.size()/PAGE_SIZE;
            }
            for(int i=0;i<pages;i++){
                int start = i*PAGE_SIZE;
                int end = (i+1)*PAGE_SIZE;
                if(end>ss.size()){
                    end = ss.size();
                }
                this.send(ss, start, end,counponConfigTaskBean);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SendFutureTask“Ï≥£{}",e.getMessage());
        }
    }
}
