
package com.api.common.utils;
import java.io.Serializable;
import java.util.List;

public class NavMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pid;
	private String title;
	private String font = "larry-icon";
	private String icon;
	private String url;
	/**
	 * 是否展开  true：展开
	 */
	private boolean spread;
	private List<?> children;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public List<?> getChildren() {
		return children;
	}

	public void setChildren(List<?> children) {
		this.children = children;
	}




}
