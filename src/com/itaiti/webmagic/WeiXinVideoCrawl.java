package com.itaiti.webmagic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Header;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.common.collect.Lists;
import com.itaiti.http.HttpClient;
import com.itaiti.http.PostParameter;
import com.itaiti.http.Response;
import com.itaiti.http.WeiboException;

public class WeiXinVideoCrawl {
	
	private static String get_video_info = "http://vv.video.qq.com/getinfo?otype=xml&vid=%s";
	
	private static String get_video_key = "http://vv.video.qq.com/getkey?otype=xml&vid=%s&filename=%s&format=%s";
	
	private static String get_video_url = "http://www.15um.com/tools/weixin_v.php";
	
	public static HttpClient client = new HttpClient();
	
	public static String getvinfo(String vid) {
		String url = String.format(get_video_info, vid);
		System.out.println(url);
		PostParameter[] params = new PostParameter[]{};
		String result = null;
		try {
			Response response = client.post(url, params, false);
			result = response.getResponseAsString();
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getvkey(String vid, String filename, String format) {
		String url = String.format(get_video_key, vid, filename, format);
		System.out.println(url);
		PostParameter[] params = new PostParameter[]{};
		String result = null;
		try {
			Response response = client.post(url, params, false);
			result = response.getResponseAsString();
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getVideoUrl(String urlStr) {
		PostParameter urlParam = new PostParameter("url", urlStr);
		// urlParam
		PostParameter[] params = new PostParameter[]{};
		List<Header> headers = Lists.newArrayList();
		//headers.add(new Header("Accept", "application/json, text/javascript, */*; q=0.01"));
		//headers.add(new Header("Cookie", "UM_distinctid=15ec219b2441ff-0375f291db719b-8383667-1fa400-15ec219b245182; CNZZDATA3278819=cnzz_eid%3D684210239-1506494319-%26ntime%3D1506494319"));
		//headers.add(new Header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"));
		//headers.add(new Header("", ""));
		String result = null;
		try {
			Response response = client.post(get_video_url, params, false);
			result = response.getResponseAsString();
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取文章内视频真实播放地址
	 * 
	 * @param crawlUrl
	 */
	public static List<String> listVideoUrl(String crawlUrl) {
		List<String> listUrl = Lists.newArrayList();
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("http://www.15um.com/tools/weixin_v.php");
		httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("Host", "www.15um.com");
		httpPost.setHeader("Referer", "http://www.15um.com/tools/weixin_v.php");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("url", crawlUrl));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpPost);
			String str = EntityUtils.toString(response.getEntity(), "utf-8");
			//logger.info("listVideoUrl success.weixinUrl={},data={}", crawlUrl, str);
			Matcher matcherUrl = Pattern.compile("\"url\":\"([^\"]*)\"").matcher(str);
			while (matcherUrl.find()) {
				listUrl.add(StringEscapeUtils.unescapeJava(matcherUrl.group(1)));
			}
		} catch (Exception e) {
			//logger.error("listVideoUrl fail.weixinUrl={}", crawlUrl, e);
		}
		return listUrl;
	}
	
	public static void main(String[] args) {
		//String vinfo = getvinfo("n0371apcnd7");//k0147envws6
		//System.out.println(vinfo);
		
		//String vkey = getvkey("n0371apcnd7", "n0371apcnd7.p703.mp4", "10703");
		//String vkey = getvkey("n0371apcnd7", "n0371apcnd7.mp4", "2");
		//System.out.println(vkey);
		
		/*String url = "http%3A%2F%2Fmp.weixin.qq.com%2Fs%3Ftimestamp%3D1506495049%26src%3D3%26ver%3D1%26signature%3DzRwfsEDWwhRuUPgclb5lGkzn5M7C-5*0RGw63b5GdvbhsUiLS-Drt1Nfli3ha*aylL8xbRgloqi-IMnrzvyin8sxlZ*7B4JirQAPMIZvkyJPFASjoV-pAyDbgHce7JDYLDohRq8e1XwCS1SzcRpcNdZ-QSRXrDtj2si95XO-xA4%3D";
		String urlResult = getVideoUrl(url);
		System.out.println(urlResult);*/
		
		
		String url = "https://mp.weixin.qq.com/s?timestamp=1507518208&src=3&ver=1&signature=ziompBrcSbLeY2o2t4XkQoUXcwEoPZQlmQlPptOyKjduWCvb81w9nFb2IN14MIvno-PQnWVvcf3SqDxfqwe9IL3M6ZCoBV9pE8SZJnjCt1dPxTqSnDUL-3rdjNFJx4sfXiEnaSbYWPJBE6CpZovtY9smGL0NUg1qq0WpFeNTPE4=";
		System.out.println(listVideoUrl(url));
		
		
	}
}
