package com.gcit.lms.entity;

import java.io.Serializable;

public class BookLoan implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1577383530394711881L;
	private int bookId;
	private int cardNo;
	private int branchId;
	private String  checkoutTime;
	private String  dueDate;
	private String  checkinDate;
	
	
	
	
	
	
	/**
	 * @return the book
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(int bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the borrower
	 */
	public int getBorrower() {
		return cardNo;
	}
	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(int cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the libraryBranch
	 */
	public int getLibraryBranch()  {
		
		
		
		return branchId;
	}
	/**
	 * @param libraryBranch the libraryBranch to set
	 */
	public void setLibraryBranch(int libraryBranch) {
		this.branchId = libraryBranch;
	}
	/**
	 * @return the checkoutTime
	 */
	public String getCheckoutTime() {
		return checkoutTime;
	}
	/**
	 * @param checkoutTime the checkoutTime to set
	 */
	public void setCheckoutTime(String checkoutTime) {
		this.checkoutTime = checkoutTime;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the checkin
	 */
	public String getCheckin() {
		return checkinDate;
	}
	/**
	 * @param checkin the checkin to set
	 */
	public void setCheckin(String checkin) {
		this.checkinDate = checkin;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + branchId;
		result = prime * result + cardNo;
		result = prime * result + ((checkinDate == null) ? 0 : checkinDate.hashCode());
		result = prime * result + ((checkoutTime == null) ? 0 : checkoutTime.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookLoan other = (BookLoan) obj;
		if (bookId != other.bookId)
			return false;
		if (branchId != other.branchId)
			return false;
		if (cardNo != other.cardNo)
			return false;
		if (checkinDate == null) {
			if (other.checkinDate != null)
				return false;
		} else if (!checkinDate.equals(other.checkinDate))
			return false;
		if (checkoutTime == null) {
			if (other.checkoutTime != null)
				return false;
		} else if (!checkoutTime.equals(other.checkoutTime))
			return false;
		return true;
	}
	
	
	

}
