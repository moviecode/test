package com.itaiti.webmagic.pageprocessor;

import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class WxAccountPageProcessor implements PageProcessor {
	
	private static Logger logger = LoggerManager.getLogger(WxAccountPageProcessor.class);
	
	private Site site = Site.me().setSleepTime(0).setTimeOut(10000).setCycleRetryTimes(10)
            .setUserAgent("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");

	
	@Override
	public void process(Page page) {
		logger.info("wx_account:{}",page.getRequest().getExtra("wx_account"));
		//获取<title></title>之间的text
		logger.info("标题：{}", page.getHtml().xpath("//title/text()").get());
		//logger.info("微信号：{}",page.getHtml().xpath("//p[@class='info']").get());
		logger.info("微信号：{}", page.getHtml().xpath("//p[@class='info']/label[@name='em_weixinhao']/text()").get());
		logger.info("文章列表url：{}", page.getHtml().xpath("//p[@class='tit']//a/@href").get());
		logger.info("最后更新时间：{}", page.getHtml().xpath("//*[@id=\"sogou_vr_11002301_box_0\"]/dl[2]/dd/span/script").get());
		logger.info("最后更新时间：{}", page.getHtml().regex("<script>document.write\\(timeConvert\\('(\\d{10})'\\)\\)</script>", 1).get());
		
		
		//page.getRequest().putExtra(key, value)
		//*[@id="sogou_vr_11002301_box_0"]/dl[2]/dd/span/script
		//*[@id="sogou_vr_11002301_box_0"]/dl[2]/dd/span/script
	}

	@Override
	public Site getSite() {
		return site;
	}
	

}
