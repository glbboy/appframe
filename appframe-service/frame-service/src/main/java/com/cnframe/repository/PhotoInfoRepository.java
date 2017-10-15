package com.cnframe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cnframe.model.photo.PhotoInfo;


public interface PhotoInfoRepository extends BaseRepository<PhotoInfo, Long>{
	@Query("Select s From PhotoInfo s where s.year=? and sysdate()>activeTime and sysdate()<inactiveTime  order by s.shotDate desc")
	public List<PhotoInfo> findByYearOrderByShotDateDesc(Integer year); 
	@Query("Select s From PhotoInfo s where sysdate()>activeTime and sysdate()<inactiveTime group by s.year order by s.year desc")
	public List<PhotoInfo> findYears();
	@Query("Select s From PhotoInfo s,PhotoBookImgs t where sysdate()>s.activeTime and sysdate()<s.inactiveTime and s.id = t.imgId and t.bookId=?")
	public List<PhotoInfo> findByBookId(Long bookId);
	@Query("Select s From PhotoInfo s,PhotoBookImgs st where sysdate()>s.activeTime and sysdate()<s.inactiveTime and st.imgId = s.id group by st.bookId")
	public List<PhotoInfo> findBooks();
}
