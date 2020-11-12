package e.commerce.shopping.guide.mvp;

/**
 * View 层 基础方法接口
 */
public interface BaseView {

    //显示loading
    void showLoading();

    //显示loading   并且不可以被取消掉
    void showLoading(boolean cancel);

    //关闭loading
    void closeLoading();

    //显示吐司
    void showToast(String msg);


}
