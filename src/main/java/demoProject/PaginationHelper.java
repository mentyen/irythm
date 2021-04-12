package demoProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

public class PaginationHelper {

	private Object[] values = null;
	private int itemsPerPage = 0;
	private int pageCount = 0;
	private Map<Integer, List<Object>> pages = null;

	public PaginationHelper(Object[] values, int itemsPerPage) {
		if (isValuesValid(values) && isItemsPerPageValid(itemsPerPage)) {
			this.values = values;
			this.itemsPerPage = itemsPerPage;
		} else {
			Assert.fail();
		}
		this.pageCount = round((double) values.length / (double) itemsPerPage);
		this.pages = getPages();
	}

	private Map<Integer, List<Object>> getPages() {
		Map<Integer, List<Object>> map = new HashMap<Integer, List<Object>>();
		List<Object> currentIterationList = new LinkedList<Object>(Arrays.asList(values));
		int pageNum = 0;

		List<Object> currentPageIndexes = new ArrayList<Object>();
		for (int i = 0; i < currentIterationList.size(); i++) {
			currentPageIndexes.add(i);
			if (currentPageIndexes.size() == itemsPerPage) {
				map.put(pageNum, currentPageIndexes);
				pageNum++;
				currentPageIndexes = new ArrayList<Object>();
			}
		}

		if (currentPageIndexes.size() != 0)
			map.put(pageNum, currentPageIndexes);

		return map;
	}
	/*
	 * itemsPerPage should be whole num itemsPerPage should be >0
	 * 
	 */

	private boolean isItemsPerPageValid(int itemsPerPage) {
		if (itemsPerPage > 0) {
			return true;
		} else {
			System.err.println("Items Per Page should be greater then 0.");
			return false;
		}

	}

	private boolean isWholeNumber(int value) {
		if (value % 1 == 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isValuesValid(Object[] values) {
		if (values.length > 0) {
			return true;
		} else {
			System.err.println("Values count :--> " + values.length + " should be > 0");
			return false;
		}
	}

	private int round(double value) {
		double dAbs = Math.abs(value);
		int i = (int) dAbs;
		double diff = dAbs - (double) i;
		if (diff < 0.1) {
			return value < 0 ? -i : i;
		} else {
			return value < 0 ? -(i + 1) : i + 1;
		}
	}

	public int pageCount() {
		System.out.println("Page count:-->" + pageCount);
		return pageCount;

	}

	public int itemCount() {
		System.out.println("Item count:-->" + values.length);
		return values.length;
	}

	/*
	 * helper.pageItemCount(0) # should == 4 helper.pageItemCount(1) # last page
	 * should == 2 helper.pageItemCount(2) # should == -1 since the page is invalid
	 */
	public int pageItemCount(int selectPage) {
		if (selectPage >= pageCount || selectPage < 0) {
			System.err.println("Selected page as:-->" + selectPage + " fail to exist, as result:-->-1");
			return -1;
		} else {
			System.out.println("Item count per selected page:--> " + getPageItemCount(selectPage));
			return getPageItemCount(selectPage);
		}

	}

	private int getPageItemCount(int selectPage) {
		return pages.get(selectPage).size();
	}

	/*
	 * helper.pageIndex(5)#should==1(zero based index) helper.pageIndex(2)#should==0
	 * helper.pageIndex(20)#should==-1 helper.pageIndex(-10)#should==-1because neg
	 */

	public int pageIndex(int value) {
		for (Entry<Integer, List<Object>> entry : pages.entrySet()) {
			for (Object index : entry.getValue()) {
				if (index.equals(value)) {
					System.out.println(
							"Item number :--> " + value + " populates on page with index:-->" + entry.getKey());
					return entry.getKey();
				}
			}
		}
		System.err.println("Item number :--> " + value + " fail to populate on existing pages, as result :--> -1");
		return -1;
	}

}
