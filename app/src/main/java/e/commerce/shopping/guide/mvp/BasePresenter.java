package e.commerce.shopping.guide.mvp;

public class BasePresenter<T extends BaseView> {


    /**
     * 绑定的view
     */
    private T mView;

    public void attachView(T mvpView) {
        this.mView = mvpView;
    }

    public void detachView() {
        this.mView = null;
    }

    public boolean isBindView(){
        return this.mView!=null;
    }

    public T getView(){
        return mView;
    }

    public void showLoading(){
        if(isBindView()){
            getView().showLoading();
        }
    }

    public void showLoading(boolean cancel){
        if(isBindView()){
            getView().showLoading(cancel);
        }
    }

    public void closeLoading(){
        if(isBindView()){
            getView().closeLoading();
        }
    }

    public void showToast(String msg){
        if(isBindView()){
            getView().showToast(msg);
        }
    }
}