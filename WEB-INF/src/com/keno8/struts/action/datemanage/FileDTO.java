package com.keno8.struts.action.datemanage;

public class FileDTO {
	private String fileName;
	private long fileSize;
	private String encodeFileName;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the encodeFileName
	 */
	public String getEncodeFileName() {
		return encodeFileName;
	}
	/**
	 * @param encodeFileName the encodeFileName to set
	 */
	public void setEncodeFileName(String encodeFileName) {
		this.encodeFileName = encodeFileName;
	}
}
