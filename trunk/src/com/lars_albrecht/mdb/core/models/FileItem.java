/**
 * 
 */
package com.lars_albrecht.mdb.core.models;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.lars_albrecht.general.utilities.ChecksumSHA1;

/**
 * @author albrela
 * 
 */
public class FileItem implements IPersistable {

	private Integer							id			= null;
	private String							name		= null;
	private String							fullpath	= null;
	private String							dir			= null;
	private Long							size		= null;
	private String							ext			= null;
	private String							filehash	= null;
	private ArrayList<FileAttributeList>	attributes	= null;

	/**
	 * 
	 */
	public FileItem() {
		super();
		this.attributes = new ArrayList<FileAttributeList>();
	}

	/**
	 * @param fullpath
	 */
	public FileItem(final String fullpath) {
		super();
		this.fullpath = fullpath;
	}

	/**
	 * @param id
	 * @param name
	 * @param fullpath
	 * @param dir
	 * @param size
	 * @param ext
	 */
	public FileItem(final Integer id, final String name, final String fullpath, final String dir, final Long size, final String ext) {
		super();
		this.id = id;
		this.name = name;
		this.fullpath = fullpath;
		this.dir = dir;
		this.size = size;
		this.ext = ext;
	}

	/**
	 * @param name
	 * @param fullpath
	 * @param dir
	 * @param size
	 * @param ext
	 */
	public FileItem(final String name, final String fullpath, final String dir, final Long size, final String ext) {
		super();
		this.name = name;
		this.fullpath = fullpath;
		this.dir = dir;
		this.size = size;
		this.ext = ext;
	}

	public FileItem generateFilehash() throws Exception {
		if (this.getFullpath() != null && new File(this.getFullpath()).exists()) {
			this.setFilehash(ChecksumSHA1.getSHA1Checksum(this.getFullpath()));
		}
		return this;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getFullpath() {
		return this.fullpath;
	}

	public void setFullpath(final String fullpath) {
		this.fullpath = fullpath;
	}

	public String getDir() {
		return this.dir;
	}

	public void setDir(final String dir) {
		this.dir = dir;
	}

	public Long getSize() {
		return this.size;
	}

	public void setSize(final Long size) {
		this.size = size;
	}

	public String getExt() {
		return this.ext;
	}

	public void setExt(final String ext) {
		this.ext = ext;
	}

	public ArrayList<FileAttributeList> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(final ArrayList<FileAttributeList> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the filehash
	 */
	public String getFilehash() {
		return this.filehash;
	}

	/**
	 * @param filehash
	 *            the filehash to set
	 */
	public void setFilehash(final String filehash) {
		this.filehash = filehash;
	}

	@Override
	public HashMap<String, Object> toHashMap() {
		final HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
		if (this.getId() != null) {
			tempHashMap.put("id", this.getId());
		}
		tempHashMap.put("name", this.getName());
		tempHashMap.put("fullpath", this.getFullpath());
		tempHashMap.put("dir", this.getDir());
		tempHashMap.put("size", this.getSize());
		tempHashMap.put("ext", this.getExt());
		tempHashMap.put("filehash", this.getFilehash());

		return tempHashMap;
	}

	@Override
	public Object fromHashMap(final HashMap<String, Object> map) {
		final FileItem result = new FileItem();
		if (map.containsKey("id")) {
			result.setId((Integer) map.get("id"));
		}
		if (map.containsKey("name")) {
			result.setName((String) map.get("name"));
		}
		if (map.containsKey("fullpath")) {
			result.setFullpath((String) map.get("fullpath"));
		}

		if (map.containsKey("dir")) {
			result.setDir((String) map.get("dir"));
		}

		if (map.containsKey("size")) {
			if (map.get("size") instanceof Integer) {
				result.setSize(((Integer) map.get("size")).longValue());
			} else {
				result.setSize((Long) map.get("size"));
			}
		}

		if (map.containsKey("ext")) {
			result.setExt((String) map.get("ext"));
		}

		if (map.containsKey("filehash") && map.get("filehash") != "" && !map.get("filehash").equals("")) {
			result.setFilehash((String) map.get("filehash"));
		}

		return result;
	}

	@Override
	public String toString() {
		return "Id: " + this.id + " | " + "Name: " + this.name + " | " + "Fullpath: " + this.fullpath + " | " + "Dir: " + this.dir + " | "
				+ "Size: " + this.size + " | " + "Ext: " + this.ext;
	}

	@Override
	public String getDatabaseTable() {
		return "fileInformation";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (this.id == null) {
			result = prime * result + ((this.dir == null) ? 0 : this.dir.hashCode());
			result = prime * result + ((this.ext == null) ? 0 : this.ext.hashCode());
			result = prime * result + ((this.filehash == null) ? 0 : this.filehash.hashCode());
			result = prime * result + ((this.fullpath == null) ? 0 : this.fullpath.hashCode());
			result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
			result = prime * result + ((this.size == null) ? 0 : this.size.hashCode());
		} else {
			result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FileItem)) {
			return false;
		}
		final FileItem other = (FileItem) obj;
		if (this.id != null && other.id != null && !this.id.equals(other.id)) {
			return false;
		} else if (this.id != null && other.id != null && this.id.equals(other.id)) {
			return true;
		}
		if (this.fullpath == null) {
			if (other.fullpath != null) {
				return false;
			}
		} else if (!this.fullpath.equals(other.fullpath)) {
			return false;
		} else if (this.fullpath.equals(other.fullpath)) {
			return true;
		}
		if (this.dir == null) {
			if (other.dir != null) {
				return false;
			}
		} else if (!this.dir.equals(other.dir)) {
			return false;
		}
		if (this.ext == null) {
			if (other.ext != null) {
				return false;
			}
		} else if (!this.ext.equals(other.ext)) {
			return false;
		}
		if (this.filehash == null) {
			if (other.filehash != null) {
				return false;
			}
		} else if (!this.filehash.equals(other.filehash)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.size == null) {
			if (other.size != null) {
				return false;
			}
		} else if (!this.size.equals(other.size)) {
			return false;
		}
		return true;
	}
}
