/**
 * 
 */
package org.zhangmz.pickles.modules.vo;

/**
 * @ClassName:PageRequest.java
 * @Description: 分页查询请求
 * @author:张孟志
 * @date:2016年3月10日下午12:30:30
 * @version V1.0
 * 说明：jqGrid默认的请求参数
 */
public class PageRequest {
	private String TOKEN;
	private boolean _search;
	private long nd;
	private int rows;
	private int page;
	private String sidx;
	private String sord;
	// filters 应该是一个对象
	// filters:{"groupOp":"AND","rules":[{"field":"code","op":"eq","data":"root"}]}
	private String filters;
	private String searchField;
	private String searchString;
	private String searchOper;
	
	
	/**
	 * @return the filters
	 */
	public String getFilters() {
		return filters;
	}
	/**
	 * @param filters the filters to set
	 */
	public void setFilters(String filters) {
		this.filters = filters;
	}
	/**
	 * @return the searchField
	 */
	public String getSearchField() {
		return searchField;
	}
	/**
	 * @param searchField the searchField to set
	 */
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	/**
	 * @return the searchOper
	 */
	public String getSearchOper() {
		return searchOper;
	}
	/**
	 * @param searchOper the searchOper to set
	 */
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
	/**
	 * @return the tOKEN
	 */
	public String getTOKEN() {
		return TOKEN;
	}
	/**
	 * @param tOKEN the tOKEN to set
	 */
	public void setTOKEN(String tOKEN) {
		TOKEN = tOKEN;
	}
	/**
	 * @return the _search
	 */
	public boolean is_search() {
		return _search;
	}
	/**
	 * @param _search the _search to set
	 */
	public void set_search(boolean _search) {
		this._search = _search;
	}
	/**
	 * @return the nd
	 */
	public long getNd() {
		return nd;
	}
	/**
	 * @param nd the nd to set
	 */
	public void setNd(long nd) {
		this.nd = nd;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the sidx
	 */
	public String getSidx() {
		return sidx;
	}
	/**
	 * @param sidx the sidx to set
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	/**
	 * @return the sord
	 */
	public String getSord() {
		return sord;
	}
	/**
	 * @param sord the sord to set
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}
}
