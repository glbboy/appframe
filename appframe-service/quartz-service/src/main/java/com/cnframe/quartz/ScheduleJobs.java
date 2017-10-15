package com.cnframe.quartz;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cnframe.model.photo.PhotoBook;
import com.cnframe.model.photo.PhotoBookImgs;
import com.cnframe.model.photo.PhotoInfo;
import com.cnframe.model.photo.PhotoInfoDetail;
import com.cnframe.modules.utils.DateUtil;
import com.cnframe.modules.utils.PicExif;
import com.cnframe.repository.PhotoBookImgsRepository;
import com.cnframe.repository.PhotoBookRepository;
import com.cnframe.repository.PhotoInfoDetailRepository;
import com.cnframe.repository.PhotoInfoRepository;

import ch.qos.logback.core.util.FileUtil;

@Component
public class ScheduleJobs {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleJobs.class);
	public final static long SECOND = 1 * 1000;
	FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public final static String fileSourcePath = "D:/photos/";
    public final static String fileTargetPath = "D:/workspaces/appframe-web/src/assets/photo/";
    private final static String format = "yyyy:MM:dd HH:mm:ss";
	@Autowired
	private PhotoInfoRepository photoInfoRepository;
	@Autowired
	private PhotoInfoDetailRepository photoInfoDetailRepository;
	@Autowired
	private PhotoBookImgsRepository photoBookImgsRepository;
	@Autowired
	private PhotoBookRepository photoBookRepository;
	
	@Scheduled(fixedDelay = SECOND * 20)
	public void fixedDelayJob() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		System.out.println("[FixedDelayJob Execute]" + fdf.format(new Date()));
	}
	public void DealFileDate(ArrayList<PhotoInfoDetail> photoInfoDetailList,PhotoInfo photoInfo){
		for(int i=0;i<photoInfoDetailList.size();i++){
			PhotoInfoDetail photoInfoDetail = photoInfoDetailList.get(i);
			if ("Date/Time Original".equals(photoInfoDetail.getTagCode())){
				Date shotDate = DateUtil.stringtoDate(photoInfoDetail.getTagValue(), format);
				photoInfo.setShotDate(shotDate);
				photoInfo.setYear(DateUtil.getYear(shotDate));
				photoInfo.setImgPath(DateUtil.getYear(shotDate)+"/");
			}else if ("File Modified Date".equals(photoInfoDetail.getTagCode())){
				//星期一 八月 10 13:05:36 +08:00 2015
				if (photoInfo.getShotDate() == null){
					SimpleDateFormat sdf =new SimpleDateFormat("EEE MMM dd HH:mm:ss +08:00 yyyy",Locale.CHINA);
					Date shotDate;
					try {
						shotDate = sdf.parse(photoInfoDetail.getTagValue());
						photoInfo.setShotDate(shotDate);
						photoInfo.setYear(DateUtil.getYear(shotDate));
						photoInfo.setImgPath(DateUtil.getYear(shotDate)+"/");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	//public DealFile(sourceFile,DestFile)
	public void DealFiles(String sourcePath,String bookName){
		File fFile=new File(sourcePath);
		//Collection<File> listFiles = FileUtils.listFiles(new File("M:/FileTest"), null, null);
		File[] files=fFile.listFiles();
		if(files == null || files.length == 0) {
			logger.debug("没有文件需要处理!");
			return;
		}
		PhotoBook photoBook = null;
		if (StringUtils.isNotBlank(bookName)){
			photoBook = photoBookRepository.findByBookName(bookName);
			if (photoBook == null){
				photoBook = new PhotoBook();
				photoBook.setBookName(bookName);
				photoBook = photoBookRepository.saveAndFlush(photoBook);
			}
		}
		for(File f:files) {
			System.out.println(f.getAbsolutePath()+f.getName());
			if (f.isDirectory()){
				DealFiles(sourcePath+f.getName()+"/",f.getName());
				f.delete();//此时下面文件为空，可以删除
				continue;
			}
			String fileName = f.getName();  
	        String suffix = fileName.substring(fileName.lastIndexOf("."));
	        //简单一点通过后缀判断是否图片
	        String[] imagType={".jpg",".png",".bmp",".gif",".jpeg",".JPG",".PNG",".BMP",".GIF",".JPEG"};
            List<String> imageTyepLists=Arrays.asList(imagType);
            if(!imageTyepLists.contains(suffix)){
            	f.delete();
				continue;
            }
			UUID uuid = UUID.randomUUID();
			String newImgName = uuid.toString()+suffix.toLowerCase();
			PhotoInfo photoInfo = new PhotoInfo();
			photoInfo.setImgName(newImgName);
			photoInfo = photoInfoRepository.saveAndFlush(photoInfo);
			ArrayList<PhotoInfoDetail> photoInfoDetailList = PicExif.getMetaData(sourcePath+fileName, photoInfo.getId());
			DealFileDate(photoInfoDetailList,photoInfo);
			photoInfoDetailRepository.save(photoInfoDetailList);
			photoInfoRepository.saveAndFlush(photoInfo);
			if (StringUtils.isNotBlank(bookName)){
				PhotoBookImgs photoBookImgs = new PhotoBookImgs();
				photoBookImgs.setBookId(photoBook.getId());
				photoBookImgs.setImgId(photoInfo.getId());
				photoBookImgs.setCreateTime(new Date());
				photoBookImgsRepository.saveAndFlush(photoBookImgs);
			}
			
			try {
				File myPath = new File( fileTargetPath );  
	            if ( !myPath.exists()){//若此目录不存在，则创建之  
	                myPath.mkdir();  
	                System.out.println("创建文件夹路径为："+ fileTargetPath);  
	            }  
				FileUtils.copyFile(f, new File(fileTargetPath+photoInfo.getImgPath()+newImgName));
				FileUtils.forceDelete(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	@Scheduled(cron = "0 0/1 6-22 * * ?")
	public void executeDealImgs(){
		DealFiles(fileSourcePath,"");	
	}
	@Scheduled(cron = "0 0/2 8-20 * * ?")
	public void executeFileDownLoadTask() {

		// 间隔2分钟,执行工单上传任务
		Thread current = Thread.currentThread();
		System.out.println("定时任务1:" + current.getId());
		logger.info("ScheduledTest.executeFileDownLoadTask 定时任务1:" + current.getId() + ",name:" + current.getName());
	}

	@Scheduled(cron = "0 0/1 8-20 * * ?")
	public void executeUploadTask() {
		// 间隔1分钟,执行工单上传任务
		Thread current = Thread.currentThread();
		System.out.println("定时任务2:" + current.getId());
		logger.info("ScheduledTest.executeUploadTask 定时任务2:" + current.getId() + ",name:" + current.getName());
	}

	@Scheduled(cron = "0 0/3 5-23 * * ?")
	public void executeUploadBackTask() {

		// 间隔3分钟,执行工单上传任务
		Thread current = Thread.currentThread();
		System.out.println("定时任务3:" + current.getId());
		logger.info("ScheduledTest.executeUploadBackTask 定时任务3:" + current.getId() + ",name:" + current.getName());
	}
}