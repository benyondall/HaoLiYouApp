package e.commerce.shopping.guide.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import e.commerce.shopping.guide.common.dialog.AvicAlertDialog;
import e.commerce.shopping.guide.common.dialog.AvicLoadingDialog;
import e.commerce.shopping.guide.common.tools.L;
import e.commerce.shopping.guide.common.tools.ToastUtil;

/**
 * @param <T>
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

  private Unbinder unbinder;
  protected T presenter;
  private View view;
  private ViewGroup container;
  private Bundle savedInstanceState;
  private LayoutInflater inflater;
  private boolean isViewCreated = false;

  protected AvicAlertDialog avicAlertDialog;
  protected AvicLoadingDialog avicLoadingDialog;
//  private final String TAG = "BaseFragment";
  protected Activity oThis;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    if (!isViewCreated) {
      this.container = container;
      this.savedInstanceState = savedInstanceState;
      this.inflater = inflater;
      this.oThis = getActivity();
      avicLoadingDialog = new AvicLoadingDialog(oThis);
      L.TAG = this.getClass().getName();
      try {
        init(0, null);
      } catch (Exception e) {
        e.printStackTrace();
        L.e( e.getMessage());
      }
    }
    // TODO Use fields...
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (!isViewCreated) {
      try {
        init(0, null);
      } catch (Exception e) {
        e.printStackTrace();
        L.e(e.getMessage());
      }
    }
  }

  protected void init(int layoutId, Class<T> clazz) throws Exception {

    if (layoutId == 0 || clazz == null) {
      throw new Exception("this fragment must be init!");
    }

    if (view == null) {
      L.v( "INIT VIEW = ======================");
      view = inflater.inflate(layoutId, container, false);
      unbinder = ButterKnife.bind(this, view);
      presenter = clazz.newInstance();
      presenter.attachView(this);
      isViewCreated = true;
    } else {
      ViewGroup parent = (ViewGroup) view.getParent();
      if (null != parent) {
        parent.removeView(view);
      }
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
//        if(unbinder!=null)
//            unbinder.unbind();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (unbinder != null)
      unbinder.unbind();
    if (presenter != null) {
      presenter.detachView();
    }
  }


  /**
   * 显示一个需要确认的提示
   */
  public void showAlertDialog(String msg, AvicAlertDialog.OkListener listener) {
    avicAlertDialog = new AvicAlertDialog(oThis, android.R.drawable.ic_dialog_alert, msg, 1);
    avicAlertDialog.setOkListener(listener);
  }

  /**
   * 显示进度条
   */
  @Override
  public void showLoading() {
    avicLoadingDialog.show();
  }

  /**
   * 显示进度条
   */
  @Override
  public void showLoading(boolean canCleable) {
    avicLoadingDialog.show();
  }

  /**
   * 关闭进度条
   */
  @Override
  public void closeLoading() {
    avicLoadingDialog.close();
  }

  /**
   * 显示吐司
   *
   * @param msg
   */
  @Override
  public void showToast(String msg) {
    ToastUtil.showToast(oThis, msg);
  }


}
