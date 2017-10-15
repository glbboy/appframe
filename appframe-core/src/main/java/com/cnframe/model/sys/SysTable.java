package com.cnframe.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;

@Entity
@Table(name = "Sys_Table")
public class SysTable extends AbstractModel {
	private static final long serialVersionUID = 1L;
	@Column(name = "TABLENAME")
	private String tableName;// 表名称
	@Column(name = "CLASSNAME")
	private String className;//
	@Column(name = "ADDCAPTION")
	private String addCaption;// 新增属性
	@Column(name = "DELCAPTION")
	private String delCaption;// 删除属性
	@Column(name = "DELCONFIRM")
	private String delConfirm;// 删除记录提示（内容中字段用具体值替换）
	@Column(name = "EDITCAPTION")
	private String editCaption;// 修改属性
	@Column(name = "FORMSTYLE")
	private Integer formStyle;// 窗体风格
	@Column(name = "FORCEFIT")
	private Integer forceFit;
	@Column(name = "FROZENROWS")
	private Integer frozenRows;
	@Column(name = "FROZENCOLUMNS")
	private Integer frozenColumns;
	@Column(name = "PAGING")
	private Integer paging;
	@Column(name = "WINWIDTH")
	private Integer winWidth;// 界面高度
	@Column(name = "WINHEIGHT")
	private Integer winHeight;// 界面宽度
	@Column(name = "LABELWIDTH")
	private Integer labelWidth;// 标签宽度
	@Column(name = "LABELTOP")
	private Integer labelTop;// 标签是否上部显示
	@Column(name = "LABELSEPARATOR")
	private String labelSeparator;// 字段标签与字段之间的分隔符
	@Column(name = "TABLETREE")
	private Integer tableTree;// 是否树形结构
	@Column(name = "SORT")
	private Integer sort;// 排序
	@Column(name = "FIRSTCOLUMN")
	private Integer firstColumn;// 首列 0 无 1RowNumbere
	@Column(name = "TREECOLUMN")
	private String treeColumn;//;
	@Column(name = "RIGHTPERMIT")
	private String rightPermit;
	@Column(name = "DETAILTABLEID")
	private String detailTableId;
	@Column(name = "RELCOL")
	private String relCol;
	@Column(name = "PAGEROWS")
	private Integer pageRows;
	@Column(name = "ORDERBY")
	private String orderBy;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAddCaption() {
		return addCaption;
	}

	public void setAddCaption(String addCaption) {
		this.addCaption = addCaption;
	}

	public String getDelCaption() {
		return delCaption;
	}

	public void setDelCaption(String delCaption) {
		this.delCaption = delCaption;
	}

	public String getEditCaption() {
		return editCaption;
	}

	public void setEditCaption(String editCaption) {
		this.editCaption = editCaption;
	}

	public Integer getFormStyle() {
		return formStyle;
	}

	public void setFormStyle(Integer formStyle) {
		this.formStyle = formStyle;
	}

	public Integer getForceFit() {
		return forceFit;
	}

	public void setForceFit(Integer forceFit) {
		this.forceFit = forceFit;
	}

	public Integer getWinWidth() {
		return winWidth;
	}

	public void setWinWidth(Integer winWidth) {
		this.winWidth = winWidth;
	}

	public Integer getWinHeight() {
		return winHeight;
	}

	public void setWinHeight(Integer winHeight) {
		this.winHeight = winHeight;
	}

	public Integer getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(Integer labelWidth) {
		this.labelWidth = labelWidth;
	}

	public Integer getLabelTop() {
		return labelTop;
	}

	public void setLabelTop(Integer labelTop) {
		this.labelTop = labelTop;
	}

	public Integer getTableTree() {
		return tableTree;
	}

	public void setTableTree(Integer tableTree) {
		this.tableTree = tableTree;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public Integer getFirstColumn() {
		return firstColumn;
	}

	public void setFirstColumn(Integer firstColumn) {
		this.firstColumn = firstColumn;
	}

	public String getDelConfirm() {
		return delConfirm;
	}

	public void setDelConfirm(String delConfirm) {
		this.delConfirm = delConfirm;
	}

	public String getLabelSeparator() {
		return labelSeparator;
	}

	public void setLabelSeparator(String labelSeparator) {
		this.labelSeparator = labelSeparator;
	}

	public String getTreeColumn() {
		return treeColumn;
	}

	public void setTreeColumn(String treeColumn) {
		this.treeColumn = treeColumn;
	}

	public String getRightPermit() {
		return rightPermit;
	}

	public void setRightPermit(String rightPermit) {
		this.rightPermit = rightPermit;
	}

	public String getDetailTableId() {
		return detailTableId;
	}

	public void setDetailTableId(String detailTableId) {
		this.detailTableId = detailTableId;
	}

	public String getRelCol() {
		return relCol;
	}

	public void setRelCol(String relCol) {
		this.relCol = relCol;
	}

	public Integer getPageRows() {
		return pageRows;
	}

	public void setPageRows(Integer pageRows) {
		this.pageRows = pageRows;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getFrozenRows() {
		return frozenRows;
	}

	public void setFrozenRows(Integer frozenRows) {
		this.frozenRows = frozenRows;
	}

	public Integer getFrozenColumns() {
		return frozenColumns;
	}

	public void setFrozenColumns(Integer frozenColumns) {
		this.frozenColumns = frozenColumns;
	}

	public Integer getPaging() {
		return paging;
	}

	public void setPaging(Integer paging) {
		this.paging = paging;
	}
}
