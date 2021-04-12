package demoProject;

public class TestClass {

	public static void main(String[] args) {

		Object[] pageItems = { "a", "v", "g", "b", "5", "7" };
		int itemsPerPage = 1;
		PaginationHelper helper = new PaginationHelper(pageItems, itemsPerPage);

		helper.pageCount();
		helper.itemCount();
		helper.pageItemCount(0);
		helper.pageItemCount(1);
		helper.pageItemCount(2);
		helper.pageIndex(0);
		helper.pageIndex(2);
		helper.pageIndex(20);
		helper.pageIndex(-10);
	}

}
